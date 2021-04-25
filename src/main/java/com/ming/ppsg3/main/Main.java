package com.ming.ppsg3.main;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String top = "因缺少部分卡片属性数据，以下排名中上阵武将及随从不包含：征南曹仁、暴怒张飞、桓侯张飞、讨虏黄忠、狂骨魏延、顾曲周瑜、修罗吕布\n" +
                "啪啪三国技术交流群：913083053\n" +
                "更新内容：1.新增逆命【悦灵蔡文姬】\n" +
                "2.新增幻化 道玄诸葛果【月雅清鸣】 剑引张郃【剑锋祭魂】\n"+
                "\n";
        top+= "";
        String advert = "";//广告
        String fileRemark = "(悦灵蔡文姬)";

        long t1 = System.currentTimeMillis();
        xzl(top,advert,fileRemark);
        long t2 = System.currentTimeMillis();
        DecimalFormat df=new DecimalFormat("0.000");
        System.out.println("共耗时："+df.format((float)(t2-t1)/1000)+"s");
    }

    public static void xzl(String top,String advert,String fileRemark){
        //获取基础数据
        List<Map<String,String>> lists = MainService.getExcelData();
        //处理基础数据
        MainService.handleExcelData(lists);
    }
}
