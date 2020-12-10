package com.ming.ppsg2.main;


import com.ming.ppsg2.entity.AppointExcludeGenerals;
import com.ming.ppsg2.entity.AppointGenerals;
import com.ming.ppsg2.entity.AppointSymbols;
import com.ming.ppsg2.entity.Destiny;
import com.ming.ppsg2.entity.Generals;
import com.ming.ppsg2.entity.Result;
import com.ming.ppsg2.enums.GeneralsEnum;
import com.ming.ppsg2.utils.GeneralsUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2 {

    public static void main(String[] args) throws Exception {
        String top = "因缺少部分卡片属性数据，以下排名中上阵武将及随从不包含：征南曹仁、七星诸葛亮、暴怒张飞、桓侯张飞、讨虏黄忠、狂骨魏延、顾曲周瑜、修罗吕布\n" +
                "啪啪三国技术交流群：913083053\n" +
                "更新内容：1.新增逆命 【回禄魏延】\n" +
                "2.新增突破 【潋滟步练师】 \n"+
                "3.新增幻化 【火凤燎原】【云蒸龙变】 \n";
        top+= "";
        String advert = "";//广告
        String fileRemark = "(回禄魏延)";
        //计算：992/658/1895
        //实际：988/654/1947

        List<AppointGenerals> appointGeneralsList = new ArrayList<>();
//        appointGeneralsList.add(new AppointGenerals("砺战赵云"));
//        appointGeneralsList.add(new AppointGenerals("御甲张辽"));
//        appointGeneralsList.add(new AppointGenerals("桀骜孙策"));
//        appointGeneralsList.add(new AppointGenerals("飞将吕布"));
//        appointGeneralsList.add(new AppointGenerals("灵雎吕姬"));

        List<AppointExcludeGenerals> excludeGeneralsList = new ArrayList<>();
//        excludeGeneralsList.add(new AppointExcludeGenerals("砺战赵云",1));
//        excludeGeneralsList.add(new AppointExcludeGenerals("御甲张辽",1));
//        excludeGeneralsList.add(new AppointExcludeGenerals("桀骜孙策",1));
//        excludeGeneralsList.add(new AppointExcludeGenerals("飞将吕布",1));

        List<AppointSymbols> appointSymbolsList = new ArrayList<>();
//        appointSymbolsList.add(new AppointSymbols(GeneralsEnum.SymbolsType.yaCi.getCode(),GeneralsEnum.SymbolsType.yaCi.getName()));
//        appointSymbolsList.add(new AppointSymbols(GeneralsEnum.SymbolsType.cangLong.getCode(),GeneralsEnum.SymbolsType.cangLong.getName()));
//        appointSymbolsList.add(new AppointSymbols(GeneralsEnum.SymbolsType.qiongQi.getCode(),GeneralsEnum.SymbolsType.qiongQi.getName()));

        boolean isHuanHua = true;//随从是否有幻化
        long t1 = System.currentTimeMillis();
        xzl(top,advert,fileRemark,appointGeneralsList,excludeGeneralsList,appointSymbolsList,isHuanHua);
        long t2 = System.currentTimeMillis();
        DecimalFormat df=new DecimalFormat("0.000");
        System.out.println("共耗时："+df.format((float)(t2-t1)/1000)+"s");

        //removeProp();
    }

    public static void xzl(String top,String advert,String fileRemark,
                           List<AppointGenerals> appointGeneralsList,
                           List<AppointExcludeGenerals> excludeGeneralsList,
                           List<AppointSymbols> appointSymbolsList,
                           boolean isHuanHua) throws Exception {
        List<Generals> nimingAllList = new ArrayList<>();//全部
        List<Generals> generalsAll = new ArrayList<>();//非重复
        List<Generals> generalsAll2 = new ArrayList<>();//多余的，非限定
        Map<String,Destiny> destinyMap = new HashMap<>();//命格材料
        //获取基础数据
        List<Map<String,String>> lists = MainService.getExcelData(excludeGeneralsList);
        //处理基础数据
        MainService.handleExcelData(lists,appointGeneralsList,nimingAllList,generalsAll,destinyMap);
        //去除重复卡，保留异化卡
        MainService.handleNimingAllList(nimingAllList,generalsAll,generalsAll2);
        // 计算随从加成
        Map<Integer,List<Generals>> map = MainService.handleEntourage(nimingAllList,generalsAll,isHuanHua);
        //随从榜
        Map<Integer,List<Generals>> allEntourage = MainService.handleEntourageBillboard(generalsAll,generalsAll2,isHuanHua);
        //最佳随从表
        List<Generals> optimumEntourage = MainService.handleOptimumEntourage(generalsAll,nimingAllList,isHuanHua);
        long t1 = System.currentTimeMillis();
        //最终排列组合list
        List<Generals> nmList = MainService.handleFinalNmList(8,nimingAllList);

        //计算战力
        //List<Result> resultList = new ArrayList<>();
        List<Result> resultList2 = new ArrayList<>();
        List<Result> grilResultList = new ArrayList<>();
        MainService.handleSword(resultList2,grilResultList,nmList,appointGeneralsList,appointSymbolsList,excludeGeneralsList);

        //结果排序、排名
        MainService.resultSortAndRank(resultList2,grilResultList);

        long t2 = System.currentTimeMillis();
        System.out.println("用时："+(t2-t1)+"ms , 阵容数量："+resultList2.size());
        System.out.println("---------end---------");
        System.gc();

        //随从榜
        List<Generals> forceTopList = allEntourage.get(GeneralsEnum.ThreeCircles.Force.getCode());
        List<Generals> intellectTopList = allEntourage.get(GeneralsEnum.ThreeCircles.Intellect.getCode());
        List<Generals> troopsTopList = allEntourage.get(GeneralsEnum.ThreeCircles.Troops.getCode());
        MainService.allEntourageBillboard(allEntourage,forceTopList,intellectTopList,troopsTopList);

        Map<String,Object> model = new HashMap<>();
        model.put("top",top);
        model.put("advert",advert);
        model.put("simplifyList2",GeneralsUtil.getSimplifyList(resultList2));//简表（特殊战器）
        if(resultList2.size()>200){
            model.put("list2",resultList2.subList(0,200));//虚战力表（特殊战器）
        }else{
            model.put("list2",resultList2);//虚战力表（特殊战器）
        }
        if(grilResultList.size()>200) {
            model.put("grilList", grilResultList.subList(0, 200));//虚战力表（女队）
        }else{
            model.put("grilList", grilResultList);
        }
        model.put("forceTopList",forceTopList);
        model.put("intellectTopList",intellectTopList);
        model.put("troopsTopList",troopsTopList);
        model.put("optimumEntourageList",optimumEntourage);//最佳随从表
        model.put("destinyMap",destinyMap);

        MainService.createExcel(fileRemark,model);
        MainService.statistics(resultList2);
    }


}
