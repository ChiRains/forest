package com.qcloud.project.forest.web.vo;

public class IntegralOrderVO {

    private long    orderId;

    private String  orderNumber;

    private double  cash;

    private int     integral;

    private long    unifiedMerchandiseId;

    private String  name;

    private String  image;

    private String  specifications;

    // 10待发货20待收货30已完成
    private int     state;

    private String  stateStr;

    private String  paymentModeStr;

    private String  orderDateStr;

    private int     paymentMode;

    private String  consignee;

    private String  address;

    private String  email;

    private String  mobile;

    // 0没催促1已催促
    private boolean canRemind;

    private int     merchandiseNumber = 1;

    public IntegralOrderVO() {

    }


    public void setOrderNumber(String orderNumber) {

        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {

        return orderNumber;
    }

    public void setCash(double cash) {

        this.cash = cash;
    }

    public double getCash() {

        return cash;
    }

    public void setIntegral(int integral) {

        this.integral = integral;
    }

    public int getIntegral() {

        return integral;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getImage() {

        return image;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public String getSpecifications() {

        return specifications;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
    }

    public void setPaymentMode(int paymentMode) {

        this.paymentMode = paymentMode;
    }

    public int getPaymentMode() {

        return paymentMode;
    }

    public void setConsignee(String consignee) {

        this.consignee = consignee;
    }

    public String getConsignee() {

        return consignee;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getAddress() {

        return address;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getEmail() {

        return email;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }

    public String getMobile() {

        return mobile;
    }

    public boolean isCanRemind() {

        return canRemind;
    }

    public void setCanRemind(boolean canRemind) {

        this.canRemind = canRemind;
    }

    public String getStateStr() {

        return stateStr;
    }

    public void setStateStr(String stateStr) {

        this.stateStr = stateStr;
    }

    public String getPaymentModeStr() {

        return paymentModeStr;
    }

    public void setPaymentModeStr(String paymentModeStr) {

        this.paymentModeStr = paymentModeStr;
    }

    public String getOrderDateStr() {

        return orderDateStr;
    }

    public void setOrderDateStr(String orderDateStr) {

        this.orderDateStr = orderDateStr;
    }

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public int getMerchandiseNumber() {

        return merchandiseNumber;
    }

    public void setMerchandiseNumber(int merchandiseNumber) {

        this.merchandiseNumber = merchandiseNumber;
    }


    
    public long getOrderId() {
    
        return orderId;
    }


    
    public void setOrderId(long orderId) {
    
        this.orderId = orderId;
    }
}
