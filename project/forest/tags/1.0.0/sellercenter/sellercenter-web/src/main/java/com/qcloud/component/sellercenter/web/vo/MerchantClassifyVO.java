package com.qcloud.component.sellercenter.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class MerchantClassifyVO {
	
	private long id;		
	
	//商家
	private long merchantId;		
	
	//商品分类
	private long classifyId;		

	public MerchantClassifyVO(){
	
	}

	public MerchantClassifyVO(long id,long merchantId,long classifyId){
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
