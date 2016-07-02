package com.qcloud.component.marketing.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class PointExchangeUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/pointExchange/exchangeList.do");
        list.add("/admin/pointExchange/toAdd.do");
        list.add("/admin/pointExchange/selectProductList.do");
        list.add("/admin/pointExchange/addExchangeMerchandise.do");
        list.add("/admin/pointExchange/delete.do");
        list.add("/admin/pointExchange/multipleSort.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/pointExchange/exchangeList.do");
        list.add("/admin/pointExchange/toAdd.do");
        list.add("/admin/pointExchange/selectProductList.do");
        list.add("/admin/pointExchange/addExchangeMerchandise.do");
        list.add("/admin/pointExchange/delete.do");
        list.add("/admin/pointExchange/multipleSort.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/pointExchange/exchangeList.do");
        //
        map.put("/admin/pointExchange/toAdd.do", list);
        map.put("/admin/pointExchange/selectProductList.do", list);
        map.put("/admin/pointExchange/addExchangeMerchandise.do", list);
        map.put("/admin/pointExchange/delete.do", list);
        map.put("/admin/pointExchange/multipleSort.do", list);
        map.put("/admin/merchandiseItem/listForAdmin.do", list);
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        return list;
    }
}
