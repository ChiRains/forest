package com.qcloud.component.my.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.my.model.MyAfterSale;
import com.qcloud.component.my.model.query.MyAfterSaleQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface MyAfterSaleDao extends ISimpleDao<MyAfterSale, Long> {

    public boolean add(MyAfterSale myAfterSale);

    public MyAfterSale get(Long id);

    public boolean delete(Long id);

    public boolean update(MyAfterSale myAfterSale);

    public List<MyAfterSale> list(List<Long> idList);

    public Map<Long, MyAfterSale> map(Set<Long> idSet);

    public Page<MyAfterSale> page(MyAfterSaleQuery query, int start, int size);

    public List<MyAfterSale> listAll();

    List<MyAfterSale> listByUser(long userId, int start, int count);

    List<MyAfterSale> listByUserAndSubOrder(long userId, long subOrderId);

    List<MyAfterSale> listByUserAndOrder(long userId, long orderId);
    
    int stat(long userId);
}
