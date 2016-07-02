package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class SalesPromotionUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/salesPromotion/listClassify.do");
        list.add("/admin/salesPromotion/toAddSalesPromotionClassify.do");
        list.add("/admin/salesPromotion/addSalesPromotionClassify.do");
        list.add("/admin/salesPromotion/mallList.do");
        list.add("/admin/salesPromotion/toAddClassifyForMallCustom.do");
        list.add("/admin/salesPromotion/addClassifyForMallCustom.do");
        list.add("/admin/salesPromotion/deleteMall.do");
        list.add("/admin/salesPromotion/toEditSalesPromotionClassify.do");
        list.add("/admin/salesPromotion/editSalesPromotionClassify.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/salesPromotion/listClassify.do");
        list.add("/admin/salesPromotion/toAddSalesPromotionClassify.do");
        list.add("/admin/salesPromotion/addSalesPromotionClassify.do");
        list.add("/admin/salesPromotion/mallList.do");
        list.add("/admin/salesPromotion/toAddClassifyForMallCustom.do");
        list.add("/admin/salesPromotion/addClassifyForMallCustom.do");
        list.add("/admin/salesPromotion/deleteMall.do");
        list.add("/admin/salesPromotion/toEditSalesPromotionClassify.do");
        list.add("/admin/salesPromotion/editSalesPromotionClassify.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/salesPromotion/listClassify.do", "/admin/salesPromotion/mallList.do");
        //
        map.put("/admin/salesPromotion/toAddSalesPromotionClassify.do", list);
        map.put("/admin/salesPromotion/addSalesPromotionClassify.do", list);
        map.put("/admin/salesPromotion/addSalesPromotionClassify.do", list);
        map.put("/admin/salesPromotion/toAddClassifyForMallCustom.do", list);
        map.put("/admin/salesPromotion/addClassifyForMallCustom.do", list);
        map.put("/admin/salesPromotion/deleteMall.do", list);
        map.put("/admin/salesPromotion/toEditSalesPromotionClassify.do", list);
        map.put("/admin/salesPromotion/editSalesPromotionClassify.do", list);
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/salesPromotion/getSalesPromotionClassify.do");
        list.add("/salesPromotion/mallList.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/salesPromotion/getSalesPromotionClassify.do");
        list.add("/app/salesPromotion/mallList.do");
        return list;
    }
}
