package com.ming.ppsg2.utils;


import com.ming.ppsg2.entity.AppointGenerals;
import com.ming.ppsg2.entity.Generals;
import com.ming.ppsg2.entity.Result;
import com.ming.ppsg2.entity.Symbols;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil2 {


    public static void main ( String[] args ){
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        numbers.add(7);
        List<Integer> appointList = new ArrayList<>();
        List<List<Integer>> lists = getResult(numbers,5,appointList);
        System.out.println(lists);
    }


    public static List<List<Integer>> getResult(List<Integer> data, int size, List<Integer> appointList) {
        List<List<Integer>> intList = new ArrayList<>();
        combinations2(intList,new ArrayList<>(),data, size,appointList);
        return intList;
    }


    /**
     * @param selector	选完的集合 初始化为空
     * @param intList		集合
     * @param selector		待选集合
     * @param n			选出的数量
     */
    //private static List<List<Integer>> intList = new ArrayList<>();
    public static void combinations2(List<List<Integer>> intList,List<Integer> selector,List<Integer> data,int n,List<Integer> appointList) {
        if(n == 0) {
            int size = appointList.size();
            int total = 0;
            for (Integer integer : selector) {
                for (Integer appoint : appointList) {
                    if(integer.equals(appoint)){
                        total++;
                    }
                }
            }
            if(size == total || appointList.isEmpty()) {
                List<Integer> list = new ArrayList<>();
                for (Integer i : selector) {
                    list.add(i);
                }
                intList.add(list);
            }
            return;
        }
        if(data.isEmpty()) {
            return;
        }
        //选择第一个元素,将元素放入集合
        selector.add(data.get(0));
        combinations2(intList,selector,data.subList(1, data.size()),n - 1,appointList); //从第二个元素开始选择，再选择两个
        //不选择第一个元素
        selector.remove(selector.size() -1 );
        combinations2(intList,selector,data.subList(1, data.size()), n,appointList); //从第二个元素开始选择，选择两个
    }


    public static void count(List<Result> resultList){
        System.out.println(resultList.size());
        Collections.sort(resultList, new Comparator<Result>(){
            @Override
            public int compare(Result o1, Result o2) {
                int type1 = o1.getSymbolsList().get(0).getType().hashCode() - o2.getSymbolsList().get(0).getType().hashCode();
                int type2 = o1.getSymbolsList().get(2).getType().hashCode() - o2.getSymbolsList().get(2).getType().hashCode();
                int type3 = o1.getSymbolsList().get(4).getType().hashCode() - o2.getSymbolsList().get(4).getType().hashCode();
                return type1 + type2 + type3;
            }
        });
        List<String> names = new ArrayList<>();
        for (Result result : resultList){
            List<Symbols> symbols = result.getSymbolsList();
            String s = symbols.get(0).getTypeName()+symbols.get(2).getTypeName()+symbols.get(4).getTypeName();
            names.add(s);
        }
        repeatCount(names);

        resultList.sort(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                int type1 = o1.getSymbolsList().get(0).getMainAttr().hashCode() - o2.getSymbolsList().get(0).getMainAttr().hashCode();
                int type2 = o1.getSymbolsList().get(1).getMainAttr().hashCode() - o2.getSymbolsList().get(1).getMainAttr().hashCode();
                int type3 = o1.getSymbolsList().get(2).getMainAttr().hashCode() - o2.getSymbolsList().get(2).getMainAttr().hashCode();
                int type4 = o1.getSymbolsList().get(3).getMainAttr().hashCode() - o2.getSymbolsList().get(3).getMainAttr().hashCode();
                int type5 = o1.getSymbolsList().get(4).getMainAttr().hashCode() - o2.getSymbolsList().get(4).getMainAttr().hashCode();
                int type6 = o1.getSymbolsList().get(5).getMainAttr().hashCode() - o2.getSymbolsList().get(5).getMainAttr().hashCode();
                return type1 + type2 + type3 + type4 + type5 + type6;
            }
        });
        names = new ArrayList<>();
        for (Result result : resultList){
            List<Symbols> symbols = result.getSymbolsList();
            String s = symbols.get(0).getMainAttrName()+","+symbols.get(1).getMainAttrName()+","+symbols.get(2).getMainAttrName()+",";
            s += symbols.get(3).getMainAttrName()+","+symbols.get(4).getMainAttrName()+","+symbols.get(5).getMainAttrName();
            names.add(s);
        }
        repeatCount(names);



        resultList.sort(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                int type1 = o1.getSymbolsList().get(0).getAttr1().hashCode() - o2.getSymbolsList().get(0).getAttr1().hashCode();
                int type2 = o1.getSymbolsList().get(0).getAttr2().hashCode() - o2.getSymbolsList().get(0).getAttr2().hashCode();
                int type3 = o1.getSymbolsList().get(0).getAttr3().hashCode() - o2.getSymbolsList().get(0).getAttr3().hashCode();
                int type4 = o1.getSymbolsList().get(0).getAttr4().hashCode() - o2.getSymbolsList().get(0).getAttr4().hashCode();
                return type1 + type2 + type3 + type4;
            }
        });
        names = new ArrayList<>();
        for (Result result : resultList){
            List<Symbols> symbols = result.getSymbolsList();
            String s = symbols.get(0).getAttrName1()+","+symbols.get(0).getAttrName2()+","+symbols.get(0).getAttrName3()+","+symbols.get(0).getAttrName4();
            names.add(s);
        }
        repeatCount(names);
    }


    public static void repeatCount(List<String> list){
        boolean sortAsc = true;
        Map<String,Integer> map = new HashMap<>();
        for(String str : list){
            Integer i = 1; //定义一个计数器，用来记录重复数据的个数
            if(map.get(str) != null){
                i=map.get(str)+1;
            }
            map.put(str,i);
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        if(sortAsc){
            map.entrySet().stream()
               .sorted(Map.Entry.comparingByValue())
               .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        }else{
            map.entrySet().stream()
               .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
               .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        }

        for(Map.Entry<String,Integer> entry : sortedMap.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    public static int autoNumber = 0;
    public static int getAutoNumber(){
        return autoNumber++;
    }

    public static List<List<Integer>> getNoRepeatList2(List<Generals> nmList, int i, List<AppointGenerals> appointGeneralsList) {
        List<Integer> numbers = new ArrayList<>();
        for (Generals generals : nmList){
            numbers.add(Integer.valueOf(generals.getId()));
        }
        List<Integer> appointList = new ArrayList<>();
        for (AppointGenerals appointGenerals : appointGeneralsList){
            for (Generals g : nmList){
                if(g.getOriginalName().equals(appointGenerals.getName())){
                    appointList.add(Integer.valueOf(g.getId()));
                }
            }
        }

        List<List<Integer>> lists = getResult(numbers,5,appointList);
        /*for (List<Integer> list : lists){
            System.out.println(list.toString());
        }*/
        return lists;
    }
}
