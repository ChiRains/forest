package com.qcloud.component.goods.model;

import java.util.Date;
import java.math.BigDecimal;

public class UnifiedMerchandise {
	
	//ID
	private long id;		
	
	//商家ID
	private long merchantId;		
	
	//类型
	private int type;		

	public UnifiedMerchandise(){
	
	}

	public UnifiedMerchandise(long id,long merchantId,int type){
		this.id = id;		
		this.merchantId = merchantId;		
		this.type = type;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
	}	
		
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}	
		
}
