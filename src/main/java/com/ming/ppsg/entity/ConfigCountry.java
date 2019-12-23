package com.ming.ppsg.entity;

/**
 * 国家配置
 */
public class ConfigCountry {

    private Long id;

    private String countryName;

    public ConfigCountry() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
