package com.qcloud.component.orderform.service;

import java.util.List;
import com.qcloud.component.orderform.model.RefundOrder;
import com.qcloud.component.orderform.model.query.RefundOrderQuery;
import com.qcloud.pirates.data.Page;

public interface RefundOrderService {

    public boolean add(RefundOrder refundOrder);

    public RefundOrder get(Long id);

    public boolean delete(Long id);

    public boolean update(RefundOrder refundOrder);

    boolean update(Long id, int state);

    public Page<RefundOrder> page(RefundOrderQuery query, int start, int count);

    public List<RefundOrder> listAll();

    public List<RefundOrder> listBySubOrder(Long subOrderId);

    List<RefundOrder> list4ExpireState(int state, int start, int size);
}
