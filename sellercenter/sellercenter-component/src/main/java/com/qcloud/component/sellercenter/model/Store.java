//package com.qcloud.component.sellercenter.model;
//
//import java.util.Date;
//import java.math.BigDecimal;
//import com.qcloud.component.publicdata.util.TreeUtils.TreeModel;
//
//public class Store implements TreeModel{
//	
//	//ID
//	private long id;		
//	
//	private long parentId;		
//	
//	//树编码
//	private String bsid;		
//	
//	//商家ID
//	private long merchantId;		
//	
//	//名称
//	private String name;		
//	
//	//省份
//	private String province;		
//	
//	//市
//	private String city;		
//	
//	//区
//	private String district;		
//	
//	//地址
//	private String address;		
//	
//	//经度
//	private double longitude;		
//	
//	//纬度
//	private double latitude;		
//	
//	//LOGO
//	private String logo;		
//	
//	//电话
//	private String phone;		
//	
//	//商家手机
//	private String mobile;		
//	
//	//是否启用 (0：禁用  1：启用)
//	private int enable;		
//
//	public Store(){
//	
//	}
//
//	public Store(long id,long parentId,String bsid,long merchantId,String name,String province,String city,String district,String address,double longitude,double latitude,String logo,String phone,String mobile,int enable){
//		this.id = id;		
//		this.parentId = parentId;		
//		this.bsid = bsid;		
//		this.merchantId = merchantId;		
//		this.name = name;		
//		this.province = province;		
//		this.city = city;		
//		this.district = district;		
//		this.address = address;		
//		this.longitude = longitude;		
//		this.latitude = latitude;		
//		this.logo = logo;		
//		this.phone = phone;		
//		this.mobile = mobile;		
//		this.enable = enable;		
//	}
//	
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public long getId() {
//		return id;
//	}	
//		
//	public void setParentId(long parentId) {
//		this.parentId = parentId;
//	}
//
//	public long getParentId() {
//		return parentId;
//	}	
//		
//	public void setBsid(String bsid) {
//		this.bsid = bsid;
//	}
//
//	public String getBsid() {
//		return bsid;
//	}	
//		
//	public void setMerchantId(long merchantId) {
//		this.merchantId = merchantId;
//	}
//
//	public long getMerchantId() {
//		return merchantId;
//	}	
//		
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getName() {
//		return name;
//	}	
//		
//	public void setProvince(String province) {
//		this.province = province;
//	}
//
//	public String getProvince() {
//		return province;
//	}	
//		
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	public String getCity() {
//		return city;
//	}	
//		
//	public void setDistrict(String district) {
//		this.district = district;
//	}
//
//	public String getDistrict() {
//		return district;
//	}	
//		
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public String getAddress() {
//		return address;
//	}	
//		
//	public void setLongitude(double longitude) {
//		this.longitude = longitude;
//	}
//
//	public double getLongitude() {
//		return longitude;
//	}	
//		
//	public void setLatitude(double latitude) {
//		this.latitude = latitude;
//	}
//
//	public double getLatitude() {
//		return latitude;
//	}	
//		
//	public void setLogo(String logo) {
//		this.logo = logo;
//	}
//
//	public String getLogo() {
//		return logo;
//	}	
//		
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//
//	public String getPhone() {
//		return phone;
//	}	
//		
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}
//
//	public String getMobile() {
//		return mobile;
//	}	
//		
//	public void setEnable(int enable) {
//		this.enable = enable;
//	}
//
//	public int getEnable() {
//		return enable;
//	}	
//		
//}
