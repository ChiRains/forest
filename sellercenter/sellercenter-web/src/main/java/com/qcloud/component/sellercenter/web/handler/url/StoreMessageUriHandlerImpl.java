package com.qcloud.component.sellercenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class StoreMessageUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/storeMessage/list.do");
        list.add("/admin/storeMessage/toShow.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/storeMessage/list.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        return map;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/storeMessage/list.do");
        list.add("/app/storeMessage/get.do");
        list.add("/app/storeMessage/read.do");
        list.add("/app/storeMessage/getNewMsgNumber.do");
        list.add("/app/storeMessage/resetNewMsgNumber.do");
        //
        list.add("/app/storeMessage/countAll.do");
        list.add("/app/storeMessage/countUnread.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/storeMessage/list.do");
        list.add("/app/storeMessage/get.do");
        list.add("/app/storeMessage/read.do");
        list.add("/app/storeMessage/getNewMsgNumber.do");
        list.add("/app/storeMessage/resetNewMsgNumber.do");
        //
        list.add("/app/storeMessage/countAll.do");
        list.add("/app/storeMessage/countUnread.do");
        return list;
    }
}
