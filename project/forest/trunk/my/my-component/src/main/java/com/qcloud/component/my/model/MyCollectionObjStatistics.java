package com.qcloud.component.my.model;

import java.util.Date;
import java.math.BigDecimal;

public class MyCollectionObjStatistics {
	
	private long id;		
	
	//对象ID
	private long objId;		
	
	//对象类别1 商品 2商家
	private int type;		
	
	//收藏数量
	private int number;		

	public MyCollectionObjStatistics(){
	
	}

	public MyCollectionObjStatistics(long id,long objId,int type,int number){
		this.id = id;		
		this.objId = objId;		
		this.type = type;		
		this.number = number;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setObjId(long objId) {
		this.objId = objId;
	}

	public long getObjId() {
		return objId;
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
