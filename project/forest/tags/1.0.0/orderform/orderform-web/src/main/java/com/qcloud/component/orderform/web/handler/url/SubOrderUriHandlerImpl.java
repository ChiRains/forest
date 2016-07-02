package com.qcloud.component.orderform.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class SubOrderUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/subOrder/list.do");
        list.add("/admin/subOrder/toAdd.do");
        list.add("/admin/subOrder/toEdit.do");
        list.add("/admin/subOrder/add.do");
        list.add("/admin/subOrder/edit.do");
        list.add("/admin/subOrder/listDetail.do");
        list.add("/admin/subOrder/changeStore.do");
        list.add("/admin/subOrder/toAddLogistics.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/subOrder/list.do");
        return list;
    }
}
