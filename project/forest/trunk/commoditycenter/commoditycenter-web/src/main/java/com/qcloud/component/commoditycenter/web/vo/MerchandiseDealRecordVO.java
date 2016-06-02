package com.qcloud.component.commoditycenter.web.vo;


public class MerchandiseDealRecordVO {

    // 商品档案ID
    private long   merchandiseId;

    // 买家ID
    private String userName;

    private String gradeName;

    // 数量
    private int    number;

    // 规格说明
    private String specifications;

    // 下单时间
    private String timeStr;

    public MerchandiseDealRecordVO() {

    }

    public void setMerchandiseId(long merchandiseId) {

        this.merchandiseId = merchandiseId;
    }

    public long getMerchandiseId() {

        return merchandiseId;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public int getNumber() {

        return number;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public String getSpecifications() {

        return specifications;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getGradeName() {

        return gradeName;
    }

    public void setGradeName(String gradeName) {

        this.gradeName = gradeName;
    }

    public String getTimeStr() {

        return timeStr;
    }

    public void setTimeStr(String timeStr) {

        this.timeStr = timeStr;
    }
}
