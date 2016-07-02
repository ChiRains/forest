package com.qcloud.component.goods.model;

import java.util.Date;
import java.math.BigDecimal;

public class MerchandiseSpecifications {
	
	private long id;		
	
	private long merchandiseId;		
	
	//relaUnifiedMerchandiseId
	private long unifiedMerchandiseId;		
	
	private long attributeId;		
	
	//值
	private String value;		
	
	//维数
	private int dimensionNumber;		

	public MerchandiseSpecifications(){
	
	}

	public MerchandiseSpecifications(long id,long merchandiseId,long unifiedMerchandiseId,long attributeId,String value,int dimensionNumber){
		this.id = id;		
		this.merchandiseId = merchandiseId;		
		this.unifiedMerchandiseId = unifiedMerchandiseId;		
		this.attributeId = attributeId;		
		this.value = value;		
		this.dimensionNumber = dimensionNumber;		
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
		
	public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {
		this.unifiedMerchandiseId = unifiedMerchandiseId;
	}

	public long getUnifiedMerchandiseId() {
		return unifiedMerchandiseId;
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
		
	public void setDimensionNumber(int dimensionNumber) {
		this.dimensionNumber = dimensionNumber;
	}

	public int getDimensionNumber() {
		return dimensionNumber;
	}	
		
}
