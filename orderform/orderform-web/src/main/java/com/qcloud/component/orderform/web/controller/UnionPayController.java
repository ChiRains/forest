package com.qcloud.component.orderform.web.controller;

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.orderform.engine.OrderSelecterService;
import com.qcloud.component.orderform.engine.OrderStateService;
import com.qcloud.component.orderform.engine.UnionPayService;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.web.util.OrderStateType;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicservice.WeiXinPayClient;
import com.qcloud.component.publicservice.exception.PublicServiceException;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.HtmlView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.union.common.AcpsdkProperties;
import com.union.common.BackRcvResponse;

@Controller
@RequestMapping(value = UnionPayController.DIR)
public class UnionPayController {

    public static final String DIR    = "/unionPay";

    @Autowired
    UnionPayService            unionPayService;

    @Autowired
    WeiXinPayClient            weiXinPayClient;

    @Autowired
    PersonalcenterClient       personalcenterClient;

//    @Autowired
//    UserFilterService          userFilterService;
//
//    @Autowired
//    TokenClient                tokenClient;

    @Autowired
    OrderStateService          orderStateService;

    @Autowired
    OrderSelecterService       orderSelecterService;

    @Autowired
    ParameterClient            parameterClient;

    Log                        logger = LogFactory.getLog(getClass());

    /**
     * 银联支付
     * @return
     */
    @RequestMapping
    @NoReferer
    public HtmlView prepareUnionPay(HttpServletRequest request, Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(orderId, "订单标识不能为空.");
        AssertUtil.assertNotNull(orderDate, "下单时间不能为空.");
        String ip = RequestUtil.getProxyIp(request);
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        String html = unionPayService.requestUnionPayHtml(user.getId(), ip, orderId, orderDate);
        HtmlView view = new HtmlView(html);
        return view;
    }

    /**
     * 银联支付(回调接口)
     * @return
     */
    @RequestMapping
    @NoReferer
    public FrontAjaxView unionPaied(HttpServletRequest request) {

        FrontAjaxView view = new FrontAjaxView();
        Map<String, String> valideData = BackRcvResponse.checkIsSignValidFromResponseString(request);
        String reqReserved = valideData.get("reqReserved");
        String[] reqReservedArray = reqReserved.split("_");
        Long orderId = Long.valueOf(reqReservedArray[0]);
        Date orderDate = DateUtil.str2Date(reqReservedArray[1], "yyyyMMddHHmmss");
        OrderEntity orderEntity = orderSelecterService.getOrder(orderId, orderDate);
        AssertUtil.assertNotNull(orderEntity, "订单不存在." + reqReserved);
        if (orderEntity.getState() < OrderStateType.NORMAL_PAID.getKey()) {
            orderStateService.exchangeOrderState(orderId, orderDate, OrderStateType.NORMAL_PAID.getKey(), -1L);
        } else {
            throw new PublicServiceException("订单状态已经发生变化." + reqReserved + " " + orderEntity.getState());
        }
        view.setMessage("支付成功.");
        return view;
    }

    /**
     * 返回商户地址，解决nginx 405 not allowed
     * @return
     */
    @RequestMapping
    public ModelAndView frontUrl() {

        ModelAndView model = new ModelAndView("redirect:" + parameterClient.get("frontUrl-key"));
        return model;
    }

//    private QUser getUser(HttpServletRequest request) {
//
//        String token = userFilterService.getTokenId(request);
//        AssertUtil.assertNotEmpty(token, "获取用户登录信息失败.");
//        String idStr = tokenClient.get(token);
//        AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
//        QUser user = personalcenterClient.getUser(Long.parseLong(idStr));
//        AssertUtil.assertNotNull(user, "用户不存在.");
//        return user;
//    }

    /**
     * 银联支付--(多商户，水漆项目的奇葩需求)
     * @return
     */
    @RequestMapping
    @NoReferer
    public HtmlView prepareUnionPay4Muti(HttpServletRequest request, Long orderId, Date orderDate, int type) {

        AssertUtil.assertNotNull(orderId, "订单标识不能为空.");
        AssertUtil.assertNotNull(orderDate, "下单时间不能为空.");
        String ip = RequestUtil.getProxyIp(request);
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        String html = "";
        if (type == Integer.valueOf(1)) {
            html = unionPayService.requestUnionPayHtml(user.getId(), ip, orderId, orderDate);
        } else if (type == Integer.valueOf(2)) {
            String signCertPath = String.valueOf(parameterClient.get("signcertPath2-key")) + "/" + String.valueOf(parameterClient.get("signcert2-key"));
            String validateCertDir = String.valueOf(parameterClient.get("signcertPath2-key"));
            String encryptCertPath = String.valueOf(parameterClient.get("signcertPath2-key")) + "/encryptpub.cer";
            String signCertPwd = String.valueOf(parameterClient.get("signCertPwd2-key"));
            AcpsdkProperties acpsdkProperties = new AcpsdkProperties();
            // 商户号
            acpsdkProperties.setMerId(String.valueOf(parameterClient.get("merId2-key")));
            acpsdkProperties.setSignCertType("PKCS12");
            // 证书路径
            acpsdkProperties.setSignCertPath(signCertPath);
            // 证书密码
            acpsdkProperties.setSignCertPwd(signCertPwd);
            acpsdkProperties.setValidateCertDir(validateCertDir);
            acpsdkProperties.setEncryptCertPath(encryptCertPath);
            html = unionPayService.requestUnionPayHtml(user.getId(), ip, orderId, orderDate, acpsdkProperties);
        } else {
        }
        HtmlView view = new HtmlView(html);
        return view;
    }
}
