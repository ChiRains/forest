package com.qcloud.component.seckill.model;

import java.util.Date;
import java.math.BigDecimal;

public class ScreeningsSlide {
	
	//ID
	private long id;		
	
	//场次
	private long screeningsId;		
	
	//点击地址
	private String clickUrl;		
	
	//图片
	private String image;		
	
	//排序
	private int orderNum;		

	public ScreeningsSlide(){
	
	}

	public ScreeningsSlide(long id,long screeningsId,String clickUrl,String image,int orderNum){
		this.id = id;		
		this.screeningsId = screeningsId;		
		this.clickUrl = clickUrl;		
		this.image = image;		
		this.orderNum = orderNum;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setScreeningsId(long screeningsId) {
		this.screeningsId = screeningsId;
	}

	public long getScreeningsId() {
		return screeningsId;
	}	
		
	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public String getClickUrl() {
		return clickUrl;
	}	
		
	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}	
		
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getOrderNum() {
		return orderNum;
	}	
		
}
