//package com.qcloud.component.commoditycenter.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.commoditycenter.web.controller.MerchandiseEvaluationController;
//import com.qcloud.component.commoditycenter.web.form.EvaluationForm;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.web.security.annotation.NoReferer;
//
//@Controller
//@RequestMapping(value = AppMerchandiseEvaluationController.DIR)
//public class AppMerchandiseEvaluationController {
//
//    public static final String      DIR = "/app/merchandiseEvaluation";
//
//    @Autowired
//    MerchandiseEvaluationController merchandiseEvaluationController;
//
//    @RequestMapping
//    @NoReferer
//    public FrontAjaxView evaluate(HttpServletRequest request, EvaluationForm evaluationForm) {
//
//        return merchandiseEvaluationController.evaluate(request, evaluationForm);
//    }
//}
