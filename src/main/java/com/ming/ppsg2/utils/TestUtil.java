package com.ming.ppsg2.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by Administrator on 2020/3/26 0026.
 */
public class TestUtil {

    public static void main(String[] args) {
        //0.7,1.72

        //a=0.78 , b=1.351 , c=1.56 , width:3.12 , height:2.70200

        // a=0.78 , b=1.351 , c=1.56
        // a=7.8 , b=13.51 , c=15.6
        /*BigDecimal b = new BigDecimal("100");
        for(int i=100;i<=1000;i++){
            BigDecimal a = new BigDecimal(i+"");
            a = a.divide(b);
            sjx(a);
        }*/
        BigDecimal a = new BigDecimal("0.78");
        sjx(a);
    }

    public static void sjx(BigDecimal a){
        BigDecimal scale = new BigDecimal("2");
        BigDecimal b;
        BigDecimal c;
        c = a.multiply(scale);
        b = sqrt(c.multiply(c).subtract(a.multiply(a)),5);
        //if((b.doubleValue()+"").split("\\.")[1].length()<=2){
            System.out.println("a="+a.doubleValue()+" , b="+b.doubleValue()+" , c="+c.doubleValue()+" , width:"+c.multiply(scale)+" , height:"+b.multiply(scale));
        //}
    }



    public static BigDecimal sqrt(BigDecimal value, int scale){
        BigDecimal num2 = BigDecimal.valueOf(2);
        int precision = 100;
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal deviation = value;
        int cnt = 0;
        while (cnt < precision) {
            deviation = (deviation.add(value.divide(deviation, mc))).divide(num2, mc);
            cnt++;
        }
        deviation = deviation.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return deviation;
    }
}
