package com.ming.system.entity;

/**
 * Created by Administrator on 2019/12/26 0026.
 */
public class Permisson {

    private Long id;
    private String url;
    private String name;
    private String description;
    private Long pid;
    private String icon;

    public Permisson() {
    }

    public Permisson(Long id, String name,String icon,Long pid,String url) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.description = description;
        this.pid = pid;
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
