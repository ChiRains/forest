package com.qcloud.component.orderform.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class ExchangeOrderItemDetailVO {
	
	//ID
	private long id;		
	
	//总单ID
	private long orderId;		
	
	//子单ID
	private long subOrderId;		
	
	//订单项ID
	private long orderItemId;		
	
	//订单项明细ID
	private long orderItemDetailId;		
	
	//商家ID
	private long merchantId;		
	
	//门店ID
	private long storeId;		
	
	//申请换货时间
	private Date time;		
	
	//状态(1:已申请 2:通过 3:未通过 )
	private int state;		
	
	//数量
	private int number;		
	
	//说明
	private String explain;		
	
	//原因
	private String reason;		
	
	//换货总计单id
	private long exchangeId;		

	public ExchangeOrderItemDetailVO(){
	
	}

	public ExchangeOrderItemDetailVO(long id,long orderId,long subOrderId,long orderItemId,long orderItemDetailId,long merchantId,long storeId,Date time,int state,int number,String explain,String reason,long exchangeId){
		this.id = id;		
		this.orderId = orderId;		
		this.subOrderId = subOrderId;		
		this.orderItemId = orderItemId;		
		this.orderItemDetailId = orderItemDetailId;		
		this.merchantId = merchantId;		
		this.storeId = storeId;		
		this.time = time;		
		this.state = state;		
		this.number = number;		
		this.explain = explain;		
		this.reason = reason;		
		this.exchangeId = exchangeId;		
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
		
	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}	
		
	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}	
		
	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}	
		
	public void setExchangeId(long exchangeId) {
		this.exchangeId = exchangeId;
	}

	public long getExchangeId() {
		return exchangeId;
	}	
		
}
