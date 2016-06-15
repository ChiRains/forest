package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ForestOrderUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/forestOrder/list.do");
        list.add("/admin/forestOrder/toAdd.do");
        list.add("/admin/forestOrder/toEdit.do");
        list.add("/admin/forestOrder/add.do");
        list.add("/admin/forestOrder/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/forestOrder/list.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/forestOrder/prepareOrder.do");
        list.add("/app/forestOrder/order.do");
        list.add("/app/forestOrder/get.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/forestOrder/prepareOrder.do");
        list.add("/forestOrder/order.do");
        list.add("/forestOrder/get.do");
        //
        list.add("/app/forestOrder/prepareOrder.do");
        list.add("/app/forestOrder/order.do");
        list.add("/app/forestOrder/get.do");
        return list;
    }
}
