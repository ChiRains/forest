package com.qcloud.component.warehouse.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseStockUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseStock/list.do");
		list.add("/admin/merchandiseStock/edit.do");
		list.add("/admin/merchandiseStock/update.do");
		list.add("/admin/merchandiseStock/delete.do");

		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseStock/list.do");
		return list;
	}
}
