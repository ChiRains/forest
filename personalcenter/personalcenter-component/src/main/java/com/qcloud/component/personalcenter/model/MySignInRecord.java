package com.qcloud.component.personalcenter.model;

import java.util.Date;
import java.math.BigDecimal;

public class MySignInRecord {
	
	private long id;		
	
	//用户ID
	private long userId;		
	
	//积分
	private double integral;		
	
	private Date signtime;		

	public MySignInRecord(){
	
	}

	public MySignInRecord(long id,long userId,double integral,Date signtime){
		this.id = id;		
		this.userId = userId;		
		this.integral = integral;		
		this.signtime = signtime;		
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
		
	public void setIntegral(double integral) {
		this.integral = integral;
	}

	public double getIntegral() {
		return integral;
	}	
		
	public void setSigntime(Date signtime) {
		this.signtime = signtime;
	}

	public Date getSigntime() {
		return signtime;
	}	
		
}
