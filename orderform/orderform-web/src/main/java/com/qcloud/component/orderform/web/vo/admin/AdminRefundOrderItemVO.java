package com.qcloud.component.orderform.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminRefundOrderItemVO {

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

    // 退款总计单
    private long   refundId;

    private String name;

    private String image;

    private String specifications;

    public AdminRefundOrderItemVO() {

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

    public void setOrderItemId(long orderItemId) {

        this.orderItemId = orderItemId;
    }

    public long getOrderItemId() {

        return orderItemId;
    }
  
    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
    }

    public void setTime(Date time) {

        this.time = time;
    }

    public Date getTime() {

        return time;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public int getNumber() {

        return number;
    }

    public void setSum(double sum) {

        this.sum = sum;
    }

    public double getSum() {

        return sum;
    }

    public void setExplain(String explain) {

        this.explain = explain;
    }

    public String getExplain() {

        return explain;
    }

    public void setReason(String reason) {

        this.reason = reason;
    }

    public String getReason() {

        return reason;
    }

    public void setRefundId(long refundId) {

        this.refundId = refundId;
    }

    public long getRefundId() {

        return refundId;
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
