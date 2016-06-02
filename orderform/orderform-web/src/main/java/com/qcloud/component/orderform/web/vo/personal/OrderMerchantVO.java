package com.qcloud.component.orderform.web.vo.personal;

import java.util.ArrayList;
import java.util.List;

public class OrderMerchantVO {

    private long              subOrderId;

    private long              merchantId;

    private String            merchantName;

    private String            merchantImage;

    private double            sum;

    private double            cash;

    private int               number;

    private double            integral;

    private double            consumption;

    private double            postage;

    private double            coupon;

    private String            explain;

    private int               personalOrderState;

    private String            stateStr;

    private OrderExpressVO    orderExpressVO;

    private List<OrderItemVO> orderItemList = new ArrayList<OrderItemVO>();

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

    public List<OrderItemVO> getOrderItemList() {

        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemVO> orderItemList) {

        this.orderItemList = orderItemList;
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

    public String getMerchantImage() {

        return merchantImage;
    }

    public void setMerchantImage(String merchantImage) {

        this.merchantImage = merchantImage;
    }

    public double getCash() {

        return cash;
    }

    public void setCash(double cash) {

        this.cash = cash;
    }

    public double getPostage() {

        return postage;
    }

    public void setPostage(double postage) {

        this.postage = postage;
    }

    public double getCoupon() {

        return coupon;
    }

    public void setCoupon(double coupon) {

        this.coupon = coupon;
    }

    public String getExplain() {

        return explain;
    }

    public void setExplain(String explain) {

        this.explain = explain;
    }

    public OrderExpressVO getOrderExpressVO() {

        return orderExpressVO;
    }

    public void setOrderExpressVO(OrderExpressVO orderExpressVO) {

        this.orderExpressVO = orderExpressVO;
    }

    public long getSubOrderId() {

        return subOrderId;
    }

    public void setSubOrderId(long subOrderId) {

        this.subOrderId = subOrderId;
    }

    public int getPersonalOrderState() {

        return personalOrderState;
    }

    public void setPersonalOrderState(int personalOrderState) {

        this.personalOrderState = personalOrderState;
    }

    public String getStateStr() {

        return stateStr;
    }

    public void setStateStr(String stateStr) {

        this.stateStr = stateStr;
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
}
