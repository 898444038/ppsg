package com.ming.system.aspect;

import com.ming.system.config.security.MyInvocationSecurityMetadataSourceService;
import com.ming.system.entity.Role;
import com.ming.system.entity.User;
import com.ming.system.service.LogService;
import com.ming.system.utils.ResultMsg;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/12/9 0009.
 */
@Aspect
@Component
public class RoleAspect {

    @Pointcut(value = "@annotation(com.ming.system.annotation.Role)")
    public void point(){}

    @Before(value="point()")
    public void before(){

    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        List<Role> authorities = user.getAuthorities();
        Map map = MyInvocationSecurityMetadataSourceService.getMap();
        Class clazz = joinPoint.getTarget().getClass();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();//获取访问的方法

        String mapping = AspectUtils.getMapping(clazz,method);
        System.out.println("Request Mapping : "+mapping);

        //Admin 有所有权限
        for(Role role : authorities){
            if("ADMIN".equals(role.getName())){
                return joinPoint.proceed();
            }
        }

        Iterator it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry)it.next();
            String url = (String)entry.getKey();
            if(url!=null && url.trim().equals(mapping.trim())){
                Collection<ConfigAttribute> list = (Collection<ConfigAttribute>)entry.getValue();
                for(ConfigAttribute attr : list){
                    for(Role role : authorities){
                        if(attr.getAttribute().equals(role.getName())){
                            return joinPoint.proceed();
                        }
                    }
                }
            }
        }
        return ResultMsg.auth();
    }

    @AfterReturning(value = "point()")
    public void after(){}

}
