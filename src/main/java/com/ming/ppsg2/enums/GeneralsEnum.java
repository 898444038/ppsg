package com.ming.ppsg2.enums;

public enum GeneralsEnum {

    threeCircles("三维属性"),
    generalsType("武将类型"),
    generalsCode("武将编码"),
    type("类型"),//类型
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
    gender("性别");//性别

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
        knife(1,"刀","刀",133,106,160,224,178,494,1.0,1.0,1.0,288,288,288,458,458,1220),
        sword(2,"剑","剑",133,133,133,227,224,445,1.0,1.0,1.0,288,288,288,458,458,1220),
        gun(3,"枪","枪",160,106,133,273,178,445,1.0,1.0,1.0,288,288,288,458,458,1220),
        arch(4,"弓","弓",133,133,133,224,227,445,1.0,1.0,1.0,288,288,288,458,458,1220),
        fan(5,"扇","扇",133,160,106,224,273,399,1.0,1.0,1.0,288,288,288,458,458,1220),
        //异化战器
//        knife2(6,"刀2","刀",133,106,160,224,178,494,1.0,1.0,1.0,288,288,288,458,458,1220),
//        sword2(7,"剑2","特殊剑",133,106,160,227,224,445,1.0,1.0,1.0,288,288,288,458,458,1220),
//        gun2(8,"枪2","特殊枪",133,133,133,273,178,445,1.0,1.0,1.0,288,288,288,458,458,1220),
//        arch2(9,"弓2","特殊弓",160,106,133,224,227,445,1.0,1.0,1.0,288,288,288,458,458,1220),
//        fan2(10,"扇2","特殊扇",133,133,133,224,273,399,1.0,1.0,1.0,288,288,288,458,458,1220),
        knife2(6,"刀2","刀",133,106,160,224,178,494,1.0,1.0,1.0,288,288,288,458,458,1220),
        sword2(7,"剑2","特殊剑",133,106,160,227,224,445,1.0,1.0,1.0,288,288,288,458,458,1220),
        gun2(8,"枪2","枪",160,106,133,273,178,445,1.0,1.0,1.0,288,288,288,458,458,1220),
        arch2(9,"弓2","弓",133,133,133,224,227,445,1.0,1.0,1.0,288,288,288,458,458,1220),
        fan2(10,"扇2","特殊扇",133,133,133,224,273,399,1.0,1.0,1.0,288,288,288,458,458,1220),
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
        WarDevice(){}

        WarDevice(Integer code, String name,String desc, Integer force, Integer intellect, Integer troops, Integer strengthenForce, Integer strengthenIntellect, Integer strengthenTroops, Double quenchingForceRate, Double quenchingIntellectRate, Double quenchingTroopsRate, Integer exclusiveForce, Integer exclusiveIntellect, Integer exclusiveTroops, Integer passive1, Integer passive2, Integer passive3) {
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

    /*public enum BookType implements GeneralsEnumInterface {
        jiaoLi(1,"角力",5,1),
        qiShu(2,"骑术",),
        duanBing(3,"短兵",),
        fangYu(4,"防御",),
        zhanLue(5,"战略",);
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
    }*/

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
        disobey5(3,"逆命5",1220,1220,1220,1220,300,680,1220,1920,2380,200,40);

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
        skin_1(1,"沉弈忘雪",30,30,80),
        skin_2(2,"繁花一梦",30,30,80),
        skin_3(3,"月落千殇",30,30,80),
        skin_4(4,"剑影流风",30,30,80),
        skin_5(5,"赤魇苍雷",30,30,80),
        skin_6(6,"锦绣丹心",30,30,80),
        skin_7(7,"醉梦千秋",30,30,80),
        skin_8(8,"魔影祸战",30,30,80),
        skin_9(9,"弦音碎梦",30,30,80),
        ;
        private Integer code;
        private String name;
        private Integer force;
        private Integer intellect;
        private Integer troops;

        Skin(){}

        Skin(Integer code, String name, Integer force, Integer intellect, Integer troops) {
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
}
