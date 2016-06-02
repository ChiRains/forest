package com.qcloud.component.personalcenter.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMyCommissionWithdrawCashVOExport {
	
		
	
	//用户
	private String userId;		
	
	//当次提现值
	private double commissionCash;		
	
	//持卡人
	private String cardholder;		
	
	//银行
	private String bank;		
	
	//卡
	private String card;		
	
	private int state;
	
	private String stateName;
	
	private String account;
	
	private String username;
	
	public AdminMyCommissionWithdrawCashVOExport(){
	
	}

	public AdminMyCommissionWithdrawCashVOExport(long id,long wealthId,long wealthDetailId,String userId,double commissionCash,String cardholder,String bank,String card,Date time,Date completeTime,int state){
	
		this.userId = userId;		
		this.commissionCash = commissionCash;		
		this.cardholder = cardholder;		
		this.bank = bank;		
		this.card = card;		

	}
	
	
		
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}	
		
	public void setCommissionCash(double commissionCash) {
		this.commissionCash = commissionCash;
	}

	public double getCommissionCash() {
		return commissionCash;
	}	
		
	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}

	public String getCardholder() {
		return cardholder;
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
		


    
    public String getStateName() {
    
        return stateName;
    }

    
    public void setStateName(String stateName) {
    
        this.stateName = stateName;
    }

    
    public int getState() {
    
        return state;
    }

    
    public void setState(int state) {
    
        this.state = state;
    }

    
    public String getAccount() {
    
        return account;
    }

    
    public void setAccount(String account) {
    
        this.account = account;
    }

    
    public String getUsername() {
    
        return username;
    }

    
    public void setUsername(String username) {
    
        this.username = username;
    }	
		
}
