package com.qcloud.project.forest.model.query;

public class IntegralOrderQuery {

    private long   orderNumber;

    private String keyword;

    public IntegralOrderQuery() {

    }

    public long getOrderNumber() {

        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {

        this.orderNumber = orderNumber;
    }

    public String getKeyword() {

        return keyword;
    }

    public void setKeyword(String keyword) {

        this.keyword = keyword;
    }
}
