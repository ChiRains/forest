package com.qcloud.component.my.model;

import java.util.Date;
import java.math.BigDecimal;

public class Consignee {
	
	//ID
	private long id;		
	
	//用户
	private long userId;		
	
	//姓名
	private String name;		
	
	//省份
	private String province;		
	
	//市
	private String city;		
	
	//区
	private String district;		
	
	//地址
	private String address;		
	
	//电话
	private String mobile;		
	
	//电子邮箱
	private String email;		
	
	//邮编
	private String zipCode;		
	
	//默认
	private int acquiesce;		

	public Consignee(){
	
	}

	public Consignee(long id,long userId,String name,String province,String city,String district,String address,String mobile,String email,String zipCode,int acquiesce){
		this.id = id;		
		this.userId = userId;		
		this.name = name;		
		this.province = province;		
		this.city = city;		
		this.district = district;		
		this.address = address;		
		this.mobile = mobile;		
		this.email = email;		
		this.zipCode = zipCode;		
		this.acquiesce = acquiesce;		
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
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
		
	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvince() {
		return province;
	}	
		
	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}	
		
	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDistrict() {
		return district;
	}	
		
	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}	
		
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}	
		
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}	
		
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getZipCode() {
		return zipCode;
	}	
		
	public void setAcquiesce(int acquiesce) {
		this.acquiesce = acquiesce;
	}

	public int getAcquiesce() {
		return acquiesce;
	}	
		
}
