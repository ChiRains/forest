package com.qcloud.component.orderform.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class RefundOrderUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/refundOrder/list.do");
		list.add("/admin/refundOrder/toAdd.do");
		list.add("/admin/refundOrder/toEdit.do");
		list.add("/admin/refundOrder/add.do");
		list.add("/admin/refundOrder/edit.do");
		list.add("/admin/refundOrder/getDetails.do");
		list.add("/admin/refundOrder/list4Merchant.do");
		list.add("/admin/refundOrder/getDetails4Merchant.do");
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/refundOrder/list.do");
		list.add("/admin/refundOrder/list4Merchant.do");
		return list;
	}
}
