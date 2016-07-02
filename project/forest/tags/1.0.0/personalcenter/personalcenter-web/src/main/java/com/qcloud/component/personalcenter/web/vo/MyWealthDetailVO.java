package com.qcloud.component.personalcenter.web.vo;

public class MyWealthDetailVO {

    // 当次财富值
    private double point;

    // 发生时间
    private String timeStr;

    // 财富描述
    private String desc;

    // 余额
    private double remainder;

    private String image;

    private int    noteKey;

    private String noteStr;

    public MyWealthDetailVO() {

    }

    public void setPoint(double point) {

        this.point = point;
    }

    public double getPoint() {

        return point;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public String getDesc() {

        return desc;
    }

    public String getTimeStr() {

        return timeStr;
    }

    public void setTimeStr(String timeStr) {

        this.timeStr = timeStr;
    }

    public double getRemainder() {

        return remainder;
    }

    public void setRemainder(double remainder) {

        this.remainder = remainder;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public int getNoteKey() {

        return noteKey;
    }

    public void setNoteKey(int noteKey) {

        this.noteKey = noteKey;
    }

    public String getNoteStr() {

        return noteStr;
    }

    public void setNoteStr(String noteStr) {

        this.noteStr = noteStr;
    }
}
