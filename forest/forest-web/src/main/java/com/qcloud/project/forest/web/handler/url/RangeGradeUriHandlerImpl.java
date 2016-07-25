package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class RangeGradeUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/rangeGrade/list.do");
		list.add("/admin/rangeGrade/toAdd.do");
		list.add("/admin/rangeGrade/toEdit.do");
		list.add("/admin/rangeGrade/add.do");
		list.add("/admin/rangeGrade/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/rangeGrade/list.do");
		return list;
	}
}
