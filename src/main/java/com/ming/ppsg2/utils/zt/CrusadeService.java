package com.ming.ppsg2.utils.zt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CrusadeService {


    public static List<Crusade> getSeconds(double second) throws Exception {
        List<Crusade> seconds = new ArrayList<>();
        seconds.add(new Crusade(0.00d," 0.00"));
        BigDecimal b0 = new BigDecimal("0.00");
        BigDecimal b1 = new BigDecimal("0.01");
        while (b0.doubleValue() < second){
            b0 = b0.add(b1);
            double d = b0.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            String[] ds = (d+"").split("\\.");
            Crusade crusade = new Crusade();
            crusade.setSecond(d);
            crusade.setFormatSecond((ds[0].length()==2?ds[0]:" "+ds[0])+"."+(ds[1].length()==2?ds[1]:ds[1]+"0"));
            seconds.add(crusade);
        }
        return seconds;
    }

    public static List<String> getMorales(List<Crusade> seconds) {
        BigDecimal b = new BigDecimal("3.33");

        BigDecimal m0 = new BigDecimal(6);
        BigDecimal m1 = new BigDecimal("1");
        for (Crusade second : seconds) {
            BigDecimal b1 = BigDecimal.valueOf(second.getSecond());
            BigDecimal[] c = b1.divideAndRemainder(b);
            if(c[1].doubleValue() == 0.00d){
                //System.out.println(second.getFormatSecond());
                second.setMorales(m0.doubleValue());
                m0 = m0.add(m1);
            }
        }
        System.out.println();
        for (Crusade second : seconds) {
            if(second.getMorales()!=null){
                System.out.println(second.getFormatSecond()+" "+ second.getMorales());
            }
        }

        //Map<String,Double> moralesMap = new HashMap<>();
        //moralesMap.put("0.00",6.2);
        /*String[] arr1 = {
                "0.00",
                "3.33","6.66","10.00",
                "13.33","16.66","20.00",
                "23.33","26.66","30.00",
                "33.33","36.66","40.00",
                "43.33","46.66","50.00",
                "53.33","56.66","60.00",
        };
        List<Crusade> moralesList = new ArrayList<>();
        BigDecimal b0 = new BigDecimal(6);
        BigDecimal b1 = new BigDecimal("1");
        for (Crusade second : seconds) {
            for (String arr : arr1) {
                if(arr.equals(second.getFormatSecond().trim())){
                    System.out.println(second.getFormatSecond()+" "+ b0.intValue());
                    b0 = b0.add(b1);
                }
            }
        }*/
        return null;
    }
}
