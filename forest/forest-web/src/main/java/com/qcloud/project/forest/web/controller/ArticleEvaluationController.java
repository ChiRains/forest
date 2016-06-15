package com.qcloud.project.forest.web.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.project.forest.model.ArticleEvaluation;
import com.qcloud.project.forest.model.query.ArticleEvaluationQuery;
import com.qcloud.project.forest.service.ArticleEvaluationService;
import com.qcloud.project.forest.web.handler.ArticleEvaluationHandler;
import com.qcloud.project.forest.web.vo.ArticleEvaluationVO;

@Controller
@RequestMapping(value = ArticleEvaluationController.DIR)
public class ArticleEvaluationController {

    public static final String       DIR = "/articleEvaluation";

    @Autowired
    private ArticleEvaluationService articleEvaluationService;

    @Autowired
    private ArticleEvaluationHandler articleEvaluationHandler;

    @PiratesApp
    @RequestMapping
    public FrontPagingView list(ArticleEvaluationQuery query, PPage pPage) {

        AssertUtil.assertNotNull(query.getArticleId(), "资讯ID不能为空");
        Page<ArticleEvaluation> page = articleEvaluationService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<ArticleEvaluationVO> list = articleEvaluationHandler.toVOList(page.getData());
        FrontPagingView view = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        view.setList(list);
        view.setMessage("查询成功");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView add(HttpServletRequest request, ArticleEvaluation articleEvaluation) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        articleEvaluation.setState(0);// 默认状态为未处理
        articleEvaluation.setUserId(user.getId());// 用户id
        articleEvaluation.setTime(new Date());// 当前时间
        articleEvaluationService.add(articleEvaluation);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("添加成功");
        return view;
    }
}
