package com.qcloud.component.orderform.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class OrderItemDetailUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/orderItemDetail/list.do");
		list.add("/admin/orderItemDetail/toAdd.do");
		list.add("/admin/orderItemDetail/toEdit.do");
		list.add("/admin/orderItemDetail/add.do");
		list.add("/admin/orderItemDetail/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/orderItemDetail/list.do");
		return list;
	}
}
