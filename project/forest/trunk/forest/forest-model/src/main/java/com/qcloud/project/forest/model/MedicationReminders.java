package com.qcloud.project.forest.model;

import java.util.Date;
import java.math.BigDecimal;

public class MedicationReminders {
	
	private long id;		
	
	//用户Id
	private long userId;		
	
	//外面用药提醒Id
	private long themeId;		
	
	//药品名
	private String medicineName;		
	
	//药品Id
	private long medicineId;		
	
	//药品剂量
	private int medicineDosage;		
	
	//用药单位
	private String medicineUnit;		
	
	//用药周期
	private String periodTimes;		
	
	//使用时间
	private String useTimes;		
	
	//备注
	private String desc;		
	
	//记录时间
	private Date recordTime;		

	public MedicationReminders(){
	
	}

	public MedicationReminders(long id,long userId,long themeId,String medicineName,long medicineId,int medicineDosage,String medicineUnit,String periodTimes,String useTimes,String desc,Date recordTime){
		this.id = id;		
		this.userId = userId;		
		this.themeId = themeId;		
		this.medicineName = medicineName;		
		this.medicineId = medicineId;		
		this.medicineDosage = medicineDosage;		
		this.medicineUnit = medicineUnit;		
		this.periodTimes = periodTimes;		
		this.useTimes = useTimes;		
		this.desc = desc;		
		this.recordTime = recordTime;		
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
		
	public void setThemeId(long themeId) {
		this.themeId = themeId;
	}

	public long getThemeId() {
		return themeId;
	}	
		
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getMedicineName() {
		return medicineName;
	}	
		
	public void setMedicineId(long medicineId) {
		this.medicineId = medicineId;
	}

	public long getMedicineId() {
		return medicineId;
	}	
		
	public void setMedicineDosage(int medicineDosage) {
		this.medicineDosage = medicineDosage;
	}

	public int getMedicineDosage() {
		return medicineDosage;
	}	
		
	public void setMedicineUnit(String medicineUnit) {
		this.medicineUnit = medicineUnit;
	}

	public String getMedicineUnit() {
		return medicineUnit;
	}	
		
	public void setPeriodTimes(String periodTimes) {
		this.periodTimes = periodTimes;
	}

	public String getPeriodTimes() {
		return periodTimes;
	}	
		
	public void setUseTimes(String useTimes) {
		this.useTimes = useTimes;
	}

	public String getUseTimes() {
		return useTimes;
	}	
		
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}	
		
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public Date getRecordTime() {
		return recordTime;
	}	
		
}
