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
import com.qcloud.project.forest.dao.IntegralOrderDao;
import com.qcloud.project.forest.model.IntegralOrder;
import com.qcloud.project.forest.model.query.IntegralOrderQuery;

@Repository
public class IntegralOrderDaoMysqlImpl implements IntegralOrderDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(IntegralOrder integralOrder) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.IntegralOrderMapper.insert", integralOrder) == 1;
    }

    @Override
    public IntegralOrder get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.IntegralOrderMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.IntegralOrderMapper.delete", id) > 0;
    }

    @Override
    public boolean update(IntegralOrder integralOrder) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.IntegralOrderMapper.update", integralOrder) > 0;
    }

    @Override
    public List<IntegralOrder> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, IntegralOrder> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<IntegralOrder> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<IntegralOrder> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.IntegralOrderMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.IntegralOrderMapper.count4page", param);
        Page<IntegralOrder> page = new Page<IntegralOrder>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<IntegralOrder> page(IntegralOrderQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("orderNumber", query.getKeyword());
        List<IntegralOrder> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.IntegralOrderMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.IntegralOrderMapper.count4query", param);
        Page<IntegralOrder> page = new Page<IntegralOrder>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<IntegralOrder> listAll() {

        List<IntegralOrder> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.IntegralOrderMapper.listAll");
        return list;
    }

    @Override
    public List<IntegralOrder> listByUser(long userId, int type, int start, int size) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("type", type);
        param.put("start", start);
        param.put("count", size);
        List<IntegralOrder> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.IntegralOrderMapper.listByUser", param);
        return list;
    }

    @Override
    public int countByUser(long userId, int type) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("type", type);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.IntegralOrderMapper.countByUser", param);
        return total;
    }

    @Override
    public IntegralOrder getByOrder(long orderId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("orderId", orderId);
        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.IntegralOrderMapper.getByOrder", param);
    }
}
