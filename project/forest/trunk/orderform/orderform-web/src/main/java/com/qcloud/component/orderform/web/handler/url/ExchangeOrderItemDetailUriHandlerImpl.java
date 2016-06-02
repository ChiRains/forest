package com.qcloud.component.orderform.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ExchangeOrderItemDetailUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/exchangeOrderItemDetail/list.do");
		list.add("/admin/exchangeOrderItemDetail/toAdd.do");
		list.add("/admin/exchangeOrderItemDetail/toEdit.do");
		list.add("/admin/exchangeOrderItemDetail/add.do");
		list.add("/admin/exchangeOrderItemDetail/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/exchangeOrderItemDetail/list.do");
		return list;
	}
}
