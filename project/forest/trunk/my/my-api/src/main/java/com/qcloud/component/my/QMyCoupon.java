package com.qcloud.component.my;

import java.util.Date;

public interface QMyCoupon {

    long getId();

    long getUserId();

    long getCouponId();

    long getCouponItemId();

    Date getExtractDate();

    Date getValidDate();

    Date getInValidDate();

    String getName();

    double getPrice();

    double getLimitPrice();

    int getState();

    String getCode();

    long getMerchantId();
}
