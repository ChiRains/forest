package com.qcloud.component.goods.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class ClassifySpecificationsVO {
	
	private long id;		
	
	//分类定义
	private long classifyId;		
	
	//属性定义
	private long attributeId;		
	
	//纳入价格计算
	private int calculatePrice;		
	
	//排序
    private int sort;

	public ClassifySpecificationsVO(long id, long classifyId, long attributeId, int calculatePrice, int sort) {

        this.id = id;
        this.classifyId = classifyId;
        this.attributeId = attributeId;
        this.calculatePrice = calculatePrice;
        this.sort = sort;
    }

    public ClassifySpecificationsVO(){
	
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
		
	public void setCalculatePrice(int calculatePrice) {
		this.calculatePrice = calculatePrice;
	}

	public int getCalculatePrice() {
		return calculatePrice;
	}

    
    public int getSort() {
    
        return sort;
    }

    
    public void setSort(int sort) {
    
        this.sort = sort;
    }	
		
}
