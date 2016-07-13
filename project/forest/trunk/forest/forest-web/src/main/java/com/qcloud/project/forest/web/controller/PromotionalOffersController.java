package com.qcloud.project.forest.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.project.forest.model.key.TypeEnum.ClassifyType;

@Controller
@RequestMapping(value = PromotionalOffersController.DIR)
public class PromotionalOffersController {

    public static final String DIR = "/promotionalOffers";

    @Autowired
    private PublicdataClient   publicdataClient;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getPromotionalOffersClassify() {

        List<Classify> classifies = publicdataClient.listClassify((long) ClassifyType.PROMOTIONALOFFERS.getKey());
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("result", classifies);
        return frontAjaxView;
    }
    // @PiratesApp
    // @RequestMapping
    // public FrontPagingView getPromotionalOfferslist(PPage pPage, Long classifyId) {
    //
    // FrontPagingView frontPagingView = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), total);
    // return frontPagingView;
    // }
}
