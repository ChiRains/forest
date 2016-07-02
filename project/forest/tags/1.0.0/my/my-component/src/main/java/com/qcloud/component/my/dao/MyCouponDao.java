package com.qcloud.component.my.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.my.model.MyCoupon;
import com.qcloud.component.my.model.query.MyCouponQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface MyCouponDao extends ISimpleDao<MyCoupon, Long> {

    public boolean add(MyCoupon myCoupon);

    public MyCoupon get(Long id);

    public boolean delete(Long id);

    public boolean update(MyCoupon myCoupon);

    public List<MyCoupon> list(List<Long> idList);

    public Map<Long, MyCoupon> map(Set<Long> idSet);

    public Page<MyCoupon> page(MyCouponQuery query, int start, int size);

    public List<MyCoupon> listAll();

    List<MyCoupon> listByUser(long userId, Integer type, int start, int count);

    int countByUser(long userId, Integer type);

    List<MyCoupon> listCanUseByUser(long userId, Long merchantId, double sum, int start, int count);

    List<MyCoupon> listByUserAndCoupon(long userId, long couponId);

    MyCoupon getByCode(String code);
}
