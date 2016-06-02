package com.qcloud.component.personalcenter.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMySignInMonthVO {
	
	//ID
	private long id;		
	
	//用户
	private long userId;		
	
	//年
	private int year;		
	
	//月
	private int month;		
	
	//记录
	private String record;		

	public AdminMySignInMonthVO(){
	
	}

	public AdminMySignInMonthVO(long id,long userId,int year,int month,String record){
		this.id = id;		
		this.userId = userId;		
		this.year = year;		
		this.month = month;		
		this.record = record;		
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
		
	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}	
		
	public void setMonth(int month) {
		this.month = month;
	}

	public int getMonth() {
		return month;
	}	
		
	public void setRecord(String record) {
		this.record = record;
	}

	public String getRecord() {
		return record;
	}	
		
}
