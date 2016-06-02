package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ArticlePraiseDao;
import com.qcloud.project.forest.model.ArticlePraise;
import com.qcloud.project.forest.model.query.ArticlePraiseQuery;

@Repository
public class ArticlePraiseDaoRedisImpl implements ArticlePraiseDao {

    // @Resource(name = "redis-forest")
    // private Redis redis;
    @Override
    public boolean add(ArticlePraise articlePraise) {

        throw new NotImplementedException();
    }

    @Override
    public ArticlePraise get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(ArticlePraise articlePraise) {

        throw new NotImplementedException();
    }

    @Override
    public List<ArticlePraise> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ArticlePraise> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ArticlePraise> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ArticlePraise> page(ArticlePraiseQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<ArticlePraise> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public ArticlePraise getByUser(Long userId, Long articleId) {

        throw new NotImplementedException();
    }
}
