package com.qcloud.component.sellercenter.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMerchantMemberVO {
	
	//ID
	private long id;		
	
	//memberID
	private long memberId;		
	
	//merchantID
	private long merchantId;		

	public AdminMerchantMemberVO(){
	
	}

	public AdminMerchantMemberVO(long id,long memberId,long merchantId){
		this.id = id;		
		this.memberId = memberId;		
		this.merchantId = merchantId;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public long getMemberId() {
		return memberId;
	}	
		
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public long getMerchantId() {
		return merchantId;
	}	
		
}
