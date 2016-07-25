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
		//
		list.add("/admin/rangeGrade/rangeList.do");
		list.add("/admin/rangeGrade/toAddRange.do");
        list.add("/admin/rangeGrade/toEditRange.do");
        list.add("/admin/rangeGrade/addRange.do");
        list.add("/admin/rangeGrade/editRange.do");
        list.add("/admin/rangeGrade/deleteRange.do");
        list.add("/admin/rangeGrade/enableRange.do");
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/rangeGrade/list.do");
		return list;
	}
}
