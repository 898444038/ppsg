package com.ming.ppsg.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/10/31 0031.
 */
public class User {

    private Long id;
    private String name;

    //多角色
    private List<Role> roleList;

    public User() {
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
