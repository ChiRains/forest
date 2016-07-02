package com.qcloud.component.my;


// 送货方式
public enum DeliveryModeType {
    //
    LOGISTICS(1, "物流"),
    //
    PICKUP(2, "门店自提"),
    //
    DELIVERY(3, "送货上门");

    //
    private final int    key;

    //
    private final String name;

    private DeliveryModeType(int key, String name) {

        this.key = key;
        this.name = name;
    }

    public int getKey() {

        return key;
    }

    public String getName() {

        return name;
    }

    public static DeliveryModeType get(int key) {

        for (DeliveryModeType deliveryModeType : DeliveryModeType.values()) {
            if (deliveryModeType.getKey() == key) {
                return deliveryModeType;
            }
        }
        return LOGISTICS;
    }
}