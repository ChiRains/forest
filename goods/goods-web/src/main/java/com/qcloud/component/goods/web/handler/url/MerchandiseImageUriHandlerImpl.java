package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseImageUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseImage/list.do");
		list.add("/admin/merchandiseImage/toAdd.do");
		list.add("/admin/merchandiseImage/toEdit.do");
		list.add("/admin/merchandiseImage/add.do");
		list.add("/admin/merchandiseImage/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseImage/list.do");
		return list;
	}
}
