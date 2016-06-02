package com.qcloud.component.commoditycenter.web.test;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.HttpUtils;

public class MerchandiseControllerTest {

    static String uri = "http://127.0.0.1:8081";

    public static void main(String[] args) {
        
     //  query();
        queryBySpecifications();
       // getByMerchandise();
     //   getBySpecifications();
        getPriceAndStockBySpecifications();
        getByUnifiedMerchandise();
        getDetailByUnifiedMerchandise();
        getDetailByMerchandise();
        getHtmlDetailByUnifiedMerchandise();
        getHtmlDetailByMerchandise();
    }
    
    




    private static void getHtmlDetailByMerchandise() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("merchandiseId", "1010006000001804");
        System.out.println(HttpUtils.doPost(uri + "/merchandise/getHtmlDetailByMerchandise.do?format=true", map));         
    }



    private static void getHtmlDetailByUnifiedMerchandise() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("unifiedMerchandiseId", "1010006000003801");
        System.out.println(HttpUtils.doPost(uri + "/merchandise/getHtmlDetailByUnifiedMerchandise.do?format=true", map));          
    }



    private static void getDetailByMerchandise() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("merchandiseId", "1010006000001804");
        System.out.println(HttpUtils.doPost(uri + "/merchandise/getDetailByMerchandise.do?format=true", map));          
    }



    private static void getDetailByUnifiedMerchandise() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("unifiedMerchandiseId", "1010006000003801");
        System.out.println(HttpUtils.doPost(uri + "/merchandise/getDetailByUnifiedMerchandise.do?format=true", map));             
    }



    private static void getByUnifiedMerchandise() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("unifiedMerchandiseId", "1010006000003801");

        System.out.println(HttpUtils.doPost(uri + "/merchandise/getByUnifiedMerchandise.do?format=true", map));           
    }



    private static void getPriceAndStockBySpecifications() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("merchandiseId", "1010006000001804");
        map.put("specificationsList[0].attributeId", "1010006000002006");
        map.put("specificationsList[0].value", "1.5米");

        System.out.println(HttpUtils.doPost(uri + "/merchandise/getPriceAndStockBySpecifications.do?format=true", map));           
    }



    private static void getBySpecifications() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("merchandiseId", "1010006000001804");
        map.put("specificationsList[0].attributeId", "1010006000002006");
        map.put("specificationsList[0].value", "1.5米");

        System.out.println(HttpUtils.doPost(uri + "/merchandise/getBySpecifications.do?format=true", map));         
    }



    private static void getByMerchandise() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("merchandiseId", "1010006000001804");
        System.out.println(HttpUtils.doPost(uri + "/merchandise/getByMerchandise.do?format=true", map));        
    }

    private static void queryBySpecifications() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("merchantId", "1010006000001003");
        map.put("queryType", "1");
        System.out.println(HttpUtils.doPost(uri + "/merchandise/queryBySpecifications.do?format=true", map));
        
    }


    public static void query() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("merchantId", "1010006000001003");
        map.put("queryType", "1");
        System.out.println(HttpUtils.doPost(uri + "/merchandise/query.do?format=true", map));
    }

     
}
