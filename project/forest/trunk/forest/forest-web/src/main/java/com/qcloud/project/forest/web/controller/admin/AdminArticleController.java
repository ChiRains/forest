package com.qcloud.project.forest.web.controller.admin;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.model.Article;
import com.qcloud.project.forest.model.key.TypeEnum;
import com.qcloud.project.forest.model.query.ArticleQuery;
import com.qcloud.project.forest.service.ArticleService;
import com.qcloud.project.forest.web.handler.ArticleHandler;
import com.qcloud.project.forest.web.vo.admin.AdminArticleVO;

@Controller
@RequestMapping(value = "/" + AdminArticleController.DIR)
public class AdminArticleController {

    public static final String DIR = "admin/article";

    @Autowired
    private ArticleService     articleService;

    @Autowired
    private ArticleHandler     articleHandler;

    @Autowired
    private FileSDKClient      fileSDKClient;

    @Autowired
    private PublicdataClient   publicdataClient;

    /**
     * 展示资讯列表
     * @param pageNum
     * @param query
     * @return
     */
    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, PPage pPage, ArticleQuery query) {

        Page<Article> page = articleService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminArticleVO> list = articleHandler.toVOList4Admin(page.getData());
        List<Classify> classifies = publicdataClient.listClassify((long) TypeEnum.ClassifyType.ARTICLE.getKey());
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/forest-Article-list", DIR + "/list" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("classifyList", classifies);
        pagingView.addObject("result", list);
        pagingView.addObject("query", query);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        return pagingView;
    }

    /**
     * 跳转到添加资讯列表
     * @return
     */
    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/forest-Article-add");
        List<Classify> list = publicdataClient.listClassify((long) TypeEnum.ClassifyType.ARTICLE.getKey());
        model.addObject("classifyList", list);
        return model;
    }

    /**
     * 提交添加资讯
     * @param article
     * @return
     */
    @RequestMapping
    public AceAjaxView add(Article article) {

        article.setImage(fileSDKClient.uidToUrl(article.getImage()));
        article.setIsOffshelves(1);
        article.setDate(new Date());
        articleService.add(article);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    /**
     * 跳转到编辑资讯页面
     * @param id
     * @return
     */
    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Article article = articleService.get(id);
        AdminArticleVO adminArticleVO = articleHandler.toVO4Admin(article);
        ModelAndView model = new ModelAndView("/admin/forest-Article-edit");
        List<Classify> list = publicdataClient.listClassify((long) TypeEnum.ClassifyType.ARTICLE.getKey());
        model.addObject("classifyList", list);
        model.addObject("article", adminArticleVO);
        return model;
    }

    /**
     * 提交编辑资讯
     * @param article
     * @return
     */
    @RequestMapping
    public AceAjaxView edit(Article article) {

        Article article2 = articleService.get(article.getId());
        if (!article.getImage().equals(article2.getImage())) {
            String url = fileSDKClient.uidToUrl(article.getImage());
            article.setImage(url);
        }
        article.setLikeCount(article2.getLikeCount());
        article.setViewCount(article2.getViewCount());
        article.setEvaluationCount(article2.getEvaluationCount());
        article.setIsOffshelves(1);
        article.setDate(article2.getDate());
        articleService.update(article);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    /**
     * 删除资讯
     * @param id
     * @return
     */
    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        articleService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    /**
     * 发布资讯
     * @param id
     * @param value
     * @return
     */
    @RequestMapping
    public AceAjaxView enable(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Article article = articleService.get(id);
        article.setEnable(1);
        article.setIsOffshelves(0);
        articleService.update(article);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    /**
     * 下架
     * @param id
     * @return
     */
    @RequestMapping
    public AceAjaxView offshelves(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Article article = articleService.get(id);
        article.setEnable(0);
        article.setIsOffshelves(1);
        articleService.update(article);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView classifyList() {

        List<Classify> list = publicdataClient.listClassify((long) TypeEnum.ClassifyType.ARTICLE.getKey());
        AcePagingView pagingView = new AcePagingView("/admin/forest-Article-Classify-list", DIR + "/list", 1, list.size(), list.size());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAddClassify() {

        ModelAndView model = new ModelAndView("/admin/forest-Article-Classify-add");
        return model;
    }

    @RequestMapping
    public ModelAndView addClassify(Classify classify) {

        classify.setParentId(-1);
        classify.setType(TypeEnum.ClassifyType.ARTICLE.getKey());
        publicdataClient.addClassify(classify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/classifyList");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEditClassify(Long id) {

        Classify result = publicdataClient.getClassify(id);
        AssertUtil.assertNotNull(id, "ID不能为空");
        ModelAndView model = new ModelAndView("/admin/forest-Article-Classify-edit");
        model.addObject("result", result);
        return model;
    }

    @RequestMapping
    public AceAjaxView editClassify(Classify classify) {

        classify.setType(TypeEnum.ClassifyType.ARTICLE.getKey());
        classify.setParentId(-1);
        publicdataClient.update(classify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/classifyList");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView deleteClassify(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        publicdataClient.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/classifyList");
        return aceAjaxView;
    }
}
