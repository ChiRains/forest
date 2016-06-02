package com.qcloud.component.orderform.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.ExchangeOrderItemDetailDao;
import com.qcloud.component.orderform.model.ExchangeOrderItemDetail;
import com.qcloud.component.orderform.service.ExchangeOrderItemDetailService;
import com.qcloud.component.orderform.model.query.ExchangeOrderItemDetailQuery;

@Service
public class ExchangeOrderItemDetailServiceImpl implements ExchangeOrderItemDetailService {

    @Autowired
    private ExchangeOrderItemDetailDao exchangeOrderItemDetailDao;

    @Autowired
    private AutoIdGenerator            autoIdGenerator;

    private static final String        ID_KEY = "orderform_exchange_order_item_detail";

    @Override
    public boolean add(ExchangeOrderItemDetail exchangeOrderItemDetail) {

        long id = autoIdGenerator.get(ID_KEY);
        exchangeOrderItemDetail.setId(id);
        return exchangeOrderItemDetailDao.add(exchangeOrderItemDetail);
    }

    @Override
    public ExchangeOrderItemDetail get(Long id) {

        return exchangeOrderItemDetailDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return exchangeOrderItemDetailDao.delete(id);
    }

    @Override
    public boolean update(ExchangeOrderItemDetail exchangeOrderItemDetail) {

        return exchangeOrderItemDetailDao.update(exchangeOrderItemDetail);
    }

    @Override
    public Page<ExchangeOrderItemDetail> page(ExchangeOrderItemDetailQuery query, int start, int count) {

        return exchangeOrderItemDetailDao.page(query, start, count);
    }

    public List<ExchangeOrderItemDetail> listAll() {

        return exchangeOrderItemDetailDao.listAll();
    }

    @Override
    public boolean update(Long id, int state) {

        ExchangeOrderItemDetail exchangeOrderItemDetail = get(id);
        exchangeOrderItemDetail.setState(state);
        return update(exchangeOrderItemDetail);
    }

    @Override
    public List<ExchangeOrderItemDetail> listByExchange(Long exchangeId) {

        return exchangeOrderItemDetailDao.listByExchange(exchangeId);
    }
}
