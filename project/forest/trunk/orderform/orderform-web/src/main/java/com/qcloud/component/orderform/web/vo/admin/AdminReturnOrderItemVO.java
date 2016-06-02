package com.qcloud.component.orderform.web.vo.admin;

import java.util.Date;

public class AdminReturnOrderItemVO {

    // ID
    private long   id;

    // 总单ID
    private long   orderId;

    // 子单ID
    private long   subOrderId;

    // 订单项ID
    private long   orderItemId;

    // 状态(1:已申请 2:通过 3:未通过 )
    private int    state;

    // 申请退货时间
    private Date   time;

    // 数量
    private int    number;

    // 退款金额
    private double sum;

    // 说明
    private String explain;

    // 原因
    private String reason;

    // 退货总计单
    private long   returnId;

    // 商品信息
    private String name;

    private String image;

    private String specifications;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

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

    public long getOrderItemId() {

        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {

        this.orderItemId = orderItemId;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public Date getTime() {

        return time;
    }

    public void setTime(Date time) {

        this.time = time;
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

    public String getExplain() {

        return explain;
    }

    public void setExplain(String explain) {

        this.explain = explain;
    }

    public String getReason() {

        return reason;
    }

    public void setReason(String reason) {

        this.reason = reason;
    }

    public long getReturnId() {

        return returnId;
    }

    public void setReturnId(long returnId) {

        this.returnId = returnId;
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

    public String getSpecifications() {

        return specifications;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }
}
