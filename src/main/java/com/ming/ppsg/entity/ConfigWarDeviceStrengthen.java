package com.ming.ppsg.entity;

/**
 * 战器配置
 */
public class ConfigWarDeviceStrengthen {

    private Long id;
    private Integer lv;//强化等级
    private Long deviceId;//ConfigWarDevice表ID
    private Integer cardSoul;//卡魂数量
    private Integer bloodstone;//血玉数量
    private Double successRate;//成功率
    private Integer safe;//是否可以安全强化1：可以，0：不可以
    private Integer bottomTicket;//保底券数量
    private Integer forceVal;//武力值
    private Integer intellectVal;//智力值
    private Integer troopsVal;//兵力值

    public ConfigWarDeviceStrengthen() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLv() {
        return lv;
    }

    public void setLv(Integer lv) {
        this.lv = lv;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getCardSoul() {
        return cardSoul;
    }

    public void setCardSoul(Integer cardSoul) {
        this.cardSoul = cardSoul;
    }

    public Integer getBloodstone() {
        return bloodstone;
    }

    public void setBloodstone(Integer bloodstone) {
        this.bloodstone = bloodstone;
    }

    public Double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Double successRate) {
        this.successRate = successRate;
    }

    public Integer getSafe() {
        return safe;
    }

    public void setSafe(Integer safe) {
        this.safe = safe;
    }

    public Integer getBottomTicket() {
        return bottomTicket;
    }

    public void setBottomTicket(Integer bottomTicket) {
        this.bottomTicket = bottomTicket;
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
