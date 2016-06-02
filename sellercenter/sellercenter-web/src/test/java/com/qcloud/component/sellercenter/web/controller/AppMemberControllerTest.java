package com.qcloud.component.sellercenter.web.controller;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.component.test.HttpTest;

public class AppMemberControllerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        login();
    }

    public static void login() {

        Map<String, String> map = new HashMap<String, String>();
//        map.put("username", "18925303318");
        map.put("username", "182076094473"); 
        map.put("pwd", "654321");
        System.out.println(HttpTest.testAppWhiteUrl("/app/member/login.do?format=true", map));
    }
}
