package com.qcloud.component.goods.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMerchandiseVipDiscountHistoryVO {
	
	//ID
	private long id;		
	
	//用户ID
	private long userId;		
	
	//单一商品ID
	private long merchandiseItemId;		
	
	//vip价
	private double price;		
	
	//折扣
	private double discount;		
	
	//支付方式
	private int paymentType;		
	
	//历史vip价
	private double priceHistory;		
	
	//历史折扣
	private double discountHistory;		
	
	//历史支付方式
	private int paymentTypeHistory;		
	
	//修改时间
	private Date updateTime;		

	public AdminMerchandiseVipDiscountHistoryVO(){
	
	}

	public AdminMerchandiseVipDiscountHistoryVO(long id,long userId,long merchandiseItemId,double price,double discount,int paymentType,double priceHistory,double discountHistory,int paymentTypeHistory,Date updateTime){
		this.id = id;		
		this.userId = userId;		
		this.merchandiseItemId = merchandiseItemId;		
		this.price = price;		
		this.discount = discount;		
		this.paymentType = paymentType;		
		this.priceHistory = priceHistory;		
		this.discountHistory = discountHistory;		
		this.paymentTypeHistory = paymentTypeHistory;		
		this.updateTime = updateTime;		
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
		
	public void setMerchandiseItemId(long merchandiseItemId) {
		this.merchandiseItemId = merchandiseItemId;
	}

	public long getMerchandiseItemId() {
		return merchandiseItemId;
	}	
		
	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}	
		
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getDiscount() {
		return discount;
	}	
		
	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	public int getPaymentType() {
		return paymentType;
	}	
		
	public void setPriceHistory(double priceHistory) {
		this.priceHistory = priceHistory;
	}

	public double getPriceHistory() {
		return priceHistory;
	}	
		
	public void setDiscountHistory(double discountHistory) {
		this.discountHistory = discountHistory;
	}

	public double getDiscountHistory() {
		return discountHistory;
	}	
		
	public void setPaymentTypeHistory(int paymentTypeHistory) {
		this.paymentTypeHistory = paymentTypeHistory;
	}

	public int getPaymentTypeHistory() {
		return paymentTypeHistory;
	}	
		
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}	
		
}
