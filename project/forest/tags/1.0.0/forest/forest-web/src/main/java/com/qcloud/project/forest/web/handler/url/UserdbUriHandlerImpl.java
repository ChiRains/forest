package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class UserdbUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/userdb/list.do");
		list.add("/admin/userdb/toAdd.do");
		list.add("/admin/userdb/toEdit.do");
		list.add("/admin/userdb/add.do");
		list.add("/admin/userdb/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/userdb/list.do");
		return list;
	}
}
