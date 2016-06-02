package com.qcloud.component.personalcenter.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.AppParamEncryptUtil;
import com.qcloud.pirates.util.HttpUtils;

public class MyCollectionControllerTest {

    static String token = "2_448D9ADE36BC4DECA2074DF1C7FDDBBE";
    static {
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

//        List<Long> ids = new ArrayList();
//        ids.add(2342342L);
//        ids.add(2343442L);
//        System.out.println(Json.toJson(ids));
        // testMerchantCollect();
        // testCollect();
//         testList();
        // testMerchantList();
         testCollectionStatistics();
//        testRemoveList();
        // removeByUnifiedMerchandiseList();
    }

    public static void testCollect() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("unifiedMerchandiseId", "1010006000001205");
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myCollection/collect.do", map));
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myMerchandiseCollection/collect.do", map));
        // System.out.println(HttpUtils.doPost(UrlConfig.getUrl() + "/myCollection/collect.do", map));
    }

    public static void testRemoveList() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("longList[0]", "1010006340001205");
        map.put("longList[1]", "1010002300001205");
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        System.out.println(map);
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myCollection/collect.do", map));
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myMerchandiseCollection/removeList.do", map));
        // System.out.println(HttpUtils.doPost(UrlConfig.getUrl() + "/myCollection/collect.do", map));
    }

    // public static void removeByUnifiedMerchandiseList() {
    //
    // Map<String, String> map = new HashMap<String, String>();
    // String str = AppParamEncryptUtil.encryptCharStr();
    // String sign = AppParamEncryptUtil.signParamStr(str, "exy");
    // map.put("qc_app_str", str);
    // map.put("qc_app_sign", sign);
    // map.put("unifiedMerchandiseId", "1010006000001205");
    // map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
    // // System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myCollection/collect.do", map));
    // System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myMerchandiseCollection/collect.do", map));
    // // System.out.println(HttpUtils.doPost(UrlConfig.getUrl() + "/myCollection/collect.do", map));
    // }
    public static void testMerchantCollect() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("merchantId", "1010006000001202");
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myCollection/collect.do", map));
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myMerchantCollection/collect.do", map));
        // System.out.println(HttpUtils.doPost(UrlConfig.getUrl() + "/myCollection/collect.do", map));
    }

    public static void testRemove() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("unifiedMerchandiseId", "1010005000000601");
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myCollection/collect.do", map));
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myMerchandiseCollection/collect.do", map));
        // System.out.println(HttpUtils.doPost(UrlConfig.getUrl() + "/myCollection/collect.do", map));
    }

    public static void testList() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        map.put("classifyId", "101000600000141244");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myCollection/collect.do", map));
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myMerchandiseCollection/list.do?format=true", map));
        // System.out.println(HttpUtils.doPost(UrlConfig.getUrl() + "/myCollection/collect.do", map));
    }

    public static void testMerchantList() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_B9DACDCCB0C748B082E017759F9FBC10");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myCollection/collect.do", map));
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myMerchantCollection/list.do?format=true", map));
        // System.out.println(HttpUtils.doPost(UrlConfig.getUrl() + "/myCollection/collect.do", map));
    }

    public static void testCollectionStatistics() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_7732004B93A1480EB3D45FA843E55277");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myCollection/collect.do", map));
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myCollectionStatistics/listMerchandiseCollectClassify.do?format=true", map));
        // System.out.println(HttpUtils.doPost(UrlConfig.getUrl() + "/myCollection/collect.do", map));
    }
}
