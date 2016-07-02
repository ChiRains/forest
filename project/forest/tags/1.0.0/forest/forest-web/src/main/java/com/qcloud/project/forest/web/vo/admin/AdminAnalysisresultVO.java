package com.qcloud.project.forest.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminAnalysisresultVO {
	
	//id
	private long id;		
	
	//唯一编码
	private String code;		
	
	//名称
	private String name;		
	
	//描述
	private String description;		
	
	//类型(1.血压分析，2.计算BMI)
	private int type;		
	
	//开始数据
	private double previousData;		
	
	//后一个数据
	private double afterData;		

	public AdminAnalysisresultVO(){
	
	}

	public AdminAnalysisresultVO(long id,String code,String name,String description,int type,double previousData,double afterData){
		this.id = id;		
		this.code = code;		
		this.name = name;		
		this.description = description;		
		this.type = type;		
		this.previousData = previousData;		
		this.afterData = afterData;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}	
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
		
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}	
		
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}	
		
	public void setPreviousData(double previousData) {
		this.previousData = previousData;
	}

	public double getPreviousData() {
		return previousData;
	}	
		
	public void setAfterData(double afterData) {
		this.afterData = afterData;
	}

	public double getAfterData() {
		return afterData;
	}	
		
}
