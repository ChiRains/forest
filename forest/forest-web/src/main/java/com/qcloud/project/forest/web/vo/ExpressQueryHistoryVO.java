package com.qcloud.project.forest.web.vo;

public class ExpressQueryHistoryVO {

    // 快递公司名称
    private String expressName;

    // 快递单号
    private String expressNum;

    // 查询时间
    private String time;

    private String expressCode;

    public ExpressQueryHistoryVO() {

    }

    public ExpressQueryHistoryVO(String expressName, String expressNum, String time, String expressCode) {

        super();
        this.expressName = expressName;
        this.expressNum = expressNum;
        this.time = time;
        this.expressCode = expressCode;
    }

    public String getExpressCode() {

        return expressCode;
    }

    public void setExpressCode(String expressCode) {

        this.expressCode = expressCode;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {

        this.time = time;
    }

    public void setExpressName(String expressName) {

        this.expressName = expressName;
    }

    public String getExpressName() {

        return expressName;
    }

    public void setExpressNum(String expressNum) {

        this.expressNum = expressNum;
    }

    public String getExpressNum() {

        return expressNum;
    }
}
