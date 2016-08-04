package com.qcloud.project.forest.service.oms;

import com.qcloud.project.forest.model.oms.XmlLogisticsCompany;
import com.qcloud.project.forest.model.oms.XmlMerchandise;
import com.qcloud.project.forest.model.oms.XmlMerchandiseBatch;
import com.qcloud.project.forest.model.oms.XmlOrderBatch;
import com.qcloud.project.forest.model.oms.QueryForm;
import com.qcloud.project.forest.model.oms.XmlOrder;
import com.qcloud.project.forest.model.oms.XmlStock;

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

    /**
     * 库存更新接口
     */
    public XmlStock updateStock(QueryForm queryForm);

    /**
     * 单个商品接口
     */
    public XmlMerchandise getMerchandise(QueryForm queryForm);

    /**
     * 商品批量查询接口
     */
    public XmlMerchandiseBatch listMerchandises(QueryForm queryForm);

    /**
     * 物流公司接口
     */
    public XmlLogisticsCompany getLogisticsCompany(QueryForm queryForm);
}
