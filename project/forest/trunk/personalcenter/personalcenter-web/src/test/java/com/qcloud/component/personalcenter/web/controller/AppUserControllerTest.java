package com.qcloud.component.personalcenter.web.controller;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.AppParamEncryptUtil;
import com.qcloud.pirates.util.HttpUtils;

public class AppUserControllerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // testSendMsgForRegister();
        // testRegisterByMobile();
        testLogin();
//         testGet();
//        editHeadImage();
    }

    public static void testGet() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_6E100EA4FB3A497BB6A9FC2FB51E86A5");
        System.out.println(HttpUtils.doPost("http://127.0.0.1:8081/app/user/getUser.do?format=true", map));
//        System.out.println(HttpUtils.doPost("http://exy-test.qi-cloud.net/app/user/getUser.do?format=true", map));
    }

    public static void testLogin() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "jjwl");
        map.put("username", "13535964908");
        map.put("pwd", "123456");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        System.out.println(HttpUtils.doPost("http://127.0.0.1:8081/app/user/login.do?format=true", map));
        // System.out.println(HttpUtils.doPost("http://heartsource.qi-cloud.net/app/user/login.do?format=true", map));
        // System.out.println(HttpUtils.doPost("http://10.10.11.143/app/user/login.do?format=true", map));
    }

    public static void testSendMsgForRegister() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile", "13232282158");
        map.put("qc_app_str", "GCIRXEEWCKPU");
        map.put("qc_app_sign", "0d44d7cb4521a0404829c75bac381e9d");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/user/sendMsgForRegister.do", map));
    }

    public static void testRegisterByMobile() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile", "13232282158");
        map.put("code", "154279");
        map.put("pwd", "123456");
        map.put("qc_app_str", "GCIRXEEWCKPU");
        map.put("qc_app_sign", "0d44d7cb4521a0404829c75bac381e9d");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/user/registerByMobile.do?format=true", map));
    }

    public static void editHeadImage() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_6E100EA4FB3A497BB6A9FC2FB51E86A5");
        map.put("headImage", "49237A13D7824F958587A7D6D376F517");
        System.out.println(HttpUtils.doPost("http://127.0.0.1:8081/app/user/editHeadImage.do?format=true", map));
    }
}
