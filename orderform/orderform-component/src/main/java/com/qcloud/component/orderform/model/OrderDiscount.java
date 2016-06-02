package com.qcloud.component.orderform.model;

import java.util.Date;
import java.math.BigDecimal;

public class OrderDiscount {
	
	//ID
	private long id;		
	
	//总单ID
	private long orderId;		
	
	//子单ID
	private long subOrderId;		
	
	//下订单时间
	private Date orderDate;		
	
	//活动ID
	private long discountId;		
	
	//面额
	private double price;		
	
	//活动类型 1优惠劵 2...
	private int type;		

	public OrderDiscount(){
	
	}

	public OrderDiscount(long id,long orderId,long subOrderId,Date orderDate,long discountId,double price,int type){
		this.id = id;		
		this.orderId = orderId;		
		this.subOrderId = subOrderId;		
		this.orderDate = orderDate;		
		this.discountId = discountId;		
		this.price = price;		
		this.type = type;		
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
		
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}	
		
	public void setDiscountId(long discountId) {
		this.discountId = discountId;
	}

	public long getDiscountId() {
		return discountId;
	}	
		
	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}	
		
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}	
		
}
