package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.project.forest.service.ArticleService;
import com.qcloud.project.forest.web.handler.ArticleEvaluationHandler;
import com.qcloud.project.forest.model.Article;
import com.qcloud.project.forest.model.ArticleEvaluation;
import com.qcloud.project.forest.web.vo.ArticleEvaluationVO;
import com.qcloud.project.forest.web.vo.admin.AdminArticleEvaluationVO;

@Component
public class ArticleEvaluationHandlerImpl implements ArticleEvaluationHandler {

    @Autowired
    private PersonalcenterClient personalcenterClient;

    @Autowired
    private ArticleService       articleService;

    @Override
    public List<ArticleEvaluationVO> toVOList(List<ArticleEvaluation> list) {

        List<ArticleEvaluationVO> voList = new ArrayList<ArticleEvaluationVO>();
        for (ArticleEvaluation articleEvaluation : list) {
            ArticleEvaluationVO vo = toVO(articleEvaluation);
            vo.setTime(DateUtil.date2String(articleEvaluation.getTime()));
            if (articleEvaluation.getUserId() != 0) {
                QUser user = personalcenterClient.getUser(articleEvaluation.getUserId());
                if (user != null) {
                    vo.setUserName(user.getName());// userName
                    vo.setHeadImage(user.getHeadImage());// 头像
                }
            }
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public ArticleEvaluationVO toVO(ArticleEvaluation articleEvaluation) {

        String json = Json.toJson(articleEvaluation);
        return Json.toObject(json, ArticleEvaluationVO.class, true);
    }

    @Override
    public List<AdminArticleEvaluationVO> toVOList4Admin(List<ArticleEvaluation> list) {

        List<AdminArticleEvaluationVO> voList = new ArrayList<AdminArticleEvaluationVO>();
        for (ArticleEvaluation adminArticleEvaluation : list) {
            AdminArticleEvaluationVO articleEvaluation = toVO4Admin(adminArticleEvaluation);
            Article article = articleService.get(adminArticleEvaluation.getArticleId());
            if (article != null) articleEvaluation.setArticleName(article.getName());// 资讯名称
            QUser user = personalcenterClient.getUser(articleEvaluation.getUserId());
            if (user != null) articleEvaluation.setUserName(user.getName());// username
            voList.add(articleEvaluation);
        }
        return voList;
    }

    @Override
    public AdminArticleEvaluationVO toVO4Admin(ArticleEvaluation articleEvaluation) {

        String json = Json.toJson(articleEvaluation);
        return Json.toObject(json, AdminArticleEvaluationVO.class, true);
    }
}
