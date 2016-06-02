package com.qcloud.component.sellercenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchantClassifyUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/classifyMerchant/list.do");
        list.add("/admin/classifyMerchant/deleteMerchantClassify.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/classifyMerchant/list.do");
        list.add("/admin/classifyMerchant/deleteMerchantClassify.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/classifyMerchant/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/classifyMerchant/deleteMerchantClassify.do", list);
        //
        map.put("/admin/classify4Type/toAdd.do", list);
        map.put("/admin/classify4Type/add.do", list);
        map.put("/admin/classify4Type/toEdit.do", list);
        map.put("/admin/classify4Type/edit.do", list);
        map.put("/admin/classify4Type/top.do", list);
        map.put("/admin/classify4Type/down.do", list);
        map.put("/admin/classify4Type/upward.do", list);
        map.put("/admin/classify4Type/enable.do", list);
        map.put("/admin/classify4Type/disable.do", list);
        //
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/classifyMerchant/listClassify.do");
        return list;
    }
}
