package com.ming.ppsg2.entity;

public class Symbols {

    private Integer number;

    private Integer mainAttr;
    private String mainAttrName;

    private Integer attr1;
    private String attrName1;
    private Integer attr2;
    private String attrName2;
    private Integer attr3;
    private String attrName3;
    private Integer attr4;
    private String attrName4;

    //private Double mainForce = 0d;
    //private Double mainIntellect = 0d;
    //private Double mainTroops = 0d;

    private Integer force = 0;
    private Integer intellect = 0;
    private Integer troops = 0;

    private Integer type;
    private String typeName;

    public Symbols() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getMainAttr() {
        return mainAttr;
    }

    public void setMainAttr(Integer mainAttr) {
        this.mainAttr = mainAttr;
    }

    public Integer getAttr1() {
        return attr1;
    }

    public void setAttr1(Integer attr1) {
        this.attr1 = attr1;
    }

    public Integer getAttr2() {
        return attr2;
    }

    public void setAttr2(Integer attr2) {
        this.attr2 = attr2;
    }

    public Integer getAttr3() {
        return attr3;
    }

    public void setAttr3(Integer attr3) {
        this.attr3 = attr3;
    }

    public Integer getAttr4() {
        return attr4;
    }

    public void setAttr4(Integer attr4) {
        this.attr4 = attr4;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMainAttrName() {
        return mainAttrName;
    }

    public void setMainAttrName(String mainAttrName) {
        this.mainAttrName = mainAttrName;
    }

    public String getAttrName1() {
        return attrName1;
    }

    public void setAttrName1(String attrName1) {
        this.attrName1 = attrName1;
    }

    public String getAttrName2() {
        return attrName2;
    }

    public void setAttrName2(String attrName2) {
        this.attrName2 = attrName2;
    }

    public String getAttrName3() {
        return attrName3;
    }

    public void setAttrName3(String attrName3) {
        this.attrName3 = attrName3;
    }

    public String getAttrName4() {
        return attrName4;
    }

    public void setAttrName4(String attrName4) {
        this.attrName4 = attrName4;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Symbols{");
        sb.append("number=").append(number);
        sb.append(", mainAttr=").append(mainAttr);
        sb.append(", mainAttrName='").append(mainAttrName).append('\'');
        sb.append(", attr1=").append(attr1);
        sb.append(", attrName1='").append(attrName1).append('\'');
        sb.append(", attr2=").append(attr2);
        sb.append(", attrName2='").append(attrName2).append('\'');
        sb.append(", attr3=").append(attr3);
        sb.append(", attrName3='").append(attrName3).append('\'');
        sb.append(", attr4=").append(attr4);
        sb.append(", attrName4='").append(attrName4).append('\'');
        sb.append(", force=").append(force);
        sb.append(", intellect=").append(intellect);
        sb.append(", troops=").append(troops);
        sb.append(", type=").append(type);
        sb.append(", typeName='").append(typeName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
