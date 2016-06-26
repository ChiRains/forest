package com.qcloud.component.goods.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.MonthHotSale;
import com.qcloud.component.goods.model.query.MonthHotSaleQuery;

public interface MonthHotSaleService {

    public boolean add(MonthHotSale monthHotSale);

    public MonthHotSale get(Long id);

    MonthHotSale getByUnifiedMerchandise(Long unifiedMerchandiseId, int year, int month);

    public boolean delete(Long id);

    public boolean update(MonthHotSale monthHotSale);

    public Page<MonthHotSale> page(MonthHotSaleQuery query, int start, int count);

    public List<MonthHotSale> listAll();

    List<MonthHotSale> listByMallBsid(String mallBsid, int number);

    List<MonthHotSale> listByMerchantBsid(String merchantBsid, int number);
}
