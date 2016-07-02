//package com.qcloud.component.orderform.web.controller.app;
//
//import java.util.Date;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.orderform.web.controller.AfterSaleController;
//import com.qcloud.component.orderform.web.form.AfterSaleForm;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//
//@Controller
//@RequestMapping(value = AppAfterSaleController.DIR)
//public class AppAfterSaleController {
//
//    public static final String DIR = "/app/afterSale";
//
//    @Autowired
//    AfterSaleController        afterSaleController;
//
//    @RequestMapping
//    public FrontAjaxView listByOrder(HttpServletRequest request, Long orderId, Date orderDate) {
//
//        return afterSaleController.listByOrder(request, orderId, orderDate);
//    }
//
//    @RequestMapping
//    public FrontAjaxView listBySubOrder(HttpServletRequest request, Long subOrderId, Date orderDate) {
//
//        return afterSaleController.listBySubOrder(request, subOrderId, orderDate);
//    }
//
//    @RequestMapping
//    public FrontAjaxView refundOrder(HttpServletRequest request, AfterSaleForm afterSaleForm) {
//
//        return afterSaleController.refundOrder(request, afterSaleForm);
//    }
//
//    // @RequestMapping
//    // public FrontAjaxView reApplyRefundOrder(HttpServletRequest request, Long refundId) {
//    //
//    // return afterSaleController.reApplyRefundOrder(request, refundId);
//    // }
//    @RequestMapping
//    public FrontAjaxView returnOrder(HttpServletRequest request, AfterSaleForm afterSaleForm) {
//
//        return afterSaleController.returnOrder(request, afterSaleForm);
//    }
//
//    @RequestMapping
//    public FrontAjaxView reApplyReturnOrder(HttpServletRequest request, Long returnId) {
//
//        return afterSaleController.reApplyReturnOrder(request, returnId);
//    }
//
//    @RequestMapping
//    public FrontAjaxView exchangeOrder(HttpServletRequest request, AfterSaleForm afterSaleForm) {
//
//        return afterSaleController.exchangeOrder(request, afterSaleForm);
//    }
//
//    @RequestMapping
//    public FrontAjaxView reApplyExchangeOrder(HttpServletRequest request, Long exchangeId) {
//
//        return afterSaleController.reApplyExchangeOrder(request, exchangeId);
//    }
//
//    @RequestMapping
//    public FrontAjaxView listAfterSaleInformation(HttpServletRequest request, Long afterSaleId, Integer type) {
//
//        return afterSaleController.listAfterSaleInformation(request, afterSaleId, type);
//    }
//}
