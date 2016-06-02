package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ArticleEvaluationUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/articleEvaluation/list.do");
		list.add("/admin/articleEvaluation/toAdd.do");
		list.add("/admin/articleEvaluation/toEdit.do");
		list.add("/admin/articleEvaluation/add.do");
		list.add("/admin/articleEvaluation/edit.do");

		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/articleEvaluation/list.do");
		return list;
	}

	@Override
	public List<String> whiteNameUris() {
		List<String> list = new ArrayList<String>();
		list.add("/articleEvaluation/list.do");
		list.add("/articleEvaluation/add.do");
		return list;
	}

}
