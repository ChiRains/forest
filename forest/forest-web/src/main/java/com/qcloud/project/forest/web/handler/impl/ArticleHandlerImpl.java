package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.project.forest.model.Article;
import com.qcloud.project.forest.service.ArticleEvaluationService;
import com.qcloud.project.forest.service.ArticleService;
import com.qcloud.project.forest.web.handler.ArticleHandler;
import com.qcloud.project.forest.web.vo.ArticleVO;
import com.qcloud.project.forest.web.vo.admin.AdminArticleVO;

@Component
public class ArticleHandlerImpl implements ArticleHandler {

    @Autowired
    private ArticleService           articleService;

    @Autowired
    private PublicdataClient         publicdataClient;

    @Autowired
    private ArticleEvaluationService articleEvaluationService;

    @Autowired
    private FileSDKClient            fileSDKClient;

    @Override
    public List<ArticleVO> toVOList(List<Article> list) {

        List<ArticleVO> voList = new ArrayList<ArticleVO>();
        ArticleVO articleVO = null;
        for (Article article : list) {
            articleVO = toVO(article);
            voList.add(articleVO);
        }
        return voList;
    }

    @Override
    public ArticleVO toVO(Article article) {

        String json = Json.toJson(article);
        ArticleVO articleVO = Json.toObject(json, ArticleVO.class, true);
        articleVO.setDate(DateUtil.date2String(article.getDate(), "yyyy年MM月dd日"));
        articleVO.setEvaluationCount(articleEvaluationService.countEnable(article.getId()));
        if (articleVO.getImage() != null) {
            articleVO.setImage(fileSDKClient.getFileServerUrl() + articleVO.getImage());
        } else {
            articleVO.setImage("");
        }
        return articleVO;
    }

    @Override
    public List<AdminArticleVO> toVOList4Admin(List<Article> list) {

        List<AdminArticleVO> voList = new ArrayList<AdminArticleVO>();
        for (Article adminArticle : list) {
            publicdataClient.getClassify(adminArticle.getClassifyId());
            AdminArticleVO vo = toVO4Admin(adminArticle);
            AssertUtil.assertNotNull(vo.getClassifyId(), "分类ID不能为空");
            vo.setClassifyName(publicdataClient.getClassify(vo.getClassifyId()).getName());
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public AdminArticleVO toVO4Admin(Article article) {

        String json = Json.toJson(article);
        AdminArticleVO adminArticleVO = Json.toObject(json, AdminArticleVO.class, true);
        adminArticleVO.setImage(fileSDKClient.getFileServerUrl() + adminArticleVO.getImage());
        adminArticleVO.setUid(article.getImage());
        return adminArticleVO;
    }
}
