package com.qcloud.component.sellercenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class StoreUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/store/list.do");
        list.add("/admin/store/toAdd.do");
        list.add("/admin/store/toEdit.do");
        list.add("/admin/store/add.do");
        list.add("/admin/store/edit.do");
        list.add("/admin/store/enable.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/store/list.do");
        list.add("/admin/store/toAdd.do");
        list.add("/admin/store/toEdit.do");
        list.add("/admin/store/add.do");
        list.add("/admin/store/edit.do");
        list.add("/admin/store/enable.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/store/list.do");
        //
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/store/toAdd.do", list);
        map.put("/admin/store/toEdit.do", list);
        map.put("/admin/store/add.do", list);
        map.put("/admin/store/edit.do", list);
        map.put("/admin/store/enable.do", list);
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/store/listByAddress.do");
        return list;
    }
}
