package com.qcloud.component.commoditycenter.web.test;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.pirates.util.AppParamEncryptUtil;
import com.qcloud.pirates.util.HttpUtils;

public class MerchandiseEvaluationControllerTest {

    static String uri = "http://127.0.0.1:8081";

    public static void main(String[] args) {

       // evaluate();
        list();
    }

    private static void list() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("merchandiseId", "1010006000001003");
        map.put("type", null);
        System.out.println(HttpUtils.doPost(uri + "/merchandiseEvaluation/list.do?format=true",map));        
                
    }

    public static void evaluate() {

        Map<String, String> map = new HashMap<String, String>();
        String str = AppParamEncryptUtil.encryptCharStr();
        String sign = AppParamEncryptUtil.signParamStr(str, "exy");
        map.put("qc_app_str", str);
        map.put("qc_app_sign", sign);
        map.put("qc_app_token", "2_EEC0D71FB897467D92FE11B21F94B908");
        map.put("orderId", "1010006000001611");
        map.put("orderDate", "2015-10-10");
        map.put("merchandiseEvaluations[0].orderItemId", "1010006000001203");
        map.put("merchandiseEvaluations[0].content", "ASDADASDADS阿诗丹顿");
        map.put("merchandiseEvaluations[0].star", "2");
        map.put("merchandiseEvaluations[1].orderItemId", "1010006000001204");
        map.put("merchandiseEvaluations[1].content", "ASDADASDADS阿诗丹顿达大厦的");
        map.put("merchandiseEvaluations[1].star", "4");
        System.out.println(HttpUtils.doPost("http://127.0.0.1:8081/app/orderEvaluation/evaluate.do?format=true&state=1", map));
    }
}
