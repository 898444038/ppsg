package com.ming.ppsg.entity;

import java.util.Date;
import java.util.List;

public class DiscussPost {

    private Long id;
    private Long userId;
    private String headPortrait;
    private String title;
    private String content;
    private String images;//多图片
    private Date createTime;//发帖时间
    private Date lastAnswerTime;//最后一次回复时间
    private Integer fabulousCount;//点赞次数
    private Integer answerCount;//回复次数
    private Integer lookCount;//查看次数
    private Integer status;//0:讨论中 1：已结束
    private Integer delFlag;//0:未删除 1：已删除
    private List<DiscussPostTags> tagsList;
    private List<String> imageUrlList;

    public DiscussPost() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<DiscussPostTags> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<DiscussPostTags> tagsList) {
        this.tagsList = tagsList;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Date getLastAnswerTime() {
        return lastAnswerTime;
    }

    public void setLastAnswerTime(Date lastAnswerTime) {
        this.lastAnswerTime = lastAnswerTime;
    }

    public Integer getFabulousCount() {
        return fabulousCount;
    }

    public void setFabulousCount(Integer fabulousCount) {
        this.fabulousCount = fabulousCount;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public Integer getLookCount() {
        return lookCount;
    }

    public void setLookCount(Integer lookCount) {
        this.lookCount = lookCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public List<String> getImageUrlList() {
        return imageUrlList;
    }

    public void setImageUrlList(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }
}
