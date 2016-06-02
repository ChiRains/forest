package com.qcloud.component.sellercenter.model;

import java.util.Date;
import java.math.BigDecimal;

public class StoreDeliveryTime {
	
	//ID
	private long id;		
	
	private long storeId;		
	
	//自提开始时间
	private String pickupStartTime;		
	
	//自提结束时间
	private String pickupEndTime;		
	
	//自提描述
	private String pickupDesc;		
	
	//送货上门开始时间
	private String deliveryStartTime;		
	
	//送货上门结束时间
	private String deliveryEndTime;		
	
	//送货频率
	private int deliveryFrequency;		
	
	//送货时长
	private int deliveryDuration;		

	public StoreDeliveryTime(){
	
	}

	public StoreDeliveryTime(long id,long storeId,String pickupStartTime,String pickupEndTime,String pickupDesc,String deliveryStartTime,String deliveryEndTime,int deliveryFrequency,int deliveryDuration){
		this.id = id;		
		this.storeId = storeId;		
		this.pickupStartTime = pickupStartTime;		
		this.pickupEndTime = pickupEndTime;		
		this.pickupDesc = pickupDesc;		
		this.deliveryStartTime = deliveryStartTime;		
		this.deliveryEndTime = deliveryEndTime;		
		this.deliveryFrequency = deliveryFrequency;		
		this.deliveryDuration = deliveryDuration;		
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
		
	public void setPickupStartTime(String pickupStartTime) {
		this.pickupStartTime = pickupStartTime;
	}

	public String getPickupStartTime() {
		return pickupStartTime;
	}	
		
	public void setPickupEndTime(String pickupEndTime) {
		this.pickupEndTime = pickupEndTime;
	}

	public String getPickupEndTime() {
		return pickupEndTime;
	}	
		
	public void setPickupDesc(String pickupDesc) {
		this.pickupDesc = pickupDesc;
	}

	public String getPickupDesc() {
		return pickupDesc;
	}	
		
	public void setDeliveryStartTime(String deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}

	public String getDeliveryStartTime() {
		return deliveryStartTime;
	}	
		
	public void setDeliveryEndTime(String deliveryEndTime) {
		this.deliveryEndTime = deliveryEndTime;
	}

	public String getDeliveryEndTime() {
		return deliveryEndTime;
	}	
		
	public void setDeliveryFrequency(int deliveryFrequency) {
		this.deliveryFrequency = deliveryFrequency;
	}

	public int getDeliveryFrequency() {
		return deliveryFrequency;
	}	
		
	public void setDeliveryDuration(int deliveryDuration) {
		this.deliveryDuration = deliveryDuration;
	}

	public int getDeliveryDuration() {
		return deliveryDuration;
	}	
		
}
