package com.ming.ppsg2.utils.zt;

public class Crusade {

    private Double second;

    private String formatSecond;

    private Double morales;

    public Crusade() {
    }

    public Crusade(Double second, String formatSecond) {
        this.second = second;
        this.formatSecond = formatSecond;
    }

    public Double getMorales() {
        return morales;
    }

    public void setMorales(Double morales) {
        this.morales = morales;
    }

    public Double getSecond() {
        return second;
    }

    public void setSecond(Double second) {
        this.second = second;
    }

    public String getFormatSecond() {
        return formatSecond;
    }

    public void setFormatSecond(String formatSecond) {
        this.formatSecond = formatSecond;
    }
}
