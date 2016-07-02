package com.qcloud.component.my.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyEvaluationDao;
import com.qcloud.component.my.model.MyEvaluation;
import com.qcloud.component.my.model.query.MyEvaluationQuery;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.TableSplitUtil;

@Repository
public class MyEvaluationDaoMysqlImpl implements MyEvaluationDao {

    @Resource(name = "sqlOperator-my")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MyEvaluation myEvaluation) {

        Map<String, Object> param = BeanUtils.transBean2Map(myEvaluation);
        param.put("table_name", getTableName(myEvaluation.getUserId()));
        return sqlOperator.insert("com.qcloud.component.my.dao.mysql.mapper.MyEvaluationMapper.insert", param) == 1;
    }

    @Override
    public MyEvaluation get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyEvaluationMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.my.dao.mysql.mapper.MyEvaluationMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MyEvaluation myEvaluation) {

        return sqlOperator.update("com.qcloud.component.my.dao.mysql.mapper.MyEvaluationMapper.update", myEvaluation) > 0;
    }

    @Override
    public List<MyEvaluation> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyEvaluation> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyEvaluation> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyEvaluation> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyEvaluationMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyEvaluationMapper.count4page", param);
        Page<MyEvaluation> page = new Page<MyEvaluation>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MyEvaluation> page(MyEvaluationQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("userId", query.getUserId());
        param.put("orderTime", DateUtil.date2String(query.getOrderTime(), DateUtil.DATE_FORMAT_STRING));
        param.put("table_name", getTableName(query.getUserId()));
        List<MyEvaluation> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyEvaluationMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyEvaluationMapper.count4query", param);
        Page<MyEvaluation> page = new Page<MyEvaluation>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MyEvaluation> listAll() {

        List<MyEvaluation> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyEvaluationMapper.listAll");
        return list;
    }

    private String getTableName(long userId) {

        return TableSplitUtil.getTableSplitName(userId, "my_my_evaluation", 100);
    }

    @Override
    public MyEvaluation getByOrderItemDetailId(long userId, long orderItemDetailId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("orderItemDetailId", orderItemDetailId);
        map.put("table_name", getTableName(userId));
        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyEvaluationMapper.getByOrderItemDetailId", map);
    }

//    @Override
//    public List<MyEvaluation> listByMap(Map<String, Object> map) {
//
//        if (!map.containsKey("userId")) {
//            throw new PersonalCenterException("缺少用户id");
//        }
//        map.put("table_name", getTableName((Long) map.get("userId")));
//        List<MyEvaluation> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyEvaluationMapper.listByMap");
//        return list;
//    }
}
