//package com.qcloud.component.orderform.web.controller.app;
//
//import java.util.Date;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.orderform.web.controller.OrderController;
//import com.qcloud.component.orderform.web.form.MultiMerchantOrderForm;
//import com.qcloud.component.orderform.web.form.OrderForm;
//import com.qcloud.component.orderform.web.form.PrepareOrderForm;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.web.security.annotation.NoReferer;
//
//@Controller
//@RequestMapping(value = AppOrderController.DIR)
//public class AppOrderController {
//
//    // URI
//    public static final String DIR = "/app/orderForm";
//
//    @Autowired
//    OrderController            orderController;
//
//    // 一分钟一个账号允许下一个订单
//    @RequestMapping
//    @NoReferer
//    public FrontAjaxView prepareOrder4Classify(HttpServletRequest request, PrepareOrderForm orderForm) {
//
//        return orderController.prepareOrder4Classify(request, orderForm);
//    }
//
//    // 一分钟一个账号允许下一个订单
//    @RequestMapping
//    @NoReferer
//    public FrontAjaxView prepareOrder4Merchant(HttpServletRequest request, PrepareOrderForm orderForm) {
//
//        return orderController.prepareOrder4Merchant(request, orderForm);
//    }
//
////    @RequestMapping
//    @NoReferer
//    public FrontAjaxView prepareOrder(HttpServletRequest request, PrepareOrderForm orderForm) {
//
//        return orderController.prepareOrder(request, orderForm);
//    }
//
//    @RequestMapping(value = "/order.do")
//    @NoReferer
//    public FrontAjaxView order(HttpServletRequest request, OrderForm orderForm) {
//
//        return orderController.order(request, orderForm);
//    }
//
//    @RequestMapping(value = "/order4MultiMerchant.do")
//    @NoReferer
//    public FrontAjaxView order4MultiMerchant(HttpServletRequest request, MultiMerchantOrderForm orderForm) {
//
//        return orderController.order4MultiMerchant(request, orderForm);
//    }
//
//    @RequestMapping
//    public FrontAjaxView orderAgain(HttpServletRequest request, Long orderId, Date orderDate) {
//
//        return orderController.orderAgain(request, orderId, orderDate);
//    }
//
//    @RequestMapping
//    @NoReferer
//    public FrontAjaxView exchangeConsumption(HttpServletRequest request, Long unifiedMerchandiseId, Integer number, String name, String mobile, String address) {
//
//        return orderController.exchangeConsumption(request, unifiedMerchandiseId, number, name, mobile, address);
//    }
//
//    @RequestMapping
//    @NoReferer
//    public FrontAjaxView exchangeIntegral(HttpServletRequest request, Long unifiedMerchandiseId, Integer number, String name, String mobile, String address) {
//
//        return orderController.exchangeIntegral(request, unifiedMerchandiseId, number, name, mobile, address);
//    }
//
//    @RequestMapping
//    public FrontAjaxView get(HttpServletRequest request, Long orderId, Date orderDate) {
//
//        return orderController.get(request, orderId, orderDate);
//    }
//
//    @RequestMapping
//    public FrontAjaxView get4Classify(HttpServletRequest request, Long orderId, Date orderDate) {
//
//        return orderController.get4Classify(request, orderId, orderDate);
//    }
//
//    @RequestMapping
//    public FrontAjaxView get4Merchant(HttpServletRequest request, Long orderId, Date orderDate) {
//
//        return orderController.get4Merchant(request, orderId, orderDate);
//    }
//
//    @RequestMapping
//    public FrontAjaxView getMerchantOrder(HttpServletRequest request, Long subOrderId, Date orderDate) {
//
//        return orderController.getMerchantOrder(request, subOrderId, orderDate);
//    }
//
//    @RequestMapping
//    public FrontAjaxView getMerchantOrder4Classify(HttpServletRequest request, Long subOrderId, Date orderDate) {
//
//        return orderController.getMerchantOrder4Classify(request, subOrderId, orderDate);
//    }
//
//    @RequestMapping
//    public FrontAjaxView getMerchantOrder4Merchant(HttpServletRequest request, Long subOrderId, Date orderDate) {
//
//        return orderController.getMerchantOrder4Merchant(request, subOrderId, orderDate);
//    }
//
//    @RequestMapping
//    public FrontAjaxView getSubOrder(HttpServletRequest request, Long subOrderId, Date orderDate) {
//
//        return orderController.getSubOrder(request, subOrderId, orderDate);
//    }
//
//    @RequestMapping
//    @NoReferer
//    public FrontAjaxView changeConsignee(HttpServletRequest request, Long consigneeId, Long orderId, Date orderDate) {
//
//        return orderController.changeConsignee(request, consigneeId, orderId, orderDate);
//    }
//}
