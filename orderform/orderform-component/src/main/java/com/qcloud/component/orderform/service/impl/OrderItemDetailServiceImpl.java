package com.qcloud.component.orderform.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.orderform.dao.OrderItemDetailDao;
import com.qcloud.component.orderform.entity.OrderItemDetailEntity;
import com.qcloud.component.orderform.model.OrderItemDetail;
import com.qcloud.component.orderform.model.query.OrderItemDetailQuery;
import com.qcloud.component.orderform.service.OrderItemDetailService;
import com.qcloud.component.orderform.service.OrderObserverService;
import com.qcloud.pirates.data.Page;

@Service
public class OrderItemDetailServiceImpl implements OrderItemDetailService {

    @Autowired
    private OrderItemDetailDao   orderItemDetailDao;

    @Autowired
    private OrderObserverService orderObserverService;

    @Autowired
    private AutoIdGenerator      autoIdGenerator;

    private static final String  ID_KEY = "orderform_order_item_detail";

    @Override
    public boolean add(OrderItemDetailEntity orderItemDetailEntity, Date time) {

        OrderItemDetail orderItemDetail = orderItemDetailEntity.getOrderItemDetail();
        long id = autoIdGenerator.get(ID_KEY);
        orderItemDetail.setId(id);
        boolean result = orderItemDetailDao.add(orderItemDetail, time);
        orderObserverService.doNotify(orderItemDetailEntity, orderItemDetail.getState());
        return result;
    }

    @Override
    public OrderItemDetail get(Long id, Date time) {

        return orderItemDetailDao.get(id, time);
    }

    @Override
    public boolean update(OrderItemDetail orderItemDetail, Date time) {

        return orderItemDetailDao.update(orderItemDetail, time);
    }

    @Override
    public boolean delete(Long id) {

        return orderItemDetailDao.delete(id);
    }

    @Override
    public Page<OrderItemDetail> page(OrderItemDetailQuery query, int start, int count) {

        return orderItemDetailDao.page(query, start, count);
    }

    @Override
    public List<OrderItemDetail> listByCollectOrder(Long collectOrderId, Date time) {

        return orderItemDetailDao.listByCollectOrder(collectOrderId, time);
    }

    @Override
    public List<OrderItemDetail> listBySubOrder(Long subOrderId, Date time) {

        return orderItemDetailDao.listBySubOrder(subOrderId, time);
    }

    @Override
    public List<OrderItemDetail> listByOrderItem(Long orderItemId, Date time) {

        return orderItemDetailDao.listByOrderItem(orderItemId, time);
    }

    @Override
    public boolean update(List<OrderItemDetail> list, Date time) {

        for (OrderItemDetail orderItemDetail : list) {
            update(orderItemDetail, time);
        }
        return true;
    }

    @Override
    public boolean updateState(OrderItemDetailEntity orderItemDetailEntity, int newState) {

        // 更新销量
        OrderItemDetail orderItemDetail = get(orderItemDetailEntity.getId(), orderItemDetailEntity.getOrder().getOrderDate());
        int state = orderItemDetail.getState();
        orderItemDetail.setState(newState);
        if (state != newState) {
            orderObserverService.doNotify(orderItemDetailEntity, newState);
        }
        return update(orderItemDetail, orderItemDetailEntity.getOrder().getOrderDate());
    }

    @Override
    public List<OrderItemDetail> listOrderItemDetail(Long orderId, Date time) {

        return orderItemDetailDao.listOrderItemDetail(orderId, time);
    }

    @Override
    public List<OrderItemDetail> listItemDetailByItemId(Long orderItemId, Date time) {

        return orderItemDetailDao.listItemDetailByItemId(orderItemId, time);
    }
}
