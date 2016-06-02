package com.qcloud.component.orderform.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.model.ExchangeOrderItemDetail;
import com.qcloud.component.orderform.model.query.ExchangeOrderItemDetailQuery;

public interface ExchangeOrderItemDetailService {

    public boolean add(ExchangeOrderItemDetail exchangeOrderItemDetail);

    public ExchangeOrderItemDetail get(Long id);

    public boolean delete(Long id);

    public boolean update(ExchangeOrderItemDetail exchangeOrderItemDetail);

    boolean update(Long id, int state);

    public Page<ExchangeOrderItemDetail> page(ExchangeOrderItemDetailQuery query, int start, int count);

    public List<ExchangeOrderItemDetail> listAll();

    public List<ExchangeOrderItemDetail> listByExchange(Long exchangeId);
}
