package com.qcloud.component.my.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class SearchHistoryUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        // list.add("/admin/mySearchHistory/list.do");
        // list.add("/admin/mySearchHistory/toAdd.do");
        // list.add("/admin/mySearchHistory/toEdit.do");
        // list.add("/admin/mySearchHistory/add.do");
        // list.add("/admin/mySearchHistory/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        // list.add("/admin/mySearchHistory/list.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/mySearchHistory/list.do");
        list.add("/app/mySearchHistory/list.do");
        list.add("/mySearchHistory/clear.do");
        list.add("/app/mySearchHistory/clear.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/mySearchHistory/list.do");
        list.add("/app/mySearchHistory/clear.do");
        return list;
    }
}
