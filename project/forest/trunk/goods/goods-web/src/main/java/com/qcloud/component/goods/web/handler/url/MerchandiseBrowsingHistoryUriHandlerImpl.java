package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseBrowsingHistoryUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseBrowsingHistory/list.do");
		list.add("/admin/merchandiseBrowsingHistory/toAdd.do");
		list.add("/admin/merchandiseBrowsingHistory/toEdit.do");
		list.add("/admin/merchandiseBrowsingHistory/add.do");
		list.add("/admin/merchandiseBrowsingHistory/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseBrowsingHistory/list.do");
		return list;
	}
}
