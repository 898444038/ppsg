package com.ming.ppsg2.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/3/6 0006.
 */
public class ReadWriteExcel {

    private Integer row = 0;//起始读取行数
    private Integer sheetNum = 0;//读取页数

    public ReadWriteExcel(){}

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSheetNum() {
        return sheetNum;
    }

    public void setSheetNum(Integer sheetNum) {
        this.sheetNum = sheetNum;
    }

    //绝对路径
    public List<List<String>> readAbsoluteExcel(String path){
        return readExcel(0,path);
    }

    //相对路径
    public List<List<String>> readRelativeExcel(String path){
        return readExcel(1,path);
    }

    /**
     * 读取excel 封装成集合
     * 该程序需要传入一份excel 和excel的列数 行数由程序自动检测
     * 注意：该方法统计的行数是默认第一行为title 不纳入统计的
     */
    private List<List<String>> readExcel(Integer pathType,String path) {
        // int column = 5;//column表示excel的列数
        List<List<String>> list = new ArrayList<>();
        String fileType = path.substring(path.lastIndexOf(".") + 1);
        InputStream is = null;
        Workbook workbook = null;
        try {
            // 需要读取的excel文件写入stream
            if(pathType==0){
                is = new FileInputStream(path);
            }else{
                is = ReadWriteExcel.class.getResourceAsStream(path);
            }

            // 获取工作薄
            if (fileType.equalsIgnoreCase("xls")) {
                workbook = new HSSFWorkbook(is);
            } else if (fileType.equalsIgnoreCase("xlsx")) {
                workbook = new XSSFWorkbook(is);
            } else {
                return null;
            }

            // 指向sheet下标为0的sheet 即第一个sheet 也可以按在sheet的名称来寻找
            Sheet sheet = workbook.getSheetAt(sheetNum);
            // 获取sheet1中的总行数
            int rowTotalCount = sheet.getLastRowNum()+row;
            //获取总列数
            int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

            System.out.println(rowTotalCount+"行,"+columnCount+"列");

            for (int i = row; i < rowTotalCount; i++) {
                // 获取第i列的row对象
                Row row = sheet.getRow(i);

                List<String> listRow = new ArrayList<>();

                for (int j = 0; j < columnCount; j++) {
                    //下列步骤为判断cell读取到的数据是否为null 如果不做处理 程序会报错
                    String cell = null;
                    //如果未null则加上""组装成非null的字符串
                    if(row.getCell(j) == null){
                        //cell = row.getCell(j)+"";
                        listRow.add(null);
                        //如果读取到额cell不为null 则直接加入	listRow集合
                    }else{
                        if("".equals(row.getCell(j).toString())){
                            listRow.add(null);
                        }else{
                            cell = row.getCell(j).toString().trim();
                            listRow.add(cell);
                        }
                    }
                    // 在第i列 依次获取第i列的第j个位置上的值 %15s表示前后间隔15个字节输出
                    System.out.printf("%15s", cell);
                }
                list.add(listRow);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /*public <T> List<T> transform(List<List<String>> dataList,Class<T> clazz){
        // 返回数据集合
        List<T> list = new ArrayList<>();
        // 对象字段名称
        String fieldname = "";
        // 对象方法名称
        String methodname = "";
        // 对象方法需要赋的值
        Object methodsetvalue = "";
        try {
            list = new ArrayList<T>();
            // 得到对象所有字段
            Field fields[] = clazz.getDeclaredFields();
            // 遍历数据
            for (Map<String, Object> mapdata : datas) {
                // 创建一个泛型类型实例
                T t = clazz.newInstance();
                // 遍历所有字段，对应配置好的字段并赋值
                for (Field field : fields) {
                    // 获取注解配置
                    JavaBean javaBean = field.getAnnotation(JavaBean.class);
                    if(null != javaBean) {  // 有注解配置，下一步操作
                        // 全部转化为大写
                        String dbfieldname = javaBean.dbfieldname().toUpperCase();
                        // 获取字段名称
                        fieldname = field.getName();
                        // 拼接set方法
                        methodname = "set" + StrUtil.capitalize(fieldname);
                        // 获取data里的对应值
                        methodsetvalue = mapdata.get(dbfieldname);
                        // 赋值给字段
                        Method m = clazz.getDeclaredMethod(methodname, field.getType());
                        m.invoke(t, methodsetvalue);
                    }
                }
                // 存入返回列表
                list.add(t);
            }
        } catch (InstantiationException e) {
            System.out.println("创建beanClass实例异常");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("创建beanClass实例异常");
            e.printStackTrace();
        } catch (SecurityException e) {
            System.out.println("获取[" + fieldname + "] getter setter 方法异常");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("获取[" + fieldname + "] getter setter 方法异常");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("[" + methodname + "] 方法赋值异常");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println("[" + methodname + "] 方法赋值异常");
            e.printStackTrace();
        }
        // 返回
        return list;
    }*/

    public <T> List<T> transform(List<List<String>> dataList,Class<T> clazz) {
        // 返回数据集合
        List<T> list = new ArrayList<>();
        // 得到对象所有字段
        Field fields[] = clazz.getDeclaredFields();
        // 遍历数据
        for (List<String> data : dataList) {

        }
        return list;
    }


    public List<Map<String,String>> transformMap(List<List<String>> list) {
        List<String> titleList = list.subList(0,1).get(0);
        List<List<String>> dataList = list.subList(1,list.size());
        List<Map<String,String>> mapList = new ArrayList<>();
        Map<String,String> map = null;
        // 遍历数据
        for (List<String> data : dataList) {
            map = new LinkedHashMap<>();
            for(int i=0;i<data.size();i++){
                map.put(titleList.get(i),data.get(i));
            }
            mapList.add(map);
        }
        return mapList;
    }

    public static void main(String[] args) {
        ReadWriteExcel readWriteExcel = new ReadWriteExcel();
        readWriteExcel.setRow(1);
        List<List<String>> lists = readWriteExcel.readRelativeExcel("/excel/data_temp2.xlsx");
        List<Map<String,String>> mapList = readWriteExcel.transformMap(lists);
        System.out.println(mapList);


    }
}
