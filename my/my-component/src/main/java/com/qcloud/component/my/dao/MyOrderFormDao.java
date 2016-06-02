package com.qcloud.component.my.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.my.model.MyOrderForm;
import com.qcloud.component.my.model.query.MyOrderFormQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface MyOrderFormDao extends ISimpleDao<MyOrderForm, Long> {

    public boolean add(MyOrderForm myOrderForm);

    public MyOrderForm get(Long id);

    public boolean delete(Long id);

    public boolean update(MyOrderForm myOrderForm);

    public List<MyOrderForm> list(List<Long> idList);

    public Map<Long, MyOrderForm> map(Set<Long> idSet);

    public Page<MyOrderForm> page(MyOrderFormQuery query, int start, int size);

    public List<MyOrderForm> listAll();

    MyOrderForm getByOrder(long userId, long orderId, long subOrderId);

    List<MyOrderForm> list(MyOrderFormQuery query, long userId, int start, int count);

    int count(MyOrderFormQuery query, long userId);

    List<MyOrderForm> listByOrder(long userId, long orderId);

    int statMyOrder(long userId, int state);

    int statMyMerchantOrder(long userId, int state);
}
