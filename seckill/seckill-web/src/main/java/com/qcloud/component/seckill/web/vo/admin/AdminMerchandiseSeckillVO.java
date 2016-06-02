package com.qcloud.component.seckill.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMerchandiseSeckillVO {
	
	private long id;		
	
	//场次
	private long screeningsId;		
	
	//唯一商品ID
	private long unifiedMerchandiseId;		
	
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
	
	//商品名称
	private String merchandiseName;
	
	// 商城分类ID
    private String mallClassifyName;

	public AdminMerchandiseSeckillVO(){
	
	}

	public AdminMerchandiseSeckillVO(long id,long screeningsId,long unifiedMerchandiseId,double discountPrice,long mallClassifyId,int sort,int salesVolume,int originalStock){
		this.id = id;		
		this.screeningsId = screeningsId;		
		this.unifiedMerchandiseId = unifiedMerchandiseId;		
		this.discountPrice = discountPrice;		
		this.mallClassifyId = mallClassifyId;		
		this.sort = sort;		
		this.salesVolume = salesVolume;		
		this.originalStock = originalStock;		
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

	public int getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(int salesVolume) {
		this.salesVolume = salesVolume;
	}

	public int getOriginalStock() {
		return originalStock;
	}

	public void setOriginalStock(int originalStock) {
		this.originalStock = originalStock;
	}

	public String getMerchandiseName() {
		return merchandiseName;
	}

	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}

	public String getMallClassifyName() {
		return mallClassifyName;
	}

	public void setMallClassifyName(String mallClassifyName) {
		this.mallClassifyName = mallClassifyName;
	}	
		
}
