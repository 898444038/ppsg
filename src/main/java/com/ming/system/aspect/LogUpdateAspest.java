package com.ming.system.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/12/9 0009.
 */
@Aspect
@Component
public class LogUpdateAspest {

    @Pointcut(value = "@annotation(com.ming.system.annotation.LogUpdate)")
    public void point(){}

    @Before(value="point()")
    public void before(){

    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        return joinPoint.proceed();
    }

    @AfterReturning(value = "point()")
    public void after(){
        //t2 = System.currentTimeMillis();
        //DecimalFormat df=new DecimalFormat("0.000");
        //log.info("结束执行时间:"+df.format((float)(t2-t1)/1000)+"s");
    }
}
