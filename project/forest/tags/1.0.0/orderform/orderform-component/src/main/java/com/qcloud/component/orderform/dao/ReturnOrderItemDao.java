package com.qcloud.component.orderform.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.orderform.model.ReturnOrderItem;
import com.qcloud.component.orderform.model.query.ReturnOrderItemQuery;

public interface ReturnOrderItemDao extends ISimpleDao<ReturnOrderItem, Long> {

    public boolean add(ReturnOrderItem returnOrderItem);

    public ReturnOrderItem get(Long id);

    public boolean delete(Long id);

    public boolean update(ReturnOrderItem returnOrderItem);

    public List<ReturnOrderItem> list(List<Long> idList);

    public Map<Long, ReturnOrderItem> map(Set<Long> idSet);

    public Page<ReturnOrderItem> page(ReturnOrderItemQuery query, int start, int size);

    public List<ReturnOrderItem> listAll();

    List<ReturnOrderItem> listByReturn(Long returnId);
}
