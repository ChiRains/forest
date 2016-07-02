//package com.qcloud.component.orderform.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.orderform.web.controller.AfterSaleStateController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//
//@Controller
//@RequestMapping(value = AppAfterSaleStateController.DIR)
//public class AppAfterSaleStateController {
//
//    public static final String       DIR = "/app/afterSaleState";
//
//    @Autowired
//    private AfterSaleStateController afterSaleStateController;
//
//    // 卖家同意退款
//    @RequestMapping
//    public FrontAjaxView confirmRefund(HttpServletRequest request, Long refundId, Double sum) {
//
//        return afterSaleStateController.confirmRefund(request, refundId, sum);
//    }
//
//    // 卖家拒绝退款
//    @RequestMapping
//    public FrontAjaxView refuseRefund(HttpServletRequest request, Long refundId) {
//
//        return afterSaleStateController.refuseRefund(request, refundId);
//    }
//
//    // 卖家退款
//    @RequestMapping
//    public FrontAjaxView payRefund(HttpServletRequest request, Long refundId) {
//
//        return afterSaleStateController.payRefund(request, refundId);
//    }
//
//    // 买家确认退款
//    @RequestMapping
//    public FrontAjaxView confirmPayRefund(HttpServletRequest request, Long refundId) {
//
//        return afterSaleStateController.confirmPayRefund(request, refundId);
//    }
//
//    // 卖家确认退货
//    @RequestMapping
//    public FrontAjaxView confirmReturn(HttpServletRequest request, Long returnId, Double sum) {
//
//        return afterSaleStateController.confirmReturn(request, returnId, sum);
//    }
//
//    // 卖家拒绝退货
//    @RequestMapping
//    public FrontAjaxView refuseReturn(HttpServletRequest request, Long returnId) {
//
//        return afterSaleStateController.refuseReturn(request, returnId);
//    }
//
//    // 买家发货
//    @RequestMapping
//    public FrontAjaxView shippedReturn(HttpServletRequest request, Long returnId, String logisticsCompany, String logisticsNumber) {
//
//        return afterSaleStateController.shippedReturn(request, returnId, logisticsCompany, logisticsNumber);
//    }
//
//    // 卖家签收
//    @RequestMapping
//    public FrontAjaxView signReturn(HttpServletRequest request, Long returnId) {
//
//        return afterSaleStateController.signReturn(request, returnId);
//    }
//
//    // 卖家退款
//    @RequestMapping
//    public FrontAjaxView payReturn(HttpServletRequest request, Long returnId) {
//
//        return afterSaleStateController.payReturn(request, returnId);
//    }
//
//    // 买家确认退款
//    @RequestMapping
//    public FrontAjaxView confirmPayReturn(HttpServletRequest request, Long returnId) {
//
//        return afterSaleStateController.confirmPayReturn(request, returnId);
//    }
//
//    // ******************************************************************
//    // 卖家确认换货
//    @RequestMapping
//    public FrontAjaxView confirmExchange(HttpServletRequest request, Long exchangeId) {
//
//        return afterSaleStateController.confirmExchange(request, exchangeId);
//    }
//
//    // 卖家拒绝换换
//    @RequestMapping
//    public FrontAjaxView refuseExchange(HttpServletRequest request, Long exchangeId) {
//
//        return afterSaleStateController.refuseExchange(request, exchangeId);
//    }
//
//    // 买家发货
//    @RequestMapping
//    public FrontAjaxView shippedExchange(HttpServletRequest request, Long exchangeId, String logisticsCompany, String logisticsNumber) {
//
//        return afterSaleStateController.shippedExchange(request, exchangeId, logisticsCompany, logisticsNumber);
//    }
//
//    // 卖家签收
//    @RequestMapping
//    public FrontAjaxView signExchange(HttpServletRequest request, Long exchangeId) {
//
//        return afterSaleStateController.signExchange(request, exchangeId);
//    }
//
//    // 卖家再次发货
//    @RequestMapping
//    public FrontAjaxView shippedAgainExchange(HttpServletRequest request, Long exchangeId, String logisticsCompany, String logisticsNumber) {
//
//        return afterSaleStateController.shippedAgainExchange(request, exchangeId, logisticsCompany, logisticsNumber);
//    }
//
//    // 买家再次确认
//    @RequestMapping
//    public FrontAjaxView signAgainExchange(HttpServletRequest request, Long exchangeId) {
//
//        return afterSaleStateController.signAgainExchange(request, exchangeId);
//    }
//}
