package com.qcloud.component.personalcenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MySignInDayUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/mySignInDay/list.do");
        list.add("/admin/mySignInDay/toAdd.do");
        list.add("/admin/mySignInDay/toEdit.do");
        list.add("/admin/mySignInDay/add.do");
        list.add("/admin/mySignInDay/edit.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        return map;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/mySignInDay/list.do");
        return list;
    }
}
