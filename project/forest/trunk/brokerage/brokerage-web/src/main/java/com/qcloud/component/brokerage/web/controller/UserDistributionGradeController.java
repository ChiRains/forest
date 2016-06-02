package com.qcloud.component.brokerage.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alipay.util.AlipayNotify;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.model.UpgradeOrder;
import com.qcloud.component.brokerage.model.UserDistributionGrade;
import com.qcloud.component.brokerage.service.DistributionGradeService;
import com.qcloud.component.brokerage.service.UpgradeOrderService;
import com.qcloud.component.brokerage.service.UserDistributionGradeService;
import com.qcloud.component.brokerage.web.vo.DistributionGradeVO;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.orderform.PaymentModeType;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicdata.EnableType;
import com.qcloud.component.publicservice.exception.PublicServiceException;
import com.qcloud.pirates.core.env.ProjectInfo;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.HtmlView;
import com.qcloud.pirates.mvc.TextView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = UserDistributionGradeController.DIR)
public class UserDistributionGradeController {

    public static final String           DIR    = "/userDistributionGrade";

    @Autowired
    private UserDistributionGradeService userDistributionGradeService;

    @Autowired
    private DistributionGradeService     distributionGradeService;

    @Autowired
    private FileSDKClient                fileSDKClient;

    @Autowired
    private UpgradeOrderService          upgradeOrderService;

    @Autowired
    private ParameterClient              parameterClient;

    private Log                          logger = LogFactory.getLog(getClass());

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getMyDistributionGrade(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        UserDistributionGrade userDistributionGrade = userDistributionGradeService.getByUser(user.getId());
        DistributionGrade distributionGrade = distributionGradeService.get(userDistributionGrade.getGradeId());
        List<DistributionGrade> list = distributionGradeService.listUpgrade(userDistributionGrade.getGradeId());
        //
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询我的分销级别.");
        view.addObject("upgrade", CollectionUtils.isNotEmpty(list));
        view.addObject("gradeName", distributionGrade.getName());
        view.addObject("gradeImage", fileSDKClient.getFileServerUrl() + "/file/get.do?uid=49237A13D7824F958587A7D6D376F517.png");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView toUpgrade(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        UserDistributionGrade userDistributionGrade = userDistributionGradeService.getByUser(user.getId());
        DistributionGrade distributionGrade = distributionGradeService.get(userDistributionGrade.getGradeId());
        List<DistributionGrade> list = distributionGradeService.listUpgrade(userDistributionGrade.getGradeId());
        //
        List<DistributionGradeVO> voList = new ArrayList<DistributionGradeVO>();
        for (DistributionGrade dg : list) {
            DistributionGradeVO vo1 = new DistributionGradeVO();
            vo1.setCash(dg.getCash() - distributionGrade.getCash());
            vo1.setName(dg.getName());
            vo1.setId(dg.getId());
            voList.add(vo1);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("升级级别信息.");
        view.addObject("gradeName", distributionGrade.getName());
        view.addObject("list", voList);
        view.addObject("ruleUrl", "/userDistributionGrade/getHtmlRule.do");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView prepareAlipayPay4Upgrade(HttpServletRequest request, Long gradeId) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        AssertUtil.assertNotNull(gradeId, "会员级别标识不能为空.");
        Map<String, Object> map = userDistributionGradeService.requestAlipayPay(user.getId(), gradeId);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取支付数据准备支付成功.");
        view.addObject("aliPayParam", map);
        logger.info(ProjectInfo.isDev());
        logger.info(map);
        return view;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping
    public TextView alipayPaied(HttpServletRequest request) {

        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        if (AlipayNotify.verify(params)) {
            // 交易状态
            String trade_status = request.getParameter("trade_status");
            //
            if ("TRADE_FINISHED".equals(trade_status) || "TRADE_SUCCESS".equals(trade_status)) {
                // 支付宝交易号
                String trade_no = request.getParameter("trade_no");
                // TODO 支付记录
                // 商户订单号
                String attach = request.getParameter("out_trade_no");
                String[] attachArray = attach.split("_");
                if (attachArray.length >= 3 && userDistributionGradeService.checkIsValidAttach(attach)) {
                    // 这里需要记录一下,微信支付成功的记录
                    String startOrserNumber = attachArray[1].substring(0, 4);
                    String endOrserNumber = attachArray[1].substring(4, 6);
                    Long orderId = Long.parseLong(attachArray[0]);
                    UpgradeOrder upgradeOrder = upgradeOrderService.get(orderId);
                    AssertUtil.assertNotNull(upgradeOrder, "订单不存在." + attach);
                    String orderNumber = upgradeOrder.getOrderNumber();
                    AssertUtil.assertTrue(orderNumber.startsWith(startOrserNumber) || orderNumber.endsWith(endOrserNumber), "通知数据不合法." + attach);
                    if (upgradeOrder.getState() == EnableType.DISABLE.getKey()) {
                        upgradeOrderService.pay(orderId, PaymentModeType.ONLINE_ALIPAY.getKey());
                    } else {
                        throw new PublicServiceException("订单状态已经发生变化." + attach + " " + upgradeOrder.getState());
                    }
                } else {
                    throw new PublicServiceException("订单数据不正确." + attach);
                }
            }
            return new TextView("success");
        } else {
            return new TextView("fail");
        }
    }

    @RequestMapping
    public HtmlView getHtmlRule() {

        String rules = (String) parameterClient.get("brokerage_grade_rules");
        HtmlView view = new HtmlView("<style>img{width:100%;} </style>" + StringUtil.nullToEmpty(rules));
        return view;
    }
}
