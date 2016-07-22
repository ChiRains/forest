package com.qcloud.project.forest.model.oms;

/**
 * 支付明细类
 */
public class SalesOrderPayEntity {

    // 支付类型代码
    public String tenderCode;

    // 支付类型名称
    public String tenderName;

    // 支付时间
    public String payTime;

    // 支付金额
    public double amount;

    public String getTenderCode() {

        return tenderCode;
    }

    public void setTenderCode(String tenderCode) {

        this.tenderCode = tenderCode;
    }

    public String getTenderName() {

        return tenderName;
    }

    public void setTenderName(String tenderName) {

        this.tenderName = tenderName;
    }

    public double getAmount() {

        return amount;
    }

    public void setAmount(double amount) {

        this.amount = amount;
    }

    public String getPayTime() {

        return payTime;
    }

    public void setPayTime(String payTime) {

        this.payTime = payTime;
    }
}
