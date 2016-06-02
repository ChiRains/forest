package com.qcloud.component.personalcenter.web.controller;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.AppParamEncryptUtil;
import com.qcloud.pirates.util.HttpUtils;

public class MessageControllerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

       // testSend();
        // testList();
        // testGet();
        // testRead();
        // testDelete();
        // testCountAll();
        // testCountUnread();
    }

    public static void testSend() {

        for (int i = 0; i < 10000; i++) {
            Map<String, String> map = new HashMap<String, String>();
            String str = AppParamEncryptUtil.encryptCharStr();
            String sign = AppParamEncryptUtil.signParamStr(str, "exy");
            map.put("userId", "1010006000001801");
            map.put("title", "这是一条测试的消息");
            map.put("content", "XX测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息");
            System.out.println(HttpUtils.doPost("http://127.0.0.1/admin/myMessage/send.do?format=true", map));
        }
    }

    public static void testList() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_762B4F2E37A94106B18DA635FAF93DCB");
        map.put("pageNum", "2");
        map.put("pageSize", "3");
        System.out.println(HttpUtils.doPost("http://heartsource.qi-cloud.net/app/myMessage/list.do?format=true", map));
    }

    public static void testCountAll() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_762B4F2E37A94106B18DA635FAF93DCB");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myMessage/countAll.do?format=true", map));
    }

    public static void testCountUnread() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_762B4F2E37A94106B18DA635FAF93DCB");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myMessage/countUnread.do?format=true", map));
    }

    public static void testGet() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_762B4F2E37A94106B18DA635FAF93DCB");
        map.put("id", "1010006000017977");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myMessage/get.do?format=true", map));
    }

    public static void testRead() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_762B4F2E37A94106B18DA635FAF93DCB");
        map.put("id", "1010006000017977");
        System.out.println(HttpUtils.doPost("http://heartsource.qi-cloud.net/app/myMessage/read.do?format=true", map));
    }

    public static void testDelete() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_762B4F2E37A94106B18DA635FAF93DCB");
        map.put("id", "1010006000017977");
        System.out.println(HttpUtils.doPost("http://heartsource.qi-cloud.net/app/myMessage/delete.do?format=true", map));
    }
}
