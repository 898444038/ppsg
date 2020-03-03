package com.ming.ppsg2.entity;

public class AppointExcludeGenerals {

    private Long id;
    private String name;
    //指定阵容中该武将的最大数量
    private Integer maxSize;
    //当前阵容中该武将的数量
    private Integer currentSize = 0;

    public AppointExcludeGenerals(){}

    public AppointExcludeGenerals(String name, Integer maxSize) {
        this.name = name;
        this.maxSize = maxSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public Integer getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(Integer currentSize) {
        this.currentSize = currentSize;
    }
}
