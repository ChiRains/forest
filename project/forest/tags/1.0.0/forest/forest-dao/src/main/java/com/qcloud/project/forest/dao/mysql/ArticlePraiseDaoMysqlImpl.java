package com.qcloud.project.forest.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.project.forest.dao.ArticlePraiseDao;
import com.qcloud.project.forest.model.ArticlePraise;
import com.qcloud.project.forest.model.query.ArticlePraiseQuery;

@Repository
public class ArticlePraiseDaoMysqlImpl implements ArticlePraiseDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(ArticlePraise articlePraise) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.ArticlePraiseMapper.insert", articlePraise) == 1;
    }

    @Override
    public ArticlePraise get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ArticlePraiseMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.ArticlePraiseMapper.delete", id) > 0;
    }

    @Override
    public boolean update(ArticlePraise articlePraise) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.ArticlePraiseMapper.update", articlePraise) > 0;
    }

    @Override
    public List<ArticlePraise> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ArticlePraise> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<ArticlePraise> listByArticleId(Long articleId) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ArticlePraise> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<ArticlePraise> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ArticlePraiseMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ArticlePraiseMapper.count4page", param);
        Page<ArticlePraise> page = new Page<ArticlePraise>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<ArticlePraise> page(ArticlePraiseQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<ArticlePraise> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ArticlePraiseMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ArticlePraiseMapper.count4query", param);
        Page<ArticlePraise> page = new Page<ArticlePraise>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<ArticlePraise> listAll() {

        List<ArticlePraise> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ArticlePraiseMapper.listAll");
        return list;
    }

    @Override
    public ArticlePraise getByArticleIdAndUserId(Long articleId, Long userId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("articleId", articleId);
        param.put("userId", userId);
        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ArticlePraiseMapper.getByArticleIdAndUserId", param);
    }
}
