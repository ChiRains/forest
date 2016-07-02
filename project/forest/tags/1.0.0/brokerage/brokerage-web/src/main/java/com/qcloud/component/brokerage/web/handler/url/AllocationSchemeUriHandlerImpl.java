package com.qcloud.component.brokerage.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class AllocationSchemeUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/allocationScheme/list.do");
        list.add("/admin/allocationScheme/toAdd.do");
        list.add("/admin/allocationScheme/toEdit.do");
        list.add("/admin/allocationScheme/add.do");
        list.add("/admin/allocationScheme/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/allocationScheme/list.do");
        list.add("/admin/allocationScheme/toAdd.do");
        list.add("/admin/allocationScheme/toEdit.do");
        list.add("/admin/allocationScheme/add.do");
        list.add("/admin/allocationScheme/edit.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/calculationFormula/list.do");
        //
        map.put("/admin/allocationScheme/toAdd.do", list);
        map.put("/admin/allocationScheme/toEdit.do", list);
        map.put("/admin/allocationScheme/add.do", list);
        map.put("/admin/allocationScheme/edit.do", list);
        map.put("/admin/allocationScheme/list.do", list);
        return map;
    }
}
