package com.qcloud.project.forest.model;

import java.util.Date;

public class Article {

    private long   id;

    // 标题
    private String title;

    // 图片
    private String image;

    // 简介
    private String brief;

    // 内容
    private String content;

    private Date   date;

    // 是否启用（1，启用；0，不启用）
    private int    enable;

    // 是否下架（0，没下架；1，下架）
    private int    isOffshelves;

    // 类别Id
    private long   classifyId;

    // 浏览数
    private int    viewCount;

    // 点赞数
    private int    likeCount;

    // 评论数
    private int    evaluationCount;

    public Article() {

    }

    public Article(long id, String title, String image, String brief, String content, Date date, int enable, int isOffshelves, long classifyId, int viewCount, int likeCount, int evaluationCount) {

        super();
        this.id = id;
        this.title = title;
        this.image = image;
        this.brief = brief;
        this.content = content;
        this.date = date;
        this.enable = enable;
        this.isOffshelves = isOffshelves;
        this.classifyId = classifyId;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.evaluationCount = evaluationCount;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getBrief() {

        return brief;
    }

    public void setBrief(String brief) {

        this.brief = brief;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public Date getDate() {

        return date;
    }

    public void setDate(Date date) {

        this.date = date;
    }

    public int getEnable() {

        return enable;
    }

    public void setEnable(int enable) {

        this.enable = enable;
    }

    public int getIsOffshelves() {

        return isOffshelves;
    }

    public void setIsOffshelves(int isOffshelves) {

        this.isOffshelves = isOffshelves;
    }

    public long getClassifyId() {

        return classifyId;
    }

    public void setClassifyId(long classifyId) {

        this.classifyId = classifyId;
    }

    public int getViewCount() {

        return viewCount;
    }

    public void setViewCount(int viewCount) {

        this.viewCount = viewCount;
    }

    public int getLikeCount() {

        return likeCount;
    }

    public void setLikeCount(int likeCount) {

        this.likeCount = likeCount;
    }

    public int getEvaluationCount() {

        return evaluationCount;
    }

    public void setEvaluationCount(int evaluationCount) {

        this.evaluationCount = evaluationCount;
    }
}
