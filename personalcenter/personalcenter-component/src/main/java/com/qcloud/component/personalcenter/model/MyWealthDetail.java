package com.qcloud.component.personalcenter.model;

import java.util.Date;
import java.math.BigDecimal;

public class MyWealthDetail {

    // ID
    private long   id;

    // 财富账号
    private long   wealthId;

    // 用户
    private long   userId;

    // 当次财富值
    private double point;

    // 余额
    private double remainder;

    // 发生时间
    private Date   time;

    // 财富描述
    private String desc;

    // 类型1. 积分 2. 佣金 3. 消费币 4. 投资
    private int    type;

    private String image;

    private int    noteKey;

    public MyWealthDetail() {

    }

    public MyWealthDetail(long id, long wealthId, long userId, double point, double remainder, Date time, String desc, int type) {

        this.id = id;
        this.wealthId = wealthId;
        this.userId = userId;
        this.point = point;
        this.remainder = remainder;
        this.time = time;
        this.desc = desc;
        this.type = type;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setWealthId(long wealthId) {

        this.wealthId = wealthId;
    }

    public long getWealthId() {

        return wealthId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public long getUserId() {

        return userId;
    }

    public void setPoint(double point) {

        this.point = point;
    }

    public double getPoint() {

        return point;
    }

    public void setRemainder(double remainder) {

        this.remainder = remainder;
    }

    public double getRemainder() {

        return remainder;
    }

    public void setTime(Date time) {

        this.time = time;
    }

    public Date getTime() {

        return time;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public String getDesc() {

        return desc;
    }

    public void setType(int type) {

        this.type = type;
    }

    public int getType() {

        return type;
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
}
