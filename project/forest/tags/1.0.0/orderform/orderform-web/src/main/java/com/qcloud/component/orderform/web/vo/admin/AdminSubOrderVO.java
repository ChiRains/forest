package com.qcloud.component.orderform.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminSubOrderVO {
	
	//ID
	private long id;		
	
	//总单ID
	private long orderId;		
	
	//商家ID
	private long merchantId;		
	
	//子单号
	private String orderNumber;		
	
	//订单金额
	private double sum;		
	
	//状态,待付款,已付款,待发货,已发货,已签收
	private int state;		

	public AdminSubOrderVO(){
	
	}

	public AdminSubOrderVO(long id,long orderId,long merchantId,String orderNumber,double sum,int state){
		this.id = id;		
		this.orderId = orderId;		
		this.merchantId = merchantId;		
		this.orderNumber = orderNumber;		
		this.sum = sum;		
		this.state = state;		
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
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
	}	
		
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
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
		
}
