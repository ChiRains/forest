package com.qcloud.project.forest.web.vo.admin;

import java.util.Date;

public class AdminArticleVO {

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

    private String classifyName;

    private String uid;

    public String getUid() {

        return uid;
    }

    public void setUid(String uid) {

        this.uid = uid;
    }

    public String getClassifyName() {

        return classifyName;
    }

    public void setClassifyName(String classifyName) {

        this.classifyName = classifyName;
    }

    public AdminArticleVO() {

    }

    public AdminArticleVO(long id, String title, String image, String brief, String content, Date date, int enable, int isOffshelves, long classifyId, int viewCount, int likeCount, int evaluationCount) {

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

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getTitle() {

        return title;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getImage() {

        return image;
    }

    public void setBrief(String brief) {

        this.brief = brief;
    }

    public String getBrief() {

        return brief;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getContent() {

        return content;
    }

    public void setDate(Date date) {

        this.date = date;
    }

    public Date getDate() {

        return date;
    }

    public void setEnable(int enable) {

        this.enable = enable;
    }

    public int getEnable() {

        return enable;
    }

    public void setIsOffshelves(int isOffshelves) {

        this.isOffshelves = isOffshelves;
    }

    public int getIsOffshelves() {

        return isOffshelves;
    }

    public void setClassifyId(long classifyId) {

        this.classifyId = classifyId;
    }

    public long getClassifyId() {

        return classifyId;
    }

    public void setViewCount(int viewCount) {

        this.viewCount = viewCount;
    }

    public int getViewCount() {

        return viewCount;
    }

    public void setLikeCount(int likeCount) {

        this.likeCount = likeCount;
    }

    public int getLikeCount() {

        return likeCount;
    }

    public void setEvaluationCount(int evaluationCount) {

        this.evaluationCount = evaluationCount;
    }

    public int getEvaluationCount() {

        return evaluationCount;
    }
}
