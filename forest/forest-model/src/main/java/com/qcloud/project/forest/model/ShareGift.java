package com.qcloud.project.forest.model;

import java.util.Date;
import java.math.BigDecimal;

public class ShareGift {
	
	private long id;		
	
	//用户Id
	private long userId;		
	
	//邀请码
	private String code;		

	public ShareGift(){
	
	}

	public ShareGift(long id,long userId,String code){
		this.id = id;		
		this.userId = userId;		
		this.code = code;		
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
		
}
