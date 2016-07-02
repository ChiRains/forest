package com.qcloud.component.orderform.engine.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.orderform.engine.OrderSelecterService;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.entity.OrderItemDetailEntity;
import com.qcloud.component.orderform.entity.OrderItemEntity;
import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.model.OrderItemDetail;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.service.CollectOrderService;
import com.qcloud.component.orderform.service.OrderConfigService;
import com.qcloud.component.orderform.service.OrderItemDetailService;
import com.qcloud.component.orderform.service.OrderItemService;
import com.qcloud.component.orderform.service.RemindRecordService;
import com.qcloud.component.orderform.service.SubOrderService;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class OrderSelecterServiceImpl implements OrderSelecterService {

    @Autowired
    private CollectOrderService    collectOrderService;

    @Autowired
    private SubOrderService        subOrderService;

    @Autowired
    private OrderItemService       orderItemService;

    @Autowired
    private OrderItemDetailService orderItemDetailService;

    // @Autowired
    // private AfterSaleSelecterService afterSaleSelecterService;
    @Autowired
    private OrderConfigService     orderConfigService;

    @Autowired
    private RemindRecordService    remindRecordService;

    @Override
    public OrderEntity getOrder(Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(orderId, "订单ID不能为空." + orderId);
        CollectOrder collectOrder = collectOrderService.get(orderId, orderDate);
        AssertUtil.assertNotNull(collectOrder, "订单不存在." + orderId);
        List<SubOrder> subOrderList = subOrderService.listByCollectOrder(orderId, orderDate);
        List<OrderItem> orderItemList = orderItemService.listByCollectOrder(orderId, orderDate);
        List<OrderItemDetail> orderItemDetailList = orderItemDetailService.listByCollectOrder(orderId, orderDate);
        OrderEntity orderEntity = modelToEntity(collectOrder, subOrderList, orderItemList, orderItemDetailList);
        return orderEntity;
    }

    @Override
    public MerchantOrderEntity getMerchantOrder(Long subOrderId, Date orderDate) {

        AssertUtil.assertNotNull(subOrderId, "子订单ID不能为空." + subOrderId);
        SubOrder subOrder = subOrderService.get(subOrderId, orderDate);
        AssertUtil.assertNotNull(subOrder, "子订单不存在." + subOrderId);
        // 获取总单
        CollectOrder collectOrder = collectOrderService.get(subOrder.getOrderId(), orderDate);
        // 获取订单项
        List<OrderItem> orderItemList = orderItemService.listBySubOrder(subOrderId, orderDate);
        // 获取订单明细 by 子单ID
        List<OrderItemDetail> orderItemDetailList = orderItemDetailService.listBySubOrder(subOrderId, orderDate);
        OrderEntity orderEntity = modelToEntity(collectOrder, new ArrayList<SubOrder>(), new ArrayList<OrderItem>(), new ArrayList<OrderItemDetail>());
        MerchantOrderEntity merchantOrderEntity = modelToEntity(orderEntity, subOrder, orderItemList, orderItemDetailList);
        List<MerchantOrderEntity> merchantOrderList = new ArrayList<MerchantOrderEntity>();
        merchantOrderList.add(merchantOrderEntity);
        orderEntity.setMerchantOrderList(merchantOrderList);
        // List<AfterSaleOrder> list = afterSaleSelecterService.listAfterSaleOrder(orderEntity);
        // orderEntity.setAfterSaleList(list);
        // spiltAfterSaleOrder(orderEntity);
        return merchantOrderEntity;
    }

    private OrderEntity modelToEntity(CollectOrder collectOrder, List<SubOrder> subOrderList, List<OrderItem> orderItemList, List<OrderItemDetail> orderItemDetailList) {

        OrderEntity orderEntity = new OrderEntity(collectOrder);
        orderEntity.setCanRefund(orderConfigService.canRefundInNormal(collectOrder.getState()));
        orderEntity.setCanReturn(orderConfigService.canReturnInNormal(collectOrder.getState()));
        orderEntity.setCanExchange(orderConfigService.canExchangeInNormal(collectOrder.getState()));
        orderEntity.setUserState(orderConfigService.getNormalPersonalOrderState(collectOrder.getState()));
        orderEntity.setUserStateStr(orderConfigService.getNormalPersonalOrderStateDesc(collectOrder.getState()));
        // orderEntity.setAddress(collectOrder.getAddress());
        // orderEntity.setConsignee(collectOrder.getConsignee());
        // orderEntity.setDeliveryTimeStr(collectOrder.getDeliveryTimeStr());
        // orderEntity.setExplain(collectOrder.getExplain());
        // orderEntity.setMobile(collectOrder.getMobile());
        // orderEntity.setOrderDate(collectOrder.getTime());
        // orderEntity.setOrderNumber(collectOrder.getOrderNumber());
        // orderEntity.setSum(collectOrder.getSum());
        // orderEntity.setCash(collectOrder.getCash());
        // orderEntity.setUserId(collectOrder.getUserId());
        // orderEntity.setId(collectOrder.getId());
        // orderEntity.setState(collectOrder.getState());
        // orderEntity.setPickupAddressStr(collectOrder.getPickupAddressStr());
        // orderEntity.setNeedInvoiceType(collectOrder.getNeedInvoice());
        // orderEntity.setInvoiceType(collectOrder.getInvoiceType());
        // orderEntity.setDeliveryMode(collectOrder.getDeliveryMode());
        // orderEntity.setCoupon(collectOrder.getCoupon());
        List<MerchantOrderEntity> merchantOrderList = new ArrayList<MerchantOrderEntity>();
        for (SubOrder subOrder : subOrderList) {
            // 筛选出属于指定的子单条目
            List<OrderItem> subItemList = new ArrayList<OrderItem>();
            for (OrderItem orderItem : orderItemList) {
                if (orderItem.getSubOrderId() == subOrder.getId()) {
                    subItemList.add(orderItem);
                }
            }
            // 筛选出属于指定的子单明细
            List<OrderItemDetail> subItemDetailList = new ArrayList<OrderItemDetail>();
            for (OrderItemDetail orderItemDetail : orderItemDetailList) {
                if (orderItemDetail.getSubOrderId() == subOrder.getId()) {
                    subItemDetailList.add(orderItemDetail);
                }
            }
            merchantOrderList.add(modelToEntity(orderEntity, subOrder, subItemList, subItemDetailList));
        }
        orderEntity.setMerchantOrderList(merchantOrderList);
        // List<AfterSaleOrder> list = afterSaleSelecterService.listAfterSaleOrder(orderEntity);
        // orderEntity.setAfterSaleList(list);
        // spiltAfterSaleOrder(orderEntity);
        return orderEntity;
    }

    // private void spiltAfterSaleOrder(OrderEntity orderEntity) {
    //
    // List<AfterSaleOrder> afterSaleOrderList = orderEntity.getAfterSaleList();
    // for (AfterSaleOrder afterSaleOrder : afterSaleOrderList) {
    // afterSaleOrder.getMerchantOrder().getAfterSaleList().add(afterSaleOrder);
    // List<AfterSaleOrderItem> list = afterSaleOrder.getList();
    // for (AfterSaleOrderItem afterSaleOrderItem : list) {
    // afterSaleOrderItem.getOrderItemDetail().getAfterSaleItemList().add(afterSaleOrderItem);
    // afterSaleOrderItem.getOrderItemDetail().getOrderItem().getAfterSaleItemList().add(afterSaleOrderItem);
    // }
    // }
    // }
    private MerchantOrderEntity modelToEntity(OrderEntity orderEntity, SubOrder subOrder, List<OrderItem> orderItemList, List<OrderItemDetail> orderItemDetailList) {

        MerchantOrderEntity merchantOrderEntity = new MerchantOrderEntity(orderEntity, subOrder);
        merchantOrderEntity.setCanRefund(orderConfigService.canRefundInNormal(subOrder.getState()));
        merchantOrderEntity.setCanReturn(orderConfigService.canReturnInNormal(subOrder.getState()));
        merchantOrderEntity.setCanExchange(orderConfigService.canExchangeInNormal(subOrder.getState()));
        merchantOrderEntity.setCanRemind(remindRecordService.canRemind(subOrder.getId(), orderEntity.getOrderDate()));
        // merchantOrderEntity.setOrderNumber(subOrder.getOrderNumber());
        // merchantOrderEntity.setSum(subOrder.getSum());
        // merchantOrderEntity.setId(subOrder.getId());
        // merchantOrderEntity.setMerchantId(subOrder.getMerchantId());
        // merchantOrderEntity.setState(subOrder.getState());
        // merchantOrderEntity.setStoreId(subOrder.getStoreId());
        List<OrderItemEntity> orderItemEntityList = new ArrayList<OrderItemEntity>();
        for (OrderItem orderItem : orderItemList) {
            // 筛选出属于指定的item明细
            List<OrderItemDetail> subItemDetailList = new ArrayList<OrderItemDetail>();
            for (OrderItemDetail orderItemDetail : orderItemDetailList) {
                if (orderItemDetail.getOrderItemId() == orderItem.getId()) {
                    subItemDetailList.add(orderItemDetail);
                }
            }
            orderItemEntityList.add(modelToEntity(orderEntity, merchantOrderEntity, orderItem, subItemDetailList));
        }
        merchantOrderEntity.setOrderItemList(orderItemEntityList);
        return merchantOrderEntity;
    }

    private OrderItemEntity modelToEntity(OrderEntity orderEntity, MerchantOrderEntity merchantOrderEntity, OrderItem orderItem, List<OrderItemDetail> orderItemDetailList) {

        OrderItemEntity orderItemEntity = new OrderItemEntity(orderEntity, merchantOrderEntity, orderItem);
        // orderItemEntity.setName(orderItem.getName());
        // orderItemEntity.setImage(orderItem.getImage());
        // orderItemEntity.setDiscount(orderItem.getDiscount());
        // orderItemEntity.setNumber(orderItem.getNumber());
        // orderItemEntity.setUnifiedMerchandiseId(orderItem.getUnifiedMerchandiseId());
        // orderItemEntity.setSence(orderItem.getSence());
        // orderItemEntity.setPurchase(orderItem.getPurchase());
        // orderItemEntity.setPrice(orderItem.getPrice());
        // orderItemEntity.setState(orderItem.getState());
        // orderItemEntity.setSum(orderItem.getSum());
        // orderItemEntity.setId(orderItem.getId());
        List<OrderItemDetailEntity> orderItemDetailEntityList = new ArrayList<OrderItemDetailEntity>();
        for (OrderItemDetail orderItemDetail : orderItemDetailList) {
            orderItemDetailEntityList.add(modelToEntity(orderEntity, merchantOrderEntity, orderItemEntity, orderItemDetail));
        }
        orderItemEntity.setOrderItemDetailList(orderItemDetailEntityList);
        return orderItemEntity;
    }

    private OrderItemDetailEntity modelToEntity(OrderEntity orderEntity, MerchantOrderEntity merchantOrderEntity, OrderItemEntity orderItemEntity, OrderItemDetail orderItemDetail) {

        OrderItemDetailEntity orderItemDetailEntity = new OrderItemDetailEntity(orderEntity, merchantOrderEntity, orderItemEntity, orderItemDetail);
        // orderItemDetailEntity.setUnifiedMerchandiseId(orderItemDetail.getUnifiedMerchandiseId());
        // orderItemDetailEntity.setMerchandiseItemId(orderItemDetail.getMerchandiseItemId());
        // orderItemDetailEntity.setName(orderItemDetail.getName());
        // orderItemDetailEntity.setImage(orderItemDetail.getImage());
        // orderItemDetailEntity.setCode(orderItemDetail.getCode());
        // orderItemDetailEntity.setSpecifications(orderItemDetail.getSpecifications());
        // orderItemDetailEntity.setLogisticsCompany(orderItemDetail.getLogisticsCompany());
        // orderItemDetailEntity.setLogisticsNumber(orderItemDetail.getLogisticsNumber());
        // orderItemDetailEntity.setState(orderItemDetail.getState());
        // orderItemDetailEntity.setId(orderItemDetail.getId());
        // orderItemDetailEntity.setNumber(orderItemDetail.getNumber());
        return orderItemDetailEntity;
    }
}
