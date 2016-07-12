package com.qcloud.project.forest.model;

import java.util.Date;
import java.math.BigDecimal;

public class PromotionalOffers {
	
	private long id;		
	
	//商品类别ID
	private long classify;		
	
	//商品图片
	private String image;		
	
	//商品名称
	private String name;		
	
	//商品价格
	private double price;		
	
	//时间
	private Date time;		

	public PromotionalOffers(){
	
	}

	public PromotionalOffers(long id,long classify,String image,String name,double price,Date time){
		this.id = id;		
		this.classify = classify;		
		this.image = image;		
		this.name = name;		
		this.price = price;		
		this.time = time;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setClassify(long classify) {
		this.classify = classify;
	}

	public long getClassify() {
		return classify;
	}	
		
	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}	
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
		
	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
}
