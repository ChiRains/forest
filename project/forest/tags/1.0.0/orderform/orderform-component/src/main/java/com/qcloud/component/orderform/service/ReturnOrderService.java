package com.qcloud.component.orderform.service;

import java.util.List;
import com.qcloud.component.orderform.model.ReturnOrder;
import com.qcloud.component.orderform.model.query.ReturnOrderQuery;
import com.qcloud.pirates.data.Page;

public interface ReturnOrderService {

    public boolean add(ReturnOrder returnOrder);

    public ReturnOrder get(Long id);

    public boolean delete(Long id);

    public boolean update(ReturnOrder returnOrder);

    boolean update(Long id, int state);

    public Page<ReturnOrder> page(ReturnOrderQuery query, int start, int count);

    public List<ReturnOrder> listAll();

    public List<ReturnOrder> listBySubOrder(Long subOrderId);

    List<ReturnOrder> list4ExpireState(int state, int start, int size);
}
