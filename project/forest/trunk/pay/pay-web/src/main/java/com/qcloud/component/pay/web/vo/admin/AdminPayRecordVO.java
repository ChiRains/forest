package com.qcloud.component.pay.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminPayRecordVO {
	
	//ID
	private long id;		
	
	//订单号
	private String orderNumber;		
	
	//总单ID
	private long orderId;		
	
	//下单时间
	private Date orderDate;		
	
	//交易ID
	private String tradeId;		
	
	//交易类别 101支付宝 102微信支付 103银联 104货到付款 105钱包 106消费币 107积分
	private int tradeTyped;		
	
	//交易ID
	private String sum;		
	
	//状态跳转时间
	private Date time;		

	public AdminPayRecordVO(){
	
	}

	public AdminPayRecordVO(long id,String orderNumber,long orderId,Date orderDate,String tradeId,int tradeTyped,String sum,Date time){
		this.id = id;		
		this.orderNumber = orderNumber;		
		this.orderId = orderId;		
		this.orderDate = orderDate;		
		this.tradeId = tradeId;		
		this.tradeTyped = tradeTyped;		
		this.sum = sum;		
		this.time = time;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
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
		
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getTradeId() {
		return tradeId;
	}	
		
	public void setTradeTyped(int tradeTyped) {
		this.tradeTyped = tradeTyped;
	}

	public int getTradeTyped() {
		return tradeTyped;
	}	
		
	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getSum() {
		return sum;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
}
