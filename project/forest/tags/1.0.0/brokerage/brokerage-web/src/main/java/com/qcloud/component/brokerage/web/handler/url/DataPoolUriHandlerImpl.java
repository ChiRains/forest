package com.qcloud.component.brokerage.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class DataPoolUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/dataPool/list.do");
		list.add("/admin/dataPool/toAdd.do");
		list.add("/admin/dataPool/toEdit.do");
		list.add("/admin/dataPool/add.do");
		list.add("/admin/dataPool/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/dataPool/list.do");
		return list;
	}
}
