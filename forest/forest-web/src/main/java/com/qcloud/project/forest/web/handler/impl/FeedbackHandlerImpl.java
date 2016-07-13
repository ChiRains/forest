package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.project.forest.model.Feedback;
import com.qcloud.project.forest.web.handler.FeedbackHandler;
import com.qcloud.project.forest.web.vo.FeedbackVO;
import com.qcloud.project.forest.web.vo.admin.AdminFeedbackVO;

@Component
public class FeedbackHandlerImpl implements FeedbackHandler {

    @Autowired
    private PersonalcenterClient personalcenterClient;

    @Autowired
    private PublicdataClient     publicdataClient;

    @Override
    public List<FeedbackVO> toVOList(List<Feedback> list) {

        List<FeedbackVO> voList = new ArrayList<FeedbackVO>();
        for (Feedback feedback : list) {
            voList.add(toVO(feedback));
        }
        return voList;
    }

    @Override
    public FeedbackVO toVO(Feedback feedback) {

        String json = Json.toJson(feedback);
        return Json.toObject(json, FeedbackVO.class, true);
    }

    @Override
    public List<AdminFeedbackVO> toVOList4Admin(List<Feedback> list) {

        List<AdminFeedbackVO> voList = new ArrayList<AdminFeedbackVO>();
        for (Feedback adminFeedback : list) {
            voList.add(toVO4Admin(adminFeedback));
        }
        return voList;
    }

    @Override
    public AdminFeedbackVO toVO4Admin(Feedback feedback) {

        String json = Json.toJson(feedback);
        QUser user = personalcenterClient.getUser(feedback.getUserId());
        String typeName = "";
        switch (feedback.getType()) {
        case 1:
            typeName = "很好";
            break;
        case 2:
            typeName = "一般";
            break;
        case 3:
            typeName = "差劲";
            break;
        }
        AdminFeedbackVO adminFeedbackVO = Json.toObject(json, AdminFeedbackVO.class, true);
        adminFeedbackVO.setUserName(user.getName());
        adminFeedbackVO.setMobile(user.getMobile());
        adminFeedbackVO.setDate(DateUtil.date2String(feedback.getDate(), "yyyy-MM-dd HH:mm:ss"));
        adminFeedbackVO.setTypeName(typeName);
        adminFeedbackVO.setClassifyName(publicdataClient.getClassify(feedback.getClassify()).getName());
        adminFeedbackVO.setStateName(feedback.getState() == 1 ? "已查看" : "未处理");
        return adminFeedbackVO;
    }
}
