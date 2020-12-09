package com.ming.ppsg2.main;

import com.ming.ppsg2.entity.ArmsBook;
import com.ming.ppsg2.entity.Destiny;
import com.ming.ppsg2.entity.Generals;
import com.ming.ppsg2.enums.GeneralsEnum;
import com.ming.ppsg2.utils.DestinyData;
import com.ming.ppsg2.utils.GeneralsUtil;
import com.ming.ppsg2.utils.ReadWriteExcel;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainService {

    public static void main(String[] args) {
        List<Generals> generalsList = getBaseData();
        System.out.println(generalsList);
    }

    /**
     * 从excel获取基础数据
     */
    public static List<Generals> getBaseData() {
        ReadWriteExcel readWriteExcel = new ReadWriteExcel();
        readWriteExcel.setRow(1);
        List<List<String>> dataList = readWriteExcel.readRelativeExcel("/excel/data_temp2.xlsx");
        List<Map<String, String>> lists = readWriteExcel.transformMap(dataList);

        List<Generals> generalsAll = new ArrayList<>();
        for (Map<String,String> map : lists) {
            if(null == map.get("armsBook1") || "".equals(map.get("armsBook1")) || "FALSE".equalsIgnoreCase(map.get("usable"))){
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
            GeneralsUtil.getBattleArrayWay(generals);//阵法三维
            //generals.setWarpath(new Warpath());

            if(name.endsWith("_限")){
                generals.setLimit(true);
            }else{
                generals.setLimit(false);
            }
            generals.setId(Double.valueOf(map.get("id")).intValue()+"");

            /*if(1 == disobeyCode || 2 == disobeyCode || 3 == disobeyCode){
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
            }*/

            ArmsBook copyBook = new ArmsBook();
            BeanUtils.copyProperties(generals.getArmsBook(),copyBook);
            generals.setArmsBook(copyBook);

            Destiny copyDestiny = new Destiny();
            BeanUtils.copyProperties(generals.getDestiny(),copyDestiny);
            generals.setDestiny(copyDestiny);
            generalsAll.add(generals);
        }
        return generalsAll;
    }

}
