package com.qcloud.component.marketing.web.vo;

public class SlideVO {

    // 点击地址
    private String clickUrl;

    // 图片
    private String image;

    private String desc;

    private String title;

    public SlideVO() {

    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
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

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }
}
