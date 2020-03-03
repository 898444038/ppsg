package com.ming.ppsg2.entity;

/**
 * 指定武将
 */
public class AppointGenerals {

    private Long id;
    private String name;

    public AppointGenerals() {
    }

    public AppointGenerals(String name) {
        this.name = name;
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
}
