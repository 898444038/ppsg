package com.ming.ppsg3.main;

import com.ming.ppsg2.utils.ReadWriteExcel;

import java.util.List;
import java.util.Map;

public class MainService {
    public static List<Map<String, String>> getExcelData() {
        String path = "/excel/data_temp4.xlsx";
        System.out.println("开始读取EXCEL："+path);
        long t1 = System.currentTimeMillis();
        ReadWriteExcel readWriteExcel = new ReadWriteExcel();
        readWriteExcel.setRow(1);
        List<List<String>> dataList = readWriteExcel.readRelativeExcel(path);
        List<Map<String, String>> lists = readWriteExcel.transformMap(dataList);

        //排除武将
        /*Iterator<Map<String,String>> iterator = lists.iterator();
        while (iterator.hasNext()) {
            Map<String,String> list = iterator.next();
            for (AppointExcludeGenerals excludeGenerals : excludeGeneralsList) {
                if (excludeGenerals.getName().equalsIgnoreCase(list.get("name")) && excludeGenerals.getMaxSize()==0) {
                    iterator.remove();
                }
            }
        }*/
        long t2 = System.currentTimeMillis();
        System.out.println("获取数据耗时："+(t2-t1)+"ms");
        System.out.println("获取初始数据："+lists.size()+"条");
        return lists;
    }

    public static void handleExcelData(List<Map<String, String>> lists) {


    }
}
