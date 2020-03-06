package com.ming.ppsg2.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/3/6 0006.
 */
public class RWExcel {
    private String filePath;
    private int sheetNum = 0;

    /**
     * 构造方法
     * */

    public RWExcel(String filePath,int sheetNum){
        this.filePath = filePath;
        this.sheetNum = sheetNum;
    }

    /**
     *
     * 读取excel 封装成集合
     * 该程序需要传入一份excel 和excel的列数 行数由程序自动检测
     * 注意：该方法统计的行数是默认第一行为title 不纳入统计的
     *
     * @return
     *
     */
    // @Test
    public ArrayList<List> ReadExcel() {
        // int column = 5;//column表示excel的列数
        ArrayList<List> list = new ArrayList<List>();
        String fileType = filePath.substring(filePath.lastIndexOf(".") + 1);
        try {
            InputStream is = null;//new FileInputStream(filePath);
            // 建需要读取的excel文件写入stream
            is = RWExcel.class.getResourceAsStream(filePath);
            //获取工作薄
            Workbook workbook = null;
            if (fileType.equals("xls")) {
                workbook = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
                workbook = new XSSFWorkbook(is);
            } else {
                return null;
            }

            // 指向sheet下标为0的sheet 即第一个sheet 也可以按在sheet的名称来寻找
            Sheet sheet = workbook.getSheetAt(sheetNum);
            // 获取sheet1中的总行数
            int rowTotalCount = sheet.getLastRowNum();
            //获取总列数
            int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

            System.out.println("行数为："+rowTotalCount+"列数为："+columnCount);

            for (int i = 0; i <= rowTotalCount; i++) {
                // 获取第i列的row对象
                Row row = sheet.getRow(i);

                ArrayList<String> listRow = new ArrayList<String>();

                for (int j = 0; j < columnCount; j++) {
                    //下列步骤为判断cell读取到的数据是否为null 如果不做处理 程序会报错
                    String cell = null;
                    //如果未null则加上""组装成非null的字符串
                    if(row.getCell(j) == null){
                        cell = row.getCell(j)+"";
                        listRow.add(cell);
                        //如果读取到额cell不为null 则直接加入	listRow集合
                    }else{
                        cell = row.getCell(j).toString();
                        listRow.add(cell);
                    }
                    // 在第i列 依次获取第i列的第j个位置上的值 %15s表示前后间隔15个字节输出
                    System.out.printf("%15s", cell);

                }
                list.add(listRow);
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 调试方法
     * */

    public static void main(String[] args) {

        ArrayList<List> arrayList = new RWExcel("D:\\345.xls", 0).ReadExcel();
        for (List list : arrayList) {
            System.out.println(list.toString());
        }

    }
}
