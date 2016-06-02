package com.qcloud.component.personalcenter;

public enum WealthType {
    //
    INTEGRAL(1, "积分"), COMMISSION(2, "佣金"), COMSUMPTION_CURRENCY(3, "消费币");

    private final int    key;

    private final String name;

    private WealthType(int key, String name) {

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
