package com.qcloud.component.goods.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminCombinationMerchandiseItemVO {
	
	//ID
	private long id;		
	
	//组合商品ID
	private long combinationMerchandiseId;		
	
	//单一商品ID
	private long merchandiseItemId;		
	
	//商家ID
	private long merchantId;		
	
	//数量
	private int num;		

	public AdminCombinationMerchandiseItemVO(){
	
	}

	public AdminCombinationMerchandiseItemVO(long id,long combinationMerchandiseId,long merchandiseItemId,long merchantId,int num){
		this.id = id;		
		this.combinationMerchandiseId = combinationMerchandiseId;		
		this.merchandiseItemId = merchandiseItemId;		
		this.merchantId = merchantId;		
		this.num = num;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setCombinationMerchandiseId(long combinationMerchandiseId) {
		this.combinationMerchandiseId = combinationMerchandiseId;
	}

	public long getCombinationMerchandiseId() {
		return combinationMerchandiseId;
	}	
		
	public void setMerchandiseItemId(long merchandiseItemId) {
		this.merchandiseItemId = merchandiseItemId;
	}

	public long getMerchandiseItemId() {
		return merchandiseItemId;
	}	
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
	}	
		
	public void setNum(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}	
		
}
