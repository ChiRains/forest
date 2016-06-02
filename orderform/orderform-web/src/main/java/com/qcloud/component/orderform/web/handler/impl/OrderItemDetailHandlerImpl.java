package com.qcloud.component.orderform.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.orderform.exception.OrderformException;
import com.qcloud.component.orderform.model.OrderItemDetail;
import com.qcloud.component.orderform.web.handler.OrderItemDetailHandler;
import com.qcloud.component.orderform.web.vo.admin.AdminOrderItemDetailVO;
import com.qcloud.component.warehouse.WarehouseClient;
import com.qcloud.pirates.core.json.Json;

@Component
public class OrderItemDetailHandlerImpl implements OrderItemDetailHandler {

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Autowired
    private WarehouseClient       warehouseClient;

    // @Override
    // public List<OrderItemDetailVO> toVOList(List<OrderItemDetail> list){
    // List<OrderItemDetailVO> voList = new ArrayList<OrderItemDetailVO>();
    // for (OrderItemDetail orderItemDetail : list) {
    // voList.add(toVO(orderItemDetail));
    // }
    // return voList;
    // }
    //
    // @Override
    // public OrderItemDetailVO toVO(OrderItemDetail orderItemDetail){
    // String json = Json.toJson(orderItemDetail);
    // return Json.toObject(json, OrderItemDetailVO.class, true);
    //
    // }
    @Override
    public List<AdminOrderItemDetailVO> toVOList4Admin(List<OrderItemDetail> list, long storeId) {

        List<AdminOrderItemDetailVO> voList = new ArrayList<AdminOrderItemDetailVO>();
        for (OrderItemDetail adminOrderItemDetail : list) {
            AdminOrderItemDetailVO vo = toVO4Admin(adminOrderItemDetail);
            long unifiedMerchandiseId = adminOrderItemDetail.getUnifiedMerchandiseId();
            QUnifiedMerchandise qUnifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
            if (qUnifiedMerchandise == null) {
                throw new OrderformException("数据异常,请检查唯一商品id:" + unifiedMerchandiseId);
            }
            vo.setStock(qUnifiedMerchandise.getStock());
            vo.setRealStock(warehouseClient.getStock(adminOrderItemDetail.getMerchantId(), storeId, unifiedMerchandiseId));
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public AdminOrderItemDetailVO toVO4Admin(OrderItemDetail orderItemDetail) {

        String json = Json.toJson(orderItemDetail);
        return Json.toObject(json, AdminOrderItemDetailVO.class, true);
    }
}
