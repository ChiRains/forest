package com.qcloud.component.goods.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class MerchandiseImageVO {
	
	//ID
	private long id;		
	
	//商品ID
	private long merchandiseId;		
	
	private long attributeId;		
	
	//值
	private String value;		
	
	//图片
	private String image;		

	public MerchandiseImageVO(){
	
	}

	public MerchandiseImageVO(long id,long merchandiseId,long attributeId,String value,String image){
		this.id = id;		
		this.merchandiseId = merchandiseId;		
		this.attributeId = attributeId;		
		this.value = value;		
		this.image = image;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setMerchandiseId(long merchandiseId) {
		this.merchandiseId = merchandiseId;
	}

	public long getMerchandiseId() {
		return merchandiseId;
	}	
		
	public void setAttributeId(long attributeId) {
		this.attributeId = attributeId;
	}

	public long getAttributeId() {
		return attributeId;
	}	
		
	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}	
		
	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}	
		
}
