package com.qcloud.component.sellercenter;

public enum SellerMessageType {
    //
    INSIDE_LETTER(1, "站内信"), PAY_NOTIFY(2, "付款通知"), DELIVER_GOODS(3, "发货通知");

    private final int    key;

    private final String name;

    private SellerMessageType(int key, String name) {

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