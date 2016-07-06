package com.qcloud.component.marketing.web.vo.admin;

import java.util.Date;

public class AdminSlideVO {

    // ID
    private long   id;

    // 点击地址
    private String clickUrl;

    // 图片
    private String image;

    private String imageUid;

    // 场景
    private int    sence;

    private String desc;

    private String senceStr;

    private Date   startDate;

    private Date   endDate;

    private int    enable;

    private String title;

    public AdminSlideVO() {

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

    public String getImageUid() {

        return imageUid;
    }

    public void setImageUid(String imageUid) {

        this.imageUid = imageUid;
    }

    public String getSenceStr() {

        return senceStr;
    }

    public void setSenceStr(String senceStr) {

        this.senceStr = senceStr;
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

    public int getEnable() {

        return enable;
    }

    public void setEnable(int enable) {

        this.enable = enable;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }
}
