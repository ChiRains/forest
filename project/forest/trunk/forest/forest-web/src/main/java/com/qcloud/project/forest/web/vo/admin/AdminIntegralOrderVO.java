package com.qcloud.project.forest.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminIntegralOrderVO {
	
	private long id;		
	
	private long orderNumber;		
	
	private Date time;		
	
	private long userId;		
	
	private double sum;		
	
	private double cash;		
	
	private int integral;		
	
	private long unfiedMerchandiseId;		
	
	private String name;		
	
	private String image;		
	
	private String specifications;		
	
	//10待发货20待收货30已完成
	private int state;		
	
	private int paymentMode;		
	
	private String consignee;		
	
	private String address;		
	
	private String email;		
	
	private String mobile;		
	
	//0没催促1已催促
	private int remind;		

	public AdminIntegralOrderVO(){
	
	}

	public AdminIntegralOrderVO(long id,long orderNumber,Date time,long userId,double sum,double cash,int integral,long unfiedMerchandiseId,String name,String image,String specifications,int state,int paymentMode,String consignee,String address,String email,String mobile,int remind){
		this.id = id;		
		this.orderNumber = orderNumber;		
		this.time = time;		
		this.userId = userId;		
		this.sum = sum;		
		this.cash = cash;		
		this.integral = integral;		
		this.unfiedMerchandiseId = unfiedMerchandiseId;		
		this.name = name;		
		this.image = image;		
		this.specifications = specifications;		
		this.state = state;		
		this.paymentMode = paymentMode;		
		this.consignee = consignee;		
		this.address = address;		
		this.email = email;		
		this.mobile = mobile;		
		this.remind = remind;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public long getOrderNumber() {
		return orderNumber;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
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
		
	public void setUnfiedMerchandiseId(long unfiedMerchandiseId) {
		this.unfiedMerchandiseId = unfiedMerchandiseId;
	}

	public long getUnfiedMerchandiseId() {
		return unfiedMerchandiseId;
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
		
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getSpecifications() {
		return specifications;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
	public void setPaymentMode(int paymentMode) {
		this.paymentMode = paymentMode;
	}

	public int getPaymentMode() {
		return paymentMode;
	}	
		
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsignee() {
		return consignee;
	}	
		
	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}	
		
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}	
		
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}	
		
	public void setRemind(int remind) {
		this.remind = remind;
	}

	public int getRemind() {
		return remind;
	}	
		
}
