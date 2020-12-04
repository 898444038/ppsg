package com.ming.ppsg2.utils.line;

/**
 * 指数
 */
public class ExpTrendLine extends OLSTrendLine {
    @Override
    protected double[] xVector(double x) {
        return new double[]{1,x};
    }

    @Override
    protected boolean logY() {return true;}
}
