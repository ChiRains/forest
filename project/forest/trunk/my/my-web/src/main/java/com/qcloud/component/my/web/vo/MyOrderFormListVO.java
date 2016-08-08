package com.qcloud.component.my.web.vo;

import java.util.ArrayList;
import java.util.List;

public class MyOrderFormListVO {

    // 总单
    private long         orderId;

    // 子订单ID
    private long         subOrderId;

    // 下单时间
    private String       timeStr;

    private String       orderDateStr;

    // 状态,待付款,已付款,待发货,发货中,已签收
    private int          state;

    // 状态
    private String       stateStr;

    private List<String> image  = new ArrayList<String>();

    private String       orderNumber;

    private double       sum;

    private boolean      evaluation;

    private boolean      afterSale;

    private boolean      addEvaluation;

    // 是否提醒过,true 已经提醒过,现在不可以提醒
    private boolean      remind = false;

    private double       cash;

    private int          merchandiseNumber;

    public long getOrderId() {

        return orderId;
    }

    public void setOrderId(long orderId) {

        this.orderId = orderId;
    }

    public long getSubOrderId() {

        return subOrderId;
    }

    public void setSubOrderId(long subOrderId) {

        this.subOrderId = subOrderId;
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

    public String getStateStr() {

        return stateStr;
    }

    public void setStateStr(String stateStr) {

        this.stateStr = stateStr;
    }

    public List<String> getImage() {

        return image;
    }

    public void setImage(List<String> image) {

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

    public boolean isRemind() {

        return remind;
    }

    public void setRemind(boolean remind) {

        this.remind = remind;
    }

    public String getOrderDateStr() {

        return orderDateStr;
    }

    public void setOrderDateStr(String orderDateStr) {

        this.orderDateStr = orderDateStr;
    }

    public double getCash() {

        return cash;
    }

    public void setCash(double cash) {

        this.cash = cash;
    }

    public int getMerchandiseNumber() {

        return merchandiseNumber;
    }

    public void setMerchandiseNumber(int merchandiseNumber) {

        this.merchandiseNumber = merchandiseNumber;
    }

    public boolean isAddEvaluation() {

        return addEvaluation;
    }

    public void setAddEvaluation(boolean addEvaluation) {

        this.addEvaluation = addEvaluation;
    }
}
