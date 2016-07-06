package com.qcloud.component.marketing.model;

import java.util.Date;

public class Slide {

    // ID
    private long   id;

    // 点击地址
    private String clickUrl;

    // 图片
    private String image;

    // 场景
    private int    sence;

    // 排序
    private int    orderNum;

    private String desc;

    private Date   startDate;

    private Date   endDate;

    private int    enable;

    private String title;

    public Slide() {

    }

    public Slide(long id, String clickUrl, String image, int sence, int orderNum, String desc, Date startDate, Date endDate, int enable, String title) {

        super();
        this.id = id;
        this.clickUrl = clickUrl;
        this.image = image;
        this.sence = sence;
        this.orderNum = orderNum;
        this.desc = desc;
        this.startDate = startDate;
        this.endDate = endDate;
        this.enable = enable;
        this.title = title;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setClickUrl(String clickUrl) {

        this.clickUrl = clickUrl;
    }

    public String getClickUrl() {

        return clickUrl;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getImage() {

        return image;
    }

    public void setSence(int sence) {

        this.sence = sence;
    }

    public int getSence() {

        return sence;
    }

    public void setOrderNum(int orderNum) {

        this.orderNum = orderNum;
    }

    public int getOrderNum() {

        return orderNum;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public Date getStartDate() {

        return startDate;
    }

    public void setStartDate(Date startDate) {

        this.startDate = startDate;
    }

    public Date getEndDate() {

        return endDate;
    }

    public void setEndDate(Date endDate) {

        this.endDate = endDate;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public int getEnable() {

        return enable;
    }

    public void setEnable(int enable) {

        this.enable = enable;
    }
}
