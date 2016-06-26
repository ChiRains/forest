package com.qcloud.component.goods.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminCombinationMerchandiseVO {
	
	//ID
	private long id;		
	
	//商家ID
	private long merchantId;		
	
	//名称
	private String name;		
	
	//ID
	private long unifiedMerchandiseId;		
	
	//进货价
	private double purchase;		
	
	//折扣价
	private double discount;		
	
	//原价
	private double price;		
	
	//库存
	private int stock;		
	
	//更新时间
	private Date updateTime;		
	
	private String image;		

	public AdminCombinationMerchandiseVO(){
	
	}

	public AdminCombinationMerchandiseVO(long id,long merchantId,String name,long unifiedMerchandiseId,double purchase,double discount,double price,int stock,Date updateTime,String image){
		this.id = id;		
		this.merchantId = merchantId;		
		this.name = name;		
		this.unifiedMerchandiseId = unifiedMerchandiseId;		
		this.purchase = purchase;		
		this.discount = discount;		
		this.price = price;		
		this.stock = stock;		
		this.updateTime = updateTime;		
		this.image = image;		
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
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
		
	public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {
		this.unifiedMerchandiseId = unifiedMerchandiseId;
	}

	public long getUnifiedMerchandiseId() {
		return unifiedMerchandiseId;
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
		
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}	
		
	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}	
		
}
