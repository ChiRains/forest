package com.qcloud.component.orderform.web.vo.personal;

import java.util.Date;

public abstract class AbstractOrderVO {

    // ID
    private long            id;

    // 子订单ID
    private long            subOrderId;

    // 订单号
    private String          orderNumber;

    // 下单时间
    private Date            orderDate;

    //
    private int             personalOrderState;

    // 下单时间
    private String          orderDateStr;

    // 订单金额
    private double          sum;

    // 数量
    private int             number;

    // 收货人
    private String          consignee;

    // 收货地址
    private String          address;

    // 收货电话
    private String          mobile;

    private int             state;

    // 状态,待付款,已付款,待发货,发货中,已签收
    private String          stateStr;

    // 差额
    private double          surplus;

    //
    private double          cash;

    // 邮费
    private double          postage;

    // 积分
    private double          integral;

    // 兑兑券:消费币
    private double          consumption;

    //
    private double          coupon;

    // 能否售后
    private boolean         afterSale;

    // 能否评论
    private boolean         evaluation;

    //
    private int             paymentMode;

    // 发票
    private String          invoice;

    //
    private OrderDeliveryVO orderDeliveryVO;

    //
    private String          expressName;

    //
    private String          expressNumber;

    private String          changeStateTime;

    // //
    // private List<AfterSaleVO> afterSaleList = new ArrayList<AfterSaleVO>();
    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getOrderNumber() {

        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {

        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {

        return orderDate;
    }

    public void setOrderDate(Date orderDate) {

        this.orderDate = orderDate;
    }

    public String getOrderDateStr() {

        return orderDateStr;
    }

    public void setOrderDateStr(String orderDateStr) {

        this.orderDateStr = orderDateStr;
    }

    public double getSum() {

        return sum;
    }

    public void setSum(double sum) {

        this.sum = sum;
    }

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
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

    public double getSurplus() {

        return surplus;
    }

    public void setSurplus(double surplus) {

        this.surplus = surplus;
    }

    // public List<AfterSaleVO> getAfterSaleList() {
    //
    // return afterSaleList;
    // }
    //
    // public void setAfterSaleList(List<AfterSaleVO> afterSaleList) {
    //
    // this.afterSaleList = afterSaleList;
    // }
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

    public boolean isAfterSale() {

        return afterSale;
    }

    public void setAfterSale(boolean afterSale) {

        this.afterSale = afterSale;
    }

    public boolean isEvaluation() {

        return evaluation;
    }

    public void setEvaluation(boolean evaluation) {

        this.evaluation = evaluation;
    }

    public double getCoupon() {

        return coupon;
    }

    public void setCoupon(double coupon) {

        this.coupon = coupon;
    }

    public int getPaymentMode() {

        return paymentMode;
    }

    public void setPaymentMode(int paymentMode) {

        this.paymentMode = paymentMode;
    }

    public OrderDeliveryVO getOrderDeliveryVO() {

        return orderDeliveryVO;
    }

    public void setOrderDeliveryVO(OrderDeliveryVO orderDeliveryVO) {

        this.orderDeliveryVO = orderDeliveryVO;
    }

    public int getPersonalOrderState() {

        return personalOrderState;
    }

    public void setPersonalOrderState(int personalOrderState) {

        this.personalOrderState = personalOrderState;
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

    public String getInvoice() {

        return invoice;
    }

    public void setInvoice(String invoice) {

        this.invoice = invoice;
    }

    public long getSubOrderId() {

        return subOrderId;
    }

    public void setSubOrderId(long subOrderId) {

        this.subOrderId = subOrderId;
    }

    public String getExpressName() {

        return expressName;
    }

    public void setExpressName(String expressName) {

        this.expressName = expressName;
    }

    public String getExpressNumber() {

        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {

        this.expressNumber = expressNumber;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public String getChangeStateTime() {

        return changeStateTime;
    }

    public void setChangeStateTime(String changeStateTime) {

        this.changeStateTime = changeStateTime;
    }
}
