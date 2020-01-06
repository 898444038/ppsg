package com.ming.tools.generate.aspect;

import com.ming.tools.generate.Generate;
import com.ming.tools.generate.annotation.GenerateScan;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

/**
 * Created by Administrator on 2020/1/6 0006.
 */
@Aspect
public class GenerateAspect {

    //execution(* *(..))防止多次切入（Ajc编译器的一个Bug）
    @Pointcut("execution(* *(..)) && @within(com.ming.tools.generate.annotation.GenerateScan)")
    private void point(){}

    @Before(value="point()")
    public void before(){
        System.out.println("start generate file...");
    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Class clazz = joinPoint.getTarget().getClass();
        String[] paths = {};
        if(clazz.isAnnotationPresent(GenerateScan.class)){
            GenerateScan generateScan = (GenerateScan)clazz.getAnnotation(GenerateScan.class);
            paths = generateScan.basePackages();
        }
        if(paths.length==0){
            Class appClass = joinPoint.getTarget().getClass();
            String simpleName = appClass.getSimpleName();
            String name = appClass.getName();
            String[] packageName = name.split("."+simpleName);
            paths = new String[]{packageName[0]};
        }
        System.out.println(Arrays.toString(paths));
        if(paths.length!=0){
            Generate.getInstance().create(paths);
        }
        return joinPoint.proceed();
    }

    @AfterReturning(value = "point()")
    public void after(){
        System.out.println("end generate file.");
    }
}
