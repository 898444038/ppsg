package com.ming.ppsg2.entity;

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
