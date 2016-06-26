package com.qcloud.component.goods.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class MerchandiseSpecificationsRelationVO {
	
	private long id;		
	
	private long merchandiseId;		
	
	private long attributeId;		
	
	private String value;		
	
	private String alias;		
	
	private int type;		
	
	private String oldAlias;		
	
	//0否1是
	private int isCheck;		

	public MerchandiseSpecificationsRelationVO(){
	
	}

	public MerchandiseSpecificationsRelationVO(long id,long merchandiseId,long attributeId,String value,String alias,int type,String oldAlias,int isCheck){
		this.id = id;		
		this.merchandiseId = merchandiseId;		
		this.attributeId = attributeId;		
		this.value = value;		
		this.alias = alias;		
		this.type = type;		
		this.oldAlias = oldAlias;		
		this.isCheck = isCheck;		
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
		
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}	
		
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}	
		
	public void setOldAlias(String oldAlias) {
		this.oldAlias = oldAlias;
	}

	public String getOldAlias() {
		return oldAlias;
	}	
		
	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}

	public int getIsCheck() {
		return isCheck;
	}	
		
}
