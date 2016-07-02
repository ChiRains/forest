package com.qcloud.component.orderform.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ExchangeOrderUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/exchangeOrder/list.do");
        list.add("/admin/exchangeOrder/toAdd.do");
        list.add("/admin/exchangeOrder/toEdit.do");
        list.add("/admin/exchangeOrder/add.do");
        list.add("/admin/exchangeOrder/edit.do");
        list.add("/admin/exchangeOrder/getDetails.do");
        list.add("/admin/exchangeOrder/addLogistics.do");
        list.add("/admin/exchangeOrder/getDetails4Merchant.do");
        list.add("/admin/exchangeOrder/list4Merchant.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/exchangeOrder/list.do");
        list.add("/admin/exchangeOrder/list4Merchant.do");
        return list;
    }
}
