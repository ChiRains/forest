package com.qcloud.project.forest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ArticleEvaluationDao;
import com.qcloud.project.forest.model.ArticleEvaluation;
import com.qcloud.project.forest.model.query.ArticleEvaluationQuery;
import com.qcloud.project.forest.service.ArticleEvaluationService;

@Service
public class ArticleEvaluationServiceImpl implements ArticleEvaluationService {

    @Autowired
    private ArticleEvaluationDao articleEvaluationDao;

    @Autowired
    private AutoIdGenerator      autoIdGenerator;

    private static final String  ID_KEY = "forest_article_evaluation";

    @Override
    public boolean add(ArticleEvaluation articleEvaluation) {

        long id = autoIdGenerator.get(ID_KEY);
        articleEvaluation.setId(id);
        return articleEvaluationDao.add(articleEvaluation);
    }

    @Override
    public ArticleEvaluation get(Long id) {

        return articleEvaluationDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return articleEvaluationDao.delete(id);
    }

    @Override
    public boolean update(ArticleEvaluation articleEvaluation) {

        return articleEvaluationDao.update(articleEvaluation);
    }

    public List<ArticleEvaluation> listByArticleId(Long articleId) {

        return articleEvaluationDao.listByArticleId(articleId);
    }

    public List<ArticleEvaluation> listByUserId(Long userId) {

        return articleEvaluationDao.listByUserId(userId);
    }

    @Override
    public Page<ArticleEvaluation> page(ArticleEvaluationQuery query, int start, int count) {

        return articleEvaluationDao.page(query, start, count);
    }

    public List<ArticleEvaluation> listAll() {

        return articleEvaluationDao.listAll();
    }

    @Override
    public int countEnable(Long id) {

        return articleEvaluationDao.countEnable(id);
    }
}
