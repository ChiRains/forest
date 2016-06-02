package com.qcloud.component.sellercenter.web.controller;


import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.AppParamEncryptUtil;
import com.qcloud.pirates.util.HttpUtils;

public class AppMerchantControllerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

//         testSendMsgForRegister();
//         testRegisterByMobile();
       // testLogin();
//        testGetMerchantInfo();
        list();
//        listHighMerchant();
    }

    public static void list(){
        Map<String, String> map=new HashMap<String, String>();
        map.put("pageNum", "1");
        map.put("pageSize", "10");
        //map.put("qc_app_token", "2_C2894BBB54E84B6CBF01CCE8C60D164C");
        
        map.put("qc_app_str", AppParamEncryptUtil.encryptCharStr());
        map.put("qc_app_sign",AppParamEncryptUtil.signParamStr(map.get("qc_app_str"), "exy") );
        System.out.println(HttpUtils.doPost("http://127.0.0.1/merchant/list.do?format=true", map));
    }
    
    
    
    //获取优质商家列表
    public static void listHighMerchant(){
        Map<String, String> map=new HashMap<String, String>();
        map.put("pageNum", "1");
        map.put("pageSize", "10");
        //map.put("qc_app_token", "2_C2894BBB54E84B6CBF01CCE8C60D164C");
        
        map.put("qc_app_str", AppParamEncryptUtil.encryptCharStr());
        map.put("qc_app_sign",AppParamEncryptUtil.signParamStr(map.get("qc_app_str"), "exy") );
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/merchant/listHighMerchant.do?format=true", map));
    }
    public static void testLogin() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("username", "13232282158");
        map.put("pwd", "123456");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/user/login.do?format=true", map));
//        System.out.println(HttpUtils.doPost("http://heartsource.qi-cloud.net/app/user/login.do?format=true", map));
//        System.out.println(HttpUtils.doPost("http://10.10.11.143/app/user/login.do?format=true", map));
    }
    
    
    public static void testRecentlyList() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("latitude", "23.325145");
        map.put("longitude", "114.369007");
        map.put("distance", "5");
        map.put("pageNum", "1");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/merchant/listRecently.do?format=true", map));
//        System.out.println(HttpUtils.doPost("http://heartsource.qi-cloud.net/app/user/login.do?format=true", map));
//        System.out.println(HttpUtils.doPost("http://10.10.11.143/app/user/login.do?format=true", map));
    }
    
    
    public static void testGetMerchantInfo(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("merchantId", "1010006000001001");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/merchant/getMerchantInfo.do?format=true", map));
    }
}
