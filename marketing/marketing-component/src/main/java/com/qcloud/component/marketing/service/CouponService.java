package com.qcloud.component.marketing.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.marketing.model.Coupon;
import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.marketing.model.query.CouponQuery;

public interface CouponService {

    public boolean add(Coupon coupon);

    public Coupon get(Long id);

    public boolean delete(Long id);

    public boolean update(Coupon coupon);

    public Page<Coupon> page(CouponQuery query, int start, int count);

    public List<Coupon> listAll();

    public boolean canExtract(Long userId, Coupon coupon);

    public boolean canIntegralExtract(Long userId, Coupon coupon);

    // 随机抽取指定优惠券活动中发行的优惠券,并返回myCouponId
    public Long extractCoupon(Long userId, Coupon coupon);

    public Long extractIntegralCoupon(Long userId, Coupon coupon);

    public Long extractCouponItem(Long userId, CouponItems couponItems);

    List<Coupon> listActivityCoupon(long merchantId);

    boolean existActivityCoupon(long merchantId);

    public Coupon getActivityCoupon(long merchantId);

    public List<Coupon> listByPlatform();
}
