package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class ForestMessageUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/forestMessage/modularList.do");
        list.add("/admin/forestMessage/sendPrivateMessage.do");
        return list;
    }

    // @Override
    // public List<String> permissionUris() {
    //
    // List<String> list = new ArrayList<String>();
    // list.add("/admin/forestMessage/modularList.do");
    // list.add("/admin/myMessage/sendPrivateMessage.do");
    // return list;
    // }
    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        // List<String> list = stringToList("/admin/analysisresult/list.do");
        //
        // map.put("/admin/analysisresult/toAdd.do", list);
        // map.put("/admin/analysisresult/toEdit.do", list);
        // map.put("/admin/analysisresult/add.do", list);
        // map.put("/admin/analysisresult/edit.do", list);
        // map.put("/admin/analysisresult/delete.do", list);
        // map.put("/admin/analysisresult/listPB.do", list);
        return map;
    }
}
