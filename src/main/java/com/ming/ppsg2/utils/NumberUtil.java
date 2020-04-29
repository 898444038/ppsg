package com.ming.ppsg2.utils;


import com.ming.ppsg2.entity.AppointGenerals;
import com.ming.ppsg2.entity.AppointSymbols;
import com.ming.ppsg2.entity.Compose;
import com.ming.ppsg2.entity.Generals;
import com.ming.ppsg2.entity.Result;
import com.ming.ppsg2.entity.Symbols;
import com.ming.ppsg2.enums.GeneralsEnum;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {

    //copy对象
    public static List<Compose> getNoRepeatList(Map<String,String> generalsMapSort,List<Generals> data, int size, List<AppointGenerals> appointGeneralsList) {
        clear();
        long t1 = System.currentTimeMillis();
        List<List<Generals>> glongList = getList(data,size,appointGeneralsList);
        System.out.println("排列组合时间："+(System.currentTimeMillis()-t1)+"ms");

        final int threadNum = 1;//子线程数
        List<List<List<Generals>>> splitList = split(glongList,threadNum);
        CyclicBarrier cb0 = new CyclicBarrier(threadNum + 1);//注意：2个子线程 + 1个主线程
        List<Compose> composeList = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            new Thread(new MyRunable0(cb0, i, splitList.get(i),composeList, generalsMapSort)).start();
        }

        try {
            cb0.await();
            System.out.println("-----------\n所有thread执行完成！");
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<List<Compose>> splitList2 = splitCompose(composeList,threadNum);
        CyclicBarrier cb = new CyclicBarrier(threadNum + 1);//注意：10个子线程 + 1个主线程
        for (int i = 0; i < threadNum; i++) {
            new Thread(new MyRunable(cb, 0,splitList2.get(i))).start();
        }
        try {
            cb.await();
            System.out.println("-----------\n所有thread执行完成！");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return noRepeatComposeList;
    }

    public static List<List<List<Generals>>> split(List<List<Generals>> glongList,Integer num){
        int finalSize = glongList.size();
        int splitSize = finalSize/num;
        return ListUtils.partition(glongList,splitSize);
    }

    public static List<List<Compose>> splitCompose(List<Compose> composeList,Integer num){
        int finalSize = composeList.size();
        int splitSize = finalSize/num;
        return ListUtils.partition(composeList,splitSize);
    }

    static class MyRunable0 implements Runnable {
        CyclicBarrier _cb;
        int _i = 0;
        List<List<Generals>> glongList = null;
        List<Compose> composeList = null;
        Map<String,String> generalsMapSort = null;

        public MyRunable0(CyclicBarrier cb, int i,List<List<Generals>> glongList,List<Compose> composeList,Map<String,String> generalsMapSort) {
            this._cb = cb;
            this._i = i;
            this.glongList = glongList;
            this.composeList = composeList;
            this.generalsMapSort = generalsMapSort;
        }

        @Override
        public void run() {
            StringBuffer ids = null;
            Integer grilCode = GeneralsEnum.Gender.gril.getCode();
            try {
                Iterator<List<Generals>> iterator = glongList.iterator();
                List<Generals> gList = null;
                int finalCount = glongList.size();
                int count = 0;
                while (iterator.hasNext()) {
                    gList = iterator.next();

                    ids = new StringBuffer();
                    ids.append(gList.get(0).getId());
                    ids.append(gList.get(1).getId());
                    ids.append(gList.get(2).getId());
                    ids.append(gList.get(3).getId());
                    ids.append(gList.get(4).getId());

                    boolean isGril = gList.get(0).getGender().equals(grilCode)
                            && gList.get(1).getGender().equals(grilCode)
                            && gList.get(2).getGender().equals(grilCode)
                            && gList.get(3).getGender().equals(grilCode)
                            && gList.get(4).getGender().equals(grilCode);
                    if(generalsMapSort.get(ids.toString())!=null && !isGril){
                        //iterator.remove();
                    }else{
                        composeList.add(new Compose(ids.toString(),isGril,gList));
                    }
                    ids = null;
                    count++;
                    glongList = null;
                    System.out.println(_i+":"+count);
                }
                ids = null;
                System.out.println("thread " + _i + " done，正在等候其它线程完成...");
                _cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class MyRunable implements Runnable {
        CyclicBarrier _cb;
        int _i = 0;
        List<Compose> composeList = null;

        public MyRunable(CyclicBarrier cb, int i,List<Compose> composeList) {
            this._cb = cb;
            this._i = i;
            this.composeList = composeList;
        }

        @Override
        public void run() {
            try {
                int count = 0;
                List<Generals> generalsList = null;
                for(Compose compose : composeList){
                    generalsList = compose.getList();
                    List<Generals> copyList = new ArrayList<>();
                    boolean no = true;
                    a:for(Generals generals : generalsList){
                        b:for(Generals generals2 : generalsList){
                            if(generals.getCode().equals(generals2.getCode()) && !generals.getId().equals(generals2.getId())){
                                no = false;
                                break a;
                            }
                        }
                        Generals copy = new Generals();
                        BeanUtils.copyProperties(generals,copy);
                        copyList.add(copy);
                    }
                    if(!no){
                        continue;
                    }
                    count++;
                    System.out.println(_i+":"+count);
                    //noRepeatList.add(copyList);
                    noRepeatComposeList.add(new Compose(compose.getId(),compose.isGril(),copyList));
                }
                System.out.println("thread " + _i + " done，正在等候其它线程完成...");
                _cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void clear(){
        longList = new ArrayList<>();
        noRepeatList = new ArrayList<>();
        noRepeatComposeList = new ArrayList<>();
    }

    public static List<List<Generals>> getList(List<Generals> data, int size, List<AppointGenerals> appointGeneralsList) {
        longList = new ArrayList<>();
        combinations(new ArrayList<>(),data, size,appointGeneralsList);
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
    private static List<Compose> noRepeatComposeList = new ArrayList<>();
    public static void combinations(List<Generals> selector,List<Generals> data,int n,List<AppointGenerals> appointGeneralsList) {
        if(n == 0) {
            int size = appointGeneralsList.size();
            int total = 0;
            for (Generals generals : selector) {
                for (AppointGenerals appointGenerals : appointGeneralsList) {
                    if(generals.getName().equalsIgnoreCase(appointGenerals.getName())){
                        total++;
                    }
                }
            }
            if(size == total || appointGeneralsList.isEmpty()){
                List<Generals> list = new ArrayList<>();
                for (Generals generals : selector) {
                    list.add(generals);
                }
                longList.add(list);
            }
            return;
        }
        if(data.isEmpty()) {
            return;
        }
        //选择第一个元素,将元素放入集合
        Generals generals0 = data.get(0);
        selector.add(generals0);
        combinations(selector,data.subList(1, data.size()),n - 1,appointGeneralsList); //从第二个元素开始选择，再选择两个
        //不选择第一个元素
        selector.remove(selector.size() -1 );
        combinations(selector,data.subList(1, data.size()), n,appointGeneralsList); //从第二个元素开始选择，选择两个
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






    public static List<List<Integer>> getResult(List<Integer> data, int size, List<AppointSymbols> appointSymbolsList) {
        intList = new ArrayList<>();
        combinations2(new ArrayList<>(),data, size,appointSymbolsList);
        return intList;
    }


    /**
     * @param selector	选完的集合 初始化为空
     * @param list		待选集合
     * @param n			选出的数量
     */
    private static List<List<Integer>> intList = new ArrayList<>();
    public static void combinations2(List<Integer> selector,List<Integer> data,int n,List<AppointSymbols> appointSymbolsList) {
        if(n == 0) {
            int size = appointSymbolsList.size();
            int total = 0;
            for (Integer integer : selector) {
                for (AppointSymbols appointSymbols : appointSymbolsList) {
                    if(integer.equals(appointSymbols.getCode())){
                        total++;
                    }
                }
            }
            if(size == total || appointSymbolsList.isEmpty()) {
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
        combinations2(selector,data.subList(1, data.size()),n - 1,appointSymbolsList); //从第二个元素开始选择，再选择两个
        //不选择第一个元素
        selector.remove(selector.size() -1 );
        combinations2(selector,data.subList(1, data.size()), n,appointSymbolsList); //从第二个元素开始选择，选择两个
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
}
