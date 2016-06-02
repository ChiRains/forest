package com.qcloud.component.my.model;

import java.util.Date;
import java.math.BigDecimal;

public class MyCoupon {
	
	//ID
	private long id;		
	
	//用户
	private long userId;		
	
	//优惠劵活动
	private long couponId;		
	
	//优惠劵项
	private long couponItemId;		
	
	//领取时间
	private Date extractDate;		
	
	//有效时间
	private Date validDate;		
	
	//优惠券名称
	private String name;		
	
	//面额
	private double price;		
	
	private double limitPrice;		
	
	//状态 1未使用 2已使用
	private int state;		
	
	//使用订单
	private long orderId;		
	
	//下订单时间
	private Date orderDate;		
	
	private long merchantId;		
	
	private String code;		

	public MyCoupon(){
	
	}

	public MyCoupon(long id,long userId,long couponId,long couponItemId,Date extractDate,Date validDate,String name,double price,double limitPrice,int state,long orderId,Date orderDate,long merchantId,String code){
		this.id = id;		
		this.userId = userId;		
		this.couponId = couponId;		
		this.couponItemId = couponItemId;		
		this.extractDate = extractDate;		
		this.validDate = validDate;		
		this.name = name;		
		this.price = price;		
		this.limitPrice = limitPrice;		
		this.state = state;		
		this.orderId = orderId;		
		this.orderDate = orderDate;		
		this.merchantId = merchantId;		
		this.code = code;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public long getCouponId() {
		return couponId;
	}	
		
	public void setCouponItemId(long couponItemId) {
		this.couponItemId = couponItemId;
	}

	public long getCouponItemId() {
		return couponItemId;
	}	
		
	public void setExtractDate(Date extractDate) {
		this.extractDate = extractDate;
	}

	public Date getExtractDate() {
		return extractDate;
	}	
		
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Date getValidDate() {
		return validDate;
	}	
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
		
	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}	
		
	public void setLimitPrice(double limitPrice) {
		this.limitPrice = limitPrice;
	}

	public double getLimitPrice() {
		return limitPrice;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
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
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
	}	
		
	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}	
		
}
