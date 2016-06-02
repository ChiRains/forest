package com.qcloud.component.marketing.web.vo;

import java.util.Date;

public class CouponVO {

    private long   id;

    // 开始时间
    private String startDateStr;

    // 结束时间
    private String endDateStr;

    // 有效时间
    private String validDateStr;

    // 描述
    private String description;

    private String image;

    public CouponVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getDescription() {

        return description;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getStartDateStr() {

        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {

        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {

        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {

        this.endDateStr = endDateStr;
    }

    public String getValidDateStr() {

        return validDateStr;
    }

    public void setValidDateStr(String validDateStr) {

        this.validDateStr = validDateStr;
    }
}
