package com.qcloud.component.orderform.web.controller;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.AppParamEncryptUtil;
import com.qcloud.pirates.util.HttpUtils;

public class OrderControllerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // testPrepareOrder();
        // testShuiqiPrepareOrder();
        // testPrepareOrderByToken();
        // for (int i = 0; i < 200; i++) {
        // testOrderByToken();
        // }
         testGet();
//        for (int i = 0; i < 25; i++) {
//            testOrder();
//        }
    }

    public static void testPrepareOrder() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_7732004B93A1480EB3D45FA843E55277");
        map.put("merchandiseList[0].unifiedMerchandiseId", "1010006000001002");
        map.put("merchandiseList[0].number", "20");
        map.put("merchandiseList[1].unifiedMerchandiseId", "1010006000001202");
        map.put("merchandiseList[1].number", "2");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/orderForm/prepareOrder4Merchant.do?format=true", map));
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/orderForm/prepareOrder4Classify.do?format=true", map));
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/orderForm/prepareOrder.do?format=true", map));
    }

    public static void testOrder() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_CADC90E8EDC74106B35566FD02EA35A0");
        map.put("consigneeId", "1010006000001201");
        map.put("invoiceId", "-1");
        map.put("deliveryId", "-1");
        map.put("merchandiseList[0].unifiedMerchandiseId", "1010006000001002");
        map.put("merchandiseList[0].number", "20");
        map.put("merchandiseList[1].unifiedMerchandiseId", "1010006000001202");
        map.put("merchandiseList[1].number", "2");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/orderForm/order.do?format=true", map));
    }

    public static void testGet() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_CADC90E8EDC74106B35566FD02EA35A0");
        map.put("orderId", "1010006000005601");
        map.put("orderDate", "2015-09-10 23:12:13");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/orderForm/get.do?format=true", map));
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/orderForm/get4Classify.do?format=true", map));
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/orderForm/get4Merchant.do?format=true", map));
    }
}
