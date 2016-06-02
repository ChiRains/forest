package com.qcloud.component.personalcenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MyWealthDetailUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        // list.add("/admin/myWealthDetail/list.do");
        // list.add("/admin/myWealthDetail/toAdd.do");
        // list.add("/admin/myWealthDetail/toEdit.do");
        // list.add("/admin/myWealthDetail/add.do");
        // list.add("/admin/myWealthDetail/edit.do");
        list.add("/admin/myWealthDetail/getWealthDetails.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/myWealthDetail/getWealthDetails.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/myWealthDetail/getWealthDetails.do", stringToList("/admin/myWealth/integralList.do", "/admin/myWealth/commissionList.do", "/admin/myWealth/couponbondList.do"));
        return map;
    }
}
