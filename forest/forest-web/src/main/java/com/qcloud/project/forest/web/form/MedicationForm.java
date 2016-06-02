package com.qcloud.project.forest.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicationForm {

    private long          id;

    // 提醒名称
    private String        theme;

    // 图片
    private String        image;

    // 添加药品
    private String        medicine;

    // 添加对象
    private String        objectName;

    // 服用时间
    private String        takeTime;

    // 每次用量
    private int           dosage;

    // 单位
    private String        unit;

    // 服药周期
    private List<Integer> periodType;

    // 个人备注
    private String        desc;

    // 截止时间
    private Date          endTime;

    private List<String>  takeTimes = new ArrayList<String>();

    public MedicationForm() {

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

    public void setTakeTime(String takeTime) {

        this.takeTime = takeTime;
    }

    public String getTakeTime() {

        return takeTime;
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

    public List<String> getTakeTimes() {

        return takeTimes;
    }

    public void setTakeTimes(List<String> takeTimes) {

        this.takeTimes = takeTimes;
    }

    public Date getEndTime() {

        return endTime;
    }

    public void setEndTime(Date endTime) {

        this.endTime = endTime;
    }

    public List<Integer> getPeriodType() {

        return periodType;
    }

    public void setPeriodType(List<Integer> periodType) {

        this.periodType = periodType;
    }
}
