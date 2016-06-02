package com.qcloud.component.my.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.my.dao.MyOrderFormDao;
import com.qcloud.component.my.model.MyOrderForm;
import com.qcloud.component.my.model.query.MyOrderFormQuery;
import com.qcloud.component.my.service.MyOrderFormService;
import com.qcloud.pirates.data.Page;

@Service
public class MyOrderFormServiceImpl implements MyOrderFormService {

    @Autowired
    private MyOrderFormDao      myOrderFormDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "personalcenter_my_order_form";

    @Override
    public boolean add(MyOrderForm myOrderForm) {

        long id = autoIdGenerator.get(ID_KEY);
        myOrderForm.setId(id);
        myOrderForm.setLastUpdateTime(new Date());
        return myOrderFormDao.add(myOrderForm);
    }

    @Override
    public MyOrderForm get(Long id) {

        return myOrderFormDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return myOrderFormDao.delete(id);
    }

    @Override
    public boolean update(MyOrderForm myOrderForm) {

        myOrderForm.setLastUpdateTime(new Date());
        return myOrderFormDao.update(myOrderForm);
    }

    @Override
    public Page<MyOrderForm> page(MyOrderFormQuery query, int start, int count) {

        return myOrderFormDao.page(query, start, count);
    }

    public List<MyOrderForm> listAll() {

        return myOrderFormDao.listAll();
    }

    @Override
    public List<MyOrderForm> list(MyOrderFormQuery query, long userId, int start, int count) {

        return myOrderFormDao.list(query, userId, start, count);
    }

    @Override
    public MyOrderForm getByOrder(long userId, long orderId, long subOrderId) {

        return myOrderFormDao.getByOrder(userId, orderId, subOrderId);
    }

    @Override
    public List<MyOrderForm> listByOrder(long userId, long orderId) {

        return myOrderFormDao.listByOrder(userId, orderId);
    }

    @Override
    public int statMyOrder(long userId, int state) {
        return myOrderFormDao.statMyOrder(userId, state);
    }

    @Override
    public int statMyMerchantOrder(long userId, int state) {
        return myOrderFormDao.statMyMerchantOrder(userId, state);
    }

    @Override
    public int count(MyOrderFormQuery query, long userId) {
        return myOrderFormDao.count(query, userId);
    }
}
