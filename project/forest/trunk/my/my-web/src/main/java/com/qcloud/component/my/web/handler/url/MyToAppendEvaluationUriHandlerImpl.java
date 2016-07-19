package com.qcloud.component.my.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MyToAppendEvaluationUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/myToAppendEvaluation/list.do");
		list.add("/admin/myToAppendEvaluation/toAdd.do");
		list.add("/admin/myToAppendEvaluation/toEdit.do");
		list.add("/admin/myToAppendEvaluation/add.do");
		list.add("/admin/myToAppendEvaluation/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/myToAppendEvaluation/list.do");
		return list;
	}
}
