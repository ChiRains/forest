package com.qcloud.project.forest.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.project.forest.dao.ArticleDao;
import com.qcloud.project.forest.model.Article;
import com.qcloud.project.forest.model.query.ArticleQuery;

@Repository
public class ArticleDaoMysqlImpl implements ArticleDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(Article article) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.ArticleMapper.insert", article) == 1;
    }

    @Override
    public Article get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ArticleMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.ArticleMapper.delete", id) > 0;
    }

    @Override
    public boolean update(Article article) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.ArticleMapper.update", article) > 0;
    }

    @Override
    public List<Article> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Article> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Article> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<Article> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ArticleMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ArticleMapper.count4page", param);
        Page<Article> page = new Page<Article>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<Article> page(ArticleQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("classifyId", query.getClassifyId());
        List<Article> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ArticleMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ArticleMapper.count4query", param);
        Page<Article> page = new Page<Article>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<Article> listAll() {

        List<Article> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ArticleMapper.listAll");
        return list;
    }

    @Override
    public Page<Article> page(Long classifyId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("classifyId", classifyId);
        List<Article> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ArticleMapper.ClassifyIdPage", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ArticleMapper.ClassifyIdPageCount", param);
        Page<Article> page = new Page<Article>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Long getBySort(long classifyId, int sort, int i) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("classifyId", classifyId);
        param.put("sort", sort);
        param.put("type", i);
        List<Article> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ArticleMapper.getBySort", param);
        Long id = null;
        for (Article a : list) {
            id = a.getId();
        }
        return id;
    }
}
