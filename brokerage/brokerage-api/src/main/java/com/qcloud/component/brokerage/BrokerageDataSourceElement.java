package com.qcloud.component.brokerage;

import java.util.Date;

public class BrokerageDataSourceElement {

    private long   sourceDataId;

    private String title;

    private String image;

    private int    dateType;

    private double purchase;

    private double discount;

    private int    number;

    private double cash;

    private Date   orderTime;

    private long   userId;

    private long   merchantId;

    private long   formulaId;

    public long getSourceDataId() {

        return sourceDataId;
    }

    public void setSourceDataId(long sourceDataId) {

        this.sourceDataId = sourceDataId;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public int getDateType() {

        return dateType;
    }

    public void setDateType(int dateType) {

        this.dateType = dateType;
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

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public double getCash() {

        return cash;
    }

    public void setCash(double cash) {

        this.cash = cash;
    }

    public Date getOrderTime() {

        return orderTime;
    }

    public void setOrderTime(Date orderTime) {

        this.orderTime = orderTime;
    }

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getFormulaId() {

        return formulaId;
    }

    public void setFormulaId(long formulaId) {

        this.formulaId = formulaId;
    }
}
