package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class AnalysisresultUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/analysisresult/list.do");
		list.add("/admin/analysisresult/toAdd.do");
		list.add("/admin/analysisresult/toEdit.do");
		list.add("/admin/analysisresult/add.do");
		list.add("/admin/analysisresult/edit.do");
		list.add("/admin/analysisresult/delete.do");
		list.add("/admin/analysisresult/listPB.do");

		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/analysisresult/list.do");
		return list;
	}

	@Override
	public List<String> whiteNameUris() {
		List<String> list = new ArrayList<String>();
	list.add("/analysisresult/BMICalculation.do");
		return list;
	}


	
}
