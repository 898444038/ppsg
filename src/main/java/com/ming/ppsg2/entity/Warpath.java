package com.ming.ppsg2.entity;

public class Warpath {

    private Integer personal;
    private String personalName;
    private Integer group;
    private String groupName;

    private String generalsCode;

    private Integer force = 0;
    private Integer intellect = 0;
    private Integer troops = 0;

    public Warpath() {
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getGeneralsCode() {
        return generalsCode;
    }

    public void setGeneralsCode(String generalsCode) {
        this.generalsCode = generalsCode;
    }

    public Integer getPersonal() {
        return personal;
    }

    public void setPersonal(Integer personal) {
        this.personal = personal;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Warpath{");
        sb.append("personal=").append(personal);
        sb.append(", personalName=").append(personalName);
        sb.append(", group=").append(group);
        sb.append(", groupName=").append(groupName);
        sb.append(", generalsCode=").append(generalsCode);
        sb.append(", force=").append(force);
        sb.append(", intellect=").append(intellect);
        sb.append(", troops=").append(troops);
        sb.append('}');
        return sb.toString();
    }
}
