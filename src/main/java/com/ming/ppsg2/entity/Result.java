package com.ming.ppsg2.entity;

import java.util.List;

public class Result {

    private Integer rank;//排名

    private Integer total;//总战力
    private Integer total2;//总战力（特殊战器）

    private Integer weiCount;//魏国数量
    private Integer shuCount;//蜀国数量
    private Integer wuCount;//吴国数量
    private Integer qunCount;//群雄数量
    private String countryNames;

    private Integer qiangCount;//枪兵数量
    private Integer qiCount;//骑兵数量
    private Integer gongCount;//弓兵数量
    private String armsNames;

    private List<Symbols> symbolsList;//兵符

    private List<Generals> generalsList;
//
//    private Symbols symbols1;
//    private Symbols symbols2;
//    private Symbols symbols3;
//    private Symbols symbols4;
//    private Symbols symbols5;
//    private Symbols symbols6;
//
//    private Generals generals1;
//    private Generals generals2;
//    private Generals generals3;
//    private Generals generals4;
//    private Generals generals5;

    public Result() {
    }

    public Integer getTotal2() {
        return total2;
    }

    public void setTotal2(Integer total2) {
        this.total2 = total2;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public List<Generals> getGeneralsList() {
        return generalsList;
    }

    public void setGeneralsList(List<Generals> generalsList) {
        this.generalsList = generalsList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Symbols> getSymbolsList() {
        return symbolsList;
    }

    public void setSymbolsList(List<Symbols> symbolsList) {
        this.symbolsList = symbolsList;
    }

    public Integer getWeiCount() {
        return weiCount;
    }

    public void setWeiCount(Integer weiCount) {
        this.weiCount = weiCount;
    }

    public Integer getShuCount() {
        return shuCount;
    }

    public void setShuCount(Integer shuCount) {
        this.shuCount = shuCount;
    }

    public Integer getWuCount() {
        return wuCount;
    }

    public void setWuCount(Integer wuCount) {
        this.wuCount = wuCount;
    }

    public Integer getQunCount() {
        return qunCount;
    }

    public void setQunCount(Integer qunCount) {
        this.qunCount = qunCount;
    }

    public Integer getQiangCount() {
        return qiangCount;
    }

    public void setQiangCount(Integer qiangCount) {
        this.qiangCount = qiangCount;
    }

    public Integer getQiCount() {
        return qiCount;
    }

    public void setQiCount(Integer qiCount) {
        this.qiCount = qiCount;
    }

    public Integer getGongCount() {
        return gongCount;
    }

    public void setGongCount(Integer gongCount) {
        this.gongCount = gongCount;
    }

    public String getCountryNames() {
        return countryNames;
    }

    public void setCountryNames(String countryNames) {
        this.countryNames = countryNames;
    }

    public String getArmsNames() {
        return armsNames;
    }

    public void setArmsNames(String armsNames) {
        this.armsNames = armsNames;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Result{");
        sb.append("rank=").append(rank);
        sb.append(", total=").append(total);
        sb.append(", symbolsList=").append(symbolsList);
        sb.append(", generalsList=").append(generalsList);
        sb.append('}');
        return sb.toString();
    }
}
