package com.qcloud.component.sellercenter.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.Merchant;
import com.qcloud.component.sellercenter.model.MerchantSort;
import com.qcloud.component.sellercenter.model.key.TypeEnum.MerchantQueryType;
import com.qcloud.component.sellercenter.model.query.MerchantQuery;
import com.qcloud.component.sellercenter.service.MerchantService;
import com.qcloud.component.sellercenter.service.MerchantSortService;
import com.qcloud.component.sellercenter.web.handler.MerchantHandler;
import com.qcloud.component.sellercenter.web.handler.MerchantSortHandler;
import com.qcloud.component.sellercenter.web.vo.MerchantSortVO;
import com.qcloud.component.sellercenter.web.vo.MerchantVO;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = MerchantController.DIR)
public class MerchantController {

    public static final String  DIR = "/merchant";

    @Autowired
    private MerchantService     merchantService;

    @Autowired
    private MerchantHandler     merchantHandler;

    @Autowired
    private MerchantSortService merchantSortService;

    @Autowired
    private MerchantSortHandler merchantSortHandler;

    @Autowired(required = false)
    private MyClient            myClient;

    @Autowired
    private PublicdataClient    publicdataClient;

    @RequestMapping
    public FrontPagingView list(HttpServletRequest request, MerchantQuery query, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_IS_LOGIN_PARAMETER_KEY);
        // 已经登录
        if (user != null && myClient != null) {
            myClient.addMySearchHistory(user.getId(), SellercenterClient.SEARCH_TYPE, query.getName());
        }
        publicdataClient.addSearch(SellercenterClient.SEARCH_TYPE, query.getName());
        // 区域中心和距离不为空,要将区域中心换算为中心的经纬度进行查询
        List<Merchant> list = merchantService.list(query, pPage.getPageStart(), pPage.getPageSize());
        List<MerchantVO> voList = merchantHandler.toVOList(list);
        int count = merchantService.count(query);
        FrontPagingView view = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), count);
        view.addObject("merchantList", voList);
        view.setMessage("查询商家成功.");
        return view;
    }

    @RequestMapping
    public FrontPagingView listNew(HttpServletRequest request, MerchantQuery query, PPage pPage) {

        query.setType(MerchantQueryType.NEW.getKey());
        return list(request, query, pPage);
    }

    @RequestMapping
    public FrontPagingView listHot(HttpServletRequest request, MerchantQuery query, PPage pPage) {

        query.setType(MerchantQueryType.HOT.getKey());
        return list(request, query, pPage);
    }

    @RequestMapping
    public FrontPagingView listFavourable(HttpServletRequest request, MerchantQuery query, PPage pPage) {

        query.setType(MerchantQueryType.FAVOURABLE.getKey());
        return list(request, query, pPage);
    }

    @RequestMapping
    public FrontPagingView listRecently(HttpServletRequest request, MerchantQuery query, PPage pPage) {

        query.setType(MerchantQueryType.RECENTLY.getKey());
        return list(request, query, pPage);
    }

    // 优质商家列表
    @PiratesApp
    @RequestMapping
    public FrontPagingView listHighMerchant(PPage pPage) {

        List<MerchantSort> list = merchantSortService.list(pPage.getPageStart(), pPage.getPageSize());
        int total = merchantSortService.listAll().size();
        List<MerchantSortVO> voList = merchantSortHandler.toVOList(list);
        FrontPagingView view = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), total);
        view.addObject("merchantList", voList);
        view.setMessage("查询优质商家成功");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getMerchantInfo(Long merchantId) {

        AssertUtil.assertNotNull(merchantId, "商家id不能为空");
        AssertUtil.assertTrue(merchantId > 0, "商家id不存在." + merchantId);
        Merchant merchant = merchantService.get(merchantId);
        AssertUtil.assertNotNull(merchant, "商家信息不存在");
        MerchantVO merchantVO = merchantHandler.toVO(merchant);
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("merchant", merchantVO);
        return view;
    }

    @RequestMapping
    public FrontAjaxView listHotSearch(Integer number) {

        number = number == null || number <= 0 || number > 100 ? 20 : number;
        List<String> list = publicdataClient.listHotSearch(SellercenterClient.SEARCH_TYPE, number);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取热搜商家关键字成功.");
        view.addObject("list", list);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listMySearch(HttpServletRequest request, Integer number) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        number = number == null || number <= 0 || number > 100 ? 20 : number;
        List<String> list = myClient.listMySearchHistory(user.getId(), SellercenterClient.SEARCH_TYPE, number);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取我的商家搜索历史成功.");
        view.addObject("list", list);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView clearMySearch(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        myClient.clearMySearchHistory(user.getId(), SellercenterClient.SEARCH_TYPE);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("清空搜索历史成功.");
        return frontAjaxView;
    }
}
