package com.qcloud.project.forest.service.oms;

import com.qcloud.project.forest.model.oms.Order;
import com.qcloud.project.forest.model.oms.QueryForm;

public interface OmsDispatcherService {

    /**
     * 单个订单接口
     */
    public Order getOrder(QueryForm queryForm);

    /**
     * OMS发货
     */
    public boolean deliverOrder(QueryForm queryForm);
}
