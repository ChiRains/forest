package com.qcloud.component.my.model;

import java.util.Date;
import java.math.BigDecimal;

public class MyEvaluation {
	
	private long id;		
	
	//评价表id
	private long evaluationId;		
	
	//商品id
	private long merchandiseId;		
	
	//评价人
	private long userId;		
	
	//订单明细id
	private long orderItemDetailId;		
	
	//订单时间
	private Date orderTime;		

	public MyEvaluation(){
	
	}

	public MyEvaluation(long id,long evaluationId,long merchandiseId,long userId,long orderItemDetailId,Date orderTime){
		this.id = id;		
		this.evaluationId = evaluationId;		
		this.merchandiseId = merchandiseId;		
		this.userId = userId;		
		this.orderItemDetailId = orderItemDetailId;		
		this.orderTime = orderTime;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setEvaluationId(long evaluationId) {
		this.evaluationId = evaluationId;
	}

	public long getEvaluationId() {
		return evaluationId;
	}	
		
	public void setMerchandiseId(long merchandiseId) {
		this.merchandiseId = merchandiseId;
	}

	public long getMerchandiseId() {
		return merchandiseId;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setOrderItemDetailId(long orderItemDetailId) {
		this.orderItemDetailId = orderItemDetailId;
	}

	public long getOrderItemDetailId() {
		return orderItemDetailId;
	}	
		
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getOrderTime() {
		return orderTime;
	}	
		
}
