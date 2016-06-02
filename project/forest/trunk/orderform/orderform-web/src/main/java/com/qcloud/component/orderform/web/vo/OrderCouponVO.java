package com.qcloud.component.orderform.web.vo;

import java.util.Date;

public class OrderCouponVO {

    // ID
    private long   id;

    // 有效时间
    private Date   validDate;

    // 优惠券名称
    private String name;

    // 面额
    private double price;

    private double limitPrice;

    private Long   merchantId;

    private String code;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public Date getValidDate() {

        return validDate;
    }

    public void setValidDate(Date validDate) {

        this.validDate = validDate;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public double getLimitPrice() {

        return limitPrice;
    }

    public void setLimitPrice(double limitPrice) {

        this.limitPrice = limitPrice;
    }

    public Long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(Long merchantId) {

        this.merchantId = merchantId;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }
}
