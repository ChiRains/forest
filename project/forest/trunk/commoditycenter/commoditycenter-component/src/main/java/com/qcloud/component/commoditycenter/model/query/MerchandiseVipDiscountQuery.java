package com.qcloud.component.commoditycenter.model.query;

public class MerchandiseVipDiscountQuery {
	
	private Long userId;
	
	private String companyName;

	public MerchandiseVipDiscountQuery(){
	
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

	
	
}
