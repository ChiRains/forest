package com.qcloud.component.marketing.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMerchandiseMarketingVO {

    // ID
    private long   id;

    private String name;
    
    private String sysCode;

    // 单一商品ID
    private long   merchandiseItemId;

    // 统一商品ID
    private long   unifiedMerchandiseId;

    // 进货价
    private double purchase;

    // 折扣价
    private double discount;

    // 原价
    private double price;

    // 库存
    private int    stock;

    // 场景
    private int    sence;

    // 更新时间
    private Date   updateTime;

    private int    order;

    private int    currencyType;

    private Long   activityId;

    public AdminMerchandiseMarketingVO(long id, String name, long merchandiseItemId, long unifiedMerchandiseId, double purchase, double discount, double price, int stock, int sence, Date updateTime, int order, int currencyType, Long activityId) {

        super();
        this.id = id;
        this.name = name;
        this.merchandiseItemId = merchandiseItemId;
        this.unifiedMerchandiseId = unifiedMerchandiseId;
        this.purchase = purchase;
        this.discount = discount;
        this.price = price;
        this.stock = stock;
        this.sence = sence;
        this.updateTime = updateTime;
        this.order = order;
        this.currencyType = currencyType;
        this.activityId = activityId;
    }

    public AdminMerchandiseMarketingVO() {

    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getMerchandiseItemId() {

        return merchandiseItemId;
    }

    public void setMerchandiseItemId(long merchandiseItemId) {

        this.merchandiseItemId = merchandiseItemId;
    }

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public double getPurchase() {

        return purchase;
    }

    public void setPurchase(double purchase) {

        this.purchase = purchase;
    }

    public double getDiscount() {

        return discount;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public int getStock() {

        return stock;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public int getSence() {

        return sence;
    }

    public void setSence(int sence) {

        this.sence = sence;
    }

    public Date getUpdateTime() {

        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {

        this.updateTime = updateTime;
    }

    public int getOrder() {

        return order;
    }

    public void setOrder(int order) {

        this.order = order;
    }

    public int getCurrencyType() {

        return currencyType;
    }

    public void setCurrencyType(int currencyType) {

        this.currencyType = currencyType;
    }

    public Long getActivityId() {

        return activityId;
    }

    public void setActivityId(Long activityId) {

        this.activityId = activityId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    
    public String getSysCode() {
    
        return sysCode;
    }

    
    public void setSysCode(String sysCode) {
    
        this.sysCode = sysCode;
    }
}
