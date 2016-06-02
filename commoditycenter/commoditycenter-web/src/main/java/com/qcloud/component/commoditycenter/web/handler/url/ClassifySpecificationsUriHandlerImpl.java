package com.qcloud.component.commoditycenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ClassifySpecificationsUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/classifySpecifications/list.do");
        list.add("/admin/classifySpecifications/toAddClassifySpec.do");
        list.add("/admin/classifySpecifications/addClassifySpec.do");
        list.add("/admin/classifySpecifications/toEditClassifySpec.do");
        list.add("/admin/classifySpecifications/editClassifySpec.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/classifySpecifications/list.do");
        list.add("/admin/classifySpecifications/toAddClassifySpec.do");
        list.add("/admin/classifySpecifications/addClassifySpec.do");
        list.add("/admin/classifySpecifications/toEditClassifySpec.do");
        list.add("/admin/classifySpecifications/editClassifySpec.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/classifyAttribute/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/classifySpecifications/list.do", list);
        map.put("/admin/classifySpecifications/toAddClassifySpec.do", list);
        map.put("/admin/classifySpecifications/addClassifySpec.do", list);
        map.put("/admin/classifySpecifications/toEditClassifySpec.do", list);
        map.put("/admin/classifySpecifications/editClassifySpec.do", list);
        return map;
    }
}
