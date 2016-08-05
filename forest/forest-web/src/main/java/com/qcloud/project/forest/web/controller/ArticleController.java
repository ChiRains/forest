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
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.mvc.HtmlView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.project.forest.model.Article;
import com.qcloud.project.forest.model.ArticlePraise;
import com.qcloud.project.forest.model.key.TypeEnum.ClassifyType;
import com.qcloud.project.forest.model.query.ArticleQuery;
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

    @PiratesApp
    @RequestMapping
    public FrontPagingView list(ArticleQuery articleQuery, PPage pPage) {

        AssertUtil.assertNotNull(articleQuery.getClassifyId(), "ID不能为空");
        Page<Article> page = articleService.page(articleQuery, pPage.getPageStart(), pPage.getPageSize());
        List<ArticleVO> articleVOs = articleHandler.toVOList(page.getData());
        for (ArticleVO articleVO : articleVOs) {
            articleVO.setContent(null);
        }
        FrontPagingView frontPagingView = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        frontPagingView.setList(articleVOs);
        return frontPagingView;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView get(Long id, HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_IS_LOGIN_PARAMETER_KEY);
        ArticlePraise articlePraise = null;
        if (user != null) {
            articlePraise = articlePraiseService.getByArticleIdAndUserId(id, user.getId());
        }
        AssertUtil.greatZero(id, "ID不能为空");
        Article article = articleService.get(id);
        AssertUtil.assertNotNull(article, "文章不存在.");
        ArticleVO articleVO = articleHandler.toVO(article);
        // 更新浏览数
        article.setViewCount(article.getViewCount() + 1);
        articleService.update(article);
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("result", articleVO);
        view.addObject("isPraise", articlePraise == null ? false : true);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public HtmlView getHtmlView(Long id) {

        Article article = articleService.get(id);
        HtmlView htmlView = new HtmlView("<style>img{width:100%;} </style>" + article.getContent());
        return htmlView;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView articleClassify() {

        List<Classify> list = publicdataClient.listClassify((long) ClassifyType.ARTICLE.getKey());
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("list", list);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView articlePraise(HttpServletRequest request, Long articleId) {

        AssertUtil.greatZero(articleId, "文章id不能为空.");
        Article article = articleService.get(articleId);
        AssertUtil.assertNotNull(article, "文章不存在.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        String message = "";
        ArticlePraise articlePraise = articlePraiseService.getByArticleIdAndUserId(articleId, user.getId());
        if (articlePraise == null) {
            articlePraise = new ArticlePraise();
            articlePraise.setArticleId(articleId);
            articlePraise.setUserId(user.getId());
            articlePraise.setTime(new Date());
            articlePraiseService.add(articlePraise);
            message = "点赞成功.";
            article.setLikeCount(article.getLikeCount() + 1);
        } else {
            articlePraiseService.delete(articlePraise.getId());
            message = "取消点赞.";
            article.setLikeCount(article.getLikeCount() - 1);
        }
        articleService.update(article);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage(message);
        return view;
    }
}
