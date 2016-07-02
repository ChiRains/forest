package com.qcloud.component.sellercenter.web.vo;

public class MerchantOrderFormVO {

    // ID
    private long   id;

    // 总单
    private long   orderId;

    // 子单ID
    private long   subOrderId;

    private String stateStr;

    private String dateStr;

    private double sum;

    private String orderNumber;

    private String image;

    private String explain;

    public MerchantOrderFormVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setOrderId(long orderId) {

        this.orderId = orderId;
    }

    public long getOrderId() {

        return orderId;
    }

    public void setSubOrderId(long subOrderId) {

        this.subOrderId = subOrderId;
    }

    public long getSubOrderId() {

        return subOrderId;
    }

    public String getStateStr() {

        return stateStr;
    }

    public void setStateStr(String stateStr) {

        this.stateStr = stateStr;
    }

    public String getDateStr() {

        return dateStr;
    }

    public void setDateStr(String dateStr) {

        this.dateStr = dateStr;
    }

    public double getSum() {

        return sum;
    }

    public void setSum(double sum) {

        this.sum = sum;
    }

    public String getOrderNumber() {

        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {

        this.orderNumber = orderNumber;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getExplain() {

        return explain;
    }

    public void setExplain(String explain) {

        this.explain = explain;
    }
}
