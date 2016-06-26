package com.qcloud.component.goods.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.goods.model.MerchandiseVipDiscount;
import com.qcloud.component.goods.model.query.MerchandiseVipDiscountQuery;

public interface MerchandiseVipDiscountDao extends ISimpleDao<MerchandiseVipDiscount, Long> {

    public boolean add(MerchandiseVipDiscount merchandiseVipDiscount);

    public MerchandiseVipDiscount get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchandiseVipDiscount merchandiseVipDiscount);

    public List<MerchandiseVipDiscount> list(List<Long> idList);

    public Map<Long, MerchandiseVipDiscount> map(Set<Long> idSet);

    public Page<MerchandiseVipDiscount> page(MerchandiseVipDiscountQuery query, int start, int size);

    public List<MerchandiseVipDiscount> listAll();

    Double statMin(Long merchandiseItemId);

    Double statMax(Long merchandiseItemId);

    MerchandiseVipDiscount get(Long userId, Long merchandiseItemId);

    List<MerchandiseVipDiscount> listByUser(long userId, long classifyId, String classifyBSID, int start, int count);

    int countByUser(long userId, long classifyId, String classifyBSID);

    public boolean deleteByUser(long userId);
}
