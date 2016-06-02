package com.qcloud.component.orderform.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.model.RefundOrderItem;
import com.qcloud.component.orderform.model.query.RefundOrderItemQuery;

public interface RefundOrderItemService {

    public boolean add(RefundOrderItem refundOrderItem);

    public RefundOrderItem get(Long id);

    public boolean delete(Long id);

    public boolean update(RefundOrderItem refundOrderItem);

    boolean update(Long id, int state);

    public Page<RefundOrderItem> page(RefundOrderItemQuery query, int start, int count);

    public List<RefundOrderItem> listAll();

    public List<RefundOrderItem> listByRefund(Long refundId);
}
