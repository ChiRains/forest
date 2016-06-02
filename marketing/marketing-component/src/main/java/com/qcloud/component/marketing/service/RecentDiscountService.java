package com.qcloud.component.marketing.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.marketing.model.RecentDiscount;
import com.qcloud.component.marketing.model.query.RecentDiscountQuery;

public interface RecentDiscountService {

    public boolean add(RecentDiscount recentDiscount);

    public RecentDiscount get(Long id);

    public boolean delete(Long id);

    public boolean update(RecentDiscount recentDiscount);

    public Page<RecentDiscount> page(RecentDiscountQuery query, int start, int count);

    public List<RecentDiscount> listAll();

    public List<RecentDiscount> list(RecentDiscountQuery query, int start, int size);

    public int count(RecentDiscountQuery query);
}
