package com.qcloud.component.sellercenter.web.controller;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QClassify;
import com.qcloud.pirates.mvc.FrontAjaxView;

@Controller
@RequestMapping(value = MerchantClassifyController.DIR)
public class MerchantClassifyController {

    public static final String DIR = "/classifyMerchant";

    @Autowired
    private PublicdataClient   publicdataClient;

    @Autowired
    private FileSDKClient      fileSDKClient;

    // 商家分类
    @RequestMapping
    public FrontAjaxView listClassify() {

        List<QClassify> classifyList = publicdataClient.listClassifyForTree(1L);
        for (QClassify qClassify : classifyList) {
            if (StringUtils.isNotEmpty(qClassify.getImage())) {
                qClassify.setImage(fileSDKClient.getFileServerUrl() + qClassify.getImage());
            } else {
                qClassify.setImage("");
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("classifyList", classifyList);
        view.setMessage("获取商家分类成功");
        return view;
    }
}
