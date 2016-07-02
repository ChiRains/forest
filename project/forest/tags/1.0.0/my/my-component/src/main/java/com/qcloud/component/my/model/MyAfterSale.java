package com.qcloud.component.my.model;

import java.util.Date;
import java.math.BigDecimal;

public class MyAfterSale {
	
	//ID
	private long id;		
	
	//用户id
	private long userId;		
	
	//类型
	private int type;		
	
	//申请时间
	private Date time;		
	
	//最后更新时间
	private Date lastUpdateTime;		
	
	//售后单号
	private long afterSaleId;		
	
	//状态(1:显示 2:不显示)
	private int view;		
	
	private long orderId;		
	
	private long subOrderId;		

	public MyAfterSale(){
	
	}

	public MyAfterSale(long id,long userId,int type,Date time,Date lastUpdateTime,long afterSaleId,int view,long orderId,long subOrderId){
		this.id = id;		
		this.userId = userId;		
		this.type = type;		
		this.time = time;		
		this.lastUpdateTime = lastUpdateTime;		
		this.afterSaleId = afterSaleId;		
		this.view = view;		
		this.orderId = orderId;		
		this.subOrderId = subOrderId;		
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
		
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}	
		
	public void setAfterSaleId(long afterSaleId) {
		this.afterSaleId = afterSaleId;
	}

	public long getAfterSaleId() {
		return afterSaleId;
	}	
		
	public void setView(int view) {
		this.view = view;
	}

	public int getView() {
		return view;
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
		
}
