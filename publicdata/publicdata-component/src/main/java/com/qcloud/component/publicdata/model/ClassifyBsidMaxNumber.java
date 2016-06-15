package com.qcloud.component.publicdata.model;

import java.util.Date;
import java.math.BigDecimal;

public class ClassifyBsidMaxNumber {
	
	//ID
	private long id;		
	
	//父分类
	private long parentClassifyId;		
	
	//分类类型
	private long type;		
	
	private int maxNumber;		

	public ClassifyBsidMaxNumber(){
	
	}

	public ClassifyBsidMaxNumber(long id,long parentClassifyId,long type,int maxNumber){
		this.id = id;		
		this.parentClassifyId = parentClassifyId;		
		this.type = type;		
		this.maxNumber = maxNumber;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setParentClassifyId(long parentClassifyId) {
		this.parentClassifyId = parentClassifyId;
	}

	public long getParentClassifyId() {
		return parentClassifyId;
	}	
		
	public void setType(long type) {
		this.type = type;
	}

	public long getType() {
		return type;
	}	
		
	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}

	public int getMaxNumber() {
		return maxNumber;
	}	
		
}
