package com.qcloud.project.forest.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QClassify;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.project.forest.model.key.TypeConstant;
import com.qcloud.project.forest.model.key.TypeEnum.ClassifyType;
import com.qcloud.project.forest.service.PartsMerchandiseService;
import com.qcloud.project.forest.web.handler.PartsMerchandiseHandler;

@Controller
@RequestMapping(value = PartsMerchandiseController.DIR)
public class PartsMerchandiseController {

    public static final String      DIR = "/partsMerchandise";

    @Autowired
    private PartsMerchandiseService partsMerchandiseService;

    @Autowired
    private PartsMerchandiseHandler partsMerchandiseHandler;

    @Autowired
    private PublicdataClient        publicdataClient;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listParts(HttpServletRequest request, Long classifyId) {

        List<QClassify> classifyList = new ArrayList<QClassify>();
        if (classifyId > 0) {
            classifyList = publicdataClient.listClassifyForTreeByParent(classifyId, TypeConstant.PART_TYPE);
        } else {
            classifyList = publicdataClient.listClassifyForTree(TypeConstant.PART_TYPE);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("classifyList", classifyList);
        view.setMessage("获取分类成功");
        return view;
    }
}
