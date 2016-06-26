package com.qcloud.component.goods.model;

import java.util.Date;
import java.math.BigDecimal;

public class MerchandiseSpecifications {
	
	private long id;		
	
	private long merchandiseId;		
	
	private long attributeId0;		
	
	//值
	private String value0;		
	
	private long attributeId1;		
	
	//值
	private String value1;		
	
	private long attributeId2;		
	
	//值
	private String value2;		
	
	//状态
	private int state;		

	public MerchandiseSpecifications(){
	
	}

	public MerchandiseSpecifications(long id,long merchandiseId,long attributeId0,String value0,long attributeId1,String value1,long attributeId2,String value2,int state){
		this.id = id;		
		this.merchandiseId = merchandiseId;		
		this.attributeId0 = attributeId0;		
		this.value0 = value0;		
		this.attributeId1 = attributeId1;		
		this.value1 = value1;		
		this.attributeId2 = attributeId2;		
		this.value2 = value2;		
		this.state = state;		
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
		
	public void setAttributeId0(long attributeId0) {
		this.attributeId0 = attributeId0;
	}

	public long getAttributeId0() {
		return attributeId0;
	}	
		
	public void setValue0(String value0) {
		this.value0 = value0;
	}

	public String getValue0() {
		return value0;
	}	
		
	public void setAttributeId1(long attributeId1) {
		this.attributeId1 = attributeId1;
	}

	public long getAttributeId1() {
		return attributeId1;
	}	
		
	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue1() {
		return value1;
	}	
		
	public void setAttributeId2(long attributeId2) {
		this.attributeId2 = attributeId2;
	}

	public long getAttributeId2() {
		return attributeId2;
	}	
		
	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue2() {
		return value2;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
}
