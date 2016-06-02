package com.qcloud.component.personalcenter.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.personalcenter.WealthType;
import com.qcloud.component.personalcenter.model.MyBankCard;
import com.qcloud.component.personalcenter.model.MyCommissionWithdrawCash;
import com.qcloud.component.personalcenter.model.MyWealth;
import com.qcloud.component.personalcenter.model.MyWealthDetail;
import com.qcloud.component.personalcenter.service.MyBankCardService;
import com.qcloud.component.personalcenter.service.MyCommissionWithdrawCashService;
import com.qcloud.component.personalcenter.service.MyWealthDetailService;
import com.qcloud.component.personalcenter.service.MyWealthService;
import com.qcloud.component.personalcenter.web.handler.MyCommissionWithdrawCashHandler;
import com.qcloud.component.personalcenter.web.handler.MyWealthDetailHandler;
import com.qcloud.component.personalcenter.web.vo.MyCommissionWithdrawCashVO;
import com.qcloud.component.personalcenter.web.vo.MyWealthDetailVO;
import com.qcloud.component.personalcenter.web.vo.MyWealthVO;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = MyWealthController.DIR)
public class MyWealthController {

    public static final String              DIR = "/myWealth";

    @Autowired
    private MyWealthService                 myWealthService;

    @Autowired
    private MyWealthDetailService           myWealthDetailService;

    @Autowired
    private MyWealthDetailHandler           myWealthDetailHandler;

    @Autowired
    private MyBankCardService               myBankCardService;

    @Autowired
    private MyCommissionWithdrawCashService myCommissionWithdrawCashService;

    @Autowired
    private MyCommissionWithdrawCashHandler myCommissionWithdrawCashHandler;

    @Autowired
    private PersonalcenterClient            personalcenterClient;

    @Autowired
    private FileSDKClient                   fileSDKClient;

    // type 1按佣金排序 2按消费币排序 3按积分排序
    @PiratesApp
    @RequestMapping
    public FrontAjaxView listTopWealth(Integer number, Integer type) {

        number = number == null || number <= 0 || number > 20 ? 5 : number;
        type = type == null || type <= 0 || type > 3 ? 1 : type;
        List<MyWealth> list = myWealthService.listTop(number, type);
        List<MyWealthVO> voList = new ArrayList<MyWealthVO>();
        for (MyWealth myWealth : list) {
            MyWealthVO vo = new MyWealthVO();
            vo.setCommission(myWealth.getCommissionSummation());
            vo.setIntegral(myWealth.getIntegralSummation());
            vo.setConsumptionCurrency(myWealth.getConsumptionCurrency());
            QUser user = personalcenterClient.getUser(myWealth.getId());
            vo.setName(user.getNickname());
            if (StringUtils.isNotEmpty(user.getHeadImage())) {
                vo.setHeadImage(fileSDKClient.getFileServerUrl() + user.getHeadImage());
            } else {
                vo.setHeadImage(StringUtil.nullToEmpty(user.getHeadImage()));
            }
            voList.add(vo);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询财富排行榜成功.");
        view.addObject("dataList", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView withdrawCommission(HttpServletRequest request, Long bankCardId, Double cash) {

        AssertUtil.assertTrue(new Double(cash * 100).longValue() > 0, "提现金额必须大于0." + cash);
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        AssertUtil.assertNotNull(bankCardId, "提现银行卡不能为空.");
        MyBankCard myBankCard = myBankCardService.get(bankCardId);
        AssertUtil.assertNotNull(myBankCard, "提现银行卡不存在." + bankCardId);
        AssertUtil.assertTrue(user.getId() == myBankCard.getUserId(), "不能提现到其他人银行卡.");
        MyWealth myWealth = myWealthService.getByUserId(user.getId());
        AssertUtil.assertTrue(myWealth.getCommission() >= cash, "提现金额必须大于账户余额." + myWealth.getCommission());
        myWealthService.withdrawCommission(user.getId(), bankCardId, cash);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("申请提现成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listWithdrawCommission(HttpServletRequest request, String checkTime, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyCommissionWithdrawCash> list = myCommissionWithdrawCashService.listByUser(user.getId(), checkTime, pPage.getPageStart(), pPage.getPageSize());
        List<MyCommissionWithdrawCashVO> voList = myCommissionWithdrawCashHandler.toVOList(list);
        double totalCash = 0;
        for (MyCommissionWithdrawCashVO vo : voList) {
            totalCash = totalCash + vo.getCommissionCash();
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取资金明细列表成功.");
        view.addObject("list", voList);
        view.addObject("totalCash", totalCash);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listWithdrawingCommission(HttpServletRequest request, Integer pageSize, Integer pageNum) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        final int PAGE_SIZE = pageSize == null || pageSize <= 0 ? 10 : pageSize;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        List<MyCommissionWithdrawCash> list = myCommissionWithdrawCashService.listWithdrawingByUser(user.getId(), start, PAGE_SIZE);
        List<MyCommissionWithdrawCashVO> voList = myCommissionWithdrawCashHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取提现明细列表成功.");
        view.addObject("list", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listWithdrawedCommission(HttpServletRequest request, Integer pageSize, Integer pageNum) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        final int PAGE_SIZE = pageSize == null || pageSize <= 0 ? 10 : pageSize;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        List<MyCommissionWithdrawCash> list = myCommissionWithdrawCashService.listWithdrawedByUser(user.getId(), start, PAGE_SIZE);
        List<MyCommissionWithdrawCashVO> voList = myCommissionWithdrawCashHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取提现明细列表成功.");
        view.addObject("list", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listIntegralDetail(HttpServletRequest request, String checkTime, Integer detailType, Integer pageSize, Integer pageNum) {

        return listWealthDetail(request, checkTime, WealthType.INTEGRAL, detailType, pageSize, pageNum);
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listCommissionDetail(HttpServletRequest request, String checkTime, Integer detailType, Integer pageSize, Integer pageNum) {

        return listWealthDetail(request, checkTime, WealthType.COMMISSION, detailType, pageSize, pageNum);
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listCurrencyDetail(HttpServletRequest request, String checkTime, Integer detailType, Integer pageSize, Integer pageNum) {

        return listWealthDetail(request, checkTime, WealthType.COMSUMPTION_CURRENCY, detailType, pageSize, pageNum);
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView integralToCurrency(HttpServletRequest request, Integer integral) {

        AssertUtil.assertTrue(integral != null && integral > 0, "积分转换,积分不能为空或者小于零" + integral);
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        myWealthService.integralToCurrency(user.getId(), integral);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("积分转换消费币成功.");
        return view;
    }

    private FrontAjaxView listWealthDetail(HttpServletRequest request, String checkTime, WealthType wealthType, Integer detailType, Integer pageSize, Integer pageNum) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MyWealth myWealth = myWealthService.getByUserId(user.getId());
        final int PAGE_SIZE = pageSize == null || pageSize <= 0 ? 10 : pageSize;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        // 按月2015-11
        Date beginTime = null;
        Date endTime = null;
        if (!StringUtils.isEmpty(checkTime)) {
            beginTime = DateUtil.str2Date(checkTime + "-01", DateUtil.DATE_FORMAT_STRING);
            endTime = DateUtils.addMonths(beginTime, 1);
        }
        List<MyWealthDetail> list = myWealthDetailService.listByUserAndTime(myWealth.getId(), user.getId(), wealthType.getKey(), detailType, beginTime, endTime, start, PAGE_SIZE);
        double sum = myWealthDetailService.sumByUserAndTime(myWealth.getId(), user.getId(), wealthType.getKey(), detailType, beginTime, endTime, start, PAGE_SIZE);
        List<MyWealthDetailVO> voList = myWealthDetailHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询财富明细成功.");
        view.addObject("list", voList);
        // 总数
        // double sum = 0;
        // for (MyWealthDetailVO vo : voList) {
        // sum = vo.getPoint() + sum;
        // }
        view.addObject("sum", sum);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView statIntegralTrend(HttpServletRequest request, Date begin, Date end) {

        return statTrend(request, WealthType.INTEGRAL, begin, end);
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView statCommissionTrend(HttpServletRequest request, Date begin, Date end) {

        return statTrend(request, WealthType.COMMISSION, begin, end);
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView statCurrencyTrend(HttpServletRequest request, Date begin, Date end) {

        return statTrend(request, WealthType.COMSUMPTION_CURRENCY, begin, end);
    }

    private FrontAjaxView statTrend(HttpServletRequest request, WealthType wealthType, Date begin, Date end) {

        AssertUtil.assertNotNull(begin, "统计开始时间不能为空.");
        AssertUtil.assertNotNull(end, "统计结束时间不能为空.");
        AssertUtil.assertTrue(begin.before(end), "统计开始时间必须早于统计结束时间");
        begin = DateUtil.str2Date(DateUtil.date2String(begin, DateUtil.DATE_FORMAT_STRING) + " 00:00:00");
        end = DateUtil.str2Date(DateUtil.date2String(end, DateUtil.DATE_FORMAT_STRING) + " 23:59:59");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MyWealth myWealth = myWealthService.getByUserId(user.getId());
        List<MyWealthDetail> list = myWealthDetailService.listByTime(myWealth.getId(), user.getId(), wealthType.getKey(), begin, end);
        List<MyWealthDetailVO> voList = myWealthDetailHandler.toVOList4Stat(list, begin, end);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("统计财富排行榜成功.");
        view.addObject("list", voList);
        return view;
    }
}
