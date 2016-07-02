package com.qcloud.component.orderform.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.orderform.model.ReturnOrder;
import com.qcloud.component.orderform.model.query.ReturnOrderQuery;

public interface ReturnOrderDao extends ISimpleDao<ReturnOrder, Long> {

    public boolean add(ReturnOrder returnOrder);

    public ReturnOrder get(Long id);

    public boolean delete(Long id);

    public boolean update(ReturnOrder returnOrder);

    public List<ReturnOrder> list(List<Long> idList);

    public Map<Long, ReturnOrder> map(Set<Long> idSet);

    public Page<ReturnOrder> page(ReturnOrderQuery query, int start, int size);

    public List<ReturnOrder> listAll();

    List<ReturnOrder> listBySubOrder(Long subOrderId);
    
    List<ReturnOrder> list4ExpireState(int state, int start, int size);
}
