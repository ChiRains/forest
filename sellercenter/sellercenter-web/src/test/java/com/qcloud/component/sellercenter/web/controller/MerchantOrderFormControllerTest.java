package com.qcloud.component.sellercenter.web.controller;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.component.test.HttpTest;

public class MerchantOrderFormControllerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        list();
    }

    public static void list() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("stateType", "4");
        System.out.println(HttpTest.testAppLoginUrl("/app/merchantOrderForm/list.do?format=true", map));
    }
}
