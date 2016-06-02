package com.qcloud.component.warehouse.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class StockStateUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/stockState/list.do");
        list.add("/admin/stockState/toAdd.do");
        list.add("/admin/stockState/toEdit.do");
        list.add("/admin/stockState/add.do");
        list.add("/admin/stockState/edit.do");
        list.add("/admin/stockState/selectProductList.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/stockState/list.do");
        return list;
    }
}
