package com.ming.system.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
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
public class LogAspest {

    @Pointcut(value = "@annotation(com.ming.system.annotation.Log) || @within(com.ming.system.annotation.Log)")
    public void point(){}

    @Before(value="point()")
    public void before(){

    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        String argStr = "";
        Object[] args = joinPoint.getArgs();
        if(args!=null){
            for(Object arg :args){
                argStr += arg.toString()+",";
            }
        }
        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Class clazz = joinPoint.getTarget().getClass();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();//获取访问的方法

        printLog(clazz.getName(),method.getName(),argStr);
        Object obj = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        printLog(clazz.getName(),method.getName(),argStr,startTime,endTime);
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
