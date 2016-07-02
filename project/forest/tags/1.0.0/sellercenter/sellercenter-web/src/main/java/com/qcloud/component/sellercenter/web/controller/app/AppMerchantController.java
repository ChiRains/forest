//package com.qcloud.component.sellercenter.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.sellercenter.web.controller.MerchantController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.mvc.FrontPagingView;
//import com.qcloud.pirates.web.page.PPage;
//
//@Controller
//@RequestMapping(value = AppMerchantController.DIR)
//public class AppMerchantController {
//
//    public static final String DIR = "/app/merchant";
//
//    // @Autowired
//    // private MerchantService merchantService;
//    //
//    // @Autowired
//    // private MerchantHandler merchantHandler;
//    // @Autowired
//    // private MerchantSortService merchantSortService;
//    @Autowired
//    MerchantController         merchantController;
//
//    // @RequestMapping
//    // public FrontAjaxView listHighQuality() {
//    //
//    // Integer pageNum = 1;
//    // MerchantQuery query = new MerchantQuery();
//    // final int PAGE_SIZE = 10;
//    // pageNum = RequestUtil.getPageid(pageNum);
//    // int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
//    // Page<Merchant> page = merchantService.page(query, start, PAGE_SIZE);
//    // List<MerchantVO> list = merchantHandler.toVOList(page.getData());
//    // FrontAjaxView frontAjaxView = new FrontAjaxView();
//    // frontAjaxView.setMessage("查询成功");
//    // frontAjaxView.addObject("merchantList", list);
//    // return frontAjaxView;
//    // }
//    @RequestMapping
//    public FrontPagingView listHighMerchant(PPage pPage) {
//
//        return merchantController.listHighMerchant(pPage);
//    }
//
//    @RequestMapping
//    public FrontAjaxView getMerchantInfo(Long merchantId) {
//
//        return merchantController.getMerchantInfo(merchantId);
//    }
//
//    @RequestMapping
//    public FrontAjaxView listMySearch(HttpServletRequest request, Integer number) {
//
//        return merchantController.listMySearch(request, number);
//    }
//
//    @RequestMapping
//    public FrontAjaxView clearMySearch(HttpServletRequest request) {
//
//        return merchantController.clearMySearch(request);
//    }
//}
