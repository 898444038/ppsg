package com.ming.system.aspect;

import com.ming.system.entity.Log;
import com.ming.system.entity.User;
import com.ming.system.enums.LogEnum;
import com.ming.system.service.LogService;
import com.ming.system.utils.IpUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2019/12/9 0009.
 */
@Aspect
@Component
@Order(-1) //保证该AOP在@Transactional之前执行
public class LogAspect {

    @Resource
    private LogService logService;

    @Pointcut(value = "@annotation(com.ming.system.annotation.Log) || @within(com.ming.system.annotation.Log)")
    public void point(){}

    @Before(value="point()")
    public void before(){

    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        AspectUtils aspectUtils = new AspectUtils();
        String args = aspectUtils.bulidParams(joinPoint);
        Class clazz = joinPoint.getTarget().getClass();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();//获取访问的方法

        printLog(clazz.getName(),method.getName(),args);
        Object obj = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        printLog(clazz.getName(),method.getName(),args,startTime,endTime);

        DecimalFormat df = new DecimalFormat("000");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//获取request
        //HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();//获取response
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            if(authentication.getPrincipal() instanceof User){
                User user = (User)authentication.getPrincipal();
                Log log = new Log();
                log.setUserId(user.getId());
                log.setUsername(user.getUsername());
                log.setIp(IpUtils.getIpAddress(request));
                log.setStartTime(new Date(startTime));
                log.setEndTime(new Date(endTime));
                String time = df.format((float)(endTime-startTime)/1000);
                log.setTime(Integer.valueOf(time));
                log.setMethod(method.getName());
                log.setMapping(AspectUtils.getMapping(clazz,method));
                log.setArgs(args);
                log.setResult(obj.toString());
                log.setType(AspectUtils.getMethodType(method));
                logService.insertLog(log);
            }
        }
        return obj;
    }

    @AfterReturning(value = "point()")
    public void after(){}

    private static void printLog(String className,String methodName,String args){
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        //System.out.println(sdf.format(new Date())+" [start] "+className+"."+methodName+"("+(args==null?"":args)+")");
    }

    private static void printLog(String className,String methodName,String args,long startTime,long endTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        DecimalFormat df=new DecimalFormat("0.000");
        //System.out.println(sdf.format(new Date())+" [  end] "+className+"."+methodName+"("+(args==null?"":args)+")");
        System.out.println(sdf.format(new Date())+" ["+df.format((float)(endTime-startTime)/1000)+"s"+"] "+className+"."+methodName+"("+(args==null?"":args)+")");
    }

    private static String formatBlankSpace(String str){
        return str;
    }
}
