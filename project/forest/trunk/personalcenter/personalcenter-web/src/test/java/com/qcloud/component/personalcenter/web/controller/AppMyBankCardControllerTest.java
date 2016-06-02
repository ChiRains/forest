package com.qcloud.component.personalcenter.web.controller;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.AppParamEncryptUtil;
import com.qcloud.pirates.util.HttpUtils;

public class AppMyBankCardControllerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        testList();
//        testAdd();
//         testDelete();
    }

    public static void testList() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_924140BC0C3749BA81EA129F7BF59637");
        map.put("pageNum", "1");
        map.put("pageSize", "2");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myBankCard/list.do?format=true&state=1", map));
    }

    public static void testAdd() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_924140BC0C3749BA81EA129F7BF59637");
        map.put("bank", "华润银行");
        map.put("card", "6225022002001276216");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myBankCard/add.do?format=true&state=1", map));
    }

    public static void testDelete() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_924140BC0C3749BA81EA129F7BF59637");
        map.put("id", "1010006000001007");
        System.out.println(HttpUtils.doPost("http://127.0.0.1/app/myBankCard/delete.do?format=true&state=1", map));
    }
}
