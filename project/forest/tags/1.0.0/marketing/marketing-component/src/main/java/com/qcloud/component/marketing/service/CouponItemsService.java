package com.qcloud.component.marketing.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.marketing.model.Coupon;
import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.marketing.model.query.CouponItemsQuery;

public interface CouponItemsService {

    public boolean add(CouponItems couponItems);

    public CouponItems get(Long id);

    public boolean delete(Long id);

    public boolean update(CouponItems couponItems);

    public Page<CouponItems> page(CouponItemsQuery query, int start, int count);

    public List<CouponItems> listAll();

    public List<CouponItems> list4Counpon(Long couponId);

    public List<CouponItems> list4Extract(Long couponId);

    public Long extractCoupon(Long userId, Coupon coupon);

    public Long extractCouponItem(Long userId, Coupon coupon, CouponItems items);

    public List<CouponItems> listByCouponId(Long couponId);
}
