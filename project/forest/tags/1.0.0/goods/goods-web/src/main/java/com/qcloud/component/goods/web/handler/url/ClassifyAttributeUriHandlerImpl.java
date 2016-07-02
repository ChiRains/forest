package com.qcloud.component.goods.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ClassifyAttributeUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/classifyAttribute/list.do");
        list.add("/admin/classifyAttribute/toSetAttributes.do");
        list.add("/admin/classifyAttribute/setAttribute.do");
        list.add("/admin/classifyAttribute/classifyList.do");
        list.add("/admin/classifyAttribute/toAddClassifyAttr.do");
        list.add("/admin/classifyAttribute/addClassifyAttr.do");
        list.add("/admin/classifyAttribute/toEditClassifyAttr.do");
        list.add("/admin/classifyAttribute/editClassifyAttr.do");
        list.add("/admin/classifyAttribute/deleteMallClassify.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/classifyAttribute/list.do");
        list.add("/admin/classifyAttribute/toSetAttributes.do");
        list.add("/admin/classifyAttribute/setAttribute.do");
        list.add("/admin/classifyAttribute/classifyList.do");
        list.add("/admin/classifyAttribute/toAddClassifyAttr.do");
        list.add("/admin/classifyAttribute/addClassifyAttr.do");
        list.add("/admin/classifyAttribute/toEditClassifyAttr.do");
        list.add("/admin/classifyAttribute/editClassifyAttr.do");
        list.add("/admin/classifyAttribute/deleteMallClassify.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/classifyAttribute/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/classifyAttribute/toSetAttributes.do", list);
        map.put("/admin/classifyAttribute/toAddClassifyAttr.do", list);
        map.put("/admin/classifyAttribute/addClassifyAttr.do", list);
        map.put("/admin/classifyAttribute/toEditClassifyAttr.do", list);
        map.put("/admin/classifyAttribute/editClassifyAttr.do", list);
        //
        List<String> classList = stringToList("/admin/classifyAttribute/list.do", "/admin/classifyAttribute/classifyList.do");
        //
        map.put("/admin/classify4Type/toAdd.do", classList);
        map.put("/admin/classify4Type/add.do", classList);
        map.put("/admin/classify4Type/toEdit.do", classList);
        map.put("/admin/classify4Type/edit.do", classList);
        map.put("/admin/classify4Type/top.do", classList);
        map.put("/admin/classify4Type/down.do", classList);
        map.put("/admin/classify4Type/upward.do", classList);
        map.put("/admin/classify4Type/enable.do", classList);
        map.put("/admin/classify4Type/disable.do", classList);
        return map;
    }
}
