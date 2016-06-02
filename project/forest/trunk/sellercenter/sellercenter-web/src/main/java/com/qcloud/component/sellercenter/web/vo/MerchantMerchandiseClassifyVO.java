package com.qcloud.component.sellercenter.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class MerchantMerchandiseClassifyVO {
	
	private long id;		
	
	private long merchantId;		
	
	private long classifyId;	
	
	private String message;
	
	private long parentId;
	
	private String name;
	
	//树编码
    private String bsid;        
    
    //分类类型
    private long type;      
    
    //图片
    private String image;       
    
    //描述
    private String remark;  
	

	public MerchantMerchandiseClassifyVO(){
	
	}

	public MerchantMerchandiseClassifyVO(long id,long merchantId,long classifyId){
		this.id = id;		
		this.merchantId = merchantId;		
		this.classifyId = classifyId;		
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
		
	public void setClassifyId(long classifyId) {
		this.classifyId = classifyId;
	}

	public long getClassifyId() {
		return classifyId;
	}

    
    public String getMessage() {
    
        return message;
    }

    
    public void setMessage(String message) {
    
        this.message = message;
    }

    
    public long getParentId() {
    
        return parentId;
    }

    
    public void setParentId(long parentId) {
    
        this.parentId = parentId;
    }

    
    public String getName() {
    
        return name;
    }

    
    public void setName(String name) {
    
        this.name = name;
    }

    
    public String getBsid() {
    
        return bsid;
    }

    
    public void setBsid(String bsid) {
    
        this.bsid = bsid;
    }

    
    public long getType() {
    
        return type;
    }

    
    public void setType(long type) {
    
        this.type = type;
    }

    
    public String getImage() {
    
        return image;
    }

    
    public void setImage(String image) {
    
        this.image = image;
    }

    
    public String getRemark() {
    
        return remark;
    }

    
    public void setRemark(String remark) {
    
        this.remark = remark;
    }	
		
}
