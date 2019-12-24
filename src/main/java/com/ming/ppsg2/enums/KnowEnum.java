package com.ming.ppsg2.enums;

public enum KnowEnum {
    jiangJun("将魂");

    private String groupName;

    private KnowEnum(String groupName) {
        this.groupName = groupName;
    }
    String getGroupName() {
        return this.groupName;
    }

    public enum JiangJun implements KnowEnumInterface {
        //白卡+15 黑卡+20 银卡+60 金卡+300
        //1-18 (1250-5500) 递增250
        //19-30 （5700）

        //白卡+15 黑卡+20 银卡+60 金卡+300
        //将魂1-18级 (需要经验1250-5500) 每级递增250
        //将魂19-30级 （固定经验5700）
        Force(1,"武力"),
        Intellect(2,"智力"),
        Troops(3,"兵力");
        private Integer code;
        private String name;
        JiangJun(){}
        JiangJun(Integer code,String name) {
            this.code = code;
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }
        public Integer getCount(){
            int total = 123450;
            return total/300;//411.5
        }
    }
    //战意
    public enum ZhanYi implements KnowEnumInterface {
        level1(1,null),
        level2(2,1000),
        level3(3,3200),
        level4(4,7600),
        level5(5,15000),
        level6(6,null),
        level7(7,null),
        level8(8,null),
        level9(9,90000),
        ;
        private Integer code;
        private Integer name;
        ZhanYi(){}
        ZhanYi(Integer code,Integer name) {
            this.code = code;
            this.name = name;
        }
        public Integer getName() {
            return this.name;
        }
        public Integer getCode() {
            return this.code;
        }
    }
}
