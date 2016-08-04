package com.qcloud.project.forest.model;

import java.util.Date;
import java.math.BigDecimal;

public class ShareGiftUser {
	
	private long id;		
	
	//用户Id
	private long userId;		
	
	//邀请码
	private String code;		
	
	//状态（1，已经注册；2已经消费）
	private int state;		

	public ShareGiftUser(){
	
	}

	public ShareGiftUser(long id,long userId,String code,int state){
		this.id = id;		
		this.userId = userId;		
		this.code = code;		
		this.state = state;		
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
		
	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
}
