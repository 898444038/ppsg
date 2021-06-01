package com.ming.ppsg2.enums;

public enum GeneralsEnum {

    threeCircles("三维属性"),
    generalsType("武将类型"),
    generalsCode("武将编码"),
    type("类型"),//类型
    combat("战力"),//战力
    level("星级"),//等级
    country("国家"),//国家
    arms("基础兵种"),//兵种
    warDevice("战器"),//战器
    armsBook("兵书"),//兵书
    bookType("兵书类型"),//兵书类型
    armsType("兵书兵种"),//兵书兵种
    destiny("命格"),//命格
    warpath("战意"),//战意
    symbols("兵符"),//兵符
    material("逆命突破材料"),
    countrys("国家队"),//国家队
    skin("幻化"),//幻化
    gender("性别"),//性别
    battleArrayWay("阵法"),//阵法
    ;
    private String groupName;

    private GeneralsEnum(String groupName) {
        this.groupName = groupName;
    }
    String getGroupName() {
        return this.groupName;
    }

    public enum ThreeCircles implements GeneralsEnumInterface {
        Force(1,"武力"),
        Intellect(2,"智力"),
        Troops(3,"兵力");
        private Integer code;
        private String name;
        ThreeCircles(){}
        ThreeCircles(Integer code,String name) {
            this.code = code;
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }
    }

    public enum GeneralsType implements GeneralsEnumInterface {
        GeneralsType_1(1,"勇将型",3,1,2),
        GeneralsType_2(2,"将军型",2,1,3),
        GeneralsType_3(3,"智将型",2,2,2),
        GeneralsType_4(4,"策士型",1,3,2),
        GeneralsType_5(5,"强袭勇将型",4,2,3),
        GeneralsType_6(6,"统帅将军型",3,2,4),
        GeneralsType_7(7,"鬼才智将型",3,3,3),
        GeneralsType_8(8,"天命策士型",2,4,3);
        private Integer code;
        private String name;
        private Integer force;//武力每级成长
        private Integer intellect;//智力每级成长
        private Integer troops;//兵力每级成长
        GeneralsType(){}
        GeneralsType(Integer code, String name, Integer force, Integer intellect, Integer troops) {
            this.code = code;
            this.name = name;
            this.force = force;
            this.intellect = intellect;
            this.troops = troops;
        }

        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }
        public Integer getForce() {
            return force;
        }
        public Integer getIntellect() {
            return intellect;
        }
        public Integer getTroops() {
            return troops;
        }

        public GeneralsType getGeneralsType(int code){
            for (GeneralsType type : GeneralsType.values()){
                if(type.code == code){
                    return type;
                }
            }
            return null;
        }
    }

    public enum Type implements GeneralsEnumInterface {
        Awaken(1,"觉醒"),
        Fall(2,"堕天"),
        disobey(3,"逆命");
        private Integer code;
        private String name;
        Type(){}
        Type(Integer code,String name) {
            this.code = code;
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }
    }

    public enum Level implements GeneralsEnumInterface {
        one_star(1,"一星"),
        two_star(2,"二星"),
        three_star(3,"三星"),
        four_star(4,"四星"),
        five_star(5,"五星");
        private Integer code;
        private String name;
        Level(){}
        Level(Integer code,String name) {
            this.code = code;
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }
    }

    public enum Country implements GeneralsEnumInterface {
        wei(1,"魏"),
        shu(2,"蜀"),
        wu(3,"吴"),
        qun(4,"群");
        private Integer code;
        private String name;
        Country(){}
        Country(Integer code,String name) {
            this.code = code;
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }
    }

    public enum Countrys implements GeneralsEnumInterface {
        weiCountry(1,"魏国国家队"),
        shuCountry(2,"蜀国国家队"),
        wuCountry(3,"吴国国家队"),
        qunCountry(4,"群雄国家队"),
        weiMashupCountry(5,"魏国混搭队"),
        shuMashupCountry(6,"蜀国混搭队"),
        wuMashupCountry(7,"吴国混搭队"),
        qunMashupCountry(8,"群雄混搭队"),

        ;
        private Integer code;
        private String name;
        Countrys(){}
        Countrys(Integer code,String name) {
            this.code = code;
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }
    }

    public enum Arms implements GeneralsEnumInterface {
        gun(1,"枪",0.6,0.4,0.8),
        arch(2,"弓",0.6,0.8,0.4),
        ride(3,"骑",0.8,0.4,0.6);
        private Integer code;
        private String name;
        private Double forceRate;//武力科技加成
        private Double intellectRate;//智力科技加成
        private Double troopsRate;//兵力科技加成
        Arms(){}
        Arms(Integer code, String name, Double forceRate, Double intellectRate, Double troopsRate) {
            this.code = code;
            this.name = name;
            this.forceRate = forceRate;
            this.intellectRate = intellectRate;
            this.troopsRate = troopsRate;
        }
        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }
        public Double getForceRate() {
            return forceRate;
        }
        public Double getIntellectRate() {
            return intellectRate;
        }
        public Double getTroopsRate() {
            return troopsRate;
        }
    }


    public enum WarDevice implements GeneralsEnumInterface {
        knife(1,"刀","刀",133,106,160,224,178,494,1.0,1.0,1.0,288,288,288,458,458,1220,null,null,null,null,null,null,null,null,null),
        sword(2,"剑","剑",133,133,133,227,224,445,1.0,1.0,1.0,288,288,288,458,458,1220,null,null,null,null,null,null,null,null,null),
        gun(3,"枪","枪",160,106,133,273,178,445,1.0,1.0,1.0,288,288,288,458,458,1220,null,null,null,null,null,null,null,null,null),
        arch(4,"弓","弓",133,133,133,224,227,445,1.0,1.0,1.0,288,288,288,458,458,1220,null,null,null,null,null,null,null,null,null),
        fan(5,"扇","扇",133,160,106,224,273,399,1.0,1.0,1.0,288,288,288,458,458,1220,null,null,null,null,null,null,null,null,null),
        //异化战器
//        knife2(6,"刀2","刀",133,106,160,224,178,494,1.0,1.0,1.0,288,288,288,458,458,1220),
//        sword2(7,"剑2","特殊剑",133,106,160,227,224,445,1.0,1.0,1.0,288,288,288,458,458,1220),
//        gun2(8,"枪2","特殊枪",133,133,133,273,178,445,1.0,1.0,1.0,288,288,288,458,458,1220),
//        arch2(9,"弓2","特殊弓",160,106,133,224,227,445,1.0,1.0,1.0,288,288,288,458,458,1220),
//        fan2(10,"扇2","特殊扇",133,133,133,224,273,399,1.0,1.0,1.0,288,288,288,458,458,1220),
        knife2(6,"刀2","刀",133,106,160,224,178,494,1.0,1.0,1.0,288,288,288,458,458,1220,65,65,380,892,796,4764,24,18,176),
        sword2(7,"剑2","特殊剑",133,106,160,227,224,445,1.0,1.0,1.0,288,288,288,458,458,1220,80,80,320,1084,1084,3800,36,36,120),
        gun2(8,"枪2","枪",160,106,133,273,178,445,1.0,1.0,1.0,288,288,288,458,458,1220,125,60,270,1522,646,3800,60,12,120),
        arch2(9,"弓2","弓",133,133,133,224,227,445,1.0,1.0,1.0,288,288,288,458,458,1220,115,115,180,1300,1300,2936,49,49,70),
        fan2(10,"扇2","特殊扇",133,133,133,224,273,399,1.0,1.0,1.0,288,288,288,458,458,1220,60,125,270,828,1532,3443,20,64,99),
        ;

        private Integer code;
        private String name;
        private String desc;
        private Integer force;//基础战器武力
        private Integer intellect;//基础战器智力
        private Integer troops;//基础战器兵力
        private Integer strengthenForce;//强化15战器武力
        private Integer strengthenIntellect;//强化15战器智力
        private Integer strengthenTroops;//强化15战器兵力
        private Double quenchingForceRate;//淬炼战器武力百分比
        private Double quenchingIntellectRate;//淬炼战器智力百分比
        private Double quenchingTroopsRate;//淬炼战器兵力百分比
        private Integer exclusiveForce;//专属战器武力
        private Integer exclusiveIntellect;//专属战器智力
        private Integer exclusiveTroops;//专属战器兵力
        private Integer passive1;//被动1战力
        private Integer passive2;//被动2战力
        private Integer passive3;//被动3战力
        private Integer awakenForce;//觉醒武力
        private Integer awakenIntellect;//觉醒智力
        private Integer awakenTroops;//觉醒兵力
        private Integer refinerForce;//炼器武力
        private Integer refinerIntellect;//炼器智力
        private Integer refinerTroops;//炼器兵力
        private Integer refiner5Force;//炼器5武力
        private Integer refiner5Intellect;//炼器5智力
        private Integer refiner5Troops;//炼器5兵力

        WarDevice(){}

        WarDevice(Integer code, String name, String desc,
                  Integer force, Integer intellect, Integer troops,
                  Integer strengthenForce, Integer strengthenIntellect, Integer strengthenTroops,
                  Double quenchingForceRate, Double quenchingIntellectRate, Double quenchingTroopsRate,
                  Integer exclusiveForce, Integer exclusiveIntellect, Integer exclusiveTroops,
                  Integer passive1, Integer passive2, Integer passive3,
                  Integer awakenForce, Integer awakenIntellect, Integer awakenTroops,
                  Integer refinerForce,Integer refinerIntellect,Integer refinerTroops,
                  Integer refiner5Force,Integer refiner5Intellect,Integer refiner5Troops
        ) {
            this.code = code;
            this.name = name;
            this.desc = desc;
            this.force = force;
            this.intellect = intellect;
            this.troops = troops;
            this.strengthenForce = strengthenForce;
            this.strengthenIntellect = strengthenIntellect;
            this.strengthenTroops = strengthenTroops;
            this.quenchingForceRate = quenchingForceRate;
            this.quenchingIntellectRate = quenchingIntellectRate;
            this.quenchingTroopsRate = quenchingTroopsRate;
            this.exclusiveForce = exclusiveForce;
            this.exclusiveIntellect = exclusiveIntellect;
            this.exclusiveTroops = exclusiveTroops;
            this.passive1 = passive1;
            this.passive2 = passive2;
            this.passive3 = passive3;
            this.awakenForce = awakenForce;
            this.awakenIntellect = awakenIntellect;
            this.awakenTroops = awakenTroops;
            this.refinerForce = refinerForce;
            this.refinerIntellect = refinerIntellect;
            this.refinerTroops = refinerTroops;
            this.refiner5Force = refiner5Force;
            this.refiner5Intellect = refiner5Intellect;
            this.refiner5Troops = refiner5Troops;
        }

        public Integer getRefiner5Force() {
            return refiner5Force;
        }

        public Integer getRefiner5Intellect() {
            return refiner5Intellect;
        }

        public Integer getRefiner5Troops() {
            return refiner5Troops;
        }

        public String getName() {
            return this.name;
        }
        public String getDesc() {
            return this.desc;
        }
        public Integer getCode() {
            return this.code;
        }
        public Integer getForce() {
            return force;
        }
        public Integer getIntellect() {
            return intellect;
        }
        public Integer getTroops() {
            return troops;
        }
        public Integer getStrengthenForce() {
            return strengthenForce;
        }
        public Integer getStrengthenIntellect() {
            return strengthenIntellect;
        }
        public Integer getStrengthenTroops() {
            return strengthenTroops;
        }

        public Double getQuenchingForceRate() {
            return quenchingForceRate;
        }

        public Double getQuenchingIntellectRate() {
            return quenchingIntellectRate;
        }

        public Double getQuenchingTroopsRate() {
            return quenchingTroopsRate;
        }

        public Integer getExclusiveForce() {
            return exclusiveForce;
        }

        public Integer getExclusiveIntellect() {
            return exclusiveIntellect;
        }

        public Integer getExclusiveTroops() {
            return exclusiveTroops;
        }

        public Integer getPassive1() {
            return passive1;
        }

        public Integer getPassive2() {
            return passive2;
        }

        public Integer getPassive3() {
            return passive3;
        }

        public Integer getAwakenForce() {
            return awakenForce;
        }

        public Integer getAwakenIntellect() {
            return awakenIntellect;
        }

        public Integer getAwakenTroops() {
            return awakenTroops;
        }

        public Integer getRefinerForce() {
            return refinerForce;
        }

        public Integer getRefinerIntellect() {
            return refinerIntellect;
        }

        public Integer getRefinerTroops() {
            return refinerTroops;
        }
    }

    public enum Gender implements GeneralsEnumInterface {
        boy(1,"男"),
        gril(2,"女");
        private Integer code;
        private String name;
        Gender(){}
        Gender(Integer code,String name) {
            this.code = code;
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }
    }

    public enum BookType implements GeneralsEnumInterface {
        jiao_li(1,"角力",5,1),
        qi_shu(2,"骑术",5,1),
        gong_shu(3,"弓术",4,2),
        zhan_lue(4,"战略",4,2),
        jing_zhun(5,"精准",4,6),
        fang_yu(6,"防御",6,3),
        qiang_shu(7,"枪术",6,3),
        zhen_lie(8,"阵列",6,5),
        duan_bing(9,"短兵",5,4),
        ;
        private Integer code;
        private String name;
        private String desc;
        private Integer code1;
        private Integer code2;
        BookType(){}
        BookType(Integer code, String name, Integer code1, Integer code2) {
            this.code = code;
            this.name = name;
            this.code1 = code1;
            this.code2 = code2;
        }

        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }

        public String getDesc() {
            return desc;
        }

        public Integer getCode1() {
            return code1;
        }

        public Integer getCode2() {
            return code2;
        }
    }

    public enum ArmsBook implements GeneralsEnumInterface {
        WuFeng(1,"武锋","蓝色",160,0,0),
        SanYi(2,"三疑","红色",0,160,0),
        BingDao(3,"兵道","黄色",0,0,160),
        JunNue(4,"军略","紫色",80,80,0),
        JinGu(5,"金鼓","绿色",40,0,120),
        WenFa(6,"文伐","橙色",0,40,120);
        private Integer code;
        private String name;
        private String desc;
        private Integer force;
        private Integer intellect;
        private Integer troops;
        ArmsBook(){}
        ArmsBook(Integer code, String name,String desc, Integer force, Integer intellect, Integer troops) {
            this.code = code;
            this.name = name;
            this.desc = desc;
            this.force = force;
            this.intellect = intellect;
            this.troops = troops;
        }

        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }

        public String getDesc() {
            return desc;
        }

        public Integer getForce() {
            return force;
        }
        public Integer getIntellect() {
            return intellect;
        }
        public Integer getTroops() {
            return troops;
        }
    }


    public enum ArmsType implements GeneralsEnumInterface {
        DunQiangBing(1,"盾枪兵",0.07,0.03,0.10),
        ChangJiBing(2,"长戟兵",0.10,0.03,0.07),
        ZhongQiBing(3,"重骑兵",0.07,0.03,0.10),
        BiaoQiBing(4,"骠骑兵",0.10,0.03,0.07),
        HuoShiBing(5,"火矢兵",0.07,0.10,0.03),
        LianNuBing(6,"连弩兵",0.10,0.07,0.03);
        private Integer code;
        private String name;
        private Double forceRate;
        private Double intellectRate;
        private Double troopsRate;
        ArmsType(){}

        ArmsType(Integer code, String name, Double forceRate, Double intellectRate, Double troopsRate) {
            this.code = code;
            this.name = name;
            this.forceRate = forceRate;
            this.intellectRate = intellectRate;
            this.troopsRate = troopsRate;
        }

        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }

        public Double getForceRate() {
            return forceRate;
        }

        public Double getIntellectRate() {
            return intellectRate;
        }

        public Double getTroopsRate() {
            return troopsRate;
        }
    }


    public enum Destiny implements GeneralsEnumInterface {

        not(0,"未开命格",0,0,0,0,0,0,0,0,0,0,0),
        disobey(1,"逆命",1220,1220,0,0,300,680,1220,1920,1920,100,10),
        breach(2,"突破",0,0,0,0,300,680,680,680,680,4,5),
        disobey5(3,"逆命5",1220,1220,1220,1220,300,680,1220,1920,2380,200,40),
        fit(4,"合体",1220,1220,1220,1220,300,680,1220,1920,2380,200,40)
        ;

        private Integer code;
        private String name;
        private Integer effect1;//命格被动1
        private Integer effect2;//命格被动2
        private Integer effect3;//命格被动3
        private Integer effect4;//命格被动4
        private Integer level1;//逆命1，突破1
        private Integer level2;//逆命2，突破2
        private Integer level3;//逆命3
        private Integer level4;//逆命4
        private Integer level5;//逆命5
        private Integer chip;//所需碎片或印
        private Integer crapeMyrtle;//紫薇之御

        Destiny(){}

        Destiny(Integer code, String name, Integer effect1, Integer effect2,Integer effect3,Integer effect4, Integer level1, Integer level2, Integer level3, Integer level4,Integer level5, Integer chip, Integer crapeMyrtle) {
            this.code = code;
            this.name = name;
            this.effect1 = effect1;
            this.effect2 = effect2;
            this.effect3 = effect3;
            this.effect4 = effect4;
            this.level1 = level1;
            this.level2 = level2;
            this.level3 = level3;
            this.level4 = level4;
            this.level5 = level5;
            this.chip = chip;
            this.crapeMyrtle = crapeMyrtle;
        }

        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }

        public Integer getEffect3() {
            return effect3;
        }

        public Integer getEffect4() {
            return effect4;
        }

        public Integer getLevel5() {
            return level5;
        }

        public Integer getEffect1() {
            return effect1;
        }

        public Integer getEffect2() {
            return effect2;
        }

        public Integer getLevel1() {
            return level1;
        }

        public Integer getLevel2() {
            return level2;
        }

        public Integer getLevel3() {
            return level3;
        }

        public Integer getLevel4() {
            return level4;
        }

        public Integer getChip() {
            return chip;
        }

        public Integer getCrapeMyrtle() {
            return crapeMyrtle;
        }
    }


    //战意
    public enum Warpath implements GeneralsEnumInterface {

        countryForce(1,"同国家上阵武将武力加成10%",0.1),
        countryIntellect(2,"同国家上阵武将智力加成10%",0.1),
        countryTroops(3,"同国家上阵武将兵力加成10%",0.1),
        armsForce(4,"同兵种上阵武将武力加成10%",0.1),
        armsIntellect(5,"同兵种上阵武将智力加成10%",0.1),
        armsTroops(6,"同兵种上阵武将兵力加成10%",0.1);

        private Integer code;
        private String name;
        private Double rate;
        Warpath(){}

        Warpath(Integer code, String name, Double rate) {
            this.code = code;
            this.name = name;
            this.rate = rate;
        }

        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }

        public Double getRate() {
            return rate;
        }
    }


    // 兵符
    public enum Symbols implements GeneralsEnumInterface {

        number1(1,"1号位",360,0,0,0d,0d,0d,0d),
        number2(2,"2号位",0,0,0,0.211,0.211,0.318,0.106),
        number3(3,"3号位",0,360,0,0d,0d,0d,0d),
        number4(4,"4号位",0,0,0,0.232,0.232,0.35,0.116),
        number5(5,"5号位",0,0,551,0d,0d,0d,0d),
        number6(6,"6号位",0,0,0,0.253,0.253,0.382,0.126);

        private Integer code;
        private String name;

        // 主属性
        private Integer force;//武力增加
        private Integer intellect;//智力增加
        private Integer troops;//兵力增加
        private Double addForceRate;//武力加成百分比
        private Double addIntellectRate;//智力加成百分比
        private Double addTroopsRate;//兵力加成百分比
        private Double allRate;//全属性加成百分比

        Symbols(){}

        Symbols(Integer code, String name, Integer force, Integer intellect, Integer troops, Double addForceRate, Double addIntellectRate, Double addTroopsRate, Double allRate) {
            this.code = code;
            this.name = name;
            this.force = force;
            this.intellect = intellect;
            this.troops = troops;
            this.addForceRate = addForceRate;
            this.addIntellectRate = addIntellectRate;
            this.addTroopsRate = addTroopsRate;
            this.allRate = allRate;
        }

        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }

        public Integer getForce() {
            return force;
        }

        public Integer getIntellect() {
            return intellect;
        }

        public Integer getTroops() {
            return troops;
        }

        public Double getAddForceRate() {
            return addForceRate;
        }

        public Double getAddIntellectRate() {
            return addIntellectRate;
        }

        public Double getAddTroopsRate() {
            return addTroopsRate;
        }

        public Double getAllRate() {
            return allRate;
        }
    }

    public enum SymbolsMainAttr implements GeneralsEnumInterface {

        force(1 , "武力增加360" , 360 , 0d),//1号位
        intellect(2 , "智力增加360" , 360 , 0d),//3号位
        troops(3 , "兵力增加551" , 551 , 0d),//5号位

        forceRate1(4 , "武力加成21.1%" , 0 , 0.211),//2号位
        forceRate2(5 , "武力加成23.2%" , 0 , 0.232),//4号位
        forceRate3(6 , "武力加成25.3%" , 0 , 0.253),//6号位

        intellectRate1(7 , "智力加成21.1%" , 0 , 0.211),//2号位
        intellectRate2(8 , "智力加成23.2%" , 0 , 0.232),//4号位
        intellectRate3(9 , "智力加成25.3%" , 0 , 0.253),//6号位

        troopsRate1(10 , "兵力加成31.8%" , 0 , 0.318),//2号位
        troopsRate2(11 , "兵力加成35.0%" , 0 , 0.350),//4号位
        troopsRate3(12 , "兵力加成38.2%" , 0 , 0.382),//6号位

        allRate1(13 , "全属性加成10.6%" , 0 , 0.106),//2号位
        allRate2(14 , "全属性加成11.6%" , 0 , 0.116),//4号位
        allRate3(15 , "全属性加成12.6%" , 0 , 0.126),//6号位
        ;


        private Double addForceRate;//武力加成百分比
        private Double addIntellectRate;//智力加成百分比
        private Double addTroopsRate;//兵力加成百分比
        private Double allRate;//全属性加成百分比

        private Integer code;
        private String name;
        private Integer value;
        private Double rate;
        SymbolsMainAttr(){}

        SymbolsMainAttr(Integer code, String name,Integer value, Double rate) {
            this.code = code;
            this.name = name;
            this.value = value;
            this.rate = rate;
        }

        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }

        public Integer getValue() {
            return value;
        }

        public Double getRate() {
            return rate;
        }

        public static SymbolsMainAttr get(int code){
            for(GeneralsEnum.SymbolsMainAttr mainAttr : GeneralsEnum.SymbolsMainAttr.values()) {
                if(mainAttr.getCode() == code){
                    return mainAttr;
                }
            }
            return null;
        }
    }

    // 兵符副属性
    public enum SymbolsSecondAttr implements GeneralsEnumInterface {

        force(1,"武力增加64",64,0d),
        forceRate(2,"武力加成10.6%",0,0.106),

        intellect(3,"智力增加64",64,0d),
        intellectRate(4,"智力加成10.6%",0,0.106),

        troops(5,"兵力增加105",105,0d),
        troopsRate(6,"兵力加成15.9%",0,0.159),

        wuAll(7,"吴国全属性52",52,0d),
        wuAllRate(8,"吴国全属性加成7.5%",0,0.075),

        shuAll(9,"蜀国全属性52",52,0d),
        shuAllRate(10,"蜀国全属性加成7.5%",0,0.075),

        weiAll(11,"魏国全属性52",52,0d),
        weiAllRate(12,"魏国全属性加成7.5%",0,0.075),

        qunAll(13,"群雄全属性52",52,0d),
        qunAllRate(14,"群雄全属性加成7.5%",0,0.075),
        ;


        private Integer code;
        private String name;
        private Integer value;
        private Double rate;
        SymbolsSecondAttr(){}

        SymbolsSecondAttr(Integer code, String name,Integer value, Double rate) {
            this.code = code;
            this.value = value;
            this.name = name;
            this.rate = rate;
        }

        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }

        public Integer getValue() {
            return value;
        }

        public Double getRate() {
            return rate;
        }

        public static SymbolsSecondAttr get(int code){
            for(GeneralsEnum.SymbolsSecondAttr secondAttr : GeneralsEnum.SymbolsSecondAttr.values()) {
                if(secondAttr.getCode() == code){
                    return secondAttr;
                }
            }
            return null;
        }
    }

    // 兵符类型
    public enum SymbolsType implements GeneralsEnumInterface {
        cangLong(1,"苍龙","蜀国全属性加10%",0.1),
        mengHu(2,"猛虎","吴国全属性加10%",0.1),
        huoFeng(3,"火凤","魏国全属性加10%",0.1),
        tianLang(4,"天狼","群雄全属性加10%",0.1),

        xianGui(5,"玄龟","枪兵全属性加10%",0.1),
        xiangYing(6,"翔鹰","弓兵全属性加10%",0.1),
        qiLin(7,"麒麟","骑兵全属性加10%",0.1),
        qingLuan(8,"青鸾","女性全属性加10%",0.1),

        baiZe(9,"白泽","全体智力加24%",0.24),
        hunDUN(10,"混沌","全体全属性加8%",0.08),
        qiongQi(11,"穷奇","全体武力加24%",0.24),
        yaCi(12,"睚眦","全体兵力加24%",0.24),

        píxiū(13,"貔貅","骑兵全属性加12%",0.12),
        zhēng(14,"狰","枪兵全属性加12%",0.12),
        gǔdiāo(15,"蛊雕","弓兵全属性加12%",0.12),
        ;

        private Integer code;
        private String name;
        private String desc;
        private Double rate;
        SymbolsType(){}
        SymbolsType(Integer code, String name,String desc,Double rate) {
            this.code = code;
            this.name = name;
            this.desc = desc;
            this.rate = rate;
        }

        public String getName() {
            return this.name;
        }
        public String getDesc() {
            return this.desc;
        }
        public Integer getCode() {
            return this.code;
        }

        public Double getRate() {
            return rate;
        }
    }

    public enum GeneralsCode implements GeneralsEnumInterface {
        wei_caocao(1001,"曹操"),
        wei_simayi(1002,"司马懿"),
        wei_zhangliao(1003,"张辽"),
        wei_xiahoudun(1004,"夏侯惇"),
        wei_dianwei(1005,"典韦"),
        wei_caopi(1006,"曹丕"),
        wei_guojia(1007,"郭嘉"),
        wei_xunyu(1008,"荀彧"),
        wei_xiahouyuan(1009,"夏侯渊"),
        wei_zhanghe(1010,"张郃"),
        wei_xiahoushi(1011,"夏侯氏"),
        wei_wangyuanji(1012,"王元姬"),
        wei_xuhuang(1013,"徐晃"),
        wei_zhangchunhua(1014,"张春华"),
        wei_beimihu(1015,"卑弥呼"),
        wei_shaonianzhangliao(1016,"少年张辽"),
        wei_caoren(1017,"曹仁"),
        wei_bianfuren(1018,"卞夫人"),
        wei_jiaxu(1019,"贾诩"),
        wei_zhenji(1020,"甄姬"),
        wei_xuchu(1021,"许褚"),

        shu_guanyu(2001,"关羽"),
        shu_zhugeliang(2002,"诸葛亮"),
        shu_zhaoyun(2003,"赵云"),
        shu_jiangwei(2004,"姜维"),
        shu_machao(2005,"马超"),
        shu_zhugeguo(2006,"诸葛果"),
        shu_liubei(2007,"刘备"),
        shu_weiyan(2008,"魏延"),
        shu_sunshangxiang(2009,"孙尚香"),
        shu_shaonianguanyu(2010,"少年关羽"),
        shu_shaonianzhaoyun(2011,"少年赵云"),
        shu_zhangfei(2012,"张飞"),
        shu_pangtong(2013,"庞统"),
        shu_huangzhong(2014,"黄忠"),
        shu_liushan(2015,"刘禅"),
        shu_huangwudie(2016,"黄舞蝶"),
        shu_mayunlu(2017,"马云禄"),
        shu_huangyueying(2018,"黄月英"),
        shu_fazheng(2019,"法正"),
        shu_zhangxingcai(2020,"张星彩"),
        shu_guanping(2021,"关平"),

        wu_lvmeng(3001,"吕蒙"),
        wu_zhouyu(3002,"周瑜"),
        wu_sunquan(3003,"孙权"),
        wu_taishici(3004,"太史慈"),
        wu_sunce(3005,"孙策"),
        wu_luxun(3006,"陆逊"),
        wu_sunjian(3007,"孙坚"),
        wu_ganning(3008,"甘宁"),
        wu_bulianshi(3009,"步练师"),
        wu_zhoutai(3010,"周泰"),
        wu_sunliang(3011,"孙亮"),
        wu_shaonianzhouyu(3012,"少年周瑜"),
        wu_sunxiaohu(3013,"孙小虎"),
        wu_huanggai(3014,"黄盖"),
        wu_sundahu(3015,"孙大虎"),
        wu_lingtong(3016,"凌统"),
        wu_lusu(3017,"鲁肃"),
        wu_xiaoqiao(3018,"小乔"),
        wu_wufuren(3019,"吴夫人"),
        wu_daqiao(3020,"大乔"),
        wu_zhuran(3021,"朱然"),

        qun_lvji(4001,"吕姬"),
        qun_tongyuan(4002,"童渊"),
        qun_dongbai(4003,"董白"),
        qun_yanliang(4004,"颜良"),
        qun_hetaihou(4005,"何太后"),
        qun_caiwenji(4006,"蔡文姬"),
        qun_wenchou(4007,"文丑"),
        qun_lvbu(4008,"吕布"),
        qun_huatuo(4009,"华佗"),
        qun_shaonianlvbu(4010,"少年吕布"),
        qun_yuji(4011,"于吉"),
        qun_dongzhuo(4012,"董卓"),
        qun_tianfeng(4013,"田丰"),
        qun_menghuo(4014,"孟获"),
        qun_yuanshao(4015,"袁绍"),
        qun_zhangjiao(4016,"张角"),
        qun_gaoshun(4017,"高顺"),
        qun_diaochan(4018,"貂蝉"),
        qun_lvlingqi(4019,"吕玲绮"),
        qun_zoushi(4020,"邹氏"),
        qun_tianzihanxiandi(4021,"天子汉献帝"),
        qun_zhurongfuren(4022,"祝融夫人"),
        qun_gongsunzan(4023,"公孙瓒"),
        qun_huaxiong(4024,"华雄"),
        ;
        private Integer code;
        private String name;
        GeneralsCode(){}
        GeneralsCode(Integer code,String name) {
            this.code = code;
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }
    }

    public enum Material implements GeneralsEnumInterface {
        life(0,"命石",MaterialType.life_pt.getCode(),MaterialType.life_jl.getCode(),MaterialType.life_wx.getCode()),
        secret(1,"天机之钥",MaterialType.secret_pt.getCode(),MaterialType.secret_jl.getCode(),MaterialType.secret_wx.getCode()),
        heaven(2,"天相之圭",MaterialType.heaven_pt.getCode(),MaterialType.heaven_jl.getCode(),MaterialType.heaven_wx.getCode()),
        lunar(3,"太阴之精",MaterialType.lunar_pt.getCode(),MaterialType.lunar_jl.getCode(),MaterialType.lunar_wx.getCode()),
        sun(4,"太阳之焰",MaterialType.sun_pt.getCode(),MaterialType.sun_jl.getCode(),MaterialType.sun_wx.getCode()),
        greedy(5,"贪狼之爪",MaterialType.greedy_pt.getCode(),MaterialType.greedy_jl.getCode(),MaterialType.greedy_wx.getCode()),
        lianZhen(6,"廉贞之锋",MaterialType.lianZhen_pt.getCode(),MaterialType.lianZhen_jl.getCode(),MaterialType.lianZhen_wx.getCode()),
        sevenKill(7,"七杀之气",MaterialType.sevenKill_pt.getCode(),MaterialType.sevenKill_jl.getCode(),MaterialType.sevenKill_wx.getCode()),
        breakArmy(8,"破军之血",MaterialType.breakArmy_pt.getCode(),MaterialType.breakArmy_jl.getCode(),MaterialType.breakArmy_wx.getCode())
        ;

        private Integer code;
        private String name;
        private Integer typeCode1;
        private Integer typeCode2;
        private Integer typeCode3;

        Material() {
        }

        Material(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        Material(Integer code, String name, Integer typeCode1, Integer typeCode2, Integer typeCode3) {
            this.code = code;
            this.name = name;
            this.typeCode1 = typeCode1;
            this.typeCode2 = typeCode2;
            this.typeCode3 = typeCode3;
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public Integer getTypeCode1() {
            return typeCode1;
        }

        public Integer getTypeCode2() {
            return typeCode2;
        }

        public Integer getTypeCode3() {
            return typeCode3;
        }
    }

    /**
     * 普通命石、精良命石、无暇命石
     * 紫薇之御
     * 普通天机之钥、普通天相之圭、普通太阴之精、普通太阳之焰、普通贪狼之爪、普通廉贞之锋、普通七杀之气、普通破军之血
     * 精良天机之钥、精良天相之圭、精良太阴之精、精良太阳之焰、精良贪狼之爪、精良廉贞之锋、精良七杀之气、精良破军之血
     * 无暇天机之钥、无暇天相之圭、无暇太阴之精、无暇太阳之焰、无暇贪狼之爪、无暇廉贞之锋、无暇七杀之气、无暇破军之血
     */
    public enum MaterialType implements GeneralsEnumInterface {

        life_pt(1,"普通命石",1210,1130),
        life_jl(2,"普通命石",570,840),
        life_wx(3,"普通命石",970,440),

        secret_pt(4,"普通天机之钥",60,10),
        secret_jl(5,"精良天机之钥",30,10),
        secret_wx(6,"无暇天机之钥",20,10),

        heaven_pt(7,"普通天相之圭",60,10),
        heaven_jl(8,"精良天相之圭",30,10),
        heaven_wx(9,"无暇天相之圭",20,10),

        lunar_pt(10,"普通太阴之精",60,10),
        lunar_jl(11,"精良太阴之精",30,10),
        lunar_wx(12,"无暇太阴之精",20,10),

        sun_pt(13,"普通太阳之焰",60,10),
        sun_jl(14,"精良太阳之焰",30,10),
        sun_wx(15,"无暇太阳之焰",20,10),

        greedy_pt(16,"普通贪狼之爪",120,30),
        greedy_jl(17,"精良贪狼之爪",60,30),
        greedy_wx(18,"无暇贪狼之爪",40,30),

        lianZhen_pt(19,"普通廉贞之锋",120,30),
        lianZhen_jl(20,"精良廉贞之锋",60,30),
        lianZhen_wx(21,"无暇廉贞之锋",40,30),

        sevenKill_pt(22,"普通七杀之气",120,30),
        sevenKill_jl(23,"精良七杀之气",60,30),
        sevenKill_wx(24,"无暇七杀之气",40,30),

        breakArmy_pt(25,"普通破军之血",120,30),
        breakArmy_jl(26,"精良破军之血",60,30),
        breakArmy_wx(27,"无暇破军之血",40,30),
        ;

        private Integer code;
        private String name;
        private Integer disobey;//逆命
        private Integer breach;//突破

        MaterialType(){}

        MaterialType(Integer code, String name, Integer disobey, Integer breach) {
            this.code = code;
            this.name = name;
            this.disobey = disobey;
            this.breach = breach;
        }

        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }

        public Integer getDisobey() {
            return disobey;
        }

        public Integer getBreach() {
            return breach;
        }
    }

    public enum Skin implements GeneralsEnumInterface {
        skin_1(1,"沉弈忘雪","",30,30,80),
        skin_2(2,"繁花一梦","",30,30,80),
        skin_3(3,"月落千殇","",30,30,80),
        skin_4(4,"剑影流风","",30,30,80),
        skin_5(5,"赤魇苍雷","",30,30,80),
        skin_6(6,"锦绣丹心","",30,30,80),
        skin_7(7,"醉梦千秋","",30,30,80),
        skin_8(8,"魔影祸战","",30,30,80),
        skin_9(9,"弦音碎梦","",30,30,80),
        skin_10(10,"巧局奇谋","",30,30,80),
        skin_11(11,"绝地冲锋","",30,30,80),
        skin_12(12,"腾凌万军","",30,30,80),
        skin_13(13,"桃之夭夭","挽歌大乔",30,30,80),
        skin_14(14,"困龙欲出","天子汉献帝",30,30,80),
        skin_15(15,"冰息虎啸","狂澜吕蒙",30,30,80),
        skin_16(16,"锦上添花","落英黄舞蝶",30,30,80),
        skin_17(17,"山河无恙","墨衍荀彧",30,30,80),
        skin_18(18,"金纹昊天","奋威袁绍",30,30,80),
        skin_19(19,"战影瑶芳","灵刃吕玲绮",30,30,80),
        skin_20(20,"誓血雄心","虎步夏侯渊",30,30,80),
        skin_21(21,"沧海云帆","游侠甘宁",30,30,80),
        skin_22(22,"忘川鬼鹤","妖仙于吉",30,30,80),
        skin_23(23,"心若冰霜","绝情张春华",30,30,80),
        skin_24(24,"西凉雄狮","锦狮马超",30,30,80),
        skin_25(25,"空桑司命","天妒郭嘉",30,30,80),
        skin_26(26,"掣牛倒行","混沌许褚",30,30,80),
        skin_27(27,"闭月羞花","顾影貂蝉",30,30,80),
        skin_28(28,"狂影奇袭","回禄魏延",30,30,80),
        skin_29(29,"火凤燎原","陨星庞统",30,30,80),
        skin_30(30,"云蒸龙变","砺战赵云",30,30,80),
        skin_31(31,"雷奔云谲","奋武公孙瓒",30,30,80),
        skin_32(32,"月雅清鸣","道玄诸葛果",30,30,80),
        skin_33(33,"剑锋祭魂","剑引张郃",30,30,80),
        ;
        private Integer code;
        private String name;
        private String generalsName;
        private Integer force;
        private Integer intellect;
        private Integer troops;

        Skin(){}

        Skin(Integer code, String name,String generalsName, Integer force, Integer intellect, Integer troops) {
            this.code = code;
            this.name = name;
            this.generalsName = generalsName;
            this.force = force;
            this.intellect = intellect;
            this.troops = troops;
        }

        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }
        public Integer getForce() {
            return force;
        }

        public Integer getIntellect() {
            return intellect;
        }

        public Integer getTroops() {
            return troops;
        }
    }

    public enum BattleArrayWay implements GeneralsEnumInterface {
        //hu_yi(1,"",3,1,6),
        //huo_niu(2,""),
        long_fei(3,"龙飞",2,1,9,390,195,1755),
        //niao_xiang(4,""),
        //she_pan(5,""),
        //he_yi(6,""),
        ;
        private Integer code;
        private String name;
        private Integer growForce;
        private Integer growIntellect;
        private Integer growTroops;
        private Integer maxForce;
        private Integer maxIntellect;
        private Integer maxTroops;
        BattleArrayWay(){}

        BattleArrayWay(Integer code, String name, Integer growForce, Integer growIntellect, Integer growTroops, Integer maxForce, Integer maxIntellect, Integer maxTroops) {
            this.code = code;
            this.name = name;
            this.growForce = growForce;
            this.growIntellect = growIntellect;
            this.growTroops = growTroops;
            this.maxForce = maxForce;
            this.maxIntellect = maxIntellect;
            this.maxTroops = maxTroops;
        }

        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }

        public Integer getGrowForce() {
            return growForce;
        }

        public Integer getGrowIntellect() {
            return growIntellect;
        }

        public Integer getGrowTroops() {
            return growTroops;
        }

        public Integer getMaxForce() {
            return maxForce;
        }

        public Integer getMaxIntellect() {
            return maxIntellect;
        }

        public Integer getMaxTroops() {
            return maxTroops;
        }
    }

    public enum Combat implements GeneralsEnumInterface {
        zs_1(1, "壮士1级",600),
        zs_2(2, "壮士2级",900),
        zs_3(3, "壮士3级",1350),
        zs_4(4, "壮士4级",1700),
        zs_5(5, "壮士5级",2400),
        zs_6(6, "壮士6级",3600),
        zs_7(7, "壮士7级",4900),
        zs_8(8, "壮士8级",6200),
        zs_9(9, "壮士9级",7500),
        hj_1(11, "豪杰1级",8800),
        hj_2(12, "豪杰2级",10200),
        hj_3(13, "豪杰3级",11500),
        hj_4(14, "豪杰4级",12800),
        hj_5(15, "豪杰5级",14100),
        hj_6(16, "豪杰6级",15400),
        hj_7(17, "豪杰7级",16800),
        hj_8(18, "豪杰8级",18100),
        hj_9(19, "豪杰9级",19400),
        zh_1(21, "诸侯1级",20700),
        zh_2(22, "诸侯2级",22000),
        zh_3(23, "诸侯3级",23400),
        zh_4(24, "诸侯4级",24700),
        zh_5(25, "诸侯5级",26000),
        zh_6(26, "诸侯6级",27300),
        zh_7(27, "诸侯7级",28600),
        zh_8(28, "诸侯8级",30000),
        zh_9(29, "诸侯9级",31900),
        wh_1(31, "王侯1级",33800),
        wh_2(32, "王侯2级",35700),
        wh_3(33, "王侯3级",37600),
        wh_4(34, "王侯4级",39500),
        wh_5(35, "王侯5级",41400),
        wh_6(36, "王侯6级",43300),
        wh_7(37, "王侯7级",45200),
        wh_8(38, "王侯8级",47100),
        wh_9(39, "王侯9级",50000),
        bz_1(41, "霸主1级",53780),
        bz_2(42, "霸主2级",57940),
        bz_3(43, "霸主3级",62480),
        bz_4(44, "霸主4级",67400),
        bz_5(45, "霸主5级",72700),
        bz_6(46, "霸主6级",78380),
        bz_7(47, "霸主7级",84440),
        bz_8(48, "霸主8级",90880),
        bz_9(49, "霸主9级",97700),
        jw_1(51, "君王1级",105000),
        jw_2(52, "君王2级",113200),
        jw_3(53, "君王3级",122000),
        jw_4(54, "君王4级",132000),
        jw_5(55, "君王5级",142000),
        jw_6(56, "君王6级",153000),
        jw_7(57, "君王7级",165000),
        jw_8(58, "君王8级",182000),
        jw_9(59, "君王9级",202000),
        dw_1(61, "帝王1级",223000),
        dw_2(62, "帝王2级",244000),
        dw_3(63, "帝王3级",265000),
        dw_4(64, "帝王4级",286000),
        dw_5(65, "帝王5级",308000),
        dw_6(66, "帝王6级",330000),
        dw_7(67, "帝王7级",352000),
        dw_8(68, "帝王8级",375000),
        dw_9(69, "帝王9级",398000),
        dh_1(71, "帝皇1级",421000),
        dh_2(72, "帝皇2级",445000),
        dh_3(73, "帝皇3级",469000),
        dh_4(74, "帝皇4级",520000),
        dh_5(75, "帝皇5级",580000),
        dh_6(76, "帝皇6级",999999),
//        dh_7(77, "帝皇7级",null),
//        dh_8(78, "帝皇8级",null),
//        dh_9(79, "帝皇9级",null),
        ;
        private Integer code;
        private String name;
        private Integer combat;

        Combat(Integer code, String name, Integer combat) {
            this.code = code;
            this.name = name;
            this.combat = combat;
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public Integer getCombat() {
            return combat;
        }

        public static String getNameByCombat(int combat){
            //Map<Integer,Integer> map = new LinkedHashMap<>();
            GeneralsEnum.Combat[] combats = GeneralsEnum.Combat.values();
            for (int i=1;i<combats.length;i++){
                int combat1 = combats[i-1].getCombat();
                int combat2 = combats[i].getCombat();
                if(combat >= combat1 && combat < combat2){
                    return combats[i-1].getName();
                }
                //map.put(combats[i-1].getCombat(),combats[i].getCombat());
            }
            return null;
        }
    }

    /*public enum Material implements GeneralsEnumInterface {
        n1(1,"霸业曹操", Destiny.disobey.getCode())
        ;

        private Integer code;
        private String name;
        private Integer disobey;//逆命或突破
        private Integer chipCount;//逆命碎片个数
        private Integer breachCount;//突破印个数
        private Integer crapeMyrtle;//紫薇之御个数
        private Integer life_pt;//普通命石
        private Integer life_jl;//精良命石
        private Integer life_wx;//无暇命石


        Material(){}

        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }
    }*/


    /*public enum DeviceResonance implements GeneralsEnumInterface {
        r1(1001,""),
        ;
        private Integer code;
        private String name;

        DeviceResonance(Integer code, String name) {
            this.code = code;
            this.name = name;
        }
    }
*/
    public enum Device implements GeneralsEnumInterface {
        dao_1001(1001, "青龙偃月刀",WarDevice.knife2, new String[]{"关羽","关平"}, new String[]{"武圣关羽","一骑关羽","汉寿亭侯云长","刀魂关平"}),
        dao_1002(1002, "古锭刀",WarDevice.knife2, new String[]{"孙坚","华雄"}, new String[]{"武烈孙坚","猛虎孙坚","战魂华雄"}),
        dao_1003(1003, "破军斩",WarDevice.knife2, new String[]{"夏侯惇"}, new String[]{"独目夏侯惇","苍狼夏侯惇"}),
        dao_1004(1004, "金刚断",WarDevice.knife2, new String[]{"黄盖"}, new String[]{"赤忠黄盖"}),
        dao_1005(1005, "百兽猎刃",WarDevice.knife2, new String[]{"孟获","祝融夫人"}, new String[]{"豹魂祝融夫人"}),
        dao_1006(1006, "七星刀",WarDevice.knife2, new String[]{"董卓"}, new String[]{"魔君董卓","浊世董卓","劫国董卓"}),
        dao_1007(1007, "霸海刀",WarDevice.knife2, new String[]{"甘宁"}, new String[]{"游侠甘宁","斗将甘宁"}),
        dao_1008(1008, "鬼魇刀",WarDevice.knife2, new String[]{"魏延"}, new String[]{"骁勇魏延","狂骨魏延","回禄魏延"}),
        dao_1009(1009, "虎煞刀",WarDevice.knife2, new String[]{"许褚"}, new String[]{"混沌许褚","战锤许褚"}),
        dao_1010(1010, "新亭候刀",WarDevice.knife2, new String[]{"张飞"}, new String[]{"暴怒张飞","桓候张飞","诡骑张飞"}),

        jian_2001(2001, "倚天剑",WarDevice.sword2, new String[]{"曹操"}, new String[]{"武帝曹操","奸雄曹操","霸业曹操"}),
        jian_2002(2002, "青釭剑",WarDevice.sword2, new String[]{"曹操","赵云"}, new String[]{"武帝曹操","奸雄曹操","霸业曹操","龙胆赵云","神威赵云","砺战赵云"}),
        jian_2003(2003, "雌雄双股剑",WarDevice.sword2, new String[]{"刘备","刘禅"}, new String[]{"昭烈刘备","仁德刘备","神武刘备","安乐公刘禅"}),
        jian_2004(2004, "弱水剑",WarDevice.sword2, new String[]{"郭嘉","荀彧"}, new String[]{"驱虎荀彧","令香荀彧","墨衍荀彧","鬼谋郭嘉","天妒郭嘉",}),
        jian_2005(2005, "燕回闪",WarDevice.sword2, new String[]{"凌统"}, new String[]{"浴血凌统","国士凌统","烈胆凌统"}),
        jian_2006(2006, "龙鳞剑",WarDevice.sword2, new String[]{"曹丕"}, new String[]{"文帝曹丕","灭奏曹丕","驭胜曹丕","淑懿甄姬"}),
        jian_2007(2007, "玲珑之心",WarDevice.sword2, new String[]{"卞夫人","蔡文姬","吴夫人","何太后"}, new String[]{"伶歌卞夫人","胡韵蔡文姬","舜华吴夫人","灵思何太后","悦灵蔡文姬"}),
        jian_2008(2008, "思召剑",WarDevice.sword2, new String[]{"袁绍"}, new String[]{"思召袁绍","贵胄袁绍","奋威袁绍"}),
        jian_2009(2009, "赤霄剑",WarDevice.sword2, new String[]{"天子汉献帝"}, new String[]{"天子汉献帝"}),
        jian_2010(2010, "青冥剑",WarDevice.sword2, new String[]{"周泰"}, new String[]{"血痕周泰","擎苍周泰"}),

        qiang_3001(3001, "方天画戟",WarDevice.gun2, new String[]{"吕布","吕玲绮"}, new String[]{"鬼神吕布","修罗吕布","飞将吕布","战姬吕玲绮","双子吕玲绮","灵刃吕玲绮"}),
        qiang_3002(3002, "丈八蛇矛",WarDevice.gun2, new String[]{"张飞","张星彩"}, new String[]{"暴怒张飞","桓候张飞","诡骑张飞","风舞张星彩"}),
        qiang_3003(3003, "龙骑枪",WarDevice.gun2, new String[]{"马超","马云禄"}, new String[]{"锦狮马超","铁骑马超","龙驹马云禄"}),
        qiang_3004(3004, "霸王枪",WarDevice.gun2, new String[]{"孙策"}, new String[]{"霸王孙策","驰骋孙策","桀骜孙策","挽歌大乔"}),
        qiang_3005(3005, "沧月",WarDevice.gun2, new String[]{"张辽"}, new String[]{"振威张辽","召虎张辽","御甲张辽"}),
        qiang_3006(3006, "若麒麟牙",WarDevice.gun2, new String[]{"姜维"}, new String[]{"麒麟姜维","斗胆姜维"}),
        qiang_3007(3007, "魑魅",WarDevice.gun2, new String[]{"文丑"}, new String[]{"猎狐文丑","霸音文丑","河间双雄"}),
        qiang_3008(3008, "白虎银枪",WarDevice.gun2, new String[]{"孙权","孙大虎","孙小虎"}, new String[]{"吴王孙权","若虎孙权","命世孙权","凤仪步练师","潋滟步练师","诛心孙大虎","瞳心孙小虎"}),
        qiang_3009(3009, "蚩尤重戟",WarDevice.gun2, new String[]{"典韦"}, new String[]{"恶来典韦","英灵典韦","虎涧典韦"}),
        qiang_3010(3010, "摧城枪",WarDevice.gun2, new String[]{"曹仁"}, new String[]{"疾风曹仁","征南曹仁"}),
        qiang_3011(3011, "魍魉",WarDevice.gun2, new String[]{"颜良"}, new String[]{"鸦杀颜良","恶目颜良","河间双雄"}),
        qiang_3012(3012, "五行鬼谷杖",WarDevice.gun2, new String[]{"华佗"}, new String[]{"妙手华佗","猿戏华佗"}),
        qiang_3013(3013, "北邙紫金枪",WarDevice.gun2, new String[]{"张郃","公孙瓒"}, new String[]{"雅歌张郃","先勇张郃","剑引张郃","白马公孙瓒","奋武公孙瓒"}),
        qiang_3014(3014, "火龙枪",WarDevice.gun2, new String[]{"高顺"}, new String[]{"斩锋高顺"}),
        qiang_3015(3015, "天燮绿沉枪",WarDevice.gun2, new String[]{""}, new String[]{"蜀魂姜维"}),

        gong_4001(4001, "诸葛连弩",WarDevice.arch2, new String[]{"诸葛亮","黄月英"}, new String[]{"卧龙诸葛亮","七星诸葛亮","阿丑黄月英","杰女黄月英"}),
        gong_4002(4002, "龙舌弓",WarDevice.arch2, new String[]{"吕布","吕姬"}, new String[]{"鬼神吕布","修罗吕布","飞将吕布","猩红吕姬","双子吕姬","灵雎吕姬"}),
        gong_4003(4003, "惊凰弓",WarDevice.arch2, new String[]{"庞统","朱然"}, new String[]{"凤雏庞统","智极庞统","陨星庞统","赤羽朱然"}),
        gong_4004(4004, "龙吟弓",WarDevice.arch2, new String[]{"周瑜","陆逊"}, new String[]{"业炎周瑜","顾曲周瑜","焚天陆逊","儒将陆逊","玉琼小乔"}),
        gong_4005(4005, "虎扑弓",WarDevice.arch2, new String[]{"太史慈","吕蒙","韩当"}, new String[]{"矢志太史慈","感古太史慈","秉义太史慈","克己吕蒙","虎威吕蒙","狂澜吕蒙","厉箭韩当"}),
        gong_4006(4006, "黄天之弓",WarDevice.arch2, new String[]{"张角"}, new String[]{"天公张角","符咒张角"}),
        gong_4007(4007, "黄杨大弓",WarDevice.arch2, new String[]{"黄忠","黄舞蝶"}, new String[]{"弓神黄忠","讨虏黄忠","射日黄忠","惊鸿黄舞蝶","落英黄舞蝶","飞琼黄舞蝶"}),
        gong_4008(4008, "鹊画弓",WarDevice.arch2, new String[]{"孙尚香"}, new String[]{"弓腰姬孙尚香","君主孙尚香","灵泽孙尚香"}),
        gong_4009(4009, "千浪破",WarDevice.arch2, new String[]{"甘宁"}, new String[]{"游侠甘宁","斗将甘宁"}),
        gong_4010(4010, "克敌机先",WarDevice.arch2, new String[]{"夏侯渊"}, new String[]{"虎步夏侯渊","典军夏侯渊"}),

        shan_5001(5001, "朱雀羽扇",WarDevice.fan2, new String[]{"诸葛亮","诸葛果"}, new String[]{"卧龙诸葛亮","七星诸葛亮","道玄诸葛果"}),
        shan_5002(5002, "琉璃天香",WarDevice.fan2, new String[]{"大乔","小乔"}, new String[]{"望月大乔","临江小乔","挽歌大乔","玉琼小乔","芳华大乔"}),
        shan_5003(5003, "冥天符",WarDevice.fan2, new String[]{"卑弥呼","于吉"}, new String[]{"妖仙于吉","天照卑弥呼"}),
        shan_5004(5004, "玄武折扇",WarDevice.fan2, new String[]{"鲁肃"}, new String[]{"缔盟鲁肃","忠魂鲁肃"}),
        shan_5005(5005, "穷奇羽扇",WarDevice.fan2, new String[]{"司马懿","贾诩","程昱"}, new String[]{"狼顾司马懿","宣王司马懿","都士贾诩","乱武贾诩","绝情张春华","筹谋程昱"}),
        shan_5006(5006, "月下美人",WarDevice.fan2, new String[]{"貂蝉","甄姬"}, new String[]{"绝舞貂蝉","倾世貂蝉","顾影貂蝉","洛神甄姬","灵蛇甄姬","淑懿甄姬"}),
        shan_5007(5007, "毕方之羽",WarDevice.fan2, new String[]{"法正"}, new String[]{"奇谋法正"}),
        ;
        private Integer code;
        private String name;
        //private Boolean resonance;
        private WarDevice warDevice;
        private String[] generals1;
        private String[] generals2;

        Device(Integer code, String name,WarDevice warDevice, String[] generals1, String[] generals2) {
            this.code = code;
            this.name = name;
            this.warDevice = warDevice;
            this.generals1 = generals1;
            this.generals2 = generals2;
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public WarDevice getWarDevice() {
            return warDevice;
        }

        public String[] getGenerals1() {
            return generals1;
        }

        public String[] getGenerals2() {
            return generals2;
        }
    }
}
