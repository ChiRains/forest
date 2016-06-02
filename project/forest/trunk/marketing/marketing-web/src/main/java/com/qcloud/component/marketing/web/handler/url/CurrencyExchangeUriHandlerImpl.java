package com.qcloud.component.marketing.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class CurrencyExchangeUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/currencyExchange/exchangeList.do");
        list.add("/admin/currencyExchange/toAdd.do");
        list.add("/admin/currencyExchange/selectProductList.do");
        list.add("/admin/currencyExchange/addExchangeMerchandise.do");
        list.add("/admin/currencyExchange/delete.do");
        list.add("/admin/currencyExchange/multipleSort.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/currencyExchange/exchangeList.do");
        list.add("/admin/currencyExchange/toAdd.do");
        list.add("/admin/currencyExchange/selectProductList.do");
        list.add("/admin/currencyExchange/addExchangeMerchandise.do");
        list.add("/admin/currencyExchange/delete.do");
        list.add("/admin/currencyExchange/multipleSort.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/currencyExchange/exchangeList.do");
        //
        map.put("/admin/currencyExchange/toAdd.do", list);
        map.put("/admin/currencyExchange/selectProductList.do", list);
        map.put("/admin/currencyExchange/addExchangeMerchandise.do", list);
        map.put("/admin/currencyExchange/delete.do", list);
        map.put("/admin/currencyExchange/multipleSort.do", list);
        map.put("/admin/merchandiseItem/listForAdmin.do", list);
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        return list;
    }
}
