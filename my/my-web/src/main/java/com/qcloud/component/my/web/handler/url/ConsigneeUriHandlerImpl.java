package com.qcloud.component.my.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ConsigneeUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/consignee/add.do");
        list.add("/consignee/update.do");
        list.add("/consignee/delete.do");
        list.add("/consignee/list.do");
        list.add("/consignee/setDefault.do");
        list.add("/consignee/getDefault.do");
        list.add("/consignee/get.do");
        // #######################################
        list.add("/app/consignee/add.do");
        list.add("/app/consignee/update.do");
        list.add("/app/consignee/delete.do");
        list.add("/app/consignee/list.do");
        list.add("/app/consignee/setDefault.do");
        list.add("/app/consignee/getDefault.do");
        list.add("/app/consignee/get.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/consignee/add.do");
        list.add("/app/consignee/update.do");
        list.add("/app/consignee/delete.do");
        list.add("/app/consignee/list.do");
        list.add("/app/consignee/setDefault.do");
        list.add("/app/consignee/getDefault.do");
        list.add("/app/consignee/get.do");
        return list;
    }
    
    @Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/consignee/toAdd.do");
		list.add("/admin/consignee/add.do");
		list.add("/admin/consignee/listBySelect.do");
		list.add("/admin/consignee/defautConsignee.do");
		return list;
	}
}
