package com.qcloud.component.orderform.web.vo.merchant;

import java.util.ArrayList;
import java.util.List;

public class MerchantOrderVO {

    private Long              orderId;

    // 下单时间
    private String            orderDateStr;

    // 订单号
    private String            orderNumber;

    // 订单号
    private String            merchantOrderNumber;

    // 收货人
    private String            consignee;

    // 收货地址
    private String            address;

    // 收货电话
    private String            mobile;

    // 状态,待付款,已付款,待发货,发货中,已签收
    private String            stateStr;

    // 订单金额
    private double            sum;

    //
    private double            cash;

    // 差额
    private double            surplus;

    // 订单说明
    private String            explain;

    private List<OrderItemVO> orderItemList = new ArrayList<OrderItemVO>();

    public Long getOrderId() {

        return orderId;
    }

    public void setOrderId(Long orderId) {

        this.orderId = orderId;
    }

    public String getOrderDateStr() {

        return orderDateStr;
    }

    public void setOrderDateStr(String orderDateStr) {

        this.orderDateStr = orderDateStr;
    }

    public String getOrderNumber() {

        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {

        this.orderNumber = orderNumber;
    }

    public String getMerchantOrderNumber() {

        return merchantOrderNumber;
    }

    public void setMerchantOrderNumber(String merchantOrderNumber) {

        this.merchantOrderNumber = merchantOrderNumber;
    }

    public String getConsignee() {

        return consignee;
    }

    public void setConsignee(String consignee) {

        this.consignee = consignee;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getMobile() {

        return mobile;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }

    public String getStateStr() {

        return stateStr;
    }

    public void setStateStr(String stateStr) {

        this.stateStr = stateStr;
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

    public double getSurplus() {

        return surplus;
    }

    public void setSurplus(double surplus) {

        this.surplus = surplus;
    }

    public String getExplain() {

        return explain;
    }

    public void setExplain(String explain) {

        this.explain = explain;
    }

    public List<OrderItemVO> getOrderItemList() {

        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemVO> orderItemList) {

        this.orderItemList = orderItemList;
    }
}
