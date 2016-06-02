package com.qcloud.component.orderform.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class ReturnOrderVO {
	
	//ID
	private long id;		
	
	//总单ID
	private long orderId;		
	
	//退款金额
	private double sum;		
	
	//状态(1:已申请 2:通过 3:未通过 )
	private int state;		
	
	//用户id
	private long userId;		
	
	private Date time;		
	
	//退货单号
	private String orderNumber;		

	public ReturnOrderVO(){
	
	}

	public ReturnOrderVO(long id,long orderId,double sum,int state,long userId,Date time,String orderNumber){
		this.id = id;		
		this.orderId = orderId;		
		this.sum = sum;		
		this.state = state;		
		this.userId = userId;		
		this.time = time;		
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
		
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}	
		
}
