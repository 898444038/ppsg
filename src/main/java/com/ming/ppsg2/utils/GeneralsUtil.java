package com.ming.ppsg2.utils;

import com.ming.ppsg2.entity.*;
import com.ming.ppsg2.enums.GeneralsEnum;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.stream.Collectors;

public class GeneralsUtil {

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
            Integer level = 120 - generals.getLevel();
            Integer force = generals.getForce() + generalsType.getForce() * level;
            Integer intellect = generals.getIntellect() + generalsType.getIntellect() * level;
            Integer troops = generals.getTroops() + generalsType.getTroops() * level;
            three.setForce(force);
            three.setIntellect(intellect);
            three.setTroops(troops);
        }
        generals.setMaxThreeDimensional(three);
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
        }
        generals.setScienceThreeDimensional(three);
        return three;
    }

    public static Map<Integer,List<Generals>> getAllEntourage(List<Generals> all) {
        //List<Integer> entourages = generals.getEntourages();

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

    // todo:获取随从三维
    // 随从三维 = 基础(满级) + 科技 + 四圣石 + 兵种 + 将魂 + 命格突破
    // 加成 = (随从三维/2+100)*(1+(0~0.25))
    public static Map<Integer,List<Generals>> getEntourage(Generals generals,List<Generals> all) {
        List<Integer> entourages = generals.getEntourages();

        String code = generals.getCode();
        List<Generals> lianxie = new ArrayList<>();

        //排除自身的随从武将
        List<Generals> list = new ArrayList<>();
        for(Generals generals1 : all){
            if(!generals1.getCode().equals(generals.getCode())){
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
            boolean b1 = entourages.contains(Integer.valueOf(copy1.getCode()));//是否联协
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
            boolean b2 = entourages.contains(Integer.valueOf(copy2.getCode()));//是否联协
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
            boolean b3 = entourages.contains(Integer.valueOf(copy3.getCode()));//是否联协
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
        generals.setEntourageThreeDimensional(three);


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



    private static Map<Integer,Generals> getGeneralsEntourage(List<Generals> forceList,List<Generals> intellectList,List<Generals> troopsList){
        Map<Integer,Generals> map = new HashMap<>();
        Integer total = 0;

        //武智兵
        Generals generals1_1 = forceList.get(0);//武随
        Generals generals1_2 = null;//智随
        Generals generals1_3 = null;//兵随
        for(Generals generals : intellectList){
            if(!generals.getCode().equals(generals1_1.getCode())){
                generals1_2 = generals;
                break;
            }
        }
        for(Generals generals : troopsList){
            if(!generals.getCode().equals(generals1_1.getCode()) && !generals.getCode().equals(generals1_2.getCode())){
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
            if(!generals.getCode().equals(generals2_1.getCode())){
                generals2_3 = generals;
                break;
            }
        }
        for(Generals generals : intellectList){
            if(!generals.getCode().equals(generals2_1.getCode()) && !generals.getCode().equals(generals2_3.getCode())){
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
            if(!generals.getCode().equals(generals3_3.getCode())){
                generals3_1 = generals;
                break;
            }
        }
        for(Generals generals : intellectList){
            if(!generals.getCode().equals(generals3_3.getCode()) && !generals.getCode().equals(generals3_1.getCode())){
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
            if(!generals.getCode().equals(generals4_3.getCode())){
                generals4_2 = generals;
                break;
            }
        }
        for(Generals generals : forceList){
            if(!generals.getCode().equals(generals4_3.getCode()) && !generals.getCode().equals(generals4_2.getCode())){
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
            if(!generals.getCode().equals(generals5_2.getCode())){
                generals5_3 = generals;
                break;
            }
        }
        for(Generals generals : forceList){
            if(!generals.getCode().equals(generals5_2.getCode()) && !generals.getCode().equals(generals5_3.getCode())){
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
            if(!generals.getCode().equals(generals6_2.getCode())){
                generals6_1 = generals;
                break;
            }
        }
        for(Generals generals : troopsList){
            if(!generals.getCode().equals(generals6_2.getCode()) && !generals.getCode().equals(generals6_1.getCode())){
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
        three.setForce(1077);
        three.setIntellect(1077);
        three.setTroops(2291);
        generals.setHolyStoneThreeDimensional(three);
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
        Integer force = 0;
        Integer intellect = 0;
        Integer troops = 0;

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
        generals.setWarDeviceThreeDimensional(three);

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
        Integer force = 0;
        Integer intellect = 0;
        Integer troops = 0;

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
        generals.setWarDevice2ThreeDimensional(three);

        Device device = new Device();
        device.setName(list.get(0).getName());
        device.setDesc(list.get(0).getDesc());
        device.setQuenchingName1(quenchingName1);
        device.setQuenchingName2(quenchingName2);
        device.setDeviceBaseThreeDimensional(deviceBase);
        device.setDeviceStrengthenThreeDimensional(deviceStrengthen);
        device.setDeviceQuenchingThreeDimensional(deviceQuenching);
        device.setDeviceExclusiveThreeDimensional(deviceExclusive);
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

        Integer force = 0;
        Integer intellect = 0;
        Integer troops = 0;
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
        generals.setArmsBookThreeDimensional(three);
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

        Integer force = 0;
        Integer intellect = 0;
        Integer troops = 0;

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
        generals.setWillSoulThreeDimensional(three);
        return three;
    }

    // 兵符
    public static List<Symbols> getSymbols(List<Generals> generalsList) {
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


        //取4个副属性的所有可能
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.add(5);
        intList.add(6);
        intList.add(7);
        intList.add(8);
        intList.add(9);
        intList.add(10);
        intList.add(11);
        intList.add(12);
        intList.add(13);
        intList.add(14);
        List<List<Integer>> integerList = NumberUtil.getResult(intList,4);

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


        double total = 0;
        ThreeDimensionals finalSecondThree = null;
        List<Integer> finalSecondList = null;
        for(List<Integer> list : integerList){
            ThreeDimensionals three = countGroupSymbols(generalsList,list,generalsAll,generalsWei,generalsShu,generalsWu,generalsQun);
            if(three.getTotal() >= total){
                total = three.getTotal();
                finalSecondThree = three;
                finalSecondList = list;
            }
            //System.out.println("three:"+three);
        }
        //System.out.println("finalSecondThree:"+finalSecondThree);
        //System.out.println("finalSecondList:"+finalSecondList);

        // 设置副属性
        symbols1.setAttr1(finalSecondList.get(0));
        symbols2.setAttr1(finalSecondList.get(0));
        symbols3.setAttr1(finalSecondList.get(0));
        symbols4.setAttr1(finalSecondList.get(0));
        symbols5.setAttr1(finalSecondList.get(0));
        symbols6.setAttr1(finalSecondList.get(0));
        for(GeneralsEnum.SymbolsSecondAttr secondAttr : GeneralsEnum.SymbolsSecondAttr.values()){
            if(secondAttr.getCode().equals(symbols1.getAttr1())){
                symbols1.setAttrName1(secondAttr.getName());
                symbols2.setAttrName1(secondAttr.getName());
                symbols3.setAttrName1(secondAttr.getName());
                symbols4.setAttrName1(secondAttr.getName());
                symbols5.setAttrName1(secondAttr.getName());
                symbols6.setAttrName1(secondAttr.getName());
            }
        }
        symbols1.setAttr2(finalSecondList.get(1));
        symbols2.setAttr2(finalSecondList.get(1));
        symbols3.setAttr2(finalSecondList.get(1));
        symbols4.setAttr2(finalSecondList.get(1));
        symbols5.setAttr2(finalSecondList.get(1));
        symbols6.setAttr2(finalSecondList.get(1));
        for(GeneralsEnum.SymbolsSecondAttr secondAttr : GeneralsEnum.SymbolsSecondAttr.values()){
            if(secondAttr.getCode().equals(symbols1.getAttr2())){
                symbols1.setAttrName2(secondAttr.getName());
                symbols2.setAttrName2(secondAttr.getName());
                symbols3.setAttrName2(secondAttr.getName());
                symbols4.setAttrName2(secondAttr.getName());
                symbols5.setAttrName2(secondAttr.getName());
                symbols6.setAttrName2(secondAttr.getName());
            }
        }
        symbols1.setAttr3(finalSecondList.get(2));
        symbols2.setAttr3(finalSecondList.get(2));
        symbols3.setAttr3(finalSecondList.get(2));
        symbols4.setAttr3(finalSecondList.get(2));
        symbols5.setAttr3(finalSecondList.get(2));
        symbols6.setAttr3(finalSecondList.get(2));
        for(GeneralsEnum.SymbolsSecondAttr secondAttr : GeneralsEnum.SymbolsSecondAttr.values()){
            if(secondAttr.getCode().equals(symbols1.getAttr3())){
                symbols1.setAttrName3(secondAttr.getName());
                symbols2.setAttrName3(secondAttr.getName());
                symbols3.setAttrName3(secondAttr.getName());
                symbols4.setAttrName3(secondAttr.getName());
                symbols5.setAttrName3(secondAttr.getName());
                symbols6.setAttrName3(secondAttr.getName());
            }
        }
        symbols1.setAttr4(finalSecondList.get(3));
        symbols2.setAttr4(finalSecondList.get(3));
        symbols3.setAttr4(finalSecondList.get(3));
        symbols4.setAttr4(finalSecondList.get(3));
        symbols5.setAttr4(finalSecondList.get(3));
        symbols6.setAttr4(finalSecondList.get(3));
        for(GeneralsEnum.SymbolsSecondAttr secondAttr : GeneralsEnum.SymbolsSecondAttr.values()){
            if(secondAttr.getCode().equals(symbols1.getAttr4())){
                symbols1.setAttrName4(secondAttr.getName());
                symbols2.setAttrName4(secondAttr.getName());
                symbols3.setAttrName4(secondAttr.getName());
                symbols4.setAttrName4(secondAttr.getName());
                symbols5.setAttrName4(secondAttr.getName());
                symbols6.setAttrName4(secondAttr.getName());
            }
        }

        //计算2号位主属性
        Integer[] n2 = {4,7,10,13};
        double total2 = 0;
        ThreeDimensionals finalMainThree2 = null;
        Integer finalN2 = null;
        String finalNameN2 = null;
        for(Integer n : n2){
            ThreeDimensionals three2 = new ThreeDimensionals();
            three2.setForce(0d);
            three2.setIntellect(0d);
            three2.setTroops(0d);
            String name = "";
            for(GeneralsEnum.SymbolsMainAttr mainAttr : GeneralsEnum.SymbolsMainAttr.values()){
                if(n.equals(mainAttr.getCode())){
                    name = mainAttr.getName();
                    if(mainAttr.getCode() == 4) {//武力加成
                        for(Generals generals : generalsList){
                            Double d = generals.getMaxThreeDimensional().getForce() * mainAttr.getRate();
                            three2.setForce(three2.getForce() + d);
                        }
                    }else if(mainAttr.getCode() == 7) {//智力加成
                        for(Generals generals : generalsList){
                            Double d = generals.getMaxThreeDimensional().getIntellect() * mainAttr.getRate();
                            three2.setIntellect(three2.getIntellect() + d);
                        }
                    }else if(mainAttr.getCode() == 10) {//兵力加成
                        for(Generals generals : generalsList){
                            Double d = generals.getMaxThreeDimensional().getTroops() * mainAttr.getRate();
                            three2.setTroops(three2.getTroops() + d);
                        }
                    }else if(mainAttr.getCode() == 13) {//全属性加成
                        for(Generals generals : generalsList){
                            Double d1 = generals.getMaxThreeDimensional().getForce() * mainAttr.getRate();
                            three2.setForce(three2.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * mainAttr.getRate();
                            three2.setIntellect(three2.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * mainAttr.getRate();
                            three2.setTroops(three2.getTroops() + d3);
                        }
                    }
                }
            }
            three2.setTotal(three2.getForce()+three2.getIntellect()+three2.getTroops());
            if(three2.getTotal() >= total2){
                total2 = three2.getTotal();
                finalMainThree2 = three2;
                finalNameN2 = name;
                finalN2 = n;
            }
        }
        symbols2.setNumber(GeneralsEnum.Symbols.number2.getCode());
        symbols2.setMainAttr(finalN2);
        symbols2.setMainAttrName(finalNameN2);


        //计算4号位主属性
        Integer[] n4 = {5,8,11,14};
        double total4 = 0;
        ThreeDimensionals finalMainThree4 = null;
        Integer finalN4 = null;
        String finalNameN4 = null;
        for(Integer n : n4){
            ThreeDimensionals three4 = new ThreeDimensionals();
            three4.setForce(0d);
            three4.setIntellect(0d);
            three4.setTroops(0d);
            String name = "";
            for(GeneralsEnum.SymbolsMainAttr mainAttr : GeneralsEnum.SymbolsMainAttr.values()){
                if(n.equals(mainAttr.getCode())){
                    name = mainAttr.getName();
                    if(mainAttr.getCode() == 5) {//武力加成
                        for(Generals generals : generalsList){
                            Double d = generals.getMaxThreeDimensional().getForce() * mainAttr.getRate();
                            three4.setForce(three4.getForce() + d);
                        }
                    }else if(mainAttr.getCode() == 8) {//智力加成
                        for(Generals generals : generalsList){
                            Double d = generals.getMaxThreeDimensional().getIntellect() * mainAttr.getRate();
                            three4.setIntellect(three4.getIntellect() + d);
                        }
                    }else if(mainAttr.getCode() == 11) {//兵力加成
                        for(Generals generals : generalsList){
                            Double d = generals.getMaxThreeDimensional().getTroops() * mainAttr.getRate();
                            three4.setTroops(three4.getTroops() + d);
                        }
                    }else if(mainAttr.getCode() == 14) {//全属性加成
                        for(Generals generals : generalsList){
                            Double d1 = generals.getMaxThreeDimensional().getForce() * mainAttr.getRate();
                            three4.setForce(three4.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * mainAttr.getRate();
                            three4.setIntellect(three4.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * mainAttr.getRate();
                            three4.setTroops(three4.getTroops() + d3);
                        }
                    }
                }
            }
            three4.setTotal(three4.getForce()+three4.getIntellect()+three4.getTroops());
            if(three4.getTotal() >= total4){
                total4 = three4.getTotal();
                finalMainThree4 = three4;
                finalNameN4 = name;
                finalN4 = n;
            }
        }
        symbols4.setNumber(GeneralsEnum.Symbols.number4.getCode());
        symbols4.setMainAttr(finalN4);
        symbols4.setMainAttrName(finalNameN4);

        //计算6号位主属性
        Integer[] n6 = {6,9,12,15};
        double total6 = 0;
        ThreeDimensionals finalMainThree6 = null;
        Integer finalN6 = null;
        String finalNameN6 = null;
        for(Integer n : n6){
            ThreeDimensionals three6 = new ThreeDimensionals();
            three6.setForce(0d);
            three6.setIntellect(0d);
            three6.setTroops(0d);
            String name = "";
            for(GeneralsEnum.SymbolsMainAttr mainAttr : GeneralsEnum.SymbolsMainAttr.values()){
                if(n.equals(mainAttr.getCode())){
                    name = mainAttr.getName();
                    if(mainAttr.getCode() == 6) {//武力加成
                        for(Generals generals : generalsList){
                            Double d = generals.getMaxThreeDimensional().getForce() * mainAttr.getRate();
                            three6.setForce(three6.getForce() + d);
                        }
                    }else if(mainAttr.getCode() == 7) {//智力加成
                        for(Generals generals : generalsList){
                            Double d = generals.getMaxThreeDimensional().getIntellect() * mainAttr.getRate();
                            three6.setIntellect(three6.getIntellect() + d);
                        }
                    }else if(mainAttr.getCode() == 12) {//兵力加成
                        for(Generals generals : generalsList){
                            Double d = generals.getMaxThreeDimensional().getTroops() * mainAttr.getRate();
                            three6.setTroops(three6.getTroops() + d);
                        }
                    }else if(mainAttr.getCode() == 15) {//全属性加成
                        for(Generals generals : generalsList){
                            Double d1 = generals.getMaxThreeDimensional().getForce() * mainAttr.getRate();
                            three6.setForce(three6.getForce() + d1);
                            Double d2 = generals.getMaxThreeDimensional().getIntellect() * mainAttr.getRate();
                            three6.setIntellect(three6.getIntellect() + d2);
                            Double d3 = generals.getMaxThreeDimensional().getTroops() * mainAttr.getRate();
                            three6.setTroops(three6.getTroops() + d3);
                        }
                    }
                }
            }
            three6.setTotal(three6.getForce()+three6.getIntellect()+three6.getTroops());
            if(three6.getTotal() >= total6){
                total6 = three6.getTotal();
                finalMainThree6 = three6;
                finalNameN6 = name;
                finalN6 = n;
            }
        }
        symbols6.setNumber(GeneralsEnum.Symbols.number6.getCode());
        symbols6.setMainAttr(finalN6);
        symbols6.setMainAttrName(finalNameN6);



        //取兵符类型的所有可能
        List<Integer> indexList = new ArrayList<>();
        indexList.add(1);
        indexList.add(2);
        indexList.add(3);
        indexList.add(4);
        indexList.add(5);
        indexList.add(6);
        indexList.add(7);
        indexList.add(8);
        indexList.add(9);
        indexList.add(10);
        indexList.add(11);
        indexList.add(12);
        List<List<Integer>> resultList = NumberUtil.getResult(indexList,3);

        int typeTotal = 0;
        ThreeDimensional finalTypeThree = null;
        List<Integer> finalTypeResult = null;
        for(List<Integer> result : resultList){
            ThreeDimensional three = new ThreeDimensional();
            three.setForce(0);
            three.setIntellect(0);
            three.setTroops(0);
            for(Integer i : result){
                for(GeneralsEnum.SymbolsType type : GeneralsEnum.SymbolsType.values()){
                    if(i.equals(type.getCode())){
                        if(i==1){//苍龙,蜀国全属性加10%
                            for (Generals generals : generalsShu){
                                Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                                three.setForce(three.getForce() + d1.intValue());
                                Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                                three.setIntellect(three.getIntellect() + d2.intValue());
                                Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                                three.setTroops(three.getTroops() + d3.intValue());
                            }
                        }else if(i==2 ){//猛虎,吴国全属性加10%
                            for (Generals generals : generalsWu){
                                Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                                three.setForce(three.getForce() + d1.intValue());
                                Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                                three.setIntellect(three.getIntellect() + d2.intValue());
                                Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                                three.setTroops(three.getTroops() + d3.intValue());
                            }
                        }else if(i==3 ){//火凤,魏国全属性加10%
                            for (Generals generals : generalsWei){
                                Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                                three.setForce(three.getForce() + d1.intValue());
                                Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                                three.setIntellect(three.getIntellect() + d2.intValue());
                                Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                                three.setTroops(three.getTroops() + d3.intValue());
                            }
                        }else if(i==4 ){//天狼,群雄全属性加10%
                            for (Generals generals : generalsQun){
                                Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                                three.setForce(three.getForce() + d1.intValue());
                                Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                                three.setIntellect(three.getIntellect() + d2.intValue());
                                Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                                three.setTroops(three.getTroops() + d3.intValue());
                            }
                        }else if(i==5 ){//玄龟,枪兵全属性加10%
                            for (Generals generals : generalsQiang){
                                Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                                three.setForce(three.getForce() + d1.intValue());
                                Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                                three.setIntellect(three.getIntellect() + d2.intValue());
                                Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                                three.setTroops(three.getTroops() + d3.intValue());
                            }
                        }else if(i==6 ){//翔鹰,弓兵全属性加10%
                            for (Generals generals : generalsGong){
                                Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                                three.setForce(three.getForce() + d1.intValue());
                                Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                                three.setIntellect(three.getIntellect() + d2.intValue());
                                Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                                three.setTroops(three.getTroops() + d3.intValue());
                            }
                        }else if(i==7 ){//麒麟,骑兵全属性加10%
                            for (Generals generals : generalsQi){
                                Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                                three.setForce(three.getForce() + d1.intValue());
                                Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                                three.setIntellect(three.getIntellect() + d2.intValue());
                                Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                                three.setTroops(three.getTroops() + d3.intValue());
                            }
                        }else if(i==8 ){//青鸾,女性全属性加10%
                            for (Generals generals : generalsNv){
                                Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                                three.setForce(three.getForce() + d1.intValue());
                                Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                                three.setIntellect(three.getIntellect() + d2.intValue());
                                Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                                three.setTroops(three.getTroops() + d3.intValue());
                            }
                        }else if(i==9 ){//白泽,全体智力加24%
                            for (Generals generals : generalsAll){
                                Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                                three.setIntellect(three.getIntellect() + d2.intValue());
                            }
                        }else if(i==10){//混沌,全体全属性加8%
                            for (Generals generals : generalsAll){
                                Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                                three.setForce(three.getForce() + d1.intValue());
                                Double d2 = generals.getMaxThreeDimensional().getIntellect() * type.getRate();
                                three.setIntellect(three.getIntellect() + d2.intValue());
                                Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                                three.setTroops(three.getTroops() + d3.intValue());
                            }
                        }else if(i==11){//穷奇,全体武力加24%
                            for (Generals generals : generalsAll){
                                Double d1 = generals.getMaxThreeDimensional().getForce() * type.getRate();
                                three.setForce(three.getForce() + d1.intValue());
                            }
                        }else if(i==12){//睚眦,全体兵力加24%
                            for (Generals generals : generalsAll){
                                Double d3 = generals.getMaxThreeDimensional().getTroops() * type.getRate();
                                three.setTroops(three.getTroops() + d3.intValue());
                            }
                        }
                    }
                }
            }
            three.setTotal(three.getForce()+three.getIntellect()+three.getTroops());
            if(three.getTotal() >= typeTotal){
                typeTotal = three.getTotal();
                finalTypeResult = result;
                finalTypeThree = three;
            }
        }

        Map<String,String> map = new HashMap<>();
        for(Integer i : finalTypeResult) {
            for (GeneralsEnum.SymbolsType type : GeneralsEnum.SymbolsType.values()) {
                if (i.equals(type.getCode())) {
                    map.put(i+"",type.getName());
                }
            }
        }

        symbols1.setType(finalTypeResult.get(0));
        symbols1.setTypeName(map.get(finalTypeResult.get(0).toString()));
        symbols2.setType(finalTypeResult.get(0));
        symbols2.setTypeName(map.get(finalTypeResult.get(0).toString()));

        symbols3.setType(finalTypeResult.get(1));
        symbols3.setTypeName(map.get(finalTypeResult.get(1).toString()));
        symbols4.setType(finalTypeResult.get(1));
        symbols4.setTypeName(map.get(finalTypeResult.get(1).toString()));

        symbols5.setType(finalTypeResult.get(2));
        symbols5.setTypeName(map.get(finalTypeResult.get(2).toString()));
        symbols6.setType(finalTypeResult.get(2));
        symbols6.setTypeName(map.get(finalTypeResult.get(2).toString()));

        List<Symbols> symbolsList = new ArrayList<>();
        symbolsList.add(symbols1);
        symbolsList.add(symbols2);
        symbolsList.add(symbols3);
        symbolsList.add(symbols4);
        symbolsList.add(symbols5);
        symbolsList.add(symbols6);
        return symbolsList;
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
        /*String[] g = {"0","1","2","3","4"};
        String[] w = {"1","2","3","4","5","6"};
        String[][] temp = { g , w };
        LinkedList<String[]> list = new LinkedList<String[]>();
        NumberUtil.recursionSub(list, temp.length, temp, 0, -1);

        List<Generals> generalsList1 = new ArrayList<>();
        List<Generals> generalsList2 = new ArrayList<>();
        List<Generals> generalsList3 = new ArrayList<>();
        List<Generals> generalsList4 = new ArrayList<>();
        List<Generals> generalsList5 = new ArrayList<>();

        for ( int i = 0; i < list.size (); i++ ){
            Integer index1 = Integer.valueOf(list.get(i)[0]);
            Integer index2 = Integer.valueOf(list.get(i)[1]);
            if(list.get(i)[0].equals(g[0])){
                Generals generals = generalsList.get(index1);
                Generals copy = new Generals();
                BeanUtils.copyProperties(generals,copy);
                Warpath warpath = new Warpath();
                warpath.setGroup(index2);
                copy.setWarpath(warpath);
                generalsList1.add(copy);
            }else if(list.get(i)[0].equals(g[1])){
                Generals generals = generalsList.get(index1);
                Generals copy = new Generals();
                BeanUtils.copyProperties(generals,copy);
                Warpath warpath = new Warpath();
                warpath.setGroup(index2);
                copy.setWarpath(warpath);
                generalsList2.add(copy);
            }else if(list.get(i)[0].equals(g[2])){
                Generals generals = generalsList.get(index1);
                Generals copy = new Generals();
                BeanUtils.copyProperties(generals,copy);
                Warpath warpath = new Warpath();
                warpath.setGroup(index2);
                copy.setWarpath(warpath);
                generalsList3.add(copy);
            }else if(list.get(i)[0].equals(g[3])){
                Generals generals = generalsList.get(index1);
                Generals copy = new Generals();
                BeanUtils.copyProperties(generals,copy);
                Warpath warpath = new Warpath();
                warpath.setGroup(index2);
                copy.setWarpath(warpath);
                generalsList4.add(copy);
            }else if(list.get(i)[0].equals(g[4])){
                Generals generals = generalsList.get(index1);
                Generals copy = new Generals();
                BeanUtils.copyProperties(generals,copy);
                Warpath warpath = new Warpath();
                warpath.setGroup(index2);
                copy.setWarpath(warpath);
                generalsList5.add(copy);
            }
        }*/


        /*String[] s1 = { "0", "1", "2", "3", "4", "5" };
        String[] s2 = { "0", "1", "2", "3", "4", "5" };
        String[] s3 = { "0", "1", "2", "3", "4", "5" };
        String[] s4 = { "0", "1", "2", "3", "4", "5" };
        String[] s5 = { "0", "1", "2", "3", "4", "5" };
        String[][] temps = { s1, s2, s3, s4, s5 };
        LinkedList<String[]> lists = new LinkedList<String[]>();
        NumberUtil.recursionSub(lists, temps.length, temps, 0, -1);*/

        /*Integer total = 0;
        Integer i1 = null;
        Integer i2 = null;
        Integer i3 = null;
        Integer i4 = null;
        Integer i5 = null;*/
        //List<Generals> finalList = new ArrayList<>();
        //List<Warpath> finalWarpathList = new ArrayList<>();

        /*for ( int i = 0; i < lists.size (); i++ ) {
            Integer index1 = Integer.valueOf(lists.get(i)[0]);
            Integer index2 = Integer.valueOf(lists.get(i)[1]);
            Integer index3 = Integer.valueOf(lists.get(i)[2]);
            Integer index4 = Integer.valueOf(lists.get(i)[3]);
            Integer index5 = Integer.valueOf(lists.get(i)[4]);
            //System.out.println (Arrays.toString (lists.get (i)).replaceAll ("[\\[\\]\\,\\s]", ""));

            Integer force = 0;
            Integer intellect = 0;
            Integer troops = 0;

            Generals generals1 = generalsList1.get(index1);
            Generals copy1 = new Generals();
            BeanUtils.copyProperties(generals1,copy1);
            Warpath warpath1 = new Warpath();
            warpath1.setGroup(generals1.getWarpath().getGroup());
            copy1.setWarpath(warpath1);

            Generals generals2 = generalsList2.get(index2);
            Generals copy2 = new Generals();
            BeanUtils.copyProperties(generals2,copy2);
            Warpath warpath2 = new Warpath();
            warpath2.setGroup(generals2.getWarpath().getGroup());
            copy2.setWarpath(warpath2);

            Generals generals3 = generalsList3.get(index3);
            Generals copy3 = new Generals();
            BeanUtils.copyProperties(generals3,copy3);
            Warpath warpath3 = new Warpath();
            warpath3.setGroup(generals3.getWarpath().getGroup());
            copy3.setWarpath(warpath3);

            Generals generals4 = generalsList4.get(index4);
            Generals copy4 = new Generals();
            BeanUtils.copyProperties(generals4,copy4);
            Warpath warpath4 = new Warpath();
            warpath4.setGroup(generals4.getWarpath().getGroup());
            copy4.setWarpath(warpath4);

            Generals generals5 = generalsList5.get(index5);
            Generals copy5 = new Generals();
            BeanUtils.copyProperties(generals5,copy5);
            Warpath warpath5 = new Warpath();
            warpath5.setGroup(generals5.getWarpath().getGroup());
            copy5.setWarpath(warpath5);

            List<Generals> generalsLists = new ArrayList<>();
            generalsLists.add(copy1);
            generalsLists.add(copy2);
            generalsLists.add(copy3);
            generalsLists.add(copy4);
            generalsLists.add(copy5);

            List<Warpath> warpathList = countWarpath(generalsLists);
            ThreeDimensional three = new ThreeDimensional();
            Integer totalForce = copy1.getWarpath().getForce() + copy2.getWarpath().getForce() + copy3.getWarpath().getForce() + copy4.getWarpath().getForce() + copy5.getWarpath().getForce();
            Integer totalIntellect = copy1.getWarpath().getIntellect() + copy2.getWarpath().getIntellect() + copy3.getWarpath().getIntellect() + copy4.getWarpath().getIntellect() + copy5.getWarpath().getIntellect();
            Integer totalTroops = copy1.getWarpath().getTroops() + copy2.getWarpath().getTroops() + copy3.getWarpath().getTroops() + copy4.getWarpath().getTroops() + copy5.getWarpath().getTroops();
            three.setForce(totalForce);
            three.setIntellect(totalIntellect);
            three.setTroops(totalTroops);
            three.setTotal(totalForce + totalIntellect + totalTroops);

            if(three.getTotal() >= total){
                total = three.getTotal();
                i1 = index1;
                i2 = index2;
                i3 = index3;
                i4 = index4;
                i5 = index5;
                finalList = generalsLists;
                finalWarpathList = warpathList;
            }
        }*/


        /*for (Warpath warpath : finalWarpathList){
            for(GeneralsEnum.Warpath warpaths : GeneralsEnum.Warpath.values()){
                if(warpath.getGroup().equals(warpaths.getCode())){
                    warpath.setGroupName(warpaths.getName());
                }
            }
        }*/

        /*ThreeDimensional three = new ThreeDimensional();
        Integer force = 0;
        Integer intellect = 0;
        Integer troops = 0;*/


        /*Warpath warpath1 = finalWarpathList.get(0);
        Warpath warpath2 = finalWarpathList.get(1);
        Warpath warpath3 = finalWarpathList.get(2);
        Warpath warpath4 = finalWarpathList.get(3);
        Warpath warpath5 = finalWarpathList.get(4);

        generalsList.get(0).setWarpath(warpath1);
        generalsList.get(1).setWarpath(warpath2);
        generalsList.get(2).setWarpath(warpath3);
        generalsList.get(3).setWarpath(warpath4);
        generalsList.get(4).setWarpath(warpath5);*/

        /*ThreeDimensional three1 = new ThreeDimensional(warpath1.getForce(),warpath1.getIntellect(),warpath1.getTroops());
        ThreeDimensional three2 = new ThreeDimensional(warpath2.getForce(),warpath2.getIntellect(),warpath2.getTroops());
        ThreeDimensional three3 = new ThreeDimensional(warpath3.getForce(),warpath3.getIntellect(),warpath3.getTroops());
        ThreeDimensional three4 = new ThreeDimensional(warpath4.getForce(),warpath4.getIntellect(),warpath4.getTroops());
        ThreeDimensional three5 = new ThreeDimensional(warpath5.getForce(),warpath5.getIntellect(),warpath5.getTroops());*/

        List<Generals> copyList = new ArrayList<>();
        for(Generals generals : generalsList) {
            Generals copy = new Generals();
            BeanUtils.copyProperties(generals,copy);
            copyList.add(copy);
        }

        for(Generals generals : generalsList) {
            countWarpath2(generals,copyList);
        }

        Warpath warpath1 = copyList.get(0).getWarpath();
        Warpath warpath2 = copyList.get(1).getWarpath();
        Warpath warpath3 = copyList.get(2).getWarpath();
        Warpath warpath4 = copyList.get(3).getWarpath();
        Warpath warpath5 = copyList.get(4).getWarpath();
        ThreeDimensional three1 = new ThreeDimensional(warpath1.getForce(),warpath1.getIntellect(),warpath1.getTroops());
        ThreeDimensional three2 = new ThreeDimensional(warpath2.getForce(),warpath2.getIntellect(),warpath2.getTroops());
        ThreeDimensional three3 = new ThreeDimensional(warpath3.getForce(),warpath3.getIntellect(),warpath3.getTroops());
        ThreeDimensional three4 = new ThreeDimensional(warpath4.getForce(),warpath4.getIntellect(),warpath4.getTroops());
        ThreeDimensional three5 = new ThreeDimensional(warpath5.getForce(),warpath5.getIntellect(),warpath5.getTroops());

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
            int t = 0;
            for(Generals g : allList){
                if(warpath.getCode().equals(GeneralsEnum.Warpath.countryForce.getCode())){//同国家上阵武力加成
                    Warpath wp = new Warpath();
                    if(g.getCountry().equals(generals1.getCountry())){
                        Double d = g.getMaxThreeDimensional().getForce() * warpath.getRate();
                        wp.setForce(d.intValue());
                        wp.setGroup(warpath.getCode());
                        wp.setGroupName(warpath.getName());
                        wp.setGeneralsCode(g.getCode());
                        t += d.intValue();
                    }
                    warpathList1.add(wp);
                }else if(warpath.getCode().equals(GeneralsEnum.Warpath.countryIntellect.getCode())){//同国家上阵智力加成
                    Warpath wp = new Warpath();
                    if(g.getCountry().equals(generals1.getCountry())){
                        Double d = g.getMaxThreeDimensional().getIntellect() * warpath.getRate();
                        wp.setIntellect(d.intValue());
                        wp.setGroup(warpath.getCode());
                        wp.setGroupName(warpath.getName());
                        wp.setGeneralsCode(g.getCode());
                        t += d.intValue();
                    }
                    warpathList2.add(wp);
                }else if(warpath.getCode().equals(GeneralsEnum.Warpath.countryTroops.getCode())){//同国家上阵兵力加成
                    Warpath wp = new Warpath();
                    if(g.getCountry().equals(generals1.getCountry())){
                        Double d = g.getMaxThreeDimensional().getTroops() * warpath.getRate();
                        wp.setTroops(d.intValue());
                        wp.setGroup(warpath.getCode());
                        wp.setGroupName(warpath.getName());
                        wp.setGeneralsCode(g.getCode());
                        t += d.intValue();
                    }
                    warpathList3.add(wp);
                }else if(warpath.getCode().equals(GeneralsEnum.Warpath.armsForce.getCode())){//同兵种上阵武力加成
                    Warpath wp = new Warpath();
                    if(g.getArms().equals(generals1.getArms())){
                        Double d = g.getMaxThreeDimensional().getForce() * warpath.getRate();
                        wp.setForce(d.intValue());
                        wp.setGroup(warpath.getCode());
                        wp.setGroupName(warpath.getName());
                        wp.setGeneralsCode(g.getCode());
                        t += d.intValue();
                    }
                    warpathList4.add(wp);
                }else if(warpath.getCode().equals(GeneralsEnum.Warpath.armsIntellect.getCode())){//同兵种上阵智力加成
                    Warpath wp = new Warpath();
                    if(g.getArms().equals(generals1.getArms())){
                        Double d = g.getMaxThreeDimensional().getIntellect() * warpath.getRate();
                        wp.setIntellect(d.intValue());
                        wp.setGroup(warpath.getCode());
                        wp.setGroupName(warpath.getName());
                        wp.setGeneralsCode(g.getCode());
                        t += d.intValue();
                    }
                    warpathList5.add(wp);
                }else if(warpath.getCode().equals(GeneralsEnum.Warpath.armsTroops.getCode())){//同兵种上阵兵力加成
                    Warpath wp = new Warpath();
                    if(g.getArms().equals(generals1.getArms())){
                        Double d = g.getMaxThreeDimensional().getTroops() * warpath.getRate();
                        wp.setTroops(d.intValue());
                        wp.setGroup(warpath.getCode());
                        wp.setGroupName(warpath.getName());
                        wp.setGeneralsCode(g.getCode());
                        t += d.intValue();
                    }
                    warpathList6.add(wp);
                }

            }

            if(t>total){
                total = t;
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
            if(warpath.getGeneralsCode() != null){
                for(Generals generals : allList){
                    if(warpath.getGeneralsCode().equals(generals.getCode())){
                        generals.getWarpath().setForce(generals.getWarpath().getForce() + warpath.getForce());
                        generals.getWarpath().setIntellect(generals.getWarpath().getIntellect() + warpath.getIntellect());
                        generals.getWarpath().setTroops(generals.getWarpath().getTroops() + warpath.getTroops());
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
        generals.setBattleArrayThreeDimensional(three);
        return three;
    }

    // 命格
    public static ThreeDimensional getDestiny(Generals generals) {
        ThreeDimensional three = new ThreeDimensional();
        Destiny destiny = generals.getDestiny();
        Integer force = destiny.getForce();
        Integer intellect = destiny.getIntellect();
        Integer troops = destiny.getTroops();
        three.setForce(force);
        three.setIntellect(intellect);
        three.setTroops(troops);
        for (GeneralsEnum.Destiny type : GeneralsEnum.Destiny.values()){
            if(type.getCode().equals(destiny.getDisobey())){
                destiny.setDestinyEffect1(type.getEffect1());
                destiny.setDestinyEffect2(type.getEffect2());
                destiny.setMaxLevel(type.getLevel4());
                break;
            }
        }
        generals.setDestinyThreeDimensional(three);
        return three;
    }

    // 获取武将总战力
    // 武将战力 =（武力+智力+兵力）*2+ 命格被动 + 战器三被动
    public static Integer getTotalSword(Generals generals) {
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

        Integer destinySword = destiny.getDestinyEffect1() + destiny.getDestinyEffect2() + destiny.getMaxLevel();

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
    }


    // 获取武将总战力(特殊战器)
    // 武将战力 =（武力+智力+兵力）*2+ 命格被动 + 战器三被动
    public static Integer getTotalSword2(Generals generals) {
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

        //特殊战器三维
        ThreeDimensional warDeviceThreeDimensional = generals.getWarDevice2ThreeDimensional();
        force += warDeviceThreeDimensional.getForce();
        intellect += warDeviceThreeDimensional.getIntellect();
        troops += warDeviceThreeDimensional.getTroops();

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

        Integer destinySword = destiny.getDestinyEffect1() + destiny.getDestinyEffect2() + destiny.getMaxLevel();

        //战器三被动战力
        Integer warDeviceSword = 458 + 458 + 1220;

        //武将战力 =（武力+智力+兵力）*2+ 命格被动 + 战器三被动
        Integer total = force + intellect + troops;
        Integer totalSword = (total) * 2 + destinySword + warDeviceSword;
        generals.setTotalSword2(totalSword);
        generals.setTotalForce2(force);
        generals.setTotalIntellect2(intellect);
        generals.setTotalTroops2(troops);
        return totalSword;
    }

    //总战力 = 武将1战力 + 武将2战力 + 武将3战力 + 武将4战力 + 武将5战力 + 工坊战力（10152）
    public static Integer getAllTotalSword(List<Generals> generalsList) {
        // 76710  75072   75060  72510  71394
        Integer total = 10152;
        for (Generals generals : generalsList){
            total += getTotalSword(generals);
        }
        return total;
    }

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
                    }
                }
            }
        }
        return null;
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
            }else if(GeneralsEnum.SymbolsSecondAttr.qunAllRate.getName().equals(result.getSymbolsList().get(0).getAttrName4())){
                map.putIfAbsent(GeneralsEnum.Countrys.qunMashupCountry.getName(),result);
            }else if(GeneralsEnum.SymbolsSecondAttr.weiAllRate.getName().equals(result.getSymbolsList().get(0).getAttrName4())){
                map.putIfAbsent(GeneralsEnum.Countrys.weiMashupCountry.getName(),result);
            }else if(GeneralsEnum.SymbolsSecondAttr.shuAllRate.getName().equals(result.getSymbolsList().get(0).getAttrName4())){
                map.putIfAbsent(GeneralsEnum.Countrys.shuMashupCountry.getName(),result);
            }else if(GeneralsEnum.SymbolsSecondAttr.wuAllRate.getName().equals(result.getSymbolsList().get(0).getAttrName4())){
                map.putIfAbsent(GeneralsEnum.Countrys.wuMashupCountry.getName(),result);
            }
        }
        return map;
    }

    public static Result getResult(List<Generals> generalsList,List<Symbols> symbolsList,int allTotalSword,int allTotalSword2){
        Result result = new Result();
        Integer weiCount = 0;//魏国数量
        Integer shuCount = 0;//蜀国数量
        Integer wuCount = 0;//吴国数量
        Integer qunCount = 0;//群雄数量
        Integer qiangCount = 0;//枪兵数量
        Integer qiCount = 0;//骑兵数量
        Integer gongCount = 0;//弓兵数量
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

        return result;
    }
}
