package com.ming.ppsg2.entity;

public class ThreeDimensionals {

    private Double force;
    private Double intellect;
    private Double Troops;

    private Integer force0;
    private Integer intellect0;
    private Integer Troops0;

    private Double total;

    public ThreeDimensionals() {}

    public ThreeDimensionals(Double force, Double intellect, Double troops) {
        this.force = force;
        this.intellect = intellect;
        this.Troops = troops;
    }

    public Double getForce() {
        return force;
    }

    public void setForce(Double force) {
        this.force = force;
        //this.force0 = force.intValue();
    }

    public Double getIntellect() {
        return intellect;
    }

    public void setIntellect(Double intellect) {
        this.intellect = intellect;
        //this.intellect0 = intellect.intValue();
    }

    public Double getTroops() {
        return Troops;
    }

    public void setTroops(Double troops) {
        this.Troops = troops;
        //this.Troops0 = troops.intValue();
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    public Integer getForce0() {
        return force.intValue();
    }

    public void setForce0(Integer force0) {
        this.force0 = force0;
    }

    public Integer getIntellect0() {
        return intellect.intValue();
    }

    public void setIntellect0(Integer intellect0) {
        this.intellect0 = intellect0;
    }

    public Integer getTroops0() {
        return Troops.intValue();
    }

    public void setTroops0(Integer troops0) {
        Troops0 = troops0;
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
