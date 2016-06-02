package com.qcloud.component.orderform.dao;

import java.util.Date;
import java.util.List;
import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.orderform.model.query.CollectOrderQuery;
import com.qcloud.pirates.data.Page;

public interface CollectOrderDao {

    public boolean add(CollectOrder collectOrder);

    public CollectOrder get(Long id, Date time);

    public boolean delete(Long id);

    public boolean update(CollectOrder collectOrder);

    public Page<CollectOrder> page(CollectOrderQuery query, int start, int size);

    Date[] getDatesByLatelyMinutes(int latelyMinutes);

    public CollectOrder get(String orderNumber);

    List<CollectOrder> list4ExpireState(Date tableDate, int state, int start, int size);
}
