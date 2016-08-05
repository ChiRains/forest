package com.qcloud.component.orderform.web.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.my.MyOrderStateType;
import com.qcloud.component.orderform.engine.AfterSaleSelecterService;
import com.qcloud.component.orderform.engine.AfterSaleStateService;
import com.qcloud.component.orderform.engine.OrderSelecterService;
import com.qcloud.component.orderform.entity.ExchangeAfterSaleOrder;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.RefundAfterSaleOrder;
import com.qcloud.component.orderform.entity.ReturnAfterSaleOrder;
import com.qcloud.component.orderform.model.ExchangeOrder;
import com.qcloud.component.orderform.model.RefundOrder;
import com.qcloud.component.orderform.model.ReturnOrder;
import com.qcloud.component.orderform.service.ExchangeOrderService;
import com.qcloud.component.orderform.service.RefundOrderService;
import com.qcloud.component.orderform.service.ReturnOrderService;
import com.qcloud.component.orderform.web.util.ExchangeOrderStateType;
import com.qcloud.component.orderform.web.util.OrderStateType;
import com.qcloud.component.orderform.web.util.RefundOrderStateType;
import com.qcloud.component.orderform.web.util.ReturnOrderStateType;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicservice.SmsClient;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.key.TypeEnum.MerchantOrderStateType;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;

@Controller
@RequestMapping(value = AfterSaleStateController.DIR)
public class AfterSaleStateController {

    public static final String       DIR = "/afterSaleState";

    @Autowired
    private OrderSelecterService     orderSelecterService;

    @Autowired
    private AfterSaleSelecterService afterSaleSelecterService;

    @Autowired
    private AfterSaleStateService    afterSaleStateService;

    @Autowired
    private ExchangeOrderService     exchangeOrderService;

    @Autowired
    private ReturnOrderService       returnOrderService;

    @Autowired
    private RefundOrderService       refundOrderService;

    @Autowired
    private SellercenterClient       sellercenterClient;

    @Autowired
    private PersonalcenterClient     personalcenterClient;

    @Autowired
    private MyClient                 myClient;

    @Autowired
    private SmsClient                smsClient;

    // 卖家确认退款
    @PiratesApp
    @RequestMapping
    public FrontAjaxView confirmRefund(HttpServletRequest request, Long refundId, Double sum) {

        RefundOrder refundOrder = refundOrderService.get(refundId);
        AssertUtil.assertNotNull(refundOrder, "退款单不存在." + refundId);
        refundOrder.setSum(sum);
        refundOrderService.update(refundOrder);
        MerchantOrderEntity merchantOrder = orderSelecterService.getMerchantOrder(refundOrder.getSubOrderId(), refundOrder.getOrderDate());
        RefundAfterSaleOrder refundAfterSaleOrder = afterSaleSelecterService.getRefundOrder(merchantOrder, refundId);
        afterSaleStateService.exchangeRefundOrderState(refundAfterSaleOrder, RefundOrderStateType.REFUND_CONFIRM.getKey());
        // 短信发送到商家手机
        String refundNumber = refundOrder.getRefundNumber();
        Map<String, String> map = new HashMap<String, String>();
        map.put("${orderNumber}", refundNumber);
        sellercenterClient.sendSmsToMerchant(merchantOrder.getMerchantId(), "personalcenter-user-sms-refund-agree-notifyTemplate", map);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("确认退款单成功.");
        return view;
    }

    // 卖家拒绝退款
    @PiratesApp
    @RequestMapping
    public FrontAjaxView refuseRefund(HttpServletRequest request, Long refundId) {

        RefundOrder refundOrder = refundOrderService.get(refundId);
        AssertUtil.assertNotNull(refundOrder, "退款单不存在." + refundId);
        MerchantOrderEntity merchantOrder = orderSelecterService.getMerchantOrder(refundOrder.getSubOrderId(), refundOrder.getOrderDate());
        RefundAfterSaleOrder refundAfterSaleOrder = afterSaleSelecterService.getRefundOrder(merchantOrder, refundId);
        afterSaleStateService.exchangeRefundOrderState(refundAfterSaleOrder, RefundOrderStateType.REFUND_REFUSE.getKey());
        // 拒绝退款，订单状态还原到待配货
        myClient.updateMyOrderFormState(refundOrder.getUserId(), refundOrder.getOrderId(), MyOrderStateType.TO_PACKING.getKey());
        if (OrderStateType.NORMAL_TO_PACKING.getKey() == merchantOrder.getState()) {
            sellercenterClient.updateOrderFormState(refundOrder.getMerchantId(), refundOrder.getSubOrderId(), MerchantOrderStateType.TO_PACKING.getKey());
        }
        // } else if (OrderStateType.NORMAL_CONFIRM_ORDER.getKey() == merchantOrder.getState()) {
        // sellercenterClient.updateOrderFormState(refundOrder.getMerchantId(), refundOrder.getSubOrderId(), MerchantOrderStateType.SHIP.getKey());
        // }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("拒绝退款单成功.");
        return view;
    }

    // 卖家退款
    @PiratesApp
    @RequestMapping
    public FrontAjaxView payRefund(HttpServletRequest request, Long refundId) {

        RefundOrder refundOrder = refundOrderService.get(refundId);
        AssertUtil.assertNotNull(refundOrder, "退款单不存在." + refundId);
        MerchantOrderEntity merchantOrder = orderSelecterService.getMerchantOrder(refundOrder.getSubOrderId(), refundOrder.getOrderDate());
        RefundAfterSaleOrder refundAfterSaleOrder = afterSaleSelecterService.getRefundOrder(merchantOrder, refundId);
        afterSaleStateService.exchangeRefundOrderState(refundAfterSaleOrder, RefundOrderStateType.REFUND_PAID.getKey());
        // 发送退款信息给用户
        String orderNumber = merchantOrder.getOrder().getOrderNumber();
        QUser user = personalcenterClient.getUser(merchantOrder.getOrder().getUserId());
        Map<String, String> map = new HashMap<String, String>();
        map.put("${orderNumber}", orderNumber);
        smsClient.send("personalcenter-user-sms-pay-refund-notifyTemplate", user.getMobile(), map);
        //
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("卖家退款成功");
        return view;
    }

    // 买家确认退款
    @PiratesApp
    @RequestMapping
    public FrontAjaxView confirmPayRefund(HttpServletRequest request, Long refundId) {

        RefundOrder refundOrder = refundOrderService.get(refundId);
        AssertUtil.assertNotNull(refundOrder, "退款单不存在." + refundId);
        MerchantOrderEntity merchantOrder = orderSelecterService.getMerchantOrder(refundOrder.getSubOrderId(), refundOrder.getOrderDate());
        RefundAfterSaleOrder refundAfterSaleOrder = afterSaleSelecterService.getRefundOrder(merchantOrder, refundId);
        afterSaleStateService.exchangeRefundOrderState(refundAfterSaleOrder, RefundOrderStateType.REFUND_CONFIRM_PAID.getKey());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("退款成功");
        return view;
    }

    // 买家取消申请
    @PiratesApp
    @RequestMapping
    public FrontAjaxView cancelRefund(HttpServletRequest request, Long refundId) {

        RefundOrder refundOrder = refundOrderService.get(refundId);
        AssertUtil.assertNotNull(refundOrder, "退款单不存在." + refundId);
        MerchantOrderEntity merchantOrder = orderSelecterService.getMerchantOrder(refundOrder.getSubOrderId(), refundOrder.getOrderDate());
        RefundAfterSaleOrder refundAfterSaleOrder = afterSaleSelecterService.getRefundOrder(merchantOrder, refundId);
        afterSaleStateService.exchangeRefundOrderState(refundAfterSaleOrder, RefundOrderStateType.REFUND_CLOSE.getKey());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("取消退款成功");
        return view;
    }

    // ******************************************************************
    // 卖家确认退货
    @PiratesApp
    @RequestMapping
    public FrontAjaxView confirmReturn(HttpServletRequest request, Long returnId, Double sum) {

        ReturnOrder returnOrder = returnOrderService.get(returnId);
        AssertUtil.assertNotNull(returnOrder, "退货单不存在." + returnId);
        returnOrder.setSum(sum);
        returnOrderService.update(returnOrder);
        MerchantOrderEntity merchantOrder = orderSelecterService.getMerchantOrder(returnOrder.getSubOrderId(), returnOrder.getOrderDate());
        ReturnAfterSaleOrder returnAfterSaleOrder = afterSaleSelecterService.getReturnOrder(merchantOrder, returnId);
        afterSaleStateService.exchangeReturnOrderState(returnAfterSaleOrder, ReturnOrderStateType.RETURN_CONFIRM.getKey());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("确认退货单成功.");
        return view;
    }

    // 卖家拒绝退货
    @PiratesApp
    @RequestMapping
    public FrontAjaxView refuseReturn(HttpServletRequest request, Long returnId) {

        ReturnOrder returnOrder = returnOrderService.get(returnId);
        AssertUtil.assertNotNull(returnOrder, "退货单不存在." + returnId);
        MerchantOrderEntity merchantOrder = orderSelecterService.getMerchantOrder(returnOrder.getSubOrderId(), returnOrder.getOrderDate());
        ReturnAfterSaleOrder returnAfterSaleOrder = afterSaleSelecterService.getReturnOrder(merchantOrder, returnId);
        afterSaleStateService.exchangeReturnOrderState(returnAfterSaleOrder, ReturnOrderStateType.RETURN_REFUSE.getKey());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("拒绝退货单成功.");
        return view;
    }

    // 买家发货
    @PiratesApp
    @RequestMapping
    public FrontAjaxView shippedReturn(HttpServletRequest request, Long returnId, String logisticsCompany, String logisticsNumber) {

        ReturnOrder returnOrder = returnOrderService.get(returnId);
        AssertUtil.assertNotNull(returnOrder, "退货单不存在." + returnId);
        MerchantOrderEntity merchantOrder = orderSelecterService.getMerchantOrder(returnOrder.getSubOrderId(), returnOrder.getOrderDate());
        ReturnAfterSaleOrder returnAfterSaleOrder = afterSaleSelecterService.getReturnOrder(merchantOrder, returnId);
        ReturnOrder ro = returnAfterSaleOrder.getReturnOrder();
        ro.setLogisticsCompany(logisticsCompany);
        ro.setLogisticsNumber(logisticsNumber);
        returnOrderService.update(ro);
        afterSaleStateService.exchangeReturnOrderState(returnAfterSaleOrder, ReturnOrderStateType.RETURN_SHIPPED.getKey());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("买家发货成功");
        return view;
    }

    // 卖家签收
    @PiratesApp
    @RequestMapping
    public FrontAjaxView signReturn(HttpServletRequest request, Long returnId) {

        ReturnOrder returnOrder = returnOrderService.get(returnId);
        AssertUtil.assertNotNull(returnOrder, "退货单不存在." + returnId);
        MerchantOrderEntity merchantOrder = orderSelecterService.getMerchantOrder(returnOrder.getSubOrderId(), returnOrder.getOrderDate());
        ReturnAfterSaleOrder returnAfterSaleOrder = afterSaleSelecterService.getReturnOrder(merchantOrder, returnId);
        afterSaleStateService.exchangeReturnOrderState(returnAfterSaleOrder, ReturnOrderStateType.RETURN_SIGN.getKey());
        // 短信发送到商家手机
        String returnNumber = returnOrder.getReturnNumber();
        Map<String, String> map = new HashMap<String, String>();
        map.put("${orderNumber}", returnNumber);
        sellercenterClient.sendSmsToMerchant(merchantOrder.getMerchantId(), "personalcenter-user-sms-return-receive-notifyTemplate", map);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("卖家签收成功");
        return view;
    }

    // 卖家退货+退款
    @PiratesApp
    @RequestMapping
    public FrontAjaxView payReturn(HttpServletRequest request, Long returnId) {

        ReturnOrder returnOrder = returnOrderService.get(returnId);
        AssertUtil.assertNotNull(returnOrder, "退货单不存在." + returnId);
        MerchantOrderEntity merchantOrder = orderSelecterService.getMerchantOrder(returnOrder.getSubOrderId(), returnOrder.getOrderDate());
        ReturnAfterSaleOrder returnAfterSaleOrder = afterSaleSelecterService.getReturnOrder(merchantOrder, returnId);
        afterSaleStateService.exchangeReturnOrderState(returnAfterSaleOrder, ReturnOrderStateType.RETURN_PAID.getKey());
        // 发送退款信息给用户
        String orderNumber = merchantOrder.getOrder().getOrderNumber();
        QUser user = personalcenterClient.getUser(merchantOrder.getOrder().getUserId());
        Map<String, String> map = new HashMap<String, String>();
        map.put("${orderNumber}", orderNumber);
        smsClient.send("personalcenter-user-sms-pay-refund-notifyTemplate", user.getMobile(), map);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("卖家退款成功");
        return view;
    }

    // 买家确认退款
    @PiratesApp
    @RequestMapping
    public FrontAjaxView confirmPayReturn(HttpServletRequest request, Long returnId) {

        // ReturnOrder returnOrder = returnOrderService.get(returnId);
        // AssertUtil.assertNotNull(returnOrder, "退货单不存在." + returnId);
        // MerchantOrderEntity merchantOrder = orderSelecterService.getMerchantOrder(returnOrder.getSubOrderId(), returnOrder.getOrderDate());
        // ReturnAfterSaleOrder returnAfterSaleOrder = afterSaleSelecterService.getReturnOrder(merchantOrder, returnId);
        // afterSaleStateService.exchangeReturnOrderState(returnAfterSaleOrder, ReturnOrderStateType.RETURN_CONFIRM_PAID.getKey());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("退款成功");
        return view;
    }

    // ******************************************************************
    // 卖家确认换货
    @PiratesApp
    @RequestMapping
    public FrontAjaxView confirmExchange(HttpServletRequest request, Long exchangeId) {

        ExchangeOrder exchangeOrder = exchangeOrderService.get(exchangeId);
        AssertUtil.assertNotNull(exchangeOrder, "换货单不存在." + exchangeId);
        MerchantOrderEntity merchantOrder = orderSelecterService.getMerchantOrder(exchangeOrder.getSubOrderId(), exchangeOrder.getOrderDate());
        ExchangeAfterSaleOrder exchangeAfterSaleOrder = afterSaleSelecterService.getExchangeOrder(merchantOrder, exchangeId);
        afterSaleStateService.exchangeExchangeOrderState(exchangeAfterSaleOrder, ExchangeOrderStateType.EXCHANGE_CONFIRM.getKey());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("确认换货单成功");
        return view;
    }

    // 卖家拒绝换货
    @PiratesApp
    @RequestMapping
    public FrontAjaxView refuseExchange(HttpServletRequest request, Long exchangeId) {

        ExchangeOrder exchangeOrder = exchangeOrderService.get(exchangeId);
        AssertUtil.assertNotNull(exchangeOrder, "换货单不存在." + exchangeId);
        MerchantOrderEntity merchantOrder = orderSelecterService.getMerchantOrder(exchangeOrder.getSubOrderId(), exchangeOrder.getOrderDate());
        ExchangeAfterSaleOrder exchangeAfterSaleOrder = afterSaleSelecterService.getExchangeOrder(merchantOrder, exchangeId);
        afterSaleStateService.exchangeExchangeOrderState(exchangeAfterSaleOrder, ExchangeOrderStateType.EXCHANGE_REFUSE.getKey());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("拒绝换货单成功");
        return view;
    }

    // 买家发货
    @PiratesApp
    @RequestMapping
    public FrontAjaxView shippedExchange(HttpServletRequest request, Long exchangeId, String logisticsCompany, String logisticsNumber) {

        ExchangeOrder exchangeOrder = exchangeOrderService.get(exchangeId);
        AssertUtil.assertNotNull(exchangeOrder, "换货单不存在." + exchangeId);
        MerchantOrderEntity merchantOrder = orderSelecterService.getMerchantOrder(exchangeOrder.getSubOrderId(), exchangeOrder.getOrderDate());
        ExchangeAfterSaleOrder exchangeAfterSaleOrder = afterSaleSelecterService.getExchangeOrder(merchantOrder, exchangeId);
        ExchangeOrder eo = exchangeAfterSaleOrder.getExchangeOrder();
        eo.setUserLogisticsCompany(logisticsCompany);
        eo.setUserLogisticsNumber(logisticsNumber);
        exchangeOrderService.update(eo);
        afterSaleStateService.exchangeExchangeOrderState(exchangeAfterSaleOrder, ExchangeOrderStateType.EXCHANGE_SHIPPED.getKey());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("换货单发货成功");
        return view;
    }

    // 卖家签收
    @PiratesApp
    @RequestMapping
    public FrontAjaxView signExchange(HttpServletRequest request, Long exchangeId) {

        ExchangeOrder exchangeOrder = exchangeOrderService.get(exchangeId);
        AssertUtil.assertNotNull(exchangeOrder, "换货单不存在." + exchangeId);
        MerchantOrderEntity merchantOrder = orderSelecterService.getMerchantOrder(exchangeOrder.getSubOrderId(), exchangeOrder.getOrderDate());
        ExchangeAfterSaleOrder exchangeAfterSaleOrder = afterSaleSelecterService.getExchangeOrder(merchantOrder, exchangeId);
        afterSaleStateService.exchangeExchangeOrderState(exchangeAfterSaleOrder, ExchangeOrderStateType.EXCHANGE_SIGN.getKey());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("换货单签收成功");
        return view;
    }

    // 卖家再次发货
    @PiratesApp
    @RequestMapping
    public FrontAjaxView shippedAgainExchange(HttpServletRequest request, Long exchangeId, String logisticsCompany, String logisticsNumber) {

        ExchangeOrder exchangeOrder = exchangeOrderService.get(exchangeId);
        AssertUtil.assertNotNull(exchangeOrder, "换货单不存在." + exchangeId);
        MerchantOrderEntity merchantOrder = orderSelecterService.getMerchantOrder(exchangeOrder.getSubOrderId(), exchangeOrder.getOrderDate());
        ExchangeAfterSaleOrder exchangeAfterSaleOrder = afterSaleSelecterService.getExchangeOrder(merchantOrder, exchangeId);
        ExchangeOrder eo = exchangeAfterSaleOrder.getExchangeOrder();
        eo.setMerchantLogisticsCompany(logisticsCompany);
        eo.setMerchantLogisticsNumber(logisticsNumber);
        exchangeOrderService.update(eo);
        afterSaleStateService.exchangeExchangeOrderState(exchangeAfterSaleOrder, ExchangeOrderStateType.EXCHANGE_SHIPPED_AGAIN.getKey());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("换货单再次发货成功");
        return view;
    }

    // 买家再次确认
    @PiratesApp
    @RequestMapping
    public FrontAjaxView signAgainExchange(HttpServletRequest request, Long exchangeId) {

        ExchangeOrder exchangeOrder = exchangeOrderService.get(exchangeId);
        AssertUtil.assertNotNull(exchangeOrder, "换货单不存在." + exchangeId);
        MerchantOrderEntity merchantOrder = orderSelecterService.getMerchantOrder(exchangeOrder.getSubOrderId(), exchangeOrder.getOrderDate());
        ExchangeAfterSaleOrder exchangeAfterSaleOrder = afterSaleSelecterService.getExchangeOrder(merchantOrder, exchangeId);
        afterSaleStateService.exchangeExchangeOrderState(exchangeAfterSaleOrder, ExchangeOrderStateType.EXCHANGE_SIGN_AGAIN.getKey());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("换货单买家签收成功");
        return view;
    }
}
