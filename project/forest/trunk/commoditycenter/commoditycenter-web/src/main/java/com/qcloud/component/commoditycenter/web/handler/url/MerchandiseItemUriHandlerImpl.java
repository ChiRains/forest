package com.qcloud.component.commoditycenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseItemUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandiseItem/list.do");
        list.add("/admin/merchandiseItem/toAdd.do");
        list.add("/admin/merchandiseItem/toEdit.do");
        list.add("/admin/merchandiseItem/add.do");
        list.add("/admin/merchandiseItem/edit.do");
        list.add("/admin/merchandiseItem/selectProductList.do");
        list.add("/admin/merchandiseItem/produce4CustomMall.do");
        list.add("/admin/merchandiseItem/list4Select4Admin.do");
        list.add("/admin/merchandiseItem/listForAdmin.do");
        list.add("/admin/merchandiseItem/listForForklift.do");
        list.add("/admin/merchandiseItem/listForMerchant.do");
        //
        list.add("/admin/merchandiseItem/listForB2BEditOrder.do");
        list.add("/admin/merchandiseItem/toImport.do");
        list.add("/admin/merchandiseItem/merchandisePriceImport.do");
        list.add("/admin/merchandiseItem/merchandisePriceExport.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandiseItem/list.do");
        list.add("/admin/merchandiseItem/toAdd.do");
        list.add("/admin/merchandiseItem/toEdit.do");
        list.add("/admin/merchandiseItem/add.do");
        list.add("/admin/merchandiseItem/edit.do");
        // list.add("/admin/merchandiseItem/selectProductList.do");
        // list.add("/admin/merchandiseItem/produce4CustomMall.do");
        // list.add("/admin/merchandiseItem/list4Select4Admin.do");
        // list.add("/admin/merchandiseItem/listForAdmin.do");
        list.add("/admin/merchandiseItem/listForForklift.do");
        list.add("/admin/merchandiseItem/toImport.do");
        list.add("/admin/merchandiseItem/merchandisePriceImport.do");
        list.add("/admin/merchandiseItem/merchandisePriceExport.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/merchandiseItem/list.do");
        //
        map.put("/admin/merchandiseItem/toAdd.do", list);
        map.put("/admin/merchandiseItem/toEdit.do", list);
        map.put("/admin/merchandiseItem/add.do", list);
        map.put("/admin/merchandiseItem/edit.do", list);
        map.put("/admin/merchandiseItem/toImport.do", list);
        map.put("/admin/merchandiseItem/merchandisePriceImport.do", list);
        map.put("/admin/merchandiseItem/merchandisePriceExport.do", list);
        return map;
    }
}
