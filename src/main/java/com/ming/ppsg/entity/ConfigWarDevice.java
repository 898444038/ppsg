package com.ming.ppsg.entity;

/**
 * 战器配置
 */
public class ConfigWarDevice {

    private Long id;

    private String warDeviceName;

    private String type;//0:普通 1：特殊

    private Integer force;//基础战器武力
    private Integer intellect;//基础战器智力
    private Integer troops;//基础战器兵力
    private Integer strengthenForce;//强化15战器武力
    private Integer strengthenIntellect;//强化15战器智力
    private Integer strengthenTroops;//强化15战器兵力
    private Double quenchingForceRate;//淬炼战器武力百分比
    private Double quenchingIntellectRate;//淬炼战器智力百分比
    private Double quenchingTroopsRate;//淬炼战器兵力百分比
    private Integer exclusiveForce;//专属战器武力
    private Integer exclusiveIntellect;//专属战器智力
    private Integer exclusiveTroops;//专属战器兵力
    private Integer passive1;//被动1战力
    private Integer passive2;//被动2战力
    private Integer passive3;//被动3战力

    public ConfigWarDevice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
