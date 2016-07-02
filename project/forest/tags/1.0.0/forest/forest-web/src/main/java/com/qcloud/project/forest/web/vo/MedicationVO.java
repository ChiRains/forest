package com.qcloud.project.forest.web.vo;

import java.util.ArrayList;
import java.util.List;

public class MedicationVO {

    private long          id;

    // 提醒名称
    private String        theme;

    // 图片
    private String        image;
    
    private String imageUid;

    // 添加药品
    private String        medicine;

    // 添加对象
    private String        objectName;

    // 每次用量
    private int           dosage;

    // 单位
    private String        unit;

    // 个人备注
    private String        desc;

    // 提交时间
    private String        recordTime;

    // 是否启用
    private int           enable;

    // 时间周期
    private int           periodType;

    private String        endTime;

    // 服药周期
    private String        periodTimes;

    // 服药点数
    private String        useTimes;

    // 多个服用时间
    private List<String>  takeTimes = new ArrayList<String>();

    private List<Integer> periodTimeList;

    private List<String>  periodNameList;

    public MedicationVO() {

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

    public String getRecordTime() {

        return recordTime;
    }

    public void setRecordTime(String recordTime) {

        this.recordTime = recordTime;
    }

    public int getEnable() {

        return enable;
    }

    public void setEnable(int enable) {

        this.enable = enable;
    }

    public List<String> getTakeTimes() {

        return takeTimes;
    }

    public void setTakeTimes(List<String> takeTimes) {

        this.takeTimes = takeTimes;
    }

    public int getPeriodType() {

        return periodType;
    }

    public void setPeriodType(int periodType) {

        this.periodType = periodType;
    }

    public String getEndTime() {

        return endTime;
    }

    public void setEndTime(String endTime) {

        this.endTime = endTime;
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

    public List<Integer> getPeriodTimeList() {

        return periodTimeList;
    }

    public void setPeriodTimeList(List<Integer> periodTimeList) {

        this.periodTimeList = periodTimeList;
    }

    public List<String> getPeriodNameList() {

        return periodNameList;
    }

    public void setPeriodNameList(List<String> periodNameList) {

        this.periodNameList = periodNameList;
    }

    
    public String getImageUid() {
    
        return imageUid;
    }

    
    public void setImageUid(String imageUid) {
    
        this.imageUid = imageUid;
    }
}
