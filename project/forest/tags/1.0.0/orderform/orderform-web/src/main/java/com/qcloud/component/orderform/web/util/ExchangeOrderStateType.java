package com.qcloud.component.orderform.web.util;

public enum ExchangeOrderStateType {
    //
    EXCHANGE(10, "申请换货"),
    //
    EXCHANGE_CONFIRM(20, "卖家同意换货"),
    //
    EXCHANGE_SHIPPED(30, "买家发货"),
    //
    EXCHANGE_SIGN(40, "卖家签收"),
    //
    EXCHANGE_SHIPPED_AGAIN(50, "卖家发货"),
    //
    EXCHANGE_SIGN_AGAIN(60, "买家签收"),
    //
    EXCHANGE_REFUSE(70, "卖家不同意换货"),
    //
    EXCHANGE_AGAIN(80, "重新申请换货");

    //
    private final int    key;

    //
    private final String name;

    private ExchangeOrderStateType(int key, String name) {

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
