package com.qcloud.project.forest.web.controller.admin;

import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.model.Feedback;
import com.qcloud.project.forest.model.query.FeedbackQuery;
import com.qcloud.project.forest.service.FeedbackService;
import com.qcloud.project.forest.web.handler.FeedbackHandler;
import com.qcloud.project.forest.web.vo.admin.AdminFeedbackVO;

@Controller
@RequestMapping(value = "/" + AdminFeedbackController.DIR)
public class AdminFeedbackController {

    public static final String DIR = "admin/feedback";

    @Autowired
    private FeedbackService    feedbackService;

    @Autowired
    private FeedbackHandler    feedbackHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, PPage pPage, FeedbackQuery query) {

        Page<Feedback> page = feedbackService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminFeedbackVO> list = feedbackHandler.toVOList4Admin(page.getData());
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/forest-Feedback-list", DIR + "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id, String queryStr) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Feedback feedback = feedbackService.get(id);
        AdminFeedbackVO adminFeedbackVO = feedbackHandler.toVO4Admin(feedback);
        feedback.setState(1);
        feedbackService.update(feedback);
        ModelAndView model = new ModelAndView("/admin/forest-Feedback-edit");
        model.addObject("feedback", adminFeedbackVO);
        model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
        return model;
    }
}
