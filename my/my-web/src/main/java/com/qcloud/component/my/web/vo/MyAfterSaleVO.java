package com.qcloud.component.my.web.vo;

import java.util.List;

public class MyAfterSaleVO {

    // 类型
    private int                            type;

    private int                            returnType;

    // 售后单号
    private long                           afterSaleId;

    // 商家ID
    private long                           merchantId;

    // 商家名称
    private String                         merchantName;

    // 商家名称
    private String                         merchantImage;

    // 状态
    private int                            state;

    // 状态描述
    private String                         stateStr;

    // 一共几件商品
    private int                            number;

    //
    private double                         cash;

    private String                         afterSaleOrderNumber;

    private List<MyAfterSaleMerchandiseVO> list;

    public MyAfterSaleVO() {

    }

    public void setType(int type) {

        this.type = type;
    }

    public int getType() {

        return type;
    }

    public void setAfterSaleId(long afterSaleId) {

        this.afterSaleId = afterSaleId;
    }

    public long getAfterSaleId() {

        return afterSaleId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public String getMerchantName() {

        return merchantName;
    }

    public void setMerchantName(String merchantName) {

        this.merchantName = merchantName;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public String getStateStr() {

        return stateStr;
    }

    public void setStateStr(String stateStr) {

        this.stateStr = stateStr;
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

    public List<MyAfterSaleMerchandiseVO> getList() {

        return list;
    }

    public void setList(List<MyAfterSaleMerchandiseVO> list) {

        this.list = list;
    }

    public String getAfterSaleOrderNumber() {

        return afterSaleOrderNumber;
    }

    public void setAfterSaleOrderNumber(String afterSaleOrderNumber) {

        this.afterSaleOrderNumber = afterSaleOrderNumber;
    }

    public String getMerchantImage() {

        return merchantImage;
    }

    public void setMerchantImage(String merchantImage) {

        this.merchantImage = merchantImage;
    }

    public int getReturnType() {

        return returnType;
    }

    public void setReturnType(int returnType) {

        this.returnType = returnType;
    }
}
