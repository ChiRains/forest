package com.qcloud.component.orderform.web.util;

public enum RefundOrderStateType {
    //
    REFUND(10, "申请退款"),
    //
    REFUND_CONFIRM(20, "同意退款"),
    //
    REFUND_PAID(30, "商家退款"),
    //
    REFUND_CONFIRM_PAID(40, "退款成功"),
    //
    REFUND_REFUSE(50, "门店不同意退款");

    //
    private final int    key;

    //
    private final String name;

    private RefundOrderStateType(int key, String name) {

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
