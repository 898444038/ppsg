package com.ming.tools.generate;

import com.ming.tools.generate.config.GenerateConfig;
import com.ming.tools.generate.entity.ClassField;
import com.ming.tools.generate.entity.Comment;
import com.ming.tools.generate.enums.DescType;
import com.ming.tools.generate.utils.GenerateString;
import com.ming.tools.generate.utils.GenerateUtil;
import com.ming.tools.generate.utils.Scanner;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2019/9/5 0005.
 */
public class Generate {
    private volatile static Generate generate = null;
    private Generate(){}
    public static Generate getInstance()   {
        if (generate == null)  {
            synchronized (Generate.class) {
                if (generate== null)  {
                    generate= new Generate();
                }
            }
        }
        return generate;
    }

    private static final String rn = "\r\n";
    private static final String t1 = "\t";
    private static final String t2 = "\t\t";
    private static final String blank_space1 = " ";



    public void generateController(Class clazz,Comment comment){}

    public void generateService(Class clazz,Comment comment){
        String entityClassName = GenerateUtil.getClassName(clazz);
        String lowerEntityClassName = GenerateUtil.firstLowerCase(entityClassName);
        String entityServiceClassName = entityClassName+"Service";
        GenerateString gs = GenerateString.build();
        gs.appendImport(clazz, List.class);
        gs.appendDesc(comment, DescType.CLASS,0);

        gs.append("public interface ").append(entityServiceClassName).append(" {").appendRN();
        gs.appendDesc(comment, DescType.SELECT,1);
        gs.appendT(1).append("List<").append(entityClassName).append("> findByCondition(").append(entityClassName+" "+lowerEntityClassName).append(");").appendRN();
        gs.appendDesc(comment, DescType.INSERT,1);
        gs.appendT(1).append("int insert(").append(entityClassName+" "+lowerEntityClassName).append(");").appendRN();
        gs.appendDesc(comment, DescType.INSERTBATCH,1);
        gs.appendT(1).append("int insertBatch(List<").append(entityClassName).append("> list);").appendRN();
        gs.appendDesc(comment, DescType.UPDATE,1);
        gs.appendT(1).append("int update(").append(entityClassName+" "+lowerEntityClassName).append(");").appendRN();
        gs.appendDesc(comment, DescType.DELETE,1);
        gs.appendT(1).append("int deleteById(Long id);").appendRN();
        gs.append("}");

        System.out.println(gs.toString());
    }

    public void generateServiceImpl(Class clazz,Comment comment){
        String entityClassName = GenerateUtil.getClassName(clazz);
        String lowerEntityClassName = GenerateUtil.firstLowerCase(entityClassName);
        GenerateString gs = GenerateString.build();
        //gs.appendImport(clazz, List.class, Resource.class, Service.class);
        gs.appendImport(clazz, List.class, Resource.class);
        gs.appendDesc(comment, DescType.CLASS,0);

        gs.append("@Service").appendRN();
        gs.append("public class ").append(entityClassName).append("ServiceImpl implements ").append(entityClassName).append("Service {").appendRN();
        gs.appendT(1).append("@Resource").appendRN();
        gs.appendT(1).append("private ").append(entityClassName).append("Mapper ").append(lowerEntityClassName).append("Mapper;").appendRN();

        gs.appendDesc(comment, DescType.SELECT,1);
        gs.appendT(1).append("@Override").appendRN();
        gs.appendT(1).append("List<").append(entityClassName).append("> findByCondition(").append(entityClassName+" "+lowerEntityClassName).append("){").appendRN();
        gs.appendT(2).append("return ").append(lowerEntityClassName).append("Mapper.findByCondition("+lowerEntityClassName+");").appendRN();
        gs.appendT(1).append("}").appendRN();

        gs.appendDesc(comment, DescType.INSERT,1);
        gs.appendT(1).append("@Override").appendRN();
        gs.appendT(1).append("int insert(").append(entityClassName+" "+lowerEntityClassName).append("){").appendRN();
        gs.appendT(2).append("return ").append(lowerEntityClassName).append("Mapper.insert("+lowerEntityClassName+");").appendRN();
        gs.appendT(1).append("}").appendRN();

        gs.appendDesc(comment, DescType.INSERTBATCH,1);
        gs.appendT(1).append("@Override").appendRN();
        gs.appendT(1).append("int insertBatch(List<").append(entityClassName).append("> list){").appendRN();
        gs.appendT(2).append("return ").append(lowerEntityClassName).append("Mapper.insertBatch(list);").appendRN();
        gs.appendT(1).append("}").appendRN();

        gs.appendDesc(comment, DescType.UPDATE,1);
        gs.appendT(1).append("@Override").appendRN();
        gs.appendT(1).append("int update(").append(entityClassName+" "+lowerEntityClassName).append("){").appendRN();
        gs.appendT(2).append("return ").append(lowerEntityClassName).append("Mapper.update("+lowerEntityClassName+");").appendRN();
        gs.appendT(1).append("}").appendRN();

        gs.appendDesc(comment, DescType.DELETE,1);
        gs.appendT(1).append("@Override").appendRN();
        gs.appendT(1).append("int deleteById(Long id){").appendRN();
        gs.appendT(2).append("return ").append(lowerEntityClassName).append("Mapper.deleteById("+lowerEntityClassName+");").appendRN();
        gs.appendT(1).append("}").appendRN();

        gs.append("}");

        System.out.println(gs.toString());
    }

    public void generateEntity(String entityName, List<ClassField> list, Comment comment){
        GenerateString gs = GenerateString.build();
        gs.appendImport(Serializable.class);
        gs.appendDesc(comment, DescType.CLASS,0);

        gs.append("public class ").append(entityName).append(" implements Serializable {").appendRN();
        gs.appendT(1).append("private static final long serialVersionUID = 1L;").appendRN();

        for(ClassField classField : list){
            String field = classField.getFieldName();
            String fieldUp = GenerateUtil.firstUpperCase(field);
            String type = classField.getFieldType();
            gs.appendT(1).append("private ").append(type).append(" "+field).appendSRN();
            gs.appendT(1).append("private "+type+" get"+fieldUp+"(){return "+field+";}").appendRN();
            gs.appendT(1).append("private void set"+fieldUp+"("+type+" "+field+"){this."+field+"="+field+";}").appendRN();
        }

        System.out.println(gs.toString());
    }

    public void generateMapper(Class clazz,Comment comment){
        String entityClassName = GenerateUtil.getClassName(clazz);
        String lowerEntityClassName = GenerateUtil.firstLowerCase(entityClassName);
        String entityMapperClassName = entityClassName+"Mapper";
        GenerateString gs = GenerateString.build();
        gs.appendImport(clazz,List.class);
        //gs.appendImport(clazz,List.class, Mapper.class);
        gs.appendDesc(comment, DescType.CLASS,0);

        gs.append("@Mapper").appendRN();
        gs.append("public interface ").append(entityMapperClassName).append(" {").appendRN();
        gs.appendDesc(comment, DescType.SELECT,1);
        gs.appendT(1).append("List<").append(entityClassName).append("> findByCondition(").append(entityClassName+" "+lowerEntityClassName+")").appendSRN();
        gs.appendDesc(comment, DescType.INSERT,1);
        gs.appendT(1).append("int insert("+entityClassName+" "+lowerEntityClassName+")").appendSRN();
        gs.appendDesc(comment, DescType.INSERTBATCH,1);
        gs.appendT(1).append("int insertBatch(List<"+entityClassName+"> list)").appendSRN();
        gs.appendDesc(comment, DescType.UPDATE,1);
        gs.appendT(1).append("int update("+entityClassName+" "+lowerEntityClassName+")").appendSRN();
        gs.appendDesc(comment, DescType.DELETE,1);
        gs.appendT(1).append("int deleteById(Long id)").appendSRN();

        gs.append(")").appendRN();
        System.out.println(gs.toString());
    }

    public void generateMapperXml(Class clazz,String tableName){
        String entityClassName = GenerateUtil.getClassName(clazz);
        String mapperClassName = "";
        List<ClassField> classFieldList = GenerateUtil.getFieldList(clazz);
        List<String> columnList = new ArrayList<String>();

        GenerateString gs = GenerateString.build();
        gs.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").appendRN();
        gs.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">").appendRN();
        gs.append("<mapper namespace=\""+mapperClassName+"\">").appendRN();

        gs.appendT(1).append("<resultMap type=\""+entityClassName+"\" id=\"baseResultMap\">").appendRN();
        for(ClassField classField : classFieldList){
            String column = classField.getFieldColumn();
            columnList.add(column);
            gs.appendT(2).append("<result column=\""+column+"\" property=\""+classField.getFieldName()+"\" jdbcType=\"" + classField.getJdbcType() + "\" />").appendRN();
        }
        gs.append("</resultMap>").appendRN();

        gs.append("<sql id=\"baseColumn\">").appendRN();
        for(int i=0;i<columnList.size();i++){
            gs.appendT(2).append(columnList.get(i));
            if(i!=columnList.size()-1){
                gs.append(",");
            }
            gs.appendRN();
        }
        gs.append("</sql>").appendRN();

        gs.appendT(1).append("<sql id=\"baseCondition\">").appendRN();
        gs.appendT(2).append("<where>").appendRN();
        gs.appendT(2).append("</where>").appendRN();
        gs.appendT(1).append("</sql>").appendRN();

        // select
        gs.appendT(1).append("<select id=\"findByCondition\" resultMap=\"baseResultMap\" parameterType=\""+entityClassName+"\">").appendRN();
        gs.appendT(2).append("SELECT <include refid=\"baseColumn\"></include> ").appendRN();
        gs.appendT(2).append("from ").append(tableName).appendRN();
        gs.appendT(2).append("<include refid=\"baseCondition\"></include>").appendRN();
        gs.appendT(1).append("</select>").appendRN();

        // insert
        gs.appendT(1).append("<insert id=\"insert\" useGenerateKeys=\"true\" keyProperty=\"id\" parameterType=\""+entityClassName+"\">").appendRN();
        gs.appendT(2).append("insert into ").append(tableName).appendRN();
        gs.appendT(2).append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >").appendRN();
        for(ClassField classField : classFieldList) {
            gs.appendT(3).append("<if test=\""+classField.getFieldName()+"!=null\">"+classField.getFieldColumn()+",</if>").appendRN();
        }
        gs.appendT(2).append("</trim>").appendRN();

        gs.appendT(2).append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >").appendRN();
        for(ClassField classField : classFieldList) {
            gs.appendT(3).append("<if test=\""+classField.getFieldName()+"!=null\">#{"+classField.getFieldName()+"},</if>").appendRN();
        }
        gs.appendT(2).append("</trim>").appendRN();
        gs.appendT(1).append("</insert>").appendRN();

        // insertBatch
        gs.appendT(1).append("<insert id=\"insertBatch\" parameterType=\"java.util.List\">").appendRN();
        gs.appendT(2).append("insert into").append(tableName).append("(").appendRN();
        for(int i=0;i<classFieldList.size();i++){
            gs.appendT(3).append(classFieldList.get(i).getFieldColumn());
            if(i!=classFieldList.size()-1){
                gs.append(",");
            }
            gs.appendRN();
        }
        gs.appendT(2).append(") values").appendRN();
        gs.appendT(2).append("<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\"> (").appendRN();
        for(int i=0;i<classFieldList.size();i++){
            gs.appendT(3).append("#{item.}"+classFieldList.get(i).getFieldName()+",jdbcType="+classFieldList.get(i).getJdbcType()+"}");
            if(i!=classFieldList.size()-1){
                gs.append(",");
            }
            gs.appendRN();
        }
        gs.appendT(2).append(")").appendRN();
        gs.appendT(2).append("</foreach>").appendRN();
        gs.appendT(1).append("</insert>").appendRN();

        //update
        gs.appendT(1).append("<update id=\"update\" parameterType=\""+entityClassName+"\">").appendRN();
        gs.appendT(2).append("update ").append(tableName).appendRN();
        gs.appendT(2).append("<set>").appendRN();
        for(ClassField classField : classFieldList){
            String property = classField.getFieldName();
            String column = classField.getFieldColumn();
            gs.appendT(3).append("<if test=\""+property+" != null\">"+column+" = #{"+property+"},</if>").appendRN();
        }
        gs.appendT(2).append("</set>").appendRN();
        gs.appendT(2).append("where id=#{id}").appendRN();
        gs.appendT(1).append("</update>").appendRN();

        //delete
        gs.appendT(1).append("<delete id=\"deleteById\" parameterType=\"java.lang.Long\">").appendRN();
        gs.appendT(2).append("delete from ").append(tableName).appendRN();
        gs.appendT(2).append("where id=#{id} ").appendRN();
        gs.appendT(1).append("</delete>").appendRN();

        gs.append("</mapper>").appendRN();

        System.out.println(gs.toString());
    }

    public static void main(String[] args) {
        //Comment comment = new Comment();
        //comment.setAuthor("wang");
        //comment.setDesc("用户");
        //comment.setCreateTime(new Date());

        /*String s = GenerateString.build().appendDesc(comment,DescType.INSERT,0).toString();
        System.out.println(s);*/

        //generateController();
        //generateService(User.class,comment);
        //generateServiceImpl(User.class,comment);
        //generateEntity(User.class,comment);
        //generateMapper(User.class,comment);
        //generateMapperXml(User.class,comment);

    }

    public void create(String[] paths) throws Exception {
        Scanner scanner = new Scanner();
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (String path : paths){
            classSet.addAll(scanner.getGenerateMybatis(path));
        }
        String path = GenerateConfig.path;
        for (Class<?> cls : classSet) {
            Comment comment = GenerateUtil.getComment(cls);
            //generateEntity(cls,comment);
//            generateController(cls,comment);
//            generateService(cls,comment);
//            generateServiceImpl(cls,comment);
            generateMapper(cls,comment);
            generateMapperXml(cls, GenerateUtil.getTableName(cls));
        }
        System.out.println("create");
    }
}
