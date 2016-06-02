package com.qcloud.component.personalcenter.web.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.personalcenter.WealthType;
import com.qcloud.component.personalcenter.model.MyWealth;
import com.qcloud.component.personalcenter.model.query.MyWealthQuery;
import com.qcloud.component.personalcenter.service.MyWealthService;
import com.qcloud.component.personalcenter.web.handler.MyWealthHandler;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMyWealthVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminMyWealthController.DIR)
public class AdminMyWealthController {

    public static final String   DIR = "admin/myWealth";

    @Autowired
    private MyWealthService      myWealthService;

    @Autowired
    private MyWealthHandler      myWealthHandler;

    @Autowired
    private PersonalcenterClient personalcenterClient;

    @RequestMapping
    @NoReferer
    public ModelAndView integralList(PPage pPage, MyWealthQuery query) {// 用户财务--积分列表

        List<QUser> userList = personalcenterClient.userListAll();
        Page<MyWealth> page = myWealthService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminMyWealthVO> list = myWealthHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/personalcenter-MyWealth-integralList", DIR + "/integralList", pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("userList", userList);
        return pagingView;
    }

    @RequestMapping
    @NoReferer
    public ModelAndView commissionList(PPage pPage, MyWealthQuery query) {// 用户财务--佣金列表

        List<QUser> userList = personalcenterClient.userListAll();
        Page<MyWealth> page = myWealthService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminMyWealthVO> list = myWealthHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/personalcenter-MyWealth-commissionList", DIR + "/commissionList", pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("userList", userList);
        return pagingView;
    }

    @RequestMapping
    @NoReferer
    public ModelAndView couponbondList(PPage pPage, MyWealthQuery query) {// 用户财务--兑兑券列表

        List<QUser> userList = personalcenterClient.userListAll();
        Page<MyWealth> page = myWealthService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminMyWealthVO> list = myWealthHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/personalcenter-MyWealth-couponbondList", DIR + "/couponbondList", pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("userList", userList);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/personalcenter-MyWealth-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(MyWealth myWealth) {

        myWealthService.add(myWealth);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEditIntegral(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        MyWealth myWealth = myWealthService.get(id);
        AdminMyWealthVO adminMyWealthVO = myWealthHandler.toVO4Admin(myWealth);
        ModelAndView model = new ModelAndView("/admin/personalcenter-MyWealth-editIntegral");
        model.addObject("myWealth", adminMyWealthVO);
        return model;
    }

    @RequestMapping
    public ModelAndView toEditCouponbond(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        MyWealth myWealth = myWealthService.get(id);
        AdminMyWealthVO adminMyWealthVO = myWealthHandler.toVO4Admin(myWealth);
        ModelAndView model = new ModelAndView("/admin/personalcenter-MyWealth-editCouponbond");
        model.addObject("myWealth", adminMyWealthVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView editIntegral(MyWealth myWealth) {

        long integer = myWealth.getIntegral();// 积分
        AssertUtil.greatZero(integer, "赠送积分必须大于0." + integer);
        MyWealth nWealth = myWealthService.get(myWealth.getId());
        personalcenterClient.calculateMyWealth(nWealth.getUserId(), WealthType.INTEGRAL, integer, false, "赠送积分");
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/integralList");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView editCouponbond(MyWealth myWealth) {

        double consumptionCurrency = myWealth.getConsumptionCurrency();// 消费币
        AssertUtil.assertTrue(consumptionCurrency > 0, "赠送消费币必须大于0." + consumptionCurrency);
        MyWealth nWealth = myWealthService.get(myWealth.getId());
        personalcenterClient.calculateMyWealth(nWealth.getUserId(), WealthType.COMSUMPTION_CURRENCY, consumptionCurrency, false, "赠送消费币");
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/integralList");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        myWealthService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
