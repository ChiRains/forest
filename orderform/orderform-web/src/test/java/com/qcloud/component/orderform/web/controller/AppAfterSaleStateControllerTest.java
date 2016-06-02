package com.qcloud.component.orderform.web.controller;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.AppParamEncryptUtil;
import com.qcloud.pirates.util.HttpUtils;

public class AppAfterSaleStateControllerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        testConfirmRefund();
        // testConfirmReturn();
        // testRefuseReturn();
        // testShippedReturn();
        // testSignReturn();
        // testPayReturn();
        // testConfirmPayReturn();
        // //
        // testConfirmExchange();
        // testRefuseExchange();
        // testShippedExchange();
        // testSignExchange();
        // testShippedAgainExchange();
        // testSignAgainExchange();
    }

    public static void testConfirmRefund() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_CADC90E8EDC74106B35566FD02EA35A0");
        map.put("refundId", "1010006000001401");
        map.put("sum", "32");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/afterSaleState/confirmPayRefund.do?format=true", map));
    }

    public static void testConfirmReturn() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        map.put("returnId", "1010006000002405");
        map.put("sum", "32");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/afterSaleState/confirmReturn.do?format=true", map));
    }

    public static void testRefuseReturn() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        map.put("returnId", "1010006000002402");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/afterSaleState/refuseReturn.do?format=true", map));
    }

    public static void testShippedReturn() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        map.put("returnId", "1010006000002405");
        map.put("logisticsCompany", "顺丰");
        map.put("logisticsNumber", "9348923349234729347239");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/afterSaleState/shippedReturn.do?format=true", map));
    }

    public static void testSignReturn() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        map.put("returnId", "1010006000002405");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/afterSaleState/signReturn.do?format=true", map));
    }

    public static void testPayReturn() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        map.put("returnId", "1010006000002405");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/afterSaleState/payReturn.do?format=true", map));
    }

    public static void testConfirmPayReturn() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        map.put("returnId", "1010006000002405");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/afterSaleState/confirmPayReturn.do?format=true", map));
    }

    // ///////////////////
    public static void testConfirmExchange() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        map.put("exchangeId", "1010006000001001");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/afterSaleState/confirmExchange.do?format=true", map));
    }

    public static void testRefuseExchange() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        map.put("exchangeId", "1010006000001001");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/afterSaleState/refuseExchange.do?format=true", map));
    }

    public static void testShippedExchange() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        map.put("exchangeId", "1010006000001001");
        map.put("logisticsCompany", "顺丰");
        map.put("logisticsNumber", "9348923349234729347239");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/afterSaleState/shippedExchange.do?format=true", map));
    }

    public static void testSignExchange() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        map.put("exchangeId", "1010006000001001");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/afterSaleState/signExchange.do?format=true", map));
    }

    public static void testShippedAgainExchange() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        map.put("exchangeId", "1010006000001001");
        map.put("logisticsCompany", "顺丰");
        map.put("logisticsNumber", "9348923349234729347239");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/afterSaleState/shippedAgainExchange.do?format=true", map));
    }

    public static void testSignAgainExchange() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        map.put("exchangeId", "1010006000001001");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/afterSaleState/signAgainExchange.do?format=true", map));
    }
}
