package com.qcloud.component.orderform.web.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.orderform.engine.OrderStateService;
import com.qcloud.component.orderform.exception.OrderformException;
import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.orderform.model.RemindRecord;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.service.CollectOrderService;
import com.qcloud.component.orderform.service.RemindRecordService;
import com.qcloud.component.orderform.service.SubOrderService;
import com.qcloud.component.orderform.web.form.OrderStateForm;
import com.qcloud.component.orderform.web.util.OrderStateType;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellerMessageType;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = OrderStateController.DIR)
public class OrderStateController {

    // URI
    public static final String  DIR = "/orderState";

    @Autowired
    private OrderStateService   orderStateService;

    @Autowired
    private CollectOrderService collectOrderService;

    @Autowired
    private SubOrderService     subOrderService;

    // @Autowired
    // private UserFilterService userFilterService;
    //
    // @Autowired
    // private TokenClient tokenClient;
    // @Autowired
    // private PersonalcenterClient personalcenterClient;
    // @Autowired
    // private AdminFilterService adminFilterService;
    // @Autowired
    // private OutdatedSellercenterClient outdatedSellercenterClient;
    @Autowired
    private SellercenterClient  sellercenterClient;

    @Autowired
    private RemindRecordService remindRecordService;

    /**
     * 商家确认订单
     * @param orderForm
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView confirmOrder(HttpServletRequest request, OrderStateForm orderStateForm) {

        // String tokenId = adminFilterService.getTokenId(request);
        // AssertUtil.assertNotEmpty(tokenId, "获取用户登录信息失败.");
        // String idStr = tokenClient.get(tokenId);
        // AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
        // long memberId = Long.parseLong(idStr);
        // // List<QMerchant> merchantList = sellercenterClient.listMerchant(memberId);
        // // Long merchantId = null;
        // // if (!CollectionUtils.isEmpty(merchantList) && merchantList.size() == 1) {
        // // merchantId = merchantList.get(0).getId();
        // // }
        // // AssertUtil.assertNotNull(merchantId, "您尚未属于一家商家.");
        // StoreMemberQuery smq = new StoreMemberQuery();
        // smq.setMemberId(memberId);
        // StoreMember storeMember = outdatedSellercenterClient.getMemberStore(smq);
        // AssertUtil.assertNotNull(storeMember, "您尚未属于一家门店.");
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
        long merchantId = store.getMerchant().getId();
        // orderStateService.exchangeSubOrderState(orderStateForm.getSubOrderId(), orderStateForm.getOrderDate(), OrderStateType.NORMAL_CONFIRM_ORDER.getKey(), merchantId);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("确认订单成功");
        return view;
    }

    /**
     * 商家订单发货
     * 
     * @param orderStateForm
     * @param logisticsNumber
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView deliverOrder(HttpServletRequest request, OrderStateForm orderStateForm, String expressCode, String expressName, String expressNumber) {

        // String tokenId = adminFilterService.getTokenId(request);
        // AssertUtil.assertNotEmpty(tokenId, "获取用户登录信息失败.");
        // String idStr = tokenClient.get(tokenId);
        // AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
        // long memberId = Long.parseLong(idStr);
        // // List<QMerchant> merchantList = sellercenterClient.listMerchant(memberId);
        // // Long merchantId = null;
        // // if (!CollectionUtils.isEmpty(merchantList) && merchantList.size() == 1) {
        // // merchantId = merchantList.get(0).getId();
        // // }
        // // AssertUtil.assertNotNull(merchantId, "您尚未属于一家商家.");
        // StoreMemberQuery smq = new StoreMemberQuery();
        // smq.setMemberId(memberId);
        // StoreMember storeMember = outdatedSellercenterClient.getMemberStore(smq);
        // AssertUtil.assertNotNull(storeMember, "您尚未属于一家门店.");
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.STORE_LOGIN_PARAMETER_KEY);
        long merchantId = store.getMerchant().getId();
        //
        Long subOrderId = orderStateForm.getSubOrderId();
        if (subOrderId != null && subOrderId > 0) {
            SubOrder subOrder = subOrderService.get(subOrderId, orderStateForm.getOrderDate());
            AssertUtil.assertNotNull(subOrder, "子订单不存在.");
            subOrder.setExpressCode(expressCode);
            subOrder.setExpressName(expressName);
            subOrder.setExpressNumber(expressNumber);
            orderStateService.exchangeSubOrderState(subOrderId, orderStateForm.getOrderDate(), OrderStateType.NORMAL_SIGN.getKey(), merchantId);
        }
        // else if (orderItemId != null && orderItemId > 0) {
        // List<OrderItemDetail> list = orderItemDetailService.listByOrderItem(orderItemId, orderStateForm.getOrderDate());
        // for (OrderItemDetail orderItemDetail : list) {
        // orderItemDetail.setLogisticsCompanyName(logisticsCompany);
        // orderItemDetail.setLogisticsNumber(logisticsNumber);
        // }
        // orderItemDetailService.update(list, orderStateForm.getOrderDate());
        // orderStateService.exchangeOrderItemState(orderItemId, orderStateForm.getOrderDate(), OrderStateType.NORMAL_SHIPPED.getKey(), merchantId);
        // }
        // else if (orderItemDetailId != null && orderItemDetailId > 0) {
        // OrderItemDetail orderItemDetail = orderItemDetailService.get(orderItemDetailId, orderStateForm.getOrderDate());
        // orderItemDetail.setLogisticsCompanyName(logisticsCompany);
        // orderItemDetail.setLogisticsNumber(logisticsNumber);
        // orderItemDetailService.update(orderItemDetail, orderStateForm.getOrderDate());
        // orderStateService.exchangeOrderItemDetailState(orderItemDetailId, orderStateForm.getOrderDate(), OrderStateType.NORMAL_SHIPPED.getKey(), merchantId);
        // }
        else {
            throw new OrderformException("订单发货参数不对,未指定发货内容.");
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("发货");
        return view;
    }

    /**
     * 买家关闭订单 -- 付款前
     * @param orderStateForm
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView cancelOrder(HttpServletRequest request, OrderStateForm orderStateForm) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        AssertUtil.assertNotNull(orderStateForm.getOrderId(), "订单ID不能为空.");
        AssertUtil.assertNotNull(orderStateForm.getOrderDate(), "订单日期不能为空.");
        CollectOrder collectOrder = collectOrderService.get(orderStateForm.getOrderId(), orderStateForm.getOrderDate());
        AssertUtil.assertNotNull(collectOrder, "订单数据不存在." + orderStateForm.getOrderId());
        if (collectOrder.getState() == OrderStateType.NORMAL_TOPAY.getKey()) {
            orderStateService.exchangeOrderState(orderStateForm.getOrderId(), orderStateForm.getOrderDate(), OrderStateType.NORMAL_CANCEL_ORDER.getKey(), user.getId());
        } else if (collectOrder.getState() == OrderStateType.NORMAL_CANCEL_ORDER.getKey()) {
            throw new OrderformException("已经取消.");
        } else if (collectOrder.getState() == OrderStateType.NORMAL_INVALID.getKey()) {
            throw new OrderformException("已经失效.");
        } else
        // if (collectOrder.getState() == OrderStateType.NORMAL_PAID.getKey() || collectOrder.getState() == OrderStateType.NORMAL_CONFIRM_ORDER.getKey()) {
        // orderService.exchangeOrderState(orderStateForm.getOrderId(), orderStateForm.getOrderDate(), OrderStateType.NORMAL_CANCEL_PAID.getKey(), user.getId());
        // } else
        {
            throw new OrderformException("关闭订单失败.");
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("关闭订单成功");
        return view;
    }

    /**
     * 提醒卖家发货
     * @param orderStateForm
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView remindDeliverGoods(HttpServletRequest request, OrderStateForm orderStateForm) {

        AssertUtil.assertNotNull(orderStateForm.getOrderDate(), "订单日期不能为空.");
        Long orderId = orderStateForm.getOrderId();
        Long subOrderId = orderStateForm.getSubOrderId();
        if (orderId != null && orderId > 0) {
            List<SubOrder> list = subOrderService.listByCollectOrder(orderId, orderStateForm.getOrderDate());
            for (SubOrder subOrder : list) {
                boolean remind = remindRecordService.canRemind(subOrder.getId(), orderStateForm.getOrderDate());
                if (remind) {
                    RemindRecord remindRecord = new RemindRecord();
                    remindRecord.setOrderDate(orderStateForm.getOrderDate());
                    remindRecord.setOrderId(orderId);
                    remindRecord.setSubOrderId(subOrder.getId());
                    remindRecord.setTime(new Date());
                    remindRecordService.add(remindRecord);
                    sellercenterClient.sendMsgToStore(subOrder.getStoreId(), SellerMessageType.DELIVER_GOODS, "发货提醒", "亲,您的顾客等不急了,订单[子单号:" + subOrder.getOrderNumber() + "]发货了吗?");
                }
            }
        } else if (subOrderId != null && subOrderId > 0) {
            SubOrder subOrder = subOrderService.get(subOrderId, orderStateForm.getOrderDate());
            AssertUtil.assertNotNull(subOrder, "子单不存在." + subOrderId);
            boolean remind = remindRecordService.canRemind(subOrder.getId(), orderStateForm.getOrderDate());
            if (remind) {
                RemindRecord remindRecord = new RemindRecord();
                remindRecord.setOrderDate(orderStateForm.getOrderDate());
                remindRecord.setOrderId(orderId);
                remindRecord.setSubOrderId(subOrder.getId());
                remindRecord.setTime(new Date());
                remindRecordService.add(remindRecord);
                sellercenterClient.sendMsgToMerchant(subOrder.getMerchantId(), SellerMessageType.DELIVER_GOODS, "发货提醒", "亲,您的顾客等不急了,订单[子单号:" + subOrder.getOrderNumber() + "]发货了吗?");
                sellercenterClient.sendMsgToStore(subOrder.getStoreId(), SellerMessageType.DELIVER_GOODS, "发货提醒", "亲,您的顾客等不急了,订单[子单号:" + subOrder.getOrderNumber() + "]发货了吗?");
            }
        } else {
            throw new OrderformException("提醒发货参数不对,应指定订单或者发货商家.");
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("提醒发货成功.");
        return view;
    }

    /**
     * 买家确认订单
     * @param orderStateForm
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView signOrder(HttpServletRequest request, OrderStateForm orderStateForm) {

        AssertUtil.assertNotNull(orderStateForm.getOrderDate(), "订单日期不能为空.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        Long orderId = orderStateForm.getOrderId();
        Long subOrderId = orderStateForm.getSubOrderId();
        // Long orderItemId = orderStateForm.getOrderItemId();
        // Long orderItemDetailId = orderStateForm.getOrderItemDetailId();
        if (orderId != null && orderId > 0) {
            CollectOrder collectOrder = collectOrderService.get(orderStateForm.getOrderId(), orderStateForm.getOrderDate());
            AssertUtil.assertNotNull(collectOrder, "订单数据不存在." + orderStateForm.getOrderId());
            if (collectOrder.getState() == OrderStateType.NORMAL_FINISHED.getKey()) {
                throw new OrderformException("已经签收.");
            }
            orderStateService.exchangeOrderState(orderId, orderStateForm.getOrderDate(), OrderStateType.NORMAL_FINISHED.getKey(), user.getId());
        } else if (subOrderId != null && subOrderId > 0) {
            SubOrder subOrder = subOrderService.get(subOrderId, orderStateForm.getOrderDate());
            AssertUtil.assertNotNull(subOrder, "订单数据不存在." + orderStateForm.getOrderId());
            if (subOrder.getState() == OrderStateType.NORMAL_FINISHED.getKey()) {
                throw new OrderformException("已经签收.");
            }
            orderStateService.exchangeSubOrderState(subOrderId, orderStateForm.getOrderDate(), OrderStateType.NORMAL_FINISHED.getKey(), user.getId());
        }
        // else if (orderItemId != null && orderItemId > 0) {
        // orderStateService.exchangeOrderItemState(orderItemId, orderStateForm.getOrderDate(), OrderStateType.NORMAL_SIGN.getKey(), user.getId());
        // } else if (orderItemDetailId != null && orderItemDetailId > 0) {
        // orderStateService.exchangeOrderItemDetailState(orderItemDetailId, orderStateForm.getOrderDate(), OrderStateType.NORMAL_SIGN.getKey(), user.getId());
        // }
        else {
            throw new OrderformException("签收订单参数不对,未指定签收内容.");
        }
        // // -------------------------测试代码，正式环境不能布 这是无用代码-------------------------------------
        // OrderEntity orderEntity = orderSelecterService.getOrder(orderId, orderStateForm.getOrderDate());
        // List<MerchantOrderEntity> merchantOrderList = orderEntity.getEntityList();
        // for (MerchantOrderEntity merchantOrder : merchantOrderList) {
        // double purchaseSum = 0.0;
        // double discountSum = 0.0;
        // double priceSum = 0.0;
        // List<OrderItemEntity> list = merchantOrder.getEntityList();
        // for (OrderItemEntity item : list) {
        // // 正常购买单品
        // // TODO 记录item id 才合理
        // if (item.getSence() == -1) {
        // distributionClient.addMerchandiseDealRecords(orderEntity.getUserId(), item.getUnifiedMerchandiseId(), item.getPurchase(), item.getDiscount(), item.getPrice(), item.getNumber(), orderEntity.getId(), item.getId(), orderEntity.getOrderDate());
        // purchaseSum += item.getPurchase() * item.getNumber();
        // discountSum += item.getSum();
        // priceSum += item.getPrice() * item.getNumber();
        // }
        // }
        // if (discountSum > 0) {
        // distributionClient.addMerchantDealRecords(orderEntity.getUserId(), merchantOrder.getMerchantId(), purchaseSum, discountSum, priceSum, merchantOrder.getId(), orderEntity.getOrderDate());
        // }
        // }
        // // -------------------------测试代码-------------------------------------
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("签收成功");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView deleteOrder(HttpServletRequest request, Date orderDate, Long orderId) {

        AssertUtil.assertNotNull(orderDate, "订单日期不能为空.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        CollectOrder collectOrder = collectOrderService.get(orderId, orderDate);
        AssertUtil.assertNotNull(collectOrder, "订单数据不存在.");
        AssertUtil.assertTrue(collectOrder.getUserId() == user.getId(), "不允许删除别人的订单哦.");
        AssertUtil.assertTrue(collectOrder.getState() == OrderStateType.NORMAL_FINISHED.getKey(), "订单已经失效.");
        orderStateService.exchangeOrderState(orderId, orderDate, OrderStateType.NORMAL_FINISHED_DELETE.getKey(), -999L);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("删除订单成功.");
        return view;
    }

    @RequestMapping
    public FrontAjaxView testExchagneOrderState(OrderStateForm orderStateForm, Integer state) {

        // 正式环境必须注释以下代码
        orderStateService.exchangeOrderState(orderStateForm.getOrderId(), orderStateForm.getOrderDate(), state, -999L);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("成功");
        return view;
    }

    @RequestMapping
    public FrontAjaxView virtualPay(OrderStateForm orderStateForm) {

        // 正式环境必须注释以下代码
        orderStateService.exchangeOrderState(orderStateForm.getOrderId(), orderStateForm.getOrderDate(), OrderStateType.NORMAL_TO_PACKING.getKey(), -999L);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("成功");
        return view;
    }
}
