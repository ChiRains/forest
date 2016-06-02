package com.qcloud.component.orderform.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ReturnOrderItemDetailUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/returnOrderItemDetail/list.do");
		list.add("/admin/returnOrderItemDetail/toAdd.do");
		list.add("/admin/returnOrderItemDetail/toEdit.do");
		list.add("/admin/returnOrderItemDetail/add.do");
		list.add("/admin/returnOrderItemDetail/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/returnOrderItemDetail/list.do");
		return list;
	}
}
