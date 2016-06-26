package com.qcloud.component.goods.model;

import java.util.Date;
import java.math.BigDecimal;

public class Enumeration {
	
	private long id;		
	
	//名称
	private String name;		
	
	//值
	private String value;		

	public Enumeration(){
	
	}

	public Enumeration(long id,String name,String value){
		this.id = id;		
		this.name = name;		
		this.value = value;		
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
		
	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}	
		
}
