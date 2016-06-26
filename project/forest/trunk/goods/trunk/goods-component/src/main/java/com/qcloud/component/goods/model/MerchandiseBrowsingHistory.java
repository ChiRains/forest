package com.qcloud.component.goods.model;

import java.util.Date;
import java.math.BigDecimal;

public class MerchandiseBrowsingHistory {
	
	//ID
	private long id;		
	
	//统一商品ID
	private long unifiedMerchandiseId;		
	
	//用户ID
	private long userId;		
	
	//浏览时间
	private Date browsingTime;		
	
	//客户端类型:1微信 2安卓 3IOS 4PC
	private int clientType;		

	public MerchandiseBrowsingHistory(){
	
	}

	public MerchandiseBrowsingHistory(long id,long unifiedMerchandiseId,long userId,Date browsingTime,int clientType){
		this.id = id;		
		this.unifiedMerchandiseId = unifiedMerchandiseId;		
		this.userId = userId;		
		this.browsingTime = browsingTime;		
		this.clientType = clientType;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {
		this.unifiedMerchandiseId = unifiedMerchandiseId;
	}

	public long getUnifiedMerchandiseId() {
		return unifiedMerchandiseId;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setBrowsingTime(Date browsingTime) {
		this.browsingTime = browsingTime;
	}

	public Date getBrowsingTime() {
		return browsingTime;
	}	
		
	public void setClientType(int clientType) {
		this.clientType = clientType;
	}

	public int getClientType() {
		return clientType;
	}	
		
}
