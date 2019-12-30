package com.ming.system.utils;

import java.util.ArrayList;
import java.util.List;

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


        List<String> list = new ArrayList<>();
        list.add("	奋威袁绍	");
        list.add("	灵刃吕玲绮	");
        list.add("	劫国董卓	");
        list.add("	顾影貂蝉	");
        list.add("	虎涧典韦	");
        list.add("	霸业曹操	");
        list.add("	墨衍荀彧	");
        list.add("	诡骑张飞	");
        list.add("	神武刘备	");
        list.add("	陨星庞统	");
        list.add("	狂澜吕蒙	");
        list.add("	命世孙权	");
        list.add("	烈胆凌统	");
        list.add("	砺战赵云	");
        list.add("	武帝曹操	");
        list.add("	武帝曹操_限	");
        list.add("	奸雄曹操	");
        list.add("	奸雄曹操_限	");
        list.add("	宣王司马懿	");
        list.add("	狼顾司马懿	");
        list.add("	狼顾司马懿_限	");
        list.add("	振威张辽	");
        list.add("	振威张辽_限	");
        list.add("	召虎张辽	");
        list.add("	召虎张辽_限	");
        list.add("	独目夏侯惇	");
        list.add("	独目夏侯惇_限	");
        list.add("	苍狼夏侯惇	");
        list.add("	苍狼夏侯惇_限	");
        list.add("	混沌许褚	");
        list.add("	英灵典韦	");
        list.add("	恶来典韦	");
        list.add("	恶来典韦_限	");
        list.add("	鬼谋郭嘉	");
        list.add("	鬼谋郭嘉_限	");
        list.add("	天妒郭嘉	");
        list.add("	天妒郭嘉_限	");
        list.add("	征南曹仁	");
        list.add("	疾风曹仁	");
        list.add("	疾风曹仁_限	");
        list.add("	乱武贾诩	");
        list.add("	乱武贾诩_限	");
        list.add("	毒士贾诩	");
        list.add("	毒士贾诩_限	");
        list.add("	文帝曹丕	");
        list.add("	灭奏曹丕	");
        list.add("	典军夏侯渊	");
        list.add("	虎步夏侯渊	");
        list.add("	令香荀彧	");
        list.add("	驱虎荀彧	");
        list.add("	伶歌卞夫人	");
        list.add("	伶歌卞夫人_限	");
        list.add("	洛神甄姬	");
        list.add("	灵蛇甄姬	");
        list.add("	灵蛇甄姬_限	");
        list.add("	龙胆赵云	");
        list.add("	龙胆赵云_限	");
        list.add("	神威赵云	");
        list.add("	一骑关羽	");
        list.add("	武圣关羽	");
        list.add("	武圣关羽_限	");
        list.add("	七星诸葛亮	");
        list.add("	卧龙诸葛亮	");
        list.add("	卧龙诸葛亮_限	");
        list.add("	凤雏庞统	");
        list.add("	智极庞统	");
        list.add("	智极庞统_限	");
        list.add("	锦狮马超	");
        list.add("	锦狮马超_限	");
        list.add("	铁骑马超	");
        list.add("	铁骑马超_限	");
        list.add("	斗胆姜维	");
        list.add("	麒麟姜维	");
        list.add("	麒麟姜维_限	");
        list.add("	仁德刘备	");
        list.add("	仁德刘备_限	");
        list.add("	昭烈刘备	");
        list.add("	昭烈刘备_限	");
        list.add("	暴怒张飞	");
        list.add("	桓侯张飞	");
        list.add("	讨虏黄忠	");
        list.add("	弓神黄忠	");
        list.add("	弓神黄忠_限	");
        list.add("	狂骨魏延	");
        list.add("	骁勇魏延	");
        list.add("	骁勇魏延_限	");
        list.add("	落英黄舞蝶	");
        list.add("	落英黄舞蝶_限	");
        list.add("	惊鸿黄舞蝶	");
        list.add("	弓腰姬孙尚香	");
        list.add("	弓腰姬孙尚香_限	");
        list.add("	郡主孙尚香	");
        list.add("	阿丑黄月英	");
        list.add("	杰女黄月英	");
        list.add("	杰女黄月英_限	");
        list.add("	龙驹马云禄	");
        list.add("	奇谋法正	");
        list.add("	业炎周瑜	");
        list.add("	业炎周瑜_限	");
        list.add("	顾曲周瑜	");
        list.add("	克己吕蒙	");
        list.add("	虎威吕蒙	");
        list.add("	忠魂鲁肃	");
        list.add("	缔盟鲁肃	");
        list.add("	缔盟鲁肃_限	");
        list.add("	焚天陆逊	");
        list.add("	儒将陆逊	");
        list.add("	儒将陆逊_限	");
        list.add("	若虎孙权	");
        list.add("	吴王孙权	");
        list.add("	吴王孙权_限	");
        list.add("	猛虎孙坚	");
        list.add("	武烈孙坚	");
        list.add("	武烈孙坚_限	");
        list.add("	驰骋孙策	");
        list.add("	驰骋孙策_限	");
        list.add("	霸王孙策	");
        list.add("	霸王孙策_限	");
        list.add("	游侠甘宁	");
        list.add("	斗将甘宁	");
        list.add("	斗将甘宁_限	");
        list.add("	感古太史慈	");
        list.add("	矢志太史慈	");
        list.add("	矢志太史慈_限	");
        list.add("	浴血凌统	");
        list.add("	浴血凌统_限	");
        list.add("	国士凌统	");
        list.add("	国士凌统_限	");
        list.add("	挽歌大乔	");
        list.add("	挽歌大乔_限	");
        list.add("	望月大乔	");
        list.add("	望月大乔_限	");
        list.add("	临江小乔	");
        list.add("	玉琼小乔	");
        list.add("	凤仪步练师	");
        list.add("	赤忠黄盖	");
        list.add("	血痕周泰	");
        list.add("	双子吕姬	");
        list.add("	双子吕姬_限	");
        list.add("	猩红吕姬	");
        list.add("	猩红吕姬_限	");
        list.add("	双子吕玲绮	");
        list.add("	双子吕玲绮_限	");
        list.add("	战姬吕玲绮	");
        list.add("	战姬吕玲绮_限	");
        list.add("	倾世貂蝉	");
        list.add("	倾世貂蝉_限	");
        list.add("	绝舞貂蝉	");
        list.add("	绝舞貂蝉_限	");
        list.add("	浊世董卓	");
        list.add("	魔君董卓	");
        list.add("	魔君董卓_限	");
        list.add("	思召袁绍	");
        list.add("	贵胄袁绍	");
        list.add("	贵胄袁绍_限	");
        list.add("	霸音文丑	");
        list.add("	猎狐文丑	");
        list.add("	恶目颜良	");
        list.add("	鸦杀颜良	");
        list.add("	修罗吕布	");
        list.add("	鬼神吕布	");
        list.add("	鬼神吕布_限	");
        list.add("	符咒张角	");
        list.add("	天公张角	");
        list.add("	天公张角_限	");
        list.add("	雅歌张郃	");
        list.add("	雅歌张郃_限	");
        list.add("	天子汉献帝	");
        list.add("	妙手华佗	");
        list.add("	妖仙于吉	");
        list.add("	斩锋高顺	");
        list.add("	豹魂祝融夫人	");
        System.out.println();
        for(String str : list){
            System.out.println("<option value=\""+str.trim()+"\">"+str.trim()+"</option>");
        }


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