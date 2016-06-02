package com.qcloud.component.my.web.vo;

public class MyCollectionStatisticsVO {

    // 对象分类ID
    private long   classifyId;

    private String classifyName;

    // 收藏数量
    private int    number;

    public MyCollectionStatisticsVO() {

    }

    public void setClassifyId(long classifyId) {

        this.classifyId = classifyId;
    }

    public long getClassifyId() {

        return classifyId;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public int getNumber() {

        return number;
    }

    public String getClassifyName() {

        return classifyName;
    }

    public void setClassifyName(String classifyName) {

        this.classifyName = classifyName;
    }
}
