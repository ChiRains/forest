package com.qcloud.component.orderform.dao;

import java.util.Date;
import java.util.List;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.model.query.SubOrderQuery;
import com.qcloud.pirates.data.Page;

public interface SubOrderDao {

    public boolean add(SubOrder subOrder, Date time);

    public boolean delete(Long id);

    public SubOrder get(Long id, Date time);

    public boolean update(SubOrder subOrder, Date time);

    public Page<SubOrder> page(SubOrderQuery query, int start, int size);

    List<SubOrder> listByCollectOrder(Long collectOrderId, Date time);
    
    SubOrder get(String orderNumber);
    
    public List<SubOrder> listByMerchantAndDay(Long merchantId, Date time);
}
