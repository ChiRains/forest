package com.qcloud.component.marketing.model.query;

public class CouponItemsQuery {
    private Long couponID;
	public CouponItemsQuery(){
	
	}
    
    public Long getCouponID() {
    
        return couponID;
    }
    
    public void setCouponID(Long couponID) {
    
        this.couponID = couponID;
    }

    public CouponItemsQuery(Long couponID) {
        this.couponID = couponID;
    }
    
}
