package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class AnalysisresultUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/analysisresult/listBMI.do");
        list.add("/admin/analysisresult/toAdd.do");
        list.add("/admin/analysisresult/toEdit.do");
        list.add("/admin/analysisresult/add.do");
        list.add("/admin/analysisresult/edit.do");
        list.add("/admin/analysisresult/delete.do");
        list.add("/admin/analysisresult/listPB.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/analysisresult/list.do");
        list.add("/admin/analysisresult/toAdd.do");
        list.add("/admin/analysisresult/toEdit.do");
        list.add("/admin/analysisresult/add.do");
        list.add("/admin/analysisresult/edit.do");
        list.add("/admin/analysisresult/delete.do");
        list.add("/admin/analysisresult/listPB.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/analysisresult/list.do", "/admin/analysisresult/listBMI.do");
        //
        map.put("/admin/analysisresult/toAdd.do", list);
        map.put("/admin/analysisresult/toEdit.do", list);
        map.put("/admin/analysisresult/add.do", list);
        map.put("/admin/analysisresult/edit.do", list);
        map.put("/admin/analysisresult/delete.do", list);
        map.put("/admin/analysisresult/listPB.do", list);
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/analysisresult/bmiCalculation.do");
        list.add("/app/analysisresult/bmiCalculation.do");
        list.add("/analysisresult/getHtmlView.do");
        list.add("/app/analysisresult/getHtmlView.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/analysisresult/bmiCalculation.do");
        list.add("/app/analysisresult/getHtmlView.do");
        return list;
    }
}
