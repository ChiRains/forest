package com.qcloud.component.goods.model;

import java.util.Date;
import java.math.BigDecimal;

public class ClassifyAttribute {
	
	private long id;		
	
	//分类定义
	private long classifyId;		
	
	//属性定义
	private long attributeId;		
	
	//排序
	private int sort;		

	public ClassifyAttribute(){
	
	}

	public ClassifyAttribute(long id,long classifyId,long attributeId,int sort){
		this.id = id;		
		this.classifyId = classifyId;		
		this.attributeId = attributeId;		
		this.sort = sort;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setClassifyId(long classifyId) {
		this.classifyId = classifyId;
	}

	public long getClassifyId() {
		return classifyId;
	}	
		
	public void setAttributeId(long attributeId) {
		this.attributeId = attributeId;
	}

	public long getAttributeId() {
		return attributeId;
	}	
		
	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getSort() {
		return sort;
	}	
		
}
