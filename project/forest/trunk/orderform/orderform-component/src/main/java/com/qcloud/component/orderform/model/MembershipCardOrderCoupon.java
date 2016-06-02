package com.qcloud.component.orderform.model;

import java.util.Date;
import java.math.BigDecimal;

public class MembershipCardOrderCoupon {
	
	//ID
	private long id;		
	
	//订单ID
	private long orderId;		
	
	//优惠劵
	private long myCouponId;		
	
	//优惠面额
	private double coupon;		

	public MembershipCardOrderCoupon(){
	
	}

	public MembershipCardOrderCoupon(long id,long orderId,long myCouponId,double coupon){
		this.id = id;		
		this.orderId = orderId;		
		this.myCouponId = myCouponId;		
		this.coupon = coupon;		
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
		
	public void setMyCouponId(long myCouponId) {
		this.myCouponId = myCouponId;
	}

	public long getMyCouponId() {
		return myCouponId;
	}	
		
	public void setCoupon(double coupon) {
		this.coupon = coupon;
	}

	public double getCoupon() {
		return coupon;
	}	
		
}
