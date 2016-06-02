package com.qcloud.component.my.entity;

import com.qcloud.component.my.DeliveryModeType;
import com.qcloud.component.my.QMyDelivery;
import com.qcloud.component.my.model.DeliveryMode;

public class MyDeliveryEntity implements QMyDelivery {

    private DeliveryMode deliveryMode;

    public MyDeliveryEntity(DeliveryMode deliveryMode) {

        super();
        this.deliveryMode = deliveryMode;
    }

    @Override
    public DeliveryModeType getType() {

        return DeliveryModeType.get(deliveryMode.getType());
    }

    @Override
    public String getTimeStr() {

        return deliveryMode.getTime();
    }

    @Override
    public long getStoreId() {

        return deliveryMode.getStoreId();
    }
}
