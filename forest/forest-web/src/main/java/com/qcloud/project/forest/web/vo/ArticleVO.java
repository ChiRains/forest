package com.qcloud.project.forest.web.vo;

import java.util.List;

public class ArticleVO {

    // ID
    private long         id;

    // 标题
    private String       name;

    // 图标
    private List<String> iconList;

    // 关键词
    private String       keywords;

    // 标签
    private String       label;

    // 简介
    private String       brief;

    // 详情
    private String       detail;

    // 日期
    private String       date;

    // 排序号
    private int          sort;

    // 是否启用
    private int          enable;

    // 分类类型
    private long         classifyId;

    // 查看次数
    private int          viewCount;

    // 点赞次数
    private int          likeCount;

    // 评价次数
    private int          comment;

    // 上一条咨询id
    private long         previousId;

    // 下一条咨询id
    private long         nextId;

    private boolean      isPraise = false;

    public ArticleVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public List<String> getIconList() {

        return iconList;
    }

    public void setIconList(List<String> iconList) {

        this.iconList = iconList;
    }

    public void setKeywords(String keywords) {

        this.keywords = keywords;
    }

    public String getKeywords() {

        return keywords;
    }

    public void setLabel(String label) {

        this.label = label;
    }

    public String getLabel() {

        return label;
    }

    public void setBrief(String brief) {

        this.brief = brief;
    }

    public String getBrief() {

        return brief;
    }

    public void setDetail(String detail) {

        this.detail = detail;
    }

    public String getDetail() {

        return detail;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public String getDate() {

        return date;
    }

    public void setSort(int sort) {

        this.sort = sort;
    }

    public int getSort() {

        return sort;
    }

    public void setEnable(int enable) {

        this.enable = enable;
    }

    public int getEnable() {

        return enable;
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

    public int getComment() {

        return comment;
    }

    public void setComment(int comment) {

        this.comment = comment;
    }

    public long getPreviousId() {

        return previousId;
    }

    public void setPreviousId(long previousId) {

        this.previousId = previousId;
    }

    public long getNextId() {

        return nextId;
    }

    public void setNextId(long nextId) {

        this.nextId = nextId;
    }

    
    public boolean getIsPraise() {
    
        return isPraise;
    }

    
    public void setIsPraise(boolean isPraise) {
    
        this.isPraise = isPraise;
    }
}
