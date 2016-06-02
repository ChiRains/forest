package com.qcloud.component.commoditycenter.web.test;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.HttpUtils;


public class MerchandiseDealRecordControllerTest {

    static String uri = "http://127.0.0.1:8081";

    public static void main(String[] args) {

        list();
    }

    private static void list() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("merchandiseId", "1010006000001804");
        System.out.println(HttpUtils.doPost(uri + "/merchandiseDealRecord/list.do?format=true",map));        
        
        
    }
}
