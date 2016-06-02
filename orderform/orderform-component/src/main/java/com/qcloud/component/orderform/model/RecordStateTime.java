package com.qcloud.component.orderform.model;

import java.util.Date;
import java.math.BigDecimal;

public class RecordStateTime {
	
	//ID
	private long id;		
	
	//总单ID
	private long orderId;		
	
	//下单时间
	private Date orderDate;		
	
	//总单ID
	private long dataId;		
	
	//dataId类型,1总单 2子单 3订单项 4订单明细 5退货单 6退货明细 7换货单 8换货明细
	private int dataIdType;		
	
	//状态
	private int state;		
	
	//状态跳转时间
	private Date time;		

	public RecordStateTime(){
	
	}

	public RecordStateTime(long id,long orderId,Date orderDate,long dataId,int dataIdType,int state,Date time){
		this.id = id;		
		this.orderId = orderId;		
		this.orderDate = orderDate;		
		this.dataId = dataId;		
		this.dataIdType = dataIdType;		
		this.state = state;		
		this.time = time;		
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
		
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}	
		
	public void setDataId(long dataId) {
		this.dataId = dataId;
	}

	public long getDataId() {
		return dataId;
	}	
		
	public void setDataIdType(int dataIdType) {
		this.dataIdType = dataIdType;
	}

	public int getDataIdType() {
		return dataIdType;
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
		
}
