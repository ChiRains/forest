package com.qcloud.component.goods.model;

import java.util.Date;
import java.math.BigDecimal;

public class MerchandiseDealRecord {
	
	//ID
	private long id;		
	
	//商品档案ID
	private long merchandiseId;		
	
	//买家ID
	private long userId;		
	
	//数量
	private int number;		
	
	//规格说明
	private String specifications;		
	
	//下单时间
	private Date time;		
	
	//总单ID
	private long orderId;		
	
	//子单ID
	private long subOrderId;		
	
	//订单项ID
	private long orderItemId;		
	
	//订单项ID
	private long orderItemDetailId;		

	public MerchandiseDealRecord(){
	
	}

	public MerchandiseDealRecord(long id,long merchandiseId,long userId,int number,String specifications,Date time,long orderId,long subOrderId,long orderItemId,long orderItemDetailId){
		this.id = id;		
		this.merchandiseId = merchandiseId;		
		this.userId = userId;		
		this.number = number;		
		this.specifications = specifications;		
		this.time = time;		
		this.orderId = orderId;		
		this.subOrderId = subOrderId;		
		this.orderItemId = orderItemId;		
		this.orderItemDetailId = orderItemDetailId;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setMerchandiseId(long merchandiseId) {
		this.merchandiseId = merchandiseId;
	}

	public long getMerchandiseId() {
		return merchandiseId;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}	
		
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getSpecifications() {
		return specifications;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
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
		
	public void setOrderItemDetailId(long orderItemDetailId) {
		this.orderItemDetailId = orderItemDetailId;
	}

	public long getOrderItemDetailId() {
		return orderItemDetailId;
	}	
		
}
