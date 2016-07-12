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
import com.qcloud.project.forest.dao.ExpressQueryHistoryDao;
import com.qcloud.project.forest.model.ExpressQueryHistory;
import com.qcloud.project.forest.model.query.ExpressQueryHistoryQuery;

@Repository
public class ExpressQueryHistoryDaoMysqlImpl implements ExpressQueryHistoryDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(ExpressQueryHistory expressQueryHistory) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.ExpressQueryHistoryMapper.insert", expressQueryHistory) == 1;
    }

    @Override
    public ExpressQueryHistory get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ExpressQueryHistoryMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.ExpressQueryHistoryMapper.delete", id) > 0;
    }

    @Override
    public boolean update(ExpressQueryHistory expressQueryHistory) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.ExpressQueryHistoryMapper.update", expressQueryHistory) > 0;
    }

    @Override
    public List<ExpressQueryHistory> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ExpressQueryHistory> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<ExpressQueryHistory> listByUserId(String userId) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ExpressQueryHistory> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<ExpressQueryHistory> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ExpressQueryHistoryMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ExpressQueryHistoryMapper.count4page", param);
        Page<ExpressQueryHistory> page = new Page<ExpressQueryHistory>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<ExpressQueryHistory> page(ExpressQueryHistoryQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("userId", query.getUserId());
        List<ExpressQueryHistory> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ExpressQueryHistoryMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ExpressQueryHistoryMapper.count4query", param);
        Page<ExpressQueryHistory> page = new Page<ExpressQueryHistory>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<ExpressQueryHistory> listAll() {

        List<ExpressQueryHistory> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ExpressQueryHistoryMapper.listAll");
        return list;
    }

    @Override
    public ExpressQueryHistory getByUserIdAndExpressNum(ExpressQueryHistoryQuery query) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("expressNum", query.getExpressNum());
        param.put("userId", query.getUserId());
        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ExpressQueryHistoryMapper.getByUserIdAndExpressNum", param);
    }
}
