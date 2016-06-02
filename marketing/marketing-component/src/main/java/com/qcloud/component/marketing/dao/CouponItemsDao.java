package com.qcloud.component.marketing.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.marketing.model.query.CouponItemsQuery;

public interface CouponItemsDao extends ISimpleDao<CouponItems, Long> {

    public boolean add(CouponItems couponItems);

    public CouponItems get(Long id);

    public boolean delete(Long id);

    public boolean update(CouponItems couponItems);

    public List<CouponItems> list(List<Long> idList);

    public Map<Long, CouponItems> map(Set<Long> idSet);

    public Page<CouponItems> page(CouponItemsQuery query, int start, int size);

    public List<CouponItems> listAll();

    public List<CouponItems> list4Counpon(Long couponId);

    public List<CouponItems> list4Extract(Long couponId);

    public List<CouponItems> listByCouponId(Long couponId);
}
