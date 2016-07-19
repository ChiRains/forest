package com.qcloud.project.forest.web.vo;

public class IntegralOrderListVO {

    private long    orderId;

    private String  orderNumber;

    private int     state;

    private String  stateStr;

    private String  name;

    private String  image;

    // 现金价
    private double  cash;

    // 积分价
    private int     integral;

    private String  specifications;

    private int     merchandiseNumber = 1;

    // 0没催促1已催促
    private boolean canRemind;

    public long getOrderId() {

        return orderId;
    }

    public void setOrderId(long orderId) {

        this.orderId = orderId;
    }

    public String getOrderNumber() {

        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {

        this.orderNumber = orderNumber;
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

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public double getCash() {

        return cash;
    }

    public void setCash(double cash) {

        this.cash = cash;
    }

    public int getIntegral() {

        return integral;
    }

    public void setIntegral(int integral) {

        this.integral = integral;
    }

    public String getSpecifications() {

        return specifications;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public int getMerchandiseNumber() {

        return merchandiseNumber;
    }

    public void setMerchandiseNumber(int merchandiseNumber) {

        this.merchandiseNumber = merchandiseNumber;
    }

    public boolean isCanRemind() {

        return canRemind;
    }

    public void setCanRemind(boolean canRemind) {

        this.canRemind = canRemind;
    }
}
