package com.qcloud.component.orderform.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class RefundOrderItemDetailUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/refundOrderItemDetail/list.do");
		list.add("/admin/refundOrderItemDetail/toAdd.do");
		list.add("/admin/refundOrderItemDetail/toEdit.do");
		list.add("/admin/refundOrderItemDetail/add.do");
		list.add("/admin/refundOrderItemDetail/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/refundOrderItemDetail/list.do");
		return list;
	}
}
