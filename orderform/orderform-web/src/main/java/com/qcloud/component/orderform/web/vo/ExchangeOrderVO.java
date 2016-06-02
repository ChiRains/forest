package com.qcloud.component.orderform.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class ExchangeOrderVO {
	
	//ID
	private long id;		
	
	//总单ID
	private long orderId;		
	
	//状态(1:已申请 2:通过 3:未通过 )
	private int state;		
	
	//申请换货时间
	private Date time;		
	
	//用户id
	private long userId;		
	
	//换货单号
	private String orderNumber;		

	public ExchangeOrderVO(){
	
	}

	public ExchangeOrderVO(long id,long orderId,int state,Date time,long userId,String orderNumber){
		this.id = id;		
		this.orderId = orderId;		
		this.state = state;		
		this.time = time;		
		this.userId = userId;		
		this.orderNumber = orderNumber;		
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
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}	
		
}
