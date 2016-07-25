package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class GradeMerchandiseUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/gradeMerchandise/list.do");
		list.add("/admin/gradeMerchandise/toAdd.do");
		list.add("/admin/gradeMerchandise/toEdit.do");
		list.add("/admin/gradeMerchandise/add.do");
		list.add("/admin/gradeMerchandise/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/gradeMerchandise/list.do");
		return list;
	}
}
