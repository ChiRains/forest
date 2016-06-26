package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseVipDiscountUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseVipDiscount/list.do");
		list.add("/admin/merchandiseVipDiscount/toAdd.do");
		list.add("/admin/merchandiseVipDiscount/toEdit.do");
		list.add("/admin/merchandiseVipDiscount/add.do");
		list.add("/admin/merchandiseVipDiscount/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseVipDiscount/list.do");
		return list;
	}
}
