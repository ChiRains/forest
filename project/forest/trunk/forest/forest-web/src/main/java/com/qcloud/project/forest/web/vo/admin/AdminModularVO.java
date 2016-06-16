package com.qcloud.project.forest.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminModularVO {
	
	private long id;		
	
	//模块名称
	private String name;		
	
	//模块编码
	private String code;		
	
	//是否启用
	private int enable;		

	public AdminModularVO(){
	
	}

	public AdminModularVO(long id,String name,String code,int enable){
		this.id = id;		
		this.name = name;		
		this.code = code;		
		this.enable = enable;		
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
		
	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}	
		
	public void setEnable(int enable) {
		this.enable = enable;
	}

	public int getEnable() {
		return enable;
	}	
		
}
