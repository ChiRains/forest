package com.qcloud.component.orderform.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.orderform.model.RefundOrder;
import com.qcloud.component.orderform.model.query.RefundOrderQuery;

public interface RefundOrderDao extends ISimpleDao<RefundOrder, Long> {

    public boolean add(RefundOrder refundOrder);

    public RefundOrder get(Long id);

    public boolean delete(Long id);

    public boolean update(RefundOrder refundOrder);

    public List<RefundOrder> list(List<Long> idList);

    public Map<Long, RefundOrder> map(Set<Long> idSet);

    public Page<RefundOrder> page(RefundOrderQuery query, int start, int size);

    public List<RefundOrder> listAll();

    List<RefundOrder> listBySubOrder(Long subOrderId);

    List<RefundOrder> list4ExpireState(int state, int start, int size);
}
