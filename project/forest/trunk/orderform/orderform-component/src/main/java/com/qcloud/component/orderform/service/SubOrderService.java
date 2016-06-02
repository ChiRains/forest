package com.qcloud.component.orderform.service;

import java.util.Date;
import java.util.List;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.model.query.SubOrderQuery;
import com.qcloud.pirates.data.Page;

public interface SubOrderService {

    public boolean add(MerchantOrderEntity merchantOrder, Date time);

    public SubOrder get(Long id, Date time);

    public SubOrder get(String orderNumber);

    public boolean delete(Long id);

    // 更新基本信息
    public boolean update(SubOrder subOrder, Date time);

    // 更新门店专用接口
    boolean updateStore(Long id, Date time, Long storeId);

    // 更新状态专用接口
    boolean updateState(MerchantOrderEntity merchantOrder, int state);

    public Page<SubOrder> page(SubOrderQuery query, int start, int count);

    public int getMerchantOrderFormState(int state);

    public List<SubOrder> listByCollectOrder(Long collectOrderId, Date time);
    
    public List<SubOrder> listByMerchantAndDay(Long merchantId, Date time);
}
