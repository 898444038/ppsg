package com.ming.ppsg2.main;


import com.ming.ppsg2.entity.*;
import com.ming.ppsg2.enums.GeneralsEnum;
import com.ming.ppsg2.utils.DestinyData;
import com.ming.ppsg2.utils.ExcelReaderUtil;
import com.ming.ppsg2.utils.GeneralsUtil;
import com.ming.ppsg2.utils.NumberUtil;
import com.ming.ppsg2.utils.RWExcel;
import com.ming.ppsg2.utils.jxls.JxlsUtil;
import org.springframework.beans.BeanUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String top = "因缺少部分卡片属性数据，以下排名中上阵武将及随从不包含：征南曹仁、七星诸葛亮、暴怒张飞、桓侯张飞、讨虏黄忠、狂骨魏延、顾曲周瑜、修罗吕布\n" +
                "安卓1服熾陽✵天下出品,如发现错误之处，欢迎指正！啪啪三国技术交流群：913083053\n" +
                "\n" +
                "\n";
        top+= "";
        String advert = "";//广告
        String fileRemark = "(模拟)";
        //计算：992/658/1895
        //实际：988/654/1947

        List<AppointGenerals> appointGeneralsList = new ArrayList<>();
        appointGeneralsList.add(new AppointGenerals("**吕布"));
        //appointGeneralsList.add(new AppointGenerals("神武刘备"));
        //appointGeneralsList.add(new AppointGenerals("陨星庞统"));
        //appointGeneralsList.add(new AppointGenerals("砺战赵云"));
        //appointGeneralsList.add(new AppointGenerals("龙驹马云禄"));

        List<AppointExcludeGenerals> excludeGeneralsList = new ArrayList<>();
        //excludeGeneralsList.add(new AppointExcludeGenerals("砺战赵云",1));
        //excludeGeneralsList.add(new AppointExcludeGenerals("御甲张辽",0));

        List<AppointSymbols> appointSymbolsList = new ArrayList<>();
        //appointSymbolsList.add(new AppointSymbols(GeneralsEnum.SymbolsType.huoFeng.getCode(),GeneralsEnum.SymbolsType.huoFeng.getName()));
        //appointSymbolsList.add(new AppointSymbols(GeneralsEnum.SymbolsType.qiongQi.getCode(),GeneralsEnum.SymbolsType.qiongQi.getName()));
        //appointSymbolsList.add(new AppointSymbols(GeneralsEnum.SymbolsType.yaCi.getCode(),GeneralsEnum.SymbolsType.yaCi.getName()));

        boolean isHuanHua = false;//随从是否有幻化
        long t1 = System.currentTimeMillis();
        xzl(top,advert,fileRemark,appointGeneralsList,excludeGeneralsList,appointSymbolsList,isHuanHua);
        long t2 = System.currentTimeMillis();
        DecimalFormat df=new DecimalFormat("0.000");
        System.out.println("共耗时："+df.format((float)(t2-t1)/1000)+"s");
    }

    public static void xzl(String top,String advert,String fileRemark,
                           List<AppointGenerals> appointGeneralsList,
                           List<AppointExcludeGenerals> excludeGeneralsList,
                           List<AppointSymbols> appointSymbolsList,
                           boolean isHuanHua){
        List<Generals> nimingList = new ArrayList<>();//全部
        List<Generals> nimingList2 = new ArrayList<>();//指定武将
        List<Generals> nimingList3 = new ArrayList<>();//非指定武将
        List<Generals> generalsAll = new ArrayList<>();
        Map<String,Destiny> destinyMap = new HashMap<>();//命格材料

        List<List<String>> lists = ExcelReaderUtil.readExcel("/excel/data_temp.xlsx");
        List<List> lists2 = new RWExcel("/excel/data_temp.xlsx",0).ReadExcel();
        //排除武将
        Iterator<List<String>> iterator = lists.iterator();
        while (iterator.hasNext()) {
            List<String> list = iterator.next();
            for (AppointExcludeGenerals excludeGenerals : excludeGeneralsList) {
                if (excludeGenerals.getName().equalsIgnoreCase(list.get(0)) && excludeGenerals.getMaxSize()==0) {
                    iterator.remove();
                }
            }
        }
        System.out.println("获取初始数据："+lists.size()+"条");

        Map<String,String> generalsMapSort = new LinkedHashMap<>();
        Properties prop = new Properties();
        try {
            InputStream inStream = new FileInputStream("data.properties");
            prop.load(inStream);     ///加载属性列表
            Enumeration enu = prop.keys();
            while (enu.hasMoreElements()) {
                Object obj = enu.nextElement();
                Object objv = prop.get(obj);
                generalsMapSort.put(obj.toString(), objv.toString());
            }
            inStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("获取排除数据："+generalsMapSort.size()+"条");

        lists.remove(0);
        for (List<String> list : lists) {
            if(list.size()<30 || "".equals(list.get(14))){
                continue;
            }
            String name = list.get(0);
            Integer code = null;
            for(GeneralsEnum.GeneralsCode generalsCode : GeneralsEnum.GeneralsCode.values()){
                if(generalsCode.getName().equals(list.get(1))){
                    code = generalsCode.getCode();
                }
            }
            Integer level = Integer.valueOf(list.get(3));
            Integer force = Integer.valueOf(list.get(4));
            Integer intellect = Integer.valueOf(list.get(5));
            Integer troops = Integer.valueOf(list.get(6));
            Integer gender = GeneralsEnum.Gender.boy.getCode();
            for(GeneralsEnum.Gender genders : GeneralsEnum.Gender.values()){
                if(genders.getName().equals(list.get(7))){
                    gender = genders.getCode();
                }
            }

            Integer generalsType = GeneralsEnum.GeneralsType.GeneralsType_6.getCode();
            for(GeneralsEnum.GeneralsType generalsTypes : GeneralsEnum.GeneralsType.values()){
                if(generalsTypes.getName().equals(list.get(8))){
                    generalsType = generalsTypes.getCode();
                }
            }

            Integer arms = GeneralsEnum.Arms.gun.getCode();
            for(GeneralsEnum.Arms arms1 : GeneralsEnum.Arms.values()){
                if(arms1.getName().equals(list.get(9))){
                    arms = arms1.getCode();
                }
            }

            Integer country = GeneralsEnum.Country.qun.getCode();
            for(GeneralsEnum.Country countrys : GeneralsEnum.Country.values()){
                if(countrys.getName().equals(list.get(2))){
                    country = countrys.getCode();
                }
            }


            Boolean isEntourage = false;
            String[] entourageArr = list.get(11).split(",");
            List<Integer> entourageList = new ArrayList<>();
            for(String entourage : entourageArr){
                for(GeneralsEnum.GeneralsCode generalsCode : GeneralsEnum.GeneralsCode.values()){
                    if(entourage.equals(generalsCode.getName())){
                        entourageList.add(generalsCode.getCode());
                        break;
                    }
                }
            }

            Integer[] entourages = new Integer[entourageList.size()];
            entourageList.toArray(entourages);

            Integer warDevicesCode = null;
            for(GeneralsEnum.WarDevice warDevice : GeneralsEnum.WarDevice.values()){
                if(warDevice.getName().equals(list.get(10))){
                    warDevicesCode = warDevice.getCode();
                }
            }
            Integer[] warDevices = {warDevicesCode};


            Integer armsType1 = null;
            Integer armsType2 = null;
            for(GeneralsEnum.ArmsType armsType : GeneralsEnum.ArmsType.values()){
                if(armsType.getName().equals(list.get(12))){
                    armsType1 = armsType.getCode();
                }
                if(armsType.getName().equals(list.get(13))){
                    armsType2 = armsType.getCode();
                }
            }

            Integer book1 = null;
            Integer book11 = null;
            Integer book2 = null;
            Integer book22 = null;
            Integer book3 = null;
            Integer book33 = null;
            Integer book4 = null;
            Integer book44 = null;
            Integer book5 = null;
            Integer book55 = null;
            for(GeneralsEnum.ArmsBook armsBook : GeneralsEnum.ArmsBook.values()){
                if(armsBook.getName().equals(list.get(14))){book1 = armsBook.getCode();}
                if(armsBook.getName().equals(list.get(15))){book11 = armsBook.getCode();}
                if(armsBook.getName().equals(list.get(16))){book2 = armsBook.getCode();}
                if(armsBook.getName().equals(list.get(17))){book22 = armsBook.getCode();}
                if(armsBook.getName().equals(list.get(18))){book3 = armsBook.getCode();}
                if(armsBook.getName().equals(list.get(19))){book33 = armsBook.getCode();}
                if(armsBook.getName().equals(list.get(20))){book4 = armsBook.getCode();}
                if(armsBook.getName().equals(list.get(21))){book44 = armsBook.getCode();}
                if(armsBook.getName().equals(list.get(22))){book5 = armsBook.getCode();}
                if(armsBook.getName().equals(list.get(23))){book55 = armsBook.getCode();}

            }

            Integer[][] armsBooks = {
                    {armsType1,armsType2},
                    {book1,book11},
                    {book2,book22},
                    {book3,book33},
                    {book4,book44},
                    {book5,book55},
            };

            String skin = list.get(24);
            Integer skinCode = null;
            for(GeneralsEnum.Skin skin1 : GeneralsEnum.Skin.values()){
                if(skin1.getName().equals(skin)){
                    skinCode = skin1.getCode();
                }
            }

            Integer disobeyCode = null;
            for(GeneralsEnum.Destiny destiny : GeneralsEnum.Destiny.values()){
                if(destiny.getName().equals(list.get(29))){
                    disobeyCode = destiny.getCode();
                }
            }

            Object[] destinys = {Integer.valueOf(list.get(25)),Integer.valueOf(list.get(26)),Integer.valueOf(list.get(27)),Boolean.valueOf(list.get(28)),disobeyCode,null,null,null};

            Generals generals = DestinyData.getGenerals(name,code,level,force,intellect,troops,gender,generalsType,arms,country,isEntourage,entourages,warDevices,armsBooks,destinys,skinCode);


            GeneralsUtil.getMaxLevel(generals);//基础满级三维
            GeneralsUtil.getScience(generals);//科技三维
            GeneralsUtil.getHolyStone(generals);//四圣石三维
            GeneralsUtil.getWarDevice(generals);//战器三维
            GeneralsUtil.getWarDevice2(generals);//特殊战器三维
            GeneralsUtil.getArmsBook(generals);//兵种兵书三维
            GeneralsUtil.getWillSoul(generals);//将魂三维
            GeneralsUtil.getBattleArray(generals);//战阵三维
            GeneralsUtil.getDestiny(generals);//命格三维
            GeneralsUtil.getSkin(generals);//幻化三维
            generals.setWarpath(new Warpath());

            if(name.endsWith("_限")){
                generals.setLimit(true);
            }else{
                generals.setLimit(false);
            }
            generals.setId(UUID.randomUUID().toString().replace("-", ""));

            if(1 == disobeyCode || 2 == disobeyCode || 3 == disobeyCode){
                Generals copy = new Generals();

                ArmsBook copyBook = new ArmsBook();
                BeanUtils.copyProperties(generals.getArmsBook(),copyBook);
                copy.setArmsBook(copyBook);

                Destiny copyDestiny = new Destiny();

                BeanUtils.copyProperties(generals.getDestiny(),copyDestiny);
                copy.setDestiny(copyDestiny);

                BeanUtils.copyProperties(generals,copy);

                if(!appointGeneralsList.isEmpty()){
                    boolean flag = true;
                    for(AppointGenerals appointGenerals : appointGeneralsList){
                        if(copy.getName().equals(appointGenerals.getName())){
                            nimingList2.add(copy);
                            flag = false;
                        }
                    }
                    if (flag){
                        nimingList3.add(copy);
                    }
                }else{
                    nimingList3.add(copy);
                }

                destinyMap.put(generals.getName(),copyDestiny);
            }

            ArmsBook copyBook = new ArmsBook();
            BeanUtils.copyProperties(generals.getArmsBook(),copyBook);
            generals.setArmsBook(copyBook);

            Destiny copyDestiny = new Destiny();
            BeanUtils.copyProperties(generals.getDestiny(),copyDestiny);
            generals.setDestiny(copyDestiny);
            generalsAll.add(generals);
        }

        nimingList.addAll(nimingList2);
        nimingList.addAll(nimingList3);

        //去除重复卡，保留异化卡
        System.out.println("上阵武将："+nimingList.size());
        List<Generals> gList1 = new ArrayList<>();
        for(int i=0;i<nimingList.size();i++){
            for(int j=0;j<nimingList.size();j++){
                if(nimingList.get(i).getName().contains(nimingList.get(j).getName())){
                    if(nimingList.get(i).getLimit() != nimingList.get(j).getLimit()){
                        if(nimingList.get(i).getName().endsWith("_限")){
                            gList1.add(nimingList.get(j));
                        }
                    }
                }
            }
        }
        for(int i=0;i<gList1.size();i++){
            nimingList.remove(gList1.get(i));
        }

        System.out.println("随从武将："+generalsAll.size());
        List<Generals> gList2 = new ArrayList<>();
        for(int i=0;i<generalsAll.size();i++){
            for(int j=0;j<generalsAll.size();j++){
                if(generalsAll.get(i).getName().contains(generalsAll.get(j).getName())){
                    if(generalsAll.get(i).getLimit() != generalsAll.get(j).getLimit()){
                        if(generalsAll.get(i).getName().endsWith("_限")){
                            gList2.add(generalsAll.get(j));
                        }
                    }
                }
            }
        }
        for(int i=0;i<gList2.size();i++){
            generalsAll.remove(gList2.get(i));
        }

        // 计算随从
        Map<Integer,List<Generals>> map = null;
        for(Generals generals : nimingList){
            map = GeneralsUtil.getEntourage(generals,generalsAll,isHuanHua);//随从三维
        }

        //随从榜
        List<Generals> allEntourageList = new ArrayList<>();
        for(Generals g : generalsAll){
            Generals copy = new Generals();
            BeanUtils.copyProperties(g,copy);
            allEntourageList.add(g);
        }
        Map<Integer,List<Generals>> allEntourage = GeneralsUtil.getAllEntourage(allEntourageList,isHuanHua);//随从三维

        //最佳随从表
        List<Generals> optimumEntourageList = new ArrayList<>();
        for(Generals g : generalsAll){
            Generals copy = new Generals();
            BeanUtils.copyProperties(g,copy);
            optimumEntourageList.add(g);
        }
        List<Generals> optimumEntourage = GeneralsUtil.getOptimumEntourage(nimingList,optimumEntourageList,isHuanHua);

        long t1 = System.currentTimeMillis();
        List<List<Generals>> all = NumberUtil.getNoRepeatList(nimingList,5,appointGeneralsList);
        System.out.println("上阵武将组合个数："+all.size());
        List<Result> resultList = new ArrayList<>();
        List<Result> resultList2 = new ArrayList<>();
        List<Result> grilResultList = new ArrayList<>();

        int count = 0;
        int finalCount = all.size();
        Integer grilCode = GeneralsEnum.Gender.gril.getCode();

        for(List<Generals> generalsList : all){
            count++;
            StringBuffer names = new StringBuffer();
            names.append(generalsList.get(0).getName()+"_");
            names.append(generalsList.get(1).getName()+"_");
            names.append(generalsList.get(2).getName()+"_");
            names.append(generalsList.get(3).getName()+"_");
            names.append(generalsList.get(4).getName());

            boolean isGril = generalsList.get(0).getGender().equals(grilCode)
                    && generalsList.get(1).getGender().equals(grilCode)
                    && generalsList.get(2).getGender().equals(grilCode)
                    && generalsList.get(3).getGender().equals(grilCode)
                    && generalsList.get(4).getGender().equals(grilCode);

            if(generalsMapSort.get(names.toString())!=null && !isGril){
                continue;
            }

            //排除主阵容重复武将
            boolean no = true;
            a:for(Generals generals1 : generalsList){
                b:for(Generals generals2 : generalsList){
                    if(generals1.getCode().equals(generals2.getCode()) && !generals1.getId().equals(generals2.getId())){
                        no = false;
                        break a;
                    }
                }
            }
            if(!no){
                continue;
            }

            for(Generals generals : generalsList){
                generals.setWarpath(new Warpath());
            }

            //极限兵符
            List<Symbols> symbolsList = GeneralsUtil.getSymbols(generalsList,appointSymbolsList);


            ThreeDimensional td = GeneralsUtil.countSymbols(generalsList,symbolsList);
            //战意三维
            GeneralsUtil.getWarpath(generalsList);

            // 总战力 = 武将1战力 + 武将2战力 + 武将3战力 + 武将4战力 + 武将5战力 + 工坊战力（10152）
            // 武将战力 =（总武力+总智力+总兵力）*2+ 命格被动战力 + 战器被动战力
            Integer allTotalSword = null;
            Integer allTotalSword2 = null;
            if(excludeGeneralsList.size() > 0){
                allTotalSword = GeneralsUtil.getAllTotalSword3(generalsList,excludeGeneralsList);
                allTotalSword2 = GeneralsUtil.getAllTotalSword4(generalsList,excludeGeneralsList);
            }else{
                allTotalSword = GeneralsUtil.getAllTotalSword(generalsList);
                allTotalSword2 = GeneralsUtil.getAllTotalSword2(generalsList);
            }

            BigDecimal b1 = new BigDecimal(Double.toString(count));
            BigDecimal b2 = new BigDecimal(Double.toString(finalCount));
            Double d = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue()*100;
            System.out.println(count+" / "+finalCount + "  " + (d.intValue())+"%");

            //女队
            if(
                generalsList.get(0).getGender().toString().equals(grilCode.toString()) &&
                generalsList.get(1).getGender().toString().equals(grilCode.toString()) &&
                generalsList.get(2).getGender().toString().equals(grilCode.toString()) &&
                generalsList.get(3).getGender().toString().equals(grilCode.toString()) &&
                generalsList.get(4).getGender().toString().equals(grilCode.toString())
            ){
                Result result = GeneralsUtil.getResult(generalsList,symbolsList,allTotalSword,allTotalSword2);
                if(allTotalSword2 > 355000){
                    grilResultList.add(result);
                }
            }

            int zhanli = 375000;
            int flag = 0;
            //跳过战力低于zhanli的
            if(allTotalSword<zhanli && appointGeneralsList.isEmpty()){
                if(allTotalSword2>zhanli){
                    flag = 1;
                }else{
                    generalsMapSort.put(names.toString(),allTotalSword+","+allTotalSword2);
                    continue;
                }
            }

            Result result = GeneralsUtil.getResult(generalsList,symbolsList,allTotalSword,allTotalSword2);
            if(flag==1){
                resultList2.add(result);
            }else{
                resultList.add(result);
                resultList2.add(result);
            }
        }

        //结果排序
        resultList.sort(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return o2.getTotal() - o1.getTotal(); //降序
            }
        });
        resultList2.sort(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return o2.getTotal() - o1.getTotal(); //降序
            }
        });
        grilResultList.sort(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return o2.getTotal() - o1.getTotal(); //降序
            }
        });

        //结果排名
        int i = 1;
        for(Result result : resultList){
            result.setRank(i++);
        }
        int i2 = 1;
        for(Result result : resultList2){
            result.setRank(i2++);
        }
        int i3 = 1;
        for(Result result : grilResultList){
            result.setRank(i3++);
        }


        long t2 = System.currentTimeMillis();
        System.out.println("用时："+(t2-t1)+"ms , 帝王八阵容数量："+resultList.size());
        System.out.println("---------end---------");

        //随从榜
        Map<String,Object> model = new HashMap<>();
        List<Generals> forceTopList = allEntourage.get(GeneralsEnum.ThreeCircles.Force.getCode());
        List<Generals> intellectTopList = allEntourage.get(GeneralsEnum.ThreeCircles.Intellect.getCode());
        List<Generals> troopsTopList = allEntourage.get(GeneralsEnum.ThreeCircles.Troops.getCode());
        Double rate = 1.25;//联协倍率
        Integer add = 100;//作为随从+100属性
        for(Generals generals : forceTopList){
            Integer g = (generals.getTotalForce()/2+add);
            Double d = g * rate;
            generals.setFn(g);
            generals.setF(d.intValue());
        }
        for(Generals generals : intellectTopList){
            Integer g = (generals.getTotalIntellect()/2+add);
            Double d = g * rate;
            generals.setIn(g);
            generals.setI(d.intValue());
        }
        for(Generals generals : troopsTopList){
            Integer g = (generals.getTotalTroops()/2+add);
            Double d = g * rate;
            generals.setTn(g);
            generals.setT(d.intValue());
        }

        model.put("top",top);
        model.put("advert",advert);
        //model.put("simplifyList1",GeneralsUtil.getSimplifyList(resultList));//简表
        model.put("simplifyList2",GeneralsUtil.getSimplifyList(resultList2));//简表（特殊战器）
        resultList = null;
        //model.put("list",resultList);//虚战力表

        //model.put("list2",GeneralsUtil.getExcludeList(resultList2,390000));//虚战力表（特殊战器）
        if(resultList2.size()>200){
            model.put("list2",resultList2.subList(0,100));//虚战力表（特殊战器）
        }else{
            model.put("list2",resultList2);//虚战力表（特殊战器）
        }
        if(grilResultList.size()>200) {
            model.put("grilList", grilResultList.subList(0, 100));//虚战力表（女队）
        }else{
            model.put("grilList", grilResultList);
        }
        model.put("forceTopList",forceTopList);
        model.put("intellectTopList",intellectTopList);
        model.put("troopsTopList",troopsTopList);
        model.put("optimumEntourageList",optimumEntourage);//最佳随从表
        model.put("destinyMap",destinyMap);

        if(excludeGeneralsList.isEmpty()){
            /*try{
                //保存属性到data.properties文件
                FileOutputStream oFile = new FileOutputStream("data.properties", false);//true表示追加打开
                for(Map.Entry<String,String> maps : generalsMapSort.entrySet()){
                    prop.setProperty(maps.getKey(), maps.getValue());
                }
                prop.store(oFile, "Number:"+generalsMapSort.size());
                oFile.close();
            }catch(Exception e){
                System.out.println(e);
            }*/
        }
        System.gc();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            InputStream is = ExcelReaderUtil.class.getResourceAsStream("/excel/result_temp2.xlsx");
            OutputStream os = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\虚战力表"+sdf.format(new Date())+fileRemark+".xlsx");
            JxlsUtil.export2Excel(is,os,model);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("生成Excel成功!");

        NumberUtil.count(resultList2);
    }

    public static void zt(){
        /*String[] g = {"命世孙权","诡骑张飞","典军夏侯渊"};

        String[] sz = {"凤仪步练师","儒将陆逊","郡主孙尚香","倾世貂蝉","惊鸿黄舞蝶"};
        List<Integer> index_sz = new ArrayList<>();
        for(int i=0;i<sz.length;i++){
            index_sz.add(i);
        }
        List<List<Integer>> ids_sz = NumberUtil.getResult(index_sz,5-g.length);
        for(List<Integer> index : ids_sz){
            System.out.println(index);
        }

        List<List<Integer>> zhenRong = new ArrayList<>();
        for(int i=0;i<ids_sz.size();i++){
            for(int j=0;j<ids_sz.size();j++){
                for(int k=0;k<ids_sz.size();k++){
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    list.add(k);
                    zhenRong.add(list);
                }
            }
        }

        for(List<Integer> zr : zhenRong){
            for (Integer zzy : zr){
                System.out.print(ids_sz.get(zzy));
            }
            System.out.println();
        }
        System.out.println(zhenRong.size());*/
    }


    public static Map properties2map(Properties prop){
        Map<Object,Object> map = new LinkedHashMap<>();
        Enumeration enu = prop.keys();
        while (enu.hasMoreElements()) {
            Object obj = enu.nextElement();
            Object objv = prop.get(obj);
            map.put(obj, objv);
        }
        return map;
    }
}
