package com.qcloud.project.forest.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class GiftCouponVO {
	
	private long id;		
	
	//图片
	private String image;		
	
	//赠品券名称
	private String name;		
	
	//生效日期
	private Date validDate;		
	
	//失效日期
	private Date inValidDate;		
	
	//是否启用（0，不启用；1，启用）
	private int enable;		
	
	//赠品券描述
	private String desc;		

	public GiftCouponVO(){
	
	}

	public GiftCouponVO(long id,String image,String name,Date validDate,Date inValidDate,int enable,String desc){
		this.id = id;		
		this.image = image;		
		this.name = name;		
		this.validDate = validDate;		
		this.inValidDate = inValidDate;		
		this.enable = enable;		
		this.desc = desc;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
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
		
	public void setEnable(int enable) {
		this.enable = enable;
	}

	public int getEnable() {
		return enable;
	}	
		
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}	
		
}
