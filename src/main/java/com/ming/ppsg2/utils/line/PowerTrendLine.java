package com.ming.ppsg2.utils.line;

/**
 * 功率
 */
public class PowerTrendLine extends OLSTrendLine {
    @Override
    protected double[] xVector(double x) {
        return new double[]{1,Math.log(x)};
    }

    @Override
    protected boolean logY() {return true;}

}
