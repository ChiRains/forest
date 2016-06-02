package com.qcloud.component.personalcenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MySignInStatisticsUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/mySignInStatistics/list.do");
        list.add("/admin/mySignInStatistics/toAdd.do");
        list.add("/admin/mySignInStatistics/toEdit.do");
        list.add("/admin/mySignInStatistics/add.do");
        list.add("/admin/mySignInStatistics/edit.do");
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
        list.add("/admin/mySignInStatistics/list.do");
        return list;
    }
}
