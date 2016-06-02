//package com.qcloud.component.seckill.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.seckill.web.controller.SeckillOrderController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.web.security.annotation.NoReferer;
//
//@Controller
//@RequestMapping(value = AppSeckillOrderController.DIR)
//public class AppSeckillOrderController {
//
//    public static final String DIR = "/app/seckillOrder";
//
//    @Autowired
//    SeckillOrderController     seckillOrderController;
//
//    @RequestMapping
//    @NoReferer
//    public FrontAjaxView order(HttpServletRequest request, Long seckillMerchandiseId) {
//
//        return seckillOrderController.order(request, seckillMerchandiseId);
//    }
//}
