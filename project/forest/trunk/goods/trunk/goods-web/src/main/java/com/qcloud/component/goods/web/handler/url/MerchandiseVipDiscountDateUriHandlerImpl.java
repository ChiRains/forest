package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseVipDiscountDateUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseVipDiscountDate/list.do");
		list.add("/admin/merchandiseVipDiscountDate/toAdd.do");
		list.add("/admin/merchandiseVipDiscountDate/toEdit.do");
		list.add("/admin/merchandiseVipDiscountDate/add.do");
		list.add("/admin/merchandiseVipDiscountDate/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseVipDiscountDate/list.do");
		return list;
	}
}
