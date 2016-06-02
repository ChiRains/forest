package com.qcloud.component.my.model;

import java.util.Date;
import java.math.BigDecimal;

public class DeliveryMode {
	
	//ID
	private long id;		
	
	//用户
	private long userId;		
	
	//类型 1物流 2门店自提 3送货上门
	private int type;		
	
	//描述
	private String desc;		
	
	//门店
	private long storeId;		
	
	//时间 门店自提时间或送货上门时间
	private String time;		

	public DeliveryMode(){
	
	}

	public DeliveryMode(long id,long userId,int type,String desc,long storeId,String time){
		this.id = id;		
		this.userId = userId;		
		this.type = type;		
		this.desc = desc;		
		this.storeId = storeId;		
		this.time = time;		
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
		
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}	
		
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}	
		
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
	}	
		
	public void setTime(String time) {
		this.time = time;
	}

	public String getTime() {
		return time;
	}	
		
}
