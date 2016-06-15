package com.qcloud.project.forest.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.marketing.model.MerchandiseCustomClassification;
import com.qcloud.component.marketing.model.query.MerchandiseCustomClassificationQuery;
import com.qcloud.component.marketing.service.MerchandiseCustomClassificationService;
import com.qcloud.component.marketing.web.handler.MerchandiseCustomClassificationHandler;
import com.qcloud.component.marketing.web.vo.MerchandiseCustomClassificationVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.project.forest.model.key.TypeEnum;

@Controller
@RequestMapping(value = SalesPromotionController.DIR)
public class SalesPromotionController {

    public static final String                     DIR = "/salesPromotion";

    @Autowired
    private PublicdataClient                       publicdataClient;

    @Autowired
    private FileSDKClient                          fileSDKClient;

    @Autowired
    private MerchandiseCustomClassificationService merchandiseCustomClassificationService;

    @Autowired
    private MerchandiseCustomClassificationHandler merchandiseCustomClassificationHandler;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getSalesPromotionClassify() {

        List<Classify> classifys = publicdataClient.listClassify((long) TypeEnum.ClassifyType.SALESPROMOTION.getKey());
        for (Classify classify : classifys) {
            classify.setImage(fileSDKClient.getFileServerUrl() + classify.getImage());
        }
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("result", classifys);
        return frontAjaxView;
    }

    @PiratesApp
    @RequestMapping
    public FrontPagingView mallList(HttpServletRequest request, PPage pPage, MerchandiseCustomClassificationQuery query) {

        Page<MerchandiseCustomClassification> page = merchandiseCustomClassificationService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<MerchandiseCustomClassificationVO> voList = merchandiseCustomClassificationHandler.toVOList(page.getData());
        FrontPagingView view = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        view.setList(voList);
        view.setMessage("获取商品列表成功.");
        return view;
    }
}
