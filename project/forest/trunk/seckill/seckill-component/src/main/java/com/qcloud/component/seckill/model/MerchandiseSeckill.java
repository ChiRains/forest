package com.qcloud.component.seckill.model;

import java.util.Date;
import java.math.BigDecimal;

public class MerchandiseSeckill {
	
	private long id;		
	
	//场次
	private long screeningsId;		
	
	//唯一商品ID
	private long unifiedMerchandiseId;	
	
	//商品ID
	private long qUnifiedMerchandiseId;
	
	//商品名称
	private String merchandiseName;
	
	//折扣价
	private double discountPrice;		
	
	//商城分类ID
	private long mallClassifyId;		
	
	//排序
	private int sort;		
	
	//已抢数量
	private int salesVolume;		
	
	//库存
	private int originalStock;		
	
	//1可用 2不可用
	private int enable;	

	public MerchandiseSeckill(){
	
	}

	public MerchandiseSeckill(long id,long screeningsId,long unifiedMerchandiseId,long qUnifiedMerchandiseId,String merchandiseName,double discountPrice,long mallClassifyId,int sort,int salesVolume,int originalStock,int enable){
		this.id = id;		
		this.screeningsId = screeningsId;		
		this.unifiedMerchandiseId = unifiedMerchandiseId;	
		this.qUnifiedMerchandiseId = qUnifiedMerchandiseId;
		this.merchandiseName = merchandiseName;
		this.discountPrice = discountPrice;		
		this.mallClassifyId = mallClassifyId;		
		this.sort = sort;		
		this.salesVolume = salesVolume;		
		this.originalStock = originalStock;		
		this.enable = enable;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setScreeningsId(long screeningsId) {
		this.screeningsId = screeningsId;
	}

	public long getScreeningsId() {
		return screeningsId;
	}	
		
	public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {
		this.unifiedMerchandiseId = unifiedMerchandiseId;
	}

	public long getUnifiedMerchandiseId() {
		return unifiedMerchandiseId;
	}	
		
	public String getMerchandiseName() {
		return merchandiseName;
	}

	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}

	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public double getDiscountPrice() {
		return discountPrice;
	}	
		
	public void setMallClassifyId(long mallClassifyId) {
		this.mallClassifyId = mallClassifyId;
	}

	public long getMallClassifyId() {
		return mallClassifyId;
	}	
		
	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getSort() {
		return sort;
	}	
		
	public void setSalesVolume(int salesVolume) {
		this.salesVolume = salesVolume;
	}

	public int getSalesVolume() {
		return salesVolume;
	}	
		
	public void setOriginalStock(int originalStock) {
		this.originalStock = originalStock;
	}

	public int getOriginalStock() {
		return originalStock;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public long getqUnifiedMerchandiseId() {
		return qUnifiedMerchandiseId;
	}

	public void setqUnifiedMerchandiseId(long qUnifiedMerchandiseId) {
		this.qUnifiedMerchandiseId = qUnifiedMerchandiseId;
	}	
		
}
