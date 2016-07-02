package com.qcloud.component.brokerage.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class DistributionBrokerageUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/distributionBrokerage/list.do");
		list.add("/admin/distributionBrokerage/toAdd.do");
		list.add("/admin/distributionBrokerage/toEdit.do");
		list.add("/admin/distributionBrokerage/add.do");
		list.add("/admin/distributionBrokerage/edit.do");
		list.add("/admin/distributionBrokerage/getDetails.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/distributionBrokerage/list.do");
		return list;
	}
}
