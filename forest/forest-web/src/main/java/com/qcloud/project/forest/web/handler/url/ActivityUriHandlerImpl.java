package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ActivityUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/activity/list.do");
		list.add("/admin/activity/toAdd.do");
		list.add("/admin/activity/toEdit.do");
		list.add("/admin/activity/add.do");
		list.add("/admin/activity/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/activity/list.do");
		return list;
	}
}
