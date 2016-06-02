package com.qcloud.component.marketing.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.marketing.model.Coupon;
import com.qcloud.component.marketing.model.query.CouponQuery;

public interface CouponDao extends ISimpleDao<Coupon, Long> {

    public boolean add(Coupon coupon);

    public Coupon get(Long id);

    public boolean delete(Long id);

    public boolean update(Coupon coupon);

    public List<Coupon> list(List<Long> idList);

    public Map<Long, Coupon> map(Set<Long> idSet);

    public Page<Coupon> page(CouponQuery query, int start, int size);

    public List<Coupon> listAll();

    public List<Coupon> listActivityCoupon(long merchantId);

    public int countActivityCoupon(long merchantId);

    public List<Coupon> listByPlatform();
}
