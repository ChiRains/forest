package com.qcloud.component.personalcenter.web.controller;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.component.test.HttpTest;

public class AppMyCouponControllerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // testList();
        getByCode();
    }

    public static void testList() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("type", "1");
        System.out.println(HttpTest.testAppLoginUrl("/app/myCoupon/list.do", map));
    }

    public static void getByCode() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("cardNumber", "0276000006");
        map.put("code", "EXY20160102813985");
        System.out.println(HttpTest.testAppLoginUrl("/app/myCoupon/getByCode.do", map));
    }
}
