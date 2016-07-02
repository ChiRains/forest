package com.qcloud.component.orderform.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminReturnOrderVO {
	
  //ID
    private long id;        
    
    //总单ID
    private long orderId;       
    
    //下单时间
    private Date orderDate;     
    
    //订单号
    private String orderNumber;     
    
    //子单ID
    private long subOrderId;        
    
    //商家ID
    private long merchantId;        
    
    //门店ID
    private long storeId;       
    
    //退款金额
    private double sum;     
    
    //状态(1:已申请 2:通过 3:未通过 )
    private int state;      
    
    //用户id
    private long userId;        
    
    private Date time;      
    
    //退货单号
    private String returnNumber;        
    
    //说明
    private String explain;     
    
    //原因
    private String reason;      
    
    //物流公司
    private String logisticsCompany;        
    
    //物流查询号
    private String logisticsNumber;

    private String userName;
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

    
    public Date getOrderDate() {
    
        return orderDate;
    }

    
    public void setOrderDate(Date orderDate) {
    
        this.orderDate = orderDate;
    }

    
    public String getOrderNumber() {
    
        return orderNumber;
    }

    
    public void setOrderNumber(String orderNumber) {
    
        this.orderNumber = orderNumber;
    }

    
    public long getSubOrderId() {
    
        return subOrderId;
    }

    
    public void setSubOrderId(long subOrderId) {
    
        this.subOrderId = subOrderId;
    }

    
    public long getMerchantId() {
    
        return merchantId;
    }

    
    public void setMerchantId(long merchantId) {
    
        this.merchantId = merchantId;
    }

    
    public long getStoreId() {
    
        return storeId;
    }

    
    public void setStoreId(long storeId) {
    
        this.storeId = storeId;
    }

    
    public double getSum() {
    
        return sum;
    }

    
    public void setSum(double sum) {
    
        this.sum = sum;
    }

    
    public int getState() {
    
        return state;
    }

    
    public void setState(int state) {
    
        this.state = state;
    }

    
    public long getUserId() {
    
        return userId;
    }

    
    public void setUserId(long userId) {
    
        this.userId = userId;
    }

    
    public Date getTime() {
    
        return time;
    }

    
    public void setTime(Date time) {
    
        this.time = time;
    }

    
    public String getReturnNumber() {
    
        return returnNumber;
    }

    
    public void setReturnNumber(String returnNumber) {
    
        this.returnNumber = returnNumber;
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

    
    public String getLogisticsCompany() {
    
        return logisticsCompany;
    }

    
    public void setLogisticsCompany(String logisticsCompany) {
    
        this.logisticsCompany = logisticsCompany;
    }

    
    public String getLogisticsNumber() {
    
        return logisticsNumber;
    }

    
    public void setLogisticsNumber(String logisticsNumber) {
    
        this.logisticsNumber = logisticsNumber;
    }


    
    public String getUserName() {
    
        return userName;
    }


    
    public void setUserName(String userName) {
    
        this.userName = userName;
    } 	

		
}
