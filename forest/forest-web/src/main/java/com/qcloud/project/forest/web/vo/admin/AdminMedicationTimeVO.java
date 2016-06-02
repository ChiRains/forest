package com.qcloud.project.forest.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMedicationTimeVO {
	
	private long id;		
	
	//用药id
	private long medicationId;		
	
	//服用时间
	private String takeTime;		
	
	//是否启用
	private int enable;		
	
	//用户id
	private long userId;		
	
	//下次执行时间
	private Date excuteTime;		
	
	//提交时间
	private Date recordTime;		
	
	//截止时间
	private Date endTime;		
	
	//服药周期
	private int periodType;		

	public AdminMedicationTimeVO(){
	
	}

	public AdminMedicationTimeVO(long id,long medicationId,String takeTime,int enable,long userId,Date excuteTime,Date recordTime,Date endTime,int periodType){
		this.id = id;		
		this.medicationId = medicationId;		
		this.takeTime = takeTime;		
		this.enable = enable;		
		this.userId = userId;		
		this.excuteTime = excuteTime;		
		this.recordTime = recordTime;		
		this.endTime = endTime;		
		this.periodType = periodType;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setMedicationId(long medicationId) {
		this.medicationId = medicationId;
	}

	public long getMedicationId() {
		return medicationId;
	}	
		
	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}

	public String getTakeTime() {
		return takeTime;
	}	
		
	public void setEnable(int enable) {
		this.enable = enable;
	}

	public int getEnable() {
		return enable;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setExcuteTime(Date excuteTime) {
		this.excuteTime = excuteTime;
	}

	public Date getExcuteTime() {
		return excuteTime;
	}	
		
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public Date getRecordTime() {
		return recordTime;
	}	
		
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
	}	
		
	public void setPeriodType(int periodType) {
		this.periodType = periodType;
	}

	public int getPeriodType() {
		return periodType;
	}	
		
}
