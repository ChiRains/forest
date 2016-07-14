package com.qcloud.project.forest.model;

import java.util.Date;

public class ExpressQueryHistory {

    private long   id;

    // 快递公司名称
    private String expressName;

    private String expressCode;

    // 快递单号
    private String expressNum;

    private long   userId;

    // 查询时间
    private Date   time;

    public ExpressQueryHistory() {

    }

    public ExpressQueryHistory(long id, String expressName, String expressCode, String expressNum, long userId, Date time) {

        super();
        this.id = id;
        this.expressName = expressName;
        this.expressCode = expressCode;
        this.expressNum = expressNum;
        this.userId = userId;
        this.time = time;
    }

    public String getExpressCode() {

        return expressCode;
    }

    public void setExpressCode(String expressCode) {

        this.expressCode = expressCode;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getExpressName() {

        return expressName;
    }

    public void setExpressName(String expressName) {

        this.expressName = expressName;
    }

    public String getExpressNum() {

        return expressNum;
    }

    public void setExpressNum(String expressNum) {

        this.expressNum = expressNum;
    }

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public Date getTime() {

        return time;
    }

    public void setTime(Date time) {

        this.time = time;
    }
}
