package com.qcloud.component.marketing.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminRecentDiscountVO {
	
	private long id;		
	
	private long merchantId;		
	
	private String name;		
	
	private String image;		
	
	private Date startDate;		
	
	private Date endDate;		
	
	private int enable;		
	
	private String imageUid;

	public AdminRecentDiscountVO(){
	
	}

	public AdminRecentDiscountVO(long id,long merchantId,String name,String image,Date startDate,Date endDate,int enable){
		this.id = id;		
		this.merchantId = merchantId;		
		this.name = name;		
		this.image = image;		
		this.startDate = startDate;		
		this.endDate = endDate;		
		this.enable = enable;		
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
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
		
	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}	
		
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return startDate;
	}	
		
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}	
		
	public void setEnable(int enable) {
		this.enable = enable;
	}

	public int getEnable() {
		return enable;
	}

    
    public String getImageUid() {
    
        return imageUid;
    }

    
    public void setImageUid(String imageUid) {
    
        this.imageUid = imageUid;
    }	
		
}
