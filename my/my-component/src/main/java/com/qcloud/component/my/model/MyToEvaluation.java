package com.qcloud.component.my.model;

import java.util.Date;
import java.math.BigDecimal;

public class MyToEvaluation {
	
	private long id;		
	
	//购买用户
	private long userId;		
	
	//统一商品ID
	private long unifiedMerchandiseId;		
	
	//商品id
	private long merchandiseId;		
	
	//商品名称
	private String name;		
	
	//缩略图
	private String image;		
	
	//折扣价,成交价
	private double discount;		
	
	//商家ID
	private long merchantId;		
	
	//订单ID
	private long orderId;		
	
	//订单ID
	private long subOrderId;		
	
	//订单ID
	private long orderItemId;		
	
	//订单时间
	private Date orderDate;		
	
	//订单号
	private String orderNumber;		
	
	//签收时间
	private Date signDate;		
	
	private long orderItemDetailId;		

	public MyToEvaluation(){
	
	}

	public MyToEvaluation(long id,long userId,long unifiedMerchandiseId,long merchandiseId,String name,String image,double discount,long merchantId,long orderId,long subOrderId,long orderItemId,Date orderDate,String orderNumber,Date signDate,long orderItemDetailId){
		this.id = id;		
		this.userId = userId;		
		this.unifiedMerchandiseId = unifiedMerchandiseId;		
		this.merchandiseId = merchandiseId;		
		this.name = name;		
		this.image = image;		
		this.discount = discount;		
		this.merchantId = merchantId;		
		this.orderId = orderId;		
		this.subOrderId = subOrderId;		
		this.orderItemId = orderItemId;		
		this.orderDate = orderDate;		
		this.orderNumber = orderNumber;		
		this.signDate = signDate;		
		this.orderItemDetailId = orderItemDetailId;		
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
		
	public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {
		this.unifiedMerchandiseId = unifiedMerchandiseId;
	}

	public long getUnifiedMerchandiseId() {
		return unifiedMerchandiseId;
	}	
		
	public void setMerchandiseId(long merchandiseId) {
		this.merchandiseId = merchandiseId;
	}

	public long getMerchandiseId() {
		return merchandiseId;
	}	
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
		
	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}	
		
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getDiscount() {
		return discount;
	}	
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
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
		
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public Date getSignDate() {
		return signDate;
	}	
		
	public void setOrderItemDetailId(long orderItemDetailId) {
		this.orderItemDetailId = orderItemDetailId;
	}

	public long getOrderItemDetailId() {
		return orderItemDetailId;
	}	
		
}
