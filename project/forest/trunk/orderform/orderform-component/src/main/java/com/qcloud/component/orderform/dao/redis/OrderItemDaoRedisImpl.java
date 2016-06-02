package com.qcloud.component.orderform.dao.redis;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.OrderItemDao;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.model.query.OrderItemQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class OrderItemDaoRedisImpl implements OrderItemDao {

    // @Resource(name = "redis-orderform")
    // private Redis redis;
    @Override
    public boolean add(OrderItem orderItem, Date time) {

        throw new NotImplementedException();
    }

    @Override
    public OrderItem get(Long id, Date time) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(OrderItem orderItem, Date time) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public Page<OrderItem> page(OrderItemQuery query, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public List<OrderItem> listByCollectOrder(Long collectOrderId, Date time) {

        throw new NotImplementedException();
    }

    @Override
    public List<OrderItem> listBySubOrder(Long subOrderId, Date time) {

        throw new NotImplementedException();
    }
}
