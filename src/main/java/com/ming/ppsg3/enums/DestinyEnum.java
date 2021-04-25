package com.ming.ppsg3.enums;

public enum DestinyEnum {

    destiny("命格"),//命格
    material("逆命突破材料"),
    ;

    private String groupName;

    private DestinyEnum(String groupName) {
        this.groupName = groupName;
    }
    String getGroupName() {
        return this.groupName;
    }



    public enum Material implements GeneralsEnumInterface {
        life(0,"命石", MaterialType.life_pt.getCode(), MaterialType.life_jl.getCode(), MaterialType.life_wx.getCode()),
        secret(1,"天机之钥", MaterialType.secret_pt.getCode(), MaterialType.secret_jl.getCode(), MaterialType.secret_wx.getCode()),
        heaven(2,"天相之圭", MaterialType.heaven_pt.getCode(), MaterialType.heaven_jl.getCode(), MaterialType.heaven_wx.getCode()),
        lunar(3,"太阴之精", MaterialType.lunar_pt.getCode(), MaterialType.lunar_jl.getCode(), MaterialType.lunar_wx.getCode()),
        sun(4,"太阳之焰", MaterialType.sun_pt.getCode(), MaterialType.sun_jl.getCode(), MaterialType.sun_wx.getCode()),
        greedy(5,"贪狼之爪", MaterialType.greedy_pt.getCode(), MaterialType.greedy_jl.getCode(), MaterialType.greedy_wx.getCode()),
        lianZhen(6,"廉贞之锋", MaterialType.lianZhen_pt.getCode(), MaterialType.lianZhen_jl.getCode(), MaterialType.lianZhen_wx.getCode()),
        sevenKill(7,"七杀之气", MaterialType.sevenKill_pt.getCode(), MaterialType.sevenKill_jl.getCode(), MaterialType.sevenKill_wx.getCode()),
        breakArmy(8,"破军之血", MaterialType.breakArmy_pt.getCode(), MaterialType.breakArmy_jl.getCode(), MaterialType.breakArmy_wx.getCode())
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

        life_pt(101,"普通命石"),
        life_jl(102,"精良命石"),
        life_wx(103,"无暇命石"),
        life_zz(104,"至臻命石"),

        secret_pt(4,"普通天机之钥"),
        secret_jl(5,"精良天机之钥"),
        secret_wx(6,"无暇天机之钥"),

        heaven_pt(7,"普通天相之圭"),
        heaven_jl(8,"精良天相之圭"),
        heaven_wx(9,"无暇天相之圭"),

        lunar_pt(10,"普通太阴之精"),
        lunar_jl(11,"精良太阴之精"),
        lunar_wx(12,"无暇太阴之精"),

        sun_pt(13,"普通太阳之焰"),
        sun_jl(14,"精良太阳之焰"),
        sun_wx(15,"无暇太阳之焰"),

        greedy_pt(16,"普通贪狼之爪"),
        greedy_jl(17,"精良贪狼之爪"),
        greedy_wx(18,"无暇贪狼之爪"),

        lianZhen_pt(19,"普通廉贞之锋"),
        lianZhen_jl(20,"精良廉贞之锋"),
        lianZhen_wx(21,"无暇廉贞之锋"),

        sevenKill_pt(22,"普通七杀之气"),
        sevenKill_jl(23,"精良七杀之气"),
        sevenKill_wx(24,"无暇七杀之气"),

        breakArmy_pt(25,"普通破军之血"),
        breakArmy_jl(26,"精良破军之血"),
        breakArmy_wx(27,"无暇破军之血"),
        ;

        private Integer code;
        private String name;

        MaterialType(){}

        MaterialType(Integer code, String name) {
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

}
