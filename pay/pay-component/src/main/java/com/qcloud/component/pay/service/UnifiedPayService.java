package com.qcloud.component.pay.service;

import java.util.Date;
import java.util.Map;
import com.qcloud.component.pay.entity.PayParam;

public interface UnifiedPayService {

    Map<String, Object> requestWeiXinPay4WeiXin(String module, String opneId, String ip, Long objectId, Date occurTime);

    String requestWeiXinPay4Web(String module, Long objectId, Date occurTime);

    Map<String, Object> requestWeiXinPay4App(String module, String ip, Long objectId, Date occurTime);

    boolean weiXinPaid4WeiXin(String module, Long objectId, Date occurTime);

    boolean weiXinPaid4Web(String module, Long objectId, Date occurTime);

    boolean weiXinPaid4App(String module, Long objectId, Date occurTime);

    // //
    Map<String, Object> requestAliPay4Web();

    Map<String, Object> requestAliPay4App();

    boolean aliPaid4Web();

    boolean aliPaid4App();

    // //
    Map<String, Object> requestUnionPay4WeiXin();

    Map<String, Object> requestUnionPay4Web();

    Map<String, Object> requestUnionPay4App();

    boolean unionPaid4WeiXin();

    boolean unionPaid4Web();

    boolean unionPaid4App();

    // //
    Map<String, Object> requestBestPay4WeiXin();

    Map<String, Object> requestBestPay4Web();

    Map<String, Object> requestBestPay4App();

    boolean bestPaid4WeiXin();

    boolean bestPaid4Web();

    boolean bestPaid4App();

    //
    PayParam checkValidAndDeParseAttach(String attach);
}
