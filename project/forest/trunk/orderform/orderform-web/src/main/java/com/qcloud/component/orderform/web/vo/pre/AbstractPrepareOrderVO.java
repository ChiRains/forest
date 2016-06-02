package com.qcloud.component.orderform.web.vo.pre;

public abstract class AbstractPrepareOrderVO {

    // 总数量
    private int    totalNumber;

    // 订单金额
    private double sum;

    // 金额
    private double cash;

    // 邮费
    private double postage;

    // 优惠
    private double preferential;

    public int getTotalNumber() {

        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {

        this.totalNumber = totalNumber;
    }

    public double getSum() {

        return sum;
    }

    public final void setSum(double sum) {

        this.sum = sum;
    }

    public double getPreferential() {

        return preferential;
    }

    public final void setPreferential(double preferential) {

        this.preferential = preferential;
    }

    public double getCash() {

        return cash;
    }

    public void setCash(double cash) {

        this.cash = cash;
    }

    public double getPostage() {

        return postage;
    }

    public void setPostage(double postage) {

        this.postage = postage;
    }
}
