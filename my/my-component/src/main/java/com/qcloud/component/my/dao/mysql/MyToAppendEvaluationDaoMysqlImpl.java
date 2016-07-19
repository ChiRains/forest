package com.qcloud.component.my.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.my.dao.MyToAppendEvaluationDao;
import com.qcloud.component.my.model.MyToAppendEvaluation;
import com.qcloud.component.my.model.MyToEvaluation;
import com.qcloud.component.my.model.query.MyToAppendEvaluationQuery;

@Repository
public class MyToAppendEvaluationDaoMysqlImpl implements MyToAppendEvaluationDao {

    @Resource(name = "sqlOperator-my")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MyToAppendEvaluation myToAppendEvaluation) {

        return sqlOperator.insert("com.qcloud.component.my.dao.mysql.mapper.MyToAppendEvaluationMapper.insert", myToAppendEvaluation) == 1;
    }

    @Override
    public MyToAppendEvaluation get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyToAppendEvaluationMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.my.dao.mysql.mapper.MyToAppendEvaluationMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MyToAppendEvaluation myToAppendEvaluation) {

        return sqlOperator.update("com.qcloud.component.my.dao.mysql.mapper.MyToAppendEvaluationMapper.update", myToAppendEvaluation) > 0;
    }

    @Override
    public List<MyToAppendEvaluation> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyToAppendEvaluation> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<MyToAppendEvaluation> listByOrderId(Long orderId) {

        throw new NotImplementedException();
    }

    public List<MyToAppendEvaluation> listByUserId(Long userId) {

        throw new NotImplementedException();
    }

    public List<MyToAppendEvaluation> listByUnifiedMerchandiseId(Long unifiedMerchandiseId) {

        throw new NotImplementedException();
    }

    public List<MyToAppendEvaluation> listBySubOrderId(Long subOrderId) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyToAppendEvaluation> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyToAppendEvaluation> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyToAppendEvaluationMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyToAppendEvaluationMapper.count4page", param);
        Page<MyToAppendEvaluation> page = new Page<MyToAppendEvaluation>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MyToAppendEvaluation> page(MyToAppendEvaluationQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyToAppendEvaluation> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyToAppendEvaluationMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyToAppendEvaluationMapper.count4query", param);
        Page<MyToAppendEvaluation> page = new Page<MyToAppendEvaluation>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MyToAppendEvaluation> listAll() {

        List<MyToAppendEvaluation> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyToAppendEvaluationMapper.listAll");
        return list;
    }

    @Override
    public List<MyToAppendEvaluation> listAppendEvaluation(Long userId, Long orderId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("orderId", orderId);
        List<MyToAppendEvaluation> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyToAppendEvaluationMapper.listAppendEvaluation", param);
        return list;
    }
}
