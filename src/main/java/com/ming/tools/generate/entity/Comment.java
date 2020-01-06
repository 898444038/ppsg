package com.ming.tools.generate.entity;

import java.util.Date;

/**
 * Created by Administrator on 2019/9/5 0005.
 */
public class Comment {
    private String author;
    private String desc;
    private Date createTime;

    public Comment() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreateTime() {
        this.createTime = new Date();
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
