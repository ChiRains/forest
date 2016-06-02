package com.qcloud.component.orderform.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ReturnOrderUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/returnOrder/list.do");
		list.add("/admin/returnOrder/toAdd.do");
		list.add("/admin/returnOrder/toEdit.do");
		list.add("/admin/returnOrder/add.do");
		list.add("/admin/returnOrder/edit.do");
		list.add("/admin/returnOrder/getDetails.do");
		list.add("/admin/returnOrder/addLogistics.do");
		list.add("/admin/returnOrder/addMoney.do");
		list.add("/admin/returnOrder/list4Merchant.do");
		list.add("/admin/returnOrder/getDetails4Merchant.do");
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/returnOrder/list.do");
		return list;
	}
}
