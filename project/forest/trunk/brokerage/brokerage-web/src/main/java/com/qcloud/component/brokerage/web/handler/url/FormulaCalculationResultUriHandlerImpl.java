package com.qcloud.component.brokerage.web.handler.url;

import java.util.ArrayList;
import java.util.List;

import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class FormulaCalculationResultUriHandlerImpl extends AbstractUriHandler {

	@Override
	public List<String> adminUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/formulaCalculationResult/list.do");
		list.add("/admin/formulaCalculationResult/toAdd.do");
		list.add("/admin/formulaCalculationResult/toEdit.do");
		list.add("/admin/formulaCalculationResult/add.do");
		list.add("/admin/formulaCalculationResult/edit.do");
		
		return list;
	}

	@Override
	public List<String> permissionUris() {
		List<String> list = new ArrayList<String>();
		list.add("/admin/formulaCalculationResult/list.do");
		return list;
	}
}
