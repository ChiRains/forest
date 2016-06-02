package com.qcloud.component.sellercenter.web.vo.admin;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class AdminStoreMemberVO {
	
	//ID
	private long id;		
	
	//memberID
	private long memberId;		
	
	//商家ID
	private long merchantId;		
	
	//storeID
	private long storeId;		
	
	private List<AdminMerchantOrderFormVO> merchantOrderFormVOs;
	
	private double sum;

	public AdminStoreMemberVO(){
	
	}

	public AdminStoreMemberVO(long id,long memberId,long merchantId,long storeId){
		this.id = id;		
		this.memberId = memberId;		
		this.merchantId = merchantId;		
		this.storeId = storeId;		
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
		
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
	}

    
    public List<AdminMerchantOrderFormVO> getMerchantOrderFormVOs() {
    
        return merchantOrderFormVOs;
    }

    
    public void setMerchantOrderFormVOs(List<AdminMerchantOrderFormVO> merchantOrderFormVOs) {
    
        this.merchantOrderFormVOs = merchantOrderFormVOs;
    }

    
    public double getSum() {
    
        return sum;
    }

    
    public void setSum(double sum) {
    
        this.sum = sum;
    }	
		
}
