package com.qcloud.project.forest.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.project.forest.model.PromotionalOffers;
import com.qcloud.project.forest.model.key.TypeEnum.ClassifyType;
import com.qcloud.project.forest.model.query.PromotionalOffersQuery;
import com.qcloud.project.forest.service.PromotionalOffersService;
import com.qcloud.project.forest.web.handler.PromotionalOffersHandler;
import com.qcloud.project.forest.web.vo.PromotionalOffersVO;

@Controller
@RequestMapping(value = PromotionalOffersController.DIR)
public class PromotionalOffersController {

    public static final String       DIR = "/promotionalOffers";

    @Autowired
    private PublicdataClient         publicdataClient;

    @Autowired
    private PromotionalOffersService promotionalOffersService;

    @Autowired
    private PromotionalOffersHandler promotionalOffersHandler;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getPromotionalOffersClassify() {

        List<Classify> classifies = publicdataClient.listClassify((long) ClassifyType.PROMOTIONALOFFERS.getKey());
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("result", classifies);
        return frontAjaxView;
    }

    @PiratesApp
    @RequestMapping
    public FrontPagingView getPromotionalOfferslist(PPage pPage, Long classifyId) {

        PromotionalOffersQuery promotionalOffersQuery = new PromotionalOffersQuery();
        promotionalOffersQuery.setClassify(classifyId);
        Page<PromotionalOffers> page = promotionalOffersService.page(promotionalOffersQuery, pPage.getPageStart(), pPage.getPageSize());
        List<PromotionalOffersVO> promotionalOffersVOs = promotionalOffersHandler.toVOList(page.getData());
        FrontPagingView frontPagingView = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), promotionalOffersVOs.size());
        frontPagingView.setList(promotionalOffersVOs);
        return frontPagingView;
    }
}
