package com.qcloud.project.forest.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.project.forest.model.oms.XmlResult;

public class OutsideUtil {

    public static Map<String, String> transUrlToMap(Map<String, Object> param) {

        Map<String, String> urlMap = new LinkedHashMap<String, String>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list.add(param);
        urlMap.put("v", "1.0");
        urlMap.put("sign", "");
        urlMap.put("message", Json.toJson(list));
        return urlMap;
    }

    /**
     * 适用raw参数
     * @param url
     * @param param
     * @return
     */
    public static String doPost(String url, Map<String, String> param) {

        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("参数url不能为空.");
        }
        StringBuilder sb = new StringBuilder();
        if (param != null) {
            Iterator<Entry<String, String>> iterator = param.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                if (sb.length() > 0) {
                    sb.append('&');
                }
                if (value == null) {
                    continue;
                }
                sb.append(StringUtil.urlEncode(key)).append('=').append(value);
            }
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        String content = "";
        CloseableHttpResponse response = null;
        try {
            System.out.println(sb.toString());
            StringEntity postingString = new StringEntity(sb.toString());
            post.setEntity(postingString);
            response = httpclient.execute(post);
            content = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    public static String getReturnXml(Object obj) {

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<response>");
        recursion(obj, sb);
        sb.append("</response>");
        return sb.toString();
    }

    private static String recursion(Object object, StringBuilder sb) {

        Map<String, Object> beanMap = BeanUtils.transBean2Map(object);
        for (Entry<String, Object> entry : beanMap.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getValue().getClass().getCanonicalName().startsWith("com.qcloud")) {
                    recursion(entry.getValue(), sb);
                } else if (entry.getValue() instanceof java.util.List && ((List<?>) entry.getValue()).get(0).getClass().getCanonicalName().startsWith("com.qcloud")) {
                    sb.append("<").append(entry.getKey()).append(">");
                    for (Object obj : ((List<?>) entry.getValue())) {
                        sb.append("<").append(obj.getClass().getSimpleName().toLowerCase()).append(">");
                        recursion(obj, sb);
                        sb.append("</").append(obj.getClass().getSimpleName().toLowerCase()).append(">");
                    }
                    sb.append("</").append(entry.getKey()).append(">");
                } else {
                    sb.append("<").append(entry.getKey()).append(">");
                    sb.append(entry.getValue());
                    sb.append("</").append(entry.getKey()).append(">");
                }
            }
        }
        return sb.toString();
    }
}
