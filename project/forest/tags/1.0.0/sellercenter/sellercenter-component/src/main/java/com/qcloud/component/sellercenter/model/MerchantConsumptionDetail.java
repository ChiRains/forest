package com.qcloud.component.sellercenter.model;

import java.util.Date;
import java.math.BigDecimal;

public class MerchantConsumptionDetail {
	
	//ID
	private long id;		
	
	//财富账号
	private long merchantId;		
	
	//当次财富值
	private double point;		
	
	//余额
	private double remainder;		
	
	//发生时间
	private Date time;		
	
	//财富描述
	private String desc;		

	public MerchantConsumptionDetail(){
	
	}

	public MerchantConsumptionDetail(long id,long merchantId,double point,double remainder,Date time,String desc){
		this.id = id;		
		this.merchantId = merchantId;		
		this.point = point;		
		this.remainder = remainder;		
		this.time = time;		
		this.desc = desc;		
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
		
	public void setPoint(double point) {
		this.point = point;
	}

	public double getPoint() {
		return point;
	}	
		
	public void setRemainder(double remainder) {
		this.remainder = remainder;
	}

	public double getRemainder() {
		return remainder;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}	
		
}
