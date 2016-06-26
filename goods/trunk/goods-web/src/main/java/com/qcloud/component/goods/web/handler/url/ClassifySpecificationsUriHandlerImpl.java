package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ClassifySpecificationsUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/classifySpecifications/list.do");
		list.add("/admin/classifySpecifications/toAdd.do");
		list.add("/admin/classifySpecifications/toEdit.do");
		list.add("/admin/classifySpecifications/add.do");
		list.add("/admin/classifySpecifications/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/classifySpecifications/list.do");
		return list;
	}
}
