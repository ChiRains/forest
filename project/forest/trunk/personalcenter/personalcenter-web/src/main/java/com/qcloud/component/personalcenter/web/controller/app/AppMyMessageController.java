//package com.qcloud.component.personalcenter.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.personalcenter.web.controller.MyMessageController;
//import com.qcloud.component.piratesship.web.form.ListForm;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.web.page.PPage;
//
//@Controller
//@RequestMapping(value = AppMyMessageController.DIR)
//public class AppMyMessageController {
//
//    public static final String  DIR = "/app/myMessage";
//
//    @Autowired
//    private MyMessageController myMessageController;
//
//    @RequestMapping
//    public FrontAjaxView list(HttpServletRequest request, PPage pPage) {
//
//        return myMessageController.list(request, pPage);
//    }
//
//    @RequestMapping
//    public FrontAjaxView countAll(HttpServletRequest request) {
//
//        return myMessageController.countAll(request);
//    }
//
//    @RequestMapping
//    public FrontAjaxView countUnread(HttpServletRequest request) {
//
//        return myMessageController.countUnread(request);
//    }
//
//    @RequestMapping
//    public FrontAjaxView getNewMsgNumber(HttpServletRequest request) {
//
//        return myMessageController.getNewMsgNumber(request);
//    }
//
//    @RequestMapping
//    public FrontAjaxView resetNewMsgNumber(HttpServletRequest request) {
//
//        return myMessageController.resetNewMsgNumber(request);
//    }
//
//    @RequestMapping
//    public FrontAjaxView get(HttpServletRequest request, Long id) {
//
//        return myMessageController.get(request, id);
//    }
//
//    @RequestMapping
//    public FrontAjaxView read(HttpServletRequest request, Long id) {
//
//        return myMessageController.read(request, id);
//    }
//
//    @RequestMapping
//    public FrontAjaxView delete(HttpServletRequest request, Long id) {
//
//        return myMessageController.delete(request, id);
//    }
//
//    @RequestMapping
//    public FrontAjaxView deleteList(HttpServletRequest request, ListForm form) {
//
//        return myMessageController.deleteList(request, form);
//    }
//}
