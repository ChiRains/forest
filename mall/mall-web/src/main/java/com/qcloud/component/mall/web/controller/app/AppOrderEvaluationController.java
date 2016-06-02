//package com.qcloud.component.mall.web.controller.app;
//
//import java.util.Date;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.commoditycenter.web.form.EvaluationForm;
//import com.qcloud.component.mall.web.controller.OrderEvaluationController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.web.security.annotation.NoReferer;
//
//@Controller
//@RequestMapping(value = AppOrderEvaluationController.DIR)
//public class AppOrderEvaluationController {
//
//    public static final String DIR = "/app/orderEvaluation";
//
//    @Autowired
//    OrderEvaluationController  orderEvaluationController;
//
//    @RequestMapping
//    @NoReferer
//    public FrontAjaxView listToEvaluate(HttpServletRequest request, Long orderId, Date orderDate) {
//
//        return orderEvaluationController.listToEvaluate(request, orderId, orderDate);
//    }
//
//    /**
//     * 评价商品
//     * @param vo  
//     * @return
//     */
//    @RequestMapping
//    @NoReferer
//    public FrontAjaxView evaluate(HttpServletRequest request, EvaluationForm evaluationForm) {
//
//        return orderEvaluationController.evaluate(request, evaluationForm);
//    }
//}
