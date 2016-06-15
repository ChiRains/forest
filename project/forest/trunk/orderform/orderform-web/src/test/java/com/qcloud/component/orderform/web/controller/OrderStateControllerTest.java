package com.qcloud.component.orderform.web.controller;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.HttpUtils;

public class OrderStateControllerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        testPrepareOrderByToken();
    }

    public static void testPrepareOrderByToken() {

        Map<String, String> map = new HashMap<String, String>();
        // 付款
        map.put("orderDate", "2016-06-15");
        map.put("state", "40");
        map.put("orderId", "1010008000012604");
        System.out.println(HttpUtils.doPost("http://127.0.0.1:8081/orderState/testExchagneOrderState.do?format=true", map));
        // map.put("orderId", "1010006000003602");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/orderState/testExchagneOrderState.do?format=true", map));
        // map.put("orderId", "1010006000003603");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/orderState/testExchagneOrderState.do?format=true", map));
        // map.put("orderId", "1010006000003604");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/orderState/testExchagneOrderState.do?format=true", map));
        // map.put("orderId", "1010006000003605");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/orderState/testExchagneOrderState.do?format=true", map));
        // map.put("orderId", "1010006000003606");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/orderState/testExchagneOrderState.do?format=true", map));
        // map.put("orderId", "1010006000003607");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/orderState/testExchagneOrderState.do?format=true", map));
        // map.put("orderId", "1010006000003608");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/orderState/testExchagneOrderState.do?format=true", map));
        // map.put("orderId", "1010006000003609");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/orderState/testExchagneOrderState.do?format=true", map));
        // map.put("orderId", "1010006000003610");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/orderState/testExchagneOrderState.do?format=true", map));
        // map.put("orderId", "1010006000003611");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/orderState/testExchagneOrderState.do?format=true", map));
        // map.put("orderId", "1010006000003612");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/orderState/testExchagneOrderState.do?format=true", map));
        // map.put("orderId", "1010006000003613");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/orderState/testExchagneOrderState.do?format=true", map));
        // map.put("orderId", "1010006000003614");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/orderState/testExchagneOrderState.do?format=true", map));
        // map.put("orderId", "1010006000003615");
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/orderState/testExchagneOrderState.do?format=true", map));
    }
}
