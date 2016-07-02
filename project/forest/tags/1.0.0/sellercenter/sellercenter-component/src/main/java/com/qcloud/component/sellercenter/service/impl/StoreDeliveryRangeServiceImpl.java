package com.qcloud.component.sellercenter.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.StoreDeliveryRangeDao;
import com.qcloud.component.sellercenter.model.StoreDeliveryRange;
import com.qcloud.component.sellercenter.service.StoreDeliveryRangeService;
import com.qcloud.component.sellercenter.model.query.StoreDeliveryRangeQuery;

@Service
public class StoreDeliveryRangeServiceImpl implements StoreDeliveryRangeService {

    @Autowired
    private StoreDeliveryRangeDao storeDeliveryRangeDao;

    @Autowired
    private AutoIdGenerator       autoIdGenerator;

    private static final String   ID_KEY = "sellercenter_store_delivery_range";

    @Override
    public boolean add(StoreDeliveryRange storeDeliveryRange) {

        long id = autoIdGenerator.get(ID_KEY);
        storeDeliveryRange.setId(id);
        return storeDeliveryRangeDao.add(storeDeliveryRange);
    }

    @Override
    public StoreDeliveryRange get(Long id) {

        return storeDeliveryRangeDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return storeDeliveryRangeDao.delete(id);
    }

    @Override
    public boolean update(StoreDeliveryRange storeDeliveryRange) {

        return storeDeliveryRangeDao.update(storeDeliveryRange);
    }

    @Override
    public Page<StoreDeliveryRange> page(StoreDeliveryRangeQuery query, int start, int count) {

        return storeDeliveryRangeDao.page(query, start, count);
    }

    public List<StoreDeliveryRange> listAll() {

        return storeDeliveryRangeDao.listAll();
    }

    @Override
    public StoreDeliveryRange getByStore(Long storeId) {

        return storeDeliveryRangeDao.getByStore(storeId);
    }
}
