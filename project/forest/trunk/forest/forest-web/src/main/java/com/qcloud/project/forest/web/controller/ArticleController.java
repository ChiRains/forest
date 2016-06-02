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
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.model.Article;
import com.qcloud.project.forest.model.ArticlePraise;
import com.qcloud.project.forest.model.key.TypeEnum.ClassifyType;
import com.qcloud.project.forest.service.ArticlePraiseService;
import com.qcloud.project.forest.service.ArticleService;
import com.qcloud.project.forest.web.handler.ArticleHandler;
import com.qcloud.project.forest.web.vo.ArticleVO;

@Controller
@RequestMapping(value = ArticleController.DIR)
public class ArticleController {

    public static final String   DIR = "/article";

    @Autowired
    private ArticleService       articleService;

    @Autowired
    private ArticleHandler       articleHandler;

    @Autowired
    private ArticlePraiseService articlePraiseService;

    @Autowired
    private PublicdataClient     publicdataClient;

    @RequestMapping
    @NoReferer
    public FrontAjaxView list(Long classifyId, HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_IS_LOGIN_PARAMETER_KEY);
        AssertUtil.assertNotNull(classifyId, "ID不能为空");
        Page<Article> page = articleService.page(classifyId);
        List<ArticleVO> list = articleHandler.toVOList(page.getData());
        if (user != null) {
            for (ArticleVO articleVO : list) {
                ArticlePraise articlePraise = articlePraiseService.getByUser(user.getId(), articleVO.getId());
                if (articlePraise != null) {
                    articleVO.setIsPraise(true);
                }
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("result", list);
        return view;
    }

    @RequestMapping
    public FrontAjaxView get(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Article article = articleService.get(id);
        ArticleVO result = articleHandler.toVO(article);
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("result", result);
        return view;
    }

    @RequestMapping
    public FrontAjaxView articleClassify() {

        List<Classify> list = publicdataClient.listClassify((long) ClassifyType.ARTICLE.getKey());
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("result", list);
        return view;
    }

    @RequestMapping
    public FrontAjaxView articlePraise(HttpServletRequest request, Long articleId) {

        AssertUtil.greatZero(articleId, "文章id不能为空.");
        Article article = articleService.get(articleId);
        AssertUtil.assertNotNull(article, "文章不存在.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        String message = "";
        ArticlePraise articlePraise = articlePraiseService.getByUser(user.getId(), articleId);
        if (articlePraise == null) {
            articlePraise = new ArticlePraise();
            articlePraise.setArticleId(articleId);
            articlePraise.setUserId(user.getId());
            articlePraise.setTime(new Date());
            articlePraiseService.add(articlePraise);
            message = "点赞成功.";
        } else {
            articlePraiseService.delete(articlePraise.getId());
            message = "取消点赞.";
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage(message);
        return view;
    }
}
