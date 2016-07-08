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
import com.qcloud.project.forest.dao.ArticleEvaluationDao;
import com.qcloud.project.forest.model.ArticleEvaluation;
import com.qcloud.project.forest.model.query.ArticleEvaluationQuery;

@Repository
public class ArticleEvaluationDaoMysqlImpl implements ArticleEvaluationDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(ArticleEvaluation articleEvaluation) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.ArticleEvaluationMapper.insert", articleEvaluation) == 1;
    }

    @Override
    public ArticleEvaluation get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ArticleEvaluationMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.ArticleEvaluationMapper.delete", id) > 0;
    }

    @Override
    public boolean update(ArticleEvaluation articleEvaluation) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.ArticleEvaluationMapper.update", articleEvaluation) > 0;
    }

    @Override
    public List<ArticleEvaluation> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ArticleEvaluation> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<ArticleEvaluation> listByArticleId(Long articleId) {

        throw new NotImplementedException();
    }

    public List<ArticleEvaluation> listByUserId(Long userId) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ArticleEvaluation> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<ArticleEvaluation> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ArticleEvaluationMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ArticleEvaluationMapper.count4page", param);
        Page<ArticleEvaluation> page = new Page<ArticleEvaluation>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<ArticleEvaluation> page(ArticleEvaluationQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("articleId", query.getArticleId());
        param.put("state", query.getState());
        param.put("keyWord", "%" + query.getKeyWord() + "%");
        List<ArticleEvaluation> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ArticleEvaluationMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ArticleEvaluationMapper.count4query", param);
        Page<ArticleEvaluation> page = new Page<ArticleEvaluation>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<ArticleEvaluation> listAll() {

        List<ArticleEvaluation> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ArticleEvaluationMapper.listAll");
        return list;
    }
}
