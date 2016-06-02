package com.qcloud.component.brokerage.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class DataPoolVO {
	
	//ID
	private long id;		
	
	//数据源ID
	private long sourceDateId;		
	
	//类别 1商品 2商家 51房产 52会员注册佣金 53扫卡积分 ...
	private int type;		
	
	//名称
	private String name;		
	
	//图
	private String image;		
	
	//进货价
	private double purchase;		
	
	//折扣价
	private double discount;		
	
	//数量
	private int number;		
	
	//多少钱
	private double cash;		
	
	//下定时间
	private Date orderTime;		
	
	//用户ID
	private long userId;		
	
	//卖家ID
	private long merchantId;		
	
	private Date generateTime;		
	
	private long formulaId;		

	public DataPoolVO(){
	
	}

	public DataPoolVO(long id,long sourceDateId,int type,String name,String image,double purchase,double discount,int number,double cash,Date orderTime,long userId,long merchantId,Date generateTime,long formulaId){
		this.id = id;		
		this.sourceDateId = sourceDateId;		
		this.type = type;		
		this.name = name;		
		this.image = image;		
		this.purchase = purchase;		
		this.discount = discount;		
		this.number = number;		
		this.cash = cash;		
		this.orderTime = orderTime;		
		this.userId = userId;		
		this.merchantId = merchantId;		
		this.generateTime = generateTime;		
		this.formulaId = formulaId;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setSourceDateId(long sourceDateId) {
		this.sourceDateId = sourceDateId;
	}

	public long getSourceDateId() {
		return sourceDateId;
	}	
		
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
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
		
	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}	
		
	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getCash() {
		return cash;
	}	
		
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getOrderTime() {
		return orderTime;
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
		
	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}

	public Date getGenerateTime() {
		return generateTime;
	}	
		
	public void setFormulaId(long formulaId) {
		this.formulaId = formulaId;
	}

	public long getFormulaId() {
		return formulaId;
	}	
		
}
