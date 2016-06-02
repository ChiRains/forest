package com.qcloud.component.commoditycenter.web.test;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.HttpUtils;


public class MerchandiseSpecificationsControllerTest {

    static String uri = "http://127.0.0.1:8081";
    public static void main(String[] args) {

        listByMerchandise();
    }
    private static void listByMerchandise() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("merchandiseId", "1010006000001003");
        System.out.println(HttpUtils.doPost(uri + "/merchandiseSpecifications/listByMerchandise.do?format=true",map));        
                
        
    }
}
