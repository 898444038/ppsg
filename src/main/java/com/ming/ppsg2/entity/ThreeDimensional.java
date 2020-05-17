package com.ming.ppsg2.entity;

public class ThreeDimensional {

    private Integer force;
    private Integer intellect;
    private Integer troops;

    private Integer total;
    private Integer totalZl;//总战力

    private String remark;

    public ThreeDimensional() {}

    public ThreeDimensional(Integer force, Integer intellect, Integer troops) {
        this.force = force;
        this.intellect = intellect;
        this.troops = troops;
    }

    public Integer getTotalZl() {
        return (this.force+this.intellect+this.troops)*2;
    }

    public void setTotalZl(Integer totalZl) {
        this.totalZl = totalZl;
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
        return troops;
    }

    public void setTroops(Integer troops) {
        this.troops = troops;
    }

    public Integer getTotal() {
        return this.force+this.intellect+this.troops;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("force=").append(force);
        sb.append(", intellect=").append(intellect);
        sb.append(", Troops=").append(troops);
        sb.append(", total=").append(total);
        sb.append('}');
        return sb.toString();
    }
}
