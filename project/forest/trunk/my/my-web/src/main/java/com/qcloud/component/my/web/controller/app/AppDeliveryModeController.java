//package com.qcloud.component.my.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.my.web.controller.DeliveryModeController;
//import com.qcloud.component.my.web.controller.form.DeliveryForm;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//
//@Controller
//@RequestMapping(value = AppDeliveryModeController.DIR)
//public class AppDeliveryModeController {
//
//    public static final String     DIR = "/app/deliveryMode";
//
//    @Autowired
//    private DeliveryModeController deliveryModeController;
//
//    @RequestMapping
//    public FrontAjaxView add(HttpServletRequest request, DeliveryForm deliveryForm) {
//
//        return deliveryModeController.add(request, deliveryForm);
//    }
//
//    @RequestMapping
//    public FrontAjaxView get(HttpServletRequest request) {
//
//        return deliveryModeController.get(request);
//    }
//
//    @RequestMapping
//    public FrontAjaxView getDefault(HttpServletRequest request) {
//
//        return deliveryModeController.getDefault(request);
//    }
//}
