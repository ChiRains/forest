package com.qcloud.component.sellercenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchantMerchandiseClassifyUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchantMerchandiseClassify/list.do");
        list.add("/admin/merchantMerchandiseClassify/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchantMerchandiseClassify/list.do");
        list.add("/admin/merchantMerchandiseClassify/edit.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/merchant/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/merchantMerchandiseClassify/list.do", list);
        map.put("/admin/merchantMerchandiseClassify/edit.do", list);
        return map;
    }
}
