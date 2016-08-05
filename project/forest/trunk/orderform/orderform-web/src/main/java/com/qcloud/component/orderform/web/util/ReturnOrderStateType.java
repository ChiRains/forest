package com.qcloud.component.orderform.web.util;

public enum ReturnOrderStateType {
    //
    RETURN(10, "申请退货"),
    //
    RETURN_CONFIRM(20, "同意退货"),
    //
    RETURN_SHIPPED(30, "买家发货"),
    //
    RETURN_SIGN(40, "门店签收"),
    //
    RETURN_PAID(50, "公司退款"),
    //
//    RETURN_CONFIRM_PAID(60, "买家确认退款"),
    //
    RETURN_REFUSE(60, "门店不同意退货"),
    //
    RETURN_AGAIN(70, "重新申请退货");

    //
    private final int    key;

    //
    private final String name;

    private ReturnOrderStateType(int key, String name) {

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
