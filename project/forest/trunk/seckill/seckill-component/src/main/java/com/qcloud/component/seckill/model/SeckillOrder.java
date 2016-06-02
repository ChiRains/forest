package com.qcloud.component.seckill.model;

import java.util.Date;
import java.math.BigDecimal;

public class SeckillOrder {
	
	//ID
	private long id;		
	
	//订单号
	private String orderNumber;		
	
	//秒杀商品ID
	private long seckillMerchandiseId;		
	
	//商家ID
	private long merchantId;		
	
	//总单ID
	private long orderId;		
	
	//下单时间
	private Date time;		
	
	//订单金额
	private double sum;		
	
	//现金
	private double cash;		
	
	//用户
	private long userId;		
	
	//统一商品ID
	private long unifiedMerchandiseId;		
	
	//商品名称
	private String name;		
	
	//缩略图
	private String image;		
	
	//进货价
	private double purchase;		
	
	//折扣价,成交价
	private double discount;		
	
	//原价
	private double price;		
	
	//秒杀价
	private double seckillPrice;		

	public SeckillOrder(){
	
	}

	public SeckillOrder(long id,String orderNumber,long seckillMerchandiseId,long merchantId,long orderId,Date time,double sum,double cash,long userId,long unifiedMerchandiseId,String name,String image,double purchase,double discount,double price,double seckillPrice){
		this.id = id;		
		this.orderNumber = orderNumber;		
		this.seckillMerchandiseId = seckillMerchandiseId;		
		this.merchantId = merchantId;		
		this.orderId = orderId;		
		this.time = time;		
		this.sum = sum;		
		this.cash = cash;		
		this.userId = userId;		
		this.unifiedMerchandiseId = unifiedMerchandiseId;		
		this.name = name;		
		this.image = image;		
		this.purchase = purchase;		
		this.discount = discount;		
		this.price = price;		
		this.seckillPrice = seckillPrice;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}	
		
	public void setSeckillMerchandiseId(long seckillMerchandiseId) {
		this.seckillMerchandiseId = seckillMerchandiseId;
	}

	public long getSeckillMerchandiseId() {
		return seckillMerchandiseId;
	}	
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
	}	
		
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderId() {
		return orderId;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getSum() {
		return sum;
	}	
		
	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getCash() {
		return cash;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {
		this.unifiedMerchandiseId = unifiedMerchandiseId;
	}

	public long getUnifiedMerchandiseId() {
		return unifiedMerchandiseId;
	}	
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
		
	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
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
		
	public void setSeckillPrice(double seckillPrice) {
		this.seckillPrice = seckillPrice;
	}

	public double getSeckillPrice() {
		return seckillPrice;
	}	
		
}
