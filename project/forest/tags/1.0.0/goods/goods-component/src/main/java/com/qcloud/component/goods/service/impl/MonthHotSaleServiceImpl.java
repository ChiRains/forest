package com.qcloud.component.goods.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MonthHotSaleDao;
import com.qcloud.component.goods.model.MonthHotSale;
import com.qcloud.component.goods.service.MonthHotSaleService;
import com.qcloud.component.goods.model.query.MonthHotSaleQuery;

@Service
public class MonthHotSaleServiceImpl implements MonthHotSaleService {

    @Autowired
    private MonthHotSaleDao     monthHotSaleDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "goods_month_hot_sale";

    @Override
    public boolean add(MonthHotSale monthHotSale) {

        long id = autoIdGenerator.get(ID_KEY);
        monthHotSale.setId(id);
        return monthHotSaleDao.add(monthHotSale);
    }

    @Override
    public MonthHotSale get(Long id) {

        return monthHotSaleDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return monthHotSaleDao.delete(id);
    }

    @Override
    public boolean update(MonthHotSale monthHotSale) {

        return monthHotSaleDao.update(monthHotSale);
    }

    @Override
    public Page<MonthHotSale> page(MonthHotSaleQuery query, int start, int count) {

        return monthHotSaleDao.page(query, start, count);
    }

    public List<MonthHotSale> listAll() {

        return monthHotSaleDao.listAll();
    }

    @Override
    public MonthHotSale getByUnifiedMerchandise(Long unifiedMerchandiseId, int year, int month) {

        return monthHotSaleDao.getByUnifiedMerchandise(unifiedMerchandiseId, year, month);
    }

    @Override
    public List<MonthHotSale> listByMallBsid(String mallBsid, int number) {

        return monthHotSaleDao.listByMallBsid(mallBsid, number);
    }

    @Override
    public List<MonthHotSale> listByMerchantBsid(String merchantBsid, int number) {

        return monthHotSaleDao.listByMerchantBsid(merchantBsid, number);
    }
}
