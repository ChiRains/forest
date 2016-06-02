package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MedicationTimeUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/medicationTime/list.do");
		list.add("/admin/medicationTime/toAdd.do");
		list.add("/admin/medicationTime/toEdit.do");
		list.add("/admin/medicationTime/add.do");
		list.add("/admin/medicationTime/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/medicationTime/list.do");
		return list;
	}
}
