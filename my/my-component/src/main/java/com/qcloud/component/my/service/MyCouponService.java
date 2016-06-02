package com.qcloud.component.my.service;

import java.util.List;
import com.qcloud.component.my.model.MyCoupon;
import com.qcloud.component.my.model.query.MyCouponQuery;
import com.qcloud.pirates.data.Page;

public interface MyCouponService {

    public boolean add(MyCoupon myCoupon);

    public MyCoupon get(Long id);

    public MyCoupon getByCode(String code);

    public boolean delete(Long id);

    public boolean update(MyCoupon myCoupon);

    public Page<MyCoupon> page(MyCouponQuery query, int start, int count);

    public List<MyCoupon> listAll();

    public List<MyCoupon> listByUser(long userId, Integer type, int start, int count);

    int countByUser(long userId, Integer type);

    public List<MyCoupon> listCanUseByUser(long userId, Long merchantId, double sum, int start, int count);

    List<MyCoupon> listByUserAndCoupon(long userId, long couponId);
}
