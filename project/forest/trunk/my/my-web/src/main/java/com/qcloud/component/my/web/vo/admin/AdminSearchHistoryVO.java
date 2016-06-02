package com.qcloud.component.my.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminSearchHistoryVO {
	
	private long id;		
	
	private String keywords;		
	
	private Date time;		
	
	private long userId;		
	
	private int type;		

	public AdminSearchHistoryVO(){
	
	}

	public AdminSearchHistoryVO(long id,String keywords,Date time,long userId,int type){
		this.id = id;		
		this.keywords = keywords;		
		this.time = time;		
		this.userId = userId;		
		this.type = type;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getKeywords() {
		return keywords;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
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
		
}
