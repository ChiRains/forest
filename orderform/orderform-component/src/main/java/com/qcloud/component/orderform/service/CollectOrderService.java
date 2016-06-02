package com.qcloud.component.orderform.service;

import java.util.Date;
import java.util.List;
import com.qcloud.component.my.QMyConsignee;
import com.qcloud.component.orderform.PaymentModeType;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.orderform.model.query.CollectOrderQuery;
import com.qcloud.pirates.data.Page;

public interface CollectOrderService {

    public boolean add(OrderEntity order);

    public CollectOrder get(Long id, Date time);

    public boolean delete(Long id);

    public boolean update(CollectOrder collectOrder);

    // 更新状态专用接口
    boolean updateState(OrderEntity order, int state);

    boolean updatePaymentMode(Long orderId, Date time, PaymentModeType mode);

    boolean updateConsignee(Long orderId, Date time, QMyConsignee consignee);

    public Page<CollectOrder> page(CollectOrderQuery query, int start, int count);

    Date[] getDatesByLatelyMinutes(int latelyMinutes);

    public CollectOrder get(String orderNumber);

    public int getUserOrderFormState(int state);

    List<CollectOrder> list4ExpireState(Date tableDate, int state, int start, int size);
}
