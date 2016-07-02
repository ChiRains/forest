package com.qcloud.component.commoditycenter.web.test;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.HttpUtils;


public class MerchandiseClassifyControllerTest {

    static String uri = "http://127.0.0.1:8081";

    public static void main(String[] args) {

      // listClassify();
        listClassifyForMerchant();
    }

    private static void listClassifyForMerchant() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("merchantId", "1010006000001003");
        System.out.println(HttpUtils.doPost(uri + "/classifyMerchandise/listClassifyForMerchant.do?format=true",map));        
        
        
    }

    private static void listClassify() {

        Map<String, String> map = new HashMap<String, String>();
        System.out.println(HttpUtils.doPost(uri + "/classifyMerchandise/listClassify.do?format=true",map));        
        
    }
}
