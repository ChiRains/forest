package com.qcloud.component.orderform.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.model.ReturnOrderItem;
import com.qcloud.component.orderform.model.query.ReturnOrderItemQuery;

public interface ReturnOrderItemService {

    public boolean add(ReturnOrderItem returnOrderItem);

    public ReturnOrderItem get(Long id);

    public boolean delete(Long id);

    public boolean update(ReturnOrderItem returnOrderItem);

    boolean update(Long id, int state);

    public Page<ReturnOrderItem> page(ReturnOrderItemQuery query, int start, int count);

    public List<ReturnOrderItem> listAll();

    public List<ReturnOrderItem> listByReturn(Long returnId);
}
