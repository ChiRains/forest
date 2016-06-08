package com.qcloud.project.forest.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.project.forest.model.key.TypeEnum;

@Controller
@RequestMapping(value = SalesPromotionController.DIR)
public class SalesPromotionController {

    public static final String DIR = "/salesPromotion";

    @Autowired
    private PublicdataClient   publicdataClient;

    @Autowired
    private FileSDKClient      fileSDKClient;

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
}
