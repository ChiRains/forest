package com.qcloud.component.sellercenter.model;

import java.util.Date;
import java.math.BigDecimal;

public class MerchantMerchandiseClassify {
	
	private long id;		
	
	private long merchantId;		
	
	private long classifyId;		

	public MerchantMerchandiseClassify(){
	
	}

	public MerchantMerchandiseClassify(long id,long merchantId,long classifyId){
		this.id = id;		
		this.merchantId = merchantId;		
		this.classifyId = classifyId;		
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
		
	public void setClassifyId(long classifyId) {
		this.classifyId = classifyId;
	}

	public long getClassifyId() {
		return classifyId;
	}	
		
}
