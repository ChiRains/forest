package com.qcloud.component.orderform.model;

import java.util.Date;
import java.math.BigDecimal;

public class MembershipCardOrder {
	
	//ID
	private long id;		
	
	//订单号
	private String orderNumber;		
	
	//用户ID
	private long userId;		
	
	//商家ID
	private long merchantId;		
	
	//门店
	private long storeId;		
	
	//扫卡时间
	private Date time;		
	
	//订单金额
	private double sum;		
	
	//现金
	private double cash;		

	public MembershipCardOrder(){
	
	}

	public MembershipCardOrder(long id,String orderNumber,long userId,long merchantId,long storeId,Date time,double sum,double cash){
		this.id = id;		
		this.orderNumber = orderNumber;		
		this.userId = userId;		
		this.merchantId = merchantId;		
		this.storeId = storeId;		
		this.time = time;		
		this.sum = sum;		
		this.cash = cash;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
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
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getSum() {
		return sum;
	}	
		
	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getCash() {
		return cash;
	}	
		
}
