package com.qcloud.component.commoditycenter.model;

import java.util.Date;
import java.math.BigDecimal;

public class MerchandiseMarketing {

    // ID
    private long   id;

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

    private int    enable;
    
    //商品名称
    private String name;
    
    //系统编号
    private String sysCode;

    public MerchandiseMarketing() {

    }

    public MerchandiseMarketing(long id, long merchandiseItemId, long unifiedMerchandiseId, double purchase, double discount, double price, int stock, int sence, Date updateTime, int order, int currencyType, Long activityId, int enable, String name, String sysCode) {
        this.id = id;
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
        this.enable = enable;
        this.name = name;
        this.sysCode = sysCode;
    }


    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setMerchandiseItemId(long merchandiseItemId) {

        this.merchandiseItemId = merchandiseItemId;
    }

    public long getMerchandiseItemId() {

        return merchandiseItemId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setPurchase(double purchase) {

        this.purchase = purchase;
    }

    public double getPurchase() {

        return purchase;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    public double getDiscount() {

        return discount;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public double getPrice() {

        return price;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public int getStock() {

        return stock;
    }

    public void setSence(int sence) {

        this.sence = sence;
    }

    public int getSence() {

        return sence;
    }

    public void setUpdateTime(Date updateTime) {

        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {

        return updateTime;
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

    public int getEnable() {

        return enable;
    }

    public void setEnable(int enable) {

        this.enable = enable;
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
