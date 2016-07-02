package com.qcloud.component.orderform.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.goods.model.MerchandiseItem;
import com.qcloud.component.goods.service.MerchandiseItemService;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.web.handler.OrderItemHandler;
import com.qcloud.component.orderform.web.vo.admin.AdminOrderItemVO;
import com.qcloud.component.sellercenter.model.Merchant;
import com.qcloud.component.sellercenter.service.MerchantService;
import com.qcloud.pirates.core.json.Json;

@Component
public class OrderItemHandlerImpl implements OrderItemHandler {

    @Autowired
    private MerchandiseItemService merchandiseItemService;

    @Autowired
    private MerchantService        merchantService;

//    @Autowired
//    private OrderItemDetailService orderItemDetailService;

    // @Override
    // public List<OrderItemVO> toVOList(List<OrderItem> list){
    // List<OrderItemVO> voList = new ArrayList<OrderItemVO>();
    // for (OrderItem orderItem : list) {
    // voList.add(toVO(orderItem));
    // }
    // return voList;
    // }
    //
    // @Override
    // public OrderItemVO toVO(OrderItem orderItem){
    // String json = Json.toJson(orderItem);
    // return Json.toObject(json, OrderItemVO.class, true);
    //
    // }
    @Override
    public List<AdminOrderItemVO> toVOList4Admin(List<OrderItem> list) {

        List<AdminOrderItemVO> voList = new ArrayList<AdminOrderItemVO>();
        for (OrderItem adminOrderItem : list) {
            voList.add(toVO4Admin(adminOrderItem));
        }
        for (AdminOrderItemVO adminOrderItemVO : voList) {
            MerchandiseItem m = merchandiseItemService.get(adminOrderItemVO.getUnifiedMerchandiseId());
            adminOrderItemVO.setItem(m);
            Merchant merchant = merchantService.get(adminOrderItemVO.getMerchantId());
            adminOrderItemVO.setMerchant(merchant);
        }
        return voList;
    }

    @Override
    public AdminOrderItemVO toVO4Admin(OrderItem orderItem) {

        String json = Json.toJson(orderItem);
        return Json.toObject(json, AdminOrderItemVO.class, true);
    }

//    @Override
//    public List<OrderItemVO> toOrderItemVOList(List<OrderItem> list, Date time) {
//
//        List<OrderItemVO> voList = new ArrayList<OrderItemVO>();
//        for (OrderItem orderItem : list) {
//            OrderItemVO vo = new OrderItemVO();
//            vo.setMerchantId(orderItem.getMerchantId());
//            vo.setUnifiedMerchandiseId(orderItem.getUnifiedMerchandiseId());
//            vo.setSence(orderItem.getSence());
//            vo.setDiscount(orderItem.getDiscount());
//            vo.setNumber(orderItem.getNumber());
//            vo.setSum(orderItem.getSum());
//            vo.setState(orderItem.getState());
//            // 订单详情
//            List<OrderItemDetailVO> orderItemDetailVOList = new ArrayList<OrderItemDetailVO>();
//            List<OrderItemDetail> orderItemDetailList = orderItemDetailService.listByOrderItem(orderItem.getId(), time);
//            for (OrderItemDetail orderItemDetail : orderItemDetailList) {
//                String json = Json.toJson(orderItemDetail);
//                orderItemDetailVOList.add(Json.toObject(json, OrderItemDetailVO.class, true));
//            }
//            vo.setItemDetailList(orderItemDetailVOList);
//            // 单一商品
//        }
//        return null;
//    }
}
