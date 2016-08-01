package com.qcloud.project.forest.service.oms;

import com.qcloud.project.forest.model.oms.XmlOrderBatch;
import com.qcloud.project.forest.model.oms.QueryForm;
import com.qcloud.project.forest.model.oms.XmlOrder;

public interface OmsDispatcherService {

    /**
     * 单个订单接口
     */
    public XmlOrder getOrder(QueryForm queryForm);

    /**
     * 批量订单接口
     */
    public XmlOrderBatch getBatchOrder(QueryForm queryForm);

    /**
     * OMS发货
     */
    public boolean deliverOrder(QueryForm queryForm);
}
