package com.qcloud.component.sellercenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchantWealthDetailUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchantWealthDetail/list.do");
        list.add("/admin/merchantWealthDetail/toAdd.do");
        list.add("/admin/merchantWealthDetail/toEdit.do");
        list.add("/admin/merchantWealthDetail/add.do");
        list.add("/admin/merchantWealthDetail/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchantWealthDetail/list.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        return map;
    }
}
