package com.qcloud.project.forest.model.oms;

/**
 * 支付方式属性
 */
public class Payment {

    // 支付方式编码
    private String paytype;

    // 支付方式名称
    private String payname;

    // 支付时间
    private String paytime;

    // 支付金额
    private String amount;

    public String getPaytype() {

        return paytype;
    }

    public void setPaytype(String paytype) {

        this.paytype = paytype;
    }

    public String getPayname() {

        return payname;
    }

    public void setPayname(String payname) {

        this.payname = payname;
    }

    public String getPaytime() {

        return paytime;
    }

    public void setPaytime(String paytime) {

        this.paytime = paytime;
    }

    public String getAmount() {

        return amount;
    }

    public void setAmount(String amount) {

        this.amount = amount;
    }
}
