package com.qcloud.project.forest.model;

import java.util.Date;
import java.math.BigDecimal;

public class GiftCouponUser {
	
	private long id;		
	
	//拥有者Id
	private long userId;		
	
	//赠品券Id
	private long giftCouponId;		
	
	//领取日期
	private Date extractDate;		
	
	//生效日期
	private Date validDate;		
	
	//失效日期
	private Date inValidDate;		
	
	private String name;		
	
	//订单Id
	private long orderId;		
	
	//下订单日期
	private Date orderDate;		
	
	//使用状态（1，未用；2，已用）
	private int state;		
	
	//图片
	private String image;		

	public GiftCouponUser(){
	
	}

	public GiftCouponUser(long id,long userId,long giftCouponId,Date extractDate,Date validDate,Date inValidDate,String name,long orderId,Date orderDate,int state,String image){
		this.id = id;		
		this.userId = userId;		
		this.giftCouponId = giftCouponId;		
		this.extractDate = extractDate;		
		this.validDate = validDate;		
		this.inValidDate = inValidDate;		
		this.name = name;		
		this.orderId = orderId;		
		this.orderDate = orderDate;		
		this.state = state;		
		this.image = image;		
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
		
	public void setGiftCouponId(long giftCouponId) {
		this.giftCouponId = giftCouponId;
	}

	public long getGiftCouponId() {
		return giftCouponId;
	}	
		
	public void setExtractDate(Date extractDate) {
		this.extractDate = extractDate;
	}

	public Date getExtractDate() {
		return extractDate;
	}	
		
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Date getValidDate() {
		return validDate;
	}	
		
	public void setInValidDate(Date inValidDate) {
		this.inValidDate = inValidDate;
	}

	public Date getInValidDate() {
		return inValidDate;
	}	
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
		
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderId() {
		return orderId;
	}	
		
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}	
		
}
