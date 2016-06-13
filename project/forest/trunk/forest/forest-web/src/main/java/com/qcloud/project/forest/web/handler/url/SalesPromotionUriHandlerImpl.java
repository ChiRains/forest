package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
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
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/salesPromotion/getSalesPromotionClassify.do");
        list.add("/salesPromotion/mallList.do");
        return list;
    }
}
