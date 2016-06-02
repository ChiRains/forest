package com.qcloud.component.commoditycenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.commoditycenter.model.MonthHotSale;
import com.qcloud.component.commoditycenter.model.query.MonthHotSaleQuery;

public interface MonthHotSaleDao extends ISimpleDao<MonthHotSale, Long> {

    public boolean add(MonthHotSale monthHotSale);

    public MonthHotSale get(Long id);

    MonthHotSale getByUnifiedMerchandise(Long unifiedMerchandiseId, int year, int month);

    public boolean delete(Long id);

    public boolean update(MonthHotSale monthHotSale);

    public List<MonthHotSale> list(List<Long> idList);

    public Map<Long, MonthHotSale> map(Set<Long> idSet);

    public Page<MonthHotSale> page(MonthHotSaleQuery query, int start, int size);

    public List<MonthHotSale> listAll();

    public List<MonthHotSale> listByMallBsid(String mallBsid, int number);

    public List<MonthHotSale> listByMerchantBsid(String merchantBsid, int number);
}
