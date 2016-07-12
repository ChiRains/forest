package com.qcloud.project.forest.web.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.project.forest.model.Feedback;
import com.qcloud.project.forest.model.key.TypeEnum.ClassifyType;
import com.qcloud.project.forest.service.FeedbackService;

@Controller
@RequestMapping(value = FeedbackController.DIR)
public class FeedbackController {

    public static final String DIR = "/feedback";

    @Autowired
    private FeedbackService    feedbackService;

    @Autowired
    private PublicdataClient   publicdataClient;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView commitFeedback(HttpServletRequest request, Feedback feedback) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        feedback.setUserId(user.getId());
        feedback.setState(0);
        feedback.setDate(new Date());
        feedbackService.add(feedback);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("提交成功");
        return frontAjaxView;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getFeedbackClassify() {

        List<Classify> classifies = publicdataClient.listClassify((long) ClassifyType.FEEDBACK.getKey());
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("result", classifies);
        return frontAjaxView;
    }
}
