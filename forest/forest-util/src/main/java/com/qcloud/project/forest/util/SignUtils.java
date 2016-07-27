package com.qcloud.project.forest.util;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SignUtils {

    public static boolean checkSign(String secret, String sign, Map<String, Object> params) {

        String checkSign = sign(secret, params);
        return checkSign.equals(sign);
    }

    public static String sign(String secret, Map<String, Object> param) {

        List<String> paramList = new ArrayList<String>();
        for (Entry<String, Object> entry : param.entrySet()) {
            paramList.add(entry.getKey() + entry.getValue());
        }
        Collections.sort(paramList);
        StringBuilder sb = new StringBuilder();
        sb.append(secret);
        for (String paramStr : paramList) {
            sb.append(paramStr);
        }
        sb.append(secret);
        System.out.println(sb.toString());
        return md5(sb.toString());
    }

    public static String md5(String str) {

        return encode(str, "MD5").toUpperCase();
    }

    private static String encode(String str, String type) {

        try {
            MessageDigest alga = MessageDigest.getInstance(type);
            alga.update(str.getBytes());
            byte digesta[] = alga.digest();
            String hex = byte2hex(digesta);
            return hex;
        } catch (Exception e) {
        }
        return "";
    }

    private static String byte2hex(byte b[]) {

        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < b.length; n++) {
            String stmp = Integer.toHexString(b[n] & 255);
            if (stmp.length() == 1) sb.append("0");
            sb.append(stmp);
        }
        return sb.toString().toUpperCase();
    }

    public static void main(String[] a) {

        Map<String, Object> param = new HashMap<String, Object>();
        String secret = "ZHNsMDAx";
        param.put("method", "item.list.get");
        param.put("timestamp", "2016-07-26 18:02:15");
        param.put("format", "xml");
        param.put("app_id", "dsl");
        //
        param.put("starttime", "2016-07-26 18:02:15");
        param.put("endtime", "2016-07-26 18:02:15");
        param.put("page", "1");
        param.put("pagesize", "20");
        System.out.println(SignUtils.sign(secret, param));
    }
}
