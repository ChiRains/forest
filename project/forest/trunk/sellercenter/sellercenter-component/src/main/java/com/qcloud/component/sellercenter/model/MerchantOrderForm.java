package com.qcloud.component.sellercenter.model;

import java.util.Date;
import java.math.BigDecimal;

public class MerchantOrderForm {
	
	//ID
	private long id;		
	
	//卖家
	private long merchantId;		
	
	//门店
	private long storeId;		
	
	//总单
	private long orderId;		
	
	//子单ID
	private long subOrderId;		
	
	//状态
	private int state;		
	
	//下单时间
	private Date time;		

	public MerchantOrderForm(){
	
	}

	public MerchantOrderForm(long id,long merchantId,long storeId,long orderId,long subOrderId,int state,Date time){
		this.id = id;		
		this.merchantId = merchantId;		
		this.storeId = storeId;		
		this.orderId = orderId;		
		this.subOrderId = subOrderId;		
		this.state = state;		
		this.time = time;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
	}	
		
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
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
