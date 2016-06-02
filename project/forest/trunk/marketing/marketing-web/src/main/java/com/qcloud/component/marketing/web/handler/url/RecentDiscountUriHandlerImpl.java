package com.qcloud.component.marketing.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class RecentDiscountUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/recentDiscount/list.do");
        list.add("/admin/recentDiscount/toAdd.do");
        list.add("/admin/recentDiscount/toEdit.do");
        list.add("/admin/recentDiscount/add.do");
        list.add("/admin/recentDiscount/edit.do");
        list.add("/admin/recentDiscount/enable.do");
        list.add("/admin/recentDiscount/disable.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/recentDiscount/list.do");
        list.add("/admin/recentDiscount/toAdd.do");
        list.add("/admin/recentDiscount/toEdit.do");
        list.add("/admin/recentDiscount/add.do");
        list.add("/admin/recentDiscount/edit.do");
        list.add("/admin/recentDiscount/enable.do");
        list.add("/admin/recentDiscount/disable.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> strList = stringToList("/admin/recentDiscount/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/recentDiscount/toAdd.do", strList);
        map.put("/admin/recentDiscount/toEdit.do", strList);
        map.put("/admin/recentDiscount/add.do", strList);
        map.put("/admin/recentDiscount/edit.do", strList);
        map.put("/admin/recentDiscount/enable.do", strList);
        map.put("/admin/recentDiscount/disable.do", strList);
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/recentDiscount/list.do");
        return list;
    }
}
