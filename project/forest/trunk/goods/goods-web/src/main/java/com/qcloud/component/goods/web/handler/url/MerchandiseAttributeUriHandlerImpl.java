package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseAttributeUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseAttribute/list.do");
		list.add("/admin/merchandiseAttribute/toAdd.do");
		list.add("/admin/merchandiseAttribute/toEdit.do");
		list.add("/admin/merchandiseAttribute/add.do");
		list.add("/admin/merchandiseAttribute/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseAttribute/list.do");
		return list;
	}
}
