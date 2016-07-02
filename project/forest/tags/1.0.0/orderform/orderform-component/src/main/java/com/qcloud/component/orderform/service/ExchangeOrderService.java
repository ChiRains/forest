package com.qcloud.component.orderform.service;

import java.util.List;
import com.qcloud.component.orderform.model.ExchangeOrder;
import com.qcloud.component.orderform.model.query.ExchangeOrderQuery;
import com.qcloud.pirates.data.Page;

public interface ExchangeOrderService {

    public boolean add(ExchangeOrder exchangeOrder);

    public ExchangeOrder get(Long id);

    public boolean delete(Long id);

    public boolean update(ExchangeOrder exchangeOrder);

    boolean update(Long id, int state);

    public Page<ExchangeOrder> page(ExchangeOrderQuery query, int start, int count);

    public List<ExchangeOrder> listAll();

    public List<ExchangeOrder> listBySubOrder(Long subOrderId);

    List<ExchangeOrder> list4ExpireState(int state, int start, int size);
}
