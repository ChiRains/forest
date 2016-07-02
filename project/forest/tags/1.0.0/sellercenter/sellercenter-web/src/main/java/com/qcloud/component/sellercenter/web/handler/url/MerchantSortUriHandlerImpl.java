package com.qcloud.component.sellercenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchantSortUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchantSort/list.do");
        list.add("/admin/merchantSort/toAdd.do");
        list.add("/admin/merchantSort/toEdit.do");
        list.add("/admin/merchantSort/add.do");
        list.add("/admin/merchantSort/edit.do");
        list.add("/admin/merchantSort/delete.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchantSort/list.do");
        list.add("/admin/merchantSort/toAdd.do");
        list.add("/admin/merchantSort/toEdit.do");
        list.add("/admin/merchantSort/add.do");
        list.add("/admin/merchantSort/edit.do");
        list.add("/admin/merchantSort/delete.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/merchantSort/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/merchantSort/toAdd.do", list);
        map.put("/admin/merchantSort/toEdit.do", list);
        map.put("/admin/merchantSort/add.do", list);
        map.put("/admin/merchantSort/edit.do", list);
        map.put("/admin/merchantSort/delete.do", list);
        //
        map.put("/admin/merchant/allMerchantlist.do", list);
        return map;
    }
}
