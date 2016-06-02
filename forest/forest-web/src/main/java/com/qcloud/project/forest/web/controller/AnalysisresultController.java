package com.qcloud.project.forest.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.model.Analysisresult;
import com.qcloud.project.forest.model.key.TypeEnum.AnalysisresultType;
import com.qcloud.project.forest.service.AnalysisresultService;
import com.qcloud.project.forest.web.form.BMIForm;
import com.qcloud.project.forest.web.handler.AnalysisresultHandler;
import com.qcloud.project.forest.web.vo.BMIVO;

@Controller
@RequestMapping(value = AnalysisresultController.DIR)
public class AnalysisresultController {

    public static final String    DIR = "/analysisresult";

    @Autowired
    private AnalysisresultService analysisresultService;

    @Autowired
    private AnalysisresultHandler analysisresultHandler;

    @RequestMapping
    @NoReferer
    public FrontAjaxView BMICalculation(BMIForm query) {

        int type = AnalysisresultType.BIM.getKey();
        double BMI = query.getHeight() / (query.getWeight() * query.getWeight());
        Analysisresult analysisResult = analysisresultService.getBMI(type, BMI);
        List<BMIVO> list = analysisresultHandler.toBMIVO(analysisResult, BMI);
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("result", list);
        view.setMessage("BMI计算成功！");
        return view;
    }
}
