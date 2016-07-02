package com.qcloud.component.my.web.vo;

import java.util.ArrayList;
import java.util.List;

public class MyMerchantOrderItemVO {

    private long                merchantId;

    private String              merchantName;

    private int                 number;

    private double              sum;

    private double              cash;

    private List<MyOrderItemVO> orderItemList = new ArrayList<MyOrderItemVO>();

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

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public double getSum() {

        return sum;
    }

    public void setSum(double sum) {

        this.sum = sum;
    }

    public List<MyOrderItemVO> getOrderItemList() {

        return orderItemList;
    }

    public void setOrderItemList(List<MyOrderItemVO> orderItemList) {

        this.orderItemList = orderItemList;
    }

    public double getCash() {

        return cash;
    }

    public void setCash(double cash) {

        this.cash = cash;
    }
}
