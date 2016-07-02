package com.qcloud.component.sellercenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchantSpecClassifyUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchantSpecClassify/list.do");
        list.add("/admin/merchantSpecClassify/toAdd.do");
        list.add("/admin/merchantSpecClassify/toEdit.do");
        list.add("/admin/merchantSpecClassify/add.do");
        list.add("/admin/merchantSpecClassify/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchantSpecClassify/list.do");
        list.add("/admin/merchantSpecClassify/edit.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/merchant/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/merchantSpecClassify/list.do", list);
        map.put("/admin/merchantSpecClassify/edit.do", list);
        return map;
    }
}
