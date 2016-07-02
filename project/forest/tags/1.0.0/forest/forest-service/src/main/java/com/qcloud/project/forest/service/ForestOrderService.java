package com.qcloud.project.forest.service;

import java.util.List;
import com.qcloud.component.orderform.OrderContext;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.ForestOrder;
import com.qcloud.project.forest.model.query.ForestOrderQuery;

public interface ForestOrderService {

    public boolean add(ForestOrder forestOrder);

    public ForestOrder get(Long id);

    public boolean delete(Long id);

    public boolean update(ForestOrder forestOrder);

    public Page<ForestOrder> page(ForestOrderQuery query, int start, int count);

    public List<ForestOrder> listAll();

    public QOrder order(OrderContext context, Long giftCouponUser, QUser user, String prove);

    public ForestOrder getByOrder(long orderId);
}
