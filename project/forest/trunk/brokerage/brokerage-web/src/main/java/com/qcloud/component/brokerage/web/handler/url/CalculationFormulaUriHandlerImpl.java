package com.qcloud.component.brokerage.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class CalculationFormulaUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/calculationFormula/list.do");
        list.add("/admin/calculationFormula/toAdd.do");
        list.add("/admin/calculationFormula/toEdit.do");
        list.add("/admin/calculationFormula/add.do");
        list.add("/admin/calculationFormula/edit.do");
        // list.add("/admin/calculationFormula/delete.do");
        list.add("/admin/calculationFormula/enableFormula.do");
        list.add("/admin/calculationFormula/disableFormula.do");
        //
        list.add("/admin/calculationFormula/listBySelect.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/calculationFormula/list.do");
        list.add("/admin/calculationFormula/toAdd.do");
        list.add("/admin/calculationFormula/toEdit.do");
        list.add("/admin/calculationFormula/add.do");
        list.add("/admin/calculationFormula/edit.do");
        // list.add("/admin/calculationFormula/delete.do");
        list.add("/admin/calculationFormula/enableFormula.do");
        list.add("/admin/calculationFormula/disableFormula.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/calculationFormula/list.do");
        //
        map.put("/admin/calculationFormula/enableFormula.do", list);
        map.put("/admin/calculationFormula/disableFormula.do", list);
        map.put("/admin/calculationFormula/toAdd.do", list);
        map.put("/admin/calculationFormula/toEdit.do", list);
        map.put("/admin/calculationFormula/add.do", list);
        map.put("/admin/calculationFormula/edit.do", list);
        return map;
    }
}
