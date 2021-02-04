package com.ming.ppsg2.utils;

import com.ming.ppsg2.entity.ArmsBook;
import com.ming.ppsg2.entity.Destiny;
import com.ming.ppsg2.entity.Generals;
import com.ming.ppsg2.enums.GeneralsEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DestinyData {

    /*//奋威袁绍
    public static Generals getYuanShao(){
        String name = "奋威袁绍";
        Integer code = GeneralsEnum.GeneralsCode.qun_yuanshao.getCode();
        Integer level = 1;
        Integer force = 704;
        Integer intellect = 512;
        Integer troops = 762;
        Integer gender = GeneralsEnum.Gender.boy.getCode();
        Integer generalsType = GeneralsEnum.GeneralsType.GeneralsType_6.getCode();
        Integer arms = GeneralsEnum.Arms.gun.getCode();
        Integer country = GeneralsEnum.Country.qun.getCode();
        Boolean isEntourage = false;
        Integer[] entourages = {

        };
        Integer[] warDevices = {GeneralsEnum.WarDevice.sword.getCode()};
        Integer[][] armsBooks = {
                {GeneralsEnum.ArmsType.DunQiangBing.getCode(),GeneralsEnum.ArmsType.ChangJiBing.getCode()},
                {GeneralsEnum.ArmsBook.JinGu.getCode(),GeneralsEnum.ArmsBook.WuFeng.getCode()},
                {GeneralsEnum.ArmsBook.JinGu.getCode(),GeneralsEnum.ArmsBook.WuFeng.getCode()},
                {GeneralsEnum.ArmsBook.JinGu.getCode(),GeneralsEnum.ArmsBook.JunNue.getCode()},
                {GeneralsEnum.ArmsBook.BingDao.getCode(),GeneralsEnum.ArmsBook.WenFa.getCode()},
                {GeneralsEnum.ArmsBook.JunNue.getCode(),GeneralsEnum.ArmsBook.SanYi.getCode()},
        };
        Object[] destinys = {580,330,645,true,GeneralsEnum.Destiny.disobey.getCode()};

        return getGenerals(name,code,level,force,intellect,troops,gender,generalsType,arms,country,isEntourage,entourages,warDevices,armsBooks,destinys);
    }

    //灵刃吕玲绮
    public static Generals getLvLingQi(){
        String name = "灵刃吕玲绮";
        Integer code = GeneralsEnum.GeneralsCode.qun_lvlingqi.getCode();
        Integer level = 1;
        Integer force = 808;
        Integer intellect = 399;
        Integer troops = 601;
        Integer gender = GeneralsEnum.Gender.gril.getCode();
        Integer generalsType = GeneralsEnum.GeneralsType.GeneralsType_5.getCode();
        Integer arms = GeneralsEnum.Arms.ride.getCode();
        Integer country = GeneralsEnum.Country.qun.getCode();
        Boolean isEntourage = false;
        Integer[] entourages = {

        };
        Integer[] warDevices = {GeneralsEnum.WarDevice.gun.getCode()};
        Integer[][] armsBooks = {
                {GeneralsEnum.ArmsType.ZhongQiBing.getCode(),GeneralsEnum.ArmsType.BiaoQiBing.getCode()},
                {GeneralsEnum.ArmsBook.JinGu.getCode(),GeneralsEnum.ArmsBook.WuFeng.getCode()},
                {GeneralsEnum.ArmsBook.JinGu.getCode(),GeneralsEnum.ArmsBook.WuFeng.getCode()},
                {GeneralsEnum.ArmsBook.JinGu.getCode(),GeneralsEnum.ArmsBook.WuFeng.getCode()},
                {GeneralsEnum.ArmsBook.BingDao.getCode(),GeneralsEnum.ArmsBook.WenFa.getCode()},
                {GeneralsEnum.ArmsBook.JunNue.getCode(),GeneralsEnum.ArmsBook.SanYi.getCode()},
        };
        Object[] destinys = {580,330,645,true,GeneralsEnum.Destiny.disobey.getCode()};

        return getGenerals(name,code,level,force,intellect,troops,gender,generalsType,arms,country,isEntourage,entourages,warDevices,armsBooks,destinys);
    }

    // 劫国董卓
    public static Generals getDongZhuo() {
        String name = "劫国董卓";
        Integer code = GeneralsEnum.GeneralsCode.qun_dongzhuo.getCode();
        Integer level = 1;
        Integer force = 753;
        Integer intellect = 432;
        Integer troops = 639;
        Integer gender = GeneralsEnum.Gender.boy.getCode();
        Integer generalsType = GeneralsEnum.GeneralsType.GeneralsType_5.getCode();
        Integer arms = GeneralsEnum.Arms.ride.getCode();
        Integer country = GeneralsEnum.Country.qun.getCode();
        Boolean isEntourage = false;
        Integer[] entourages = {
                GeneralsEnum.GeneralsCode.wei_caocao.getCode(),
                GeneralsEnum.GeneralsCode.qun_yuanshao.getCode(),
                GeneralsEnum.GeneralsCode.qun_lvbu.getCode(),
                GeneralsEnum.GeneralsCode.qun_dongbai.getCode(),
                GeneralsEnum.GeneralsCode.qun_tianzihanxiandi.getCode(),
        };
        Integer[] warDevices = {GeneralsEnum.WarDevice.knife.getCode()};
        Integer[][] armsBooks = {
                {GeneralsEnum.ArmsType.ZhongQiBing.getCode(),GeneralsEnum.ArmsType.BiaoQiBing.getCode()},
                {GeneralsEnum.ArmsBook.JinGu.getCode(),GeneralsEnum.ArmsBook.WuFeng.getCode()},
                {GeneralsEnum.ArmsBook.JinGu.getCode(),GeneralsEnum.ArmsBook.WuFeng.getCode()},
                {GeneralsEnum.ArmsBook.JinGu.getCode(),GeneralsEnum.ArmsBook.JunNue.getCode()},
                {GeneralsEnum.ArmsBook.BingDao.getCode(),GeneralsEnum.ArmsBook.WenFa.getCode()},
                {GeneralsEnum.ArmsBook.JunNue.getCode(),GeneralsEnum.ArmsBook.SanYi.getCode()},
        };
        Object[] destinys = {580,330,645,true,GeneralsEnum.Destiny.disobey.getCode()};

        return getGenerals(name,code,level,force,intellect,troops,gender,generalsType,arms,country,isEntourage,entourages,warDevices,armsBooks,destinys);
    }

    // 诡骑张飞
    public static Generals getZhangFei() {
        String name = "诡骑张飞";
        Integer code = GeneralsEnum.GeneralsCode.shu_zhangfei.getCode();
        Integer level = 1;
        Integer force = 798;
        Integer intellect = 440;
        Integer troops = 665;
        Integer gender = GeneralsEnum.Gender.boy.getCode();
        Integer generalsType = GeneralsEnum.GeneralsType.GeneralsType_6.getCode();
        Integer arms = GeneralsEnum.Arms.ride.getCode();
        Integer country = GeneralsEnum.Country.shu.getCode();
        Boolean isEntourage = false;
        Integer[] entourages = {

        };
        Integer[] warDevices = {GeneralsEnum.WarDevice.knife.getCode()};
        Integer[][] armsBooks = {
                {GeneralsEnum.ArmsType.ZhongQiBing.getCode(),GeneralsEnum.ArmsType.BiaoQiBing.getCode()},
                {GeneralsEnum.ArmsBook.JinGu.getCode(),GeneralsEnum.ArmsBook.WuFeng.getCode()},
                {GeneralsEnum.ArmsBook.JinGu.getCode(),GeneralsEnum.ArmsBook.WuFeng.getCode()},
                {GeneralsEnum.ArmsBook.JinGu.getCode(),GeneralsEnum.ArmsBook.WenFa.getCode()},
                {GeneralsEnum.ArmsBook.BingDao.getCode(),GeneralsEnum.ArmsBook.WenFa.getCode()},
                {GeneralsEnum.ArmsBook.JinGu.getCode(),GeneralsEnum.ArmsBook.WuFeng.getCode()},
        };
        Object[] destinys = {580,330,645,true,GeneralsEnum.Destiny.disobey.getCode()};

        return getGenerals(name,code,level,force,intellect,troops,gender,generalsType,arms,country,isEntourage,entourages,warDevices,armsBooks,destinys);
    }

    // 虎涧典韦
    public static Generals getDianWei() {
        String name = "虎涧典韦";
        Integer code = GeneralsEnum.GeneralsCode.wei_dianwei.getCode();
        Integer level = 1;
        Integer force = 809;
        Integer intellect = 470;
        Integer troops = 750;
        Integer gender = GeneralsEnum.Gender.boy.getCode();
        Integer generalsType = GeneralsEnum.GeneralsType.GeneralsType_6.getCode();
        Integer arms = GeneralsEnum.Arms.gun.getCode();
        Integer country = GeneralsEnum.Country.wei.getCode();
        Boolean isEntourage = false;
        Integer[] entourages = {

        };
        Integer[] warDevices = {GeneralsEnum.WarDevice.gun.getCode()};
        Integer[][] armsBooks = {
                {GeneralsEnum.ArmsType.DunQiangBing.getCode(),GeneralsEnum.ArmsType.ChangJiBing.getCode()},
                {GeneralsEnum.ArmsBook.JinGu.getCode(),GeneralsEnum.ArmsBook.WuFeng.getCode()},
                {GeneralsEnum.ArmsBook.JinGu.getCode(),GeneralsEnum.ArmsBook.WuFeng.getCode()},
                {GeneralsEnum.ArmsBook.BingDao.getCode(),GeneralsEnum.ArmsBook.WenFa.getCode()},
                {GeneralsEnum.ArmsBook.BingDao.getCode(),GeneralsEnum.ArmsBook.WenFa.getCode()},
                {GeneralsEnum.ArmsBook.JinGu.getCode(),GeneralsEnum.ArmsBook.JunNue.getCode()},
        };
        Object[] destinys = {490,360,735,true,GeneralsEnum.Destiny.disobey.getCode()};

        return getGenerals(name,code,level,force,intellect,troops,gender,generalsType,arms,country,isEntourage,entourages,warDevices,armsBooks,destinys);
    }

    // 顾影貂蝉
    public static Generals getDiaoChan() {
        String name = "顾影貂蝉";
        Integer code = GeneralsEnum.GeneralsCode.qun_diaochan.getCode();
        Integer level = 1;
        Integer force = 582;
        Integer intellect = 626;
        Integer troops = 526;
        Integer gender = GeneralsEnum.Gender.gril.getCode();
        Integer generalsType = GeneralsEnum.GeneralsType.GeneralsType_7.getCode();
        Integer arms = GeneralsEnum.Arms.arch.getCode();
        Integer country = GeneralsEnum.Country.qun.getCode();
        Boolean isEntourage = false;
        Integer[] entourages = {

        };
        Integer[] warDevices = {GeneralsEnum.WarDevice.fan.getCode()};
        Integer[][] armsBooks = {
                {GeneralsEnum.ArmsType.HuoShiBing.getCode(),GeneralsEnum.ArmsType.LianNuBing.getCode()},
                {GeneralsEnum.ArmsBook.JinGu.getCode(),GeneralsEnum.ArmsBook.WuFeng.getCode()},
                {GeneralsEnum.ArmsBook.JunNue.getCode(),GeneralsEnum.ArmsBook.SanYi.getCode()},
                {GeneralsEnum.ArmsBook.JunNue.getCode(),GeneralsEnum.ArmsBook.SanYi.getCode()},
                {GeneralsEnum.ArmsBook.BingDao.getCode(),GeneralsEnum.ArmsBook.WenFa.getCode()},
                {GeneralsEnum.ArmsBook.JunNue.getCode(),GeneralsEnum.ArmsBook.SanYi.getCode()},
        };
        Object[] destinys = {450,460,645,true,GeneralsEnum.Destiny.disobey.getCode()};

        return getGenerals(name,code,level,force,intellect,troops,gender,generalsType,arms,country,isEntourage,entourages,warDevices,armsBooks,destinys);
    }*/

    public static Generals getGenerals(String name,List<Integer> codes,Integer level,Integer force,Integer intellect,Integer troops,Integer gender,
        Integer generalsType,Integer arms,Integer country,Boolean isEntourage,Integer[] entourages,Integer[] warDevices,
        Integer[][] armsBooks,Object[] destinys,Integer skinCode
    ){
        Generals generals = new Generals();
        generals.setName(name);
        generals.setCodes(codes);
        generals.setLevel(level);
        generals.setForce(force);
        generals.setIntellect(intellect);
        generals.setTroops(troops);
        generals.setGender(gender);
        generals.setGeneralsType(generalsType);
        generals.setArms(arms);
        generals.setCountry(country);
        generals.setIsEntourage(isEntourage);
        List<Integer> entourageList = new ArrayList<>(Arrays.asList(entourages));
        generals.setEntourages(entourageList);
        List<Integer> devices = new ArrayList<>(Arrays.asList(warDevices));
        generals.setWarDevices(devices);
        ArmsBook armsBook = new ArmsBook();
        armsBook.setArmsType1(armsBooks[0][0]);
        armsBook.setArmsType2(armsBooks[0][1]);
        armsBook.setArmsBookOne1(armsBooks[1][0]);
        armsBook.setArmsBookOne2(armsBooks[1][1]);
        armsBook.setArmsBookTwo1(armsBooks[2][0]);
        armsBook.setArmsBookTwo2(armsBooks[2][1]);
        armsBook.setArmsBookThree1(armsBooks[3][0]);
        armsBook.setArmsBookThree2(armsBooks[3][1]);
        armsBook.setArmsBookFour1(armsBooks[4][0]);
        armsBook.setArmsBookFour2(armsBooks[4][1]);
        armsBook.setArmsBookFive1(armsBooks[5][0]);
        armsBook.setArmsBookFive2(armsBooks[5][1]);
        generals.setArmsBook(armsBook);

        Destiny destiny = new Destiny();
        destiny.setForce((Integer) destinys[0]);
        destiny.setIntellect((Integer) destinys[1]);
        destiny.setTroops((Integer) destinys[2]);
        destiny.setIsDestiny((Boolean) destinys[3]);
        destiny.setDisobey((Integer) destinys[4]);
        for(GeneralsEnum.Material material : GeneralsEnum.Material.values()){
            if(material.getName().equals(destinys[5])){
                destiny.setCode1(material.getCode());
            }
            if(material.getName().equals(destinys[6])){
                destiny.setCode2(material.getCode());
            }
            if(material.getName().equals(destinys[7])){
                destiny.setCode3(material.getCode());
            }
        }
        generals.setDestiny(destiny);
        generals.setSkinCode(skinCode);

        generals.setTotalSword(0);
        generals.setTotalForce(0);
        generals.setTotalIntellect(0);
        generals.setTotalTroops(0);
        return generals;
    }


}
