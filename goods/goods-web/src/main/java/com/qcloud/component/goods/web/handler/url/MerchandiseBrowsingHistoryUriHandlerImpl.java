package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseBrowsingHistoryUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandiseBrowsingHistory/list.do");
        list.add("/admin/merchandiseBrowsingHistory/toAdd.do");
        list.add("/admin/merchandiseBrowsingHistory/toEdit.do");
        list.add("/admin/merchandiseBrowsingHistory/add.do");
        list.add("/admin/merchandiseBrowsingHistory/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandiseBrowsingHistory/list.do");
        list.add("/admin/merchandiseBrowsingHistory/toAdd.do");
        list.add("/admin/merchandiseBrowsingHistory/toEdit.do");
        list.add("/admin/merchandiseBrowsingHistory/add.do");
        list.add("/admin/merchandiseBrowsingHistory/edit.do");
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
        list.add("/merchandiseBrowsingHistory/list.do");
        list.add("/merchandiseBrowsingHistory/clearMyList.do");
        list.add("/merchandiseBrowsingHistory/remove.do");
        list.add("/app/merchandiseBrowsingHistory/list.do");
        list.add("/app/merchandiseBrowsingHistory/clearMyList.do");
        list.add("/app/merchandiseBrowsingHistory/remove.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/merchandiseBrowsingHistory/list.do");
        list.add("/app/merchandiseBrowsingHistory/clearMyList.do");
        list.add("/app/merchandiseBrowsingHistory/remove.do");
        return list;
    }
}
