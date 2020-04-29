package com.ming.ppsg2.entity;

import java.util.List;

/**
 * 霸主3:62480
 * 霸主4:67400
 * 霸主5:72700
 * 霸主6:78380
 * 霸主7:84440
 * 霸主8:90880
 * 霸主9:97700
 * 君王1:105000
 * 君王2:113200
 * 君王3:
 * 君王4:132000
 * 君王5:142000
 * 君王6:153000
 *
 * 帝五：308000
 * 帝六：330000
 * 帝七：352000
 * 帝八：375000
 * 帝九：398000
 */
public class Generals {

    private String id;
    //武将名称
    private String name;
    //武将唯一编码  GeneralsEnum.GeneralsCode
    private String code;
    //是否‘限’卡
    private Boolean limit;
    //是否逆命
    private Boolean isDestiny;
    //性别
    private Integer gender;
    //初始武将等级
    private Integer level = 1;
    //初始武将武力
    private Integer force;
    //初始武将智力
    private Integer intellect;
    //初始武将兵力
    private Integer Troops;
    //武将类型 GeneralsEnum.GeneralsType
    private Integer generalsType;
    //是否是随从
    private Boolean isEntourage;
    //是否是联协
    private Boolean isAssociation;
    //联协
    private List<Integer> entourages;
    //武力联协
    private Generals forceEntourage;
    //智力联协
    private Generals intellectEntourage;
    //兵力联协
    private Generals troopsEntourage;
    //最佳武力联协
    private List<Generals> forceEntourageList;
    //最佳智力联协
    private List<Generals> intellectEntourageList;
    //最佳兵力联协
    private List<Generals> troopsEntourageList;
    //国家 GeneralsEnum.country
    private Integer country;
    //兵种 GeneralsEnum.Arms
    private Integer arms;
    //兵种 GeneralsEnum.ArmsBook
    private ArmsBook armsBook;
    //命格突破
    private Destiny destiny;
    //战意
    private Warpath warpath;
    //兵符
    private Symbols symbols;

    //专属战器类型
    private List<Integer> warDevices;
    //战器
    private Device device;
    //战器(特殊)
    private Device device2;
    //幻化
    private Integer skinCode;

    //120满级三维
    private ThreeDimensional maxThreeDimensional;
    //科技三维
    private ThreeDimensional scienceThreeDimensional;
    //随从三维
    private ThreeDimensional entourageThreeDimensional;
    //科技三维
    private ThreeDimensional holyStoneThreeDimensional;
    //战器三维
    private ThreeDimensional warDeviceThreeDimensional;
    //战器三维(特殊)
    private ThreeDimensional warDevice2ThreeDimensional;
    //兵种兵书三维
    private ThreeDimensional armsBookThreeDimensional;
    //将魂三维
    private ThreeDimensional willSoulThreeDimensional;
    //兵符三维
    //private ThreeDimensional symbolsThreeDimensional;
    //兵符三维
    private ThreeDimensionals symbolsThreeDimensionals;
    //战阵三维
    private ThreeDimensional battleArrayThreeDimensional;
    //战意三维
    private ThreeDimensional warpathThreeDimensional;
    //命格
    private ThreeDimensional destinyThreeDimensional;
    //幻化
    private ThreeDimensional skinThreeDimensional;
    //总战力
    private Integer totalSword;
    //武将总武力
    private Integer totalForce;
    //武将总智力
    private Integer totalIntellect;
    //武将总兵力
    private Integer totalTroops;

    //总战力
    private Integer totalSword2;
    //武将总武力
    private Integer totalForce2;
    //武将总智力
    private Integer totalIntellect2;
    //武将总兵力
    private Integer totalTroops2;

    //武将武力(无联协加成)只限于显示
    private Integer fn;
    //武将智力(无联协加成)只限于显示
    private Integer in;
    //武将兵力(无联协加成)只限于显示
    private Integer tn;
    //武将武力(联协加成)只限于显示
    private Integer f;
    //武将智力(联协加成)只限于显示
    private Integer i;
    //武将兵力(联协加成)只限于显示
    private Integer t;

    //武将武力(判断有无联协后结果)只限于显示
    private Integer rf;
    //武将智力(判断有无联协后结果)只限于显示
    private Integer ri;
    //武将兵力(判断有无联协后结果)只限于显示
    private Integer rt;

    //武将总武力(联协加成)
    private Integer totalAddForce;
    //武将总智力(联协加成)
    private Integer totalAddIntellect;
    //武将总兵力(联协加成)
    private Integer totalAddTroops;

    private String remark;

    public Generals(){}

    public void setIsDestiny(Boolean isDestiny) {
        this.isDestiny = isDestiny;
    }
    public Boolean getIsDestiny() {
        return isDestiny;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getIsAssociation() {
        return isAssociation;
    }

    public void setIsAssociation(Boolean isAssociation) {
        this.isAssociation = isAssociation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArms() {
        return arms;
    }

    public void setArms(Integer arms) {
        this.arms = arms;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getGeneralsType() {
        return generalsType;
    }

    public void setGeneralsType(Integer generalsType) {
        this.generalsType = generalsType;
    }

    public Integer getRf() {
        return rf;
    }

    public void setRf(Integer rf) {
        this.rf = rf;
    }

    public Integer getRi() {
        return ri;
    }

    public void setRi(Integer ri) {
        this.ri = ri;
    }

    public Integer getRt() {
        return rt;
    }

    public void setRt(Integer rt) {
        this.rt = rt;
    }

    public Integer getForce() {
        return force;
    }

    public void setForce(Integer force) {
        this.force = force;
    }

    public Integer getIntellect() {
        return intellect;
    }

    public void setIntellect(Integer intellect) {
        this.intellect = intellect;
    }

    public Integer getTroops() {
        return Troops;
    }

    public void setTroops(Integer troops) {
        Troops = troops;
    }

    public ThreeDimensional getMaxThreeDimensional() {
        return maxThreeDimensional;
    }

    public void setMaxThreeDimensional(ThreeDimensional maxThreeDimensional) {
        this.maxThreeDimensional = maxThreeDimensional;
    }

    public ThreeDimensional getScienceThreeDimensional() {
        return scienceThreeDimensional;
    }

    public void setScienceThreeDimensional(ThreeDimensional scienceThreeDimensional) {
        this.scienceThreeDimensional = scienceThreeDimensional;
    }

    public ThreeDimensional getEntourageThreeDimensional() {
        return entourageThreeDimensional;
    }

    public void setEntourageThreeDimensional(ThreeDimensional entourageThreeDimensional) {
        this.entourageThreeDimensional = entourageThreeDimensional;
    }

    public ThreeDimensional getHolyStoneThreeDimensional() {
        return holyStoneThreeDimensional;
    }

    public void setHolyStoneThreeDimensional(ThreeDimensional holyStoneThreeDimensional) {
        this.holyStoneThreeDimensional = holyStoneThreeDimensional;
    }

    public ThreeDimensional getWarDeviceThreeDimensional() {
        return warDeviceThreeDimensional;
    }

    public void setWarDeviceThreeDimensional(ThreeDimensional warDeviceThreeDimensional) {
        this.warDeviceThreeDimensional = warDeviceThreeDimensional;
    }

    public List<Integer> getWarDevices() {
        return warDevices;
    }

    public void setWarDevices(List<Integer> warDevices) {
        this.warDevices = warDevices;
    }

    public ArmsBook getArmsBook() {
        return armsBook;
    }

    public void setArmsBook(ArmsBook armsBook) {
        this.armsBook = armsBook;
    }

    public ThreeDimensional getArmsBookThreeDimensional() {
        return armsBookThreeDimensional;
    }

    public void setArmsBookThreeDimensional(ThreeDimensional armsBookThreeDimensional) {
        this.armsBookThreeDimensional = armsBookThreeDimensional;
    }

    public ThreeDimensional getWillSoulThreeDimensional() {
        return willSoulThreeDimensional;
    }

    public void setWillSoulThreeDimensional(ThreeDimensional willSoulThreeDimensional) {
        this.willSoulThreeDimensional = willSoulThreeDimensional;
    }

    public Boolean getIsEntourage() {
        return isEntourage;
    }

    public void setIsEntourage(Boolean isEntourage) {
        this.isEntourage = isEntourage;
    }

    /*public ThreeDimensional getSymbolsThreeDimensional() {
        return symbolsThreeDimensional;
    }

    public void setSymbolsThreeDimensional(ThreeDimensional symbolsThreeDimensional) {
        this.symbolsThreeDimensional = symbolsThreeDimensional;
    }*/

    public ThreeDimensional getBattleArrayThreeDimensional() {
        return battleArrayThreeDimensional;
    }

    public void setBattleArrayThreeDimensional(ThreeDimensional battleArrayThreeDimensional) {
        this.battleArrayThreeDimensional = battleArrayThreeDimensional;
    }

    public ThreeDimensional getWarpathThreeDimensional() {
        return warpathThreeDimensional;
    }

    public void setWarpathThreeDimensional(ThreeDimensional warpathThreeDimensional) {
        this.warpathThreeDimensional = warpathThreeDimensional;
    }

    public ThreeDimensional getDestinyThreeDimensional() {
        return destinyThreeDimensional;
    }

    public void setDestinyThreeDimensional(ThreeDimensional destinyThreeDimensional) {
        this.destinyThreeDimensional = destinyThreeDimensional;
    }

    public ThreeDimensionals getSymbolsThreeDimensionals() {
        return symbolsThreeDimensionals;
    }

    public void setSymbolsThreeDimensionals(ThreeDimensionals symbolsThreeDimensionals) {
        this.symbolsThreeDimensionals = symbolsThreeDimensionals;
    }

    public Destiny getDestiny() {
        return destiny;
    }

    public void setDestiny(Destiny destiny) {
        this.destiny = destiny;
    }

    public Integer getTotalSword() {
        return totalSword;
    }

    public void setTotalSword(Integer totalSword) {
        this.totalSword = totalSword;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Warpath getWarpath() {
        return warpath;
    }

    public void setWarpath(Warpath warpath) {
        this.warpath = warpath;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Symbols getSymbols() {
        return symbols;
    }

    public void setSymbols(Symbols symbols) {
        this.symbols = symbols;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Integer> getEntourages() {
        return entourages;
    }

    public void setEntourages(List<Integer> entourages) {
        this.entourages = entourages;
    }

    public Integer getTotalForce() {
        return totalForce;
    }

    public void setTotalForce(Integer totalForce) {
        this.totalForce = totalForce;
    }

    public Integer getTotalIntellect() {
        return totalIntellect;
    }

    public void setTotalIntellect(Integer totalIntellect) {
        this.totalIntellect = totalIntellect;
    }

    public Integer getTotalTroops() {
        return totalTroops;
    }

    public void setTotalTroops(Integer totalTroops) {
        this.totalTroops = totalTroops;
    }

    public Integer getTotalAddForce() {
        return totalAddForce;
    }

    public void setTotalAddForce(Integer totalAddForce) {
        this.totalAddForce = totalAddForce;
    }

    public Integer getTotalAddIntellect() {
        return totalAddIntellect;
    }

    public void setTotalAddIntellect(Integer totalAddIntellect) {
        this.totalAddIntellect = totalAddIntellect;
    }

    public Integer getTotalAddTroops() {
        return totalAddTroops;
    }

    public void setTotalAddTroops(Integer totalAddTroops) {
        this.totalAddTroops = totalAddTroops;
    }

    public Generals getForceEntourage() {
        return forceEntourage;
    }

    public void setForceEntourage(Generals forceEntourage) {
        this.forceEntourage = forceEntourage;
    }

    public Generals getIntellectEntourage() {
        return intellectEntourage;
    }

    public void setIntellectEntourage(Generals intellectEntourage) {
        this.intellectEntourage = intellectEntourage;
    }

    public Generals getTroopsEntourage() {
        return troopsEntourage;
    }

    public void setTroopsEntourage(Generals troopsEntourage) {
        this.troopsEntourage = troopsEntourage;
    }

    public Boolean getLimit() {
        return limit;
    }

    public void setLimit(Boolean limit) {
        this.limit = limit;
    }

    public Integer getF() {
        return f;
    }

    public void setF(Integer f) {
        this.f = f;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Integer getT() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }

    public Integer getFn() {
        return fn;
    }

    public void setFn(Integer fn) {
        this.fn = fn;
    }

    public Integer getIn() {
        return in;
    }

    public void setIn(Integer in) {
        this.in = in;
    }

    public Integer getTn() {
        return tn;
    }

    public void setTn(Integer tn) {
        this.tn = tn;
    }

    public Device getDevice2() {
        return device2;
    }

    public void setDevice2(Device device2) {
        this.device2 = device2;
    }

    public ThreeDimensional getWarDevice2ThreeDimensional() {
        return warDevice2ThreeDimensional;
    }

    public void setWarDevice2ThreeDimensional(ThreeDimensional warDevice2ThreeDimensional) {
        this.warDevice2ThreeDimensional = warDevice2ThreeDimensional;
    }

    public List<Generals> getForceEntourageList() {
        return forceEntourageList;
    }

    public void setForceEntourageList(List<Generals> forceEntourageList) {
        this.forceEntourageList = forceEntourageList;
    }

    public List<Generals> getIntellectEntourageList() {
        return intellectEntourageList;
    }

    public void setIntellectEntourageList(List<Generals> intellectEntourageList) {
        this.intellectEntourageList = intellectEntourageList;
    }

    public List<Generals> getTroopsEntourageList() {
        return troopsEntourageList;
    }

    public void setTroopsEntourageList(List<Generals> troopsEntourageList) {
        this.troopsEntourageList = troopsEntourageList;
    }

    public Integer getTotalSword2() {
        return totalSword2;
    }

    public void setTotalSword2(Integer totalSword2) {
        this.totalSword2 = totalSword2;
    }

    public Integer getTotalForce2() {
        return totalForce2;
    }

    public void setTotalForce2(Integer totalForce2) {
        this.totalForce2 = totalForce2;
    }

    public Integer getTotalIntellect2() {
        return totalIntellect2;
    }

    public void setTotalIntellect2(Integer totalIntellect2) {
        this.totalIntellect2 = totalIntellect2;
    }

    public Integer getTotalTroops2() {
        return totalTroops2;
    }

    public void setTotalTroops2(Integer totalTroops2) {
        this.totalTroops2 = totalTroops2;
    }

    public Integer getSkinCode() {
        return skinCode;
    }

    public void setSkinCode(Integer skinCode) {
        this.skinCode = skinCode;
    }

    public ThreeDimensional getSkinThreeDimensional() {
        return skinThreeDimensional;
    }

    public void setSkinThreeDimensional(ThreeDimensional skinThreeDimensional) {
        this.skinThreeDimensional = skinThreeDimensional;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Generals{");
        sb.append("name='").append(name).append('\'');
        sb.append(", totalSword=").append(totalSword);
        sb.append(", force=").append(force);
        sb.append(", intellect=").append(intellect);
        sb.append(", Troops=").append(Troops);
        sb.append(", generalsType=").append(generalsType);
        sb.append(", arms=").append(arms);
        sb.append('}');
        return sb.toString();
    }
}
