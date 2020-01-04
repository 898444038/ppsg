package com.ming.system.aspect;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ming.system.annotation.methodtype.*;
import com.ming.system.enums.LogEnum;
import com.ming.system.utils.StringUtils;
import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AspectUtils {

    public static Gson gson = new GsonBuilder().serializeNulls().create();

    public static String getMapping(Class clazz, Method method){
        String mapping = "";
        if(clazz.isAnnotationPresent(RequestMapping.class)){
            RequestMapping mappingClass = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
            if(mappingClass.value().length!=0){
                mapping += mappingClass.value()[0];
            }
        }
        if(method.isAnnotationPresent(RequestMapping.class)){
            RequestMapping mappingMethod = method.getAnnotation(RequestMapping.class);
            mapping += mappingMethod.value()[0];
        }else if(method.isAnnotationPresent(GetMapping.class)){
            GetMapping mappingMethod = method.getAnnotation(GetMapping.class);
            mapping += mappingMethod.value()[0];
        }else if(method.isAnnotationPresent(PostMapping.class)){
            PostMapping mappingMethod = method.getAnnotation(PostMapping.class);
            mapping += mappingMethod.value()[0];
        }else if(method.isAnnotationPresent(PutMapping.class)){
            PutMapping mappingMethod = method.getAnnotation(PutMapping.class);
            mapping += mappingMethod.value()[0];
        }else if(method.isAnnotationPresent(DeleteMapping.class)){
            DeleteMapping mappingMethod = method.getAnnotation(DeleteMapping.class);
            mapping += mappingMethod.value()[0];
        }
        return mapping;
    }


    public static String getMethodType(Method method){
        if(method.isAnnotationPresent(Select.class)){
            return LogEnum.SELECT.name();
        }else if(method.isAnnotationPresent(Insert.class)){
            return LogEnum.INSERT.name();
        }else if(method.isAnnotationPresent(Update.class)){
            return LogEnum.UPDATE.name();
        }else if(method.isAnnotationPresent(Delete.class)){
            return LogEnum.DELETE.name();
        }else if(method.isAnnotationPresent(Page.class)){
            return LogEnum.PAGE.name();
        }else if(method.isAnnotationPresent(Login.class)){
            return LogEnum.LOGIN.name();
        }else if(method.isAnnotationPresent(Register.class)){
            return LogEnum.REGISTER.name();
        }else if(method.isAnnotationPresent(Upload.class)){
            return LogEnum.UPLOAD.name();
        }
        return LogEnum.OTHER.name();
    }
    public static String getArgs(ProceedingJoinPoint joinPoint) {
        Map<String, Object> params = getNameAndValue(joinPoint);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            System.out.println("name: " + entry.getKey() + " value: " + entry.getValue());
        }
        return "";
    }

    /**
     * 获取参数Map集合
     * @param joinPoint
     * @return
     */
    public static Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }
        return param;
    }

    private static String[] types = { "java.lang.Integer", "java.lang.Double", "java.lang.Float", "java.lang.Long",
            "java.lang.Short", "java.lang.Byte", "java.lang.Boolean", "java.lang.Char", "java.lang.String", "int",
            "double", "long", "short", "byte", "boolean", "char", "float" };

    /**
     * 获取方法参数名及对应值
     */
    public String bulidParams(JoinPoint joinPoint) throws Exception {
        String[] paramNames = getParamNames(joinPoint);
        Object[] args = joinPoint.getArgs();

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < args.length; k++) {
            boolean clazzFlag = true;
            Object arg = args[k];
            if (arg == null) {
                continue;
            }
            sb.append(paramNames[k]+":{");
            String typeName = arg.getClass().getTypeName();
            for (String type : types) {
                if (type.equals(typeName)) {
                    clazzFlag = false;
                    sb.append("=").append(argValue(arg)).append(",");
                }
            }
            if (clazzFlag) {
                sb.append(getFieldsValue(arg));
            }
            sb.append("},");
        }
        return sb.toString();
    }

    private String[] getParamNames(JoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getParameterNames();
    }

    private static String argValue(Object arg) {
        String argStr = gson.toJson(arg);
        return argStr.length() > 256 ? argStr.subSequence(0, 256) + "..." : argStr;
    }

    /**
     * 获取对象中的参数名及对应参数值
     */
    private static String getFieldsValue(Object obj) throws Exception {
        Field[] fields = obj.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                field.get(null);// 只能获取静态常量
            } catch (NullPointerException e) {
                Object objArg = field.get(obj);
                if (objArg == null) {
                    continue;
                }
                for (String type : types) {
                    if (field.getType().getName().equals(type)) {
                        sb.append(field.getName()).append("=").append(argValue(objArg)).append(",");
                    }
                }
            }
        }
        return sb.toString();
    }

}
