package com.qcloud.component.orderform.web.util;

public enum OrderStateType {
    //
    NORMAL_TOPAY(10, "待付款"),
    //
    NORMAL_PAID(20, "已付款"),
    //
    NORMAL_CONFIRM_ORDER(30, "卖家确认"),
    //
    NORMAL_SHIPPED(40, "卖家发货"),
    //
    NORMAL_SIGN(50, "买家签收"),
    //
    NORMAL_AFTER_SALE(60, "售后"),
    //
    NORMAL_TRADE_SUCCESS(70, "交易成功"),
    //
    NORMAL_TRADE_FAIL(80, "交易失败"),
    //
    NORMAL_INVALID(90, "已失效"),
    //
    NORMAL_CANCEL_ORDER(100, "取消订单");

    //
    private final int    key;

    //
    private final String name;

    private OrderStateType(int key, String name) {

        this.key = key;
        this.name = name;
    }

    public int getKey() {

        return key;
    }

    public String getName() {

        return name;
    }
}
