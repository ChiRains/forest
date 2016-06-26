package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseItemUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseItem/list.do");
		list.add("/admin/merchandiseItem/toAdd.do");
		list.add("/admin/merchandiseItem/toEdit.do");
		list.add("/admin/merchandiseItem/add.do");
		list.add("/admin/merchandiseItem/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseItem/list.do");
		return list;
	}
}
