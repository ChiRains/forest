package com.qcloud.component.seckill.model;

import java.util.Date;
import java.math.BigDecimal;

public class Screenings {
	
	private long id;		
	
	private Date beginTime;		
	
	private Date endTime;		
	
	//1可用 2不可用
	private int enable;	

	public Screenings(){
	
	}

	public Screenings(long id,Date beginTime,Date endTime,int enable){
		this.id = id;		
		this.beginTime = beginTime;		
		this.endTime = endTime;		
		this.enable = enable;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}	
		
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}	
		
}
