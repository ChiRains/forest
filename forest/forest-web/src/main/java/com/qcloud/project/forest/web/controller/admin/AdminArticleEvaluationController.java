package com.qcloud.project.forest.web.controller.admin;

import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.model.ArticleEvaluation;
import com.qcloud.project.forest.model.query.ArticleEvaluationQuery;
import com.qcloud.project.forest.service.ArticleEvaluationService;
import com.qcloud.project.forest.web.handler.ArticleEvaluationHandler;
import com.qcloud.project.forest.web.vo.admin.AdminArticleEvaluationVO;

@Controller
@RequestMapping(value = "/" + AdminArticleEvaluationController.DIR)
public class AdminArticleEvaluationController {

    public static final String       DIR = "admin/articleEvaluation";

    @Autowired
    private ArticleEvaluationService articleEvaluationService;

    @Autowired
    private ArticleEvaluationHandler articleEvaluationHandler;

    /**
     * 展示平论列表
     * @param request
     * @param pPage
     * @param query
     * @return
     */
    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, PPage pPage, ArticleEvaluationQuery query) {

        Page<ArticleEvaluation> page = articleEvaluationService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminArticleEvaluationVO> list = articleEvaluationHandler.toVOList4Admin(page.getData());
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/forest-ArticleEvaluation-list", DIR + "/list" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("query", query);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/forest-ArticleEvaluation-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(ArticleEvaluation articleEvaluation) {

        articleEvaluationService.add(articleEvaluation);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        ArticleEvaluation articleEvaluation = articleEvaluationService.get(id);
        AdminArticleEvaluationVO adminArticleEvaluationVO = articleEvaluationHandler.toVO4Admin(articleEvaluation);
        ModelAndView model = new ModelAndView("/admin/forest-ArticleEvaluation-edit");
        model.addObject("articleEvaluation", adminArticleEvaluationVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(ArticleEvaluation articleEvaluation) {

        ArticleEvaluation evaluation = articleEvaluationService.get(articleEvaluation.getId());
        evaluation.setState(articleEvaluation.getState());
        articleEvaluationService.update(evaluation);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        articleEvaluationService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    /**
     * 审核评论
     * @param id
     * @return
     */
    @RequestMapping
    public AceAjaxView enable(Long id, Integer state) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        ArticleEvaluation articleEvaluation = articleEvaluationService.get(id);
        articleEvaluation.setState(state);
        articleEvaluationService.update(articleEvaluation);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("操作成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
