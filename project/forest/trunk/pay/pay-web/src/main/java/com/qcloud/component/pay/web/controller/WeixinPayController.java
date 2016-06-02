package com.qcloud.component.pay.web.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.qcloud.component.pay.entity.PayParam;
import com.qcloud.component.pay.model.PayRecord;
import com.qcloud.component.pay.model.key.TypeEnum.PayClientType;
import com.qcloud.component.pay.model.key.TypeEnum.PayMethodType;
import com.qcloud.component.pay.service.PayRecordService;
import com.qcloud.component.pay.service.UnifiedPayService;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicservice.exception.PublicServiceException;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.tencent.common.Signature;
import com.tencent.common.XMLParser;

// 微信支付
@Controller
@RequestMapping(value = WeixinPayController.DIR)
public class WeixinPayController {

    public static final String   DIR    = "/weixinPay";

    @Autowired
    private UnifiedPayService    unifiedPayService;

    @Autowired
    private PersonalcenterClient personalcenterClient;

    private Log                  logger = LogFactory.getLog(getClass());

    @Autowired
    private PayRecordService     payRecordService;

    @RequestMapping
    @NoReferer
    public FrontAjaxView preparePay4WeiXin(HttpServletRequest request, String module, Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(orderId, "订单标识不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空");
        String ip = RequestUtil.getProxyIp(request);
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        String opneId = personalcenterClient.getThirdId(user.getId());
        Map<String, Object> map = unifiedPayService.requestWeiXinPay4WeiXin(module, opneId, ip, orderId, orderDate);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取支付数据准备支付成功.");
        view.addObject("wxPayParam", map);
        logger.info(map);
        return view;
    }

    @RequestMapping
    @NoReferer
    public FrontAjaxView paied4WeiXin(HttpServletRequest request, String return_code, String return_msg) {

        try {
            ServletInputStream servletInputStream = request.getInputStream();
            String xml = inputstr2Str_Reader(servletInputStream);
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
                    if (attachArray.length == 4) {
                        PayParam payParam = unifiedPayService.checkValidAndDeParseAttach(attach);
                        AssertUtil.assertNotNull(payParam, "支付数据解析有误.");
                        boolean paid = unifiedPayService.weiXinPaid4WeiXin(payParam.getModule(), payParam.getObjectId(), payParam.getOccurTime());
                        AssertUtil.assertTrue(paid, "支付通知失败." + payParam.getModule() + " " + payParam.getObjectId());
                        PayRecord payRecord = new PayRecord();
                        payRecord.setAttach(attach);
                        payRecord.setClient(PayClientType.WEIXIN.getKey());
                        payRecord.setModule(payParam.getModule());
                        payRecord.setObjectId(payParam.getObjectId());
                        payRecord.setOccurTime(payParam.getOccurTime());
                        payRecord.setTime(new Date());
                        payRecord.setTradeId("");
                        payRecord.setType(PayMethodType.WEIXIN.getKey());
                        payRecordService.add(payRecord);
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

    @RequestMapping
    @NoReferer
    public FrontAjaxView preparePay4Web(HttpServletRequest request, HttpServletResponse response, String module, Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(orderId, "订单标识不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空");
        String codeUrl = unifiedPayService.requestWeiXinPay4Web(module, orderId, orderDate);
        // encodeQrcode(codeUrl, response);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取支付数据准备支付成功.");
        view.addObject("codeUrl", codeUrl);
        return view;
    }

    /**
          * 生成二维码图片 不存储 直接以流的形式输出到页面
          * @param content
          * @param response
          */
    @SuppressWarnings({ "unchecked", "rawtypes"})
    public static void encodeQrcode(String content, HttpServletResponse response) {

        if (StringUtils.isBlank(content)) return;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 设置字符集编码类型
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);
            BufferedImage image = toBufferedImage(bitMatrix);
            // 输出二维码图片流
            try {
                ImageIO.write(image, "png", response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (WriterException e1) {
            e1.printStackTrace();
        }
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {

        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    private static final int BLACK = 0xFF000000;

    private static final int WHITE = 0xFFFFFFFF;

    @RequestMapping
    @NoReferer
    public FrontAjaxView paied4Web(HttpServletRequest request, String return_code, String return_msg) {

        try {
            ServletInputStream servletInputStream = request.getInputStream();
            String xml = inputstr2Str_Reader(servletInputStream);
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
                    if (attachArray.length == 4) {
                        PayParam payParam = unifiedPayService.checkValidAndDeParseAttach(attach);
                        AssertUtil.assertNotNull(payParam, "支付数据解析有误.");
                        boolean paid = unifiedPayService.weiXinPaid4Web(payParam.getModule(), payParam.getObjectId(), payParam.getOccurTime());
                        AssertUtil.assertTrue(paid, "支付通知失败." + payParam.getModule() + " " + payParam.getObjectId());
                        PayRecord payRecord = new PayRecord();
                        payRecord.setAttach(attach);
                        payRecord.setClient(PayClientType.WEB.getKey());
                        payRecord.setModule(payParam.getModule());
                        payRecord.setObjectId(payParam.getObjectId());
                        payRecord.setOccurTime(payParam.getOccurTime());
                        payRecord.setTime(new Date());
                        payRecord.setTradeId("");
                        payRecord.setType(PayMethodType.WEIXIN.getKey());
                        payRecordService.add(payRecord);
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

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView preparePay4App(HttpServletRequest request, String module, Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(orderId, "订单标识不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空");
        String ip = RequestUtil.getProxyIp(request);
        Map<String, Object> map = unifiedPayService.requestWeiXinPay4App(module, ip, orderId, orderDate);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取支付数据准备支付成功.");
        view.addObject("wxPayParam", map);
        logger.info(map);
        return view;
    }

    @RequestMapping
    @NoReferer
    public FrontAjaxView paied4App(HttpServletRequest request, String return_code, String return_msg) {

        try {
            ServletInputStream servletInputStream = request.getInputStream();
            String xml = inputstr2Str_Reader(servletInputStream);
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
                    if (attachArray.length == 4) {
                        PayParam payParam = unifiedPayService.checkValidAndDeParseAttach(attach);
                        AssertUtil.assertNotNull(payParam, "支付数据解析有误.");
                        boolean paid = unifiedPayService.weiXinPaid4App(payParam.getModule(), payParam.getObjectId(), payParam.getOccurTime());
                        AssertUtil.assertTrue(paid, "支付通知失败." + payParam.getModule() + " " + payParam.getObjectId());
                        PayRecord payRecord = new PayRecord();
                        payRecord.setAttach(attach);
                        payRecord.setClient(PayClientType.WEIXIN.getKey());
                        payRecord.setModule(payParam.getModule());
                        payRecord.setObjectId(payParam.getObjectId());
                        payRecord.setOccurTime(payParam.getOccurTime());
                        payRecord.setTime(new Date());
                        payRecord.setTradeId("");
                        payRecord.setType(PayMethodType.WEIXIN.getKey());
                        payRecordService.add(payRecord);
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

    private static String inputstr2Str_Reader(InputStream in) {

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
