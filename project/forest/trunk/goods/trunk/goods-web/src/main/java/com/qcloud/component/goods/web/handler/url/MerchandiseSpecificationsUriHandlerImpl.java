package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseSpecificationsUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseSpecifications/list.do");
		list.add("/admin/merchandiseSpecifications/toAdd.do");
		list.add("/admin/merchandiseSpecifications/toEdit.do");
		list.add("/admin/merchandiseSpecifications/add.do");
		list.add("/admin/merchandiseSpecifications/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseSpecifications/list.do");
		return list;
	}
}
