package com.qcloud.component.orderform.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class CollectOrderUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/collectOrder/list.do");
		list.add("/admin/collectOrder/toAdd.do");
		list.add("/admin/collectOrder/toEdit.do");
		list.add("/admin/collectOrder/add.do");
		list.add("/admin/collectOrder/edit.do");
		list.add("/admin/collectOrder/getDetails.do");
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/collectOrder/list.do");
		return list;
	}
}
