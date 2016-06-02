package com.qcloud.component.my.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.my.dao.DeliveryModeDao;
import com.qcloud.component.my.model.DeliveryMode;
import com.qcloud.component.my.model.query.DeliveryModeQuery;
import com.qcloud.component.my.service.DeliveryModeService;
import com.qcloud.pirates.data.Page;

@Service
public class DeliveryModeServiceImpl implements DeliveryModeService {

    @Autowired
    private DeliveryModeDao     deliveryModeDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "personalcenter_delivery_mode";

    @Override
    public boolean add(DeliveryMode deliveryMode) {

        long id = autoIdGenerator.get(ID_KEY);
        deliveryMode.setId(id);
        return deliveryModeDao.add(deliveryMode);
    }

    @Override
    public DeliveryMode get(Long id) {

        return deliveryModeDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return deliveryModeDao.delete(id);
    }

    @Override
    public boolean update(DeliveryMode deliveryMode) {

        return deliveryModeDao.update(deliveryMode);
    }

    @Override
    public Page<DeliveryMode> page(DeliveryModeQuery query, int start, int count) {

        return deliveryModeDao.page(query, start, count);
    }

    public List<DeliveryMode> listAll() {

        return deliveryModeDao.listAll();
    }

    @Override
    public DeliveryMode getByUser(Long userId) {

        return deliveryModeDao.getByUser(userId);
    }
}
