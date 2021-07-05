package com.ming.ppsg2.utils;

import com.ming.ppsg2.entity.AppointExcludeGenerals;
import com.ming.ppsg2.entity.AppointSymbols;
import com.ming.ppsg2.entity.ArmsBook;
import com.ming.ppsg2.entity.CountryArms;
import com.ming.ppsg2.entity.Destiny;
import com.ming.ppsg2.entity.Device;
import com.ming.ppsg2.entity.Generals;
import com.ming.ppsg2.entity.Result;
import com.ming.ppsg2.entity.Symbols;
import com.ming.ppsg2.entity.SymbolsTop;
import com.ming.ppsg2.entity.ThreeDimensional;
import com.ming.ppsg2.entity.ThreeDimensionals;
import com.ming.ppsg2.entity.Warpath;
import com.ming.ppsg2.enums.GeneralsEnum;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneralsUtil {

    public static void setTotal(Generals generals,ThreeDimensional three){
        generals.setTotalSword(generals.getTotalSword()+three.getTotalZl());
        generals.setTotalForce(generals.getTotalForce()+three.getForce());
        generals.setTotalIntellect(generals.getTotalIntellect()+three.getIntellect());
        generals.setTotalTroops(generals.getTotalTroops()+three.getTroops());
    }

    // 获取基础满级三维
    public static ThreeDimensional getMaxLevel(Generals generals) {
        ThreeDimensional three = new ThreeDimensional();
        GeneralsEnum.GeneralsType generalsType = null;
        for (GeneralsEnum.GeneralsType type : GeneralsEnum.GeneralsType.values()){
            if(type.getCode().equals(generals.getGeneralsType())){
                generalsType = type;
                break;
            }
        }
        if(generalsType!=null){
            int level = 120 - generals.getLevel();
            int force = generals.getForce() + generalsType.getForce() * level;
            int intellect = generals.getIntellect() + generalsType.getIntellect() * level;
            int troops = generals.getTroops() + generalsType.getTroops() * level;
            three.setForce(force);
            three.setIntellect(intellect);
            three.setTroops(troops);
            three.setTotalZl((force+intellect+troops)*2);
        }
        generals.setMaxThreeDimensional(three);
        setTotal(generals,three);
        return three;
    }

    // 获取科技三维
    public static ThreeDimensional getScience(Generals generals) {
        ThreeDimensional three = new ThreeDimensional();
        GeneralsEnum.Arms arms = null;
        for (GeneralsEnum.Arms arms1 : GeneralsEnum.Arms.values()){
            if(arms1.getCode().equals(generals.getArms())){
                arms = arms1;
                break;
            }
        }
        ThreeDimensional maxThree = generals.getMaxThreeDimensional();
        if(arms!=null){
            Double force = maxThree.getForce() * arms.getForceRate();
            Double intellect = maxThree.getIntellect() * arms.getIntellectRate();
            Double troops = maxThree.getTroops() * arms.getTroopsRate();
            three.setForce(force.intValue());
            three.setIntellect(intellect.intValue());
            three.setTroops(troops.intValue());
            three.setTotalZl((force.intValue()+intellect.intValue()+troops.intValue())*2);
        }
        generals.setScienceThreeDimensional(three);
        setTotal(generals,three);
        return three;
    }

    //最佳随从表  // todo:获取随从三维
    public static List<Generals> getOptimumEntourage(List<Generals> nimingList,List<Generals> all,boolean isHuanHua) {
        Map<String,Map<Integer,List<Generals>>> map = new LinkedHashMap<>();
        List<Generals> resultList = new ArrayList<>();
        for (Generals generals : nimingList){
            if(generals.getName().equalsIgnoreCase("桀骜孙策")){
                System.out.println();
            }
            Generals gs = new Generals();
            gs.setName(generals.getName());

            List<Integer> entourages = generals.getEntourages();
            List<Generals> list = new ArrayList<>();
            List<Integer> codes0 = generals.getCodes();
            List<Integer> codes1 = null;
            for(Generals generals1 : all){
                codes1 = generals1.getCodes();
                boolean flag = false;
                aa: for (Integer code0 : codes0){
                    for (Integer code1 : codes1){
                        if(code0.equals(code1)){
                            flag = true;
                            break aa;
                        }
                    }
                }
                if(flag){
                    continue;
                }

                generals1.setIsEntourage(true);

                generals1.getArmsBook().setEnableType(null);
                generals1.getArmsBook().setEnableTypeName(null);
                generals1.getArmsBook().setBook1(null);
                generals1.getArmsBook().setBookName1(null);
                generals1.getArmsBook().setBook2(null);
                generals1.getArmsBook().setBookName2(null);
                generals1.getArmsBook().setBook3(null);
                generals1.getArmsBook().setBookName3(null);
                generals1.getArmsBook().setBook4(null);
                generals1.getArmsBook().setBookName4(null);
                generals1.getArmsBook().setBook5(null);
                generals1.getArmsBook().setBookName5(null);
                list.add(generals1);
            }
            List<Generals> forceList = new ArrayList<>();//最佳武随榜
            List<Generals> intellectList = new ArrayList<>();//最佳智随榜
            List<Generals> troopsList = new ArrayList<>();//最佳兵随榜
            for(Generals generals1 : list){
                Generals copy1 = new Generals();
                BeanUtils.copyProperties(generals1,copy1);
                ArmsBook copyBook1 = new ArmsBook();
                BeanUtils.copyProperties(generals1.getArmsBook(),copyBook1);
                copy1.setArmsBook(copyBook1);

                Generals copy2 = new Generals();
                BeanUtils.copyProperties(generals1,copy2);
                ArmsBook copyBook2 = new ArmsBook();
                BeanUtils.copyProperties(generals1.getArmsBook(),copyBook2);
                copy2.setArmsBook(copyBook2);

                Generals copy3 = new Generals();
                BeanUtils.copyProperties(generals1,copy3);
                ArmsBook copyBook3 = new ArmsBook();
                BeanUtils.copyProperties(generals1.getArmsBook(),copyBook3);
                copy3.setArmsBook(copyBook3);

                Integer totalForce = 0;
                Integer totalIntellect = 0;
                Integer totalTroops = 0;
                ThreeDimensional maxLevelThree =  getMaxLevel(generals1);
                totalForce += maxLevelThree.getForce();
                totalIntellect += maxLevelThree.getIntellect();
                totalTroops += maxLevelThree.getTroops();

                ThreeDimensional scienceThree =  getScience(generals1);
                totalForce += scienceThree.getForce();
                totalIntellect += scienceThree.getIntellect();
                totalTroops += scienceThree.getTroops();

                ThreeDimensional holyStoneThree =  getHolyStone(generals1);
                totalForce += holyStoneThree.getForce();
                totalIntellect += holyStoneThree.getIntellect();
                totalTroops += holyStoneThree.getTroops();

                if(isHuanHua){
                    ThreeDimensional skinStoneThree =  getSkin(generals1);
                    totalForce += skinStoneThree.getForce();
                    totalIntellect += skinStoneThree.getIntellect();
                    totalTroops += skinStoneThree.getTroops();
                }

                //兵书最大武力
                ThreeDimensional armsBookThree1 =  getArmsBook(copy1,GeneralsEnum.ThreeCircles.Force.getCode());

                //兵书最大智力
                ThreeDimensional armsBookThree2 =  getArmsBook(copy2,GeneralsEnum.ThreeCircles.Intellect.getCode());

                //兵书最大兵力
                ThreeDimensional armsBookThree3 =  getArmsBook(copy3,GeneralsEnum.ThreeCircles.Troops.getCode());


                //将魂
                ThreeDimensional willSoulThree =  getWillSoul(generals1);
                totalForce += willSoulThree.getForce();
                totalIntellect += willSoulThree.getIntellect();
                totalTroops += willSoulThree.getTroops();

                //命格
                ThreeDimensional destinyThree =  getDestiny(generals1);
                totalForce += destinyThree.getForce();
                totalIntellect += destinyThree.getIntellect();
                totalTroops += destinyThree.getTroops();

                Double rate = 1.25;//联协倍率
                Integer add = 100;//作为随从+100属性

                copy1.setTotalForce(totalForce + armsBookThree1.getForce());
                copy1.setTotalIntellect(totalIntellect + armsBookThree1.getIntellect());
                copy1.setTotalTroops(totalTroops + armsBookThree1.getTroops());
                List<Integer> codeList1 = copy1.getCodes();
                boolean b1 = false;//entourages.contains(Integer.valueOf());//是否联协
                for(Integer code : codeList1){
                    b1 = entourages.contains(code);//是否联协
                    if(b1){
                        break;
                    }
                }
                //武力联协
                if(b1){
                    Double d1 = (copy1.getTotalForce()/2+add) * rate;
                    copy1.setTotalAddForce(d1.intValue());
                    Double d2 = (copy1.getTotalIntellect()/2+add) * rate;
                    copy1.setTotalAddIntellect(d2.intValue());
                    Double d3 = (copy1.getTotalTroops()/2+add) * rate;
                    copy1.setTotalAddTroops(d3.intValue());
                    copy1.setIsAssociation(true);
                    copy1.setRemark("联协");
                }else{
                    copy1.setTotalAddForce(copy1.getTotalForce()/2+add);
                    copy1.setTotalAddIntellect(copy1.getTotalIntellect()/2+add);
                    copy1.setTotalAddTroops(copy1.getTotalTroops()/2+add);
                    copy1.setIsAssociation(false);
                    copy1.setRemark("");
                }

                copy2.setTotalForce(totalForce + armsBookThree2.getForce());
                copy2.setTotalIntellect(totalIntellect + armsBookThree2.getIntellect());
                copy2.setTotalTroops(totalTroops + armsBookThree2.getTroops());
                List<Integer> codeList2 = copy2.getCodes();
                boolean b2 = false;//entourages.contains(Integer.valueOf());//是否联协
                for(Integer code : codeList2){
                    b2 = entourages.contains(code);//是否联协
                    if(b2){
                        break;
                    }
                }
                //智力联协
                if(b2){
                    Double d1 = (copy2.getTotalForce()/2+add) * rate;
                    copy2.setTotalAddForce(d1.intValue());
                    Double d2 = (copy2.getTotalIntellect()/2+add) * rate;
                    copy2.setTotalAddIntellect(d2.intValue());
                    Double d3 = (copy2.getTotalTroops()/2+add) * rate;
                    copy2.setTotalAddTroops(d3.intValue());
                    copy2.setIsAssociation(true);
                    copy2.setRemark("联协");
                }else{
                    copy2.setTotalAddForce(copy2.getTotalForce()/2+add);
                    copy2.setTotalAddIntellect(copy2.getTotalIntellect()/2+add);
                    copy2.setTotalAddTroops(copy2.getTotalTroops()/2+add);
                    copy2.setIsAssociation(false);
                    copy2.setRemark("");
                }

                copy3.setTotalForce(totalForce + armsBookThree3.getForce());
                copy3.setTotalIntellect(totalIntellect + armsBookThree3.getIntellect());
                copy3.setTotalTroops(totalTroops + armsBookThree3.getTroops());
                //boolean b3 = entourages.contains(Integer.valueOf(copy3.getCode()));//是否联协
                List<Integer> codeList3 = copy3.getCodes();
                boolean b3 = false;//entourages.contains(Integer.valueOf());//是否联协
                for(Integer code : codeList3){
                    b3 = entourages.contains(code);//是否联协
                    if(b3){
                        break;
                    }
                }
                //兵力联协
                if(b3){
                    Double d1 = (copy3.getTotalForce()/2+add) * rate;
                    copy3.setTotalAddForce(d1.intValue());
                    Double d2 = (copy3.getTotalIntellect()/2+add) * rate;
                    copy3.setTotalAddIntellect(d2.intValue());
                    Double d3 = (copy3.getTotalTroops()/2+add) * rate;
                    copy3.setTotalAddTroops(d3.intValue());
                    copy3.setIsAssociation(true);
                    copy3.setRemark("联协");
                }else{
                    copy3.setTotalAddForce(copy3.getTotalForce()/2+add);
                    copy3.setTotalAddIntellect(copy3.getTotalIntellect()/2+add);
                    copy3.setTotalAddTroops(copy3.getTotalTroops()/2+add);
                    copy3.setIsAssociation(false);
                    copy3.setRemark("");
                }

                forceList.add(copy1);
                intellectList.add(copy2);
                troopsList.add(copy3);
            }

            //武力排序（高->低）
            forceList.sort(new Comparator<Generals>() {
                @Override
                public int compare(Generals o1, Generals o2) {
                    return o2.getTotalAddForce() - o1.getTotalAddForce(); //降序
                }
            });

            //智力排序（高->低）
            intellectList.sort(new Comparator<Generals>() {
                @Override
                public int compare(Generals o1, Generals o2) {
                    return o2.getTotalAddIntellect() - o1.getTotalAddIntellect(); //降序
                }
            });

            //兵力排序（高->低）
            troopsList.sort(new Comparator<Generals>() {
                @Override
                public int compare(Generals o1, Generals o2) {
                    return o2.getTotalAddTroops() - o1.getTotalAddTroops(); //降序
                }
            });
            if(forceList.size()>9){
                forceList = forceList.subList(0, 10);
            }
            if(intellectList.size()>9){
                intellectList = intellectList.subList(0, 10);
            }
            if(troopsList.size()>9){
                troopsList = troopsList.subList(0, 10);
            }
            gs.setForceEntourageList(forceList);
            gs.setIntellectEntourageList(intellectList);
            gs.setTroopsEntourageList(troopsList);
            generals.setForceEntourageList(forceList);
            generals.setIntellectEntourageList(intellectList);
            generals.setTroopsEntourageList(troopsList);
            resultList.add(gs);
        }
        return resultList;
    }

    //随从榜
    public static Map<Integer,List<Generals>> getAllEntourage(List<Generals> all,boolean isHuanHua) {
        //排除自身的随从武将
        List<Generals> list = new ArrayList<>();
        for(Generals generals1 : all){
            generals1.setIsEntourage(true);

            generals1.getArmsBook().setEnableType(null);
            generals1.getArmsBook().setEnableTypeName(null);
            generals1.getArmsBook().setBook1(null);
            generals1.getArmsBook().setBookName1(null);
            generals1.getArmsBook().setBook2(null);
            generals1.getArmsBook().setBookName2(null);
            generals1.getArmsBook().setBook3(null);
            generals1.getArmsBook().setBookName3(null);
            generals1.getArmsBook().setBook4(null);
            generals1.getArmsBook().setBookName4(null);
            generals1.getArmsBook().setBook5(null);
            generals1.getArmsBook().setBookName5(null);
            list.add(generals1);
        }

        List<Generals> forceList = new ArrayList<>();//武力榜
        List<Generals> intellectList = new ArrayList<>();//智力榜
        List<Generals> troopsList = new ArrayList<>();//兵力榜

        for(Generals generals1 : list){
            Generals copy1 = new Generals();
            BeanUtils.copyProperties(generals1,copy1);
            ArmsBook copyBook1 = new ArmsBook();
            BeanUtils.copyProperties(generals1.getArmsBook(),copyBook1);
            copy1.setArmsBook(copyBook1);

            Generals copy2 = new Generals();
            BeanUtils.copyProperties(generals1,copy2);
            ArmsBook copyBook2 = new ArmsBook();
            BeanUtils.copyProperties(generals1.getArmsBook(),copyBook2);
            copy2.setArmsBook(copyBook2);

            Generals copy3 = new Generals();
            BeanUtils.copyProperties(generals1,copy3);
            ArmsBook copyBook3 = new ArmsBook();
            BeanUtils.copyProperties(generals1.getArmsBook(),copyBook3);
            copy3.setArmsBook(copyBook3);

            Integer totalForce = 0;
            Integer totalIntellect = 0;
            Integer totalTroops = 0;
            ThreeDimensional maxLevelThree =  getMaxLevel(generals1);
            totalForce += maxLevelThree.getForce();
            totalIntellect += maxLevelThree.getIntellect();
            totalTroops += maxLevelThree.getTroops();

            ThreeDimensional scienceThree =  getScience(generals1);
            totalForce += scienceThree.getForce();
            totalIntellect += scienceThree.getIntellect();
            totalTroops += scienceThree.getTroops();

            ThreeDimensional holyStoneThree =  getHolyStone(generals1);
            totalForce += holyStoneThree.getForce();
            totalIntellect += holyStoneThree.getIntellect();
            totalTroops += holyStoneThree.getTroops();

            if(isHuanHua){
                ThreeDimensional skinStoneThree =  getSkin(generals1);
                totalForce += skinStoneThree.getForce();
                totalIntellect += skinStoneThree.getIntellect();
                totalTroops += skinStoneThree.getTroops();
            }

            //兵书最大武力
            ThreeDimensional armsBookThree1 =  getArmsBook(copy1,GeneralsEnum.ThreeCircles.Force.getCode());

            //兵书最大智力
            ThreeDimensional armsBookThree2 =  getArmsBook(copy2,GeneralsEnum.ThreeCircles.Intellect.getCode());

            //兵书最大兵力
            ThreeDimensional armsBookThree3 =  getArmsBook(copy3,GeneralsEnum.ThreeCircles.Troops.getCode());


            ThreeDimensional willSoulThree =  getWillSoul(generals1);
            totalForce += willSoulThree.getForce();
            totalIntellect += willSoulThree.getIntellect();
            totalTroops += willSoulThree.getTroops();

            ThreeDimensional destinyThree =  getDestiny(generals1);
            totalForce += destinyThree.getForce();
            totalIntellect += destinyThree.getIntellect();
            totalTroops += destinyThree.getTroops();

            Double rate = 1.25;//联协倍率
            Integer add = 100;//作为随从+100属性

            copy1.setTotalForce(totalForce + armsBookThree1.getForce());
            copy1.setTotalIntellect(totalIntellect + armsBookThree1.getIntellect());
            copy1.setTotalTroops(totalTroops + armsBookThree1.getTroops());
            boolean b1 = false;//entourages.contains(Integer.valueOf(copy1.getCode()));//是否联协
            //武力联协
            if(b1){
                Double d1 = (copy1.getTotalForce()/2+add) * rate;
                copy1.setTotalAddForce(d1.intValue());
                Double d2 = (copy1.getTotalIntellect()/2+add) * rate;
                copy1.setTotalAddIntellect(d2.intValue());
                Double d3 = (copy1.getTotalTroops()/2+add) * rate;
                copy1.setTotalAddTroops(d3.intValue());
            }else{
                copy1.setTotalAddForce(copy1.getTotalForce()/2+add);
                copy1.setTotalAddIntellect(copy1.getTotalIntellect()/2+add);
                copy1.setTotalAddTroops(copy1.getTotalTroops()/2+add);
            }

            copy2.setTotalForce(totalForce + armsBookThree2.getForce());
            copy2.setTotalIntellect(totalIntellect + armsBookThree2.getIntellect());
            copy2.setTotalTroops(totalTroops + armsBookThree2.getTroops());
            boolean b2 = false;//entourages.contains(Integer.valueOf(copy2.getCode()));//是否联协
            //智力联协
            if(b2){
                Double d1 = (copy2.getTotalForce()/2+add) * rate;
                copy2.setTotalAddForce(d1.intValue());
                Double d2 = (copy2.getTotalIntellect()/2+add) * rate;
                copy2.setTotalAddIntellect(d2.intValue());
                Double d3 = (copy2.getTotalTroops()/2+add) * rate;
                copy2.setTotalAddTroops(d3.intValue());
            }else{
                copy2.setTotalAddForce(copy2.getTotalForce()/2+add);
                copy2.setTotalAddIntellect(copy2.getTotalIntellect()/2+add);
                copy2.setTotalAddTroops(copy2.getTotalTroops()/2+add);
            }

            copy3.setTotalForce(totalForce + armsBookThree3.getForce());
            copy3.setTotalIntellect(totalIntellect + armsBookThree3.getIntellect());
            copy3.setTotalTroops(totalTroops + armsBookThree3.getTroops());
            boolean b3 = false;//entourages.contains(Integer.valueOf(copy3.getCode()));//是否联协
            //兵力联协
            if(b3){
                Double d1 = (copy3.getTotalForce()/2+add) * rate;
                copy3.setTotalAddForce(d1.intValue());
                Double d2 = (copy3.getTotalIntellect()/2+add) * rate;
                copy3.setTotalAddIntellect(d2.intValue());
                Double d3 = (copy3.getTotalTroops()/2+add) * rate;
                copy3.setTotalAddTroops(d3.intValue());
            }else{
                copy3.setTotalAddForce(copy3.getTotalForce()/2+add);
                copy3.setTotalAddIntellect(copy3.getTotalIntellect()/2+add);
                copy3.setTotalAddTroops(copy3.getTotalTroops()/2+add);
            }

            forceList.add(copy1);
            intellectList.add(copy2);
            troopsList.add(copy3);
        }

        //武力排序（高->低）
        forceList.sort(new Comparator<Generals>() {
            @Override
            public int compare(Generals o1, Generals o2) {
                return o2.getTotalAddForce() - o1.getTotalAddForce(); //降序
            }
        });

        //智力排序（高->低）
        intellectList.sort(new Comparator<Generals>() {
            @Override
            public int compare(Generals o1, Generals o2) {
                return o2.getTotalAddIntellect() - o1.getTotalAddIntellect(); //降序
            }
        });

        //兵力排序（高->低）
        troopsList.sort(new Comparator<Generals>() {
            @Override
            public int compare(Generals o1, Generals o2) {
                return o2.getTotalAddTroops() - o1.getTotalAddTroops(); //降序
            }
        });

        Map<Integer,Generals> map = getGeneralsEntourage(forceList,intellectList,troopsList);
//        generals.setForceEntourage(map.get(GeneralsEnum.ThreeCircles.Force.getCode()));
//        generals.setIntellectEntourage(map.get(GeneralsEnum.ThreeCircles.Intellect.getCode()));
//        generals.setTroopsEntourage(map.get(GeneralsEnum.ThreeCircles.Troops.getCode()));
//
//        ThreeDimensional three = new ThreeDimensional();
//        three.setForce(generals.getForceEntourage().getTotalAddForce());
//        three.setIntellect(generals.getIntellectEntourage().getTotalAddIntellect());
//        three.setTroops(generals.getTroopsEntourage().getTotalAddTroops());
//        generals.setEntourageThreeDimensional(three);


        //武力排序（高->低）
        forceList.sort(new Comparator<Generals>() {
            @Override
            public int compare(Generals o1, Generals o2) {
                return o2.getTotalForce() - o1.getTotalForce(); //降序
            }
        });

        //智力排序（高->低）
        intellectList.sort(new Comparator<Generals>() {
            @Override
            public int compare(Generals o1, Generals o2) {
                return o2.getTotalIntellect() - o1.getTotalIntellect(); //降序
            }
        });

        //兵力排序（高->低）
        troopsList.sort(new Comparator<Generals>() {
            @Override
            public int compare(Generals o1, Generals o2) {
                return o2.getTotalTroops() - o1.getTotalTroops(); //降序
            }
        });

        Map<Integer,List<Generals>> top = new HashMap<>();
        top.put(GeneralsEnum.ThreeCircles.Force.getCode(),forceList);
        top.put(GeneralsEnum.ThreeCircles.Intellect.getCode(),intellectList);
        top.put(GeneralsEnum.ThreeCircles.Troops.getCode(),troopsList);
        return top;
    }

    // 获取随从三维
    // 随从三维 = 基础(满级) + 科技 + 四圣石 + 兵种 + 将魂 + 命格突破 + 幻化
    // 加成 = (随从三维/2+100)*(1+(0~0.25))
    public static Map<Integer,List<Generals>> getEntourage(Generals generals,List<Generals> all,boolean isHuanHua) {
        List<Integer> entourages = generals.getEntourages();

        //排除自身的随从武将
        List<Generals> list = new ArrayList<>();
        List<Integer> codes0 = generals.getCodes();
        List<Integer> codes1 = null;
        for(Generals generals1 : all){
            codes1 = generals1.getCodes();
            boolean flag = false;
            aa: for (Integer code0 : codes0){
                for (Integer code1 : codes1){
                    if(code0.equals(code1)){
                        flag = true;
                        break aa;
                    }
                }
            }

            if(!flag){
                generals1.setIsEntourage(true);

                generals1.getArmsBook().setEnableType(null);
                generals1.getArmsBook().setEnableTypeName(null);
                generals1.getArmsBook().setBook1(null);
                generals1.getArmsBook().setBookName1(null);
                generals1.getArmsBook().setBook2(null);
                generals1.getArmsBook().setBookName2(null);
                generals1.getArmsBook().setBook3(null);
                generals1.getArmsBook().setBookName3(null);
                generals1.getArmsBook().setBook4(null);
                generals1.getArmsBook().setBookName4(null);
                generals1.getArmsBook().setBook5(null);
                generals1.getArmsBook().setBookName5(null);
                list.add(generals1);
            }
        }

        List<Generals> forceList = new ArrayList<>();//武力榜
        List<Generals> intellectList = new ArrayList<>();//智力榜
        List<Generals> troopsList = new ArrayList<>();//兵力榜

        for(Generals generals1 : list){
            Generals copy1 = new Generals();
            BeanUtils.copyProperties(generals1,copy1);
            ArmsBook copyBook1 = new ArmsBook();
            BeanUtils.copyProperties(generals1.getArmsBook(),copyBook1);
            copy1.setArmsBook(copyBook1);

            Generals copy2 = new Generals();
            BeanUtils.copyProperties(generals1,copy2);
            ArmsBook copyBook2 = new ArmsBook();
            BeanUtils.copyProperties(generals1.getArmsBook(),copyBook2);
            copy2.setArmsBook(copyBook2);

            Generals copy3 = new Generals();
            BeanUtils.copyProperties(generals1,copy3);
            ArmsBook copyBook3 = new ArmsBook();
            BeanUtils.copyProperties(generals1.getArmsBook(),copyBook3);
            copy3.setArmsBook(copyBook3);

            Integer totalForce = 0;
            Integer totalIntellect = 0;
            Integer totalTroops = 0;
            ThreeDimensional maxLevelThree =  getMaxLevel(generals1);
            totalForce += maxLevelThree.getForce();
            totalIntellect += maxLevelThree.getIntellect();
            totalTroops += maxLevelThree.getTroops();

            ThreeDimensional scienceThree =  getScience(generals1);
            totalForce += scienceThree.getForce();
            totalIntellect += scienceThree.getIntellect();
            totalTroops += scienceThree.getTroops();

            ThreeDimensional holyStoneThree =  getHolyStone(generals1);
            totalForce += holyStoneThree.getForce();
            totalIntellect += holyStoneThree.getIntellect();
            totalTroops += holyStoneThree.getTroops();

            if(isHuanHua){
                ThreeDimensional skinStoneThree =  getSkin(generals1);
                totalForce += skinStoneThree.getForce();
                totalIntellect += skinStoneThree.getIntellect();
                totalTroops += skinStoneThree.getTroops();
            }

            //兵书最大武力
            ThreeDimensional armsBookThree1 =  getArmsBook(copy1,GeneralsEnum.ThreeCircles.Force.getCode());

            //兵书最大智力
            ThreeDimensional armsBookThree2 =  getArmsBook(copy2,GeneralsEnum.ThreeCircles.Intellect.getCode());

            //兵书最大兵力
            ThreeDimensional armsBookThree3 =  getArmsBook(copy3,GeneralsEnum.ThreeCircles.Troops.getCode());


            //将魂
            ThreeDimensional willSoulThree =  getWillSoul(generals1);
            totalForce += willSoulThree.getForce();
            totalIntellect += willSoulThree.getIntellect();
            totalTroops += willSoulThree.getTroops();

            //命格
            ThreeDimensional destinyThree =  getDestiny(generals1);
            totalForce += destinyThree.getForce();
            totalIntellect += destinyThree.getIntellect();
            totalTroops += destinyThree.getTroops();

            Double rate = 1.25;//联协倍率
            Integer add = 100;//作为随从+100属性

            copy1.setTotalForce(totalForce + armsBookThree1.getForce());
            copy1.setTotalIntellect(totalIntellect + armsBookThree1.getIntellect());
            copy1.setTotalTroops(totalTroops + armsBookThree1.getTroops());
            //boolean b1 = entourages.contains(Integer.valueOf(copy1.getCode()));//是否联协
            List<Integer> codeList1 = copy1.getCodes();
            boolean b1 = false;//entourages.contains(Integer.valueOf());//是否联协
            for(Integer code : codeList1){
                b1 = entourages.contains(code);//是否联协
                if(b1){
                    break;
                }
            }
            //武力联协
            if(b1){
                Double d1 = (copy1.getTotalForce()/2+add) * rate;
                copy1.setTotalAddForce(d1.intValue());
                Double d2 = (copy1.getTotalIntellect()/2+add) * rate;
                copy1.setTotalAddIntellect(d2.intValue());
                Double d3 = (copy1.getTotalTroops()/2+add) * rate;
                copy1.setTotalAddTroops(d3.intValue());
            }else{
                copy1.setTotalAddForce(copy1.getTotalForce()/2+add);
                copy1.setTotalAddIntellect(copy1.getTotalIntellect()/2+add);
                copy1.setTotalAddTroops(copy1.getTotalTroops()/2+add);
            }

            copy2.setTotalForce(totalForce + armsBookThree2.getForce());
            copy2.setTotalIntellect(totalIntellect + armsBookThree2.getIntellect());
            copy2.setTotalTroops(totalTroops + armsBookThree2.getTroops());
            //boolean b2 = entourages.contains(Integer.valueOf(copy2.getCode()));//是否联协
            List<Integer> codeList2 = copy2.getCodes();
            boolean b2 = false;//entourages.contains(Integer.valueOf());//是否联协
            for(Integer code : codeList2){
                b2 = entourages.contains(code);//是否联协
                if(b2){
                    break;
                }
            }
            //智力联协
            if(b2){
                Double d1 = (copy2.getTotalForce()/2+add) * rate;
                copy2.setTotalAddForce(d1.intValue());
                Double d2 = (copy2.getTotalIntellect()/2+add) * rate;
                copy2.setTotalAddIntellect(d2.intValue());
                Double d3 = (copy2.getTotalTroops()/2+add) * rate;
                copy2.setTotalAddTroops(d3.intValue());
            }else{
                copy2.setTotalAddForce(copy2.getTotalForce()/2+add);
                copy2.setTotalAddIntellect(copy2.getTotalIntellect()/2+add);
                copy2.setTotalAddTroops(copy2.getTotalTroops()/2+add);
            }

            copy3.setTotalForce(totalForce + armsBookThree3.getForce());
            copy3.setTotalIntellect(totalIntellect + armsBookThree3.getIntellect());
            copy3.setTotalTroops(totalTroops + armsBookThree3.getTroops());
            //boolean b3 = entourages.contains(Integer.valueOf(copy3.getCode()));//是否联协
            List<Integer> codeList3 = copy3.getCodes();
            boolean b3 = false;//entourages.contains(Integer.valueOf());//是否联协
            for(Integer code : codeList3){
                b3 = entourages.contains(code);//是否联协
                if(b3){
                    break;
                }
            }
            //兵力联协
            if(b3){
                Double d1 = (copy3.getTotalForce()/2+add) * rate;
                copy3.setTotalAddForce(d1.intValue());
                Double d2 = (copy3.getTotalIntellect()/2+add) * rate;
                copy3.setTotalAddIntellect(d2.intValue());
                Double d3 = (copy3.getTotalTroops()/2+add) * rate;
                copy3.setTotalAddTroops(d3.intValue());
            }else{
                copy3.setTotalAddForce(copy3.getTotalForce()/2+add);
                copy3.setTotalAddIntellect(copy3.getTotalIntellect()/2+add);
                copy3.setTotalAddTroops(copy3.getTotalTroops()/2+add);
            }

            forceList.add(copy1);
            intellectList.add(copy2);
            troopsList.add(copy3);
        }

        //武力排序（高->低）
        forceList.sort(new Comparator<Generals>() {
            @Override
            public int compare(Generals o1, Generals o2) {
                return o2.getTotalAddForce() - o1.getTotalAddForce(); //降序
            }
        });

        //智力排序（高->低）
        intellectList.sort(new Comparator<Generals>() {
            @Override
            public int compare(Generals o1, Generals o2) {
                return o2.getTotalAddIntellect() - o1.getTotalAddIntellect(); //降序
            }
        });

        //兵力排序（高->低）
        troopsList.sort(new Comparator<Generals>() {
            @Override
            public int compare(Generals o1, Generals o2) {
                return o2.getTotalAddTroops() - o1.getTotalAddTroops(); //降序
            }
        });

        Map<Integer,Generals> map = getGeneralsEntourage(forceList,intellectList,troopsList);
        generals.setForceEntourage(map.get(GeneralsEnum.ThreeCircles.Force.getCode()));
        generals.setIntellectEntourage(map.get(GeneralsEnum.ThreeCircles.Intellect.getCode()));
        generals.setTroopsEntourage(map.get(GeneralsEnum.ThreeCircles.Troops.getCode()));

        ThreeDimensional three = new ThreeDimensional();
        three.setForce(generals.getForceEntourage().getTotalAddForce());
        three.setIntellect(generals.getIntellectEntourage().getTotalAddIntellect());
        three.setTroops(generals.getTroopsEntourage().getTotalAddTroops());
        three.setTotalZl((three.getForce()+three.getIntellect()+three.getTroops())*2);
        generals.setEntourageThreeDimensional(three);
        setTotal(generals,three);

        //武力排序（高->低）
        forceList.sort(new Comparator<Generals>() {
            @Override
            public int compare(Generals o1, Generals o2) {
                return o2.getTotalForce() - o1.getTotalForce(); //降序
            }
        });

        //智力排序（高->低）
        intellectList.sort(new Comparator<Generals>() {
            @Override
            public int compare(Generals o1, Generals o2) {
                return o2.getTotalIntellect() - o1.getTotalIntellect(); //降序
            }
        });

        //兵力排序（高->低）
        troopsList.sort(new Comparator<Generals>() {
            @Override
            public int compare(Generals o1, Generals o2) {
                return o2.getTotalTroops() - o1.getTotalTroops(); //降序
            }
        });
        Map<Integer,List<Generals>> top = new HashMap<>();
        top.put(GeneralsEnum.ThreeCircles.Force.getCode(),forceList);
        top.put(GeneralsEnum.ThreeCircles.Intellect.getCode(),intellectList);
        top.put(GeneralsEnum.ThreeCircles.Troops.getCode(),troopsList);
        return top;
    }

    //判断上阵和随从是否相同
    public static boolean checkCode(List<Integer> codes0,List<Integer> codes1){
        boolean flag = false;
        aa: for (Integer code0 : codes0){
            for (Integer code1 : codes1){
                if(code0.equals(code1)){
                    flag = true;
                    break aa;
                }
            }
        }
        return flag;
    }

    private static Map<Integer,Generals> getGeneralsEntourage(List<Generals> forceList,List<Generals> intellectList,List<Generals> troopsList){
        Map<Integer,Generals> map = new HashMap<>();
        Integer total = 0;

        //武智兵
        Generals generals1_1 = forceList.get(0);//武随
        Generals generals1_2 = null;//智随
        Generals generals1_3 = null;//兵随
        for(Generals generals : intellectList){
            if(!checkCode(generals.getCodes(),generals1_1.getCodes())){
                generals1_2 = generals;
                break;
            }
        }
        for(Generals generals : troopsList){
            if(!checkCode(generals.getCodes(),generals1_1.getCodes()) && !checkCode(generals.getCodes(),generals1_2.getCodes())){
                generals1_3 = generals;
                break;
            }
        }
        Integer total1 = generals1_1.getTotalAddForce()+generals1_2.getTotalAddIntellect()+generals1_3.getTotalAddTroops();
        if(total1 > total){
            total = total1;
            map.put(GeneralsEnum.ThreeCircles.Force.getCode(),generals1_1);
            map.put(GeneralsEnum.ThreeCircles.Intellect.getCode(),generals1_2);
            map.put(GeneralsEnum.ThreeCircles.Troops.getCode(),generals1_3);
        }

        //武兵智
        Generals generals2_1 = forceList.get(0);//武随
        Generals generals2_2 = null;//智随
        Generals generals2_3 = null;//兵随
        for(Generals generals : troopsList){
            if(!checkCode(generals.getCodes(),generals2_1.getCodes())){
                generals2_3 = generals;
                break;
            }
        }
        for(Generals generals : intellectList){
            if(!checkCode(generals.getCodes(),generals2_1.getCodes()) && !checkCode(generals.getCodes(),generals2_3.getCodes())){
                generals2_2 = generals;
                break;
            }
        }
        Integer total2 = generals2_1.getTotalAddForce()+generals2_2.getTotalAddIntellect()+generals2_3.getTotalAddTroops();
        if(total2 > total){
            total = total2;
            map.put(GeneralsEnum.ThreeCircles.Force.getCode(),generals2_1);
            map.put(GeneralsEnum.ThreeCircles.Intellect.getCode(),generals2_2);
            map.put(GeneralsEnum.ThreeCircles.Troops.getCode(),generals2_3);
        }

        //兵武智
        Generals generals3_1 = null;//武随
        Generals generals3_2 = null;//智随
        Generals generals3_3 = troopsList.get(0);//兵随
        for(Generals generals : forceList){
            if(!checkCode(generals.getCodes(),generals3_3.getCodes())){
                generals3_1 = generals;
                break;
            }
        }
        for(Generals generals : intellectList){
            if(!checkCode(generals.getCodes(),generals3_3.getCodes()) && !checkCode(generals.getCodes(),generals3_1.getCodes())){
                generals3_2 = generals;
                break;
            }
        }
        Integer total3 = generals3_1.getTotalAddForce()+generals3_2.getTotalAddIntellect()+generals3_3.getTotalAddTroops();
        if(total3 > total){
            total = total3;
            map.put(GeneralsEnum.ThreeCircles.Force.getCode(),generals3_1);
            map.put(GeneralsEnum.ThreeCircles.Intellect.getCode(),generals3_2);
            map.put(GeneralsEnum.ThreeCircles.Troops.getCode(),generals3_3);
        }

        //兵智武
        Generals generals4_1 = null;//武随
        Generals generals4_2 = null;//智随
        Generals generals4_3 = troopsList.get(0);//兵随
        for(Generals generals : intellectList){
            if(!checkCode(generals.getCodes(),generals4_3.getCodes())){
                generals4_2 = generals;
                break;
            }
        }
        for(Generals generals : forceList){
            if(!checkCode(generals.getCodes(),generals4_3.getCodes()) && !checkCode(generals.getCodes(),generals4_2.getCodes())){
                generals4_1 = generals;
                break;
            }
        }
        Integer total4 = generals4_1.getTotalAddForce()+generals4_2.getTotalAddIntellect()+generals4_3.getTotalAddTroops();
        if(total4 > total){
            total = total4;
            map.put(GeneralsEnum.ThreeCircles.Force.getCode(),generals4_1);
            map.put(GeneralsEnum.ThreeCircles.Intellect.getCode(),generals4_2);
            map.put(GeneralsEnum.ThreeCircles.Troops.getCode(),generals4_3);
        }

        //智兵武
        Generals generals5_1 = null;//武随
        Generals generals5_2 = intellectList.get(0);//智随
        Generals generals5_3 = null;//兵随
        for(Generals generals : troopsList){
            if(!checkCode(generals.getCodes(),generals5_2.getCodes())){
                generals5_3 = generals;
                break;
            }
        }
        for(Generals generals : forceList){
            if(!checkCode(generals.getCodes(),generals5_2.getCodes()) && !checkCode(generals.getCodes(),generals5_3.getCodes())){
                generals5_1 = generals;
                break;
            }
        }
        Integer total5 = generals5_1.getTotalAddForce()+generals5_2.getTotalAddIntellect()+generals5_3.getTotalAddTroops();
        if(total5 > total){
            total = total5;
            map.put(GeneralsEnum.ThreeCircles.Force.getCode(),generals5_1);
            map.put(GeneralsEnum.ThreeCircles.Intellect.getCode(),generals5_2);
            map.put(GeneralsEnum.ThreeCircles.Troops.getCode(),generals5_3);
        }

        //智武兵
        Generals generals6_1 = null;//武随
        Generals generals6_2 = intellectList.get(0);//智随
        Generals generals6_3 = null;//兵随
        for(Generals generals : forceList){
            if(!checkCode(generals.getCodes(),generals6_2.getCodes())){
                generals6_1 = generals;
                break;
            }
        }
        for(Generals generals : troopsList){
            if(!checkCode(generals.getCodes(),generals6_2.getCodes()) && !checkCode(generals.getCodes(),generals6_1.getCodes())){
                generals6_3 = generals;
                break;
            }
        }
        Integer total6 = generals6_1.getTotalAddForce()+generals6_2.getTotalAddIntellect()+generals6_3.getTotalAddTroops();
        if(total6 > total){
            total = total6;
            map.put(GeneralsEnum.ThreeCircles.Force.getCode(),generals6_1);
            map.put(GeneralsEnum.ThreeCircles.Intellect.getCode(),generals6_2);
            map.put(GeneralsEnum.ThreeCircles.Troops.getCode(),generals6_3);
        }

        return map;
    }

    // 获取四圣石三维
    public static ThreeDimensional getHolyStone(Generals generals) {
        ThreeDimensional three = new ThreeDimensional();
        int force = 1077;
        int intellect = 1077;
        int troops = 2291;
        three.setForce(force);
        three.setIntellect(intellect);
        three.setTroops(troops);
        three.setTotalZl((force+intellect+troops)*2);
        generals.setHolyStoneThreeDimensional(three);
        setTotal(generals,three);
        return three;
    }

    //战器三维
    public static ThreeDimensional getWarDevice(Generals generals) {
        ThreeDimensional three = new ThreeDimensional();
        List<Integer> typeList = generals.getWarDevices();
        List<GeneralsEnum.WarDevice> list = new ArrayList<>();
        for(Integer type : typeList){
            for (GeneralsEnum.WarDevice device : GeneralsEnum.WarDevice.values()){
                if(device.getCode().equals(type)){
                    list.add(device);
                    break;
                }
            }
        }
        int force = 0;
        int intellect = 0;
        int troops = 0;

        ThreeDimensional deviceBase = null;
        ThreeDimensional deviceStrengthen = null;
        ThreeDimensional deviceQuenching = new ThreeDimensional(0,0,0);
        ThreeDimensional deviceExclusive = null;
        String quenchingName1 = "";
        String quenchingName2 = "";

        for(GeneralsEnum.WarDevice warDevice : list){
            force = warDevice.getForce() + warDevice.getStrengthenForce();
            intellect = warDevice.getIntellect() + warDevice.getStrengthenIntellect();
            troops = warDevice.getTroops() + warDevice.getStrengthenTroops();
            deviceBase = new ThreeDimensional(warDevice.getForce(),warDevice.getIntellect(),warDevice.getTroops());
            deviceStrengthen = new ThreeDimensional(warDevice.getStrengthenForce(),warDevice.getStrengthenIntellect(),warDevice.getStrengthenTroops());
            if(troops >= force && troops >= intellect){//troops
                Double d= troops * warDevice.getQuenchingTroopsRate();
                deviceQuenching.setTroops(d.intValue()*2);
                troops += d.intValue()*2;
                quenchingName2 = quenchingName1 = "战器的兵力值提升"+(int)(warDevice.getQuenchingTroopsRate()*100)+"%";
            }else if(force >= intellect && force >= troops){//force
                Double d= force * warDevice.getQuenchingForceRate();
                deviceQuenching.setForce(d.intValue()*2);
                force += d.intValue()*2;
                quenchingName2 = quenchingName1 = "战器的武力值提升"+(int)(warDevice.getQuenchingForceRate()*100)+"%";
            }else{//intellect
                Double d= intellect * warDevice.getQuenchingIntellectRate();
                deviceQuenching.setIntellect(d.intValue()*2);
                quenchingName2 = quenchingName1 = "战器的智力值提升"+(int)(warDevice.getQuenchingIntellectRate()*100)+"%";
                intellect += d.intValue()*2;
            }
            force += warDevice.getExclusiveForce();
            intellect += warDevice.getExclusiveIntellect();
            troops += warDevice.getExclusiveTroops();
            deviceExclusive = new ThreeDimensional(warDevice.getExclusiveForce(),warDevice.getExclusiveIntellect(),warDevice.getExclusiveTroops());
        }
        three.setForce(force);
        three.setIntellect(intellect);
        three.setTroops(troops);
        three.setTotalZl((force+intellect+troops)*2);
        generals.setWarDeviceThreeDimensional(three);
        setTotal(generals,three);

        Device device = new Device();
        device.setName(list.get(0).getName());
        device.setDesc(list.get(0).getDesc());
        device.setQuenchingName1(quenchingName1);
        device.setQuenchingName2(quenchingName2);
        device.setDeviceBaseThreeDimensional(deviceBase);
        device.setDeviceStrengthenThreeDimensional(deviceStrengthen);
        device.setDeviceQuenchingThreeDimensional(deviceQuenching);
        device.setDeviceExclusiveThreeDimensional(deviceExclusive);
        generals.setDevice(device);
        return three;
    }

    //战器三维(特殊战器)
    public static ThreeDimensional getWarDevice2(Generals generals) {
        ThreeDimensional three = new ThreeDimensional();
        List<Integer> typeList = generals.getWarDevices();
        List<GeneralsEnum.WarDevice> list = new ArrayList<>();
        Integer incr = 5;
        for(Integer type : typeList){
            for (GeneralsEnum.WarDevice device : GeneralsEnum.WarDevice.values()){
                if(device.getCode().equals(type+incr)){
                    list.add(device);
                    break;
                }
            }
        }
        int force = 0;
        int intellect = 0;
        int troops = 0;

        ThreeDimensional deviceBase = null;
        ThreeDimensional deviceStrengthen = null;
        ThreeDimensional deviceQuenching = new ThreeDimensional(0,0,0);
        ThreeDimensional deviceExclusive = null;
        ThreeDimensional deviceAwaken = null;//觉醒三维
        ThreeDimensional deviceRefiner = null;//炼器三维
        String quenchingName1 = "";
        String quenchingName2 = "";

        ThreeDimensional destinyThree = generals.getDestinyThreeDimensional();
        for(GeneralsEnum.WarDevice warDevice : list){
            force = warDevice.getForce() + warDevice.getStrengthenForce();
            intellect = warDevice.getIntellect() + warDevice.getStrengthenIntellect();
            troops = warDevice.getTroops() + warDevice.getStrengthenTroops();
            deviceBase = new ThreeDimensional(warDevice.getForce(),warDevice.getIntellect(),warDevice.getTroops());
            deviceStrengthen = new ThreeDimensional(warDevice.getStrengthenForce(),warDevice.getStrengthenIntellect(),warDevice.getStrengthenTroops());
            if(troops >= force && troops >= intellect){//troops
                Double d= troops * warDevice.getQuenchingTroopsRate();
                deviceQuenching.setTroops(d.intValue()*2);
                troops += d.intValue()*2;
                quenchingName2 = quenchingName1 = "战器的兵力值提升"+(int)(warDevice.getQuenchingTroopsRate()*100)+"%";
            }else if(force >= intellect && force >= troops){//force
                Double d= force * warDevice.getQuenchingForceRate();
                deviceQuenching.setForce(d.intValue()*2);
                force += d.intValue()*2;
                quenchingName2 = quenchingName1 = "战器的武力值提升"+(int)(warDevice.getQuenchingForceRate()*100)+"%";
            }else{//intellect
                Double d= intellect * warDevice.getQuenchingIntellectRate();
                deviceQuenching.setIntellect(d.intValue()*2);
                quenchingName2 = quenchingName1 = "战器的智力值提升"+(int)(warDevice.getQuenchingIntellectRate()*100)+"%";
                intellect += d.intValue()*2;
            }
            force += warDevice.getExclusiveForce();
            intellect += warDevice.getExclusiveIntellect();
            troops += warDevice.getExclusiveTroops();
            deviceExclusive = new ThreeDimensional(warDevice.getExclusiveForce(),warDevice.getExclusiveIntellect(),warDevice.getExclusiveTroops());

            //觉醒
            force += warDevice.getAwakenForce();
            intellect += warDevice.getAwakenIntellect();
            troops += warDevice.getAwakenTroops();
            deviceAwaken = new ThreeDimensional(warDevice.getAwakenForce(),warDevice.getAwakenIntellect(),warDevice.getAwakenTroops());

            //炼器 Refiner
            int force0 = 49;
            int intellect0 = 49;
            int troops0 = 70;
            if(generals.getIsResonance()){
                force0 = 1522 + (int)(destinyThree.getForce()*0.7);
                intellect0 = 646 + (int)(destinyThree.getIntellect()*0.7);
                troops0 = 3800 + (int)(destinyThree.getTroops()*0.7);
            }
            force += force0;
            intellect += intellect0;
            troops += troops0;
            deviceRefiner = new ThreeDimensional(force0,intellect0,troops0);

            //器灵
        }
        three.setForce(force);
        three.setIntellect(intellect);
        three.setTroops(troops);
        three.setTotalZl((force+intellect+troops)*2);
        generals.setWarDevice2ThreeDimensional(three);
        setTotal(generals,three);

        Device device = new Device();
        device.setName(list.get(0).getName());
        device.setDesc(list.get(0).getDesc());
        device.setQuenchingName1(quenchingName1);
        device.setQuenchingName2(quenchingName2);
        device.setDeviceBaseThreeDimensional(deviceBase);
        device.setDeviceStrengthenThreeDimensional(deviceStrengthen);
        device.setDeviceQuenchingThreeDimensional(deviceQuenching);
        device.setDeviceExclusiveThreeDimensional(deviceExclusive);
        device.setDeviceAwakenThreeDimensional(deviceAwaken);
        device.setDeviceRefiner95ThreeDimensional(deviceRefiner);
        generals.setDevice2(device);
        return three;
    }

    //兵种(兵书)三维
    public static ThreeDimensional getArmsBook(Generals generals) {
        ThreeDimensional three = new ThreeDimensional();

        ArmsBook armsBook = generals.getArmsBook();
        ThreeDimensional maxThree = generals.getMaxThreeDimensional();
        Double force1 = 0d;
        Double intellect1 = 0d;
        Double troops1 = 0d;
        Double force2 = 0d;
        Double intellect2 = 0d;
        Double troops2 = 0d;
        String typeName1 = "";
        String typeName2 = "";
        for (GeneralsEnum.ArmsType type : GeneralsEnum.ArmsType.values()){
            if(type.getCode().equals(armsBook.getArmsType1())){
                force1 = maxThree.getForce() * type.getForceRate();
                intellect1 = maxThree.getIntellect() * type.getIntellectRate();
                troops1 = maxThree.getTroops() * type.getTroopsRate();
                typeName1 = type.getName();
            }
            if(type.getCode().equals(armsBook.getArmsType2())){
                force2 = maxThree.getForce() * type.getForceRate();
                intellect2 = maxThree.getIntellect() * type.getIntellectRate();
                troops2 = maxThree.getTroops() * type.getTroopsRate();
                typeName2 = type.getName();
            }
        }
        Double total1 = force1 + intellect1 + troops1;
        Double total2 = force2 + intellect2 + troops2;

        int force = 0;
        int intellect = 0;
        int troops = 0;
        if(total1 > total2){
            force = force1.intValue();
            intellect = intellect1.intValue();
            troops = troops1.intValue();
            armsBook.setEnableType(armsBook.getArmsType1());
            armsBook.setEnableTypeName(typeName1);
        }else{
            force = force2.intValue();
            intellect = intellect2.intValue();
            troops = troops2.intValue();
            armsBook.setEnableType(armsBook.getArmsType2());
            armsBook.setEnableTypeName(typeName2);
        }

        GeneralsEnum.ArmsBook book1 = null;
        GeneralsEnum.ArmsBook book2 = null;
        GeneralsEnum.ArmsBook book3 = null;
        GeneralsEnum.ArmsBook book4 = null;
        GeneralsEnum.ArmsBook book5 = null;
        for (GeneralsEnum.ArmsBook book : GeneralsEnum.ArmsBook.values()){
            if(book.getCode().equals(armsBook.getArmsBookOne1())){
                book1 = book;
            }
            if(book.getCode().equals(armsBook.getArmsBookTwo1())){
                book2 = book;
            }
            if(book.getCode().equals(armsBook.getArmsBookThree1())){
                book3 = book;
            }
            if(book.getCode().equals(armsBook.getArmsBookFour1())){
                book4 = book;
            }
            if(book.getCode().equals(armsBook.getArmsBookFive1())){
                book5 = book;
            }
        }

        armsBook.setBook1(book1.getCode());
        armsBook.setBookName1(book1.getName());
        armsBook.setBook2(book2.getCode());
        armsBook.setBookName2(book2.getName());
        armsBook.setBook3(book3.getCode());
        armsBook.setBookName3(book3.getName());
        armsBook.setBook4(book4.getCode());
        armsBook.setBookName4(book4.getName());
        armsBook.setBook5(book5.getCode());
        armsBook.setBookName5(book5.getName());

        force += (book1.getForce() + book2.getForce() + book3.getForce() + book4.getForce() + book5.getForce());
        intellect += (book1.getIntellect() + book2.getIntellect() + book3.getIntellect() + book4.getIntellect() + book5.getIntellect());
        troops += (book1.getTroops() + book2.getTroops() + book3.getTroops() + book4.getTroops() + book5.getTroops());

        three.setForce(force);
        three.setIntellect(intellect);
        three.setTroops(troops);
        three.setTotalZl((force+intellect+troops)*2);
        generals.setArmsBookThreeDimensional(three);
        setTotal(generals,three);
        return three;
    }

    //计算随从兵种(兵书)三维
    //code : GeneralsEnum.ThreeCircles
    public static ThreeDimensional getArmsBook(Generals generals,Integer code) {
        ThreeDimensional three = new ThreeDimensional();

        ArmsBook armsBook = generals.getArmsBook();
        ThreeDimensional maxThree = generals.getMaxThreeDimensional();
        //兵种1三维
        Double force1 = 0d;
        Double intellect1 = 0d;
        Double troops1 = 0d;
        //兵种2三维
        Double force2 = 0d;
        Double intellect2 = 0d;
        Double troops2 = 0d;
        for (GeneralsEnum.ArmsType type : GeneralsEnum.ArmsType.values()){
            if(type.getCode().equals(armsBook.getArmsType1())){
                force1 = maxThree.getForce() * type.getForceRate();
                intellect1 = maxThree.getIntellect() * type.getIntellectRate();
                troops1 = maxThree.getTroops() * type.getTroopsRate();
            }
            if(type.getCode().equals(armsBook.getArmsType2())){
                force2 = maxThree.getForce() * type.getForceRate();
                intellect2 = maxThree.getIntellect() * type.getIntellectRate();
                troops2 = maxThree.getTroops() * type.getTroopsRate();
            }
        }
        //判断启用兵种
        if(code.equals(GeneralsEnum.ThreeCircles.Force.getCode())){
            if(force1 > force2){
                armsBook.setEnableType(armsBook.getArmsType1());
            }else{
                armsBook.setEnableType(armsBook.getArmsType2());
            }
        }else if(code.equals(GeneralsEnum.ThreeCircles.Intellect.getCode())){
            if(intellect1 > intellect2){
                armsBook.setEnableType(armsBook.getArmsType1());
            }else{
                armsBook.setEnableType(armsBook.getArmsType2());
            }
        }else if(code.equals(GeneralsEnum.ThreeCircles.Troops.getCode())){
            if(troops1 > troops2){
                armsBook.setEnableType(armsBook.getArmsType1());
            }else{
                armsBook.setEnableType(armsBook.getArmsType2());
            }
        }

        //设置启用兵种名称
        for (GeneralsEnum.ArmsType type : GeneralsEnum.ArmsType.values()){
            if(type.getCode().equals(armsBook.getEnableType())){
                armsBook.setEnableTypeName(type.getName());
            }
        }

        //启用兵种加成三维
        Integer force = 0;
        Integer intellect = 0;
        Integer troops = 0;
        if(armsBook.getArmsType1().equals(armsBook.getEnableType())){
            force = force1.intValue();
            intellect = intellect1.intValue();
            troops = troops1.intValue();
        }else{
            force = force2.intValue();
            intellect = intellect2.intValue();
            troops = troops2.intValue();
        }

        //计算兵书加成三维
        GeneralsEnum.ArmsBook book1 = null;
        GeneralsEnum.ArmsBook book11 = null;
        GeneralsEnum.ArmsBook book2 = null;
        GeneralsEnum.ArmsBook book22 = null;
        GeneralsEnum.ArmsBook book3 = null;
        GeneralsEnum.ArmsBook book33 = null;
        GeneralsEnum.ArmsBook book4 = null;
        GeneralsEnum.ArmsBook book44 = null;
        GeneralsEnum.ArmsBook book5 = null;
        GeneralsEnum.ArmsBook book55 = null;
        for (GeneralsEnum.ArmsBook book : GeneralsEnum.ArmsBook.values()){
            if(book.getCode().equals(armsBook.getArmsBookOne1())){
                book1 = book;
            }
            if(book.getCode().equals(armsBook.getArmsBookOne2())){
                book11 = book;
            }
            if(book.getCode().equals(armsBook.getArmsBookTwo1())){
                book2 = book;
            }
            if(book.getCode().equals(armsBook.getArmsBookTwo2())){
                book22 = book;
            }
            if(book.getCode().equals(armsBook.getArmsBookThree1())){
                book3 = book;
            }
            if(book.getCode().equals(armsBook.getArmsBookThree2())){
                book33 = book;
            }
            if(book.getCode().equals(armsBook.getArmsBookFour1())){
                book4 = book;
            }
            if(book.getCode().equals(armsBook.getArmsBookFour2())){
                book44 = book;
            }
            if(book.getCode().equals(armsBook.getArmsBookFive1())){
                book5 = book;
            }
            if(book.getCode().equals(armsBook.getArmsBookFive2())){
                book55 = book;
            }
        }

        //判断使用兵书
        if(code.equals(GeneralsEnum.ThreeCircles.Force.getCode())){
            if(book1.getForce() >= book11.getForce()){armsBook.setBook1(book1.getCode());}else{armsBook.setBook1(book11.getCode());}
            if(book2.getForce() >= book22.getForce()){armsBook.setBook2(book2.getCode());}else{armsBook.setBook2(book22.getCode());}
            if(book3.getForce() >= book33.getForce()){armsBook.setBook3(book3.getCode());}else{armsBook.setBook3(book33.getCode());}
            if(book4.getForce() >= book44.getForce()){armsBook.setBook4(book4.getCode());}else{armsBook.setBook4(book44.getCode());}
            if(book5.getForce() >= book55.getForce()){armsBook.setBook5(book5.getCode());}else{armsBook.setBook5(book55.getCode());}
        }else if(code.equals(GeneralsEnum.ThreeCircles.Intellect.getCode())){
            if(book1.getIntellect() >= book11.getIntellect()){armsBook.setBook1(book1.getCode());}else{armsBook.setBook1(book11.getCode());}
            if(book2.getIntellect() >= book22.getIntellect()){armsBook.setBook2(book2.getCode());}else{armsBook.setBook2(book22.getCode());}
            if(book3.getIntellect() >= book33.getIntellect()){armsBook.setBook3(book3.getCode());}else{armsBook.setBook3(book33.getCode());}
            if(book4.getIntellect() >= book44.getIntellect()){armsBook.setBook4(book4.getCode());}else{armsBook.setBook4(book44.getCode());}
            if(book5.getIntellect() >= book55.getIntellect()){armsBook.setBook5(book5.getCode());}else{armsBook.setBook5(book55.getCode());}
        }else if(code.equals(GeneralsEnum.ThreeCircles.Troops.getCode())){
            if(book1.getTroops() >= book11.getTroops()){armsBook.setBook1(book1.getCode());}else{armsBook.setBook1(book11.getCode());}
            if(book2.getTroops() >= book22.getTroops()){armsBook.setBook2(book2.getCode());}else{armsBook.setBook2(book22.getCode());}
            if(book3.getTroops() >= book33.getTroops()){armsBook.setBook3(book3.getCode());}else{armsBook.setBook3(book33.getCode());}
            if(book4.getTroops() >= book44.getTroops()){armsBook.setBook4(book4.getCode());}else{armsBook.setBook4(book44.getCode());}
            if(book5.getTroops() >= book55.getTroops()){armsBook.setBook5(book5.getCode());}else{armsBook.setBook5(book55.getCode());}
        }

        //设置使用兵书名称
        for (GeneralsEnum.ArmsBook book : GeneralsEnum.ArmsBook.values()){
            if(book.getCode().equals(armsBook.getBook1())){
                armsBook.setBookName1(book.getName());
            }
            if(book.getCode().equals(armsBook.getBook2())){
                armsBook.setBookName2(book.getName());
            }
            if(book.getCode().equals(armsBook.getBook3())){
                armsBook.setBookName3(book.getName());
            }
            if(book.getCode().equals(armsBook.getBook4())){
                armsBook.setBookName4(book.getName());
            }
            if(book.getCode().equals(armsBook.getBook5())){
                armsBook.setBookName5(book.getName());
            }
        }

        //兵书1加成三维
        if(armsBook.getArmsBookOne1().equals(armsBook.getBook1())) {
            force += book1.getForce();
            intellect += book1.getIntellect();
            troops += book1.getTroops();
        }else{
            force += book11.getForce();
            intellect += book11.getIntellect();
            troops += book11.getTroops();
        }

        //兵书2加成三维
        if(armsBook.getArmsBookTwo1().equals(armsBook.getBook2())) {
            force += book2.getForce();
            intellect += book2.getIntellect();
            troops += book2.getTroops();
        }else{
            force += book22.getForce();
            intellect += book22.getIntellect();
            troops += book22.getTroops();
        }

        //兵书3加成三维
        if(armsBook.getArmsBookThree1().equals(armsBook.getBook3())) {
            force += book3.getForce();
            intellect += book3.getIntellect();
            troops += book3.getTroops();
        }else{
            force += book33.getForce();
            intellect += book33.getIntellect();
            troops += book33.getTroops();
        }

        //兵书4加成三维
        if(armsBook.getArmsBookFour1().equals(armsBook.getBook4())) {
            force += book4.getForce();
            intellect += book4.getIntellect();
            troops += book4.getTroops();
        }else{
            force += book44.getForce();
            intellect += book44.getIntellect();
            troops += book44.getTroops();
        }

        //兵书5加成三维
        if(armsBook.getArmsBookFive1().equals(armsBook.getBook5())) {
            force += book5.getForce();
            intellect += book5.getIntellect();
            troops += book5.getTroops();
        }else{
            force += book55.getForce();
            intellect += book55.getIntellect();
            troops += book55.getTroops();
        }

        three.setForce(force);
        three.setIntellect(intellect);
        three.setTroops(troops);
        generals.setArmsBookThreeDimensional(three);
        return three;
    }

    //将魂
    public static ThreeDimensional getWillSoul(Generals generals) {
        ThreeDimensional three = new ThreeDimensional();

        GeneralsEnum.GeneralsType generalsType = null;
        for (GeneralsEnum.GeneralsType type : GeneralsEnum.GeneralsType.values()){
            if(type.getCode().equals(generals.getGeneralsType())){
                generalsType = type;
                break;
            }
        }

        int force = 0;
        int intellect = 0;
        int troops = 0;

        force = generalsType.getForce() * 4 * 30;
        intellect = generalsType.getIntellect() * 4 * 30;
        troops = generalsType.getTroops() * 4 * 30;

        troops += 200;
        //随从+100兵力
        if(generals.getIsEntourage()){
            //troops += 100;
        }

        three.setForce(force);
        three.setIntellect(intellect);
        three.setTroops(troops);
        three.setTotalZl((force+intellect+troops)*2);
        generals.setWillSoulThreeDimensional(three);
        setTotal(generals,three);
        return three;
    }

    //计算兵符主属性
    private static GeneralsEnum.SymbolsMainAttr countSymbolsMainMax(ThreeDimensional totalThree,int[] n){
        int totalForce = totalThree.getForce();
        int totalIntellect = totalThree.getIntellect();
        int totalTroops = totalThree.getTroops();
        Map<GeneralsEnum.SymbolsMainAttr,Integer> map = new HashMap<>();
        for (int i=0;i<n.length;i++){
            GeneralsEnum.SymbolsMainAttr mainAttr = GeneralsEnum.SymbolsMainAttr.get(n[i]);
            int code = mainAttr.getCode();
            Double d = 0d;
            if(code == 4 || code ==5 || code==6) {//武力加成
                d = totalForce * mainAttr.getRate();
            }else if(code == 7 || code ==8 || code==9) {//智力加成
                d = totalIntellect * mainAttr.getRate();
            }else if(code == 10 || code ==11 || code==12) {//兵力加成
                d = totalTroops * mainAttr.getRate();
            }else if(code == 13 || code ==14 || code==15) {//全属性加成
                Double d1 = totalForce * mainAttr.getRate();
                Double d2 = totalIntellect * mainAttr.getRate();
                Double d3 = totalTroops * mainAttr.getRate();
                d = d1+d2+d3;
            }
            map.put(mainAttr,d.intValue());
        }
        return MapUtils.sortByValue(map).entrySet().stream().findFirst().get().getKey();
    }

    //计算兵符主属性
    private static Map<GeneralsEnum.SymbolsSecondAttr,Integer> countSymbolsSecondTop(ThreeDimensional totalThree, int[] seconds, List<Generals> generalsAll, List<Generals> generalsWei, List<Generals> generalsShu, List<Generals> generalsWu, List<Generals> generalsQun){
        int totalForce = totalThree.getForce();
        int totalIntellect = totalThree.getIntellect();
        int totalTroops = totalThree.getTroops();
        Map<GeneralsEnum.SymbolsSecondAttr,Integer> map = new HashMap<>();
        for(int i=0;i<seconds.length;i++){
            GeneralsEnum.SymbolsSecondAttr secondAttr = GeneralsEnum.SymbolsSecondAttr.get(seconds[i]);
            int code = secondAttr.getCode();
            Double d = 0d;
            if(code == 1){//武力增加
                d = secondAttr.getValue().doubleValue() * 6 * 5;
            }else if(code == 2){//武力加成
                d = totalForce * secondAttr.getRate() * 6;
            }else if(code == 3){//智力增加
                d = secondAttr.getValue().doubleValue() * 6 * 5;
            }else if(code == 4){//智力加成
                d = totalIntellect * secondAttr.getRate() * 6;
            }else if(code == 5){//兵力增加
                d = secondAttr.getValue().doubleValue() * 6 * 5;
            }else if(code == 6){//兵力加成
                d = totalTroops * secondAttr.getRate() * 6;
            }else if(code == 7){//吴国全属性
                d += secondAttr.getValue() * 3 * 6 * generalsWu.size();
            }else if(code == 8){//吴国全属性加成
                for(Generals generals : generalsWu){
                    d += generals.getMaxThreeDimensional().getForce() * secondAttr.getRate() * 6;
                    d += generals.getMaxThreeDimensional().getIntellect() * secondAttr.getRate() * 6;
                    d += generals.getMaxThreeDimensional().getTroops() * secondAttr.getRate() * 6;
                }
            }else if(code == 9){//蜀国全属性
                d += secondAttr.getValue() * 3 * 6 * generalsShu.size();
            }else if(code == 10){//蜀国全属性加成
                for(Generals generals : generalsShu){
                    d += generals.getMaxThreeDimensional().getForce() * secondAttr.getRate() * 6;
                    d += generals.getMaxThreeDimensional().getIntellect() * secondAttr.getRate() * 6;
                    d += generals.getMaxThreeDimensional().getTroops() * secondAttr.getRate() * 6;
                }
            }else if(code == 11){//魏国全属性
                d += secondAttr.getValue() * 3 * 6 * generalsWei.size();
            }else if(code == 12){//魏国全属性加成
                for(Generals generals : generalsWei){
                    d += generals.getMaxThreeDimensional().getForce() * secondAttr.getRate() * 6;
                    d += generals.getMaxThreeDimensional().getIntellect() * secondAttr.getRate() * 6;
                    d += generals.getMaxThreeDimensional().getTroops() * secondAttr.getRate() * 6;
                }
            }else if(code == 13){//群国全属性
                d += secondAttr.getValue() * 3 * 6 * generalsQun.size();
            }else if(code == 14){//群国全属性加成
                for(Generals generals : generalsQun){
                    d += generals.getMaxThreeDimensional().getForce() * secondAttr.getRate() * 6;
                    d += generals.getMaxThreeDimensional().getIntellect() * secondAttr.getRate() * 6;
                    d += generals.getMaxThreeDimensional().getTroops() * secondAttr.getRate() * 6;
                }
            }
            map.put(secondAttr,d.intValue());
        }
        return MapUtils.sortByValue(map);
    }

    // 兵符
    public static Map<String,Object> getSymbols(List<Generals> generalsList,List<AppointSymbols> appointSymbolsList) {
        ThreeDimensional totalThree = new ThreeDimensional();
        int totalForce = 0;
        int totalIntellect = 0;
        int totalTroops = 0;
        for (Generals generals : generalsList){
            ThreeDimensional three = generals.getMaxThreeDimensional();
            totalForce += three.getForce();
            totalIntellect += three.getIntellect();
            totalTroops += three.getTroops();
        }
        totalThree.setForce(totalForce);
        totalThree.setIntellect(totalIntellect);
        totalThree.setTroops(totalTroops);

        Symbols symbols1 = new Symbols();
        Symbols symbols2 = new Symbols();
        Symbols symbols3 = new Symbols();
        Symbols symbols4 = new Symbols();
        Symbols symbols5 = new Symbols();
        Symbols symbols6 = new Symbols();

        //1号位主属性
        symbols1.setNumber(GeneralsEnum.Symbols.number1.getCode());
        symbols1.setMainAttr(GeneralsEnum.SymbolsMainAttr.force.getCode());
        symbols1.setMainAttrName(GeneralsEnum.SymbolsMainAttr.force.getName());

        //3号位主属性
        symbols3.setNumber(GeneralsEnum.Symbols.number3.getCode());
        symbols3.setMainAttr(GeneralsEnum.SymbolsMainAttr.intellect.getCode());
        symbols3.setMainAttrName(GeneralsEnum.SymbolsMainAttr.intellect.getName());

        //5号位主属性
        symbols5.setNumber(GeneralsEnum.Symbols.number5.getCode());
        symbols5.setMainAttr(GeneralsEnum.SymbolsMainAttr.troops.getCode());
        symbols5.setMainAttrName(GeneralsEnum.SymbolsMainAttr.troops.getName());

        //计算2号位主属性
        int[] s2 = {4,7,10,13};
        GeneralsEnum.SymbolsMainAttr mainAttr2 = countSymbolsMainMax(totalThree,s2);
        symbols2.setNumber(GeneralsEnum.Symbols.number2.getCode());
        symbols2.setMainAttr(mainAttr2.getCode());
        symbols2.setMainAttrName(mainAttr2.getName());

        //计算4号位主属性
        int[] s4 = {5,8,11,14};
        GeneralsEnum.SymbolsMainAttr mainAttr4 = countSymbolsMainMax(totalThree,s4);
        symbols4.setNumber(GeneralsEnum.Symbols.number4.getCode());
        symbols4.setMainAttr(mainAttr4.getCode());
        symbols4.setMainAttrName(mainAttr4.getName());

        //计算6号位主属性
        int[] s6 = {6,9,12,15};
        GeneralsEnum.SymbolsMainAttr mainAttr6 = countSymbolsMainMax(totalThree,s6);
        symbols6.setNumber(GeneralsEnum.Symbols.number6.getCode());
        symbols6.setMainAttr(mainAttr6.getCode());
        symbols6.setMainAttrName(mainAttr6.getName());

        List<Generals> generalsAll = new ArrayList<>();//全部
        List<Generals> generalsWei = new ArrayList<>();//魏
        List<Generals> generalsShu = new ArrayList<>();//蜀
        List<Generals> generalsWu  = new ArrayList<>();//吴
        List<Generals> generalsQun = new ArrayList<>();//群
        List<Generals> generalsQiang = new ArrayList<>();//枪
        List<Generals> generalsGong = new ArrayList<>();//弓
        List<Generals> generalsQi = new ArrayList<>();//骑
        List<Generals> generalsNv = new ArrayList<>();//女
        for(Generals generals : generalsList){
            generalsAll.add(generals);
            if(generals.getArms().equals(GeneralsEnum.Arms.gun.getCode())){//枪
                generalsQiang.add(generals);
            }else if(generals.getArms().equals(GeneralsEnum.Arms.ride.getCode())){//骑
                generalsQi.add(generals);
            }else if(generals.getArms().equals(GeneralsEnum.Arms.arch.getCode())){//弓
                generalsGong.add(generals);
            }
            if(generals.getGender().equals(GeneralsEnum.Gender.gril.getCode())){
                generalsNv.add(generals);
            }
            if(generals.getCountry().equals(GeneralsEnum.Country.wei.getCode())){//魏
                generalsWei.add(generals);
            }else if(generals.getCountry().equals(GeneralsEnum.Country.shu.getCode())){//蜀
                generalsShu.add(generals);
            }else if(generals.getCountry().equals(GeneralsEnum.Country.wu.getCode())){//吴
                generalsWu.add(generals);
            }else if(generals.getCountry().equals(GeneralsEnum.Country.qun.getCode())){//群
                generalsQun.add(generals);
            }
        }

        //计算副属性排行
        int[] seconds = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        Map<GeneralsEnum.SymbolsSecondAttr,Integer> secondTopMap = countSymbolsSecondTop(totalThree,seconds,generalsAll,generalsWei,generalsShu,generalsWu,generalsQun);
        int limit = 0;
        for (Map.Entry<GeneralsEnum.SymbolsSecondAttr,Integer> entry : secondTopMap.entrySet()){
            GeneralsEnum.SymbolsSecondAttr key = entry.getKey();
            int code = key.getCode();
            String name = key.getName();
            // 设置副属性
            limit++;
            if(limit == 1){
                symbols1.setAttr1(code);symbols1.setAttrName1(name);
                symbols2.setAttr1(code);symbols2.setAttrName1(name);
                symbols3.setAttr1(code);symbols3.setAttrName1(name);
                symbols4.setAttr1(code);symbols4.setAttrName1(name);
                symbols5.setAttr1(code);symbols5.setAttrName1(name);
                symbols6.setAttr1(code);symbols6.setAttrName1(name);
            }else if(limit == 2){
                symbols1.setAttr2(code);symbols1.setAttrName2(name);
                symbols2.setAttr2(code);symbols2.setAttrName2(name);
                symbols3.setAttr2(code);symbols3.setAttrName2(name);
                symbols4.setAttr2(code);symbols4.setAttrName2(name);
                symbols5.setAttr2(code);symbols5.setAttrName2(name);
                symbols6.setAttr2(code);symbols6.setAttrName2(name);
            }else if(limit == 3){
                symbols1.setAttr3(code);symbols1.setAttrName3(name);
                symbols2.setAttr3(code);symbols2.setAttrName3(name);
                symbols3.setAttr3(code);symbols3.setAttrName3(name);
                symbols4.setAttr3(code);symbols4.setAttrName3(name);
                symbols5.setAttr3(code);symbols5.setAttrName3(name);
                symbols6.setAttr3(code);symbols6.setAttrName3(name);
            }else if(limit == 4){
                symbols1.setAttr4(code);symbols1.setAttrName4(name);
                symbols2.setAttr4(code);symbols2.setAttrName4(name);
                symbols3.setAttr4(code);symbols3.setAttrName4(name);
                symbols4.setAttr4(code);symbols4.setAttrName4(name);
                symbols5.setAttr4(code);symbols5.setAttrName4(name);
                symbols6.setAttr4(code);symbols6.setAttrName4(name);
            }else{
                break;
            }
        }

        Map<GeneralsEnum.SymbolsType,Integer> typeMap = new HashMap<>();
        for(GeneralsEnum.SymbolsType type : GeneralsEnum.SymbolsType.values()){
            int force = 0;
            int intellect = 0;
            int troops = 0;
            Integer code = type.getCode();
            boolean flag = true;
            /*for(AppointSymbols appointSymbols : appointSymbolsList){
                if(code.equals(appointSymbols.getCode())){
                    flag = false;
                }
            }*/
            if(flag){
                if(code.equals(GeneralsEnum.SymbolsType.cangLong.getCode())){//苍龙,蜀国全属性加10%
                    for (Generals generals : generalsShu){
                        Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                        force += d1.intValue();
                        Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                        intellect += d2.intValue();
                        Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                        troops += d3.intValue();
                    }
                }else if(code.equals(GeneralsEnum.SymbolsType.mengHu.getCode())){//猛虎,吴国全属性加10%
                    for (Generals generals : generalsWu){
                        Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                        force += d1.intValue();
                        Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                        intellect += d2.intValue();
                        Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                        troops += d3.intValue();
                    }
                }else if(code.equals(GeneralsEnum.SymbolsType.huoFeng.getCode())){//火凤,魏国全属性加10%
                    for (Generals generals : generalsWei){
                        Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                        force += d1.intValue();
                        Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                        intellect += d2.intValue();
                        Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                        troops += d3.intValue();
                    }
                }else if(code.equals(GeneralsEnum.SymbolsType.tianLang.getCode())){//天狼,群雄全属性加10%
                    for (Generals generals : generalsQun){
                        Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                        force += d1.intValue();
                        Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                        intellect += d2.intValue();
                        Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                        troops += d3.intValue();
                    }
                }else if(code.equals(GeneralsEnum.SymbolsType.xianGui.getCode())){//玄龟,枪兵全属性加10%
                    for (Generals generals : generalsQiang){
                        Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                        force += d1.intValue();
                        Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                        intellect += d2.intValue();
                        Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                        troops += d3.intValue();
                    }
                }else if(code.equals(GeneralsEnum.SymbolsType.xiangYing.getCode())){//翔鹰,弓兵全属性加10%
                    for (Generals generals : generalsGong){
                        Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                        force += d1.intValue();
                        Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                        intellect += d2.intValue();
                        Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                        troops += d3.intValue();
                    }
                }else if(code.equals(GeneralsEnum.SymbolsType.qiLin.getCode())){//麒麟,骑兵全属性加10%
                    for (Generals generals : generalsQi){
                        Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                        force += d1.intValue();
                        Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                        intellect += d2.intValue();
                        Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                        troops += d3.intValue();
                    }
                }else if(code.equals(GeneralsEnum.SymbolsType.qingLuan.getCode())){//青鸾,女性全属性加10%
                    for (Generals generals : generalsNv){
                        Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                        force += d1.intValue();
                        Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                        intellect += d2.intValue();
                        Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                        troops += d3.intValue();
                    }
                }else if(code.equals(GeneralsEnum.SymbolsType.baiZe.getCode())){//白泽,全体智力加24%
                    for (Generals generals : generalsAll){
                        Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                        intellect += d2.intValue();
                    }
                }else if(code.equals(GeneralsEnum.SymbolsType.hunDUN.getCode())){//混沌,全体全属性加8%
                    for (Generals generals : generalsAll){
                        Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                        force += d1.intValue();
                        Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                        intellect += d2.intValue();
                        Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                        troops += d3.intValue();
                    }
                }else if(code.equals(GeneralsEnum.SymbolsType.qiongQi.getCode())){//穷奇,全体武力加24%
                    for (Generals generals : generalsAll){
                        Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                        force += d1.intValue();
                    }
                }else if(code.equals(GeneralsEnum.SymbolsType.yaCi.getCode())){//睚眦,全体兵力加24%
                    for (Generals generals : generalsAll){
                        Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                        troops += d3.intValue();
                    }
                }else if(code.equals(GeneralsEnum.SymbolsType.píxiū.getCode())){//貔貅,骑兵全属性加12%
                    for (Generals generals : generalsQi){
                        Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                        force += d1.intValue();
                        Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                        intellect += d2.intValue();
                        Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                        troops += d3.intValue();
                    }
                }else if(code.equals(GeneralsEnum.SymbolsType.zhēng.getCode())){//狰,枪兵全属性加12%
                    for (Generals generals : generalsQiang){
                        Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                        force += d1.intValue();
                        Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                        intellect += d2.intValue();
                        Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                        troops += d3.intValue();
                    }
                }else if(code.equals(GeneralsEnum.SymbolsType.gǔdiāo.getCode())){//蛊雕,弓兵全属性加12%
                    for (Generals generals : generalsGong){
                        Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                        force += d1.intValue();
                        Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                        intellect += d2.intValue();
                        Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                        troops += d3.intValue();
                    }
                }
                typeMap.put(type,force+intellect+troops);
            }
        }

        List<Map.Entry<GeneralsEnum.SymbolsType, Integer>> list = new ArrayList<>(typeMap.entrySet());
        list.sort(new Comparator<Map.Entry<GeneralsEnum.SymbolsType, Integer>>() {
            // 降序排序
            public int compare(Map.Entry<GeneralsEnum.SymbolsType, Integer> o1, Map.Entry<GeneralsEnum.SymbolsType, Integer> o2) {
                return -o1.getValue().compareTo(o2.getValue());
            }
        });
        GeneralsEnum.SymbolsType top1 = list.get(0).getKey();
        GeneralsEnum.SymbolsType top2 = list.get(1).getKey();
        GeneralsEnum.SymbolsType top3 = list.get(2).getKey();

        symbols1.setType(top1.getCode());
        symbols1.setTypeName(top1.getName());
        symbols2.setType(top1.getCode());
        symbols2.setTypeName(top1.getName());

        symbols3.setType(top2.getCode());
        symbols3.setTypeName(top2.getName());
        symbols4.setType(top2.getCode());
        symbols4.setTypeName(top2.getName());

        symbols5.setType(top3.getCode());
        symbols5.setTypeName(top3.getName());
        symbols6.setType(top3.getCode());
        symbols6.setTypeName(top3.getName());

        List<SymbolsTop> symbolsTopList = new ArrayList<>();
        for(Map.Entry<GeneralsEnum.SymbolsType, Integer> map : list){
            symbolsTopList.add(new SymbolsTop(map.getKey().getName(),map.getValue()));
        }

        List<SymbolsTop> symbolsSecondList = new ArrayList<>();
        for(Map.Entry<GeneralsEnum.SymbolsSecondAttr, Integer> map : secondTopMap.entrySet()){
            symbolsSecondList.add(new SymbolsTop(map.getKey().getName(),map.getValue()));
        }

        Map<String,Object> map = new HashMap<>();
        List<Symbols> symbolsList = new ArrayList<>();
        symbolsList.add(symbols1);
        symbolsList.add(symbols2);
        symbolsList.add(symbols3);
        symbolsList.add(symbols4);
        symbolsList.add(symbols5);
        symbolsList.add(symbols6);
        map.put("symbolsList",symbolsList);
        map.put("symbolsTop",symbolsTopList);
        map.put("symbolsTopSecond",symbolsSecondList);
        return map;
    }

    private static ThreeDimensionals countGroupSymbols(List<Generals> generalsList,List<Integer> list,List<Generals> generalsAll,List<Generals> generalsWei,List<Generals> generalsShu,List<Generals> generalsWu,List<Generals> generalsQun){
        ThreeDimensionals three = new ThreeDimensionals();
        three.setForce(0d);
        three.setIntellect(0d);
        three.setTroops(0d);
        for(Integer t : list){
            for(GeneralsEnum.SymbolsSecondAttr secondAttr : GeneralsEnum.SymbolsSecondAttr.values()){
                if(t.equals(secondAttr.getCode())){
                    if(secondAttr.getCode() == 1){//武力增加
                        three.setForce(three.getForce() + secondAttr.getValue() * 5);
                    }else if(secondAttr.getCode() == 2){//武力加成
                        for(Generals generals :generalsList){
                            Double d = generals.getMaxThreeDimensional().getForce() * secondAttr.getRate();
                            three.setForce(three.getForce() + d);
                        }
                    }else if(secondAttr.getCode() == 3){//智力增加
                        three.setIntellect(three.getIntellect() + secondAttr.getValue() * 5);
                    }else if(secondAttr.getCode() == 4){//智力加成
                        for(Generals generals :generalsList){
                            Double d = generals.getMaxThreeDimensional().getIntellect() * secondAttr.getRate();
                            three.setIntellect(three.getIntellect() + d);
                        }
                    }else if(secondAttr.getCode() == 5){//兵力增加
                        three.setTroops(three.getTroops() + secondAttr.getValue() * 5);
                    }else if(secondAttr.getCode() == 6){//兵力加成
                        for(Generals generals :generalsList){
                            Double d = generals.getMaxThreeDimensional().getTroops() * secondAttr.getRate();
                            three.setTroops(three.getTroops() + d);
                        }
                    }else if(secondAttr.getCode() == 7){//吴国全属性
                        for(Generals generals : generalsWu){
                            three.setForce(three.getForce() + secondAttr.getValue());
                            three.setIntellect(three.getIntellect() + secondAttr.getValue());
                            three.setTroops(three.getTroops() + secondAttr.getValue());
                        }
                    }else if(secondAttr.getCode() == 8){//吴国全属性加成
                        for(Generals generals : generalsWu){
                            Double d1 = generals.getMaxThreeDimensional().getForce() * secondAttr.getRate();
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * secondAttr.getRate();
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * secondAttr.getRate();
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(secondAttr.getCode() == 9){//蜀国全属性
                        for(Generals generals : generalsShu){
                            three.setForce(three.getForce() + secondAttr.getValue());
                            three.setIntellect(three.getIntellect() + secondAttr.getValue());
                            three.setTroops(three.getTroops() + secondAttr.getValue());
                        }
                    }else if(secondAttr.getCode() == 10){//蜀国全属性加成
                        for(Generals generals : generalsShu){
                            Double d1 = generals.getMaxThreeDimensional().getForce() * secondAttr.getRate();
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * secondAttr.getRate();
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * secondAttr.getRate();
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(secondAttr.getCode() == 11){//魏国全属性
                        for(Generals generals : generalsWei){
                            three.setForce(three.getForce() + secondAttr.getValue());
                            three.setIntellect(three.getIntellect() + secondAttr.getValue());
                            three.setTroops(three.getTroops() + secondAttr.getValue());
                        }
                    }else if(secondAttr.getCode() == 12){//魏国全属性加成
                        for(Generals generals : generalsWei){
                            Double d1 = generals.getMaxThreeDimensional().getForce() * secondAttr.getRate();
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * secondAttr.getRate();
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * secondAttr.getRate();
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(secondAttr.getCode() == 13){//群国全属性
                        for(Generals generals : generalsQun){
                            three.setForce(three.getForce() + secondAttr.getValue());
                            three.setIntellect(three.getIntellect() + secondAttr.getValue());
                            three.setTroops(three.getTroops() + secondAttr.getValue());
                        }
                    }else if(secondAttr.getCode() == 14){//群国全属性加成
                        for(Generals generals : generalsQun){
                            Double d1 = generals.getMaxThreeDimensional().getForce() * secondAttr.getRate();
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * secondAttr.getRate();
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * secondAttr.getRate();
                            three.setTroops(three.getTroops() + d3);
                        }
                    }
                }
            }
        }
        three.setTotal(three.getForce()+three.getIntellect()+three.getTroops());
        return three;
    }

    // 战意
    public static void getWarpath(List<Generals> generalsList) {
        for(Generals generals : generalsList){
            generals.setWarpath(new Warpath());
        }

        for(Generals generals : generalsList) {
            countWarpath2(generals,generalsList);
        }

        Warpath warpath1 = generalsList.get(0).getWarpath();
        Warpath warpath2 = generalsList.get(1).getWarpath();
        Warpath warpath3 = generalsList.get(2).getWarpath();
        Warpath warpath4 = generalsList.get(3).getWarpath();
        Warpath warpath5 = generalsList.get(4).getWarpath();
        ThreeDimensional three1 = new ThreeDimensional(warpath1.getForce0().intValue(),warpath1.getIntellect0().intValue(),warpath1.getTroops0().intValue());
        ThreeDimensional three2 = new ThreeDimensional(warpath2.getForce0().intValue(),warpath2.getIntellect0().intValue(),warpath2.getTroops0().intValue());
        ThreeDimensional three3 = new ThreeDimensional(warpath3.getForce0().intValue(),warpath3.getIntellect0().intValue(),warpath3.getTroops0().intValue());
        ThreeDimensional three4 = new ThreeDimensional(warpath4.getForce0().intValue(),warpath4.getIntellect0().intValue(),warpath4.getTroops0().intValue());
        ThreeDimensional three5 = new ThreeDimensional(warpath5.getForce0().intValue(),warpath5.getIntellect0().intValue(),warpath5.getTroops0().intValue());

        generalsList.get(0).setWarpathThreeDimensional(three1);
        generalsList.get(1).setWarpathThreeDimensional(three2);
        generalsList.get(2).setWarpathThreeDimensional(three3);
        generalsList.get(3).setWarpathThreeDimensional(three4);
        generalsList.get(4).setWarpathThreeDimensional(three5);

    }

    //计算战意优化
    public static void countWarpath2(Generals generals1,List<Generals> allList){
        List<Warpath> warpathList1 = new ArrayList<>();
        List<Warpath> warpathList2 = new ArrayList<>();
        List<Warpath> warpathList3 = new ArrayList<>();
        List<Warpath> warpathList4 = new ArrayList<>();
        List<Warpath> warpathList5 = new ArrayList<>();
        List<Warpath> warpathList6 = new ArrayList<>();

        int index = 0;
        int total = 0;
        int code = 0;
        String name = "";
        for(GeneralsEnum.Warpath warpath : GeneralsEnum.Warpath.values()){
            Double t = 0d;
            for(Generals g : allList){
                if(warpath.getCode().equals(GeneralsEnum.Warpath.countryForce.getCode())){//同国家上阵武力加成
                    Warpath wp = new Warpath();
                    if(g.getCountry().equals(generals1.getCountry())){
                        Double d = g.getMaxThreeDimensional().getForce() * warpath.getRate();
                        wp.setForce0(d);
                        wp.setGroup(warpath.getCode());
                        wp.setGroupName(warpath.getName());
                        wp.setGeneralsCodes(g.getCodes());
                        t += d;
                    }
                    warpathList1.add(wp);
                }else if(warpath.getCode().equals(GeneralsEnum.Warpath.countryIntellect.getCode())){//同国家上阵智力加成
                    Warpath wp = new Warpath();
                    if(g.getCountry().equals(generals1.getCountry())){
                        Double d = g.getMaxThreeDimensional().getIntellect() * warpath.getRate();
                        wp.setIntellect0(d);
                        wp.setGroup(warpath.getCode());
                        wp.setGroupName(warpath.getName());
                        wp.setGeneralsCodes(g.getCodes());
                        t += d;
                    }
                    warpathList2.add(wp);
                }else if(warpath.getCode().equals(GeneralsEnum.Warpath.countryTroops.getCode())){//同国家上阵兵力加成
                    Warpath wp = new Warpath();
                    if(g.getCountry().equals(generals1.getCountry())){
                        Double d = g.getMaxThreeDimensional().getTroops() * warpath.getRate();
                        wp.setTroops0(d);
                        wp.setGroup(warpath.getCode());
                        wp.setGroupName(warpath.getName());
                        wp.setGeneralsCodes(g.getCodes());
                        t += d;
                    }
                    warpathList3.add(wp);
                }else if(warpath.getCode().equals(GeneralsEnum.Warpath.armsForce.getCode())){//同兵种上阵武力加成
                    Warpath wp = new Warpath();
                    if(g.getArms().equals(generals1.getArms())){
                        Double d = g.getMaxThreeDimensional().getForce() * warpath.getRate();
                        wp.setForce0(d);
                        wp.setGroup(warpath.getCode());
                        wp.setGroupName(warpath.getName());
                        wp.setGeneralsCodes(g.getCodes());
                        t += d;
                    }
                    warpathList4.add(wp);
                }else if(warpath.getCode().equals(GeneralsEnum.Warpath.armsIntellect.getCode())){//同兵种上阵智力加成
                    Warpath wp = new Warpath();
                    if(g.getArms().equals(generals1.getArms())){
                        Double d = g.getMaxThreeDimensional().getIntellect() * warpath.getRate();
                        wp.setIntellect0(d);
                        wp.setGroup(warpath.getCode());
                        wp.setGroupName(warpath.getName());
                        wp.setGeneralsCodes(g.getCodes());
                        t += d;
                    }
                    warpathList5.add(wp);
                }else if(warpath.getCode().equals(GeneralsEnum.Warpath.armsTroops.getCode())){//同兵种上阵兵力加成
                    Warpath wp = new Warpath();
                    if(g.getArms().equals(generals1.getArms())){
                        Double d = g.getMaxThreeDimensional().getTroops() * warpath.getRate();
                        wp.setTroops0(d);
                        wp.setGroup(warpath.getCode());
                        wp.setGroupName(warpath.getName());
                        wp.setGeneralsCodes(g.getCodes());
                        t += d;
                    }
                    warpathList6.add(wp);
                }

            }

            if(t>total){
                total = t.intValue();
                index = warpath.getCode()-1;
                code = warpath.getCode();
                name = warpath.getName();
            }
        }

        generals1.getWarpath().setGroup(code);
        generals1.getWarpath().setGroupName(name);

        List<Warpath> finalWarpathList = new ArrayList<>();
        if(index == 0){
            finalWarpathList.addAll(warpathList1);
        }else if(index == 1){
            finalWarpathList.addAll(warpathList2);
        }else if(index == 2){
            finalWarpathList.addAll(warpathList3);
        }else if(index == 3){
            finalWarpathList.addAll(warpathList4);
        }else if(index == 4){
            finalWarpathList.addAll(warpathList5);
        }else if(index == 5){
            finalWarpathList.addAll(warpathList6);
        }

        for(Warpath warpath : finalWarpathList){
            if(warpath.getGeneralsCodes() != null){
                for(Generals generals : allList){
                    if(checkCode(warpath.getGeneralsCodes(),generals.getCodes())){
                        generals.getWarpath().setForce0(generals.getWarpath().getForce0() + warpath.getForce0());
                        generals.getWarpath().setIntellect0(generals.getWarpath().getIntellect0() + warpath.getIntellect0());
                        generals.getWarpath().setTroops0(generals.getWarpath().getTroops0() + warpath.getTroops0());
                        break;
                    }
                }
            }
        }
    }

    //战阵
    public static ThreeDimensional getBattleArray(Generals generals) {
        ThreeDimensional three = new ThreeDimensional();
        ThreeDimensional maxThree = generals.getMaxThreeDimensional();
        Double rate = 0.1;
        Double force = maxThree.getForce() * rate;
        Double intellect = maxThree.getIntellect() * rate;
        Double troops = maxThree.getTroops() * rate;
        three.setForce(force.intValue());
        three.setIntellect(intellect.intValue());
        three.setTroops(troops.intValue());
        three.setTotalZl((force.intValue()+intellect.intValue()+troops.intValue())*2);
        generals.setBattleArrayThreeDimensional(three);
        setTotal(generals,three);
        return three;
    }

    // 命格
    public static ThreeDimensional getDestiny(Generals generals) {
        ThreeDimensional three = new ThreeDimensional();
        Destiny destiny = generals.getDestiny();
        int force = destiny.getForce();
        int intellect = destiny.getIntellect();
        int troops = destiny.getTroops();
        three.setForce(force);
        three.setIntellect(intellect);
        three.setTroops(troops);
        three.setTotalZl((force+intellect+troops)*2);
        for (GeneralsEnum.Destiny type : GeneralsEnum.Destiny.values()){
            //
            if(type.getCode().equals(destiny.getDisobey())){
                destiny.setDestinyEffect1(type.getEffect1());
                destiny.setDestinyEffect2(type.getEffect2());
                destiny.setDestinyEffect3(type.getEffect3());
                destiny.setDestinyEffect4(type.getEffect4());
                destiny.setMaxLevel(type.getLevel5());
                break;
            }
        }
        generals.setDestinyThreeDimensional(three);
        setTotal(generals,three);
        return three;
    }

    /**
     * 幻化
     * @param generals
     * @return
     */
    public static ThreeDimensional getSkin(Generals generals) {
        ThreeDimensional three = new ThreeDimensional();
        int force = 0;
        int intellect = 0;
        int troops = 0;
        if(generals.getSkinCode()!=null){
            for(GeneralsEnum.Skin skin : GeneralsEnum.Skin.values()){
                if(skin.getCode().equals(generals.getSkinCode())){
                    force = skin.getForce();
                    intellect = skin.getIntellect();
                    troops = skin.getTroops();
                    three.setRemark(generals.getSkinName());
                    break;
                }
            }
        }
        three.setForce(force);
        three.setIntellect(intellect);
        three.setTroops(troops);
        three.setTotalZl((force+intellect+troops)*2);
        generals.setSkinThreeDimensional(three);
        setTotal(generals,three);
        return three;
    }

    /**
     * 阵法
     * @param generals
     * @return
     */
    public static ThreeDimensional getBattleArrayWay(Generals generals) {
        ThreeDimensional three = new ThreeDimensional();
        GeneralsEnum.BattleArrayWay battleArrayWay = GeneralsEnum.BattleArrayWay.long_fei;
        int force = battleArrayWay.getMaxForce();
        int intellect = battleArrayWay.getMaxIntellect();
        int troops = battleArrayWay.getMaxTroops();
        three.setForce(force);
        three.setIntellect(intellect);
        three.setTroops(troops);
        three.setTotalZl((force+intellect+troops)*2);
        generals.setBattleArrayWayThreeDimensional(three);
        setTotal(generals,three);
        return three;
    }

    /**
     * 其他战力加成
     * @param generals
     * @return
     */
    public static void getOther(Generals generals) {
        //命格
        Destiny destiny = generals.getDestiny();
        //阵法五被动战力
        int battleArrayWaySword = 1920;
        //命格被动战力
        int destinySword = destiny.getDestinyEffect1() + destiny.getDestinyEffect2()+ destiny.getDestinyEffect3()+ destiny.getDestinyEffect4() + destiny.getMaxLevel();
        //战器三被动战力
        int warDeviceSword = 458 + 458 + 1220;
        generals.setTotalSword(generals.getTotalSword()+battleArrayWaySword+destinySword+warDeviceSword);
    }

    // 获取武将总战力
    // 武将战力 =（武力+智力+兵力）*2+ 命格被动 + 战器三被动
    /*public static Integer getTotalSword(Generals generals) {
        Integer force = 0;
        Integer intellect = 0;
        Integer troops = 0;

        //120满级三维
        ThreeDimensional maxThreeDimensional = generals.getMaxThreeDimensional();
        force += maxThreeDimensional.getForce();
        intellect += maxThreeDimensional.getIntellect();
        troops += maxThreeDimensional.getTroops();

        //科技三维
        ThreeDimensional scienceThreeDimensional = generals.getScienceThreeDimensional();
        force += scienceThreeDimensional.getForce();
        intellect += scienceThreeDimensional.getIntellect();
        troops += scienceThreeDimensional.getTroops();

        //随从三维
        ThreeDimensional entourageThreeDimensional = generals.getEntourageThreeDimensional();
        force += entourageThreeDimensional.getForce();
        intellect += entourageThreeDimensional.getIntellect();
        troops += entourageThreeDimensional.getTroops();

        //圣石三维
        ThreeDimensional holyStoneThreeDimensional = generals.getHolyStoneThreeDimensional();
        force += holyStoneThreeDimensional.getForce();
        intellect += holyStoneThreeDimensional.getIntellect();
        troops += holyStoneThreeDimensional.getTroops();

        //战器三维
        ThreeDimensional warDeviceThreeDimensional = generals.getWarDeviceThreeDimensional();
        force += warDeviceThreeDimensional.getForce();
        intellect += warDeviceThreeDimensional.getIntellect();
        troops += warDeviceThreeDimensional.getTroops();

        //特殊战器三维
        //ThreeDimensional warDeviceThreeDimensional2 = generals.getWarDevice2ThreeDimensional();
        //int f = warDeviceThreeDimensional2.getForce();
        //int i = warDeviceThreeDimensional2.getIntellect();
        //int t = warDeviceThreeDimensional2.getTroops();

        //兵种兵书三维
        ThreeDimensional armsBookThreeDimensional = generals.getArmsBookThreeDimensional();
        force += armsBookThreeDimensional.getForce();
        intellect += armsBookThreeDimensional.getIntellect();
        troops += armsBookThreeDimensional.getTroops();

        //将魂三维
        ThreeDimensional willSoulThreeDimensional = generals.getWillSoulThreeDimensional();
        force += willSoulThreeDimensional.getForce();
        intellect += willSoulThreeDimensional.getIntellect();
        troops += willSoulThreeDimensional.getTroops();

        //兵符三维
        ThreeDimensionals symbolsThreeDimensional = generals.getSymbolsThreeDimensionals();
        force += symbolsThreeDimensional.getForce().intValue();
        intellect += symbolsThreeDimensional.getIntellect().intValue();
        troops += symbolsThreeDimensional.getTroops().intValue();

        //战阵三维
        ThreeDimensional battleArrayThreeDimensional = generals.getBattleArrayThreeDimensional();
        force += battleArrayThreeDimensional.getForce();
        intellect += battleArrayThreeDimensional.getIntellect();
        troops += battleArrayThreeDimensional.getTroops();

        //战意三维
        ThreeDimensional warpathThreeDimensional = generals.getWarpathThreeDimensional();
        force += warpathThreeDimensional.getForce();
        intellect += warpathThreeDimensional.getIntellect();
        troops += warpathThreeDimensional.getTroops();
//        Warpath warpath = generals.getWarpath();
//        force += warpath.getForce();
//        intellect += warpath.getIntellect();
//        troops += warpath.getTroops();
//        System.out.println(generals.getName()+" 战意："+warpathThreeDimensional);

        //命格被动战力
        Destiny destiny = generals.getDestiny();
        force += destiny.getForce();
        intellect += destiny.getIntellect();
        troops += destiny.getTroops();

        //幻化三维
        ThreeDimensional skinThreeDimensional = generals.getSkinThreeDimensional();
        force += skinThreeDimensional.getForce();
        intellect += skinThreeDimensional.getIntellect();
        troops += skinThreeDimensional.getTroops();

        //命格被动战力
        Integer destinySword = destiny.getDestinyEffect1() + destiny.getDestinyEffect2()+ destiny.getDestinyEffect3()+ destiny.getDestinyEffect4() + destiny.getMaxLevel();

        //战器三被动战力
        Integer warDeviceSword = 458 + 458 + 1220;

        //武将战力 =（武力+智力+兵力）*2+ 命格被动 + 战器三被动
        Integer total = force + intellect + troops;
        Integer totalSword = (total) * 2 + destinySword + warDeviceSword;
        generals.setTotalSword(totalSword);
        generals.setTotalForce(force);
        generals.setTotalIntellect(intellect);
        generals.setTotalTroops(troops);
        return totalSword;
    }*/


    // 获取武将总战力(特殊战器)
    // 武将战力 =（武力+智力+兵力）*2+ 命格被动 + 战器三被动
    public static Integer getTotalSword2(Generals generals) {
        int force = generals.getTotalForce();
        int intellect = generals.getTotalIntellect();
        int troops = generals.getTotalTroops();

        //兵符三维
        ThreeDimensionals symbolsThreeDimensional = generals.getSymbolsThreeDimensionals();
        force += symbolsThreeDimensional.getForce().intValue();
        intellect += symbolsThreeDimensional.getIntellect().intValue();
        troops += symbolsThreeDimensional.getTroops().intValue();

        //战意三维
        ThreeDimensional warpathThreeDimensional = generals.getWarpathThreeDimensional();
        force += warpathThreeDimensional.getForce();
        intellect += warpathThreeDimensional.getIntellect();
        troops += warpathThreeDimensional.getTroops();

        //战器三维
        /*Device device = generals.getDevice2();
        ThreeDimensional device2Three = generals.getWarDevice2ThreeDimensional();
        if(device.getResonance()){
            force += device2Three.getForce();
            intellect += device2Three.getIntellect();
            troops += device2Three.getTroops();
        }else{
            force += device2Three.getForce();
            intellect += device2Three.getIntellect();
            troops += device2Three.getTroops();
        }
*/
        //命格
        Destiny destiny = generals.getDestiny();
        //阵法五被动战力
        int battleArrayWaySword = 1920;
        //命格被动战力
        int destinySword = destiny.getDestinyEffect1() + destiny.getDestinyEffect2()+ destiny.getDestinyEffect3()+ destiny.getDestinyEffect4() + destiny.getMaxLevel();
        //战器三被动战力
        int warDeviceSword = 458 + 458 + 1220;
        //器灵天赋属性战力
        int warDeviceTfAndCz = 660 * 3;
        //器灵成长属性培养战力
        int warDeviceCz = 60*6*3;
        //器灵套装战力
        int warDeviceQiLing = 1920;

        //武将战力 =（武力+智力+兵力）*2+ 命格被动 + 战器三被动
        int total = force + intellect + troops;
        int totalSword = (total) * 2 + battleArrayWaySword + destinySword + warDeviceSword + warDeviceQiLing + warDeviceTfAndCz + warDeviceCz;
        generals.setTotalSword2(totalSword);
        generals.setTotalForce2(force);
        generals.setTotalIntellect2(intellect);
        generals.setTotalTroops2(troops);
        return totalSword;
    }

    //总战力 = 武将1战力 + 武将2战力 + 武将3战力 + 武将4战力 + 武将5战力 + 工坊战力（10152）
    /*public static Integer getAllTotalSword(List<Generals> generalsList) {
        // 76710  75072   75060  72510  71394
        Integer total = 10152;
        for (Generals generals : generalsList){
            total += getTotalSword(generals);
        }
        return total;
    }*/

    // 特殊战器
    //总战力 = 武将1战力 + 武将2战力 + 武将3战力 + 武将4战力 + 武将5战力 + 工坊战力（10152）
    public static Integer getAllTotalSword2(List<Generals> generalsList) {
        // 76710  75072   75060  72510  71394
        Integer total = 10152;
        for (Generals generals : generalsList){
            total += getTotalSword2(generals);
        }
        return total;
    }

    //总战力 = 武将1战力 + 武将2战力 + 武将3战力 + 武将4战力 + 武将5战力 + 工坊战力（10152）
    /*public static Integer getAllTotalSword3(List<Generals> generalsList,List<AppointExcludeGenerals> excludeGeneralsList) {
        Integer total = 10152;
        //判断上阵武将有没有danList
        for (Generals generals : generalsList){
            for (AppointExcludeGenerals exclude : excludeGeneralsList){
                if(exclude.getName().equalsIgnoreCase(generals.getName())){
                    exclude.setCurrentSize(1);
                }
            }
        }

        for (Generals generals : generalsList){
            total += getTotalSword3(generals,excludeGeneralsList);
        }
        return total;
    }*/

    //总战力 = 武将1战力 + 武将2战力 + 武将3战力 + 武将4战力 + 武将5战力 + 工坊战力（10152）
    public static Integer getAllTotalSword4(List<Generals> generalsList,List<AppointExcludeGenerals> excludeGeneralsList) {
        Integer total = 10152;
        //判断上阵武将有没有danList
        for (Generals generals : generalsList){
            for (AppointExcludeGenerals exclude : excludeGeneralsList){
                if(exclude.getName().equalsIgnoreCase(generals.getName())){
                    exclude.setCurrentSize(1);
                }
            }
        }

        for (Generals generals : generalsList){
            total += getTotalSword4(generals,excludeGeneralsList);
        }
        return total;
    }


    //计算固定战意
    private static List<Warpath> countWarpath(List<Generals> generalsList){
        List<Generals> generalsQiang = null;//枪
        List<Generals> generalsQi    = null;//骑
        List<Generals> generalsGong  = null;//弓

        List<Generals> generalsWei = null;//魏
        List<Generals> generalsShu = null;//蜀
        List<Generals> generalsWu  = null;//吴
        List<Generals> generalsQun = null;//群

        for(Generals generals : generalsList){
            //Generals generals = new Generals();
            //BeanUtils.copyProperties(generalss,generals);
            if(generals.getArms().equals(GeneralsEnum.Arms.gun.getCode())){//枪
                if(generalsQiang == null){
                    generalsQiang = new ArrayList<>();
                }
                generalsQiang.add(generals);
            }else if(generals.getArms().equals(GeneralsEnum.Arms.ride.getCode())){//骑
                if(generalsQi == null){
                    generalsQi = new ArrayList<>();
                }
                generalsQi.add(generals);
            }else if(generals.getArms().equals(GeneralsEnum.Arms.arch.getCode())){//弓
                if(generalsGong == null){
                    generalsGong = new ArrayList<>();
                }
                generalsGong.add(generals);
            }
            if(generals.getCountry().equals(GeneralsEnum.Country.wei.getCode())){//魏
                if(generalsWei == null){
                    generalsWei = new ArrayList<>();
                }
                generalsWei.add(generals);
            }else if(generals.getCountry().equals(GeneralsEnum.Country.shu.getCode())){//蜀
                if(generalsShu == null){
                    generalsShu = new ArrayList<>();
                }
                generalsShu.add(generals);
            }else if(generals.getCountry().equals(GeneralsEnum.Country.wu.getCode())){//吴
                if(generalsWu == null){
                    generalsWu = new ArrayList<>();
                }
                generalsWu.add(generals);
            }else if(generals.getCountry().equals(GeneralsEnum.Country.qun.getCode())){//群
                if(generalsQun == null){
                    generalsQun = new ArrayList<>();
                }
                generalsQun.add(generals);
            }
        }

        Generals generals1 = generalsList.get(0);
        Generals copy1 = new Generals();
        BeanUtils.copyProperties(generals1,copy1);
        countGroupWarpath(copy1,generalsQiang,generalsQi,generalsGong,generalsWei,generalsShu,generalsWu,generalsQun);

        Generals generals2 = generalsList.get(1);
        Generals copy2 = new Generals();
        BeanUtils.copyProperties(generals2,copy2);
        countGroupWarpath(copy2,generalsQiang,generalsQi,generalsGong,generalsWei,generalsShu,generalsWu,generalsQun);

        Generals generals3 = generalsList.get(2);
        Generals copy3 = new Generals();
        BeanUtils.copyProperties(generals3,copy3);
        countGroupWarpath(copy3,generalsQiang,generalsQi,generalsGong,generalsWei,generalsShu,generalsWu,generalsQun);

        Generals generals4 = generalsList.get(3);
        Generals copy4 = new Generals();
        BeanUtils.copyProperties(generals4,copy4);
        countGroupWarpath(copy4,generalsQiang,generalsQi,generalsGong,generalsWei,generalsShu,generalsWu,generalsQun);

        Generals generals5 = generalsList.get(4);
        Generals copy5 = new Generals();
        BeanUtils.copyProperties(generals5,copy5);
        countGroupWarpath(copy5,generalsQiang,generalsQi,generalsGong,generalsWei,generalsShu,generalsWu,generalsQun);

        List<Warpath> warpathList = new ArrayList<>();
        warpathList.add(copy1.getWarpath());
        warpathList.add(copy2.getWarpath());
        warpathList.add(copy3.getWarpath());
        warpathList.add(copy4.getWarpath());
        warpathList.add(copy5.getWarpath());
        return warpathList;
    }

    private static void countGroupWarpath(Generals generals1,
                              List<Generals> generalsQiang,List<Generals> generalsQi,List<Generals> generalsGong,
                              List<Generals> generalsWei,List<Generals> generalsShu,List<Generals> generalsWu,List<Generals> generalsQun
                              ){
        if(generals1.getWarpath().getGroup().equals(GeneralsEnum.Warpath.countryForce.getCode())){//同国家武力
            if(generals1.getCountry().equals(GeneralsEnum.Country.wei.getCode())){//魏
                for(Generals generals : generalsWei){
                    Double d = generals.getMaxThreeDimensional().getForce() * GeneralsEnum.Warpath.countryForce.getRate();
                    generals.getWarpath().setForce(generals.getWarpath().getForce()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.countryForce.getName());
                }
            }else if(generals1.getCountry().equals(GeneralsEnum.Country.shu.getCode())){//蜀
                for(Generals generals : generalsShu){
                    Double d = generals.getMaxThreeDimensional().getForce() * GeneralsEnum.Warpath.countryForce.getRate();
                    generals.getWarpath().setForce(generals.getWarpath().getForce()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.countryForce.getName());
                }
            }else if(generals1.getCountry().equals(GeneralsEnum.Country.wu.getCode())){//吴
                for(Generals generals : generalsWu){
                    Double d = generals.getMaxThreeDimensional().getForce() * GeneralsEnum.Warpath.countryForce.getRate();
                    generals.getWarpath().setForce(generals.getWarpath().getForce()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.countryForce.getName());
                }
            }else if(generals1.getCountry().equals(GeneralsEnum.Country.qun.getCode())){//群
                for(Generals generals : generalsQun){
                    Double d = generals.getMaxThreeDimensional().getForce() * GeneralsEnum.Warpath.countryForce.getRate();
                    generals.getWarpath().setForce(generals.getWarpath().getForce()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.countryForce.getName());
                }
            }
        }else if(generals1.getWarpath().getGroup().equals(GeneralsEnum.Warpath.countryIntellect.getCode())){//同国家智力
            if(generals1.getCountry().equals(GeneralsEnum.Country.wei.getCode())){//魏
                for(Generals generals : generalsWei){
                    Double d = generals.getMaxThreeDimensional().getIntellect() * GeneralsEnum.Warpath.countryIntellect.getRate();
                    generals.getWarpath().setIntellect(generals.getWarpath().getIntellect()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.countryIntellect.getName());
                }
            }else if(generals1.getCountry().equals(GeneralsEnum.Country.shu.getCode())){//蜀
                for(Generals generals : generalsShu){
                    Double d = generals.getMaxThreeDimensional().getIntellect() * GeneralsEnum.Warpath.countryIntellect.getRate();
                    generals.getWarpath().setIntellect(generals.getWarpath().getIntellect()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.countryIntellect.getName());
                }
            }else if(generals1.getCountry().equals(GeneralsEnum.Country.wu.getCode())){//吴
                for(Generals generals : generalsWu){
                    Double d = generals.getMaxThreeDimensional().getIntellect() * GeneralsEnum.Warpath.countryIntellect.getRate();
                    generals.getWarpath().setIntellect(generals.getWarpath().getIntellect()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.countryIntellect.getName());
                }
            }else if(generals1.getCountry().equals(GeneralsEnum.Country.qun.getCode())){//群
                for(Generals generals : generalsQun){
                    Double d = generals.getMaxThreeDimensional().getIntellect() * GeneralsEnum.Warpath.countryIntellect.getRate();
                    generals.getWarpath().setIntellect(generals.getWarpath().getIntellect()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.countryIntellect.getName());
                }
            }
        }else if(generals1.getWarpath().getGroup().equals(GeneralsEnum.Warpath.countryTroops.getCode())){//同国家兵力
            if(generals1.getCountry().equals(GeneralsEnum.Country.wei.getCode())){//魏
                for(Generals generals : generalsWei){
                    Double d = generals.getMaxThreeDimensional().getTroops() * GeneralsEnum.Warpath.countryTroops.getRate();
                    generals.getWarpath().setTroops(generals.getWarpath().getTroops()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.countryTroops.getName());
                }
            }else if(generals1.getCountry().equals(GeneralsEnum.Country.shu.getCode())){//蜀
                for(Generals generals : generalsShu){
                    Double d = generals.getMaxThreeDimensional().getTroops() * GeneralsEnum.Warpath.countryTroops.getRate();
                    generals.getWarpath().setTroops(generals.getWarpath().getTroops()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.countryTroops.getName());
                }
            }else if(generals1.getCountry().equals(GeneralsEnum.Country.wu.getCode())){//吴
                for(Generals generals : generalsWu){
                    Double d = generals.getMaxThreeDimensional().getTroops() * GeneralsEnum.Warpath.countryTroops.getRate();
                    generals.getWarpath().setTroops(generals.getWarpath().getTroops()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.countryTroops.getName());
                }
            }else if(generals1.getCountry().equals(GeneralsEnum.Country.qun.getCode())){//群
                for(Generals generals : generalsQun){
                    Double d = generals.getMaxThreeDimensional().getTroops() * GeneralsEnum.Warpath.countryTroops.getRate();
                    generals.getWarpath().setTroops(generals.getWarpath().getTroops()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.countryTroops.getName());
                }
            }
        }else if(generals1.getWarpath().getGroup().equals(GeneralsEnum.Warpath.armsForce.getCode())){//同兵种武力
            if(generals1.getArms().equals(GeneralsEnum.Arms.gun.getCode())){//枪
                for(Generals generals : generalsQiang){
                    Double d = generals.getMaxThreeDimensional().getForce() * GeneralsEnum.Warpath.armsForce.getRate();
                    generals.getWarpath().setForce(generals.getWarpath().getForce()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.armsForce.getName());
                }
            }else if(generals1.getArms().equals(GeneralsEnum.Arms.ride.getCode())){//骑
                for(Generals generals : generalsQi){
                    Double d = generals.getMaxThreeDimensional().getForce() * GeneralsEnum.Warpath.armsForce.getRate();
                    generals.getWarpath().setForce(generals.getWarpath().getForce()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.armsForce.getName());
                }
            }else if(generals1.getArms().equals(GeneralsEnum.Arms.arch.getCode())){//弓
                for(Generals generals : generalsGong){
                    Double d = generals.getMaxThreeDimensional().getForce() * GeneralsEnum.Warpath.armsForce.getRate();
                    generals.getWarpath().setForce(generals.getWarpath().getForce()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.armsForce.getName());
                }
            }
        }else if(generals1.getWarpath().getGroup().equals(GeneralsEnum.Warpath.armsIntellect.getCode())){//同兵种智力
            if(generals1.getArms().equals(GeneralsEnum.Arms.gun.getCode())){//枪
                for(Generals generals : generalsQiang){
                    Double d = generals.getMaxThreeDimensional().getIntellect() * GeneralsEnum.Warpath.armsIntellect.getRate();
                    generals.getWarpath().setIntellect(generals.getWarpath().getIntellect()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.armsIntellect.getName());
                }
            }else if(generals1.getArms().equals(GeneralsEnum.Arms.ride.getCode())){//骑
                for(Generals generals : generalsQi){
                    Double d = generals.getMaxThreeDimensional().getIntellect() * GeneralsEnum.Warpath.armsIntellect.getRate();
                    generals.getWarpath().setIntellect(generals.getWarpath().getIntellect()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.armsIntellect.getName());
                }
            }else if(generals1.getArms().equals(GeneralsEnum.Arms.arch.getCode())){//弓
                for(Generals generals : generalsGong){
                    Double d = generals.getMaxThreeDimensional().getIntellect() * GeneralsEnum.Warpath.armsIntellect.getRate();
                    generals.getWarpath().setIntellect(generals.getWarpath().getIntellect()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.armsIntellect.getName());
                }
            }
        }else if(generals1.getWarpath().getGroup().equals(GeneralsEnum.Warpath.armsTroops.getCode())){//同兵种兵力
            if(generals1.getArms().equals(GeneralsEnum.Arms.gun.getCode())){//枪
                for(Generals generals : generalsQiang){
                    Double d = generals.getMaxThreeDimensional().getTroops() * GeneralsEnum.Warpath.armsTroops.getRate();
                    generals.getWarpath().setTroops(generals.getWarpath().getTroops()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.armsTroops.getName());
                }
            }else if(generals1.getArms().equals(GeneralsEnum.Arms.ride.getCode())){//骑
                for(Generals generals : generalsQi){
                    Double d = generals.getMaxThreeDimensional().getTroops() * GeneralsEnum.Warpath.armsTroops.getRate();
                    generals.getWarpath().setTroops(generals.getWarpath().getTroops()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.armsTroops.getName());
                }
            }else if(generals1.getArms().equals(GeneralsEnum.Arms.arch.getCode())){//弓
                for(Generals generals : generalsGong){
                    Double d = generals.getMaxThreeDimensional().getTroops() * GeneralsEnum.Warpath.armsTroops.getRate();
                    generals.getWarpath().setTroops(generals.getWarpath().getTroops()+ d.intValue());
                    generals.getWarpath().setGroupName(GeneralsEnum.Warpath.armsTroops.getName());
                }
            }
        }
    }

    //计算武将兵符加成
    public static ThreeDimensional countSymbols(List<Generals> generalsList, List<Symbols> symbolsList) {
        List<Generals> generalsAll = new ArrayList<>();//全部
        List<Generals> generalsWei = new ArrayList<>();//魏
        List<Generals> generalsShu = new ArrayList<>();//蜀
        List<Generals> generalsWu  = new ArrayList<>();//吴
        List<Generals> generalsQun = new ArrayList<>();//群
        List<Generals> generalsQiang = new ArrayList<>();//枪
        List<Generals> generalsGong = new ArrayList<>();//弓
        List<Generals> generalsQi = new ArrayList<>();//骑
        List<Generals> generalsNv = new ArrayList<>();//女
        for(Generals generals : generalsList){
            generalsAll.add(generals);
            if(generals.getArms().equals(GeneralsEnum.Arms.gun.getCode())){//枪
                generalsQiang.add(generals);
            }else if(generals.getArms().equals(GeneralsEnum.Arms.ride.getCode())){//骑
                generalsQi.add(generals);
            }else if(generals.getArms().equals(GeneralsEnum.Arms.arch.getCode())){//弓
                generalsGong.add(generals);
            }
            if(generals.getGender().equals(GeneralsEnum.Gender.gril.getCode())){
                generalsNv.add(generals);
            }
            if(generals.getCountry().equals(GeneralsEnum.Country.wei.getCode())){//魏
                generalsWei.add(generals);
            }else if(generals.getCountry().equals(GeneralsEnum.Country.shu.getCode())){//蜀
                generalsShu.add(generals);
            }else if(generals.getCountry().equals(GeneralsEnum.Country.wu.getCode())){//吴
                generalsWu.add(generals);
            }else if(generals.getCountry().equals(GeneralsEnum.Country.qun.getCode())){//群
                generalsQun.add(generals);
            }
        }

        //初始化三维
        ThreeDimensionals symbolsThree1 = new ThreeDimensionals();
        symbolsThree1.setForce(0d);
        symbolsThree1.setIntellect(0d);
        symbolsThree1.setTroops(0d);

        ThreeDimensionals symbolsThree2 = new ThreeDimensionals();
        symbolsThree2.setForce(0d);
        symbolsThree2.setIntellect(0d);
        symbolsThree2.setTroops(0d);

        ThreeDimensionals symbolsThree3 = new ThreeDimensionals();
        symbolsThree3.setForce(0d);
        symbolsThree3.setIntellect(0d);
        symbolsThree3.setTroops(0d);

        ThreeDimensionals symbolsThree4 = new ThreeDimensionals();
        symbolsThree4.setForce(0d);
        symbolsThree4.setIntellect(0d);
        symbolsThree4.setTroops(0d);

        ThreeDimensionals symbolsThree5 = new ThreeDimensionals();
        symbolsThree5.setForce(0d);
        symbolsThree5.setIntellect(0d);
        symbolsThree5.setTroops(0d);

        //武将
        Generals generals1 = generalsList.get(0);
        Generals generals2 = generalsList.get(1);
        Generals generals3 = generalsList.get(2);
        Generals generals4 = generalsList.get(3);
        Generals generals5 = generalsList.get(4);

        //兵符
        Symbols symbols1 = symbolsList.get(0);
        Symbols symbols2 = symbolsList.get(1);
        Symbols symbols3 = symbolsList.get(2);
        Symbols symbols4 = symbolsList.get(3);
        Symbols symbols5 = symbolsList.get(4);
        Symbols symbols6 = symbolsList.get(5);

        //设置兵符三维
        generals1.setSymbolsThreeDimensionals(symbolsThree1);
        generals2.setSymbolsThreeDimensionals(symbolsThree2);
        generals3.setSymbolsThreeDimensionals(symbolsThree3);
        generals4.setSymbolsThreeDimensionals(symbolsThree4);
        generals5.setSymbolsThreeDimensionals(symbolsThree5);



        /**
         * 主属性
         */
        //1号位主属性
        for(GeneralsEnum.SymbolsMainAttr mainAttr : GeneralsEnum.SymbolsMainAttr.values()){
            if(mainAttr.getCode().equals(symbols1.getMainAttr())){
                symbolsThree1.setForce(symbolsThree1.getForce() + mainAttr.getValue());
                symbolsThree2.setForce(symbolsThree2.getForce() + mainAttr.getValue());
                symbolsThree3.setForce(symbolsThree3.getForce() + mainAttr.getValue());
                symbolsThree4.setForce(symbolsThree4.getForce() + mainAttr.getValue());
                symbolsThree5.setForce(symbolsThree5.getForce() + mainAttr.getValue());
            }
        }

        //2号位主属性
        for(GeneralsEnum.SymbolsMainAttr mainAttr : GeneralsEnum.SymbolsMainAttr.values()){
            if(mainAttr.getCode().equals(symbols2.getMainAttr())){
                if(mainAttr.getCode() == 4) {//武力加成
                    for(Generals generals : generalsList){
                        Double d = generals.getMaxThreeDimensional().getForce() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setForce(generals.getSymbolsThreeDimensionals().getForce() + d);
                    }
                }else if(mainAttr.getCode() == 7) {//智力加成
                    for(Generals generals : generalsList){
                        Double d = generals.getMaxThreeDimensional().getIntellect() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setIntellect(generals.getSymbolsThreeDimensionals().getIntellect() + d);
                    }
                }else if(mainAttr.getCode() == 10) {//兵力加成
                    for(Generals generals : generalsList){
                        Double d = generals.getMaxThreeDimensional().getTroops() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setTroops(generals.getSymbolsThreeDimensionals().getTroops() + d);
                    }
                }else if(mainAttr.getCode() == 13) {//全属性加成
                    for(Generals generals : generalsList){
                        Double d1 = generals.getMaxThreeDimensional().getForce() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setForce(generals.getSymbolsThreeDimensionals().getForce() + d1);
                        Double d2 = generals.getMaxThreeDimensional().getIntellect() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setIntellect(generals.getSymbolsThreeDimensionals().getIntellect() + d2);
                        Double d3 = generals.getMaxThreeDimensional().getTroops() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setTroops(generals.getSymbolsThreeDimensionals().getTroops() + d3);
                    }
                }
            }
        }

        //3号位主属性
        for(GeneralsEnum.SymbolsMainAttr mainAttr : GeneralsEnum.SymbolsMainAttr.values()){
            if(mainAttr.getCode().equals(symbols3.getMainAttr())){
                symbolsThree1.setIntellect(symbolsThree1.getIntellect() + mainAttr.getValue());
                symbolsThree2.setIntellect(symbolsThree2.getIntellect() + mainAttr.getValue());
                symbolsThree3.setIntellect(symbolsThree3.getIntellect() + mainAttr.getValue());
                symbolsThree4.setIntellect(symbolsThree4.getIntellect() + mainAttr.getValue());
                symbolsThree5.setIntellect(symbolsThree5.getIntellect() + mainAttr.getValue());
            }
        }

        //4号位主属性
        for(GeneralsEnum.SymbolsMainAttr mainAttr : GeneralsEnum.SymbolsMainAttr.values()){
            if(mainAttr.getCode().equals(symbols4.getMainAttr())){
                if(mainAttr.getCode() == 5) {//武力加成
                    for(Generals generals : generalsList){
                        Double d = generals.getMaxThreeDimensional().getForce() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setForce(generals.getSymbolsThreeDimensionals().getForce() + d);
                    }
                }else if(mainAttr.getCode() == 8) {//智力加成
                    for(Generals generals : generalsList){
                        Double d = generals.getMaxThreeDimensional().getIntellect() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setIntellect(generals.getSymbolsThreeDimensionals().getIntellect() + d);
                    }
                }else if(mainAttr.getCode() == 11) {//兵力加成
                    for(Generals generals : generalsList){
                        Double d = generals.getMaxThreeDimensional().getTroops() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setTroops(generals.getSymbolsThreeDimensionals().getTroops() + d);
                    }
                }else if(mainAttr.getCode() == 14) {//全属性加成
                    for(Generals generals : generalsList){
                        Double d1 = generals.getMaxThreeDimensional().getForce() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setForce(generals.getSymbolsThreeDimensionals().getForce() + d1);
                        Double d2 = generals.getMaxThreeDimensional().getIntellect() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setIntellect(generals.getSymbolsThreeDimensionals().getIntellect() + d2);
                        Double d3 = generals.getMaxThreeDimensional().getTroops() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setTroops(generals.getSymbolsThreeDimensionals().getTroops() + d3);
                    }
                }
            }
        }

        //5号位主属性
        for(GeneralsEnum.SymbolsMainAttr mainAttr : GeneralsEnum.SymbolsMainAttr.values()){
            if(mainAttr.getCode().equals(symbols5.getMainAttr())){
                symbolsThree1.setTroops(symbolsThree1.getTroops() + mainAttr.getValue());
                symbolsThree2.setTroops(symbolsThree2.getTroops() + mainAttr.getValue());
                symbolsThree3.setTroops(symbolsThree3.getTroops() + mainAttr.getValue());
                symbolsThree4.setTroops(symbolsThree4.getTroops() + mainAttr.getValue());
                symbolsThree5.setTroops(symbolsThree5.getTroops() + mainAttr.getValue());
            }
        }

        //6号位主属性
        for(GeneralsEnum.SymbolsMainAttr mainAttr : GeneralsEnum.SymbolsMainAttr.values()){
            if(mainAttr.getCode().equals(symbols6.getMainAttr())){
                if(mainAttr.getCode() == 6) {//武力加成
                    for(Generals generals : generalsList){
                        Double d = generals.getMaxThreeDimensional().getForce() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setForce(generals.getSymbolsThreeDimensionals().getForce() + d);
                    }
                }else if(mainAttr.getCode() == 7) {//智力加成
                    for(Generals generals : generalsList){
                        Double d = generals.getMaxThreeDimensional().getIntellect() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setIntellect(generals.getSymbolsThreeDimensionals().getIntellect() + d);
                    }
                }else if(mainAttr.getCode() == 12) {//兵力加成
                    for(Generals generals : generalsList){
                        Double d = generals.getMaxThreeDimensional().getTroops() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setTroops(generals.getSymbolsThreeDimensionals().getTroops() + d);
                    }
                }else if(mainAttr.getCode() == 15) {//全属性加成
                    for(Generals generals : generalsList){
                        Double d1 = generals.getMaxThreeDimensional().getForce() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setForce(generals.getSymbolsThreeDimensionals().getForce() + d1);
                        Double d2 = generals.getMaxThreeDimensional().getIntellect() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setIntellect(generals.getSymbolsThreeDimensionals().getIntellect() + d2);
                        Double d3 = generals.getMaxThreeDimensional().getTroops() * mainAttr.getRate();
                        generals.getSymbolsThreeDimensionals().setTroops(generals.getSymbolsThreeDimensionals().getTroops() + d3);
                    }
                }
            }
        }

        /**
         * 副属性
         */
        List<Integer> secondAttrIndex = new ArrayList<>();
        secondAttrIndex.add(symbols1.getAttr1());
        secondAttrIndex.add(symbols1.getAttr2());
        secondAttrIndex.add(symbols1.getAttr3());
        secondAttrIndex.add(symbols1.getAttr4());

        int multiple = 6;
        for(Integer index : secondAttrIndex){
            for(GeneralsEnum.SymbolsSecondAttr secondAttr : GeneralsEnum.SymbolsSecondAttr.values()){
                //副属性1
                if(index.equals(secondAttr.getCode())){
                    if(secondAttr.getCode() == 1){//武力增加
                        symbolsThree1.setForce(symbolsThree1.getForce() + secondAttr.getValue() * multiple);
                        symbolsThree2.setForce(symbolsThree2.getForce() + secondAttr.getValue() * multiple);
                        symbolsThree3.setForce(symbolsThree3.getForce() + secondAttr.getValue() * multiple);
                        symbolsThree4.setForce(symbolsThree4.getForce() + secondAttr.getValue() * multiple);
                        symbolsThree5.setForce(symbolsThree5.getForce() + secondAttr.getValue() * multiple);
                    }else if(secondAttr.getCode() == 2){//武力加成
                        for(Generals generals :generalsList){
                            Double d = generals.getMaxThreeDimensional().getForce() * (secondAttr.getRate() * multiple);
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            three.setForce(three.getForce() + d);
                        }
                    }else if(secondAttr.getCode() == 3){//智力增加
                        symbolsThree1.setIntellect(symbolsThree1.getIntellect() + secondAttr.getValue() * multiple);
                        symbolsThree2.setIntellect(symbolsThree2.getIntellect() + secondAttr.getValue() * multiple);
                        symbolsThree3.setIntellect(symbolsThree3.getIntellect() + secondAttr.getValue() * multiple);
                        symbolsThree4.setIntellect(symbolsThree4.getIntellect() + secondAttr.getValue() * multiple);
                        symbolsThree5.setIntellect(symbolsThree5.getIntellect() + secondAttr.getValue() * multiple);
                    }else if(secondAttr.getCode() == 4){//智力加成
                        for(Generals generals :generalsList){
                            Double d = generals.getMaxThreeDimensional().getIntellect() * (secondAttr.getRate() * multiple);
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            three.setIntellect(three.getIntellect() + d);
                        }
                    }else if(secondAttr.getCode() == 5){//兵力增加
                        symbolsThree1.setTroops(symbolsThree1.getTroops() + secondAttr.getValue() * multiple);
                        symbolsThree2.setTroops(symbolsThree2.getTroops() + secondAttr.getValue() * multiple);
                        symbolsThree3.setTroops(symbolsThree3.getTroops() + secondAttr.getValue() * multiple);
                        symbolsThree4.setTroops(symbolsThree4.getTroops() + secondAttr.getValue() * multiple);
                        symbolsThree5.setTroops(symbolsThree5.getTroops() + secondAttr.getValue() * multiple);
                    }else if(secondAttr.getCode() == 6){//兵力加成
                        for(Generals generals :generalsList){
                            Double d = generals.getMaxThreeDimensional().getTroops() * (secondAttr.getRate() * multiple);
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            three.setTroops(three.getTroops() + d);
                        }
                    }else if(secondAttr.getCode() == 7){//吴国全属性
                        for(Generals generals : generalsWu){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            three.setForce(three.getForce() + secondAttr.getValue() * multiple);
                            three.setIntellect(three.getIntellect() + secondAttr.getValue() * multiple);
                            three.setTroops(three.getTroops() + secondAttr.getValue() * multiple);
                        }
                    }else if(secondAttr.getCode() == 8){//吴国全属性加成
                        for(Generals generals : generalsWu){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d1 = generals.getMaxThreeDimensional().getForce() * (secondAttr.getRate() * multiple);
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * (secondAttr.getRate() * multiple);
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * (secondAttr.getRate() * multiple);
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(secondAttr.getCode() == 9){//蜀国全属性
                        for(Generals generals : generalsShu){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            three.setForce(three.getForce() + secondAttr.getValue() * multiple);
                            three.setIntellect(three.getIntellect() + secondAttr.getValue() * multiple);
                            three.setTroops(three.getTroops() + secondAttr.getValue() * multiple);
                        }
                    }else if(secondAttr.getCode() == 10){//蜀国全属性加成
                        for(Generals generals : generalsShu){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d1 = generals.getMaxThreeDimensional().getForce() * (secondAttr.getRate() * multiple);
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * (secondAttr.getRate() * multiple);
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * (secondAttr.getRate() * multiple);
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(secondAttr.getCode() == 11){//魏国全属性
                        for(Generals generals : generalsWei){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            three.setForce(three.getForce() + secondAttr.getValue() * multiple);
                            three.setIntellect(three.getIntellect() + secondAttr.getValue() * multiple);
                            three.setTroops(three.getTroops() + secondAttr.getValue() * multiple);
                        }
                    }else if(secondAttr.getCode() == 12){//魏国全属性加成
                        for(Generals generals : generalsWei){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d1 = generals.getMaxThreeDimensional().getForce() * (secondAttr.getRate() * multiple);
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * (secondAttr.getRate() * multiple);
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * (secondAttr.getRate() * multiple);
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(secondAttr.getCode() == 13){//群国全属性
                        for(Generals generals : generalsQun){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            three.setForce(three.getForce() + secondAttr.getValue() * multiple);
                            three.setIntellect(three.getIntellect() + secondAttr.getValue() * multiple);
                            three.setTroops(three.getTroops() + secondAttr.getValue() * multiple);
                        }
                    }else if(secondAttr.getCode() == 14){//群国全属性加成
                        for(Generals generals : generalsQun){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d1 = generals.getMaxThreeDimensional().getForce() * (secondAttr.getRate() * multiple);
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * (secondAttr.getRate() * multiple);
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * (secondAttr.getRate() * multiple);
                            three.setTroops(three.getTroops() + d3);
                        }
                    }
                }
            }
        }
        /**
         * 套装属性
         */
        Set<Integer> typeIndex = new HashSet<>();
        typeIndex.add(symbols1.getType());
        typeIndex.add(symbols2.getType());
        typeIndex.add(symbols3.getType());
        typeIndex.add(symbols4.getType());
        typeIndex.add(symbols5.getType());
        typeIndex.add(symbols6.getType());
        for(Integer i : typeIndex){
            for(GeneralsEnum.SymbolsType type : GeneralsEnum.SymbolsType.values()){
                if(i.equals(type.getCode())){
                    if(i==1){//苍龙,蜀国全属性加10%
                        for (Generals generals : generalsShu){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(i==2 ){//猛虎,吴国全属性加10%
                        for (Generals generals : generalsWu){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(i==3 ){//火凤,魏国全属性加10%
                        for (Generals generals : generalsWei){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(i==4 ){//天狼,群雄全属性加10%
                        for (Generals generals : generalsQun){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(i==5 ){//玄龟,枪兵全属性加10%
                        for (Generals generals : generalsQiang){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(i==6 ){//翔鹰,弓兵全属性加10%
                        for (Generals generals : generalsGong){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(i==7 ){//麒麟,骑兵全属性加10%
                        for (Generals generals : generalsQi){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(i==8 ){//青鸾,女性全属性加10%
                        for (Generals generals : generalsNv){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(i==9 ){//白泽,全体智力加24%
                        for (Generals generals : generalsAll){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                            three.setIntellect(three.getIntellect() + d2);
                        }
                    }else if(i==10){//混沌,全体全属性加8%
                        for (Generals generals : generalsAll){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(i==11){//穷奇,全体武力加24%
                        for (Generals generals : generalsAll){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                            three.setForce(three.getForce() + d1);
                        }
                    }else if(i==12){//睚眦,全体兵力加24%
                        for (Generals generals : generalsAll){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(i==13 ){//貔貅,骑兵全属性加12%
                        for (Generals generals : generalsQi){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(i==14 ){//狰,枪兵全属性加12%
                        for (Generals generals : generalsQiang){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                            three.setTroops(three.getTroops() + d3);
                        }
                    }else if(i==15 ){//蛊雕,弓兵全属性加12%
                        for (Generals generals : generalsGong){
                            ThreeDimensionals three = generals.getSymbolsThreeDimensionals();
                            Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                            three.setForce(three.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                            three.setIntellect(three.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                            three.setTroops(three.getTroops() + d3);
                        }
                    }
                }
            }
        }
        return null;
    }

    public static List<Result> getExcludeList(List<Result> resultList,int i) {
        List<Result> list = new ArrayList<>();
        for (Result result: resultList) {
            if(result.getTotal2() >= i){
                list.add(result);
            }
        }
        return list;
    }

    public static List<Result> getSimplifyList(List<Result> resultList) {
        Map<String,Result> map = getResultMap(resultList);//国家队
        List<Result> simplifyList = map.values().stream().collect(Collectors.toList());
        return simplifyList;
    }

    private static Map<String,Result> getResultMap(List<Result> resultList){
        Map<String,Result> map = new LinkedHashMap<>();
        for(Result result : resultList){
            if("5群".equals(result.getCountryNames())){
                map.putIfAbsent(GeneralsEnum.Countrys.qunCountry.getName(),result);
            }else if("5魏".equals(result.getCountryNames())){
                map.putIfAbsent(GeneralsEnum.Countrys.weiCountry.getName(),result);
            }else if("5蜀".equals(result.getCountryNames())){
                map.putIfAbsent(GeneralsEnum.Countrys.shuCountry.getName(),result);
            }else if("5吴".equals(result.getCountryNames())){
                map.putIfAbsent(GeneralsEnum.Countrys.wuCountry.getName(),result);
            }else{
                String qunAllRate = GeneralsEnum.SymbolsSecondAttr.qunAllRate.getName();
                String weiAllRate = GeneralsEnum.SymbolsSecondAttr.weiAllRate.getName();
                String shuAllRate = GeneralsEnum.SymbolsSecondAttr.shuAllRate.getName();
                String wuAllRate = GeneralsEnum.SymbolsSecondAttr.wuAllRate.getName();
                String attrName1 = result.getSymbolsList().get(0).getAttrName1();
                String attrName2 = result.getSymbolsList().get(0).getAttrName2();
                String attrName3 = result.getSymbolsList().get(0).getAttrName3();
                String attrName4 = result.getSymbolsList().get(0).getAttrName4();
                if(qunAllRate.equals(attrName1) || qunAllRate.equals(attrName2) || qunAllRate.equals(attrName3) || qunAllRate.equals(attrName4)){
                    map.putIfAbsent(GeneralsEnum.Countrys.qunMashupCountry.getName(),result);
                }else if(weiAllRate.equals(attrName1) || weiAllRate.equals(attrName2) || weiAllRate.equals(attrName3) || weiAllRate.equals(attrName4)){
                    map.putIfAbsent(GeneralsEnum.Countrys.weiMashupCountry.getName(),result);
                }else if(shuAllRate.equals(attrName1) || shuAllRate.equals(attrName2) || shuAllRate.equals(attrName3) || shuAllRate.equals(attrName4)){
                    map.putIfAbsent(GeneralsEnum.Countrys.shuMashupCountry.getName(),result);
                }else if(wuAllRate.equals(attrName1) || wuAllRate.equals(attrName2) || wuAllRate.equals(attrName3) || wuAllRate.equals(attrName4)){
                    map.putIfAbsent(GeneralsEnum.Countrys.wuMashupCountry.getName(),result);
                }
            }
        }
        return map;
    }

    public static Result getResult(List<Generals> generalsList, List<Symbols> symbolsList, List<SymbolsTop> symbolsTopList,List<SymbolsTop> symbolsTopSecond, int allTotalSword, int allTotalSword2){
        Result result = new Result();
        Integer weiCount = 0;//魏国数量
        Integer shuCount = 0;//蜀国数量
        Integer wuCount = 0;//吴国数量
        Integer qunCount = 0;//群雄数量
        Integer qiangCount = 0;//枪兵数量
        Integer qiCount = 0;//骑兵数量
        Integer gongCount = 0;//弓兵数量
        Integer grilCount = 0;//女将数量
        List<CountryArms> countryArms1 = new ArrayList<>();
        List<CountryArms> countryArms2 = new ArrayList<>();
        for(Generals generals : generalsList){
            if(generals.getCountry().equals(GeneralsEnum.Country.wei.getCode())){
                weiCount++;
            }else if(generals.getCountry().equals(GeneralsEnum.Country.shu.getCode())){
                shuCount++;
            }else if(generals.getCountry().equals(GeneralsEnum.Country.wu.getCode())){
                wuCount++;
            }else if(generals.getCountry().equals(GeneralsEnum.Country.qun.getCode())){
                qunCount++;
            }
            if(generals.getArms().equals(GeneralsEnum.Arms.gun.getCode())){
                qiangCount++;
            }else if(generals.getArms().equals(GeneralsEnum.Arms.ride.getCode())){
                qiCount++;
            }else if(generals.getArms().equals(GeneralsEnum.Arms.arch.getCode())){
                gongCount++;
            }
            if(generals.getGender().equals(GeneralsEnum.Gender.gril.getCode())){
                grilCount++;
            }
        }
        if(grilCount == 5){
            result.setIsGril(true);
        }else{
            result.setIsGril(false);
        }
        if(weiCount!=0){countryArms1.add(new CountryArms(weiCount,GeneralsEnum.Country.wei.getName()));}
        if(shuCount!=0){countryArms1.add(new CountryArms(shuCount,GeneralsEnum.Country.shu.getName()));}
        if(wuCount!=0){countryArms1.add(new CountryArms(wuCount,GeneralsEnum.Country.wu.getName()));}
        if(qunCount!=0){countryArms1.add(new CountryArms(qunCount,GeneralsEnum.Country.qun.getName()));}
        if(qiangCount!=0){countryArms2.add(new CountryArms(qiangCount,GeneralsEnum.Arms.gun.getName()));}
        if(qiCount!=0){countryArms2.add(new CountryArms(qiCount,GeneralsEnum.Arms.ride.getName()));}
        if(gongCount!=0){countryArms2.add(new CountryArms(gongCount,GeneralsEnum.Arms.arch.getName()));}
        countryArms1.sort((CountryArms o1,CountryArms o2)->{
            return o2.getCount() - o1.getCount(); //降序
        });
        countryArms2.sort((CountryArms o1,CountryArms o2)->{
            return o2.getCount() - o1.getCount(); //降序
        });
        String countryNames = "";
        for(CountryArms countryArms : countryArms1){
            countryNames += countryArms.getCount()+countryArms.getName();
        }
        String armsNames = "";
        for(CountryArms countryArms : countryArms2){
            armsNames += countryArms.getCount()+countryArms.getName();
        }
        StringBuilder sb = new StringBuilder();
        for(SymbolsTop symbolsTop : symbolsTopList){
            sb.append(symbolsTop.getName()).append(":").append(symbolsTop.getTotal()).append("\r\n");
        }
        StringBuilder second = new StringBuilder();
        for(SymbolsTop symbolsTop : symbolsTopSecond){
            second.append(symbolsTop.getName()).append(":").append(symbolsTop.getTotal()).append("\r\n");
        }

        result.setTotal(allTotalSword);
        result.setTotal2(allTotalSword2);
        result.setGeneralsList(generalsList);
        result.setSymbolsList(symbolsList);
        result.setWeiCount(weiCount);
        result.setShuCount(shuCount);
        result.setWuCount(wuCount);
        result.setQunCount(qunCount);
        result.setQiangCount(qiangCount);
        result.setQiCount(qiCount);
        result.setGongCount(gongCount);
        result.setCountryNames(countryNames);
        result.setArmsNames(armsNames);
        result.setSymbolsTopList(symbolsTopList);
        result.setSymbolsTop(sb.toString());
        result.setSymbolsTopSecond(second.toString());
        return result;
    }


    /*public static Integer getTotalSword3(Generals generals,List<AppointExcludeGenerals> excludeGeneralsList) {
        Integer force = 0;
        Integer intellect = 0;
        Integer troops = 0;

        //120满级三维
        ThreeDimensional maxThreeDimensional = generals.getMaxThreeDimensional();
        force += maxThreeDimensional.getForce();
        intellect += maxThreeDimensional.getIntellect();
        troops += maxThreeDimensional.getTroops();

        //科技三维
        ThreeDimensional scienceThreeDimensional = generals.getScienceThreeDimensional();
        force += scienceThreeDimensional.getForce();
        intellect += scienceThreeDimensional.getIntellect();
        troops += scienceThreeDimensional.getTroops();

        //随从三维
        for(AppointExcludeGenerals excludeGenerals : excludeGeneralsList){
            if(generals.getForceEntourage().getName().equalsIgnoreCase(excludeGenerals.getName())){
                if(excludeGenerals.getCurrentSize() >= excludeGenerals.getMaxSize()) {
                    List<Generals> forceEntourageList = generals.getForceEntourageList();
                    Generals g = null;
                    for (Generals entourage : forceEntourageList) {
                        if (!entourage.getCode().equals(generals.getForceEntourage().getCode())) {
                            if (entourage.getCode().equals(generals.getIntellectEntourage().getCode())) {
                                continue;
                            }
                            if (entourage.getCode().equals(generals.getTroopsEntourage().getCode())) {
                                continue;
                            }
                            g = entourage;
                            break;
                        }
                    }
                    generals.setForceEntourage(g);
                    generals.getEntourageThreeDimensional().setForce(g.getTotalAddForce());
                }
                excludeGenerals.setCurrentSize(excludeGenerals.getCurrentSize()+1);
            }
            if(generals.getIntellectEntourage().getName().equalsIgnoreCase(excludeGenerals.getName())){
                if(excludeGenerals.getCurrentSize() >= excludeGenerals.getMaxSize()){
                    List<Generals> intellectEntourageList = generals.getIntellectEntourageList();
                    Generals g = null;
                    for(Generals entourage : intellectEntourageList){
                        if(!entourage.getCode().equals(generals.getIntellectEntourage().getCode())){
                            if(entourage.getCode().equals(generals.getForceEntourage().getCode())){
                                continue;
                            }
                            if(entourage.getCode().equals(generals.getTroopsEntourage().getCode())){
                                continue;
                            }
                            g = entourage;
                            break;
                        }
                    }
                    generals.setIntellectEntourage(g);
                    generals.getEntourageThreeDimensional().setIntellect(g.getTotalAddIntellect());
                }
                excludeGenerals.setCurrentSize(excludeGenerals.getCurrentSize()+1);
            }
            if(generals.getTroopsEntourage().getName().equalsIgnoreCase(excludeGenerals.getName())){
                if(excludeGenerals.getCurrentSize() >= excludeGenerals.getMaxSize()){
                    List<Generals> entourageList = generals.getTroopsEntourageList();
                    Generals g = null;
                    for(Generals entourage : entourageList){
                        if(!entourage.getCode().equals(generals.getTroopsEntourage().getCode())){
                            if(entourage.getCode().equals(generals.getForceEntourage().getCode())){
                                continue;
                            }
                            if(entourage.getCode().equals(generals.getIntellectEntourage().getCode())){
                                continue;
                            }
                            g = entourage;
                            break;
                        }
                    }
                    generals.setTroopsEntourage(g);
                    generals.getEntourageThreeDimensional().setTroops(g.getTotalAddTroops());
                }
                excludeGenerals.setCurrentSize(excludeGenerals.getCurrentSize()+1);
            }
        }



        ThreeDimensional entourageThreeDimensional = generals.getEntourageThreeDimensional();
        force += entourageThreeDimensional.getForce();
        intellect += entourageThreeDimensional.getIntellect();
        troops += entourageThreeDimensional.getTroops();

        //圣石三维
        ThreeDimensional holyStoneThreeDimensional = generals.getHolyStoneThreeDimensional();
        force += holyStoneThreeDimensional.getForce();
        intellect += holyStoneThreeDimensional.getIntellect();
        troops += holyStoneThreeDimensional.getTroops();

        //战器三维
        ThreeDimensional warDeviceThreeDimensional = generals.getWarDeviceThreeDimensional();
        force += warDeviceThreeDimensional.getForce();
        intellect += warDeviceThreeDimensional.getIntellect();
        troops += warDeviceThreeDimensional.getTroops();

        //特殊战器三维
        //ThreeDimensional warDeviceThreeDimensional2 = generals.getWarDevice2ThreeDimensional();
        //int f = warDeviceThreeDimensional2.getForce();
        //int i = warDeviceThreeDimensional2.getIntellect();
        //int t = warDeviceThreeDimensional2.getTroops();

        //兵种兵书三维
        ThreeDimensional armsBookThreeDimensional = generals.getArmsBookThreeDimensional();
        force += armsBookThreeDimensional.getForce();
        intellect += armsBookThreeDimensional.getIntellect();
        troops += armsBookThreeDimensional.getTroops();

        //将魂三维
        ThreeDimensional willSoulThreeDimensional = generals.getWillSoulThreeDimensional();
        force += willSoulThreeDimensional.getForce();
        intellect += willSoulThreeDimensional.getIntellect();
        troops += willSoulThreeDimensional.getTroops();

        //兵符三维
        ThreeDimensionals symbolsThreeDimensional = generals.getSymbolsThreeDimensionals();
        force += symbolsThreeDimensional.getForce().intValue();
        intellect += symbolsThreeDimensional.getIntellect().intValue();
        troops += symbolsThreeDimensional.getTroops().intValue();

        //战阵三维
        ThreeDimensional battleArrayThreeDimensional = generals.getBattleArrayThreeDimensional();
        force += battleArrayThreeDimensional.getForce();
        intellect += battleArrayThreeDimensional.getIntellect();
        troops += battleArrayThreeDimensional.getTroops();

        //战意三维
        ThreeDimensional warpathThreeDimensional = generals.getWarpathThreeDimensional();
        force += warpathThreeDimensional.getForce();
        intellect += warpathThreeDimensional.getIntellect();
        troops += warpathThreeDimensional.getTroops();
//        Warpath warpath = generals.getWarpath();
//        force += warpath.getForce();
//        intellect += warpath.getIntellect();
//        troops += warpath.getTroops();
//        System.out.println(generals.getName()+" 战意："+warpathThreeDimensional);

        //命格被动战力
        Destiny destiny = generals.getDestiny();
        force += destiny.getForce();
        intellect += destiny.getIntellect();
        troops += destiny.getTroops();

        //幻化三维
        ThreeDimensional skinThreeDimensional = generals.getSkinThreeDimensional();
        force += skinThreeDimensional.getForce();
        intellect += skinThreeDimensional.getIntellect();
        troops += skinThreeDimensional.getTroops();

        //命格被动战力
        Integer destinySword = destiny.getDestinyEffect1() + destiny.getDestinyEffect2()+ destiny.getDestinyEffect3()+ destiny.getDestinyEffect4() + destiny.getMaxLevel();

        //战器三被动战力
        Integer warDeviceSword = 458 + 458 + 1220;

        //武将战力 =（武力+智力+兵力）*2+ 命格被动 + 战器三被动
        Integer total = force + intellect + troops;
        Integer totalSword = (total) * 2 + destinySword + warDeviceSword;
        generals.setTotalSword(totalSword);
        generals.setTotalForce(force);
        generals.setTotalIntellect(intellect);
        generals.setTotalTroops(troops);
        return totalSword;
    }*/

    public static Integer getTotalSword4(Generals generals,List<AppointExcludeGenerals> excludeGeneralsList) {
        int force = 0;
        int intellect = 0;
        int troops = 0;

        //随从三维
        /*for(AppointExcludeGenerals excludeGenerals : excludeGeneralsList){
            if(generals.getForceEntourage().getName().equalsIgnoreCase(excludeGenerals.getName())){
                if(excludeGenerals.getCurrentSize() >= excludeGenerals.getMaxSize()) {
                    List<Generals> forceEntourageList = generals.getForceEntourageList();
                    Generals g = null;
                    for (Generals entourage : forceEntourageList) {
                        if (!entourage.getCode().equals(generals.getForceEntourage().getCode())) {
                            if (entourage.getCode().equals(generals.getIntellectEntourage().getCode())) {
                                continue;
                            }
                            if (entourage.getCode().equals(generals.getTroopsEntourage().getCode())) {
                                continue;
                            }
                            g = entourage;
                            break;
                        }
                    }
                    generals.setForceEntourage(g);
                    generals.getEntourageThreeDimensional().setForce(g.getTotalAddForce());
                }
                excludeGenerals.setCurrentSize(excludeGenerals.getCurrentSize()+1);
            }
            if(generals.getIntellectEntourage().getName().equalsIgnoreCase(excludeGenerals.getName())){
                if(excludeGenerals.getCurrentSize() >= excludeGenerals.getMaxSize()){
                    List<Generals> intellectEntourageList = generals.getIntellectEntourageList();
                    Generals g = null;
                    for(Generals entourage : intellectEntourageList){
                        if(!entourage.getCode().equals(generals.getIntellectEntourage().getCode())){
                            if(entourage.getCode().equals(generals.getForceEntourage().getCode())){
                                continue;
                            }
                            if(entourage.getCode().equals(generals.getTroopsEntourage().getCode())){
                                continue;
                            }
                            g = entourage;
                            break;
                        }
                    }
                    generals.setIntellectEntourage(g);
                    generals.getEntourageThreeDimensional().setIntellect(g.getTotalAddIntellect());
                }
                excludeGenerals.setCurrentSize(excludeGenerals.getCurrentSize()+1);
            }
            if(generals.getTroopsEntourage().getName().equalsIgnoreCase(excludeGenerals.getName())){
                if(excludeGenerals.getCurrentSize() >= excludeGenerals.getMaxSize()){
                    List<Generals> entourageList = generals.getTroopsEntourageList();
                    Generals g = null;
                    for(Generals entourage : entourageList){
                        if(!entourage.getCode().equals(generals.getTroopsEntourage().getCode())){
                            if(entourage.getCode().equals(generals.getForceEntourage().getCode())){
                                continue;
                            }
                            if(entourage.getCode().equals(generals.getIntellectEntourage().getCode())){
                                continue;
                            }
                            g = entourage;
                            break;
                        }
                    }
                    generals.setTroopsEntourage(g);
                    generals.getEntourageThreeDimensional().setTroops(g.getTotalAddTroops());
                }
                excludeGenerals.setCurrentSize(excludeGenerals.getCurrentSize()+1);
            }
        }

        ThreeDimensional entourageThreeDimensional = generals.getEntourageThreeDimensional();
        force += entourageThreeDimensional.getForce();
        intellect += entourageThreeDimensional.getIntellect();
        troops += entourageThreeDimensional.getTroops();*/

        //兵符三维
        ThreeDimensionals symbolsThreeDimensional = generals.getSymbolsThreeDimensionals();
        force += symbolsThreeDimensional.getForce().intValue();
        intellect += symbolsThreeDimensional.getIntellect().intValue();
        troops += symbolsThreeDimensional.getTroops().intValue();

        //战意三维
        ThreeDimensional warpathThreeDimensional = generals.getWarpathThreeDimensional();
        force += warpathThreeDimensional.getForce();
        intellect += warpathThreeDimensional.getIntellect();
        troops += warpathThreeDimensional.getTroops();

        //武将战力 =（武力+智力+兵力）*2+ 命格被动 + 战器三被动
        int total = force + intellect + troops;
        int totalSword = (total) * 2 + generals.getTotalSword();
        generals.setTotalSword2(totalSword);
        generals.setTotalForce2(force+generals.getTotalForce());
        generals.setTotalIntellect2(intellect+generals.getTotalIntellect());
        generals.setTotalTroops2(troops+generals.getTotalTroops());
        return totalSword;
    }
}
