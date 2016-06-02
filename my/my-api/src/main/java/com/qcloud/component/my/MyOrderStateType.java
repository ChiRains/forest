package com.qcloud.component.my;

public enum MyOrderStateType {
    //
    TOPAY(1, "待付款"),
    //
    INVALID(2, "已失效"),
    //
    CANCEL_ORDER(3, "已取消"),
    //
    SHIP(4, "待发货"),
    //
    SIGN(5, "待收货"),
    //
    EVALUATION(6, "待评价"),
    //
    EVALUATED(7, "已评价"),
    //
    AFTERSALES(8, "售后"),
    //
    SUCCESS(9, "成功");

    //
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