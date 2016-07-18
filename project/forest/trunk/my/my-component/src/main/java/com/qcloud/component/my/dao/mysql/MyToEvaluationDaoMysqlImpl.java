package com.qcloud.component.my.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyToEvaluationDao;
import com.qcloud.component.my.model.MyToEvaluation;
import com.qcloud.component.my.model.query.MyToEvaluationQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class MyToEvaluationDaoMysqlImpl implements MyToEvaluationDao {

    @Resource(name = "sqlOperator-my")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MyToEvaluation myToEvaluation) {

        return sqlOperator.insert("com.qcloud.component.my.dao.mysql.mapper.MyToEvaluationMapper.insert", myToEvaluation) == 1;
    }

    @Override
    public MyToEvaluation get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyToEvaluationMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.my.dao.mysql.mapper.MyToEvaluationMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MyToEvaluation myToEvaluation) {

        return sqlOperator.update("com.qcloud.component.my.dao.mysql.mapper.MyToEvaluationMapper.update", myToEvaluation) > 0;
    }

    @Override
    public List<MyToEvaluation> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyToEvaluation> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyToEvaluation> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyToEvaluation> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyToEvaluationMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyToEvaluationMapper.count4page", param);
        Page<MyToEvaluation> page = new Page<MyToEvaluation>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MyToEvaluation> page(MyToEvaluationQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyToEvaluation> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyToEvaluationMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyToEvaluationMapper.count4query", param);
        Page<MyToEvaluation> page = new Page<MyToEvaluation>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MyToEvaluation> listAll() {

        List<MyToEvaluation> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyToEvaluationMapper.listAll");
        return list;
    }

    @Override
    public List<MyToEvaluation> listByUser(long userId, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        //
        param.put("userId", userId);
        //
        List<MyToEvaluation> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyToEvaluationMapper.listByUser", param);
        return list;
    }

    @Override
    public List<MyToEvaluation> listByUserAndOrder(long userId, long subOrderId, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        //
        param.put("userId", userId);
        param.put("subOrderId", subOrderId);
        //
        List<MyToEvaluation> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyToEvaluationMapper.listByUserAndOrder", param);
        return list;
    }

    @Override
    public int countByUserAndOrder(long userId, long subOrderId) {

        Map<String, Object> param = new HashMap<String, Object>();
        //
        param.put("userId", userId);
        param.put("subOrderId", subOrderId);
        //
        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyToEvaluationMapper.countByUserAndOrder", param);
    }

    @Override
    public List<MyToEvaluation> listByUserAndOrderId(long userId, long orderId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("orderId", orderId);
        List<MyToEvaluation> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyToEvaluationMapper.listByUserAndOrderId", param);
        return list;
    }
}
