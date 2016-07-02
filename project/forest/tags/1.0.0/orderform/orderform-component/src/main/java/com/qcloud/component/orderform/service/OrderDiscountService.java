package com.qcloud.component.orderform.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.model.OrderDiscount;
import com.qcloud.component.orderform.model.query.OrderDiscountQuery;

public interface OrderDiscountService {

    public boolean add(OrderDiscount orderDiscount);

    public OrderDiscount get(Long id);

    public boolean delete(Long id);

    public boolean update(OrderDiscount orderDiscount);

    public Page<OrderDiscount> page(OrderDiscountQuery query, int start, int count);

    public List<OrderDiscount> listAll();

    public List<OrderDiscount> listBySubOrder(long subOrderId);
}
