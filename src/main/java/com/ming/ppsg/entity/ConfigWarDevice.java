package com.ming.ppsg.entity;

/**
 * 战器配置
 */
public class ConfigWarDevice {

    private Long id;

    private String name;

    private Integer type;//0:普通战器 1：特殊战器

    private Integer level;//品质：白 黑 银 金 钻

    private Integer forceVal;
    private Integer intellectVal;
    private Integer troopsVal;

    public ConfigWarDevice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getForceVal() {
        return forceVal;
    }

    public void setForceVal(Integer forceVal) {
        this.forceVal = forceVal;
    }

    public Integer getIntellectVal() {
        return intellectVal;
    }

    public void setIntellectVal(Integer intellectVal) {
        this.intellectVal = intellectVal;
    }

    public Integer getTroopsVal() {
        return troopsVal;
    }

    public void setTroopsVal(Integer troopsVal) {
        this.troopsVal = troopsVal;
    }
}
