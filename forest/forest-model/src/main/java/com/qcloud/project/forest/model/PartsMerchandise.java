package com.qcloud.project.forest.model;

import java.util.Date;
import java.math.BigDecimal;

public class PartsMerchandise {

    // ID
    private long id;

    // 商品id
    private long merchandiseId;

    // 分类id
    private long classifyId;

    // 状态(1, "待初始化" 2,"新增" 3,"待审核" 4, "上线" 5"下线")
    private int  state;

    public PartsMerchandise() {

    }

    public PartsMerchandise(long id, long merchandiseId, long classifyId) {

        this.id = id;
        this.merchandiseId = merchandiseId;
        this.classifyId = classifyId;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setMerchandiseId(long merchandiseId) {

        this.merchandiseId = merchandiseId;
    }

    public long getMerchandiseId() {

        return merchandiseId;
    }

    public void setClassifyId(long classifyId) {

        this.classifyId = classifyId;
    }

    public long getClassifyId() {

        return classifyId;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }
}
