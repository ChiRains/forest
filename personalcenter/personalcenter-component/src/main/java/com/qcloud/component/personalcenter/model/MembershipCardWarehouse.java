package com.qcloud.component.personalcenter.model;

import java.util.Date;
import java.math.BigDecimal;

public class MembershipCardWarehouse {
	
	//ID
	private long id;		
	
	//卡号
	private String cardNumber;		
	
	//状态 1初始化 2已使用
	private int state;		

	public MembershipCardWarehouse(){
	
	}

	public MembershipCardWarehouse(long id,String cardNumber,int state){
		this.id = id;		
		this.cardNumber = cardNumber;		
		this.state = state;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
}
