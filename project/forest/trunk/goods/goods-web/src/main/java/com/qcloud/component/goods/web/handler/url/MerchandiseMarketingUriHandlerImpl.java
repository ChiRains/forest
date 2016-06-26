package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseMarketingUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseMarketing/list.do");
		list.add("/admin/merchandiseMarketing/toAdd.do");
		list.add("/admin/merchandiseMarketing/toEdit.do");
		list.add("/admin/merchandiseMarketing/add.do");
		list.add("/admin/merchandiseMarketing/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseMarketing/list.do");
		return list;
	}
}
