package com.qcloud.component.marketing.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class FullReducesUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/fullReduces/list.do");
		list.add("/admin/fullReduces/toAdd.do");
		list.add("/admin/fullReduces/toEdit.do");
		list.add("/admin/fullReduces/add.do");
		list.add("/admin/fullReduces/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/fullReduces/list.do");
		return list;
	}
}
