package com.ming.ppsg.controller;

import com.ming.ppsg2.entity.ArmsBook;
import com.ming.ppsg2.entity.Destiny;
import com.ming.ppsg2.entity.Generals;
import com.ming.ppsg2.entity.Result;
import com.ming.ppsg2.entity.Symbols;
import com.ming.ppsg2.entity.ThreeDimensional;
import com.ming.ppsg2.entity.Warpath;
import com.ming.ppsg2.enums.GeneralsEnum;
import com.ming.ppsg2.utils.DestinyData;
import com.ming.ppsg2.utils.ExcelReaderUtil;
import com.ming.ppsg2.utils.GeneralsUtil;
import com.ming.ppsg2.utils.NumberUtil;
import com.ming.system.utils.CacheUtil;
import com.ming.system.utils.ResultMsg;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/ppsg")
public class PpsgController {

    @RequestMapping("/generals")
    public String generals(){
        return "ppsg/generals";
    }

    @RequestMapping("/generalsAdd")
    public String generalsAdd(){
        return "ppsg/generals_add";
    }

    @PostMapping("/search")
    @ResponseBody
    public ResultMsg search(String generals){
        Integer count = (Integer) CacheUtil.getInstance().getCacheData("searchCount");
        if(count==null){
            count = 0;
        }
        count++;
        CacheUtil.getInstance().addCacheData("searchCount",count);
        System.out.println("searchCount:"+count);
        if(StringUtils.isNotBlank(generals)){
            String[] names = generals.split(",");
            Map<String,Object> model = xzl(names);
            List<Result> result = (List<Result>)model.get("list2");
            if(result==null || result.size()==0){
                return ResultMsg.failed();
            }
            return ResultMsg.success(result.get(0));
        }
        return ResultMsg.error();
    }



    private Map<String,Object> xzl(String[] sz){
        List<Generals> nimingList = new ArrayList<>();
        List<Generals> generalsAll = new ArrayList<>();

        List<List<String>> lists = (List<List<String>>)CacheUtil.getInstance().getCacheData("xzl");
        if(null == lists){
            lists = ExcelReaderUtil.readExcel("/excel/data_temp.xlsx");
        }
        System.out.println("获取初始数据："+lists.size()+"条");

        Map<String,String> generalsMapSort = new LinkedHashMap<>();
//        Properties prop = new Properties();
//        try {
//            InputStream inStream = new FileInputStream("data.properties");
//            prop.load(inStream);     ///加载属性列表
//            Enumeration enu = prop.keys();
//            while (enu.hasMoreElements()) {
//                Object obj = enu.nextElement();
//                Object objv = prop.get(obj);
//                generalsMapSort.put(obj.toString(), objv.toString());
//            }
//            inStream.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("获取排除数据："+generalsMapSort.size()+"条");

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

                if(sz.length!=0){
                    for(String s : sz){
                        if(copy.getName().equals(s)){
                            nimingList.add(copy);
                        }
                    }
                }else{
                    nimingList.add(copy);
                }

                //destinyMap.put(generals.getName(),copyDestiny);
            }

            ArmsBook copyBook = new ArmsBook();
            BeanUtils.copyProperties(generals.getArmsBook(),copyBook);
            generals.setArmsBook(copyBook);

            Destiny copyDestiny = new Destiny();
            BeanUtils.copyProperties(generals.getDestiny(),copyDestiny);
            generals.setDestiny(copyDestiny);
            generalsAll.add(generals);
        }

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


        Map<Integer,List<Generals>> map = null;
        for(Generals generals : nimingList){
            map = GeneralsUtil.getEntourage(generals,generalsAll);//随从三维
        }

        List<Generals> allEntourageList = new ArrayList<>();
        for(Generals g : generalsAll){
            Generals copy = new Generals();
            BeanUtils.copyProperties(g,copy);
            allEntourageList.add(g);
        }

        Map<Integer,List<Generals>> allEntourage = GeneralsUtil.getAllEntourage(allEntourageList);//随从三维

        long t1 = System.currentTimeMillis();
        NumberUtil.clear();
        List<List<Generals>> all = NumberUtil.getNoRepeatList(nimingList,5);
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
            List<Symbols> symbolsList = GeneralsUtil.getSymbols(generalsList);
            ThreeDimensional td = GeneralsUtil.countSymbols(generalsList,symbolsList);

            GeneralsUtil.getWarpath(generalsList);//战意三维
            // 总战力 = 武将1战力 + 武将2战力 + 武将3战力 + 武将4战力 + 武将5战力 + 工坊战力（10152）
            // 武将战力 =（总武力+总智力+总兵力）*2+ 命格被动战力 + 战器被动战力
            Integer allTotalSword = GeneralsUtil.getAllTotalSword(generalsList);
            Integer allTotalSword2 = GeneralsUtil.getAllTotalSword2(generalsList);


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
                grilResultList.add(result);
            }

            int zhanli = 375000;
            int flag = 0;
            //跳过战力低于zhanli的
            /*if(allTotalSword<zhanli && sz.length==0){
                if(allTotalSword2>zhanli){
                    flag = 1;
                }else{
                    generalsMapSort.put(names.toString(),allTotalSword+","+allTotalSword2);
                    continue;
                }
            }*/

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
//        Map<String,Object> model = new HashMap<>();
//        List<Generals> forceTopList = allEntourage.get(GeneralsEnum.ThreeCircles.Force.getCode());
//        List<Generals> intellectTopList = allEntourage.get(GeneralsEnum.ThreeCircles.Intellect.getCode());
//        List<Generals> troopsTopList = allEntourage.get(GeneralsEnum.ThreeCircles.Troops.getCode());
//        Double rate = 1.25;//联协倍率
//        Integer add = 100;//作为随从+100属性
//        for(Generals generals : forceTopList){
//            Integer g = (generals.getTotalForce()/2+add);
//            Double d = g * rate;
//            generals.setFn(g);
//            generals.setF(d.intValue());
//        }
//        for(Generals generals : intellectTopList){
//            Integer g = (generals.getTotalIntellect()/2+add);
//            Double d = g * rate;
//            generals.setIn(g);
//            generals.setI(d.intValue());
//        }
//        for(Generals generals : troopsTopList){
//            Integer g = (generals.getTotalTroops()/2+add);
//            Double d = g * rate;
//            generals.setTn(g);
//            generals.setT(d.intValue());
//        }

//        model.put("top",top);
//        model.put("advert",advert);
        Map<String,Object> model = new HashMap<>();
//        model.put("simplifyList1",GeneralsUtil.getSimplifyList(resultList));//简表
//        model.put("simplifyList2",GeneralsUtil.getSimplifyList(resultList2));//简表（特殊战器）
//        model.put("list",resultList);//虚战力表
        model.put("list2",resultList2);//虚战力表（特殊战器）
//        model.put("grilList",grilResultList);//虚战力表（女队）
//        model.put("forceTopList",forceTopList);
//        model.put("intellectTopList",intellectTopList);
//        model.put("troopsTopList",troopsTopList);
//        model.put("destinyMap",destinyMap);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        try {
//            InputStream is = ExcelReaderUtil.class.getResourceAsStream("/excel/result_temp.xlsx");
//            OutputStream os = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\虚战力表"+sdf.format(new Date())+".xlsx");
//            JxlsUtil.export2Excel(is,os,model);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        try{
//            ///保存属性到data.properties文件
//            FileOutputStream oFile = new FileOutputStream("data.properties", false);//true表示追加打开
//            for(Map.Entry<String,String> maps : generalsMapSort.entrySet()){
//                prop.setProperty(maps.getKey(), maps.getValue());
//            }
//            prop.store(oFile, "Number:"+generalsMapSort.size());
//            oFile.close();
//        }catch(Exception e){
//            System.out.println(e);
//        }
//        System.out.println("生成Excel成功!");
        return model;
    }
}
