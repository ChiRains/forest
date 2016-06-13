package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MedicationRemindersThemeUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/medicationRemindersTheme/list.do");
		list.add("/admin/medicationRemindersTheme/toAdd.do");
		list.add("/admin/medicationRemindersTheme/toEdit.do");
		list.add("/admin/medicationRemindersTheme/add.do");
		list.add("/admin/medicationRemindersTheme/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/medicationRemindersTheme/list.do");
		return list;
	}
}
