package com.ming.ppsg.entity;

public class ConfigGeneralsType {
    private Long id;

    private String generalsTypeName;

    private Integer forceGrowth;//武力成长
    private Integer intellectGrowth;//智力成长
    private Integer troopsGrowth;//兵力成长

    public ConfigGeneralsType(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeneralsTypeName() {
        return generalsTypeName;
    }

    public void setGeneralsTypeName(String generalsTypeName) {
        this.generalsTypeName = generalsTypeName;
    }

    public Integer getForceGrowth() {
        return forceGrowth;
    }

    public void setForceGrowth(Integer forceGrowth) {
        this.forceGrowth = forceGrowth;
    }

    public Integer getIntellectGrowth() {
        return intellectGrowth;
    }

    public void setIntellectGrowth(Integer intellectGrowth) {
        this.intellectGrowth = intellectGrowth;
    }

    public Integer getTroopsGrowth() {
        return troopsGrowth;
    }

    public void setTroopsGrowth(Integer troopsGrowth) {
        this.troopsGrowth = troopsGrowth;
    }
}
