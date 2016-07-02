package com.qcloud.component.personalcenter.model;

import java.util.Date;
import java.math.BigDecimal;

public class MyBankCard {
	
	//ID
	private long id;		
	
	//用户
	private long userId;		
	
	//银行
	private String bank;		
	
	//卡
	private String card;		
	
	//添加时间
	private Date time;		
	
	private String cardholder;
	
	private String mobile;
	

	public MyBankCard(){
	
	}

	public MyBankCard(long id,long userId,String bank,String card,Date time){
		this.id = id;		
		this.userId = userId;		
		this.bank = bank;		
		this.card = card;		
		this.time = time;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBank() {
		return bank;
	}	
		
	public void setCard(String card) {
		this.card = card;
	}

	public String getCard() {
		return card;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}

    
    public String getCardholder() {
    
        return cardholder;
    }

    
    public void setCardholder(String cardholder) {
    
        this.cardholder = cardholder;
    }

    
    public String getMobile() {
    
        return mobile;
    }

    
    public void setMobile(String mobile) {
    
        this.mobile = mobile;
    }	
		
}
