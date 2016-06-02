package com.qcloud.component.brokerage.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class DistributionGradeVO {

    // ID
    private long   id;

    // 名称
    private String name;

    // 金额
    private double cash;

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setCash(double cash) {

        this.cash = cash;
    }

    public double getCash() {

        return cash;
    }
}
