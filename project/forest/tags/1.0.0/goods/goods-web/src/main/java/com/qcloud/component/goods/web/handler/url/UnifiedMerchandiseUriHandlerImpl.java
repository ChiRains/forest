package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class UnifiedMerchandiseUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/unifiedMerchandise/list.do");
        list.add("/admin/unifiedMerchandise/toAdd.do");
        list.add("/admin/unifiedMerchandise/toEdit.do");
        list.add("/admin/unifiedMerchandise/add.do");
        list.add("/admin/unifiedMerchandise/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/unifiedMerchandise/list.do");
        list.add("/admin/unifiedMerchandise/toAdd.do");
        list.add("/admin/unifiedMerchandise/toEdit.do");
        list.add("/admin/unifiedMerchandise/add.do");
        list.add("/admin/unifiedMerchandise/edit.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        return map;
    }
}
