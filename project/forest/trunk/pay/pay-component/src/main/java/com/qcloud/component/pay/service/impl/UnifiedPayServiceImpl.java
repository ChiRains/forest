package com.qcloud.component.pay.service.impl;

import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.pay.PayObject;
import com.qcloud.component.pay.entity.PayParam;
import com.qcloud.component.pay.model.PayRequest;
import com.qcloud.component.pay.model.key.TypeEnum.PayClientType;
import com.qcloud.component.pay.model.key.TypeEnum.PayMethodType;
import com.qcloud.component.pay.service.OperatorService;
import com.qcloud.component.pay.service.PayRequestService;
import com.qcloud.component.pay.service.UnifiedPayService;
import com.qcloud.component.publicservice.PayClient;
import com.qcloud.component.publicservice.QWeiXinRequestPayResult;
import com.qcloud.pirates.core.env.ProjectInfo;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.Base64;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.EncryptUtil;

@Service
public class UnifiedPayServiceImpl implements UnifiedPayService {

    @Autowired
    private ParameterClient   parameterClient;

    @Value("${pirates.pay.key}")
    private String            payKey     = "";

    @Value("${pirates.pay.serverIP}")
    private String            serverIP   = "";

    private final String      DOMAIN_KEY = "domain-key";

    private Log               logger     = LogFactory.getLog(getClass());

    @Autowired
    private OperatorService   operatorService;

    @Autowired
    private PayClient         payClient;

    @Autowired
    private PayRequestService payRequestService;

    @PostConstruct
    public void init() {

        logger.info("payKey encode " + payKey);
        payKey = Base64.decode(payKey);
        logger.info("payKey decode " + payKey);
        AssertUtil.assertNotEmpty(payKey, "支付加密字符串不能为空.");
    }

    @Override
    public Map<String, Object> requestWeiXinPay4WeiXin(String module, String opneId, String ip, Long objectId, Date occurTime) {

        PayObject payObject = operatorService.op(module, objectId, occurTime);
        AssertUtil.assertNotNull(payObject, "支付数据不存在.");
        AssertUtil.assertTrue(payObject.canPay(), "支付数据不在允许支付." + objectId);
        int wxCash = new Double(payObject.getCash() * 100).intValue();
        QWeiXinRequestPayResult result = null;
        String attach = module + "_" + String.valueOf(objectId) + "_" + DateUtil.date2String(occurTime, "yyyyMMddHHmmss");
        attach = encodeAttach(attach);
        // TODO 支付有效时间 ,失效数据不能支付
        if (ProjectInfo.isDev()) {
            result = payClient.getWeiXinPayClient().request(payObject.getObjectNumber(), attach, "支付订单" + payObject.getObjectNumber(), getNotifyUrl("/weixinPay/paied4WeiXin.do"), ip, 1, payObject.getOccurTime(), 30, opneId);
        } else {
            result = payClient.getWeiXinPayClient().request(payObject.getObjectNumber(), attach, "支付订单" + payObject.getObjectNumber(), getNotifyUrl("/weixinPay/paied4WeiXin.do"), ip, wxCash, payObject.getOccurTime(), 30, opneId);
        }
        PayRequest pr = payRequestService.getByObj(module, objectId, PayMethodType.WEIXIN, PayClientType.WEIXIN);
        if (pr == null) {
            pr = new PayRequest();
            pr.setAttach(attach);
            pr.setCash(payObject.getCash());
            pr.setClient(PayClientType.WEIXIN.getKey());
            pr.setModule(module);
            pr.setObjectId(objectId);
            pr.setObjectNumber(payObject.getObjectNumber());
            pr.setOccurTime(occurTime);
            pr.setType(PayMethodType.WEIXIN.getKey());
            payRequestService.add(pr);
        }
        return result.map();
    }

    @Override
    public String requestWeiXinPay4Web(String module, Long objectId, Date occurTime) {

        PayObject payObject = operatorService.op(module, objectId, occurTime);
        AssertUtil.assertNotNull(payObject, "支付数据不存在.");
        AssertUtil.assertTrue(payObject.canPay(), "支付数据不在允许支付." + objectId);
        int wxCash = new Double(payObject.getCash() * 100).intValue();
        String codeUrl = null;
        String attach = module + "_" + String.valueOf(objectId) + "_" + DateUtil.date2String(occurTime, "yyyyMMddHHmmss");
        attach = encodeAttach(attach);
        // TODO 支付有效时间 ,失效数据不能支付
        String ip = serverIP;
        if (ProjectInfo.isDev()) {
            codeUrl = payClient.getWeiXinPayClient().request4Web(payObject.getObjectNumber(), attach, "支付订单" + payObject.getObjectNumber(), getNotifyUrl("/weixinPay/paied4Web.do"), ip, 1, payObject.getOccurTime(), 30);
        } else {
            codeUrl = payClient.getWeiXinPayClient().request4Web(payObject.getObjectNumber(), attach, "支付订单" + payObject.getObjectNumber(), getNotifyUrl("/weixinPay/paied4Web.do"), ip, wxCash, payObject.getOccurTime(), 30);
        }
        PayRequest pr = payRequestService.getByObj(module, objectId, PayMethodType.WEIXIN, PayClientType.WEB);
        if (pr == null) {
            pr = new PayRequest();
            pr.setAttach(attach);
            pr.setCash(payObject.getCash());
            pr.setClient(PayClientType.WEB.getKey());
            pr.setModule(module);
            pr.setObjectId(objectId);
            pr.setObjectNumber(payObject.getObjectNumber());
            pr.setOccurTime(occurTime);
            pr.setType(PayMethodType.WEIXIN.getKey());
            payRequestService.add(pr);
        }
        return codeUrl;
    }

    @Override
    public Map<String, Object> requestWeiXinPay4App(String module, String ip, Long objectId, Date occurTime) {

        PayObject payObject = operatorService.op(module, objectId, occurTime);
        AssertUtil.assertNotNull(payObject, "支付数据不存在.");
        AssertUtil.assertTrue(payObject.canPay(), "支付数据不在允许支付." + objectId);
        int wxCash = new Double(payObject.getCash() * 100).intValue();
        QWeiXinRequestPayResult result = null;
        String attach = module + "_" + String.valueOf(objectId) + "_" + DateUtil.date2String(occurTime, "yyyyMMddHHmmss");
        attach = encodeAttach(attach);
        // TODO 支付有效时间 ,失效数据不能支付
        if (ProjectInfo.isDev()) {
            result = payClient.getWeiXinPayClient().request4App(payObject.getObjectNumber(), attach, "支付订单" + payObject.getObjectNumber(), getNotifyUrl("/weixinPay/paied4App.do"), ip, 1, payObject.getOccurTime(), 30);
        } else {
            result = payClient.getWeiXinPayClient().request4App(payObject.getObjectNumber(), attach, "支付订单" + payObject.getObjectNumber(), getNotifyUrl("/weixinPay/paied4App.do"), ip, wxCash, payObject.getOccurTime(), 30);
        }
        PayRequest pr = payRequestService.getByObj(module, objectId, PayMethodType.WEIXIN, PayClientType.WEIXIN);
        if (pr == null) {
            pr = new PayRequest();
            pr.setAttach(attach);
            pr.setCash(payObject.getCash());
            pr.setClient(PayClientType.APP.getKey());
            pr.setModule(module);
            pr.setObjectId(objectId);
            pr.setObjectNumber(payObject.getObjectNumber());
            pr.setOccurTime(occurTime);
            pr.setType(PayMethodType.WEIXIN.getKey());
            payRequestService.add(pr);
        }
        return result.map();
    }

    @Override
    public boolean weiXinPaid4WeiXin(String module, Long objectId, Date occurTime) {

        return operatorService.notify(module, objectId, occurTime);
    }

    @Override
    public boolean weiXinPaid4Web(String module, Long objectId, Date occurTime) {

        return operatorService.notify(module, objectId, occurTime);
    }

    @Override
    public boolean weiXinPaid4App(String module, Long objectId, Date occurTime) {

        return operatorService.notify(module, objectId, occurTime);
    }

    @Override
    public Map<String, Object> requestAliPay4Web() {

        return null;
    }

    @Override
    public Map<String, Object> requestAliPay4App() {

        return null;
    }

    @Override
    public boolean aliPaid4Web() {

        return false;
    }

    @Override
    public boolean aliPaid4App() {

        return false;
    }

    @Override
    public Map<String, Object> requestUnionPay4WeiXin() {

        return null;
    }

    @Override
    public Map<String, Object> requestUnionPay4Web() {

        return null;
    }

    @Override
    public Map<String, Object> requestUnionPay4App() {

        return null;
    }

    @Override
    public boolean unionPaid4WeiXin() {

        return false;
    }

    @Override
    public boolean unionPaid4Web() {

        return false;
    }

    @Override
    public boolean unionPaid4App() {

        return false;
    }

    @Override
    public Map<String, Object> requestBestPay4WeiXin() {

        return null;
    }

    @Override
    public Map<String, Object> requestBestPay4Web() {

        return null;
    }

    @Override
    public Map<String, Object> requestBestPay4App() {

        return null;
    }

    @Override
    public boolean bestPaid4WeiXin() {

        return false;
    }

    @Override
    public boolean bestPaid4Web() {

        return false;
    }

    @Override
    public boolean bestPaid4App() {

        return false;
    }

    @Override
    public PayParam checkValidAndDeParseAttach(String attach) {

        if (attach == null) {
            return null;
        }
        String[] attachs = attach.split("_");
        if (attachs.length < 4) {
            return null;
        }
        String str = attachs[0] + "_" + attachs[1] + "_" + attachs[2];
        if (encodeAttach(str).equals(attach)) {
            return new PayParam(Long.parseLong(attachs[1]), DateUtil.str2Date(attachs[2], "yyyyMMddHHmmss"), attachs[0]);
        }
        return null;
    }

    private String encodeAttach(String attach) {

        return attach + "_" + EncryptUtil.md5(payKey + "@" + attach);
    }

    private String getNotifyUrl(String notifyUri) {

        return "http://" + parameterClient.get(DOMAIN_KEY) + notifyUri;
    }
}
