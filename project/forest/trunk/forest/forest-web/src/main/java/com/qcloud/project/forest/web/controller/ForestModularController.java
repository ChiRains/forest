package com.qcloud.project.forest.web.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.project.forest.model.ForestModular;
import com.qcloud.project.forest.service.ForestModularService;

@Controller
@RequestMapping(value = ForestModularController.DIR)
public class ForestModularController {

    public static final String   DIR = "/forestModular";

    @Autowired
    private ForestModularService forestModularService;

    @Autowired
    private FileSDKClient        fileSDKClient;

    @RequestMapping
    public FrontAjaxView indexModularlist() {

        List<ForestModular> list = forestModularService.listAppIndexModular();
        for (ForestModular forestModular : list) {
            forestModular.setValue(fileSDKClient.getFileServerUrl() + forestModular.getValue());
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("app首页模块");
        view.addObject("list", list);
        return view;
    }
}
