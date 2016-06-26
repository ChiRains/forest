package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class CombinationMerchandiseUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/combinationMerchandise/list.do");
		list.add("/admin/combinationMerchandise/toAdd.do");
		list.add("/admin/combinationMerchandise/toEdit.do");
		list.add("/admin/combinationMerchandise/add.do");
		list.add("/admin/combinationMerchandise/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/combinationMerchandise/list.do");
		return list;
	}
}
