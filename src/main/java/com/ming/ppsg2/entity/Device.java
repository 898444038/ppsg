package com.ming.ppsg2.entity;

import com.ming.ppsg2.enums.GeneralsEnum;

import java.math.BigDecimal;

/**
 * 炼器：
 * 0 -> 1 : 31/0/0/0%
 * 1 -> 2 : 31/0/31/0%
 * 2 -> 3 : 31/31/31/0%
 * 3 -> 4 : 31/31/62/0% 200/20
 * 4 -> 5 : 49/49/70/0% 200/20
 */
public class Device {
    //战器类型名称
    private String name;
    private Integer code;
    //战器类型描述
    private String desc;
    //排名
    private String tops;
    //战器名称
    private String deviceName;
    //专属
    private String[] generals2;
    //专属显示
    private String exclusiveDesc;
    //是否共鸣
    private Boolean resonance;
    //共鸣显示
    private String resonanceDesc;
    //战器淬炼效果1名称
    private String QuenchingName1;
    //战器淬炼效果2名称
    private String QuenchingName2;
    //战器基础三维
    private ThreeDimensional deviceBaseThreeDimensional;
    //战器强化三维
    private ThreeDimensional deviceStrengthenThreeDimensional;
    //战器淬炼三维
    private ThreeDimensional deviceQuenchingThreeDimensional;
    //战器专属三维
    private ThreeDimensional deviceExclusiveThreeDimensional;
    //战器觉醒三维
    private ThreeDimensional deviceAwakenThreeDimensional;
    //战器炼器三维
    private ThreeDimensional deviceRefinerThreeDimensional;
    private ThreeDimensional deviceRefiner95ThreeDimensional;
    private ThreeDimensional deviceRefiner5ThreeDimensional;
    private ThreeDimensional deviceRefinerDestinyThreeDimensional;
    //战器器灵三维
    private ThreeDimensional deviceQiLingThreeDimensional;
    private ThreeDimensional totalThreeDimensional;

    public Device() {
    }

    public Device(Integer force, Integer intellect, Integer troops,GeneralsEnum.WarDevice warDevice,double rate) {
        deviceBaseThreeDimensional = new ThreeDimensional();
        deviceBaseThreeDimensional.setForce(force);
        deviceBaseThreeDimensional.setIntellect(intellect);
        deviceBaseThreeDimensional.setTroops(troops);
        deviceBaseThreeDimensional.setTotalZl((force+intellect+troops)*2);

        deviceStrengthenThreeDimensional = new ThreeDimensional();
        deviceStrengthenThreeDimensional.setForce(warDevice.getStrengthenForce());
        deviceStrengthenThreeDimensional.setIntellect(warDevice.getStrengthenIntellect());
        deviceStrengthenThreeDimensional.setTroops(warDevice.getStrengthenTroops());
        deviceStrengthenThreeDimensional.setTotalZl((warDevice.getStrengthenForce()+warDevice.getStrengthenIntellect()+warDevice.getStrengthenTroops())*2);

        deviceQuenchingThreeDimensional = new ThreeDimensional();
        BigDecimal rates = new BigDecimal(rate);
        BigDecimal doubles = new BigDecimal("2");

        BigDecimal b1 = new BigDecimal(force+warDevice.getStrengthenForce());
        BigDecimal qf = b1.multiply(rates).multiply(doubles);
        deviceQuenchingThreeDimensional.setForce(0);
        BigDecimal b2 = new BigDecimal(intellect+warDevice.getStrengthenIntellect());
        BigDecimal qi = b2.multiply(rates).multiply(doubles);
        deviceQuenchingThreeDimensional.setIntellect(0);
        BigDecimal b3 = new BigDecimal(troops+warDevice.getStrengthenTroops());
        BigDecimal qt = b3.multiply(rates).multiply(doubles);
        deviceQuenchingThreeDimensional.setTroops(qt.intValue());
        deviceQuenchingThreeDimensional.setTotalZl((qf.intValue()+qi.intValue()+qt.intValue())*2);
    }

    public String getExclusiveDesc() {
        return exclusiveDesc;
    }

    public void setExclusiveDesc(String exclusiveDesc) {
        this.exclusiveDesc = exclusiveDesc;
    }

    public String getResonanceDesc() {
        return resonanceDesc;
    }

    public void setResonanceDesc(String resonanceDesc) {
        this.resonanceDesc = resonanceDesc;
    }

    public String getTops() {
        return tops;
    }

    public void setTops(String tops) {
        this.tops = tops;
    }

    public ThreeDimensional getDeviceRefinerThreeDimensional() {
        return deviceRefinerThreeDimensional;
    }

    public void setDeviceRefinerThreeDimensional(ThreeDimensional deviceRefinerThreeDimensional) {
        this.deviceRefinerThreeDimensional = deviceRefinerThreeDimensional;
    }

    public ThreeDimensional getTotalThreeDimensional() {
        return totalThreeDimensional;
    }

    public void setTotalThreeDimensional(ThreeDimensional totalThreeDimensional) {
        this.totalThreeDimensional = totalThreeDimensional;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ThreeDimensional getDeviceRefinerDestinyThreeDimensional() {
        return deviceRefinerDestinyThreeDimensional;
    }

    public void setDeviceRefinerDestinyThreeDimensional(ThreeDimensional deviceRefinerDestinyThreeDimensional) {
        this.deviceRefinerDestinyThreeDimensional = deviceRefinerDestinyThreeDimensional;
    }

    public ThreeDimensional getDeviceRefiner5ThreeDimensional() {
        return deviceRefiner5ThreeDimensional;
    }

    public void setDeviceRefiner5ThreeDimensional(ThreeDimensional deviceRefiner5ThreeDimensional) {
        this.deviceRefiner5ThreeDimensional = deviceRefiner5ThreeDimensional;
    }

    public Boolean getResonance() {
        return resonance;
    }

    public void setResonance(Boolean resonance) {
        this.resonance = resonance;
    }

    public String[] getGenerals2() {
        return generals2;
    }

    public void setGenerals2(String[] generals2) {
        this.generals2 = generals2;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public ThreeDimensional getDeviceQiLingThreeDimensional() {
        return deviceQiLingThreeDimensional;
    }

    public void setDeviceQiLingThreeDimensional(ThreeDimensional deviceQiLingThreeDimensional) {
        this.deviceQiLingThreeDimensional = deviceQiLingThreeDimensional;
    }

    public ThreeDimensional getDeviceAwakenThreeDimensional() {
        return deviceAwakenThreeDimensional;
    }

    public void setDeviceAwakenThreeDimensional(ThreeDimensional deviceAwakenThreeDimensional) {
        this.deviceAwakenThreeDimensional = deviceAwakenThreeDimensional;
    }

    public ThreeDimensional getDeviceRefiner95ThreeDimensional() {
        return deviceRefiner95ThreeDimensional;
    }

    public void setDeviceRefiner95ThreeDimensional(ThreeDimensional deviceRefiner95ThreeDimensional) {
        this.deviceRefiner95ThreeDimensional = deviceRefiner95ThreeDimensional;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuenchingName1() {
        return QuenchingName1;
    }

    public void setQuenchingName1(String quenchingName1) {
        QuenchingName1 = quenchingName1;
    }

    public String getQuenchingName2() {
        return QuenchingName2;
    }

    public void setQuenchingName2(String quenchingName2) {
        QuenchingName2 = quenchingName2;
    }

    public ThreeDimensional getDeviceBaseThreeDimensional() {
        return deviceBaseThreeDimensional;
    }

    public void setDeviceBaseThreeDimensional(ThreeDimensional deviceBaseThreeDimensional) {
        this.deviceBaseThreeDimensional = deviceBaseThreeDimensional;
    }

    public ThreeDimensional getDeviceStrengthenThreeDimensional() {
        return deviceStrengthenThreeDimensional;
    }

    public void setDeviceStrengthenThreeDimensional(ThreeDimensional deviceStrengthenThreeDimensional) {
        this.deviceStrengthenThreeDimensional = deviceStrengthenThreeDimensional;
    }

    public ThreeDimensional getDeviceQuenchingThreeDimensional() {
        return deviceQuenchingThreeDimensional;
    }

    public void setDeviceQuenchingThreeDimensional(ThreeDimensional deviceQuenchingThreeDimensional) {
        this.deviceQuenchingThreeDimensional = deviceQuenchingThreeDimensional;
    }

    public ThreeDimensional getDeviceExclusiveThreeDimensional() {
        return deviceExclusiveThreeDimensional;
    }

    public void setDeviceExclusiveThreeDimensional(ThreeDimensional deviceExclusiveThreeDimensional) {
        this.deviceExclusiveThreeDimensional = deviceExclusiveThreeDimensional;
    }
}
