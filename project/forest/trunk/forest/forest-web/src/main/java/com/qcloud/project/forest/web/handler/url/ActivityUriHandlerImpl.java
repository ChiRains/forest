package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ActivityUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/activity/list.do");
        list.add("/admin/activity/toAdd.do");
        list.add("/admin/activity/toEdit.do");
        list.add("/admin/activity/add.do");
        list.add("/admin/activity/edit.do");
        list.add("/admin/activity/delete.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/activity/list.do");
        list.add("/admin/activity/toAdd.do");
        list.add("/admin/activity/toEdit.do");
        list.add("/admin/activity/add.do");
        list.add("/admin/activity/edit.do");
        list.add("/admin/activity/delete.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/activity/list.do");
        //
        map.put("/admin/activity/toAdd.do", list);
        map.put("/admin/activity/toEdit.do", list);
        map.put("/admin/activity/toEdit.do", list);
        map.put("/admin/activity/add.do", list);
        map.put("/admin/activity/edit.do", list);
        map.put("/admin/activity/delete.do", list);
        return map;
    }
}
