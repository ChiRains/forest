package com.qcloud.component.my.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.my.dao.MyAfterSaleDao;
import com.qcloud.component.my.model.MyAfterSale;
import com.qcloud.component.my.model.query.MyAfterSaleQuery;
import com.qcloud.component.my.service.MyAfterSaleService;
import com.qcloud.component.publicdata.EnableType;
import com.qcloud.pirates.data.Page;

@Service
public class MyAfterSaleServiceImpl implements MyAfterSaleService {

    @Autowired
    private MyAfterSaleDao      myAfterSaleDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "personalcenter_my_after_sale";

    @Override
    public boolean add(MyAfterSale myAfterSale) {

        long id = autoIdGenerator.get(ID_KEY);
        myAfterSale.setId(id);
        myAfterSale.setTime(new Date());
        myAfterSale.setLastUpdateTime(new Date());
        myAfterSale.setView(EnableType.ENABLE.getKey());
        return myAfterSaleDao.add(myAfterSale);
    }

    @Override
    public MyAfterSale get(Long id) {

        return myAfterSaleDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return myAfterSaleDao.delete(id);
    }

    @Override
    public boolean update(MyAfterSale myAfterSale) {

        return myAfterSaleDao.update(myAfterSale);
    }

    @Override
    public Page<MyAfterSale> page(MyAfterSaleQuery query, int start, int count) {

        return myAfterSaleDao.page(query, start, count);
    }

    public List<MyAfterSale> listAll() {

        return myAfterSaleDao.listAll();
    }

    @Override
    public List<MyAfterSale> listByUser(long userId, int start, int count) {

        return myAfterSaleDao.listByUser(userId, start, count);
    }

    @Override
    public List<MyAfterSale> listByUserAndSubOrder(long userId, long subOrderId) {

        return myAfterSaleDao.listByUserAndSubOrder(userId, subOrderId);
    }

    @Override
    public List<MyAfterSale> listByUserAndOrder(long userId, long orderId) {

        return myAfterSaleDao.listByUserAndOrder(userId, orderId);
    }

    @Override
    public int stat(long userId) {
        return myAfterSaleDao.stat(userId);
    }
}
