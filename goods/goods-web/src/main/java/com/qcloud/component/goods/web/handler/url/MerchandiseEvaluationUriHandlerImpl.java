package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MerchandiseEvaluationUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseEvaluation/list.do");
		list.add("/admin/merchandiseEvaluation/toAdd.do");
		list.add("/admin/merchandiseEvaluation/toEdit.do");
		list.add("/admin/merchandiseEvaluation/add.do");
		list.add("/admin/merchandiseEvaluation/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/merchandiseEvaluation/list.do");
		return list;
	}
}
