package com.ming.ppsg2.entity;

public class CountryArms {

    private Integer count;

    private String name;

    public CountryArms() {}

    public CountryArms(Integer count, String name) {
        this.count = count;
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
