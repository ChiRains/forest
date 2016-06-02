//package com.qcloud.component.orderform.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.orderform.web.controller.OrderStateController;
//import com.qcloud.component.orderform.web.form.OrderStateForm;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//
//@Controller
//@RequestMapping(value = AppOrderStateController.DIR)
//public class AppOrderStateController {
//
//    // URI
//    public static final String DIR = "/app/orderState";
//
//    @Autowired
//    OrderStateController       orderStateController;
//
//    @RequestMapping
//    public FrontAjaxView remindDeliverGoods(HttpServletRequest request, OrderStateForm orderStateForm) {
//
//        return orderStateController.remindDeliverGoods(request, orderStateForm);
//    }
//
//    @RequestMapping
//    public FrontAjaxView signOrder(HttpServletRequest request, OrderStateForm orderStateForm) {
//
//        return orderStateController.signOrder(request, orderStateForm);
//    }
//
//    @RequestMapping
//    public FrontAjaxView cancelOrder(HttpServletRequest request, OrderStateForm orderStateForm) {
//
//        return orderStateController.cancelOrder(request, orderStateForm);
//    }
//
//    /**
//     * 商家确认订单
//     * @param orderForm
//     * @return
//     */
//    @RequestMapping
//    public FrontAjaxView confirmOrder(HttpServletRequest request, OrderStateForm orderStateForm) {
//
//        return orderStateController.confirmOrder(request, orderStateForm);
//    }
//
//    /**
//     * 商家订单发货
//     * 
//     * @param orderStateForm
//     * @param logisticsNumber
//     * @return
//     */
//    @RequestMapping
//    public FrontAjaxView deliverOrder(HttpServletRequest request, OrderStateForm orderStateForm, String expressCode, String expressName, String expressNumber) {
//
//        return orderStateController.deliverOrder(request, orderStateForm, expressCode, expressName, expressNumber);
//    }
//}
