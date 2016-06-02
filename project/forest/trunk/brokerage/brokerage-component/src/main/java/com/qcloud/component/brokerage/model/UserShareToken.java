package com.qcloud.component.brokerage.model;

import java.util.Date;
import java.math.BigDecimal;

public class UserShareToken {
	
	private long id;		
	
	//用户ID
	private long userId;		
	
	//token
	private String token;		

	public UserShareToken(){
	
	}

	public UserShareToken(long id,long userId,String token){
		this.id = id;		
		this.userId = userId;		
		this.token = token;		
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
		
	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}	
		
}
