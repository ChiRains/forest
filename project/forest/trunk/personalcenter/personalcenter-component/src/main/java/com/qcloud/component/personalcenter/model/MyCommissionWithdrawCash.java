package com.qcloud.component.personalcenter.model;

import java.util.Date;
import java.math.BigDecimal;

public class MyCommissionWithdrawCash {
	
	//ID
	private long id;		
	
	//财富账号
	private long wealthId;		
	
	//财富明细
	private long wealthDetailId;		
	
	//用户
	private long userId;		
	
	//当次提现值
	private double commissionCash;		
	
	//持卡人
	private String cardholder;		
	
	//银行
	private String bank;		
	
	//卡
	private String card;		
	
	//发生时间
	private Date time;		
	
	//完成时间
	private Date completeTime;		
	
	//类型1. 申请 2.审核  3.成功
	private int state;		

	public MyCommissionWithdrawCash(){
	
	}

	public MyCommissionWithdrawCash(long id,long wealthId,long wealthDetailId,long userId,double commissionCash,String cardholder,String bank,String card,Date time,Date completeTime,int state){
		this.id = id;		
		this.wealthId = wealthId;		
		this.wealthDetailId = wealthDetailId;		
		this.userId = userId;		
		this.commissionCash = commissionCash;		
		this.cardholder = cardholder;		
		this.bank = bank;		
		this.card = card;		
		this.time = time;		
		this.completeTime = completeTime;		
		this.state = state;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setWealthId(long wealthId) {
		this.wealthId = wealthId;
	}

	public long getWealthId() {
		return wealthId;
	}	
		
	public void setWealthDetailId(long wealthDetailId) {
		this.wealthDetailId = wealthDetailId;
	}

	public long getWealthDetailId() {
		return wealthDetailId;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
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
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
}
