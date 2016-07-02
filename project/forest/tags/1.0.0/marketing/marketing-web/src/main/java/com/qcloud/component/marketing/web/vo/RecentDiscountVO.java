package com.qcloud.component.marketing.web.vo;

import java.util.Date;

public class RecentDiscountVO {

    private long   id;

    // private long merchantId;
    private String name;

    private String image;

    private Date   startDate;

    private Date   endDate;

    private int    enable;

    public RecentDiscountVO() {

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

    public void setImage(String image) {

        this.image = image;
    }

    public String getImage() {

        return image;
    }

    public void setStartDate(Date startDate) {

        this.startDate = startDate;
    }

    public Date getStartDate() {

        return startDate;
    }

    public void setEndDate(Date endDate) {

        this.endDate = endDate;
    }

    public Date getEndDate() {

        return endDate;
    }

    public void setEnable(int enable) {

        this.enable = enable;
    }

    public int getEnable() {

        return enable;
    }
}
