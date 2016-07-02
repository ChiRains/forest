//package com.qcloud.component.my.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.my.web.controller.MyMerchandiseCollectionController;
//import com.qcloud.component.piratesship.web.form.ListForm;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.web.page.PPage;
//
//@Controller
//@RequestMapping(value = AppMyMerchandiseCollectionController.DIR)
//public class AppMyMerchandiseCollectionController {
//
//    public static final String        DIR = "/app/myMerchandiseCollection";
//
//    @Autowired
//    MyMerchandiseCollectionController myMerchandiseCollectionController;
//
//    @RequestMapping
//    public FrontAjaxView collect(HttpServletRequest request, Long unifiedMerchandiseId) {
//
//        return myMerchandiseCollectionController.collect(request, unifiedMerchandiseId);
//    }
//
//    @RequestMapping
//    public FrontAjaxView isCollect(HttpServletRequest request, Long unifiedMerchandiseId) {
//
//        return myMerchandiseCollectionController.isCollect(request, unifiedMerchandiseId);
//    }
//
//    @RequestMapping
//    public FrontAjaxView remove(HttpServletRequest request, Long id) {
//
//        return myMerchandiseCollectionController.remove(request, id);
//    }
//
//    @RequestMapping
//    public FrontAjaxView removeList(HttpServletRequest request, ListForm form) {
//
//        return myMerchandiseCollectionController.removeList(request, form);
//    }
//
//    @RequestMapping
//    public FrontAjaxView removeByUnifiedMerchandise(HttpServletRequest request, Long unifiedMerchandiseId) {
//
//        return myMerchandiseCollectionController.removeByUnifiedMerchandise(request, unifiedMerchandiseId);
//    }
//
//    @RequestMapping
//    public FrontAjaxView removeByUnifiedMerchandiseList(HttpServletRequest request, ListForm form) {
//
//        return myMerchandiseCollectionController.removeByUnifiedMerchandiseList(request, form);
//    }
//
//    @RequestMapping
//    public FrontAjaxView list(HttpServletRequest request, PPage pPage, Long classifyId) {
//
//        return myMerchandiseCollectionController.list(request, pPage, classifyId);
//    }
//}
