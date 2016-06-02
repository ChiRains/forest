package com.qcloud.component.orderform.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class OrderItemUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/orderItem/list.do");
        list.add("/admin/orderItem/toAdd.do");
        list.add("/admin/orderItem/toEdit.do");
        list.add("/admin/orderItem/add.do");
        list.add("/admin/orderItem/edit.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/orderItem/list.do");
        return list;
    }
}
