package com.qcloud.project.forest.form;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import com.qcloud.pirates.util.HttpUtils;

public class MedicationControllerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // testSave();
        testEdit();
    }

    public static void testSave() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("theme", "每月的用药提醒");
        map.put("medicine", "好吃点");
        map.put("objectName", "爷爷");
        map.put("takeTimes[0]", "15:25");
        map.put("takeTimes[1]", "15:30");
        map.put("takeTimes[2]", "15:35");
        map.put("takeTimes[3]", "15:50");
        map.put("dosage", "1");
        map.put("unit", "片");
        map.put("periodType", "3");
        map.put("desc", "等一下叫他起床");
        map.put("endTime", "2015-10-20");
        System.out.println(mapToUrl("http://127.0.0.1/medication/add.do", map));
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/medication/add.do?format=true", map));
    }

    public static void testEdit() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1010008000001203");
        map.put("theme", "每月的用药提醒");
        map.put("medicine", "好吃点");
        map.put("objectName", "奶奶");
        map.put("takeTimes[0]", "15:25");
        map.put("takeTimes[1]", "15:30");
        map.put("takeTimes[2]", "15:35");
        map.put("takeTimes[3]", "15:50");
        map.put("dosage", "1");
        map.put("unit", "片");
        map.put("periodType", "1");
        map.put("desc", "等一下叫他起床22");
        map.put("endTime", "2015-10-20");
        System.out.println(mapToUrl("http://127.0.0.1/medication/edit.do", map));
        // System.out.println(HttpUtils.doPost("http://127.0.0.1/medication/edit.do?format=true", map));
    }

    private static String mapToUrl(String apiUrl, Map<String, String> param) {

        StringBuilder sb = new StringBuilder();
        Iterator<Entry<String, String>> it = param.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            String paramStr = entry.getKey() + "=" + entry.getValue();
            sb.append(paramStr).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        String url = apiUrl + "?" + sb.toString();
        return url;
    }
}
