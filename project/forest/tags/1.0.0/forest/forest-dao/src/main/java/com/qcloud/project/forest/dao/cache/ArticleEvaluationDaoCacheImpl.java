package com.qcloud.project.forest.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ArticleEvaluationDao;
import com.qcloud.project.forest.model.ArticleEvaluation;
import com.qcloud.project.forest.model.query.ArticleEvaluationQuery;

@Repository
public class ArticleEvaluationDaoCacheImpl implements ArticleEvaluationDao {

    @Autowired
    private ArticleEvaluationDao articleEvaluationDaoMysqlImpl;

    @Autowired
    private ArticleEvaluationDao articleEvaluationDaoRedisImpl;

    @Override
    public boolean add(ArticleEvaluation articleEvaluation) {

        return articleEvaluationDaoMysqlImpl.add(articleEvaluation);
    }

    @Override
    public ArticleEvaluation get(Long id) {

        return articleEvaluationDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return articleEvaluationDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(ArticleEvaluation articleEvaluation) {

        return articleEvaluationDaoMysqlImpl.update(articleEvaluation);
    }

    @Override
    public List<ArticleEvaluation> list(List<Long> idList) {

        return CacheLoader.list(articleEvaluationDaoRedisImpl, articleEvaluationDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, ArticleEvaluation> map(Set<Long> idSet) {

        return CacheLoader.map(articleEvaluationDaoRedisImpl, articleEvaluationDaoMysqlImpl, idSet);
    }

    public List<ArticleEvaluation> listByArticleId(Long articleId) {

        return articleEvaluationDaoMysqlImpl.listByArticleId(articleId);
    }

    public List<ArticleEvaluation> listByUserId(Long userId) {

        return articleEvaluationDaoMysqlImpl.listByUserId(userId);
    }

    @Override
    public Page<ArticleEvaluation> page(int start, int count) {

        return articleEvaluationDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<ArticleEvaluation> page(ArticleEvaluationQuery query, int start, int count) {

        return articleEvaluationDaoMysqlImpl.page(query, start, count);
    }

    public List<ArticleEvaluation> listAll() {

        return articleEvaluationDaoMysqlImpl.listAll();
    }
}
