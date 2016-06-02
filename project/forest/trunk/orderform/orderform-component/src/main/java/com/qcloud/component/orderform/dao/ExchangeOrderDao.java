package com.qcloud.component.orderform.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.orderform.model.ExchangeOrder;
import com.qcloud.component.orderform.model.query.ExchangeOrderQuery;

public interface ExchangeOrderDao extends ISimpleDao<ExchangeOrder, Long> {

    public boolean add(ExchangeOrder exchangeOrder);

    public ExchangeOrder get(Long id);

    public boolean delete(Long id);

    public boolean update(ExchangeOrder exchangeOrder);

    public List<ExchangeOrder> list(List<Long> idList);

    public Map<Long, ExchangeOrder> map(Set<Long> idSet);

    public Page<ExchangeOrder> page(ExchangeOrderQuery query, int start, int size);

    public List<ExchangeOrder> listAll();

    List<ExchangeOrder> listBySubOrder(Long subOrderId);

    List<ExchangeOrder> list4ExpireState(int state, int start, int size);
}
