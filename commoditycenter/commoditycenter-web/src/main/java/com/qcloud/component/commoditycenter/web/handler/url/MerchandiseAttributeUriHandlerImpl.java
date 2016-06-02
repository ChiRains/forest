package com.qcloud.component.commoditycenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseAttributeUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandiseAttribute/list.do");
        list.add("/admin/merchandiseAttribute/toAdd.do");
        list.add("/admin/merchandiseAttribute/toEdit.do");
        list.add("/admin/merchandiseAttribute/add.do");
        list.add("/admin/merchandiseAttribute/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandiseAttribute/list.do");
        list.add("/admin/merchandiseAttribute/toAdd.do");
        list.add("/admin/merchandiseAttribute/toEdit.do");
        list.add("/admin/merchandiseAttribute/add.do");
        list.add("/admin/merchandiseAttribute/edit.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/merchandiseAttribute/listAttrByMerchandise.do");
        list.add("/merchandiseAttribute/listAttrByUnifiedMerchandise.do");
        return list;
    }
}
