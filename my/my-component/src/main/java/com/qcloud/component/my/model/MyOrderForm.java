package com.qcloud.component.my.model;

import java.util.Date;
import java.math.BigDecimal;

public class MyOrderForm {
	
	//ID
	private long id;		
	
	//用户
	private long userId;		
	
	//总单
	private long orderId;		
	
	//下单时间
	private Date time;		
	
	//状态,待付款,已付款,待发货,发货中,已签收
	private int state;		
	
	private long subOrderId;		
	
	private Date lastUpdateTime;		

	public MyOrderForm(){
	
	}

	public MyOrderForm(long id,long userId,long orderId,Date time,int state,long subOrderId,Date lastUpdateTime){
		this.id = id;		
		this.userId = userId;		
		this.orderId = orderId;		
		this.time = time;		
		this.state = state;		
		this.subOrderId = subOrderId;		
		this.lastUpdateTime = lastUpdateTime;		
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
		
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderId() {
		return orderId;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
	public void setSubOrderId(long subOrderId) {
		this.subOrderId = subOrderId;
	}

	public long getSubOrderId() {
		return subOrderId;
	}	
		
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}	
		
}
