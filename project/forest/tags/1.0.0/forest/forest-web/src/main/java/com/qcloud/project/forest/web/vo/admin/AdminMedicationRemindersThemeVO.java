package com.qcloud.project.forest.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMedicationRemindersThemeVO {
	
	private long id;		
	
	//名称
	private String name;		
	
	//图片
	private String image;		
	
	//登记用户
	private long userId;		
	
	//是否启用（1启用；0不启用）
	private int enable;		
	
	//记录时间
	private Date recordTime;		

	public AdminMedicationRemindersThemeVO(){
	
	}

	public AdminMedicationRemindersThemeVO(long id,String name,String image,long userId,int enable,Date recordTime){
		this.id = id;		
		this.name = name;		
		this.image = image;		
		this.userId = userId;		
		this.enable = enable;		
		this.recordTime = recordTime;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
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
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setEnable(int enable) {
		this.enable = enable;
	}

	public int getEnable() {
		return enable;
	}	
		
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public Date getRecordTime() {
		return recordTime;
	}	
		
}
