package com.qcloud.component.warehouse.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseStockStateUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandiseStockState/list.do");
        list.add("/admin/merchandiseStockState/toAdd.do");
        list.add("/admin/merchandiseStockState/toEdit.do");
        list.add("/admin/merchandiseStockState/add.do");
        list.add("/admin/merchandiseStockState/edit.do");
        list.add("/admin/merchandiseStockState/delete.do");
        list.add("/admin/merchandiseStockState/outList.do");
        list.add("/admin/merchandiseStockState/confirm.do");
        list.add("/admin/merchandiseStockState/sign.do");
        list.add("/admin/merchandiseStockState/toOutEdit.do");
        list.add("/admin/merchandiseStockState/outEdit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/merchandiseStockState/list.do");
        return list;
    }
}
