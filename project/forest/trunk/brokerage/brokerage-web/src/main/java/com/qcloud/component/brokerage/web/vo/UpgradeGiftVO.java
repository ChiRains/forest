package com.qcloud.component.brokerage.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class UpgradeGiftVO {
	
	private long id;		
	
	private long gradeId;		
	
	private long couponId;		
	
	private int number;		
	
	private Date limitTime;		

	public UpgradeGiftVO(){
	
	}

	public UpgradeGiftVO(long id,long gradeId,long couponId,int number,Date limitTime){
		this.id = id;		
		this.gradeId = gradeId;		
		this.couponId = couponId;		
		this.number = number;		
		this.limitTime = limitTime;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setGradeId(long gradeId) {
		this.gradeId = gradeId;
	}

	public long getGradeId() {
		return gradeId;
	}	
		
	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public long getCouponId() {
		return couponId;
	}	
		
	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}	
		
	public void setLimitTime(Date limitTime) {
		this.limitTime = limitTime;
	}

	public Date getLimitTime() {
		return limitTime;
	}	
		
}
