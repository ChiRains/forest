package com.qcloud.project.forest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;

@Controller
@RequestMapping(value = PromotionalOffersController.DIR)
public class PromotionalOffersController {

    public static final String DIR = "/promotionalOffers";

    @Autowired
    private PublicdataClient   publicdataClient;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getPromotionalOffersClassify() {

        FrontAjaxView frontAjaxView = new FrontAjaxView();
        return frontAjaxView;
    }
}
