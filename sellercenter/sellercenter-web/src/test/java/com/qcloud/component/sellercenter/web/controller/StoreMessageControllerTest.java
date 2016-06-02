package com.qcloud.component.sellercenter.web.controller;

import java.util.HashMap;
import java.util.Map;
import com.qcloud.component.test.HttpTest;

public class StoreMessageControllerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

         list();
        // countAll();
        // countUnread();
        // get();
//         read();
        getNewMsgNumber();
//        resetNewMsgNumber();
    }

    public static void list() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("pageNum", "2");
        map.put("pageSize", "5");
        System.out.println(HttpTest.testAppLoginUrl("/app/storeMessage/list.do?format=true", map));
    }

    public static void countAll() {

        Map<String, String> map = new HashMap<String, String>();
        System.out.println(HttpTest.testAppLoginUrl("/app/storeMessage/countAll.do?format=true", map));
    }

    public static void countUnread() {

        Map<String, String> map = new HashMap<String, String>();
        System.out.println(HttpTest.testAppLoginUrl("/app/storeMessage/countUnread.do?format=true", map));
    }

    public static void get() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1010006000004004");
        System.out.println(HttpTest.testAppLoginUrl("/app/storeMessage/get.do?format=true", map));
    }

    public static void read() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1010006000004220");
        System.out.println(HttpTest.testAppLoginUrl("/app/storeMessage/read.do?format=true", map));
    }

    public static void getNewMsgNumber() {

        Map<String, String> map = new HashMap<String, String>();
        System.out.println(HttpTest.testAppLoginUrl("/app/storeMessage/getNewMsgNumber.do?format=true", map));
    }

    public static void resetNewMsgNumber() {

        Map<String, String> map = new HashMap<String, String>();
        System.out.println(HttpTest.testAppLoginUrl("/app/storeMessage/resetNewMsgNumber.do?format=true", map));
    }
}
