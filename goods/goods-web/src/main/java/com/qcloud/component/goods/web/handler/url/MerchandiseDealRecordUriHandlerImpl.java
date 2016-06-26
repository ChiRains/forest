package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseDealRecordUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseDealRecord/list.do");
		list.add("/admin/merchandiseDealRecord/toAdd.do");
		list.add("/admin/merchandiseDealRecord/toEdit.do");
		list.add("/admin/merchandiseDealRecord/add.do");
		list.add("/admin/merchandiseDealRecord/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseDealRecord/list.do");
		return list;
	}
}
