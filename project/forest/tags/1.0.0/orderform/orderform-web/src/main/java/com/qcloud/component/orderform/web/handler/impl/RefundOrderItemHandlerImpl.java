package com.qcloud.component.orderform.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.model.OrderItemDetail;
import com.qcloud.component.orderform.model.RefundOrderItem;
import com.qcloud.component.orderform.service.OrderItemDetailService;
import com.qcloud.component.orderform.service.OrderItemService;
import com.qcloud.component.orderform.web.handler.RefundOrderItemHandler;
import com.qcloud.component.orderform.web.vo.RefundOrderItemVO;
import com.qcloud.component.orderform.web.vo.admin.AdminRefundOrderItemVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class RefundOrderItemHandlerImpl implements RefundOrderItemHandler {

    @Autowired
    private FileSDKClient          fileSDKClient;

    @Autowired
    private OrderItemService       orderItemService;

    @Autowired
    private OrderItemDetailService orderItemDetailService;

    @Override
    public List<RefundOrderItemVO> toVOList(List<RefundOrderItem> list) {

        List<RefundOrderItemVO> voList = new ArrayList<RefundOrderItemVO>();
        for (RefundOrderItem refundOrderItem : list) {
            voList.add(toVO(refundOrderItem));
        }
        return voList;
    }

    @Override
    public RefundOrderItemVO toVO(RefundOrderItem refundOrderItem) {

        String json = Json.toJson(refundOrderItem);
        return Json.toObject(json, RefundOrderItemVO.class, true);
    }

    @Override
    public List<AdminRefundOrderItemVO> toVOList4Admin(List<RefundOrderItem> list) {

        List<AdminRefundOrderItemVO> voList = new ArrayList<AdminRefundOrderItemVO>();
        for (RefundOrderItem adminRefundOrderItem : list) {
            AdminRefundOrderItemVO vo = toVO4Admin(adminRefundOrderItem);
            OrderItem item = orderItemService.get(vo.getOrderItemId(), vo.getTime());
            vo.setName(item.getName());
            vo.setImage(fileSDKClient.getFileServerUrl() + item.getImage());
            List<OrderItemDetail> details = orderItemDetailService.listByOrderItem(vo.getOrderItemId(), vo.getTime());
            if (details.size() > 1) {
                vo.setSpecifications("组合套餐");
            } else {
                vo.setSpecifications(details.get(0).getSpecifications());
            }
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public AdminRefundOrderItemVO toVO4Admin(RefundOrderItem refundOrderItem) {

        String json = Json.toJson(refundOrderItem);
        return Json.toObject(json, AdminRefundOrderItemVO.class, true);
    }
}
