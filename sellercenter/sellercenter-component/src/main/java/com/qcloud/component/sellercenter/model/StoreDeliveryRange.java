package com.qcloud.component.sellercenter.model;

import java.util.Date;
import java.math.BigDecimal;

public class StoreDeliveryRange {
	
	//ID
	private long id;		
	
	private long storeId;		
	
	private long radius;		

	public StoreDeliveryRange(){
	
	}

	public StoreDeliveryRange(long id,long storeId,long radius){
		this.id = id;		
		this.storeId = storeId;		
		this.radius = radius;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
	}	
		
	public void setRadius(long radius) {
		this.radius = radius;
	}

	public long getRadius() {
		return radius;
	}	
		
}
