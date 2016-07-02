package com.qcloud.component.goods.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.goods.model.MerchandiseVipDiscountDate;
import com.qcloud.component.goods.model.query.MerchandiseVipDiscountDateQuery;

public interface MerchandiseVipDiscountDateDao extends ISimpleDao<MerchandiseVipDiscountDate, Long> {

    public boolean add(MerchandiseVipDiscountDate merchandiseVipDiscountDate);

    public MerchandiseVipDiscountDate get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchandiseVipDiscountDate merchandiseVipDiscountDate);

    public List<MerchandiseVipDiscountDate> list(List<Long> idList);

    public Map<Long, MerchandiseVipDiscountDate> map(Set<Long> idSet);

    public Page<MerchandiseVipDiscountDate> page(MerchandiseVipDiscountDateQuery query, int start, int size);

    public List<MerchandiseVipDiscountDate> listAll();

    MerchandiseVipDiscountDate getByUser(Long userId, int year, int month, int day);

    MerchandiseVipDiscountDate getLastByUser(Long userId);
}
