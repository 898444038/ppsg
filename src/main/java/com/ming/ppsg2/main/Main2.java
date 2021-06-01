package com.ming.ppsg2.main;


import com.ming.ppsg2.entity.AppointExcludeGenerals;
import com.ming.ppsg2.entity.AppointGenerals;
import com.ming.ppsg2.entity.AppointSymbols;
import com.ming.ppsg2.entity.Destiny;
import com.ming.ppsg2.entity.Device;
import com.ming.ppsg2.entity.Generals;
import com.ming.ppsg2.entity.Result;
import com.ming.ppsg2.enums.GeneralsEnum;
import com.ming.ppsg2.utils.GeneralsUtil;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class Main2 {
    public static void main(String[] args) throws Exception {
        start(null,null);
    }
    public static void start(Object[] selects, JTextArea txaDisplay) throws Exception {
        String top = "" +
                "啪啪三国技术交流群：913083053\n" +
                "更新内容：1.新增【御战曹仁】【厉箭韩当】【筹谋程昱】【驭灵董白】\n" +
                "\n"+
                "\n";
        top+= "";//更新日志
        String advert = "";//广告
        String fileRemark = "(御战曹仁)";//文件名备注
        String path = "/excel/data_temp_v5.8.0.xlsx";//基础数据EXCEL

        //指定阵容必须包含一个或多个武将
        List<AppointGenerals> appointGeneralsList = new ArrayList<>();
        for (Object obj : selects){
            appointGeneralsList.add(new AppointGenerals(obj.toString()));
        }
//        appointGeneralsList.add(new AppointGenerals("御甲张辽"));
//        appointGeneralsList.add(new AppointGenerals("桀骜孙策"));
//        appointGeneralsList.add(new AppointGenerals("砺战赵云"));
//        appointGeneralsList.add(new AppointGenerals("飞将吕布"));
//        appointGeneralsList.add(new AppointGenerals("灵雎吕姬"));
//        appointGeneralsList.add(new AppointGenerals("河间双雄"));
//        appointGeneralsList.add(new AppointGenerals("悦灵蔡文姬"));
//        appointGeneralsList.add(new AppointGenerals("猿戏华佗"));
//        appointGeneralsList.add(new AppointGenerals("灵刃吕玲绮"));
//        appointGeneralsList.add(new AppointGenerals("河间双雄"));
//        appointGeneralsList.add(new AppointGenerals("砺战赵云"));
//        appointGeneralsList.add(new AppointGenerals("奋威袁绍"));

        //指定某个武将在阵容中，上阵及随从的最大数量
        List<AppointExcludeGenerals> excludeGeneralsList = new ArrayList<>();
//        excludeGeneralsList.add(new AppointExcludeGenerals("砺战赵云",1));
//        excludeGeneralsList.add(new AppointExcludeGenerals("御甲张辽",1));
//        excludeGeneralsList.add(new AppointExcludeGenerals("桀骜孙策",1));
//        excludeGeneralsList.add(new AppointExcludeGenerals("飞将吕布",1));

        //指定阵容使用的兵符
        List<AppointSymbols> appointSymbolsList = new ArrayList<>();
//        appointSymbolsList.add(new AppointSymbols(GeneralsEnum.SymbolsType.yaCi.getCode(),GeneralsEnum.SymbolsType.yaCi.getName()));
//        appointSymbolsList.add(new AppointSymbols(GeneralsEnum.SymbolsType.cangLong.getCode(),GeneralsEnum.SymbolsType.cangLong.getName()));
//        appointSymbolsList.add(new AppointSymbols(GeneralsEnum.SymbolsType.qiongQi.getCode(),GeneralsEnum.SymbolsType.qiongQi.getName()));

        boolean isHuanHua = true;//随从是否有幻化
        long t1 = System.currentTimeMillis();
        xzl(txaDisplay,path,top,advert,fileRemark,appointGeneralsList,excludeGeneralsList,appointSymbolsList,isHuanHua);
        long t2 = System.currentTimeMillis();
        DecimalFormat df=new DecimalFormat("0.000");
        System.out.println("共耗时："+df.format((float)(t2-t1)/1000)+"s");
    }

    /**
     * 虚战力核心方法
     */
    public static void xzl(JTextArea jTextArea,String path,String top,String advert,String fileRemark,
                           List<AppointGenerals> appointGeneralsList,
                           List<AppointExcludeGenerals> excludeGeneralsList,
                           List<AppointSymbols> appointSymbolsList,
                           boolean isHuanHua) throws Exception {
        List<Generals> nimingAllList = new Vector<>();//全部武将
        List<Generals> generalsAll = new Vector<>();//非重复
        List<Generals> generalsAll2 = new Vector<>();//多余的，非限定
        Map<String,Destiny> destinyMap = new ConcurrentHashMap<>();//命格材料
        //获取基础数据，并排除指定武将
        List<Map<String,String>> lists = MainService.getExcelData(path,excludeGeneralsList);
        //处理基础数据
        MainService.handleExcelData(lists,appointGeneralsList,nimingAllList,generalsAll,destinyMap);
        //去除重复卡，保留异化卡
        MainService.handleNimingAllList(nimingAllList,generalsAll,generalsAll2);
        //战器排行榜
        List<Device> deviceList = MainService.handleWarDevice(nimingAllList);
        //计算随从加成
        Map<Integer,List<Generals>> map = MainService.handleEntourage(nimingAllList,generalsAll,isHuanHua);
        //随从榜
        Map<Integer,List<Generals>> allEntourage = MainService.handleEntourageBillboard(generalsAll,generalsAll2,isHuanHua);
        //最佳随从表
        List<Generals> optimumEntourage = MainService.handleOptimumEntourage(generalsAll,nimingAllList,isHuanHua);
        long t1 = System.currentTimeMillis();
        //最终排列组合list
        List<Generals> nmList = MainService.handleFinalNmList(30,nimingAllList);

        //计算战力
        List<Result> grilResultList = new Vector<>();
        List<Result> resultList2 = MainService.handleSword2(jTextArea,grilResultList,nmList,appointGeneralsList,appointSymbolsList,excludeGeneralsList);

        long t2 = System.currentTimeMillis();
        System.out.println("用时："+(t2-t1)+"ms , 阵容数量："+resultList2.size());
        System.out.println("---------end---------");
        System.gc();

        //计算随从榜
        List<Generals> forceTopList = allEntourage.get(GeneralsEnum.ThreeCircles.Force.getCode());
        List<Generals> intellectTopList = allEntourage.get(GeneralsEnum.ThreeCircles.Intellect.getCode());
        List<Generals> troopsTopList = allEntourage.get(GeneralsEnum.ThreeCircles.Troops.getCode());
        MainService.allEntourageBillboard(allEntourage,forceTopList,intellectTopList,troopsTopList);

        Map<String,Object> model = new HashMap<>();
        List<Result> simplifyList = GeneralsUtil.getSimplifyList(resultList2);
        List<Result> list2 = GeneralsUtil.getSimplifyList(resultList2);
        List<Result> grilList = GeneralsUtil.getSimplifyList(resultList2);

        //更新日志
        model.put("top",top);
        //广告位
        model.put("advert",advert);

        //只取虚战力榜前50名
        if(resultList2.size()>=50){
            list2 = resultList2.subList(0,50);//虚战力表（特殊战器）
        }else{
            list2 = resultList2;//虚战力表（特殊战器）
        }

        //只取女队虚战力榜前50名
        if(grilResultList.size()>=50) {
            grilList = grilResultList.subList(0, 50);//虚战力表（女队）
        }else{
            grilList = grilResultList;
        }

        //获取战力名称
        MainService.countCombatName(simplifyList);
        MainService.countCombatName(list2);
        MainService.countCombatName(grilList);

        model.put("simplifyList2",simplifyList);//简表（特殊战器）
        model.put("list2",list2);//虚战力表（特殊战器）
        model.put("grilList", grilList);//虚战力表（女队）

        model.put("forceTopList",forceTopList);//武随榜
        model.put("intellectTopList",intellectTopList);//智随榜
        model.put("troopsTopList",troopsTopList);//兵随榜
        model.put("optimumEntourageList",optimumEntourage);//最佳随从表
        model.put("destinyMap",destinyMap);//命格

        //生成excel
        MainService.createExcel(fileRemark,model);
        //打印兵符统计
        MainService.statistics(resultList2);
    }

}
