package com.qcloud.component.goods.model;

import java.util.Date;
import java.math.BigDecimal;

public class MerchandiseItem {
	
	//ID
	private long id;		
	
	//ID
	private long unifiedMerchandiseId;		
	
	//商家ID
	private long merchantId;		
	
	//商品ID
	private long merchandiseId;		
	
	//商城分类ID
	private long mallClassifyId;		
	
	//树编码
	private String mallClassifyBsid;		
	
	//商品分类ID
	private long merchantClassifyId;		
	
	//树编码
	private String merchantClassifyBsid;		
	
	//名称
	private String name;		
	
	//关键字
	private String keywords;		
	
	//状态
	private int state;		
	
	//进货价
	private double purchase;		
	
	//折扣价
	private double discount;		
	
	//原价
	private double price;		
	
	//库存
	private int stock;		
	
	//商品规格
	private long merchandiseSpecificationsId;		
	
	//销量
	private long salesVolume;		
	
	//虚销量
	private long virtualSalesVolume;		
	
	//点击数量
	private long clickRate;		
	
	//差评数量
	private long lowEvaluation;		
	
	//中评数量
	private long middleEvaluation;		
	
	//好评数量
	private long goodEvaluation;		
	
	//录入时间
	private Date recordTime;		
	
	//更新时间
	private Date updateTime;		
	
	private long brandId;		

	public MerchandiseItem(){
	
	}

	public MerchandiseItem(long id,long unifiedMerchandiseId,long merchantId,long merchandiseId,long mallClassifyId,String mallClassifyBsid,long merchantClassifyId,String merchantClassifyBsid,String name,String keywords,int state,double purchase,double discount,double price,int stock,long merchandiseSpecificationsId,long salesVolume,long virtualSalesVolume,long clickRate,long lowEvaluation,long middleEvaluation,long goodEvaluation,Date recordTime,Date updateTime,long brandId){
		this.id = id;		
		this.unifiedMerchandiseId = unifiedMerchandiseId;		
		this.merchantId = merchantId;		
		this.merchandiseId = merchandiseId;		
		this.mallClassifyId = mallClassifyId;		
		this.mallClassifyBsid = mallClassifyBsid;		
		this.merchantClassifyId = merchantClassifyId;		
		this.merchantClassifyBsid = merchantClassifyBsid;		
		this.name = name;		
		this.keywords = keywords;		
		this.state = state;		
		this.purchase = purchase;		
		this.discount = discount;		
		this.price = price;		
		this.stock = stock;		
		this.merchandiseSpecificationsId = merchandiseSpecificationsId;		
		this.salesVolume = salesVolume;		
		this.virtualSalesVolume = virtualSalesVolume;		
		this.clickRate = clickRate;		
		this.lowEvaluation = lowEvaluation;		
		this.middleEvaluation = middleEvaluation;		
		this.goodEvaluation = goodEvaluation;		
		this.recordTime = recordTime;		
		this.updateTime = updateTime;		
		this.brandId = brandId;		
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
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
	}	
		
	public void setMerchandiseId(long merchandiseId) {
		this.merchandiseId = merchandiseId;
	}

	public long getMerchandiseId() {
		return merchandiseId;
	}	
		
	public void setMallClassifyId(long mallClassifyId) {
		this.mallClassifyId = mallClassifyId;
	}

	public long getMallClassifyId() {
		return mallClassifyId;
	}	
		
	public void setMallClassifyBsid(String mallClassifyBsid) {
		this.mallClassifyBsid = mallClassifyBsid;
	}

	public String getMallClassifyBsid() {
		return mallClassifyBsid;
	}	
		
	public void setMerchantClassifyId(long merchantClassifyId) {
		this.merchantClassifyId = merchantClassifyId;
	}

	public long getMerchantClassifyId() {
		return merchantClassifyId;
	}	
		
	public void setMerchantClassifyBsid(String merchantClassifyBsid) {
		this.merchantClassifyBsid = merchantClassifyBsid;
	}

	public String getMerchantClassifyBsid() {
		return merchantClassifyBsid;
	}	
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
		
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getKeywords() {
		return keywords;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
	public void setPurchase(double purchase) {
		this.purchase = purchase;
	}

	public double getPurchase() {
		return purchase;
	}	
		
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getDiscount() {
		return discount;
	}	
		
	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}	
		
	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStock() {
		return stock;
	}	
		
	public void setMerchandiseSpecificationsId(long merchandiseSpecificationsId) {
		this.merchandiseSpecificationsId = merchandiseSpecificationsId;
	}

	public long getMerchandiseSpecificationsId() {
		return merchandiseSpecificationsId;
	}	
		
	public void setSalesVolume(long salesVolume) {
		this.salesVolume = salesVolume;
	}

	public long getSalesVolume() {
		return salesVolume;
	}	
		
	public void setVirtualSalesVolume(long virtualSalesVolume) {
		this.virtualSalesVolume = virtualSalesVolume;
	}

	public long getVirtualSalesVolume() {
		return virtualSalesVolume;
	}	
		
	public void setClickRate(long clickRate) {
		this.clickRate = clickRate;
	}

	public long getClickRate() {
		return clickRate;
	}	
		
	public void setLowEvaluation(long lowEvaluation) {
		this.lowEvaluation = lowEvaluation;
	}

	public long getLowEvaluation() {
		return lowEvaluation;
	}	
		
	public void setMiddleEvaluation(long middleEvaluation) {
		this.middleEvaluation = middleEvaluation;
	}

	public long getMiddleEvaluation() {
		return middleEvaluation;
	}	
		
	public void setGoodEvaluation(long goodEvaluation) {
		this.goodEvaluation = goodEvaluation;
	}

	public long getGoodEvaluation() {
		return goodEvaluation;
	}	
		
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public Date getRecordTime() {
		return recordTime;
	}	
		
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}	
		
	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

	public long getBrandId() {
		return brandId;
	}	
		
}
