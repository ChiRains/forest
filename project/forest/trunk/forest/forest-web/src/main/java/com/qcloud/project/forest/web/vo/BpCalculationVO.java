package com.qcloud.project.forest.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class BpCalculationVO {
	
	private long id;		
	
	//名称
	private String name;		
	
	//描述
	private String description;		
	
	//搜索呀最大值
	private int dbpMax;		
	
	//收缩压最小值
	private int dbpMin;		
	
	//舒张压最大值
	private int sdpMax;		
	
	//舒张压最小值
	private int sdpMin;		

	public BpCalculationVO(){
	
	}

	public BpCalculationVO(long id,String name,String description,int dbpMax,int dbpMin,int sdpMax,int sdpMin){
		this.id = id;		
		this.name = name;		
		this.description = description;		
		this.dbpMax = dbpMax;		
		this.dbpMin = dbpMin;		
		this.sdpMax = sdpMax;		
		this.sdpMin = sdpMin;		
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
		
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}	
		
	public void setDbpMax(int dbpMax) {
		this.dbpMax = dbpMax;
	}

	public int getDbpMax() {
		return dbpMax;
	}	
		
	public void setDbpMin(int dbpMin) {
		this.dbpMin = dbpMin;
	}

	public int getDbpMin() {
		return dbpMin;
	}	
		
	public void setSdpMax(int sdpMax) {
		this.sdpMax = sdpMax;
	}

	public int getSdpMax() {
		return sdpMax;
	}	
		
	public void setSdpMin(int sdpMin) {
		this.sdpMin = sdpMin;
	}

	public int getSdpMin() {
		return sdpMin;
	}	
		
}
