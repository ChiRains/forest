package com.qcloud.component.publicservice.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MessageSourceUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/messageSource/list.do");
		list.add("/admin/messageSource/toAdd.do");
		list.add("/admin/messageSource/toEdit.do");
		list.add("/admin/messageSource/add.do");
		list.add("/admin/messageSource/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/messageSource/list.do");
		return list;
	}
}
