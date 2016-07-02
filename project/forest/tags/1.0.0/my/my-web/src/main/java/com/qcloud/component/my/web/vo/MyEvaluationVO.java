package com.qcloud.component.my.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class MyEvaluationVO {
	
	private long id;		
	
	//评价表id
	private long evaluationId;		
	
	//评价人
	private long userId;		
	
	//订单明细id
	private long orderItemDetailId;		
	
	//订单时间
	private Date orderTime;		

	public MyEvaluationVO(){
	
	}

	public MyEvaluationVO(long id,long evaluationId,long userId,long orderItemDetailId,Date orderTime){
		this.id = id;		
		this.evaluationId = evaluationId;		
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
