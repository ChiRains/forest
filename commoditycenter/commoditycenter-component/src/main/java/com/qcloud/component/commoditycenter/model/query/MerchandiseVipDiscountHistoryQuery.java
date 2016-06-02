package com.qcloud.component.commoditycenter.model.query;

public class MerchandiseVipDiscountHistoryQuery {
	
	private Long userId;
	
	private String companyName;
	
	private long merchandiseItemId;	
	
	public MerchandiseVipDiscountHistoryQuery(){
	
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public long getMerchandiseItemId() {
		return merchandiseItemId;
	}

	public void setMerchandiseItemId(long merchandiseItemId) {
		this.merchandiseItemId = merchandiseItemId;
	}
	
	
}
