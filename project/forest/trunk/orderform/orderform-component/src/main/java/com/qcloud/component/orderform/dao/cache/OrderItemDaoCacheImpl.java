package com.qcloud.component.orderform.dao.cache;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.OrderItemDao;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.model.query.OrderItemQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class OrderItemDaoCacheImpl implements OrderItemDao {

    @Autowired
    private OrderItemDao orderItemDaoMysqlImpl;

    // @Autowired
    // private OrderItemDao orderItemDaoRedisImpl;
    @Override
    public boolean add(OrderItem orderItem, Date time) {

        return orderItemDaoMysqlImpl.add(orderItem, time);
    }

    @Override
    public OrderItem get(Long id, Date time) {

        return orderItemDaoMysqlImpl.get(id, time);
    }

    @Override
    public boolean update(OrderItem orderItem, Date time) {

        return orderItemDaoMysqlImpl.update(orderItem, time);
    }

    @Override
    public boolean delete(Long id) {

        return orderItemDaoMysqlImpl.delete(id);
    }

    @Override
    public Page<OrderItem> page(OrderItemQuery query, int start, int size) {

        return orderItemDaoMysqlImpl.page(query, start, size);
    }

    @Override
    public List<OrderItem> listByCollectOrder(Long collectOrderId, Date time) {

        return orderItemDaoMysqlImpl.listByCollectOrder(collectOrderId, time);
    }

    @Override
    public List<OrderItem> listBySubOrder(Long subOrderId, Date time) {

        return orderItemDaoMysqlImpl.listBySubOrder(subOrderId, time);
    }
}
