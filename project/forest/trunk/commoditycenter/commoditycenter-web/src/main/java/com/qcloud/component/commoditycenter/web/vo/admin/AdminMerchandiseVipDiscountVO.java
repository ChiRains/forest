package com.qcloud.component.commoditycenter.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMerchandiseVipDiscountVO {

    // ID
    private long   id;

    // 用户ID
    private long   userId;

    // 单一商品ID
    private long   merchandiseItemId;

    // vip折扣
    private double discount;

    // vip价
    private double price;

    // 结算方式： 1按价格 2按折扣
    private int    paymentType;

    /**
     * 客户名
     */
    private String companyName;

    /**
     * 商品名
     */
    private String merchandiseItemName;

    private double marketDiscount;

    public AdminMerchandiseVipDiscountVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public long getUserId() {

        return userId;
    }

    public void setMerchandiseItemId(long merchandiseItemId) {

        this.merchandiseItemId = merchandiseItemId;
    }

    public long getMerchandiseItemId() {

        return merchandiseItemId;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    public double getDiscount() {

        return discount;
    }

    public String getCompanyName() {

        return companyName;
    }

    public void setCompanyName(String companyName) {

        this.companyName = companyName;
    }

    public String getMerchandiseItemName() {

        return merchandiseItemName;
    }

    public void setMerchandiseItemName(String merchandiseItemName) {

        this.merchandiseItemName = merchandiseItemName;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public int getPaymentType() {

        return paymentType;
    }

    public void setPaymentType(int paymentType) {

        this.paymentType = paymentType;
    }

    public double getMarketDiscount() {

        return marketDiscount;
    }

    public void setMarketDiscount(double marketDiscount) {

        this.marketDiscount = marketDiscount;
    }
}
