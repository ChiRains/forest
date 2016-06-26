package com.qcloud.component.goods.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMonthHotSaleVO {
	
	private long id;		
	
	private long mallClassifyId;		
	
	//分类树编码
	private String mallBsid;		
	
	//统一商品ID
	private long unifiedMerchandiseId;		
	
	//数量
	private int number;		
	
	//年
	private int year;		
	
	//月
	private int month;		
	
	private long merchantClassifyId;		
	
	private String merchantBsid;		

	public AdminMonthHotSaleVO(){
	
	}

	public AdminMonthHotSaleVO(long id,long mallClassifyId,String mallBsid,long unifiedMerchandiseId,int number,int year,int month,long merchantClassifyId,String merchantBsid){
		this.id = id;		
		this.mallClassifyId = mallClassifyId;		
		this.mallBsid = mallBsid;		
		this.unifiedMerchandiseId = unifiedMerchandiseId;		
		this.number = number;		
		this.year = year;		
		this.month = month;		
		this.merchantClassifyId = merchantClassifyId;		
		this.merchantBsid = merchantBsid;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setMallClassifyId(long mallClassifyId) {
		this.mallClassifyId = mallClassifyId;
	}

	public long getMallClassifyId() {
		return mallClassifyId;
	}	
		
	public void setMallBsid(String mallBsid) {
		this.mallBsid = mallBsid;
	}

	public String getMallBsid() {
		return mallBsid;
	}	
		
	public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {
		this.unifiedMerchandiseId = unifiedMerchandiseId;
	}

	public long getUnifiedMerchandiseId() {
		return unifiedMerchandiseId;
	}	
		
	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}	
		
	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}	
		
	public void setMonth(int month) {
		this.month = month;
	}

	public int getMonth() {
		return month;
	}	
		
	public void setMerchantClassifyId(long merchantClassifyId) {
		this.merchantClassifyId = merchantClassifyId;
	}

	public long getMerchantClassifyId() {
		return merchantClassifyId;
	}	
		
	public void setMerchantBsid(String merchantBsid) {
		this.merchantBsid = merchantBsid;
	}

	public String getMerchantBsid() {
		return merchantBsid;
	}	
		
}
