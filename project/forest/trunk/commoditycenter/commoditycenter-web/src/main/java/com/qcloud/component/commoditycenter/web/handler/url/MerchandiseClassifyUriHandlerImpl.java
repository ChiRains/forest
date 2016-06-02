package com.qcloud.component.commoditycenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseClassifyUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/classifyMerchandise/listForMerchant.do");
        list.add("/admin/classifyMerchandise/deleteMallClassify.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/classifyMerchandise/listForMerchant.do");
        list.add("/admin/classifyMerchandise/deleteMallClassify.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/classifyMerchandise/listForMerchant.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/classifyMerchandise/deleteMallClassify.do", list);
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
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/classifyMerchandise/listClassify.do");
        list.add("/classifyMerchandise/listClassifyForMerchant.do");
        return list;
    }
}
