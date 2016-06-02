package com.qcloud.component.orderform.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.model.OrderItemDetail;
import com.qcloud.component.orderform.model.ReturnOrderItem;
import com.qcloud.component.orderform.service.OrderItemDetailService;
import com.qcloud.component.orderform.service.OrderItemService;
import com.qcloud.component.orderform.web.handler.ReturnOrderItemHandler;
import com.qcloud.component.orderform.web.vo.ReturnOrderItemVO;
import com.qcloud.component.orderform.web.vo.admin.AdminReturnOrderItemVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class ReturnOrderItemHandlerImpl implements ReturnOrderItemHandler {

    @Autowired
    private OrderItemService       orderItemService;

    @Autowired
    private OrderItemDetailService orderItemDetailService;

    @Autowired
    private FileSDKClient          fileSDKClient;

    @Override
    public List<ReturnOrderItemVO> toVOList(List<ReturnOrderItem> list) {

        List<ReturnOrderItemVO> voList = new ArrayList<ReturnOrderItemVO>();
        for (ReturnOrderItem returnOrderItem : list) {
            voList.add(toVO(returnOrderItem));
        }
        return voList;
    }

    @Override
    public ReturnOrderItemVO toVO(ReturnOrderItem returnOrderItem) {

        String json = Json.toJson(returnOrderItem);
        return Json.toObject(json, ReturnOrderItemVO.class, true);
    }

    @Override
    public List<AdminReturnOrderItemVO> toVOList4Admin(List<ReturnOrderItem> list) {

        List<AdminReturnOrderItemVO> voList = new ArrayList<AdminReturnOrderItemVO>();
        for (ReturnOrderItem adminReturnOrderItem : list) {
            AdminReturnOrderItemVO vo = toVO4Admin(adminReturnOrderItem);
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
    public AdminReturnOrderItemVO toVO4Admin(ReturnOrderItem returnOrderItem) {

        String json = Json.toJson(returnOrderItem);
        return Json.toObject(json, AdminReturnOrderItemVO.class, true);
    }
}
