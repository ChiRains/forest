package com.qcloud.project.forest.model;

import java.util.Date;
import java.math.BigDecimal;

public class MedicationRemindersTime {
	
	private long id;		
	
	//提醒Id
	private long reminderId;		
	
	//执行时间
	private String excuteTime;		

	public MedicationRemindersTime(){
	
	}

	public MedicationRemindersTime(long id,long reminderId,String excuteTime){
		this.id = id;		
		this.reminderId = reminderId;		
		this.excuteTime = excuteTime;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setReminderId(long reminderId) {
		this.reminderId = reminderId;
	}

	public long getReminderId() {
		return reminderId;
	}	
		
	public void setExcuteTime(String excuteTime) {
		this.excuteTime = excuteTime;
	}

	public String getExcuteTime() {
		return excuteTime;
	}	
		
}
