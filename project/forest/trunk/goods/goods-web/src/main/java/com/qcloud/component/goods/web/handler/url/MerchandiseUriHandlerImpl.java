package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandise/list.do");
		list.add("/admin/merchandise/toAdd.do");
		list.add("/admin/merchandise/toEdit.do");
		list.add("/admin/merchandise/add.do");
		list.add("/admin/merchandise/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandise/list.do");
		return list;
	}
}
