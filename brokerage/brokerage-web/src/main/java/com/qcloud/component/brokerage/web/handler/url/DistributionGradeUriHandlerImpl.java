package com.qcloud.component.brokerage.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class DistributionGradeUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/distributionGrade/list.do");
        list.add("/admin/distributionGrade/toAdd.do");
        list.add("/admin/distributionGrade/toEdit.do");
        list.add("/admin/distributionGrade/add.do");
        list.add("/admin/distributionGrade/edit.do");
        list.add("/admin/distributionGrade/editFormula.do");
        //
        list.add("/admin/distributionGrade/gradeList.do");
        //
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/distributionGrade/list.do");
        list.add("/admin/distributionGrade/toAdd.do");
        list.add("/admin/distributionGrade/toEdit.do");
        list.add("/admin/distributionGrade/add.do");
        list.add("/admin/distributionGrade/edit.do");
        list.add("/admin/distributionGrade/editFormula.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/distributionGrade/list.do");
        //
        map.put("/admin/distributionGrade/toAdd.do", list);
        map.put("/admin/distributionGrade/toEdit.do", list);
        map.put("/admin/distributionGrade/add.do", list);
        map.put("/admin/distributionGrade/edit.do", list);
        map.put("/admin/distributionGrade/editFormula.do", list);
        return map;
    }
    
    
}
