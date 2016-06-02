package com.qcloud.component.orderform.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminRefundOrderVO {
	
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
	
	private String userName;
	
	private Date time;		
	
	//退款单号
	private String refundNumber;		
	
	//说明
	private String explain;		
	
	//原因
	private String reason;		

	public AdminRefundOrderVO(){
	
	}

	public AdminRefundOrderVO(long id,long orderId,Date orderDate,String orderNumber,long subOrderId,long merchantId,long storeId,double sum,int state,long userId,Date time,String refundNumber,String explain,String reason){
		this.id = id;		
		this.orderId = orderId;		
		this.orderDate = orderDate;		
		this.orderNumber = orderNumber;		
		this.subOrderId = subOrderId;		
		this.merchantId = merchantId;		
		this.storeId = storeId;		
		this.sum = sum;		
		this.state = state;		
		this.userId = userId;		
		this.time = time;		
		this.refundNumber = refundNumber;		
		this.explain = explain;		
		this.reason = reason;		
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
		
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}	
		
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}	
		
	public void setSubOrderId(long subOrderId) {
		this.subOrderId = subOrderId;
	}

	public long getSubOrderId() {
		return subOrderId;
	}	
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
	}	
		
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
	}	
		
	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getSum() {
		return sum;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
	public void setRefundNumber(String refundNumber) {
		this.refundNumber = refundNumber;
	}

	public String getRefundNumber() {
		return refundNumber;
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

    
    public String getUserName() {
    
        return userName;
    }

    
    public void setUserName(String userName) {
    
        this.userName = userName;
    }	
		
}
