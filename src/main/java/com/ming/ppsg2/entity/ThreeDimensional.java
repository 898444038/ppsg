package com.ming.ppsg2.entity;

public class ThreeDimensional {

    private Integer force;
    private Integer intellect;
    private Integer Troops;

    private Integer total;

    public ThreeDimensional() {}

    public ThreeDimensional(Integer force, Integer intellect, Integer troops) {
        this.force = force;
        this.intellect = intellect;
        Troops = troops;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("force=").append(force);
        sb.append(", intellect=").append(intellect);
        sb.append(", Troops=").append(Troops);
        sb.append(", total=").append(total);
        sb.append('}');
        return sb.toString();
    }
}
