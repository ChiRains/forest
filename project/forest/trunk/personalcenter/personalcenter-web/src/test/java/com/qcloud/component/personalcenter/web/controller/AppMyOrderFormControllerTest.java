package com.qcloud.component.personalcenter.web.controller;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.AppParamEncryptUtil;
import com.qcloud.pirates.util.HttpUtils;

public class AppMyOrderFormControllerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        testList();
    }

    public static void testList() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_7732004B93A1480EB3D45FA843E55277");
        map.put("pageNum", "1");
        map.put("pageSize", "2");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myOrderForm/queryComplex.do?format=true&state=1", map));
    }

    public static void testList2() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_7732004B93A1480EB3D45FA843E55277");
        map.put("pageNum", "1");
        map.put("pageSize", "2");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myOrderForm/queryComplex.do?format=true&state=1", map));
    }
}
