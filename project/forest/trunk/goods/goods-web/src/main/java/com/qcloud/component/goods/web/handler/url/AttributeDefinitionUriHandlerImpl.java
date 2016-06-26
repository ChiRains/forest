package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class AttributeDefinitionUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/attributeDefinition/list.do");
		list.add("/admin/attributeDefinition/toAdd.do");
		list.add("/admin/attributeDefinition/toEdit.do");
		list.add("/admin/attributeDefinition/add.do");
		list.add("/admin/attributeDefinition/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/attributeDefinition/list.do");
		return list;
	}
}
