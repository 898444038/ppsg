package com.ming.ppsg2.entity;

import com.ming.ppsg2.enums.GeneralsEnum;

import java.math.BigDecimal;

public class Device {
    //战器类型名称
    private String name;
    //战器类型描述
    private String desc;
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
