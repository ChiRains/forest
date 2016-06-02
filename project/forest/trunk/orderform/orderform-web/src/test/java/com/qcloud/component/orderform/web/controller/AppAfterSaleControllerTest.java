package com.qcloud.component.orderform.web.controller;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.AppParamEncryptUtil;
import com.qcloud.pirates.util.HttpUtils;

public class AppAfterSaleControllerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

//        testReturnOrder();
        testRefundOrder();
    }

    public static void testReturnOrder() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "shuiqi");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_CADC90E8EDC74106B35566FD02EA35A0");
        map.put("explain", "快点退钱");
        map.put("reason", "质量太差");
        map.put("orderId", "1010006000003602");
        map.put("orderDate", "2015-09-28");
        map.put("type", "1");
        System.out.println(HttpUtils.doPost("http://10.10.11.82:8081/afterSale/returnOrder.do?format=true", map));
    }

    public static void testRefundOrder() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_CADC90E8EDC74106B35566FD02EA35A0");
        map.put("explain", "快点退钱");
        map.put("reason", "质量太差");
        map.put("orderId", "1010006000005401");
        map.put("orderDate", "2015-09-28");
        map.put("type", "1");
        System.out.println(HttpUtils.doPost("http://10.10.11.82:8081/app/afterSale/refundOrder.do?format=true", map));
    }
}
