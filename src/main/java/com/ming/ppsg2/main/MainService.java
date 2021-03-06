package com.ming.ppsg2.main;

import com.ming.ppsg2.entity.AppointExcludeGenerals;
import com.ming.ppsg2.entity.AppointGenerals;
import com.ming.ppsg2.entity.AppointSymbols;
import com.ming.ppsg2.entity.ArmsBook;
import com.ming.ppsg2.entity.Compose;
import com.ming.ppsg2.entity.Destiny;
import com.ming.ppsg2.entity.Device;
import com.ming.ppsg2.entity.Generals;
import com.ming.ppsg2.entity.Result;
import com.ming.ppsg2.entity.Symbols;
import com.ming.ppsg2.entity.SymbolsTop;
import com.ming.ppsg2.entity.ThreeDimensional;
import com.ming.ppsg2.enums.GeneralsEnum;
import com.ming.ppsg2.utils.CountDownUtils;
import com.ming.ppsg2.utils.DestinyData;
import com.ming.ppsg2.utils.ExcelReaderUtil;
import com.ming.ppsg2.utils.GeneralsUtil;
import com.ming.ppsg2.utils.NumberUtil;
import com.ming.ppsg2.utils.NumberUtil2;
import com.ming.ppsg2.utils.ReadWriteExcel;
import com.ming.ppsg2.utils.jxls.JxlsUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class MainService {

    public static void main(String[] args) {
        //List<Generals> generalsList = getExcelData();
        //System.out.println(generalsList);
    }

    /**
     * 从excel获取基础数据
     */
    public static List<Map<String, String>> getExcelData(String path,List<AppointExcludeGenerals> excludeGeneralsList) {
        System.out.println("开始读取EXCEL："+path);
        long t1 = System.currentTimeMillis();
        ReadWriteExcel readWriteExcel = new ReadWriteExcel();
        readWriteExcel.setRow(1);
        List<List<String>> dataList = readWriteExcel.readRelativeExcel(path);
        List<Map<String, String>> lists = readWriteExcel.transformMap(dataList);
        lists = lists.subList(1,lists.size());
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
        long t2 = System.currentTimeMillis();
        System.out.println("获取数据耗时："+(t2-t1)+"ms");
        System.out.println("获取初始数据："+lists.size()+"条");
        return lists;
    }

    public static void handleExcelData(
            List<Map<String,String>> lists,
            List<AppointGenerals> appointGeneralsList,
            List<Generals> nimingAllList,
//            List<Generals> nimingAppointList,//指定
//            List<Generals> nimingNoAppointList,//非指定
            List<Generals> generalsAll,
            Map<String,Destiny> destinyMap//命格材料
    ) throws Exception {
        List<Generals> nimingAppointList = new Vector<>();//指定武将
        List<Generals> nimingNoAppointList = new Vector<>();//非指定武将
        System.out.println("初始数据处理开始！");
        //int split = lists.size()/CountDownUtils.POOL_SIZE+1;
        //List<List<Map<String,String>>> splitList = ListUtils.partition(lists,split);
        long t = System.currentTimeMillis();
        CountDownUtils.dispose(lists, map -> {
            try {
                long t1 = System.currentTimeMillis();
                //for (Map<String,String> map : items) {
                    if ("TRUE".equalsIgnoreCase(map.get("usable"))) {
                        String name = map.get("name");//如果是异化卡，加上 `_限`
                        String originalName = name;//原名，不带 `_限`
                        String[] parents = map.get("generalsCode").split("-");
                        List<Integer> codes = new ArrayList<>();
                        for(String parent : parents){
                            for (GeneralsEnum.GeneralsCode generalsCode : GeneralsEnum.GeneralsCode.values()) {
                                if (generalsCode.getName().equals(parent)) {
                                    codes.add(generalsCode.getCode());
                                    break;
                                }
                            }
                        }

                        boolean isResonance = false;//共鸣
                        if("TRUE".equalsIgnoreCase(map.get("resonance"))){
                            isResonance = true;
                        }else{
                            isResonance = false;
                        }

                        Integer level = 1;
                        Integer force = Integer.valueOf(Double.valueOf(map.get("force")).intValue());
                        Integer intellect = Integer.valueOf(Double.valueOf(map.get("intellect")).intValue());
                        Integer troops = Integer.valueOf(Double.valueOf(map.get("troops")).intValue());
                        Integer gender = GeneralsEnum.Gender.boy.getCode();
                        for (GeneralsEnum.Gender genders : GeneralsEnum.Gender.values()) {
                            if (genders.getName().equals(map.get("gender"))) {
                                gender = genders.getCode();
                            }
                        }

                        String force_x = map.get("force_x");
                        String intellect_x = map.get("intellect_x");
                        String troops_x = map.get("troops_x");
                        if(StringUtils.isNotBlank(force_x) && StringUtils.isNotBlank(intellect_x) && StringUtils.isNotBlank(troops_x)){
                            force = Integer.valueOf(Double.valueOf(map.get("force_x")).intValue());
                            intellect = Integer.valueOf(Double.valueOf(map.get("intellect_x")).intValue());
                            troops = Integer.valueOf(Double.valueOf(map.get("troops_x")).intValue());
                            name += "_限";
                        }

                        Integer generalsType = GeneralsEnum.GeneralsType.GeneralsType_6.getCode();
                        for (GeneralsEnum.GeneralsType generalsTypes : GeneralsEnum.GeneralsType.values()) {
                            if (generalsTypes.getName().equals(map.get("generalsType"))) {
                                generalsType = generalsTypes.getCode();
                            }
                        }

                        Integer armsType1 = null;
                        Integer armsType2 = null;
                        Integer arms = GeneralsEnum.Arms.gun.getCode();
                        for (GeneralsEnum.Arms arms1 : GeneralsEnum.Arms.values()) {
                            if (arms1.getName().equals(map.get("arms"))) {
                                arms = arms1.getCode();
                                if(arms == 1){//枪
                                    armsType1 = GeneralsEnum.ArmsType.DunQiangBing.getCode();
                                    armsType2 = GeneralsEnum.ArmsType.ChangJiBing.getCode();
                                }else if(arms == 2){//弓
                                    armsType1 = GeneralsEnum.ArmsType.HuoShiBing.getCode();
                                    armsType2 = GeneralsEnum.ArmsType.LianNuBing.getCode();
                                }else if(arms == 3){//骑
                                    armsType1 = GeneralsEnum.ArmsType.ZhongQiBing.getCode();
                                    armsType2 = GeneralsEnum.ArmsType.BiaoQiBing.getCode();
                                }
                            }
                        }

                        Integer country = GeneralsEnum.Country.qun.getCode();
                        for (GeneralsEnum.Country countrys : GeneralsEnum.Country.values()) {
                            if (countrys.getName().equals(map.get("country"))) {
                                country = countrys.getCode();
                            }
                        }

                        Boolean isEntourage = false;
                        String[] entourageArr = map.get("entourage").split("-");
                        List<Integer> entourageList = new ArrayList<>();
                        for (String entourage : entourageArr) {
                            for (GeneralsEnum.GeneralsCode generalsCode : GeneralsEnum.GeneralsCode.values()) {
                                if (entourage.equals(generalsCode.getName())) {
                                    entourageList.add(generalsCode.getCode());
                                    break;
                                }
                            }
                        }

                        Integer[] entourages = new Integer[entourageList.size()];
                        entourageList.toArray(entourages);

                        Integer warDevicesCode = null;
                        Integer warDevicesCode2 = null;
                        for (GeneralsEnum.WarDevice warDevice : GeneralsEnum.WarDevice.values()) {
                            if (warDevice.getName().equals(map.get("war1"))) {
                                warDevicesCode = warDevice.getCode();
                            }
                            if (warDevice.getName().equals(map.get("war2"))) {
                                warDevicesCode2 = warDevice.getCode();
                            }
                        }
                        Integer[] warDevices = {warDevicesCode,warDevicesCode2};

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
                        Integer[][] armsBooks = new Integer[6][2];
                        armsBooks[0] = new Integer[]{armsType1, armsType2};
                                /*{
                                {armsType1, armsType2},
                                {book1, book11},
                                {book2, book22},
                                {book3, book33},
                                {book4, book44},
                                {book5, book55},
                        };*/
                        for (GeneralsEnum.BookType bookType : GeneralsEnum.BookType.values()) {
                            if (bookType.getName().equals(map.get("armsBookType1"))) {
                                for (GeneralsEnum.ArmsBook armsBook : GeneralsEnum.ArmsBook.values()) {
                                    if(armsBook.getCode().equals(bookType.getCode1())){
                                        book1 = armsBook.getCode();
                                    }
                                    if(armsBook.getCode().equals(bookType.getCode2())){
                                        book11 = armsBook.getCode();
                                    }
                                }
                                armsBooks[1] = new Integer[]{book1, book11};
                            }
                            if (bookType.getName().equals(map.get("armsBookType2"))) {
                                for (GeneralsEnum.ArmsBook armsBook : GeneralsEnum.ArmsBook.values()) {
                                    if(armsBook.getCode().equals(bookType.getCode1())){
                                        book2 = armsBook.getCode();
                                    }
                                    if(armsBook.getCode().equals(bookType.getCode2())){
                                        book22 = armsBook.getCode();
                                    }
                                }
                                armsBooks[2] = new Integer[]{book2, book22};
                            }
                            if (bookType.getName().equals(map.get("armsBookType3"))) {
                                for (GeneralsEnum.ArmsBook armsBook : GeneralsEnum.ArmsBook.values()) {
                                    if(armsBook.getCode().equals(bookType.getCode1())){
                                        book3 = armsBook.getCode();
                                    }
                                    if(armsBook.getCode().equals(bookType.getCode2())){
                                        book33 = armsBook.getCode();
                                    }
                                }
                                armsBooks[3] = new Integer[]{book3, book33};
                            }
                            if (bookType.getName().equals(map.get("armsBookType4"))) {
                                for (GeneralsEnum.ArmsBook armsBook : GeneralsEnum.ArmsBook.values()) {
                                    if(armsBook.getCode().equals(bookType.getCode1())){
                                        book4 = armsBook.getCode();
                                    }
                                    if(armsBook.getCode().equals(bookType.getCode2())){
                                        book44 = armsBook.getCode();
                                    }
                                }
                                armsBooks[4] = new Integer[]{book4, book44};
                            }
                            if (bookType.getName().equals(map.get("armsBookType5"))) {
                                for (GeneralsEnum.ArmsBook armsBook : GeneralsEnum.ArmsBook.values()) {
                                    if(armsBook.getCode().equals(bookType.getCode1())){
                                        book5 = armsBook.getCode();
                                    }
                                    if(armsBook.getCode().equals(bookType.getCode2())){
                                        book55 = armsBook.getCode();
                                    }
                                }
                                armsBooks[5] = new Integer[]{book5, book55};
                            }
                        }

                        String skin = map.get("skin");
                        Integer skinCode = null;
                        for (GeneralsEnum.Skin skin1 : GeneralsEnum.Skin.values()) {
                            if (skin1.getName().equals(skin)) {
                                skinCode = skin1.getCode();
                            }
                        }
                        if(skinCode == null && StringUtils.isNotBlank(skin) && !"无".equals(skin)){
                            skinCode = GeneralsEnum.Skin.skin_0.getCode();
                        }else{
                            skinCode = GeneralsEnum.Skin.skin_00.getCode();
                        }

                        boolean isDestiny = false;
                        Integer disobeyCode = null;
                        for (GeneralsEnum.Destiny destiny : GeneralsEnum.Destiny.values()) {
                            if (destiny.getName().equals(map.get("destinyType"))) {
                                disobeyCode = destiny.getCode();
                                if(disobeyCode != 0){
                                    isDestiny = true;
                                }
                            }
                        }

                        Integer destinyForce = map.get("destinyForce") == null ? 0 : Double.valueOf(map.get("destinyForce")).intValue();
                        Integer destinyIntellect = map.get("destinyIntellect") == null ? 0 : Double.valueOf(map.get("destinyIntellect")).intValue();
                        Integer destinyTroops = map.get("destinyTroops") == null ? 0 : Double.valueOf(map.get("destinyTroops")).intValue();
                        Object[] destinys = {destinyForce, destinyIntellect, destinyTroops, isDestiny, disobeyCode, null, null, null};

                        Generals generals = DestinyData.getGenerals(name,originalName, codes, level, force, intellect, troops, gender, generalsType, arms, country, isEntourage, entourages, warDevices, armsBooks, destinys, skinCode,skin, isResonance);

                        GeneralsUtil.getMaxLevel(generals);//基础满级三维
                        GeneralsUtil.getScience(generals);//科技三维
                        GeneralsUtil.getHolyStone(generals);//四圣石三维
                        GeneralsUtil.getDestiny(generals);//命格三维(在战器前计算)
                        //GeneralsUtil.getWarDevice(generals);//战器三维
                        //GeneralsUtil.getWarDevice2(generals);//特殊战器三维
                        GeneralsUtil.getArmsBook(generals);//兵种兵书三维
                        GeneralsUtil.getWillSoul(generals);//将魂三维
                        GeneralsUtil.getBattleArray(generals);//战阵三维
                        GeneralsUtil.getSkin(generals);//幻化三维
                        GeneralsUtil.getBattleArrayWay(generals);//阵法三维
                        //GeneralsUtil.getOther(generals);//设置其他战力加成
                        //generals.setWarpath(new Warpath());

                        if (name.endsWith("_限")) {
                            generals.setLimit(true);
                        } else {
                            generals.setLimit(false);
                        }
                        generals.setId(Double.valueOf(map.get("id")).intValue() + "");

                        if (1 == disobeyCode || 2 == disobeyCode || 3 == disobeyCode || 4 == disobeyCode) {
                            Generals copy = new Generals();

                            ArmsBook copyBook = new ArmsBook();
                            BeanUtils.copyProperties(generals.getArmsBook(), copyBook);
                            copy.setArmsBook(copyBook);

                            Destiny copyDestiny = new Destiny();

                            BeanUtils.copyProperties(generals.getDestiny(), copyDestiny);
                            copy.setDestiny(copyDestiny);

                            BeanUtils.copyProperties(generals, copy);

                            if (!appointGeneralsList.isEmpty()) {
                                boolean flag = true;
                                for (AppointGenerals appointGenerals : appointGeneralsList) {
                                    if (copy.getName().equals(appointGenerals.getName())) {
                                        nimingAppointList.add(copy);
                                        flag = false;
                                    }
                                }
                                if (flag) {
                                    nimingNoAppointList.add(copy);
                                }
                            } else {
                                nimingNoAppointList.add(copy);
                            }

                            destinyMap.put(generals.getName(), copyDestiny);
                        }

                        ArmsBook copyBook = new ArmsBook();
                        BeanUtils.copyProperties(generals.getArmsBook(), copyBook);
                        generals.setArmsBook(copyBook);

                        Destiny copyDestiny = new Destiny();
                        BeanUtils.copyProperties(generals.getDestiny(), copyDestiny);
                        generals.setDestiny(copyDestiny);
                        generalsAll.add(generals);
                    }
                //}

                long t2 = System.currentTimeMillis();
                System.out.println(System.currentTimeMillis()+",任务执行完毕，耗时:"+(t2-t1));
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        nimingAllList.addAll(nimingAppointList);
        nimingAllList.addAll(nimingNoAppointList);
        long t2 = System.currentTimeMillis();
        //上面所有任务处理完毕完毕之后，程序才能继续
        System. out.println("初始数据处理完毕!处理数据耗时："+(t2-t)+"ms");
    }

    public static void handleNimingAllList(List<Generals> nimingAllList,List<Generals> generalsAll,List<Generals> generalsAll2) {
        long t1 = System.currentTimeMillis();
        //去除重复卡，保留异化卡
        System.out.println("上阵武将："+nimingAllList.size());
        List<Generals> gList1 = new ArrayList<>();
        for(int i=0;i<nimingAllList.size();i++){
            for(int j=0;j<nimingAllList.size();j++){
                if(nimingAllList.get(i).getName().contains(nimingAllList.get(j).getName())){
                    if(nimingAllList.get(i).getLimit() != nimingAllList.get(j).getLimit()){
                        if(nimingAllList.get(i).getName().endsWith("_限")){
                            gList1.add(nimingAllList.get(j));
                        }
                    }
                }
            }
        }

        for(int i=0;i<gList1.size();i++){
            nimingAllList.remove(gList1.get(i));
        }


        System.out.println("随从武将数量："+generalsAll.size());
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
        long t2 = System.currentTimeMillis();
        System. out.println("数据去重处理完毕!耗时："+(t2-t1)+"ms");
    }

    public static Map<Integer,List<Generals>> handleEntourage(List<Generals> nimingAllList, List<Generals> generalsAll, boolean isHuanHua) {
        long t1 = System.currentTimeMillis();
        Map<Integer,List<Generals>> map = null;
        for(Generals generals : nimingAllList){
            map = GeneralsUtil.getEntourage(generals,generalsAll,isHuanHua);//随从三维
        }
        long t2 = System.currentTimeMillis();
        System. out.println("计算随从三维完毕!耗时："+(t2-t1)+"ms");
        return map;
    }

    public static Map<Integer, List<Generals>> handleEntourageBillboard(List<Generals> generalsAll, List<Generals> generalsAll2, boolean isHuanHua) {
        long t1 = System.currentTimeMillis();
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
        long t2 = System.currentTimeMillis();
        System. out.println("计算随从榜完毕!耗时："+(t2-t1)+"ms");
        return allEntourage;
    }

    public static List<Generals> handleOptimumEntourage(List<Generals> generalsAll, List<Generals> nimingAllList, boolean isHuanHua) {
        long t1 = System.currentTimeMillis();
        //最佳随从表
        List<Generals> optimumEntourageList = new ArrayList<>();
        for(Generals g : generalsAll){
            Generals copy = new Generals();
            BeanUtils.copyProperties(g,copy);
            optimumEntourageList.add(copy);
        }
        List<Generals> optimumEntourage = GeneralsUtil.getOptimumEntourage(nimingAllList,optimumEntourageList,isHuanHua);
        long t2 = System.currentTimeMillis();
        System. out.println("计算最佳随从榜完毕!耗时："+(t2-t1)+"ms");
        return optimumEntourage;
    }

    public static List<Generals> handleFinalNmList(int totalSize,List<Generals> nimingAllList) {
        long t1 = System.currentTimeMillis();
        List<Generals> nmList = new ArrayList<>();
        List<Generals> nList = new ArrayList<>();//逆命
        List<Generals> tList = new ArrayList<>();//突破
        for(Generals generals : nimingAllList){
            if(generals.getDestiny().getDisobey()==1 || generals.getDestiny().getDisobey()==3 || generals.getDestiny().getDisobey()==4){
                nList.add(generals);
            }else if(generals.getDestiny().getDisobey()==2){//突破
                tList.add(generals);
            }
        }
        tList.sort(new Comparator<Generals>() {
            @Override
            public int compare(Generals o1, Generals o2) {
                return o2.getTotalSword() - o1.getTotalSword(); //降序
            }
        });
        nmList.addAll(nList);
        if(nList.size() < totalSize){
            nmList.addAll(tList.subList(0,totalSize-nList.size()));
        }
        long t2 = System.currentTimeMillis();
        System. out.println("处理排列组合数据完毕!耗时："+(t2-t1)+"ms");
        return nmList;
    }

    public static void resultSortAndRank(List<Result> resultList2, List<Result> grilResultList) {
        long t1 = System.currentTimeMillis();
        //结果排序
        for(Result result : resultList2){
            if(result == null){
                System.out.println();
            }
        }
        resultList2.sort(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return o2.getTotal2() - o1.getTotal2(); //降序
            }
        });
        grilResultList.sort(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return o2.getTotal2() - o1.getTotal2(); //降序
            }
        });
        //结果排名
//        int i = 1;
//        for(Result result : resultList){
//            result.setRank(i++);
//        }
        int i2 = 1;
        for(Result result : resultList2){
            result.setRank(i2++);
        }
        int i3 = 1;
        for(Result result : grilResultList){
            result.setRank(i3++);
        }
        long t2 = System.currentTimeMillis();
        System. out.println("处理排序排名完毕!耗时："+(t2-t1)+"ms");
    }

    public static void allEntourageBillboard(Map<Integer, List<Generals>> allEntourage, List<Generals> forceTopList, List<Generals> intellectTopList, List<Generals> troopsTopList) {
        long t1 = System.currentTimeMillis();
        Double rate = 1.25;//联协倍率
        Integer add = 100;//作为随从+100属性
        for(Generals generals : forceTopList){
            int g = (generals.getTotalForce()/2+add);
            Double d0 = g * rate;
            generals.setFn(g);
            generals.setF(d0.intValue());
        }
        for(Generals generals : intellectTopList){
            int g = (generals.getTotalIntellect()/2+add);
            Double d0 = g * rate;
            generals.setIn(g);
            generals.setI(d0.intValue());
        }
        for(Generals generals : troopsTopList){
            int g = (generals.getTotalTroops()/2+add);
            Double d0 = g * rate;
            generals.setTn(g);
            generals.setT(d0.intValue());
        }
        long t2 = System.currentTimeMillis();
        System. out.println("处理随从三榜单完毕!耗时："+(t2-t1)+"ms");
    }

    public static void createExcel(String fileRemark, Map<String, Object> model) {
        long t1 = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            //当前用户桌面
            File desktopDir = FileSystemView.getFileSystemView() .getHomeDirectory();
            String desktopPath = desktopDir.getAbsolutePath();
            //System.out.println(desktopPath);
            InputStream is = ExcelReaderUtil.class.getResourceAsStream("/excel/result_temp4.xlsx");
            OutputStream os = new FileOutputStream(desktopPath+"\\虚战力表"+sdf.format(new Date())+fileRemark+".xlsx");
            JxlsUtil.export2Excel(is,os,model);
        }catch (Exception e){
            e.printStackTrace();
        }
        long t2 = System.currentTimeMillis();
        System. out.println("生成Excel完毕!耗时："+(t2-t1)+"ms");
    }

    public static void statistics(List<Result> resultList2) {
        long t1 = System.currentTimeMillis();
        NumberUtil.count(resultList2);
        long t2 = System.currentTimeMillis();
        System. out.println("兵符统计完毕!耗时："+(t2-t1)+"ms");
    }

    public static List<Result> handleSword(List<Result> grilResultList, List<Generals> nmList, List<AppointGenerals> appointGeneralsList, List<AppointSymbols> appointSymbolsList, List<AppointExcludeGenerals> excludeGeneralsList) throws InterruptedException {
        List<Result> resultList2 = new Vector<>();
        long t = System.currentTimeMillis();
        List<Compose> all = NumberUtil.getNoRepeatList2(nmList,5,appointGeneralsList);
        final int size = all.size();
        System.out.println("上阵武将组合个数："+size+",耗时："+(System.currentTimeMillis()-t));
        TimeUnit.MILLISECONDS.sleep(3000);
        CountDownUtils.dispose(all,composes->{
            long t1 = System.currentTimeMillis();

            List<Generals> generalsList = composes.getList();
            //极限兵符
            Map<String,Object> symbolsMap = GeneralsUtil.getSymbols(generalsList,appointSymbolsList);
            List<Symbols> symbolsList = (List<Symbols>)symbolsMap.get("symbolsList");
            List<SymbolsTop> symbolsTop = (List<SymbolsTop>)symbolsMap.get("symbolsTop");
            List<SymbolsTop> symbolsTopSecond = (List<SymbolsTop>)symbolsMap.get("symbolsTopSecond");
            //long t2 = System.currentTimeMillis();

            GeneralsUtil.countSymbols(generalsList,symbolsList);
            //long t3 = System.currentTimeMillis();
            //战意三维
            GeneralsUtil.getWarpath(generalsList);
            //long t4 = System.currentTimeMillis();
            // 总战力 = 武将1战力 + 武将2战力 + 武将3战力 + 武将4战力 + 武将5战力 + 工坊战力（10152）
            // 武将战力 =（总武力+总智力+总兵力）*2+ 命格被动战力 + 战器被动战力
            int allTotalSword2 = 0;
            if(excludeGeneralsList.size() > 0){
                //allTotalSword2 = GeneralsUtil.getAllTotalSword4(generalsList,excludeGeneralsList);
            }else{
                allTotalSword2 = GeneralsUtil.getAllTotalSword2(generalsList);
            }

            Result result = GeneralsUtil.getResult(generalsList,symbolsList,symbolsTop,symbolsTopSecond,0,allTotalSword2);
            resultList2.add(result);
            //女队
            if(composes.isGril() && allTotalSword2 > 390000){
                grilResultList.add(result);
            }

            long t5 = System.currentTimeMillis();
            System.out.println(composes.getSort()+"/"+size+"，耗时:"+(t5-t1));
        });
        long t2 = System.currentTimeMillis();
        System.out.println("最终武将组合个数："+resultList2.size()+"/"+size);
        System.out.println("战力计算完毕!耗时："+(t2-t)+"ms");
        TimeUnit.MILLISECONDS.sleep(3000);
        //结果排序、排名
        MainService.resultSortAndRank(resultList2,grilResultList);
        return resultList2;
    }


    public static List<Result> handleSword2(JTextArea txaDisplay, List<Result> grilResultList, List<Generals> nmList, List<AppointGenerals> appointGeneralsList, List<AppointSymbols> appointSymbolsList, List<AppointExcludeGenerals> excludeGeneralsList) throws InterruptedException {
        List<Result> resultList2 = new Vector<>();
        long t = System.currentTimeMillis();
        List<List<Integer>> all = NumberUtil2.getNoRepeatList2(nmList,5,appointGeneralsList);
        final int size = all.size();
        System.out.println("上阵武将组合个数："+size+",耗时："+(System.currentTimeMillis()-t));
        TimeUnit.MILLISECONDS.sleep(3000);
        final BeanCopier copier = BeanCopier.create(Generals.class, Generals.class, false);
        CountDownUtils.dispose(all,composes->{
            long t1 = System.currentTimeMillis();

            List<Generals> generalsList = new ArrayList<>();
            /*WeakReference<Generals> reference = new WeakReference<Generals>(new People("zhouqian",20));
            System.out.println(reference.get());
            System.gc();//通知GVM回收资源
            System.out.println(reference.get());*/

            for (Integer item : composes){
                for (Generals generals : nmList){
                    if(generals.getId().equals(item.toString())){
                        Generals generalsNew = new Generals();
                        copier.copy(generals, generalsNew, null);
                        WeakReference<Generals> reference = new WeakReference<>(generalsNew);
                        generalsList.add(reference.get());
                        break;
                    }
                }
            }
            //WeakReference<List<Generals>> reference = new WeakReference<List<Generals>>(generalsList);

            //极限兵符
            Map<String,Object> symbolsMap = GeneralsUtil.getSymbols(generalsList,appointSymbolsList);
            List<Symbols> symbolsList = (List<Symbols>)symbolsMap.get("symbolsList");
            List<SymbolsTop> symbolsTop = (List<SymbolsTop>)symbolsMap.get("symbolsTop");
            List<SymbolsTop> symbolsTopSecond = (List<SymbolsTop>)symbolsMap.get("symbolsTopSecond");
            //long t2 = System.currentTimeMillis();

            GeneralsUtil.countSymbols(generalsList,symbolsList);
            //long t3 = System.currentTimeMillis();
            //战意三维
            GeneralsUtil.getWarpath(generalsList);
            //long t4 = System.currentTimeMillis();
            // 总战力 = 武将1战力 + 武将2战力 + 武将3战力 + 武将4战力 + 武将5战力 + 工坊战力（10152）
            // 武将战力 =（总武力+总智力+总兵力）*2+ 命格被动战力 + 战器被动战力
            int allTotalSword2 = 0;
            if(excludeGeneralsList.size() > 0){
                //allTotalSword2 = GeneralsUtil.getAllTotalSword4(generalsList,excludeGeneralsList);
            }else{
                allTotalSword2 = GeneralsUtil.getAllTotalSword2(generalsList);
            }


            //女队
            if(allTotalSword2 > 390000){
                Result result = null;
                if(allTotalSword2 > 545000) {
                    result = GeneralsUtil.getResult(generalsList, symbolsList, symbolsTop, symbolsTopSecond, 0, allTotalSword2);
                    resultList2.add(result);
                }
                if(result!=null && result.getIsGril()){
                    grilResultList.add(result);
                }
            }

            long t5 = System.currentTimeMillis();
            int number = NumberUtil2.getAutoNumber();
            System.out.println(number+"/"+size+"，耗时:"+(t5-t1));
            //txaDisplay.append(number+"/"+size+"，耗时:"+(t5-t1));
            //txaDisplay.setCaretPosition(txaDisplay.getText().length());
            if(number%20000 == 0){
                System.out.println("JVM回收垃圾...");
                System.gc();
            }
        });
        long t2 = System.currentTimeMillis();
        System.out.println("最终武将组合个数："+resultList2.size()+"/"+size);
        System.out.println("战力计算完毕!耗时："+(t2-t)+"ms");
        System.gc();
        TimeUnit.MILLISECONDS.sleep(3000);
        //结果排序、排名
        MainService.resultSortAndRank(resultList2,grilResultList);
        return resultList2;
    }

    public static void countCombatName(List<Result> list) {
        for (Result result : list){
            result.setCombatName(GeneralsEnum.Combat.getNameByCombat(result.getTotal2()));
        }
    }

    public static List<Device> handleWarDevice(List<Generals> nimingAllList) {
        List<Device> deviceList = new ArrayList<>();
        for (GeneralsEnum.Device dev : GeneralsEnum.Device.values()){
            ThreeDimensional deviceBase = null;
            ThreeDimensional deviceStrengthen = null;
            ThreeDimensional deviceQuenching = new ThreeDimensional(0,0,0);
            ThreeDimensional deviceExclusive = null;
            ThreeDimensional deviceAwaken = null;//觉醒三维
            ThreeDimensional deviceRefiner = null;//炼器95三维
            ThreeDimensional deviceRefiner5 = null;//炼器5三维
            ThreeDimensional deviceQiLing = null;//器灵三维
            String quenchingName1 = "";
            String quenchingName2 = "";

            GeneralsEnum.WarDevice warDevice = dev.getWarDevice();

            int force = 0;
            int intellect = 0;
            int troops = 0;

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

            //炼器
            force += warDevice.getRefinerForce();
            intellect += warDevice.getRefinerIntellect();
            troops += warDevice.getRefinerTroops();
            deviceRefiner = new ThreeDimensional(warDevice.getRefinerForce(),warDevice.getRefinerIntellect(),warDevice.getRefinerTroops());

            deviceRefiner5 = new ThreeDimensional(warDevice.getRefiner5Force(),warDevice.getRefiner5Intellect(),warDevice.getRefiner5Troops());

            //器灵
            deviceQiLing = new ThreeDimensional(0,0,0);

            Device device = new Device();
            device.setCode(warDevice.getCode());
            device.setName(warDevice.getName());
            device.setDesc(warDevice.getDesc());
            device.setDeviceName(dev.getName());
            device.setGenerals2(dev.getGenerals2());
            device.setQuenchingName1(quenchingName1);
            device.setQuenchingName2(quenchingName2);
            device.setDeviceBaseThreeDimensional(deviceBase);
            device.setDeviceStrengthenThreeDimensional(deviceStrengthen);
            device.setDeviceQuenchingThreeDimensional(deviceQuenching);
            device.setDeviceExclusiveThreeDimensional(deviceExclusive);
            device.setDeviceAwakenThreeDimensional(deviceAwaken);
            device.setDeviceRefiner95ThreeDimensional(deviceRefiner);
            device.setDeviceRefiner5ThreeDimensional(deviceRefiner5);
            device.setDeviceQiLingThreeDimensional(deviceQiLing);
            deviceList.add(device);
        }

        //设置共鸣
        List<String> resonanceList = new ArrayList<>();
        for(Generals generals : nimingAllList){
            if(generals.getIsResonance()){
                resonanceList.add(generals.getOriginalName());
            }
        }
        for (Device d : deviceList){
            String[] generals2List = d.getGenerals2();
            boolean flag = false;
            for(String generals2 : generals2List){
                for(String r : resonanceList){
                    if(r.equals(generals2)){
                        flag = true;
                        break;
                    }
                }
            }
            d.setResonance(flag);
        }


        //List<String> resonanceList = new ArrayList<>();
        ThreeDimensional three = null;
        for(Generals generals : nimingAllList){
            System.out.println("战器计算："+generals.getOriginalName());

            //ThreeDimensional threeDestiny = new ThreeDimensional(0,0,0);
            Map<Device,Integer> deviceMap = new HashMap<>();
            for (Device d : deviceList){
                //判断可装备战器
                boolean flag = false;
                if(d.getCode()!=null){
                    List<Integer> devices = generals.getWarDevices();
                    for (Integer code : devices){
                        if(code!=null && code == d.getCode()-5){
                            flag = true;
                            break;
                        }
                    }
                }
                //如果可装备
                if(flag){
                    int f = 0;
                    int i = 0;
                    int t = 0;
                    three = d.getDeviceBaseThreeDimensional();
                    f+=three.getForce();i+=three.getIntellect();t+=three.getTroops();
                    three = d.getDeviceStrengthenThreeDimensional();
                    f+=three.getForce();i+=three.getIntellect();t+=three.getTroops();
                    three = d.getDeviceQuenchingThreeDimensional();
                    f+=three.getForce();i+=three.getIntellect();t+=three.getTroops();

                    //专属
                    String[] generals2List = d.getGenerals2();
                    boolean exclusive = false;
                    for(String generals2 : generals2List){
                        if(generals.getOriginalName().equals(generals2)){
                            exclusive = true;
                            break;
                        }
                    }
                    if(exclusive){
                        three = d.getDeviceExclusiveThreeDimensional();
                        f+=three.getForce();i+=three.getIntellect();t+=three.getTroops();
                    }

                    //觉醒
                    three = d.getDeviceAwakenThreeDimensional();
                    f+=three.getForce();i+=three.getIntellect();t+=three.getTroops();

                    //炼器+命格
                    int f0 = 0;
                    int i0 = 0;
                    int t0 = 0;
                    if(d.getResonance()){
                        three = d.getDeviceRefiner95ThreeDimensional();
                        f+=three.getForce();i+=three.getIntellect();t+=three.getTroops();
                        ThreeDimensional threeDimensional = generals.getDestinyThreeDimensional();
                        f0+=threeDimensional.getForce()*0.7;
                        i0+=threeDimensional.getIntellect()*0.7;
                        t0+=threeDimensional.getTroops()*0.7;
                    }else{
                        three = d.getDeviceRefiner5ThreeDimensional();
                        f+=three.getForce();i+=three.getIntellect();t+=three.getTroops();
                    }

                    //器灵
                    //int tf = d.getDeviceBaseThreeDimensional().getForce()+d.getDeviceStrengthenThreeDimensional().getForce();
                    //int ti = d.getDeviceBaseThreeDimensional().getIntellect()+d.getDeviceStrengthenThreeDimensional().getIntellect();
                    int tt = d.getDeviceBaseThreeDimensional().getTroops()+d.getDeviceStrengthenThreeDimensional().getTroops();
                    //f+=tf*0.8;
                    //i+=ti*0.8;
                    t+=tt*0.8*3;

                    int totalCombat = (f+i+t)*2;
                    int totalCombat2 = (f0+i0+t0)*2;
                    //if(totalCombat + totalCombat2 > total){
                    int total = totalCombat + totalCombat2;
                    ThreeDimensional three0 = new ThreeDimensional();
                    three0.setForce(f);
                    three0.setIntellect(i);
                    three0.setTroops(t);
                    three0.setTotalZl((f+i+t)*2);

                    ThreeDimensional threeDestiny = new ThreeDimensional(f0,i0,t0);
                    threeDestiny.setTotalZl((f0+i0+t0)*2);
                    ThreeDimensional threeQiLing = new ThreeDimensional(0,0,(int)(tt*0.8*3));
                    //}

                    Device device = new Device();
                    device.setName(d.getName());
                    device.setCode(d.getCode());
                    device.setDeviceName(d.getDeviceName());
                    device.setGenerals2(d.getGenerals2());
                    device.setResonance(d.getResonance());
                    device.setQuenchingName1(d.getQuenchingName1());
                    device.setQuenchingName2(d.getQuenchingName2());
                    device.setDeviceBaseThreeDimensional(d.getDeviceBaseThreeDimensional());
                    device.setDeviceStrengthenThreeDimensional(d.getDeviceStrengthenThreeDimensional());
                    device.setDeviceQuenchingThreeDimensional(d.getDeviceQuenchingThreeDimensional());
                    if(exclusive){
                        device.setDeviceExclusiveThreeDimensional(d.getDeviceExclusiveThreeDimensional());
                        device.setExclusiveDesc("(专属)");
                    }else{
                        device.setDeviceExclusiveThreeDimensional(new ThreeDimensional(0,0,0));
                        device.setExclusiveDesc("");
                    }
                    device.setDeviceAwakenThreeDimensional(d.getDeviceAwakenThreeDimensional());
                    device.setDeviceRefiner95ThreeDimensional(d.getDeviceRefiner95ThreeDimensional());
                    device.setDeviceRefiner5ThreeDimensional(d.getDeviceRefiner5ThreeDimensional());
                    if(device.getResonance()){
                        device.setDeviceRefinerThreeDimensional(d.getDeviceRefiner95ThreeDimensional());
                        device.setResonanceDesc("(共鸣)");
                    }else{
                        device.setDeviceRefinerThreeDimensional(d.getDeviceRefiner5ThreeDimensional());
                        device.setResonanceDesc("");
                    }
                    device.setDesc(d.getDesc()+"|"+d.getDeviceName()+device.getExclusiveDesc()+device.getResonanceDesc());
                    device.setDeviceRefinerDestinyThreeDimensional(threeDestiny);
                    device.setDeviceQiLingThreeDimensional(threeQiLing);
                    device.setTotalThreeDimensional(three0);
                    deviceMap.put(device,total);
                }
            }

            //倒序并获取第一个战器
            List<Map.Entry<Device,Integer>> list = new ArrayList<>();
            list.addAll(deviceMap.entrySet());
            MainService.ValueComparator vc = new ValueComparator();
            Collections.sort(list,vc);
            Device device = list.get(0).getKey();
            Integer maxCombat = list.get(0).getValue();

            //并列第一战器
            String descs = "";
            StringBuilder tops = new StringBuilder();
            for(Map.Entry<Device,Integer> map : list){
                if(!map.getKey().getDeviceName().equals(device.getDeviceName()) && map.getValue().equals(maxCombat)){
                    descs+="("+map.getKey().getDesc()+"|"+map.getKey().getDeviceName()+")";
                }
                tops.append(map.getKey().getDesc()).append("：").append(map.getValue()).append("\r\n");
            }
            device.setDesc(device.getDesc()+descs);
            device.setTops(tops.toString());

            //更新战器
            generals.setDevice2(device);
            ThreeDimensional three0 = device.getTotalThreeDimensional();
            generals.setWarDevice2ThreeDimensional(three0);
            GeneralsUtil.setTotal(generals,three0);

            //更新命格数据
            ThreeDimensional threeDestiny = device.getDeviceRefinerDestinyThreeDimensional();
            ThreeDimensional destiny = generals.getDestinyThreeDimensional();
            destiny.setForce(destiny.getForce()+threeDestiny.getForce());
            destiny.setIntellect(destiny.getIntellect()+threeDestiny.getIntellect());
            destiny.setTroops(destiny.getTroops()+threeDestiny.getTroops());
            destiny.setTotalZl((destiny.getForce()+destiny.getIntellect()+destiny.getTroops())*2);
            generals.setDestinyThreeDimensional(destiny);

            /*Destiny destiny1 = generals.getDestiny();
            destiny1.setForce(destiny1.getForce()+threeDestiny.getForce());
            destiny1.setIntellect(destiny1.getIntellect()+threeDestiny.getIntellect());
            destiny1.setTroops(destiny1.getTroops()+threeDestiny.getTroops());
            generals.setDestiny(destiny1);*/

            GeneralsUtil.setTotal(generals,threeDestiny);
        }
        return deviceList;
    }

    //map根据值排序
    private static class ValueComparator implements Comparator<Map.Entry<Device,Integer>> {
        public int compare(Map.Entry<Device,Integer> m,Map.Entry<Device,Integer> n) {
            return n.getValue()-m.getValue();
        }
    }

    /*public static void handleSword2(List<Result> resultList, List<Result> resultList2, List<Result> grilResultList, List<Generals> nmList, List<AppointGenerals> appointGeneralsList, List<AppointSymbols> appointSymbolsList, List<AppointExcludeGenerals> excludeGeneralsList) {
        Map<String,String> generalsMapSort = new LinkedHashMap<>();
        List<Compose> all = NumberUtil.getNoRepeatList(generalsMapSort,nmList,5,appointGeneralsList);
        System.out.println("上阵武将组合个数："+all.size());
        int count = 0;
        int finalCount = all.size();
        List<Generals> generalsList = null;
        BigDecimal b1 = null;
        BigDecimal b2 = null;
        Double d = null;
        //List<List<Compose>> splitList = NumberUtil.splitCompose(all,10);
        //ComposeUtil.each(splitList);
        //for(List<Compose> list : all){
        for(Compose composes : all){
            generalsList = composes.getList();
            count++;

            //极限兵符
            Map<String,Object> symbolsMap = GeneralsUtil.getSymbols(generalsList,appointSymbolsList);
            List<Symbols> symbolsList = (List<Symbols>)symbolsMap.get("symbolsList");
            List<SymbolsTop> symbolsTop = (List<SymbolsTop>)symbolsMap.get("symbolsTop");
            GeneralsUtil.countSymbols(generalsList,symbolsList);
            //战意三维
            GeneralsUtil.getWarpath(generalsList);
            // 总战力 = 武将1战力 + 武将2战力 + 武将3战力 + 武将4战力 + 武将5战力 + 工坊战力（10152）
            // 武将战力 =（总武力+总智力+总兵力）*2+ 命格被动战力 + 战器被动战力
            //int allTotalSword = 0;
            int allTotalSword2 = 0;
            if(excludeGeneralsList.size() > 0){
                //allTotalSword2 = GeneralsUtil.getAllTotalSword4(generalsList,excludeGeneralsList);
            }else{
                allTotalSword2 = GeneralsUtil.getAllTotalSword2(generalsList);
            }
            b1 = new BigDecimal(Double.toString(count));
            b2 = new BigDecimal(Double.toString(finalCount));
            d = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue()*100;

            //女队
            if(composes.isGril()){
                Result result = GeneralsUtil.getResult(generalsList,symbolsList,symbolsTop,0,allTotalSword2);
                if(allTotalSword2 > 390000){
                    grilResultList.add(result);
                }else{
                    result = null;
                }
            }
            System.out.println(count+" / "+finalCount + "  " + (d.intValue())+"%");

            int zhanli = 420000;
            int flag = 0;
            //跳过战力低于zhanli的
            if(allTotalSword2<zhanli && appointGeneralsList.isEmpty()){
                if(allTotalSword2>zhanli){
                    flag = 1;
                }else{
                    //prop.setProperty(composes.getId(), allTotalSword2 + "");
                    composes = null;
                    continue;
                }
            }

            Result result = GeneralsUtil.getResult(generalsList,symbolsList,symbolsTop,0,allTotalSword2);
            if(flag==1){
                resultList2.add(result);
            }else{
                resultList.add(result);
                resultList2.add(result);
            }
            composes = null;
            if((count % 10000) == 0){
                System.gc();
            }
        }
        //}
    }*/
}
