//package com.qcloud.component.personalcenter.web.controller.app;
//
//import java.util.Date;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.personalcenter.web.controller.MyWealthController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//
//@Controller
//@RequestMapping(value = AppMyWealthController.DIR)
//public class AppMyWealthController {
//
//    public static final String DIR = "/app/myWealth";
//
//    @Autowired
//    MyWealthController         myWealthController;
//
//    @RequestMapping
//    public FrontAjaxView listTopWealth(Integer number, Integer type) {
//
//        return myWealthController.listTopWealth(number, type);
//    }
//
//    @RequestMapping
//    public FrontAjaxView listCommissionDetail(HttpServletRequest request, String checkTime, Integer detailType, Integer pageSize, Integer pageNum) {
//
//        return myWealthController.listCommissionDetail(request, checkTime, detailType, pageSize, pageNum);
//    }
//
//    @RequestMapping
//    public FrontAjaxView listIntegralDetail(HttpServletRequest request, String checkTime, Integer detailType, Integer pageSize, Integer pageNum) {
//
//        return myWealthController.listIntegralDetail(request, checkTime, detailType, pageSize, pageNum);
//    }
//
//    @RequestMapping
//    public FrontAjaxView listCurrencyDetail(HttpServletRequest request, String checkTime, Integer detailType, Integer pageSize, Integer pageNum) {
//
//        return myWealthController.listCurrencyDetail(request, checkTime, detailType, pageSize, pageNum);
//    }
//
//    @RequestMapping
//    public FrontAjaxView withdrawCommission(HttpServletRequest request, Long bankCardId, double cash) {
//
//        return myWealthController.withdrawCommission(request, bankCardId, cash);
//    }
//
//    @RequestMapping
//    public FrontAjaxView listWithdrawCommission(HttpServletRequest request,String checkTime, Integer pageSize, Integer pageNum) {
//
//        return myWealthController.listWithdrawCommission(request, checkTime, pageSize, pageNum);
//    }
//
//    @RequestMapping
//    public FrontAjaxView listWithdrawingCommission(HttpServletRequest request, Integer pageSize, Integer pageNum) {
//
//        return myWealthController.listWithdrawingCommission(request, pageSize, pageNum);
//    }
//
//    @RequestMapping
//    public FrontAjaxView listWithdrawedCommission(HttpServletRequest request, Integer pageSize, Integer pageNum) {
//
//        return myWealthController.listWithdrawedCommission(request, pageSize, pageNum);
//    }
//
//    @RequestMapping
//    public FrontAjaxView statIntegralTrend(HttpServletRequest request, Date begin, Date end) {
//
//        return myWealthController.statIntegralTrend(request, begin, end);
//    }
//
//    @RequestMapping
//    public FrontAjaxView statCommissionTrend(HttpServletRequest request, Date begin, Date end) {
//
//        return myWealthController.statCommissionTrend(request, begin, end);
//    }
//
//    @RequestMapping
//    public FrontAjaxView statCurrencyTrend(HttpServletRequest request, Date begin, Date end) {
//
//        return myWealthController.statCurrencyTrend(request, begin, end);
//    }
//
//    @RequestMapping
//    public FrontAjaxView integralToCurrency(HttpServletRequest request, Integer integral) {
//
//        return myWealthController.integralToCurrency(request, integral);
//    }
//}
