package com.qcloud.component.my.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMyOrderFormVO {
	
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

	public AdminMyOrderFormVO(){
	
	}

	public AdminMyOrderFormVO(long id,long userId,long orderId,Date time,int state){
		this.id = id;		
		this.userId = userId;		
		this.orderId = orderId;		
		this.time = time;		
		this.state = state;		
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
		
}
