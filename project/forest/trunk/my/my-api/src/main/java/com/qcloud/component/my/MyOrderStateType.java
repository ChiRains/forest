package com.qcloud.component.my;

public enum MyOrderStateType {
    //
    TOPAY(1, "待付款"),
    //
    TO_PACKING(2, "待配货"),
    //
    SHIP(3, "待发货"),
    //
    SIGN(4, "待收货"), SUCCESS(5, "成功"),
    //
    CANCEL_ORDER(6, "已关闭"),
    //
    AFTERSALES(7, "售后"),
    //
    INVALID(8, "已取消"),
    //
    EVALUATED(9, "评价");

    private final int    key;

    //
    private final String name;

    private MyOrderStateType(int key, String name) {

        this.key = key;
        this.name = name;
    }

    public int getKey() {

        return key;
    }

    public String getName() {

        return name;
    }

    public static String getNameByState(int state) {

        for (MyOrderStateType stateType : MyOrderStateType.values()) {
            if (stateType.getKey() == state) {
                return stateType.getName();
            }
        }
        return "";
    }
}