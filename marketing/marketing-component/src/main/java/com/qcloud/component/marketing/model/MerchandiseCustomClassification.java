package com.qcloud.component.marketing.model;

import java.util.Date;
import java.math.BigDecimal;

public class MerchandiseCustomClassification {
	
	//ID
	private long id;		
	
	//商家ID,商城为1
	private long merchantId;		
	
	//商品ID
	private long unifiedMerchandiseId;		
	
	//排序
	private int orderNum;		
	
	//分类定义
	private long customClassifyId;	
	
	//商品名称
	private String name;

	//系统编号
	private String sysCode;
	
	public MerchandiseCustomClassification(){
	
	}


	
	public MerchandiseCustomClassification(long id, long merchantId, long unifiedMerchandiseId, int orderNum, long customClassifyId, String name, String sysCode) {

        this.id = id;
        this.merchantId = merchantId;
        this.unifiedMerchandiseId = unifiedMerchandiseId;
        this.orderNum = orderNum;
        this.customClassifyId = customClassifyId;
        this.name = name;
        this.sysCode = sysCode;
    }



    public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
	}	
		
	public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {
		this.unifiedMerchandiseId = unifiedMerchandiseId;
	}

	public long getUnifiedMerchandiseId() {
		return unifiedMerchandiseId;
	}	
		
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getOrderNum() {
		return orderNum;
	}	
		
	public void setCustomClassifyId(long customClassifyId) {
		this.customClassifyId = customClassifyId;
	}

	public long getCustomClassifyId() {
		return customClassifyId;
	}

    
    public String getName() {
    
        return name;
    }

    
    public void setName(String name) {
    
        this.name = name;
    }

    
    public String getSysCode() {
    
        return sysCode;
    }

    
    public void setSysCode(String sysCode) {
    
        this.sysCode = sysCode;
    }	
		
}
