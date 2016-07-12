package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class PromotionalOffersUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/promotionalOffers/list.do");
        list.add("/admin/promotionalOffers/toAdd.do");
        list.add("/admin/promotionalOffers/toEdit.do");
        list.add("/admin/promotionalOffers/add.do");
        list.add("/admin/promotionalOffers/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/promotionalOffers/list.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/promotionalOffers/getPromotionalOffersClassify.do");
        list.add("/promotionalOffers/getPromotionalOfferslist.do");
        list.add("/app/promotionalOffers/getPromotionalOffersClassify.do");
        list.add("/app/promotionalOffers/getPromotionalOfferslist.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/promotionalOffers/getPromotionalOffersClassify.do");
        list.add("/app/promotionalOffers/getPromotionalOfferslist.do");
        return list;
    }
}
