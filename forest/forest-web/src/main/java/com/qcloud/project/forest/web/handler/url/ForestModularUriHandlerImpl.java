package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ForestModularUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/forestModular/indexModular.do");
		list.add("/admin/forestModular/editIndexModular.do");
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		return list;
	}

	@Override
	public List<String> whiteNameUris() {
		List<String> list = new ArrayList<String>();
		list.add("/forestModular/indexModularlist.do");
		return list;
	}

}
