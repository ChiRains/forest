package com.qcloud.project.forest.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ArticlePraiseDao;
import com.qcloud.project.forest.model.ArticlePraise;
import com.qcloud.project.forest.model.query.ArticlePraiseQuery;

@Repository
public class ArticlePraiseDaoCacheImpl implements ArticlePraiseDao {

    @Autowired
    private ArticlePraiseDao articlePraiseDaoMysqlImpl;

    @Autowired
    private ArticlePraiseDao articlePraiseDaoRedisImpl;

    @Override
    public boolean add(ArticlePraise articlePraise) {

        return articlePraiseDaoMysqlImpl.add(articlePraise);
    }

    @Override
    public ArticlePraise get(Long id) {

        return CacheLoader.get(articlePraiseDaoRedisImpl, articlePraiseDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return articlePraiseDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(ArticlePraise articlePraise) {

        return articlePraiseDaoMysqlImpl.update(articlePraise);
    }

    @Override
    public List<ArticlePraise> list(List<Long> idList) {

        return CacheLoader.list(articlePraiseDaoRedisImpl, articlePraiseDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, ArticlePraise> map(Set<Long> idSet) {

        return CacheLoader.map(articlePraiseDaoRedisImpl, articlePraiseDaoMysqlImpl, idSet);
    }

    @Override
    public Page<ArticlePraise> page(int start, int count) {

        return articlePraiseDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<ArticlePraise> page(ArticlePraiseQuery query, int start, int count) {

        return articlePraiseDaoMysqlImpl.page(query, start, count);
    }

    public List<ArticlePraise> listAll() {

        return articlePraiseDaoMysqlImpl.listAll();
    }

    @Override
    public ArticlePraise getByUser(Long userId, Long articleId) {

        return articlePraiseDaoMysqlImpl.getByUser(userId, articleId);
    }
}
