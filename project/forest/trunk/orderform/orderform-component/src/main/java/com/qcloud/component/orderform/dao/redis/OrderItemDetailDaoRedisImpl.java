package com.qcloud.component.orderform.dao.redis;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.OrderItemDetailDao;
import com.qcloud.component.orderform.model.OrderItemDetail;
import com.qcloud.component.orderform.model.query.OrderItemDetailQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class OrderItemDetailDaoRedisImpl implements OrderItemDetailDao {

    // @Resource(name = "redis-orderform")
    // private Redis redis;
    @Override
    public boolean add(OrderItemDetail orderItemDetail, Date time) {

        throw new NotImplementedException();
    }

    @Override
    public OrderItemDetail get(Long id, Date time) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(OrderItemDetail orderItemDetail, Date time) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public Page<OrderItemDetail> page(OrderItemDetailQuery query, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public List<OrderItemDetail> listByCollectOrder(Long collectOrderId, Date time) {

        throw new NotImplementedException();
    }

    @Override
    public List<OrderItemDetail> listBySubOrder(Long subOrderId, Date time) {

        throw new NotImplementedException();
    }

    @Override
    public List<OrderItemDetail> listByOrderItem(Long orderItemId, Date time) {

        throw new NotImplementedException();
    }

    @Override
    public List<OrderItemDetail> listOrderItemDetail(Long orderId, Date time) {

        throw new NotImplementedException();
    }

    @Override
    public List<OrderItemDetail> listItemDetailByItemId(Long orderItemId, Date time) {

        throw new NotImplementedException();
    }
}
