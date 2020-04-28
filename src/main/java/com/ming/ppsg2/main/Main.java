package com.ming.ppsg2.main;


import com.ming.ppsg2.entity.AppointExcludeGenerals;
import com.ming.ppsg2.entity.AppointGenerals;
import com.ming.ppsg2.entity.AppointSymbols;
import com.ming.ppsg2.entity.ArmsBook;
import com.ming.ppsg2.entity.Destiny;
import com.ming.ppsg2.entity.Generals;
import com.ming.ppsg2.entity.Result;
import com.ming.ppsg2.entity.Symbols;
import com.ming.ppsg2.entity.Warpath;
import com.ming.ppsg2.enums.GeneralsEnum;
import com.ming.ppsg2.utils.DestinyData;
import com.ming.ppsg2.utils.ExcelReaderUtil;
import com.ming.ppsg2.utils.GeneralsUtil;
import com.ming.ppsg2.utils.NumberUtil;
import com.ming.ppsg2.utils.ReadWriteExcel;
import com.ming.ppsg2.utils.jxls.JxlsUtil;
import org.springframework.beans.BeanUtils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        String top = "因缺少部分卡片属性数据，以下排名中上阵武将及随从不包含：征南曹仁、七星诸葛亮、暴怒张飞、桓侯张飞、讨虏黄忠、狂骨魏延、顾曲周瑜、修罗吕布\n" +
                "啪啪三国技术交流群：913083053\n" +
                "更新内容：1.新增兵符类型【狰】【蛊雕】\n" +
                "2.新增异化【豹魂祝融夫人】【奇谋法正】\n";
        top+= "";
        String advert = "";//广告
        String fileRemark = "(test)";
        //计算：992/658/1895
        //实际：988/654/1947

        List<AppointGenerals> appointGeneralsList = new ArrayList<>();
        appointGeneralsList.add(new AppointGenerals("虎涧典韦"));
        appointGeneralsList.add(new AppointGenerals("砺战赵云"));
        appointGeneralsList.add(new AppointGenerals("御甲张辽"));
        appointGeneralsList.add(new AppointGenerals("桀骜孙策"));
        appointGeneralsList.add(new AppointGenerals("飞将吕布"));
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
        List<Generals> generalsAll2 = new ArrayList<>();//重复的
        Map<String,Destiny> destinyMap = new HashMap<>();//命格材料

        ReadWriteExcel readWriteExcel = new ReadWriteExcel();
        readWriteExcel.setRow(1);
        List<List<String>> dataList = readWriteExcel.readRelativeExcel("/excel/data_temp2.xlsx");
        List<Map<String,String>> lists = readWriteExcel.transformMap(dataList);

        //排除武将
        Iterator<Map<String,String>> iterator = lists.iterator();
        while (iterator.hasNext()) {
            Map<String,String> list = iterator.next();
            for (AppointExcludeGenerals excludeGenerals : excludeGeneralsList) {
                if (excludeGenerals.getName().equalsIgnoreCase(list.get("name")) && excludeGenerals.getMaxSize()==0) {
                    iterator.remove();
                }
            }
        }
        System.out.println("获取初始数据："+lists.size()+"条");

        Map<String,String> generalsMapSort = new LinkedHashMap<>();
        Properties prop = new Properties();
        try {
            InputStream inStream = Main.class.getResourceAsStream("/data/data.properties");
            prop.load(inStream);//加载属性列表
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

        for (Map<String,String> map : lists) {
            if(null == map.get("armsBook1") || "".equals(map.get("armsBook1"))){
                continue;
            }

            String name = map.get("name");
            Integer code = null;
            for(GeneralsEnum.GeneralsCode generalsCode : GeneralsEnum.GeneralsCode.values()){
                if(generalsCode.getName().equals(map.get("generalsCode"))){
                    code = generalsCode.getCode();
                }
            }
            Integer level = Integer.valueOf(Double.valueOf(map.get("level")).intValue());
            Integer force = Integer.valueOf(Double.valueOf(map.get("force")).intValue());
            Integer intellect = Integer.valueOf(Double.valueOf(map.get("intellect")).intValue());
            Integer troops = Integer.valueOf(Double.valueOf(map.get("troops")).intValue());
            Integer gender = GeneralsEnum.Gender.boy.getCode();
            for(GeneralsEnum.Gender genders : GeneralsEnum.Gender.values()){
                if(genders.getName().equals(map.get("gender"))){
                    gender = genders.getCode();
                }
            }

            Integer generalsType = GeneralsEnum.GeneralsType.GeneralsType_6.getCode();
            for(GeneralsEnum.GeneralsType generalsTypes : GeneralsEnum.GeneralsType.values()){
                if(generalsTypes.getName().equals(map.get("generalsType"))){
                    generalsType = generalsTypes.getCode();
                }
            }

            Integer arms = GeneralsEnum.Arms.gun.getCode();
            for(GeneralsEnum.Arms arms1 : GeneralsEnum.Arms.values()){
                if(arms1.getName().equals(map.get("arms"))){
                    arms = arms1.getCode();
                }
            }

            Integer country = GeneralsEnum.Country.qun.getCode();
            for(GeneralsEnum.Country countrys : GeneralsEnum.Country.values()){
                if(countrys.getName().equals(map.get("country"))){
                    country = countrys.getCode();
                }
            }


            Boolean isEntourage = false;
            String[] entourageArr = map.get("entourage").split(",");
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
                if(warDevice.getName().equals(map.get("warDevice"))){
                    warDevicesCode = warDevice.getCode();
                }
            }
            Integer[] warDevices = {warDevicesCode};


            Integer armsType1 = null;
            Integer armsType2 = null;
            for(GeneralsEnum.ArmsType armsType : GeneralsEnum.ArmsType.values()){
                if(armsType.getName().equals(map.get("armsType1"))){
                    armsType1 = armsType.getCode();
                }
                if(armsType.getName().equals(map.get("armsType2"))){
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
                if(armsBook.getName().equals(map.get("armsBook1"))){book1 = armsBook.getCode();}
                if(armsBook.getName().equals(map.get("armsBook11"))){book11 = armsBook.getCode();}
                if(armsBook.getName().equals(map.get("armsBook2"))){book2 = armsBook.getCode();}
                if(armsBook.getName().equals(map.get("armsBook22"))){book22 = armsBook.getCode();}
                if(armsBook.getName().equals(map.get("armsBook3"))){book3 = armsBook.getCode();}
                if(armsBook.getName().equals(map.get("armsBook33"))){book33 = armsBook.getCode();}
                if(armsBook.getName().equals(map.get("armsBook4"))){book4 = armsBook.getCode();}
                if(armsBook.getName().equals(map.get("armsBook44"))){book44 = armsBook.getCode();}
                if(armsBook.getName().equals(map.get("armsBook5"))){book5 = armsBook.getCode();}
                if(armsBook.getName().equals(map.get("armsBook55"))){book55 = armsBook.getCode();}
            }

            Integer[][] armsBooks = {
                    {armsType1,armsType2},
                    {book1,book11},
                    {book2,book22},
                    {book3,book33},
                    {book4,book44},
                    {book5,book55},
            };

            String skin = map.get("skin");
            Integer skinCode = null;
            for(GeneralsEnum.Skin skin1 : GeneralsEnum.Skin.values()){
                if(skin1.getName().equals(skin)){
                    skinCode = skin1.getCode();
                }
            }

            Integer disobeyCode = null;
            for(GeneralsEnum.Destiny destiny : GeneralsEnum.Destiny.values()){
                if(destiny.getName().equals(map.get("destiny"))){
                    disobeyCode = destiny.getCode();
                }
            }
            Integer destinyForce = map.get("destinyForce")==null?null:Double.valueOf(map.get("destinyForce")).intValue();
            Integer destinyIntellect = map.get("destinyIntellect")==null?null:Double.valueOf(map.get("destinyIntellect")).intValue();
            Integer destinyTroops = map.get("destinyTroops")==null?null:Double.valueOf(map.get("destinyTroops")).intValue();
            Object[] destinys = {destinyForce,destinyIntellect,destinyTroops,Boolean.valueOf(map.get("isDestiny")),disobeyCode,null,null,null};

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
            generals.setId(Double.valueOf(map.get("id")).intValue()+"");

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
            generalsAll2.add(gList2.get(i));
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
        for(Generals g : generalsAll2){
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
            optimumEntourageList.add(copy);
        }
        List<Generals> optimumEntourage = GeneralsUtil.getOptimumEntourage(nimingList,optimumEntourageList,isHuanHua);

        long t1 = System.currentTimeMillis();
        List<Generals> nmList = new ArrayList<>();
        for(Generals generals : nimingList){
            //if(generals.getDestiny().getDisobey()==1 || generals.getDestiny().getDisobey()==3){
                nmList.add(generals);
            //}
        }
        List<List<Generals>> all = NumberUtil.getNoRepeatList(generalsMapSort,nmList,5,appointGeneralsList);
        System.out.println("上阵武将组合个数："+all.size());
        List<Result> resultList = new ArrayList<>();
        List<Result> resultList2 = new ArrayList<>();
        List<Result> grilResultList = new ArrayList<>();

        int count = 0;
        int finalCount = all.size();
        Integer grilCode = GeneralsEnum.Gender.gril.getCode();
        StringBuilder ids = null;
        for(List<Generals> generalsList : all){
            count++;

            ids = new StringBuilder();
            ids.append(generalsList.get(0).getId());
            ids.append(generalsList.get(1).getId());
            ids.append(generalsList.get(2).getId());
            ids.append(generalsList.get(3).getId());
            ids.append(generalsList.get(4).getId());

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
            Map<String,Object> symbolsMap = GeneralsUtil.getSymbols(generalsList,appointSymbolsList);
            List<Symbols> symbolsList = (List<Symbols>)symbolsMap.get("symbolsList");
            GeneralsUtil.countSymbols(generalsList,symbolsList);
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

            //女队
            if(
                generalsList.get(0).getGender().toString().equals(grilCode.toString()) &&
                generalsList.get(1).getGender().toString().equals(grilCode.toString()) &&
                generalsList.get(2).getGender().toString().equals(grilCode.toString()) &&
                generalsList.get(3).getGender().toString().equals(grilCode.toString()) &&
                generalsList.get(4).getGender().toString().equals(grilCode.toString())
            ){
                Result result = GeneralsUtil.getResult(generalsList,symbolsList,allTotalSword,allTotalSword2);
                if(allTotalSword2 > 360000){
                    grilResultList.add(result);
                }
            }
            System.out.println(count+" / "+finalCount + "  " + (d.intValue())+"%");

            int zhanli = 394000;
            int flag = 0;
            //跳过战力低于zhanli的
            if(allTotalSword<zhanli && appointGeneralsList.isEmpty()){
                if(allTotalSword2>zhanli){
                    flag = 1;
                }else{
                    generalsMapSort.put(ids.toString(),allTotalSword+","+allTotalSword2);
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
            try{
                //保存属性到data.properties文件
                FileOutputStream oFile = new FileOutputStream("src/main/resources/data/data.properties", false);//true表示追加打开
                for(Map.Entry<String,String> maps : generalsMapSort.entrySet()){
                    prop.setProperty(maps.getKey(), maps.getValue());
                }
                prop.store(oFile, "Number:"+generalsMapSort.size());
                oFile.close();
            }catch(Exception e){
                System.out.println(e);
            }
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
