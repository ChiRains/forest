package com.qcloud.component.orderform.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.orderform.model.RefundOrderItem;
import com.qcloud.component.orderform.model.query.RefundOrderItemQuery;

public interface RefundOrderItemDao extends ISimpleDao<RefundOrderItem, Long> {

    public boolean add(RefundOrderItem refundOrderItem);

    public RefundOrderItem get(Long id);

    public boolean delete(Long id);

    public boolean update(RefundOrderItem refundOrderItem);

    public List<RefundOrderItem> list(List<Long> idList);

    public Map<Long, RefundOrderItem> map(Set<Long> idSet);

    public Page<RefundOrderItem> page(RefundOrderItemQuery query, int start, int size);

    public List<RefundOrderItem> listAll();

    List<RefundOrderItem> listByRefund(Long refundId);
}
