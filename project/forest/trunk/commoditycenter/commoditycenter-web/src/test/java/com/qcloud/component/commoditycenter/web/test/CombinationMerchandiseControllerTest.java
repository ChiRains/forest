package com.qcloud.component.commoditycenter.web.test;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.HttpUtils;

public class CombinationMerchandiseControllerTest {

    static String uri = "http://127.0.0.1:8081";

    public static void main(String[] args) {
        existByUnifiedMerchandise();
        getByUnifiedMerchandise();
        listByUnifiedMerchandise();
        getBySingleUnifiedMerchandise();
    }
    
    
    private static void getBySingleUnifiedMerchandise() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("unifiedMerchandiseId", "1010006000003801");
        System.out.println(HttpUtils.doPost(uri + "/combinationMerchandise/getBySingleUnifiedMerchandise.do?format=true",map));        
    }


    public static void existByUnifiedMerchandise(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("unifiedMerchandiseId", "1010006000003801");
        System.out.println(HttpUtils.doPost(uri + "/combinationMerchandise/existByUnifiedMerchandise.do?format=true",map));
    }
    public static void getByUnifiedMerchandise(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("unifiedMerchandiseId", "1010006000003801");
        System.out.println(HttpUtils.doPost(uri + "/combinationMerchandise/getByUnifiedMerchandise.do?format=true", map));
    }

    public static void listByUnifiedMerchandise() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("unifiedMerchandiseId", "1010006000003801");
        System.out.println(HttpUtils.doPost(uri + "/combinationMerchandise/listByUnifiedMerchandise.do?format=true", map));
    }

     
}
