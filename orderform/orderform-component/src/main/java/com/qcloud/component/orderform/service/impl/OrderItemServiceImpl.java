package com.qcloud.component.orderform.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.orderform.dao.OrderItemDao;
import com.qcloud.component.orderform.entity.OrderItemEntity;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.model.query.OrderItemQuery;
import com.qcloud.component.orderform.service.OrderItemService;
import com.qcloud.component.orderform.service.OrderObserverService;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemDao         orderItemDao;

    @Autowired
    private AutoIdGenerator      autoIdGenerator;

    @Autowired
    private OrderObserverService orderObserverService;

    private static final String  ID_KEY = "orderform_order_item";

    @Transactional
    public boolean add(OrderItemEntity orderItemEntity, Date time) {

        OrderItem orderItem = orderItemEntity.getOrderItem();
        long id = autoIdGenerator.get(ID_KEY);
        orderItem.setId(id);
        boolean result = orderItemDao.add(orderItem, time);
        orderObserverService.doNotify(orderItemEntity, orderItem.getState());
        return result;
    }

    @Override
    public OrderItem get(Long id, Date time) {

        return orderItemDao.get(id, time);
    }

    @Transactional
    public boolean update(OrderItem orderItem, Date time) {

        return orderItemDao.update(orderItem, time);
    }

    @Override
    public boolean delete(Long id) {

        return orderItemDao.delete(id);
    }

    @Override
    public Page<OrderItem> page(OrderItemQuery query, int start, int count) {

        return orderItemDao.page(query, start, count);
    }

    @Override
    public List<OrderItem> listByCollectOrder(Long collectOrderId, Date time) {

        return orderItemDao.listByCollectOrder(collectOrderId, time);
    }

    @Override
    public List<OrderItem> listBySubOrder(Long subOrderId, Date time) {

        return orderItemDao.listBySubOrder(subOrderId, time);
    }

    @Transactional
    public boolean updateState(OrderItemEntity orderItemEntity, int newState) {

        OrderItem orderItem = get(orderItemEntity.getId(), orderItemEntity.getOrder().getOrderDate());
        int state = orderItem.getState();
        orderItem.setState(newState);
        if (state != newState) {
            orderObserverService.doNotify(orderItemEntity, newState);
        }
        // // TODO
        // if (state != newState && (newState == OrderStateType.NORMAL_INVALID.getKey() || newState == OrderStateType.NORMAL_CANCEL_ORDER.getKey())) {
        // // if (state != newState && (newState == OrderStateType.NORMAL_INVALID.getKey() || newState == OrderStateType.NORMAL_CANCEL_ORDER.getKey() || newState == OrderStateType.NORMAL_CANCEL_PAID.getKey())) {
        // commoditycenterClient.lockOnlineStock(orderItem.getUnifiedMerchandiseId(), orderItem.getNumber());
        // }
        // if (state != newState && newState == OrderStateType.NORMAL_SHIPPED.getKey()) {
        // // 减线下的库存
        // List<OrderItemDetail> list = orderItemDetailDao.listByOrderItem(orderItem.getId(), time);
        // for (OrderItemDetail orderItemDetail : list) {
        // warehouseClient.reduce(orderItemDetail.getMerchantId(), storeId, orderItemDetail.getUnifiedMerchandiseId(), orderItem.getNumber());
        // }
        // }
        return update(orderItem, orderItemEntity.getOrder().getOrderDate());
    }
}
