package com.qcloud.component.my.model;

import java.util.Date;
import java.math.BigDecimal;

public class MyCollectionStatistics {
	
	private long id;		
	
	//用户ID
	private long userId;		
	
	//对象分类ID
	private long classifyId;		
	
	//对象类别1 商品 2商家
	private int type;		
	
	//收藏数量
	private int number;		

	public MyCollectionStatistics(){
	
	}

	public MyCollectionStatistics(long id,long userId,long classifyId,int type,int number){
		this.id = id;		
		this.userId = userId;		
		this.classifyId = classifyId;		
		this.type = type;		
		this.number = number;		
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
		
	public void setClassifyId(long classifyId) {
		this.classifyId = classifyId;
	}

	public long getClassifyId() {
		return classifyId;
	}	
		
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}	
		
	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}	
		
}
