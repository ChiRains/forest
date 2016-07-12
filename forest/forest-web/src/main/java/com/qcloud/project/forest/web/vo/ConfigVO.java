package com.qcloud.project.forest.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class ConfigVO {
	
	private long id;		
	
	//编码标识
	private String code;		
	
	//配置信息
	private String config;		
	
	//备注
	private String remark;		
	
	//分类
	private int type;		

	public ConfigVO(){
	
	}

	public ConfigVO(long id,String code,String config,String remark,int type){
		this.id = id;		
		this.code = code;		
		this.config = config;		
		this.remark = remark;		
		this.type = type;		
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
		
	public void setConfig(String config) {
		this.config = config;
	}

	public String getConfig() {
		return config;
	}	
		
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}	
		
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}	
		
}
