package com.qcloud.component.personalcenter.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.personalcenter.model.MySignInDay;
import com.qcloud.component.personalcenter.model.MySignInMonth;
import com.qcloud.component.personalcenter.model.MySignInRecord;
import com.qcloud.component.personalcenter.model.MySignInRuleConfig;
import com.qcloud.component.personalcenter.model.MySignInStatistics;
import com.qcloud.component.personalcenter.service.MySignInDayService;
import com.qcloud.component.personalcenter.service.MySignInMonthService;
import com.qcloud.component.personalcenter.service.MySignInRecordService;
import com.qcloud.component.personalcenter.service.MySignInRuleConfigService;
import com.qcloud.component.personalcenter.service.MySignInStatisticsService;
import com.qcloud.component.personalcenter.web.handler.MySignInDayHandler;
import com.qcloud.component.personalcenter.web.vo.MySignInDayVO;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.HtmlView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = MySignInController.DIR)
public class MySignInController {

    public static final String        DIR = "/mySignIn";

    @Autowired
    private MySignInDayService        mySignInDayService;

    @Autowired
    private MySignInMonthService      mySignInMonthService;

    @Autowired
    private MySignInDayHandler        mySignInDayHandler;

    @Autowired
    private MySignInRuleConfigService mySignInRuleConfigService;

    @Autowired
    private MySignInStatisticsService mySignInStatisticsService;
    
    @Autowired
    private MySignInRecordService mySignInRecordService;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView signIn(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        boolean result = mySignInDayService.signIn(user.getId());
        MySignInStatistics mySignInStatistics = mySignInStatisticsService.getByUser(user.getId());
        AssertUtil.assertNotNull(mySignInStatistics, "签到信息不存在.");
        MySignInRuleConfig mySignInRuleConfig = mySignInRuleConfigService.get();
        int signNumber = mySignInStatistics.getCurrentSignIn();
        int integral = mySignInDayService.calculateIntegral(signNumber, mySignInRuleConfig);
        MySignInRecord mySignInRecord = mySignInRecordService.listByUserId(user.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage(result ? "签到成功" : "已经签到");
        view.addObject("integral", integral);
        view.addObject("giveIntegral", mySignInRecord.getIntegral());
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listSignInMonth(HttpServletRequest request, Integer year, Integer month) {

        AssertUtil.assertNotNull(year, "年份不能为空");
        AssertUtil.assertNotNull(month, "月份不能为空");
        AssertUtil.assertTrue(year > 2000, "年份必须大于2000 " + year);
        AssertUtil.assertTrue(1 <= month && month <= 12, "月份必须大于等于1小于等于12 " + month);
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MySignInMonth mySignInMonth = mySignInMonthService.getByYearAndMonth(user.getId(), year, month);
        List<MySignInDayVO> voList = null;
        if (mySignInMonth == null) {
            List<MySignInDay> list = mySignInDayService.listByYearAndMonth(user.getId(), year, month);
            voList = mySignInDayHandler.toVOList(list, year, month);
        } else {
            voList = mySignInDayHandler.toVOList(mySignInMonth);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("签到成功.");
        view.addObject("voList", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getSignInMseeage(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MySignInStatistics mySignInStatistics = mySignInStatisticsService.getByUser(user.getId());
        AssertUtil.assertNotNull(mySignInStatistics, "签到信息不存在.");
        MySignInRuleConfig mySignInRuleConfig = mySignInRuleConfigService.get();
        int signNumber = mySignInStatistics.getCurrentSignIn();
        int integral = mySignInDayService.calculateIntegral(signNumber, mySignInRuleConfig);
        int nextDayNumber = mySignInDayService.calculateNextDay(signNumber, mySignInRuleConfig);
        int nextIntegral = mySignInDayService.calculateNextIntegral(signNumber, mySignInRuleConfig);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("签到成功.");
        view.addObject("signNumber", signNumber);
        view.addObject("integral", integral);
        view.addObject("nextDayNumber", nextDayNumber);
        view.addObject("nextIntegral", nextIntegral);
        Date first = mySignInStatistics.getFirstSignInDay();
        if (DateUtil.date2String(first, DateUtil.DATE_FORMAT_STRING).equals(DateUtil.date2String(new Date(), DateUtil.DATE_FORMAT_STRING))) {
            view.addObject("firstIntegral", nextIntegral);
        } else {
            view.addObject("firstIntegral", nextIntegral);
        }
        return view;
    }

    @RequestMapping
    public HtmlView getHtmlRule() {

        MySignInRuleConfig mySignInRuleConfig = mySignInRuleConfigService.get();
        HtmlView view = new HtmlView("<style>img{width:100%;} </style>" + mySignInRuleConfig.getRule());
        return view;
    }
}
