package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class PromotionalOffersUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/promotionalOffers/listPromotionalOfferClassify.do");
        list.add("/admin/promotionalOffers/enablePromotionalOfferClassify.do");
        list.add("/admin/promotionalOffers/toAddPromotionalOfferClassify.do");
        list.add("/admin/promotionalOffers/addPromotionalOfferClassify.do");
        list.add("/admin/promotionalOffers/toEditPromotionalOfferClassify.do");
        list.add("/admin/promotionalOffers/editPromotionalOfferClassify.do");
        list.add("/admin/promotionalOffers/listPromotionalOffer.do");
        list.add("/admin/promotionalOffers/toAddPromotionalOffer.do");
        list.add("/admin/promotionalOffers/addPromotionalOffer.do");
        list.add("/admin/promotionalOffers/deletePromotionalOffer.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/promotionalOffers/listPromotionalOfferClassify.do");
        list.add("/admin/promotionalOffers/enablePromotionalOfferClassify.do");
        list.add("/admin/promotionalOffers/toAddPromotionalOfferClassify.do");
        list.add("/admin/promotionalOffers/addPromotionalOfferClassify.do");
        list.add("/admin/promotionalOffers/toEditPromotionalOfferClassify.do");
        list.add("/admin/promotionalOffers/editPromotionalOfferClassify.do");
        list.add("/admin/promotionalOffers/listPromotionalOffer.do");
        list.add("/admin/promotionalOffers/toAddPromotionalOffer.do");
        list.add("/admin/promotionalOffers/addPromotionalOffer.do");
        list.add("/admin/promotionalOffers/deletePromotionalOffer.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/promotionalOffers/listPromotionalOfferClassify.do", "/admin/promotionalOffers/listPromotionalOffer.do");
        //
        map.put("/admin/promotionalOffers/listPromotionalOfferClassify.do", list);
        map.put("/admin/promotionalOffers/enablePromotionalOfferClassify.do", list);
        map.put("/admin/promotionalOffers/toAddPromotionalOfferClassify.do", list);
        map.put("/admin/promotionalOffers/addPromotionalOfferClassify.do", list);
        map.put("/admin/promotionalOffers/toEditPromotionalOfferClassify.do", list);
        map.put("/admin/promotionalOffers/editPromotionalOfferClassify.do", list);
        map.put("/admin/promotionalOffers/listPromotionalOffer.do", list);
        map.put("/admin/promotionalOffers/toAddPromotionalOffer.do", list);
        map.put("/admin/promotionalOffers/addPromotionalOffer.do", list);
        map.put("/admin/promotionalOffers/deletePromotionalOffer.do", list);
        return map;
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
