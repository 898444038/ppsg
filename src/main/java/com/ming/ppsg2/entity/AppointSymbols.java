package com.ming.ppsg2.entity;

/**
 * 指定兵符
 */
public class AppointSymbols {

    private Long id;
    private Integer code;
    private String name;

    public AppointSymbols() {
    }

    public AppointSymbols(String name) {
        this.name = name;
    }

    public AppointSymbols(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
