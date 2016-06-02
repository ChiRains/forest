package com.qcloud.component.personalcenter.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMyWealthVO {
	
	//ID
	private long id;		
	
	//用户
	private long userId;		
	
	//积分
	private long integral;		
	
	//佣金
	private double commission;		
	
	//消费币
	private double consumptionCurrency;		
	
	//投资
	private double investment;		
	
	//最后更新时间
	private Date time;		
	
	private String userName;


	public AdminMyWealthVO(){
	
	}

	public AdminMyWealthVO(long id,long userId,long integral,double commission,double consumptionCurrency,double investment,Date time){
		this.id = id;		
		this.userId = userId;		
		this.integral = integral;		
		this.commission = commission;		
		this.consumptionCurrency = consumptionCurrency;		
		this.investment = investment;		
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
		
	public void setIntegral(long integral) {
		this.integral = integral;
	}

	public long getIntegral() {
		return integral;
	}	
		
	public void setCommission(double commission) {
		this.commission = commission;
	}

	public double getCommission() {
		return commission;
	}	
		
	public void setConsumptionCurrency(double consumptionCurrency) {
		this.consumptionCurrency = consumptionCurrency;
	}

	public double getConsumptionCurrency() {
		return consumptionCurrency;
	}	
		
	public void setInvestment(double investment) {
		this.investment = investment;
	}

	public double getInvestment() {
		return investment;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}

    
    public String getUserName() {
    
        return userName;
    }

    
    public void setUserName(String userName) {
    
        this.userName = userName;
    }

}
