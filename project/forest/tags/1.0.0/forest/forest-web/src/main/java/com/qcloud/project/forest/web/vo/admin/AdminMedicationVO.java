package com.qcloud.project.forest.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMedicationVO {
	
	private long id;		
	
	//提醒名称
	private String theme;		
	
	//图片
	private String image;		
	
	//添加药品
	private String medicine;		
	
	//添加对象
	private String objectName;		
	
	//每次用量
	private int dosage;		
	
	//单位
	private String unit;		
	
	//个人备注
	private String desc;		
	
	//是否启用
	private int enable;		
	
	//用户id
	private long userId;		
	
	//提交时间
	private Date recordTime;	
	
	//服药周期
    private String periodTimes;
    
 // 服药点数
    private String       useTimes;

	public AdminMedicationVO(){
	
	}

	public AdminMedicationVO(long id,String theme,String image,String medicine,String objectName,int dosage,String unit,String desc,int enable,long userId,Date recordTime){
		this.id = id;		
		this.theme = theme;		
		this.image = image;		
		this.medicine = medicine;		
		this.objectName = objectName;		
		this.dosage = dosage;		
		this.unit = unit;		
		this.desc = desc;		
		this.enable = enable;		
		this.userId = userId;		
		this.recordTime = recordTime;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getTheme() {
		return theme;
	}	
		
	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}	
		
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public String getMedicine() {
		return medicine;
	}	
		
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getObjectName() {
		return objectName;
	}	
		
	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

	public int getDosage() {
		return dosage;
	}	
		
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnit() {
		return unit;
	}	
		
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
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
		
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public Date getRecordTime() {
		return recordTime;
	}

    
    public String getPeriodTimes() {
    
        return periodTimes;
    }

    
    public void setPeriodTimes(String periodTimes) {
    
        this.periodTimes = periodTimes;
    }

    
    public String getUseTimes() {
    
        return useTimes;
    }

    
    public void setUseTimes(String useTimes) {
    
        this.useTimes = useTimes;
    }	
		
}
