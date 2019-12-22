package com.ming.system.entity;

import java.util.List;

public class Menu {

    private Long id;

    private String menuName;

    private String menuIcon;

    private Long parentId;

    private Integer sort;

    private String url;

    private List<Event> eventList;

    public Menu() {
    }

    public Menu(Long id, String menuName, String menuIcon, Long parentId, String url) {
        this.id = id;
        this.menuName = menuName;
        this.menuIcon = menuIcon;
        this.parentId = parentId;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
}
