package com.qcloud.component.marketing.model;

import java.util.Date;
import java.math.BigDecimal;

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

    public Slide() {

    }

    public Slide(long id, String clickUrl, String image, int sence, int orderNum, String desc) {

        this.id = id;
        this.clickUrl = clickUrl;
        this.image = image;
        this.sence = sence;
        this.orderNum = orderNum;
        this.desc = desc;
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
}
