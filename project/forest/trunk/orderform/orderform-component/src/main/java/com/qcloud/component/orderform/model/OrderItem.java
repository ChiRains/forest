package com.qcloud.component.orderform.model;

import java.util.Date;
import java.math.BigDecimal;

public class OrderItem {
	
	//ID
	private long id;		
	
	//总单ID
	private long orderId;		
	
	//子单ID
	private long subOrderId;		
	
	//商家ID
	private long merchantId;		
	
	//统一商品ID
	private long unifiedMerchandiseId;		
	
	//场景
	private int sence;		
	
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
	
	//优惠面额
	private double preferential;		
	
	//小计金额
	private double sum;		
	
	//现金
	private double cash;		
	
	//积分
	private int integral;		
	
	//消费币
	private double consumption;		
	
	//优惠描述
	private String preferentialStr;		
	
	//数量
	private int number;		
	
	//状态,待付款,已付款,待发货,已发货,已签收
	private int state;		
	
	//商品快照
	private String snapshot;		
	
	//能否评论,1可以  2不行
	private int evaluation;		
	
	//能否售后,1可以  2不行
	private int afterSale;		

	public OrderItem(){
	
	}

	public OrderItem(long id,long orderId,long subOrderId,long merchantId,long unifiedMerchandiseId,int sence,String name,String image,double purchase,double discount,double price,double preferential,double sum,double cash,int integral,double consumption,String preferentialStr,int number,int state,String snapshot,int evaluation,int afterSale){
		this.id = id;		
		this.orderId = orderId;		
		this.subOrderId = subOrderId;		
		this.merchantId = merchantId;		
		this.unifiedMerchandiseId = unifiedMerchandiseId;		
		this.sence = sence;		
		this.name = name;		
		this.image = image;		
		this.purchase = purchase;		
		this.discount = discount;		
		this.price = price;		
		this.preferential = preferential;		
		this.sum = sum;		
		this.cash = cash;		
		this.integral = integral;		
		this.consumption = consumption;		
		this.preferentialStr = preferentialStr;		
		this.number = number;		
		this.state = state;		
		this.snapshot = snapshot;		
		this.evaluation = evaluation;		
		this.afterSale = afterSale;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderId() {
		return orderId;
	}	
		
	public void setSubOrderId(long subOrderId) {
		this.subOrderId = subOrderId;
	}

	public long getSubOrderId() {
		return subOrderId;
	}	
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
	}	
		
	public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {
		this.unifiedMerchandiseId = unifiedMerchandiseId;
	}

	public long getUnifiedMerchandiseId() {
		return unifiedMerchandiseId;
	}	
		
	public void setSence(int sence) {
		this.sence = sence;
	}

	public int getSence() {
		return sence;
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
		
	public void setPreferential(double preferential) {
		this.preferential = preferential;
	}

	public double getPreferential() {
		return preferential;
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
		
	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public int getIntegral() {
		return integral;
	}	
		
	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	public double getConsumption() {
		return consumption;
	}	
		
	public void setPreferentialStr(String preferentialStr) {
		this.preferentialStr = preferentialStr;
	}

	public String getPreferentialStr() {
		return preferentialStr;
	}	
		
	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}

	public String getSnapshot() {
		return snapshot;
	}	
		
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	public int getEvaluation() {
		return evaluation;
	}	
		
	public void setAfterSale(int afterSale) {
		this.afterSale = afterSale;
	}

	public int getAfterSale() {
		return afterSale;
	}	
		
}
