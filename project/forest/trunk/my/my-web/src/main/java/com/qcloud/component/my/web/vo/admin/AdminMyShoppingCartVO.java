package com.qcloud.component.my.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMyShoppingCartVO {
	
	private long id;		
	
	//用户ID
	private long userId;		
	
	//商家ID
	private long merchantId;		
	
	//商家分类ID
	private long merchantClassifyId;		
	
	//统一商品ID
	private long unifiedMerchandiseId;		
	
	//收藏时间
	private Date time;		
	
	//数量
	private int number;		

	public AdminMyShoppingCartVO(){
	
	}

	public AdminMyShoppingCartVO(long id,long userId,long merchantId,long merchantClassifyId,long unifiedMerchandiseId,Date time,int number){
		this.id = id;		
		this.userId = userId;		
		this.merchantId = merchantId;		
		this.merchantClassifyId = merchantClassifyId;		
		this.unifiedMerchandiseId = unifiedMerchandiseId;		
		this.time = time;		
		this.number = number;		
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
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
	}	
		
	public void setMerchantClassifyId(long merchantClassifyId) {
		this.merchantClassifyId = merchantClassifyId;
	}

	public long getMerchantClassifyId() {
		return merchantClassifyId;
	}	
		
	public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {
		this.unifiedMerchandiseId = unifiedMerchandiseId;
	}

	public long getUnifiedMerchandiseId() {
		return unifiedMerchandiseId;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}	
		
}
