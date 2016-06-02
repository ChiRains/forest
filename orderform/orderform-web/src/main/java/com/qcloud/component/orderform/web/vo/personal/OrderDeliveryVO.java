package com.qcloud.component.orderform.web.vo.personal;

public class OrderDeliveryVO extends OrderExpressVO {

    // 送货方式
    private int    deliveryMode;

    // 自提地址
    private String pickupAddressStr;

    // 送货时间说明
    private String deliveryTimeStr;

    public int getDeliveryMode() {

        return deliveryMode;
    }

    public void setDeliveryMode(int deliveryMode) {

        this.deliveryMode = deliveryMode;
    }

    public String getPickupAddressStr() {

        return pickupAddressStr;
    }

    public void setPickupAddressStr(String pickupAddressStr) {

        this.pickupAddressStr = pickupAddressStr;
    }

    public String getDeliveryTimeStr() {

        return deliveryTimeStr;
    }

    public void setDeliveryTimeStr(String deliveryTimeStr) {

        this.deliveryTimeStr = deliveryTimeStr;
    }
}
