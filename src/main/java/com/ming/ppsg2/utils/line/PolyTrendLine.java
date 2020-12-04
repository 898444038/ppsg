package com.ming.ppsg2.utils.line;

/**
 * 多项式或线性模型的实现（对于线性模型，只需在调用构造函数时将度数设置为1.）
 */
public class PolyTrendLine extends OLSTrendLine {
    final int degree;
    public PolyTrendLine(int degree) {
        if (degree < 0) throw new IllegalArgumentException("The degree of the polynomial must not be negative");
        this.degree = degree;
    }
    protected double[] xVector(double x) { // {1, x, x*x, x*x*x, ...}
        double[] poly = new double[degree+1];
        double xi=1;
        for(int i=0; i<=degree; i++) {
            poly[i]=xi;
            xi*=x;
        }
        return poly;
    }
    @Override
    protected boolean logY() {return false;}
}
