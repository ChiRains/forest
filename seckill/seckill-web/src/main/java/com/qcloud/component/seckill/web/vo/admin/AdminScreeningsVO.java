package com.qcloud.component.seckill.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminScreeningsVO {
	
	private long id;		
	
	private Date beginTime;		
	
	private Date endTime;		

	public AdminScreeningsVO(){
	
	}

	public AdminScreeningsVO(long id,Date beginTime,Date endTime){
		this.id = id;		
		this.beginTime = beginTime;		
		this.endTime = endTime;		
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
		
}
