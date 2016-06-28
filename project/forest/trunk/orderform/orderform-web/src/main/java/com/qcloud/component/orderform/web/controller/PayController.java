package com.qcloud.component.orderform.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alipay.util.AlipayNotify;
import com.qcloud.component.orderform.PaymentModeType;
import com.qcloud.component.orderform.engine.OrderSelecterService;
import com.qcloud.component.orderform.engine.OrderStateService;
import com.qcloud.component.orderform.engine.PayService;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.service.CollectOrderService;
import com.qcloud.component.orderform.web.util.OrderStateType;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicservice.WeiXinPayClient;
import com.qcloud.component.publicservice.exception.PublicServiceException;
import com.qcloud.pirates.core.env.ProjectInfo;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.TextView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.tencent.common.Signature;
import com.tencent.common.XMLParser;

@Controller
@RequestMapping(value = PayController.DIR)
public class PayController {

    public static final String DIR    = "/pay";

    @Autowired
    PayService                 payService;

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
    OrderSelecterService       orderSelecterService;

    @Autowired
    OrderStateService          orderStateService;

    @Autowired
    CollectOrderService        collectOrderService;

    Log                        logger = LogFactory.getLog(getClass());

    // @RequestMapping
    // @NoReferer
    // public FrontAjaxView getPrepareWeiXinPayParam(Long orderId, Date orderDate) {
    //
    // String appId = weiXinPayClient.getWeiXinAppId();
    // String redirect_uri = "http://" + "wxpay.qi-cloud.com" + "/pay/preparePay.do?orderId=" + orderId + "&orderDate=" + DateUtil.date2String(orderDate, DateUtil.DATE_FORMAT_STRING);
    // FrontAjaxView view = new FrontAjaxView();
    // view.setMessage("获取支付数据准备支付成功.");
    // view.addObject("appId", appId);
    // view.addObject("redirectUri", redirect_uri);
    // return view;
    // }
    // @RequestMapping
    // @NoReferer
    // public FrontAjaxView getPrepareWeiXinPayUrl(Long orderId, Date orderDate) {
    //
    // AssertUtil.assertNotNull(orderId, "订单标识不能为空.");
    // AssertUtil.assertNotNull(orderDate, "订单日期不能为空");
    // String redirect_uri = "http://" + "wxpay.qi-cloud.com" + "/pay/preparePay.do?orderId=" + orderId + "&orderDate=" + DateUtil.date2String(orderDate, DateUtil.DATE_FORMAT_STRING);
    // String appId = weiXinPayClient.getWeiXinAppId();
    // String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + "&redirect_uri=" + UrlEncoded.encodeString(redirect_uri) + "&response_type=code&scope=snsapi_base&state=STATE";
    // // try {
    // // String resu = new HttpsRequest().sendPost(url, new HashMap());
    // // System.out.println("r  " + resu);
    // // } catch (Exception e) {
    // // e.printStackTrace();
    // // }
    // FrontAjaxView view = new FrontAjaxView();
    // view.setMessage("获取支付数据准备支付成功.");
    // view.addObject("payUrl", url);
    // System.out.println(url);
    // return view;
    // }
    // weixin
    @RequestMapping
    @NoReferer
    public FrontAjaxView preparePay(HttpServletRequest request, Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(orderId, "订单标识不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空");
        String ip = RequestUtil.getProxyIp(request);
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        String opneId = personalcenterClient.getThirdId(user.getId());
        Map<String, Object> map = payService.requestWeiXinPay(opneId, ip, orderId, orderDate);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取支付数据准备支付成功.");
        view.addObject("wxPayParam", map);
        logger.info(map);
        return view;
    }

    @RequestMapping
    @NoReferer
    public FrontAjaxView paied(HttpServletRequest request, String return_code, String return_msg) {

        try {
            ServletInputStream servletInputStream = request.getInputStream();
            String xml = Inputstr2Str_Reader(servletInputStream);
            if (!Signature.checkIsSignValidFromResponseString(xml)) {
                throw new PublicServiceException("处理微信支付异步通知失败,签名失败");
            }
            logger.info("支付完成了 " + xml);
            Map<String, Object> map = XMLParser.getMapFromXML(xml);
            Object resultCode = map.get("return_code");
            logger.info("resultCode " + resultCode);
            Object msg = map.get("return_msg");
            logger.info("return_msg " + msg);
            if ("SUCCESS".equals(resultCode)) {
                String result_code = (String) map.get("result_code");
                if ("SUCCESS".equals(result_code)) {
                    String attach = (String) map.get("attach");
                    String[] attachArray = attach.split("_");
                    if (attachArray.length >= 3 && payService.checkIsValidAttach(attach)) {
                        // 这里需要记录一下,微信支付成功的记录
                        Long orderId = Long.parseLong(attachArray[0]);
                        Date orderDate = DateUtil.str2Date(attachArray[1], "yyyyMMddHHmmss");
                        OrderEntity orderEntity = orderSelecterService.getOrder(orderId, orderDate);
                        AssertUtil.assertNotNull(orderEntity, "订单不存在." + attach);
                        if (orderEntity.getState() < OrderStateType.NORMAL_TO_PACKING.getKey()) {
                            orderStateService.exchangeOrderState(orderId, orderDate, OrderStateType.NORMAL_TO_PACKING.getKey(), -1L);
                            collectOrderService.updatePaymentMode(orderId, orderDate, PaymentModeType.ONLINE_MICROCHANNELPAY);
                        } else {
                            throw new PublicServiceException("订单状态已经发生变化." + attach + " " + orderEntity.getState());
                        }
                    } else {
                        throw new PublicServiceException("订单数据不正确." + attach);
                    }
                    // String device_info = (String) map.get("device_info");
                    // String nonce_str = (String) map.get("nonce_str");
                    // String trade_type = (String) map.get("trade_type");
                    String appId = (String) map.get("appid");
                    String mch_id = (String) map.get("mch_id");
                    String code_url = (String) map.get("code_url");
                    String prepay_id = (String) map.get("prepay_id");
                    logger.info("appId " + appId);
                    logger.info("mch_id " + mch_id);
                    logger.info("code_url " + code_url);
                    logger.info("prepay_id " + prepay_id);
                } else {
                    String err_code = (String) map.get("err_code");
                    String err_code_des = (String) map.get("err_code_des");
                    logger.info("err_code " + err_code);
                    logger.info("err_code_des " + err_code_des);
                }
                FrontAjaxView view = new FrontAjaxView();
                view.setMessage("支付成功.");
                return view;
            } else {
                FrontAjaxView view = new FrontAjaxView();
                view.setMessage("支付失败.");
                return view;
            }
        } catch (Exception e) {
            throw new PublicServiceException("处理微信支付异步通知失败" + e.getMessage(), e);
        }
    }

    // alipay
    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView prepareAlipayPay(HttpServletRequest request, Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(orderId, "订单标识不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空");
        Map<String, Object> map = payService.requestAlipayPay(orderId, orderDate);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取支付数据准备支付成功.");
        view.addObject("aliPayParam", map);
        logger.info(ProjectInfo.isDev());
        logger.info(map);
        return view;
    }

    @RequestMapping
    @NoReferer
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
                //
                // 商户订单号
                String attach = request.getParameter("out_trade_no");
                String[] attachArray = attach.split("_");
                if (attachArray.length >= 3 && payService.checkIsValidAttach(attach)) {
                    // 这里需要记录一下,微信支付成功的记录
                    Long orderId = Long.parseLong(attachArray[0]);
                    Date orderDate = DateUtil.str2Date(attachArray[1], "yyyyMMddHHmmss");
                    OrderEntity orderEntity = orderSelecterService.getOrder(orderId, orderDate);
                    AssertUtil.assertNotNull(orderEntity, "订单不存在." + attach);
                    if (orderEntity.getState() < OrderStateType.NORMAL_TO_PACKING.getKey()) {
                        orderStateService.exchangeOrderState(orderId, orderDate, OrderStateType.NORMAL_TO_PACKING.getKey(), -1L);
                        collectOrderService.updatePaymentMode(orderId, orderDate, PaymentModeType.ONLINE_ALIPAY);
                    } else {
                        throw new PublicServiceException("订单状态已经发生变化." + attach + " " + orderEntity.getState());
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

    private static String Inputstr2Str_Reader(InputStream in) {

        try {
            String str = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            StringBuffer sb = new StringBuffer();
            while ((str = reader.readLine()) != null) {
                sb.append(str).append("\n");
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e1) {
            throw new PublicServiceException("处理微信支付异步通知失败" + e1.getMessage(), e1);
        } catch (IOException e) {
            throw new PublicServiceException("处理微信支付异步通知失败" + e.getMessage(), e);
        }
    }
}
