package com.ming.ppsg2.utils.line;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        double[][] arr = {
                {100,200},
                {200,400},
                {300,400},
                {400,200},
        };

        TrendLine t = new PolyTrendLine(2);
        Random rand = new Random();
        /*double[] x = new double[1000*1000];
        double[] err = new double[x.length];
        double[] y = new double[x.length];
        for (int i=0; i<x.length; i++) { x[i] = 1000*rand.nextDouble(); }
        for (int i=0; i<x.length; i++) { err[i] = 100*rand.nextGaussian(); }
        for (int i=0; i<x.length; i++) { y[i] = x[i]*x[i]+err[i]; } // quadratic model
*/
        int len = arr.length;
        double[] x = new double[len];
        double[] y = new double[len];
        for(int i=0;i<len;i++){
            x[i] = arr[i][0];
            y[i] = arr[i][1];
        }

        t.setValues(y,x);
        System.out.println(t.predict(500)); // when x=12, y should be... , eg 143.61380202745192
    }
}
