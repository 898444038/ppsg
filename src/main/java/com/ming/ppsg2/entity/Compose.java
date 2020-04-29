package com.ming.ppsg2.entity;

import java.util.List;

/**
 * Created by Administrator on 2020/4/29 0029.
 */
public class Compose {

    private String id;

    private boolean isGril;

    private List<Generals> list;

    public Compose(String id, boolean isGril, List<Generals> list) {
        this.id = id;
        this.isGril = isGril;
        this.list = list;
    }

    public Compose() {
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
