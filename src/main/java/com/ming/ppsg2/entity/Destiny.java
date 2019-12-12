package com.ming.ppsg2.entity;


//命格
public class Destiny {

    private Integer force;
    private Integer intellect;
    private Integer Troops;

    // 1：开启命格  0 未开启命格
    private Boolean isDestiny;
    // 逆命或突破
    // not(0,"未开命格",0,0,0,0,0,0),
    // disobey(1,"逆命",1220,1220,300,680,1220,1920),
    // breach(2,"突破",0,0,300,680,680,680);
    private Integer disobey;

    private Integer destinyEffect1;//效果1战力
    private Integer destinyEffect2;//效果2战力

    private Integer maxLevel;//被动满级战力
    private Integer code1;//命格材料1
    private Integer code2;//命格材料2
    private Integer code3;//命格材料3

    private Integer chipCount;//逆命碎片个数
    private Integer breachCount;//突破印个数
    private Integer crapeMyrtle;//紫薇之御个数
    private String life_pt;//普通命石
    private String life_jl;//精良命石
    private String life_wx;//无暇命石
    private String secret_pt;//普通天机之钥
    private String secret_jl;//精良天机之钥
    private String secret_wx;//无暇天机之钥
    private String heaven_pt;//普通天相之圭
    private String heaven_jl;//精良天相之圭
    private String heaven_wx;//无暇天相之圭
    private String lunar_pt;//普通太阴之精
    private String lunar_jl;//精良太阴之精
    private String lunar_wx;//无暇太阴之精
    private String sun_pt;//普通太阳之焰
    private String sun_jl;//精良太阳之焰
    private String sun_wx;//无暇太阳之焰

    private String greedy_pt;//普通贪狼之爪
    private String greedy_jl;//精良贪狼之爪
    private String greedy_wx;//无暇贪狼之爪
    private String lianZhen_pt;//普通廉贞之锋
    private String lianZhen_jl;//精良廉贞之锋
    private String lianZhen_wx;//无暇廉贞之锋
    private String sevenKill_pt;//普通七杀之气
    private String sevenKill_jl;//精良七杀之气
    private String sevenKill_wx;//无暇七杀之气
    private String breakArmy_pt;//普通破军之血
    private String breakArmy_jl;//精良破军之血
    private String breakArmy_wx;//无暇破军之血

    public Destiny() {
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

    public Boolean getIsDestiny() {
        return isDestiny;
    }

    public void setIsDestiny(Boolean destiny) {
        isDestiny = destiny;
    }

    public Integer getDisobey() {
        return disobey;
    }

    public void setDisobey(Integer disobey) {
        this.disobey = disobey;
    }

    public Integer getDestinyEffect1() {
        return destinyEffect1;
    }

    public void setDestinyEffect1(Integer destinyEffect1) {
        this.destinyEffect1 = destinyEffect1;
    }

    public Integer getDestinyEffect2() {
        return destinyEffect2;
    }

    public void setDestinyEffect2(Integer destinyEffect2) {
        this.destinyEffect2 = destinyEffect2;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Integer getChipCount() {
        return chipCount;
    }

    public void setChipCount(Integer chipCount) {
        this.chipCount = chipCount;
    }

    public Integer getBreachCount() {
        return breachCount;
    }

    public void setBreachCount(Integer breachCount) {
        this.breachCount = breachCount;
    }

    public Integer getCrapeMyrtle() {
        return crapeMyrtle;
    }

    public void setCrapeMyrtle(Integer crapeMyrtle) {
        this.crapeMyrtle = crapeMyrtle;
    }

    public String getLife_pt() {
        return life_pt;
    }

    public void setLife_pt(String life_pt) {
        this.life_pt = life_pt;
    }

    public String getLife_jl() {
        return life_jl;
    }

    public void setLife_jl(String life_jl) {
        this.life_jl = life_jl;
    }

    public String getLife_wx() {
        return life_wx;
    }

    public void setLife_wx(String life_wx) {
        this.life_wx = life_wx;
    }

    public String getSecret_pt() {
        return secret_pt;
    }

    public void setSecret_pt(String secret_pt) {
        this.secret_pt = secret_pt;
    }

    public String getSecret_jl() {
        return secret_jl;
    }

    public void setSecret_jl(String secret_jl) {
        this.secret_jl = secret_jl;
    }

    public String getSecret_wx() {
        return secret_wx;
    }

    public void setSecret_wx(String secret_wx) {
        this.secret_wx = secret_wx;
    }

    public String getHeaven_pt() {
        return heaven_pt;
    }

    public void setHeaven_pt(String heaven_pt) {
        this.heaven_pt = heaven_pt;
    }

    public String getHeaven_jl() {
        return heaven_jl;
    }

    public void setHeaven_jl(String heaven_jl) {
        this.heaven_jl = heaven_jl;
    }

    public String getHeaven_wx() {
        return heaven_wx;
    }

    public void setHeaven_wx(String heaven_wx) {
        this.heaven_wx = heaven_wx;
    }

    public String getLunar_pt() {
        return lunar_pt;
    }

    public void setLunar_pt(String lunar_pt) {
        this.lunar_pt = lunar_pt;
    }

    public String getLunar_jl() {
        return lunar_jl;
    }

    public void setLunar_jl(String lunar_jl) {
        this.lunar_jl = lunar_jl;
    }

    public String getLunar_wx() {
        return lunar_wx;
    }

    public void setLunar_wx(String lunar_wx) {
        this.lunar_wx = lunar_wx;
    }

    public String getSun_pt() {
        return sun_pt;
    }

    public void setSun_pt(String sun_pt) {
        this.sun_pt = sun_pt;
    }

    public String getSun_jl() {
        return sun_jl;
    }

    public void setSun_jl(String sun_jl) {
        this.sun_jl = sun_jl;
    }

    public String getSun_wx() {
        return sun_wx;
    }

    public void setSun_wx(String sun_wx) {
        this.sun_wx = sun_wx;
    }

    public String getGreedy_pt() {
        return greedy_pt;
    }

    public void setGreedy_pt(String greedy_pt) {
        this.greedy_pt = greedy_pt;
    }

    public String getGreedy_jl() {
        return greedy_jl;
    }

    public void setGreedy_jl(String greedy_jl) {
        this.greedy_jl = greedy_jl;
    }

    public String getGreedy_wx() {
        return greedy_wx;
    }

    public void setGreedy_wx(String greedy_wx) {
        this.greedy_wx = greedy_wx;
    }

    public String getLianZhen_pt() {
        return lianZhen_pt;
    }

    public void setLianZhen_pt(String lianZhen_pt) {
        this.lianZhen_pt = lianZhen_pt;
    }

    public String getLianZhen_jl() {
        return lianZhen_jl;
    }

    public void setLianZhen_jl(String lianZhen_jl) {
        this.lianZhen_jl = lianZhen_jl;
    }

    public String getLianZhen_wx() {
        return lianZhen_wx;
    }

    public void setLianZhen_wx(String lianZhen_wx) {
        this.lianZhen_wx = lianZhen_wx;
    }

    public String getSevenKill_pt() {
        return sevenKill_pt;
    }

    public void setSevenKill_pt(String sevenKill_pt) {
        this.sevenKill_pt = sevenKill_pt;
    }

    public String getSevenKill_jl() {
        return sevenKill_jl;
    }

    public void setSevenKill_jl(String sevenKill_jl) {
        this.sevenKill_jl = sevenKill_jl;
    }

    public String getSevenKill_wx() {
        return sevenKill_wx;
    }

    public void setSevenKill_wx(String sevenKill_wx) {
        this.sevenKill_wx = sevenKill_wx;
    }

    public String getBreakArmy_pt() {
        return breakArmy_pt;
    }

    public void setBreakArmy_pt(String breakArmy_pt) {
        this.breakArmy_pt = breakArmy_pt;
    }

    public String getBreakArmy_jl() {
        return breakArmy_jl;
    }

    public void setBreakArmy_jl(String breakArmy_jl) {
        this.breakArmy_jl = breakArmy_jl;
    }

    public String getBreakArmy_wx() {
        return breakArmy_wx;
    }

    public void setBreakArmy_wx(String breakArmy_wx) {
        this.breakArmy_wx = breakArmy_wx;
    }

    public Integer getCode1() {
        return code1;
    }

    public void setCode1(Integer code1) {
        this.code1 = code1;
    }

    public Integer getCode2() {
        return code2;
    }

    public void setCode2(Integer code2) {
        this.code2 = code2;
    }

    public Integer getCode3() {
        return code3;
    }

    public void setCode3(Integer code3) {
        this.code3 = code3;
    }
}
