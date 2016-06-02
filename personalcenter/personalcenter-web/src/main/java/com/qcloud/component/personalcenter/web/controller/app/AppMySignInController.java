//package com.qcloud.component.personalcenter.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.personalcenter.web.controller.MySignInController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//
//@Controller
//@RequestMapping(value = AppMySignInController.DIR)
//public class AppMySignInController {
//
//    public static final String DIR = "/app/mySignIn";
//
//    @Autowired
//    private MySignInController mySignInController;
//
//    @RequestMapping
//    public FrontAjaxView signIn(HttpServletRequest request) {
//
//        return mySignInController.signIn(request);
//    }
//
//    @RequestMapping
//    public FrontAjaxView listSignInMonth(HttpServletRequest request, Integer year, Integer month) {
//
//        return mySignInController.listSignInMonth(request, year, month);
//    }
//
//    @RequestMapping
//    public FrontAjaxView getSignInMseeage(HttpServletRequest request) {
//
//        return mySignInController.getSignInMseeage(request);
//    }
//}
