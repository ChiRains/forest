package com.qcloud.project.forest.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ArticleDao;
import com.qcloud.project.forest.model.Article;
import com.qcloud.project.forest.model.query.ArticleQuery;

@Repository
public class ArticleDaoCacheImpl implements ArticleDao {

    @Autowired
    private ArticleDao articleDaoMysqlImpl;

    @Autowired
    private ArticleDao articleDaoRedisImpl;

    @Override
    public boolean add(Article article) {

        return articleDaoMysqlImpl.add(article);
    }

    @Override
    public Article get(Long id) {

        return articleDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return articleDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(Article article) {

        return articleDaoMysqlImpl.update(article);
    }

    @Override
    public List<Article> list(List<Long> idList) {

        return CacheLoader.list(articleDaoRedisImpl, articleDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, Article> map(Set<Long> idSet) {

        return CacheLoader.map(articleDaoRedisImpl, articleDaoMysqlImpl, idSet);
    }

    public List<Article> listByClassifyId(Long classifyId) {

        return articleDaoMysqlImpl.listByClassifyId(classifyId);
    }

    @Override
    public Page<Article> page(int start, int count) {

        return articleDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<Article> page(ArticleQuery query, int start, int count) {

        return articleDaoMysqlImpl.page(query, start, count);
    }

    public List<Article> listAll() {

        return articleDaoMysqlImpl.listAll();
    }
}
