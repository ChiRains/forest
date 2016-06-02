package com.qcloud.component.pay.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class PayRecordUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/payRecord/list.do");
		list.add("/admin/payRecord/toAdd.do");
		list.add("/admin/payRecord/toEdit.do");
		list.add("/admin/payRecord/add.do");
		list.add("/admin/payRecord/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/payRecord/list.do");
		return list;
	}
}
