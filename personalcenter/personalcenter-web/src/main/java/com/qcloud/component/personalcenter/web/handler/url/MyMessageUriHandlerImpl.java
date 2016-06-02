package com.qcloud.component.personalcenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MyMessageUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/myMessage/toSend.do");
        list.add("/admin/myMessage/send.do");
        list.add("/admin/myMessage/toSelect.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/myMessage/toSend.do");
        list.add("/admin/myMessage/send.do");
        list.add("/admin/myMessage/toSelect.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/user/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        //
        map.put("/admin/myMessage/toSend.do", list);
        map.put("/admin/myMessage/send.do", list);
        map.put("/admin/myMessage/toSelect.do", list);
        //
        return map;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/myMessage/list.do");
        list.add("/myMessage/get.do");
        list.add("/myMessage/read.do");
        list.add("/myMessage/delete.do");
        list.add("/myMessage/deleteList.do");
        // ##############################################
        list.add("/app/myMessage/list.do");
        list.add("/app/myMessage/get.do");
        list.add("/app/myMessage/read.do");
        list.add("/app/myMessage/delete.do");
        //
        list.add("/app/myMessage/countAll.do");
        list.add("/app/myMessage/countUnread.do");
        list.add("/app/myMessage/deleteList.do");
        list.add("/app/myMessage/getNewMsgNumber.do");
        list.add("/app/myMessage/resetNewMsgNumber.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/myMessage/list.do");
        list.add("/app/myMessage/get.do");
        list.add("/app/myMessage/read.do");
        list.add("/app/myMessage/delete.do");
        list.add("/app/myMessage/deleteList.do");
        //
        list.add("/app/myMessage/countAll.do");
        list.add("/app/myMessage/countUnread.do");
        list.add("/app/myMessage/getNewMsgNumber.do");
        list.add("/app/myMessage/resetNewMsgNumber.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
//        list.add("/admin/myMessage/send.do");
        return list;
    }
}
