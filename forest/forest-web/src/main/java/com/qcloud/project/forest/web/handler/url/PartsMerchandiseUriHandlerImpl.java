package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class PartsMerchandiseUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/partsMerchandise/list.do");
        list.add("/admin/partsMerchandise/toAdd.do");
        list.add("/admin/partsMerchandise/toEdit.do");
        list.add("/admin/partsMerchandise/add.do");
        list.add("/admin/partsMerchandise/edit.do");
        list.add("/admin/partsMerchandise/listParts.do");
        list.add("/admin/partsMerchandise/toAddParts.do");
        list.add("/admin/partsMerchandise/addParts.do");
        list.add("/admin/partsMerchandise/toEditParts.do");
        list.add("/admin/partsMerchandise/editParts.do");
        list.add("/admin/partsMerchandise/toEditMerchandise.do");
        list.add("/admin/partsMerchandise/editMerchandise.do");
        list.add("/admin/partsMerchandise/flushState.do");
        list.add("/admin/partsMerchandise/delete.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/partsMerchandise/list.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/partsMerchandise/listParts.do");
        list.add("/partsMerchandise/list.do");
        list.add("/app/partsMerchandise/listParts.do");
        list.add("/app/partsMerchandise/list.do");
        // 找周边
        list.add("/nearbyDrugstore/listNearby.do");
        list.add("/nearbyDrugstore/getStore.do");
        list.add("/app/nearbyDrugstore/listNearby.do");
        list.add("/app/nearbyDrugstore/getStore.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/partsMerchandise/listParts.do");
        list.add("/app/partsMerchandise/list.do");
        return list;
    }
}
