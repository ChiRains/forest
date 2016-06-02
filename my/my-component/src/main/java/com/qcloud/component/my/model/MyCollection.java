package com.qcloud.component.my.model;

import java.util.Date;
import java.math.BigDecimal;

public class MyCollection {
	
	private long id;		
	
	//用户ID
	private long userId;		
	
	//对象ID
	private long objId;		
	
	//对象分类ID
	private long classifyId;		
	
	//收藏时间
	private Date time;		
	
	//对象类别1 商品 2商家
	private int type;		

	public MyCollection(){
	
	}

	public MyCollection(long id,long userId,long objId,long classifyId,Date time,int type){
		this.id = id;		
		this.userId = userId;		
		this.objId = objId;		
		this.classifyId = classifyId;		
		this.time = time;		
		this.type = type;		
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
		
	public void setObjId(long objId) {
		this.objId = objId;
	}

	public long getObjId() {
		return objId;
	}	
		
	public void setClassifyId(long classifyId) {
		this.classifyId = classifyId;
	}

	public long getClassifyId() {
		return classifyId;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}	
		
}
