package com.qcloud.component.marketing.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class FullReducesUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/fullReduces/list.do");
        list.add("/admin/fullReduces/toAdd.do");
        list.add("/admin/fullReduces/toEdit.do");
        list.add("/admin/fullReduces/add.do");
        list.add("/admin/fullReduces/edit.do");
        list.add("/admin/fullReduces/delete.do");
        list.add("/admin/fullReduces/enable.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/fullReduces/list.do");
        list.add("/admin/fullReduces/toAdd.do");
        list.add("/admin/fullReduces/toEdit.do");
        list.add("/admin/fullReduces/add.do");
        list.add("/admin/fullReduces/edit.do");
        list.add("/admin/fullReduces/delete.do");
        list.add("/admin/fullReduces/enable.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/fullReduces/list.do");
        //
        map.put("/admin/fullReduces/toAdd.do", list);
        map.put("/admin/fullReduces/toEdit.do", list);
        map.put("/admin/fullReduces/add.do", list);
        map.put("/admin/fullReduces/edit.do", list);
        map.put("/admin/brandSales/edit.do", list);
        map.put("/admin/fullReduces/delete.do", list);
        map.put("/admin/fullReduces/enable.do", list);
        return map;
    }
}
