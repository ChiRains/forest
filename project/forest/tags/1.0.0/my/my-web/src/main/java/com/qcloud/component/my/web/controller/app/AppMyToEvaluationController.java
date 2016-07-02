//package com.qcloud.component.my.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.my.web.controller.MyToEvaluationController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.web.page.PPage;
//
//@Controller
//@RequestMapping(value = AppMyToEvaluationController.DIR)
//public class AppMyToEvaluationController {
//
//    public static final String       DIR = "/app/myToEvaluation";
//
//    @Autowired
//    private MyToEvaluationController myToEvaluationController;
//
//    @RequestMapping
//    public FrontAjaxView list(HttpServletRequest request, PPage pPage) {
//
//        return myToEvaluationController.list(request, pPage);
//    }
//
//    @RequestMapping
//    public FrontAjaxView listBySubOrder(HttpServletRequest request, PPage pPage, Long subOrderId) {
//
//        return myToEvaluationController.listBySubOrder(request, pPage, subOrderId);
//    }
//}
