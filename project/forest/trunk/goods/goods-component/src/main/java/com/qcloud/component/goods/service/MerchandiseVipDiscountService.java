package com.qcloud.component.goods.service;

import java.util.List;
import com.qcloud.component.goods.model.MerchandiseVipDiscount;
import com.qcloud.component.goods.model.query.MerchandiseVipDiscountQuery;
import com.qcloud.pirates.data.Page;

public interface MerchandiseVipDiscountService {

    public boolean add(MerchandiseVipDiscount merchandiseVipDiscount);

    public MerchandiseVipDiscount get(Long id);

    MerchandiseVipDiscount get(Long userId, Long merchandiseItemId);

    public boolean delete(Long id);

    public boolean update(MerchandiseVipDiscount merchandiseVipDiscount);

    public Page<MerchandiseVipDiscount> page(MerchandiseVipDiscountQuery query, int start, int count);

    List<MerchandiseVipDiscount> listByUser(long userId, long classifyId, int start, int count);

    int countByUser(long userId, long classifyId);

    public Double statMin(Long merchandiseItemId);

    public Double statMax(Long merchandiseItemId);

    /**
     * 保存大客户价格or商品（并保存历史）
     * @param merchandiseVipDiscount
     */
    public void save(MerchandiseVipDiscount merchandiseVipDiscount);

    public boolean deleteByUser(long userId);
}
