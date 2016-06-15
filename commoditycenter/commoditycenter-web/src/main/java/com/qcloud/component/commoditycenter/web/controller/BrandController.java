package com.qcloud.component.commoditycenter.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.BrandType;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QClassify;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;

@Controller
@RequestMapping(value = "/" + BrandController.DIR)
public class BrandController {

    public static final String DIR = "/brand";

    @Autowired
    private PublicdataClient   publicdataClient;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView list() {

        List<QClassify> classifyList = publicdataClient.listClassifyForTree((long) BrandType.Brand.getKey());
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("list", classifyList);
        return view;
    }
}
