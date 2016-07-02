package com.qcloud.component.sellercenter.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.StoreDeliveryTimeDao;
import com.qcloud.component.sellercenter.model.StoreDeliveryTime;
import com.qcloud.component.sellercenter.service.StoreDeliveryTimeService;
import com.qcloud.component.sellercenter.model.query.StoreDeliveryTimeQuery;

@Service
public class StoreDeliveryTimeServiceImpl implements StoreDeliveryTimeService {

    @Autowired
    private StoreDeliveryTimeDao storeDeliveryTimeDao;

    @Autowired
    private AutoIdGenerator      autoIdGenerator;

    private static final String  ID_KEY = "sellercenter_store_delivery_time";

    @Override
    public boolean add(StoreDeliveryTime storeDeliveryTime) {

        long id = autoIdGenerator.get(ID_KEY);
        storeDeliveryTime.setId(id);
        return storeDeliveryTimeDao.add(storeDeliveryTime);
    }

    @Override
    public StoreDeliveryTime get(Long id) {

        return storeDeliveryTimeDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return storeDeliveryTimeDao.delete(id);
    }

    @Override
    public boolean update(StoreDeliveryTime storeDeliveryTime) {

        return storeDeliveryTimeDao.update(storeDeliveryTime);
    }

    @Override
    public Page<StoreDeliveryTime> page(StoreDeliveryTimeQuery query, int start, int count) {

        return storeDeliveryTimeDao.page(query, start, count);
    }

    public List<StoreDeliveryTime> listAll() {

        return storeDeliveryTimeDao.listAll();
    }

    @Override
    public StoreDeliveryTime getByStore(Long storeId) {

        return storeDeliveryTimeDao.getByStore(storeId);
    }
}
