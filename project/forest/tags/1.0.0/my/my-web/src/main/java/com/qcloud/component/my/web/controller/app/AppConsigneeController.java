//package com.qcloud.component.my.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.my.model.Consignee;
//import com.qcloud.component.my.web.controller.ConsigneeController;
//import com.qcloud.component.my.web.controller.form.ConsigneeForm;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//
//@Controller
//@RequestMapping(value = AppConsigneeController.DIR)
//public class AppConsigneeController {
//
//    @Autowired
//    ConsigneeController        consigneeController;
//
//    public static final String DIR = "/app/consignee";
//
//    @RequestMapping
//    public FrontAjaxView add(HttpServletRequest request, ConsigneeForm consigneeForm) {
//
//        return consigneeController.add(request, consigneeForm);
//    }
//
//    @RequestMapping
//    public FrontAjaxView update(HttpServletRequest request, Consignee consignee) {
//
//        return consigneeController.update(request, consignee);
//    }
//
//    @RequestMapping
//    public FrontAjaxView delete(HttpServletRequest request, Long consigneeId) {
//
//        return consigneeController.delete(request, consigneeId);
//    }
//
//    @RequestMapping
//    public FrontAjaxView list(HttpServletRequest request) {
//
//        return consigneeController.list(request);
//    }
//
//    @RequestMapping
//    public FrontAjaxView setDefault(HttpServletRequest request, Long consigneeId) {
//
//        return consigneeController.setDefault(request, consigneeId);
//    }
//
//    @RequestMapping
//    public FrontAjaxView getDefault(HttpServletRequest request) {
//
//        return consigneeController.getDefault(request);
//    }
//
//    @RequestMapping
//    public FrontAjaxView get(HttpServletRequest request, Long consigneeId) {
//
//        return consigneeController.get(request, consigneeId);
//    }
//}
