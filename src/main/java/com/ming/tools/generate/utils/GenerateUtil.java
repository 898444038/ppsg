package com.ming.tools.generate.utils;

import com.ming.tools.generate.annotation.Description;
import com.ming.tools.generate.annotation.GenerateMybatis;
import com.ming.tools.generate.annotation.Transient;
import com.ming.tools.generate.entity.ClassField;
import com.ming.tools.generate.entity.Comment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019/9/5 0005.
 */
public class GenerateUtil {

    public static boolean isBlank(String str) {
        int strLen;
        if(str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    //下划线转驼峰
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    public static String lineToHump(String str){
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()){
            matcher.appendReplacement(sb,matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    //驼峰转下划线
    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    public static String humpToLine(String str){
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()){
            matcher.appendReplacement(sb,"_"+matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    //获取类名，不带包名
    public static String getClassName(Class clazz){
        String name = clazz.getName();
        String[] names = name.split("\\.");
        return names[names.length-1];
    }

    //首字母大写
    public static String firstUpperCase(String str){
        char[] chars = str.toCharArray();
        chars[0]-=32;
        return String.valueOf(chars);
    }

    //首字母小写
    public static String firstLowerCase(String str){
        char[] chars = str.toCharArray();
        chars[0]+=32;
        return String.valueOf(chars);
    }

    public static List<ClassField> getFieldList(Class clazz){
        List<ClassField> list = new ArrayList<ClassField>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            if(field.isAnnotationPresent(Transient.class)){
                continue;
            }
            String fieldName = field.getName();
            if("serialVersionUID".equals(fieldName)){
                continue;
            }
            ClassField classField = new ClassField();
            String typeName = field.getGenericType().getTypeName();
            classField.setFieldName(fieldName);
            classField.setFieldColumn(GenerateUtil.humpToLine(fieldName));
            classField.setFieldType(typeName);
            if("java.lang.String".equals(typeName)){
                classField.setJdbcType("VARCHAR");
            }else if("java.lang.Long".equals(typeName)){
                classField.setJdbcType("BIGINT");
            }else if("java.lang.Integer".equals(typeName)){
                classField.setJdbcType("INTEGER");
            }else if("java.util.Date".equals(typeName)){
                classField.setJdbcType("TIMESTAMP");
            }else if("int".equalsIgnoreCase(typeName)){
                classField.setJdbcType("BIT");
            }else if("java.math.BigDecimal".equalsIgnoreCase(typeName)){
                classField.setJdbcType("DECIMAL");
            }else{
                classField.setJdbcType("");
            }
            list.add(classField);
        }
        return list;
    }

    public static Comment getComment(Class clazz){
        Comment comment = new Comment();
        if(clazz.isAnnotationPresent(Description.class)){
            Description desc = (Description)clazz.getAnnotation(Description.class);
            comment.setAuthor(desc.author());
            comment.setDesc(desc.desc());
            comment.setCreateTime(new Date());
        }
        return comment;
    }

    public static String getTableName(Class<?> cls) {
        GenerateMybatis generateMybatis =  cls.getAnnotation(GenerateMybatis.class);
        String tableName = generateMybatis.tableName();
        if(GenerateUtil.isBlank(tableName)){
            String className = GenerateUtil.getClassName(cls);
            tableName = GenerateUtil.firstLowerCase(className);
        }
        return tableName;
    }
}
