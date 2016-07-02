package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ModularUserUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/modularUser/list.do");
		list.add("/admin/modularUser/toAdd.do");
		list.add("/admin/modularUser/toEdit.do");
		list.add("/admin/modularUser/add.do");
		list.add("/admin/modularUser/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/modularUser/list.do");
		return list;
	}
}
