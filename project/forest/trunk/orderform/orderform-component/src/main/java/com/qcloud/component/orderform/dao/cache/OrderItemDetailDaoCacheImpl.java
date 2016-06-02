package com.qcloud.component.orderform.dao.cache;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.OrderItemDetailDao;
import com.qcloud.component.orderform.model.OrderItemDetail;
import com.qcloud.component.orderform.model.query.OrderItemDetailQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class OrderItemDetailDaoCacheImpl implements OrderItemDetailDao {

    @Autowired
    private OrderItemDetailDao orderItemDetailDaoMysqlImpl;

    // @Autowired
    // private OrderItemDetailDao orderItemDetailDaoRedisImpl;
    @Override
    public boolean add(OrderItemDetail orderItemDetail, Date time) {

        return orderItemDetailDaoMysqlImpl.add(orderItemDetail, time);
    }

    @Override
    public OrderItemDetail get(Long id, Date time) {

        return orderItemDetailDaoMysqlImpl.get(id, time);
    }

    @Override
    public boolean update(OrderItemDetail orderItemDetail, Date time) {

        return orderItemDetailDaoMysqlImpl.update(orderItemDetail, time);
    }

    @Override
    public boolean delete(Long id) {

        return orderItemDetailDaoMysqlImpl.delete(id);
    }

    @Override
    public Page<OrderItemDetail> page(OrderItemDetailQuery query, int start, int size) {

        return orderItemDetailDaoMysqlImpl.page(query, start, size);
    }

    @Override
    public List<OrderItemDetail> listByCollectOrder(Long collectOrderId, Date time) {

        return orderItemDetailDaoMysqlImpl.listByCollectOrder(collectOrderId, time);
    }

    @Override
    public List<OrderItemDetail> listBySubOrder(Long subOrderId, Date time) {

        return orderItemDetailDaoMysqlImpl.listBySubOrder(subOrderId, time);
    }

    @Override
    public List<OrderItemDetail> listByOrderItem(Long orderItemId, Date time) {

        return orderItemDetailDaoMysqlImpl.listByOrderItem(orderItemId, time);
    }

    @Override
    public List<OrderItemDetail> listOrderItemDetail(Long orderId, Date time) {

        return orderItemDetailDaoMysqlImpl.listOrderItemDetail(orderId, time);
    }

    @Override
    public List<OrderItemDetail> listItemDetailByItemId(Long orderItemId, Date time) {

        return orderItemDetailDaoMysqlImpl.listItemDetailByItemId(orderItemId, time);
    }
}
