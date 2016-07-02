package com.qcloud.component.personalcenter.web.controller;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.AppParamEncryptUtil;
import com.qcloud.pirates.util.HttpUtils;

public class AppMyWealthControllerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // testlistIntegralDetail();
        // testlistCommissionDetail();
        // testlistCurrencyDetail();
        integralToCurrency();
        // teststatCommission();
        // testlistWithdrawCommission();
    }

    public static void integralToCurrency() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_924140BC0C3749BA81EA129F7BF59637");
        map.put("integral", "400");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myWealth/integralToCurrency.do?format=true", map));
    }

    public static void testwithdrawCommission() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_924140BC0C3749BA81EA129F7BF59637");
        map.put("cash", "5");
        map.put("bankCardId", "1010006000001002");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myWealth/withdrawCommission.do?format=true", map));
    }

    public static void teststatCommission() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_924140BC0C3749BA81EA129F7BF59637");
        map.put("begin", "2015-10-01");
        map.put("end", "2015-12-31");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myWealth/statIntegralTrend.do?format=true", map));
    }

    public static void testlistWithdrawCommission() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_924140BC0C3749BA81EA129F7BF59637");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myWealth/listWithdrawCommission.do?format=true", map));
    }

    public static void testListTopWealth() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_924140BC0C3749BA81EA129F7BF59637");
        map.put("number", "5");
        map.put("type", "1");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myWealth/listTopWealth.do?format=true", map));
    }

    public static void testlistCommissionDetail() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_BBFB547D0E994934A65F787119253040");
        map.put("detailType", "2");
        map.put("pageSize", "5");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myWealth/listCommissionDetail.do?format=true", map));
    }

    public static void testlistIntegralDetail() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_924140BC0C3749BA81EA129F7BF59637");
        map.put("type", "1");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myWealth/listIntegralDetail.do?format=true", map));
    }

    public static void testlistCurrencyDetail() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_924140BC0C3749BA81EA129F7BF59637");
        map.put("type", "1");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myWealth/listCurrencyDetail.do?format=true", map));
    }
}
