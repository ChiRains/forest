package com.qcloud.component.commoditycenter.model;

import java.util.Date;
import java.math.BigDecimal;

public class MerchandiseAttribute {
	
	private long id;		
	
	private long attributeId;		
	
	private long merchandiseId;		
	
	//值
	private String value;		

	public MerchandiseAttribute(){
	
	}

	public MerchandiseAttribute(long id,long attributeId,long merchandiseId,String value){
		this.id = id;		
		this.attributeId = attributeId;		
		this.merchandiseId = merchandiseId;		
		this.value = value;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setAttributeId(long attributeId) {
		this.attributeId = attributeId;
	}

	public long getAttributeId() {
		return attributeId;
	}	
		
	public void setMerchandiseId(long merchandiseId) {
		this.merchandiseId = merchandiseId;
	}

	public long getMerchandiseId() {
		return merchandiseId;
	}	
		
	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}	
		
}
