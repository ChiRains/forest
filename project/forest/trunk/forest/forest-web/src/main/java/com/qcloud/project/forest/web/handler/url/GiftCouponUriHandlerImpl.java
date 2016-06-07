package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class GiftCouponUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/giftCoupon/list.do");
		list.add("/admin/giftCoupon/toAdd.do");
		list.add("/admin/giftCoupon/toEdit.do");
		list.add("/admin/giftCoupon/add.do");
		list.add("/admin/giftCoupon/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/giftCoupon/list.do");
		return list;
	}
}
