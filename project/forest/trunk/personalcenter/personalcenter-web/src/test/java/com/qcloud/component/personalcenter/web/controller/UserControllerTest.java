package com.qcloud.component.personalcenter.web.controller;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.HttpUtils;

public class UserControllerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // testSendMsgForRegister();
        // testRegisterByMobile();
//        testLogin();
        // testSetPswByCode();
        testSendMsgForRegister();
    }

    public static void sendMsgForActivateMembershipCard() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile", "13232282158");
        // System.out.println(HttpUtils.doPost("http://shuiqi-dev.qi-cloud.net/user/sendMsgForRegister.do", map));
        System.out.println(HttpUtils.doPost("/app/user/sendMsgForActivateMembershipCard.do", map));
    }

    public static void testSendMsgForRegister() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile", "13169184487");
        // System.out.println(HttpUtils.doPost("http://shuiqi-dev.qi-cloud.net/user/sendMsgForRegister.do", map));
        System.out.println(HttpUtils.doPost("http://10.10.11.81:8081/user/sendMsgForRegister.do", map));
    }

    public static void testRegisterByMobile() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile", "13232282158");
        map.put("code", "149949");
        map.put("pwd", "123456");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/user/registerByMobile.do?format=true", map));
        // System.out.println(HttpUtils.doPost("http://shuiqi-dev.qi-cloud.net/user/registerByMobile.do?format=true", map));
    }

    public static void testSetPswByCode() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("account", "13232282158");
        map.put("code", "099290");
        map.put("pwd", "123456");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/user/setPswByCode.do?format=true", map));
    }

    public static void testLogin() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("username", "13415781868");
        map.put("pwd", "123456");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/user/login.do?format=true", map));
    }
}
