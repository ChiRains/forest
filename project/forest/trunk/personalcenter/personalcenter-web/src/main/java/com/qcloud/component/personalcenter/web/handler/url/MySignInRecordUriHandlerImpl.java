package com.qcloud.component.personalcenter.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MySignInRecordUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/mySignInRecord/list.do");
		list.add("/admin/mySignInRecord/toAdd.do");
		list.add("/admin/mySignInRecord/toEdit.do");
		list.add("/admin/mySignInRecord/add.do");
		list.add("/admin/mySignInRecord/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/mySignInRecord/list.do");
		return list;
	}
}
