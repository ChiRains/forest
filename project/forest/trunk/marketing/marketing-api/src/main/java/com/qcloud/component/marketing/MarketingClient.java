package com.qcloud.component.marketing;

import java.util.List;

public interface MarketingClient {

    QCoupon getActivityCoupon(long merchantId);

    boolean existCoupon(long couponId);

    boolean useCoupon(long couponItemId);

    //
    boolean canExtract(long userId, long couponId);

    // 随机抽取指定优惠券活动中发行的优惠券,并返回myCouponId
    Long extractCoupon(long userId, long couponId);

    List<QCoupon> listByPlatform();
}