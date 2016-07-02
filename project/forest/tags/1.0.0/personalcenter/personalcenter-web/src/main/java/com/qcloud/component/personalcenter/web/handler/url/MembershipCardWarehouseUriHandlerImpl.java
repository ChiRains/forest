package com.qcloud.component.personalcenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MembershipCardWarehouseUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/membershipCardWarehouse/list.do");
        list.add("/admin/membershipCardWarehouse/toAdd.do");
        list.add("/admin/membershipCardWarehouse/toEdit.do");
        list.add("/admin/membershipCardWarehouse/add.do");
        list.add("/admin/membershipCardWarehouse/edit.do");
        list.add("/admin/membershipCardWarehouse/toInit.do");
        list.add("/admin/membershipCardWarehouse/init.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/membershipCardWarehouse/list.do");
        list.add("/admin/membershipCardWarehouse/toInit.do");
        list.add("/admin/membershipCardWarehouse/init.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/membershipCardWarehouse/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/membershipCardWarehouse/toInit.do", list);
        map.put("/admin/membershipCardWarehouse/init.do", list);
        return map;
    }
}
