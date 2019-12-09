package com.ming.system.utils;

/**
 * Created by Administrator on 2019/11/6 0006.
 */
public enum Operate {

    NONE(-1,"NONE",""),
    SELECT(0,"SELECT","查询"),
    INSERT(1,"INSERT","新增"),
    UPDATE(2,"UPDATE","更新"),
    DELETE(3,"DELETE","删除");
    private int code;
    private String name;
    private String desc;
    Operate(int code,String name ,String desc){
        this.code = code;
        this.name = name;
        this.desc = desc;
    }
    public int getCode() {return code;}
    public String getName() {return name;}
    public String getDesc() {return desc;}

}
