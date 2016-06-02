package com.qcloud.component.marketing.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.marketing.MarketingClient;
import com.qcloud.component.marketing.QCoupon;
import com.qcloud.component.marketing.model.Coupon;
import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.marketing.service.CouponItemsService;
import com.qcloud.component.marketing.service.CouponService;
import com.qcloud.pirates.util.DateUtil;

@Service
public class MarketingClientImpl implements MarketingClient {

    @Autowired
    CouponItemsService couponItemsService;

    @Autowired
    CouponService      couponService;

    @Override
    public boolean useCoupon(long couponItemId) {

        CouponItems couponItems = couponItemsService.get(couponItemId);
        couponItems.setUsedNum(couponItems.getUsedNum() + 1);
        return couponItemsService.update(couponItems);
    }

    @Override
    public boolean canExtract(long userId, long couponId) {

        Coupon coupon = couponService.get(couponId);
        if (coupon == null) {
            return false;
        }
        // 赠送的优惠劵,不在商城首页领.// 把结束时间加到明天
        coupon.setEndDate(DateUtil.addDate(new Date(), 1));
        return couponService.canExtract(userId, coupon);
    }

    @Override
    public Long extractCoupon(long userId, long couponId) {

        Coupon coupon = couponService.get(couponId);
        if (coupon == null) {
            return -1L;
        }
        // 赠送的优惠劵,不在商城首页领.// 把结束时间加到明天
        coupon.setEndDate(DateUtil.addDate(new Date(), 1));
        return couponService.extractCoupon(userId, coupon);
    }

    @Override
    public boolean existCoupon(long couponId) {

        return couponService.get(couponId) != null;
    }

    @Override
    public QCoupon getActivityCoupon(long merchantId) {

        return couponService.getActivityCoupon(merchantId);
    }

    @Override
    public List<QCoupon> listByPlatform() {

        return new ArrayList<QCoupon>(couponService.listByPlatform());
    }
}