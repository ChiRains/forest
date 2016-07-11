package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.Feedback;
import com.qcloud.project.forest.web.vo.FeedbackVO;
import com.qcloud.project.forest.web.vo.admin.AdminFeedbackVO;

public interface FeedbackHandler {

	List<FeedbackVO> toVOList(List<Feedback> list);

	FeedbackVO toVO(Feedback feedback);

	List<AdminFeedbackVO> toVOList4Admin(List<Feedback> list);

	AdminFeedbackVO toVO4Admin(Feedback feedback);
}
