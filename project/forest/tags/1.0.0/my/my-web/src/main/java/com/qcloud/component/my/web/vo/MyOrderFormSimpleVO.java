package com.qcloud.component.my.web.vo;

public class MyOrderFormSimpleVO {

    // 总单
    private long    orderId;

    // 子订单ID
    private long    subOrderId;

    // 下单时间
    private String  timeStr;

    // 状态,待付款,已付款,待发货,发货中,已签收
    private int     state;

    // 状态
    private String  stateStr;

    private String  image;

    private String  orderNumber;

    private double  sum;

    private double  cash;

    // 邮费
    private double  postage;

    // 积分
    private double  integral;

    // 兑兑券:消费币
    private double  consumption;

    // private String afterSaleStateName;
    // 节省多少钱
    private double  surplus;

    private boolean evaluation;

    private boolean afterSale;

    private int     paymentMode;

    private String  mobile;

    private String  consignee;

    // 是否提醒过,true 已经提醒过,现在不可以提醒
    private boolean remind = false;

    private String  deliveryTimeStr;

    public long getOrderId() {

        return orderId;
    }

    public void setOrderId(long orderId) {

        this.orderId = orderId;
    }

    public String getTimeStr() {

        return timeStr;
    }

    public void setTimeStr(String timeStr) {

        this.timeStr = timeStr;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getOrderNumber() {

        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {

        this.orderNumber = orderNumber;
    }

    public double getSum() {

        return sum;
    }

    public void setSum(double sum) {

        this.sum = sum;
    }

    public double getCash() {

        return cash;
    }

    public void setCash(double cash) {

        this.cash = cash;
    }

    // public String getAfterSaleStateName() {
    //
    // return afterSaleStateName;
    // }
    //
    // public void setAfterSaleStateName(String afterSaleStateName) {
    //
    // this.afterSaleStateName = afterSaleStateName;
    // }
    public double getSurplus() {

        return surplus;
    }

    public void setSurplus(double surplus) {

        this.surplus = surplus;
    }

    public boolean isEvaluation() {

        return evaluation;
    }

    public void setEvaluation(boolean evaluation) {

        this.evaluation = evaluation;
    }

    public boolean isAfterSale() {

        return afterSale;
    }

    public void setAfterSale(boolean afterSale) {

        this.afterSale = afterSale;
    }

    public double getPostage() {

        return postage;
    }

    public void setPostage(double postage) {

        this.postage = postage;
    }

    public String getStateStr() {

        return stateStr;
    }

    public void setStateStr(String stateStr) {

        this.stateStr = stateStr;
    }

    public int getPaymentMode() {

        return paymentMode;
    }

    public void setPaymentMode(int paymentMode) {

        this.paymentMode = paymentMode;
    }

    public double getIntegral() {

        return integral;
    }

    public void setIntegral(double integral) {

        this.integral = integral;
    }

    public double getConsumption() {

        return consumption;
    }

    public void setConsumption(double consumption) {

        this.consumption = consumption;
    }

    public long getSubOrderId() {

        return subOrderId;
    }

    public void setSubOrderId(long subOrderId) {

        this.subOrderId = subOrderId;
    }

    public String getMobile() {

        return mobile;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }

    public String getConsignee() {

        return consignee;
    }

    public void setConsignee(String consignee) {

        this.consignee = consignee;
    }

    public boolean isRemind() {

        return remind;
    }

    public void setRemind(boolean remind) {

        this.remind = remind;
    }

    public String getDeliveryTimeStr() {

        return deliveryTimeStr;
    }

    public void setDeliveryTimeStr(String deliveryTimeStr) {

        this.deliveryTimeStr = deliveryTimeStr;
    }
}
