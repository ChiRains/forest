package com.qcloud.component.commoditycenter.model;

import java.util.Date;
import java.math.BigDecimal;

public class MerchandiseSpecificationsRelation {
	
	private long id;		
	
	private long merchandiseId;		
	
	private long attributeId;		
	
	private String value;		
	
	private String alias;
	
	private int type ;
	
	private String oldAlias;
	
	private int isCheck;
	
	

	public MerchandiseSpecificationsRelation(){
	
	}

	public MerchandiseSpecificationsRelation(long id,long merchandiseId,long attributeId,String value){
		this.id = id;		
		this.merchandiseId = merchandiseId;		
		this.attributeId = attributeId;		
		this.value = value;		
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

    
    public String getAlias() {
        if(type == 1){
            return value;
        }else{
            return alias;
        }       
    }

    
    public void setAlias(String alias) {
    
        this.alias = alias;
    }

    
    public int getType() {
    
        return type;
    }

    
    public void setType(int type) {
    
        this.type = type;
    }

    
    public String getOldAlias() {
    
        return oldAlias;
    }

    
    public void setOldAlias(String oldAlias) {
    
        this.oldAlias = oldAlias;
    }

    
    public int getIsCheck() {
    
        return isCheck;
    }

    
    public void setIsCheck(int isCheck) {
    
        this.isCheck = isCheck;
    }

    
   
}
