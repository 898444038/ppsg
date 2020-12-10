package com.ming.ppsg2.entity;

import java.util.List;

/**
 * Created by Administrator on 2020/4/29 0029.
 */
public class Compose {

    private String id;

    private Integer sort;

    private boolean isGril;

    private List<Generals> list;

    public Compose(Integer sort,String id, boolean isGril, List<Generals> list) {
        this.sort = sort;
        this.id = id;
        this.isGril = isGril;
        this.list = list;
    }

    public Compose() {
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isGril() {
        return isGril;
    }

    public void setGril(boolean gril) {
        isGril = gril;
    }

    public List<Generals> getList() {
        return list;
    }

    public void setList(List<Generals> list) {
        this.list = list;
    }
}
