package com.qcloud.component.commoditycenter.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.commoditycenter.model.MerchandiseVipDiscount;
import com.qcloud.component.commoditycenter.model.MerchandiseVipDiscountDate;
import com.qcloud.component.commoditycenter.service.MerchandiseVipDiscountDateService;
import com.qcloud.component.commoditycenter.service.MerchandiseVipDiscountService;
import com.qcloud.component.commoditycenter.web.handler.MerchandiseVipDiscountHandler;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseVipDiscountVO;
import com.qcloud.component.commoditycenter.web.vo.VipDiscountClassifyVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QClassify;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = MerchandiseVipDiscountController.DIR)
public class MerchandiseVipDiscountController {

    public static final String                DIR = "/merchandiseVipDiscount";

    @Autowired
    private MerchandiseVipDiscountService     merchandiseVipDiscountService;

    @Autowired
    private MerchandiseVipDiscountHandler     merchandiseVipDiscountHandler;

    @Autowired
    private PublicdataClient                  publicdataClient;

    @Autowired
    private MerchandiseVipDiscountDateService merchandiseVipDiscountDateService;

    @RequestMapping
    public FrontAjaxView listClassify(HttpServletRequest request, Long type) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<QClassify> classifyList = publicdataClient.listTopClassify(type);
        List<VipDiscountClassifyVO> voList = new ArrayList<VipDiscountClassifyVO>();
        for (QClassify qClassify : classifyList) {
            VipDiscountClassifyVO vo = new VipDiscountClassifyVO();
            vo.setClassifyId(qClassify.getId());
            vo.setName(qClassify.getName());
            vo.setNumber(merchandiseVipDiscountService.countByUser(user.getId(), qClassify.getId()));
            if (vo.getNumber() > 0) {
                voList.add(vo);
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询我的价格数据成功.");
        view.addObject("list", voList);
        MerchandiseVipDiscountDate merchandiseVipDiscountDate = merchandiseVipDiscountDateService.getLastByUser(user.getId());
        if (merchandiseVipDiscountDate == null) {
            view.addObject("vipDiscountDate", "");
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, merchandiseVipDiscountDate.getYear());
            calendar.set(Calendar.MONTH, merchandiseVipDiscountDate.getMonth() - 1);
            calendar.set(Calendar.DAY_OF_MONTH, merchandiseVipDiscountDate.getDay());
            view.addObject("vipDiscountDate", DateUtil.date2String(calendar.getTime(), "yyyy.MM.dd"));
        }
        return view;
    }

    @RequestMapping
    public FrontPagingView page(HttpServletRequest request, Long classifyId, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MerchandiseVipDiscount> list = merchandiseVipDiscountService.listByUser(user.getId(), classifyId, pPage.getPageStart(), pPage.getPageSize());
        List<MerchandiseVipDiscountVO> voList = merchandiseVipDiscountHandler.toVOList(list);
        int number = merchandiseVipDiscountService.countByUser(user.getId(), classifyId);
        FrontPagingView view = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), number);
        view.setMessage("查询我的价格数据成功.");
        view.addObject("list", voList);
        return view;
    }

    @RequestMapping
    public FrontAjaxView list(HttpServletRequest request, Long classifyId, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MerchandiseVipDiscount> list = merchandiseVipDiscountService.listByUser(user.getId(), classifyId, pPage.getPageStart(), pPage.getPageSize());
        List<MerchandiseVipDiscountVO> voList = merchandiseVipDiscountHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询我的价格数据成功.");
        view.addObject("list", voList);
        return view;
    }

    @RequestMapping
    public FrontAjaxView count(HttpServletRequest request, Long classifyId) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        int number = merchandiseVipDiscountService.countByUser(user.getId(), classifyId);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询我的价格条数成功.");
        view.addObject("count", number);
        return view;
    }
}
