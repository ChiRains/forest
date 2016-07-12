package com.qcloud.project.forest.model.query;

public class ExpressQueryHistoryQuery {

    private long   userId;

    private String expressNum;

    public ExpressQueryHistoryQuery() {

    }

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public String getExpressNum() {

        return expressNum;
    }

    public void setExpressNum(String expressNum) {

        this.expressNum = expressNum;
    }
}
