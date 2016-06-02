package com.qcloud.component.orderform.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.orderform.dao.ExchangeOrderDao;
import com.qcloud.component.orderform.engine.AutoChangeService;
import com.qcloud.component.orderform.model.ExchangeOrder;
import com.qcloud.component.orderform.model.query.ExchangeOrderQuery;
import com.qcloud.component.orderform.service.ExchangeOrderService;
import com.qcloud.pirates.data.Page;

@Service
public class ExchangeOrderServiceImpl implements ExchangeOrderService {

    @Autowired
    private ExchangeOrderDao    exchangeOrderDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    @Autowired
    private AutoChangeService   autoChangeService;

    private static final String ID_KEY = "orderform_exchange_order";

    @Override
    public boolean add(ExchangeOrder exchangeOrder) {

        exchangeOrder.setLastUpdateTime(new Date());
        exchangeOrder.setStateValidTime(autoChangeService.getStateValidTime(4, exchangeOrder.getState(), exchangeOrder.getLastUpdateTime()));
        long id = autoIdGenerator.get(ID_KEY);
        exchangeOrder.setId(id);
        return exchangeOrderDao.add(exchangeOrder);
    }

    @Override
    public ExchangeOrder get(Long id) {

        return exchangeOrderDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return exchangeOrderDao.delete(id);
    }

    @Override
    public boolean update(ExchangeOrder exchangeOrder) {

        return exchangeOrderDao.update(exchangeOrder);
    }

    @Override
    public Page<ExchangeOrder> page(ExchangeOrderQuery query, int start, int count) {

        return exchangeOrderDao.page(query, start, count);
    }

    public List<ExchangeOrder> listAll() {

        return exchangeOrderDao.listAll();
    }

    @Override
    public boolean update(Long id, int state) {

        ExchangeOrder exchangeOrder = get(id);
        exchangeOrder.setLastUpdateTime(new Date());
        exchangeOrder.setStateValidTime(autoChangeService.getStateValidTime(4, state, exchangeOrder.getLastUpdateTime()));
        exchangeOrder.setState(state);
        return update(exchangeOrder);
    }

    @Override
    public List<ExchangeOrder> listBySubOrder(Long subOrderId) {

        return exchangeOrderDao.listBySubOrder(subOrderId);
    }

    @Override
    public List<ExchangeOrder> list4ExpireState(int state, int start, int size) {

        return exchangeOrderDao.list4ExpireState(state, start, size);
    }
}
