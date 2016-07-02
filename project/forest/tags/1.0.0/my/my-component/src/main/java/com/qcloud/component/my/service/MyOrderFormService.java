package com.qcloud.component.my.service;

import java.util.List;
import com.qcloud.component.my.model.MyOrderForm;
import com.qcloud.component.my.model.query.MyOrderFormQuery;
import com.qcloud.pirates.data.Page;

public interface MyOrderFormService {

    public boolean add(MyOrderForm myOrderForm);

    public MyOrderForm get(Long id);

    MyOrderForm getByOrder(long userId, long orderId, long subOrderId);

    public boolean delete(Long id);

    public boolean update(MyOrderForm myOrderForm);

    public Page<MyOrderForm> page(MyOrderFormQuery query, int start, int count);

    public List<MyOrderForm> listAll();

    List<MyOrderForm> list(MyOrderFormQuery query, long userId, int start, int count);

    int count(MyOrderFormQuery query, long userId);

    List<MyOrderForm> listByOrder(long userId, long orderId);

    int statMyOrder(long userId, int state);

    int statMyMerchantOrder(long userId, int state);
}
