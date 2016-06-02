package com.qcloud.component.personalcenter.web.controller;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.AppParamEncryptUtil;
import com.qcloud.pirates.util.HttpUtils;

public class AppMyShoppingCartControllerTest {

    public static void main(String[] args) {

        list();
        // add();
    }

    public static void list() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_EEC0D71FB897467D92FE11B21F94B908");
        
        
        
        System.out.println(HttpUtils.doPost("http://127.0.0.1:8081/app/myShoppingCart/list4Merchant.do?format=true", map));
        System.out.println(HttpUtils.doPost("http://127.0.0.1:8081/app/myShoppingCart/list4Classify.do?format=true", map));
        System.out.println(HttpUtils.doPost("http://127.0.0.1:8081/app/myShoppingCart/list.do?format=true", map));
    }

    public static void add() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_AA067995A5CE4AC98E083891A691338F");
        map.put("unifiedMerchandiseId", "1010006000002204");
        map.put("number", "2");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myShoppingCart/add.do?format=true", map));
    }
}
