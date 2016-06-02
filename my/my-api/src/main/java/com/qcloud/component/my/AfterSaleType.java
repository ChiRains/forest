package com.qcloud.component.my;

public enum AfterSaleType {
    //
    REFUND(1, "退款"), RETURN(2, "退货"), EXCHANGE(3, "换货");

    private final int    key;

    private final String name;

    private AfterSaleType(int key, String name) {

        this.key = key;
        this.name = name;
    }

    public int getKey() {

        return key;
    }

    public String getName() {

        return name;
    }

    public static AfterSaleType get(int type) {

        for (AfterSaleType afterSaleType : AfterSaleType.values()) {
            if (afterSaleType.getKey() == type) {
                return afterSaleType;
            }
        }
        return null;
    }
}
