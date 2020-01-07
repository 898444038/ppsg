package com.ming.ppsg2.utils;


import com.ming.ppsg2.entity.Generals;
import com.ming.ppsg2.entity.Result;
import com.ming.ppsg2.entity.Symbols;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NumberUtil {

    //copy对象
    public static List<List<Generals>> getNoRepeatList(List<Generals> data, int size) {

        List<List<Generals>> glongList = getList(data,size);
        for(List<Generals> generalsList : glongList){
            List<Generals> copyList = new ArrayList<>();
            for(Generals generals : generalsList){
                Generals copy = new Generals();
                BeanUtils.copyProperties(generals,copy);
                copyList.add(copy);
            }
            noRepeatList.add(copyList);
        }
        return noRepeatList;
    }

    public static void clear(){
        longList = new ArrayList<>();
        noRepeatList = new ArrayList<>();
    }

    public static List<List<Generals>> getList(List<Generals> data, int size) {
        longList = new ArrayList<>();
        combinations(new ArrayList<>(),data, size);
        /*for(List<Generals> list : longList){
            System.out.println(list.toString());
        }*/
        System.out.println(longList.size());
        return longList;
    }

    /**
     * @param selector	选完的集合 初始化为空
     * @param list		待选集合
     * @param n			选出的数量
     */
    private static List<List<Generals>> longList = new ArrayList<>();
    private static List<List<Generals>> noRepeatList = new ArrayList<>();
    public static void combinations(List<Generals> selector,List<Generals> data,int n) {
        if(n == 0) {
            List<Generals> list = new ArrayList<>();
            for (Generals generals : selector) {
                //Generals copy = new Generals();
                //BeanUtils.copyProperties(generals,copy);
                list.add(generals);
            }
            longList.add(list);
            return;
        }
        if(data.isEmpty()) {
            return;
        }
        //选择第一个元素,将元素放入集合
        Generals generals0 = data.get(0);
        selector.add(generals0);
        combinations(selector,data.subList(1, data.size()),n - 1); //从第二个元素开始选择，再选择两个
        //不选择第一个元素
        selector.remove(selector.size() -1 );
        combinations(selector,data.subList(1, data.size()), n); //从第二个元素开始选择，选择两个
    }



    public static LinkedList<String[]> recursionSub ( LinkedList<String[]> list, int count, String[][] array, int ind,int start, int... indexs ){
        start++;
        if (start > count - 1){
            return null;
        }
        if (start == 0){
            indexs = new int[array.length];
        }
        for ( indexs[start] = 0; indexs[start] < array[start].length; indexs[start]++ ){
            recursionSub (list, count, array, 0, start, indexs);
            if (start == count - 1){
                String[] temp = new String[count];
                for ( int i = count - 1; i >= 0; i-- ){
                    temp[start - i] = array[start - i][indexs[start - i]];
                }
                list.add (temp);
            }
        }
        return list;
    }

    public static LinkedList<int[]> recursionSubInteger( LinkedList<int[]> list, int count, int[][] array, int ind,int start, int... indexs ){
        start++;
        if (start > count - 1){
            return null;
        }
        if (start == 0){
            indexs = new int[array.length];
        }
        for ( indexs[start] = 0; indexs[start] < array[start].length; indexs[start]++ ){
            recursionSubInteger(list, count, array, 0, start, indexs);
            if (start == count - 1){
                int[] temp = new int[count];
                for ( int i = count - 1; i >= 0; i-- ){
                    temp[start - i] = array[start - i][indexs[start - i]];
                }
                list.add (temp);
            }
        }
        return list;
    }

    public static LinkedList<Object[]> recursionSubObject ( LinkedList<Object[]> list, int count, Object[][] array, int ind,int start, int... indexs ){
        start++;
        if (start > count - 1){
            return null;
        }
        if (start == 0){
            indexs = new int[array.length];
        }
        for ( indexs[start] = 0; indexs[start] < array[start].length; indexs[start]++ ){
            recursionSubObject(list, count, array, 0, start, indexs);
            if (start == count - 1){
                Object[] temp = new Object[count];
                for ( int i = count - 1; i >= 0; i-- ){
                    temp[start - i] = array[start - i][indexs[start - i]];
                }
                list.add (temp);
            }
        }
        return list;
    }

    public static void main ( String[] args ){
        String[] s1 = { "1", "2", "3", "4", "5", "6" };
        String[] s2 = { "1", "2", "3", "4", "5", "6" };
        String[] s3 = { "1", "2", "3", "4", "5", "6" };
        String[] s4 = { "1", "2", "3", "4", "5", "6" };
        String[] s5 = { "1", "2", "3", "4", "5", "6" };
        //String[] s3 = { "x", "y", "z"  };
        //String[][] temp = { s1, s2, s3 };
        String[][] temp = { s1, s2, s3, s4, s5 };

        LinkedList<String[]> list = new LinkedList<String[]>();
        recursionSub (list, temp.length, temp, 0, -1);
        for ( int i = 0; i < list.size (); i++ ){
            System.out.println (Arrays.toString (list.get (i)).replaceAll ("[\\[\\]\\,\\s]", ""));
        }
    }






    public static List<List<Integer>> getResult(List<Integer> data, int size) {
        intList = new ArrayList<>();
        combinations2(new ArrayList<>(),data, size);
        /*for(List<Integer> list : intList){
            System.out.println(list.toString());
        }
        System.out.println(intList.size());*/
        return intList;
    }


    /**
     * @param selector	选完的集合 初始化为空
     * @param list		待选集合
     * @param n			选出的数量
     */
    private static List<List<Integer>> intList = new ArrayList<>();
    public static void combinations2(List<Integer> selector,List<Integer> data,int n) {
        if(n == 0) {
            List<Integer> list = new ArrayList<>();
            for (Integer i : selector) {
                list.add(i);
            }
            intList.add(list);
            return;
        }
        if(data.isEmpty()) {
            return;
        }
        //选择第一个元素,将元素放入集合
        selector.add(data.get(0));
        combinations2(selector,data.subList(1, data.size()),n - 1); //从第二个元素开始选择，再选择两个
        //不选择第一个元素
        selector.remove(selector.size() -1 );
        combinations2(selector,data.subList(1, data.size()), n); //从第二个元素开始选择，选择两个
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
}
