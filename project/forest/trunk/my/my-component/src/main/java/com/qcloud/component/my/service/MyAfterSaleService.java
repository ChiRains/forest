package com.qcloud.component.my.service;

import java.util.List;
import com.qcloud.component.my.model.MyAfterSale;
import com.qcloud.component.my.model.query.MyAfterSaleQuery;
import com.qcloud.pirates.data.Page;

public interface MyAfterSaleService {

    public boolean add(MyAfterSale myAfterSale);

    public MyAfterSale get(Long id);

    public boolean delete(Long id);

    public boolean update(MyAfterSale myAfterSale);

    public Page<MyAfterSale> page(MyAfterSaleQuery query, int start, int count);

    public List<MyAfterSale> listAll();

    public List<MyAfterSale> listByUser(long userId, int start, int count);

    public List<MyAfterSale> listByUserAndSubOrder(long userId, long subOrderId);

    public List<MyAfterSale> listByUserAndOrder(long userId, long orderId);

    int stat(long userId);
}
