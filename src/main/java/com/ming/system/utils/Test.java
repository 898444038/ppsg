package com.ming.system.utils;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Administrator on 2019/12/10 0010.
 */
public class Test {
    private static Generals g1;
    private static Generals g2;
    private static Generals g3;
    private static Generals g4;
    private static Generals g5;

    private static Generals g6;
    private static Generals g7;
    private static Generals g8;
    private static Generals g9;
    private static Generals g10;

    //((A2*7.680799912*1.12*1.252*1.12*1.4)*(1.916867577*(A2/B2)-0.999954381))*(1-0.252)*(1-0.4)*(1-0.35)*(1-0.15)*(1-0.2)
    public Test() {
        g1 = new Generals("yx1",7000,11800,11600,120,new Double[]{0.12,0.252,0.12,0.4,0.15},new Double[]{0.252,0.15,0.2});
        g2 = new Generals("ly1",7000,8500,11600,150,new Double[]{0.12,0.252,0.12,0.4,0.15},new Double[]{0.252,0.15,0.2});
        g3 = new Generals("sw1",7000,8500,11600,150,new Double[]{0.12,0.252,0.12,0.4,0.15},new Double[]{0.252,0.15,0.2});
        g4 = new Generals("gq1",7000,8500,11600,120,new Double[]{0.12,0.252,0.12,0.4,0.15},new Double[]{0.252,0.15,0.2});
        g5 = new Generals("lj1",7000,8500,11600,120,new Double[]{0.12,0.252,0.12,0.4,0.15},new Double[]{0.252,0.15,0.2});

        g6 = new Generals("yx2",7000,11800,11600,120,new Double[]{0.12,0.252,0.12,0.4,0.15},new Double[]{0.252,0.15,0.2});
        g7 = new Generals("ly2",7000,8500,11600,150,new Double[]{0.12,0.252,0.12,0.4,0.15},new Double[]{0.252,0.15,0.2});
        g8 = new Generals("sw2",7000,8500,11600,150,new Double[]{0.12,0.252,0.12,0.4,0.15},new Double[]{0.252,0.15,0.2});
        g9 = new Generals("gq2",7000,8500,11600,120,new Double[]{0.12,0.252,0.12,0.4,0.15},new Double[]{0.252,0.15,0.2});
        g10= new Generals("lj2",7000,8500,11600,120,new Double[]{0.12,0.252,0.12,0.4,0.15},new Double[]{0.252,0.15,0.2});
    }

    public static void main(String[] args) {
        Test test = new Test();
        add(g1,g2,g3,g4,g5);

        add2(g6,g7,g8,g9,g10);
        add3(g6,g7,g8,g9,g10);

        System.out.println("");






    }

    public static void addHp(Generals g1,Generals g2,Generals g3,Generals g4,Generals g5){
        int h2 = g2.getBin()*g2.getBinXi();
        int h3 = g3.getBin()*g3.getBinXi();

        g1.setHp(g1.getBin()*g1.getBinXi());
        g2.setHp(h2);
        g3.setHp(h3);
        g4.setHp(g4.getBin()*g4.getBinXi());
        g5.setHp(g5.getBin()*g5.getBinXi());
    }

    public static void add(Generals g1,Generals g2,Generals g3,Generals g4,Generals g5){
        Double d1= g1.getZhi()*1.15*1.6*1.2*1.2*1.2*1.2*1.2*1.45*1.35;
        g1.setZhi(d1.intValue());
        Double d2= g2.getZhi()*1.6*1.45;
        g2.setZhi(d2.intValue());
        Double d3= g3.getZhi()*1.6*1.45;
        g3.setZhi(d3.intValue());
        Double d4= g4.getZhi()*1.6*1.45;
        g4.setZhi(d4.intValue());
        Double d5= g5.getZhi()*1.6*1.45;
        g5.setZhi(d5.intValue());
    }

    public static void add2(Generals g1,Generals g2,Generals g3,Generals g4,Generals g5){
        Double d1= g1.getZhi()*1.15*1.6*1.2*1.2*1.2*1.2*1.2;
        g1.setZhi(d1.intValue());
        Double d2= g2.getZhi()*1.6;
        g2.setZhi(d2.intValue());
        Double d3= g3.getZhi()*1.6;
        g3.setZhi(d3.intValue());
        Double d4= g4.getZhi()*1.6;
        g4.setZhi(d4.intValue());
        Double d5= g5.getZhi()*1.6;
        g5.setZhi(d5.intValue());
    }

    public static void add3(Generals g1,Generals g2,Generals g3,Generals g4,Generals g5){
        Double d1= g1.getZhi()*1.45*1.35;
        g1.setZhi(d1.intValue());
        Double d2= g2.getZhi()*1.45;
        g2.setZhi(d2.intValue());
        Double d3= g3.getZhi()*1.45;
        g3.setZhi(d3.intValue());
        Double d4= g4.getZhi()*1.45;
        g4.setZhi(d4.intValue());
        Double d5= g5.getZhi()*1.45;
        g5.setZhi(d5.intValue());
    }
}


class Generals{
    private String name;
    private Integer wu;
    private Integer zhi;
    private Integer bin;
    private Integer binXi;
    private Integer hp;
    private Double[] zeng;
    private Double[] jian;
    Generals(){}

    public Generals(String name, Integer wu, Integer zhi, Integer bin,Integer binXi, Double[] zeng, Double[] jian) {
        this.name = name;
        this.wu = wu;
        this.zhi = zhi;
        this.bin = bin;
        this.binXi = binXi;
        this.hp = hp;
        this.zeng = zeng;
        this.jian = jian;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWu() {
        return wu;
    }

    public void setWu(Integer wu) {
        this.wu = wu;
    }

    public Integer getZhi() {
        return zhi;
    }

    public void setZhi(Integer zhi) {
        this.zhi = zhi;
    }

    public Integer getBin() {
        return bin;
    }

    public void setBin(Integer bin) {
        this.bin = bin;
    }

    public Double[] getZeng() {
        return zeng;
    }

    public void setZeng(Double[] zeng) {
        this.zeng = zeng;
    }

    public Double[] getJian() {
        return jian;
    }

    public void setJian(Double[] jian) {
        this.jian = jian;
    }

    public Integer getBinXi() {
        return binXi;
    }

    public void setBinXi(Integer binXi) {
        this.binXi = binXi;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }
}