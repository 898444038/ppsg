package com.ming.ppsg.entity;

public class ConfigArms {

    private Long id;

    private String armsName;

    private Double forceRate;//武力科技加成
    private Double intellectRate;//智力科技加成
    private Double troopsRate;//兵力科技加成

    public ConfigArms(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArmsName() {
        return armsName;
    }

    public void setArmsName(String armsName) {
        this.armsName = armsName;
    }

    public Double getForceRate() {
        return forceRate;
    }

    public void setForceRate(Double forceRate) {
        this.forceRate = forceRate;
    }

    public Double getIntellectRate() {
        return intellectRate;
    }

    public void setIntellectRate(Double intellectRate) {
        this.intellectRate = intellectRate;
    }

    public Double getTroopsRate() {
        return troopsRate;
    }

    public void setTroopsRate(Double troopsRate) {
        this.troopsRate = troopsRate;
    }
}
