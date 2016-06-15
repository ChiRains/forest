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
import com.qcloud.project.forest.dao.ForestOrderDao;
import com.qcloud.project.forest.model.ForestOrder;
import com.qcloud.project.forest.model.query.ForestOrderQuery;

@Repository
public class ForestOrderDaoMysqlImpl implements ForestOrderDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(ForestOrder forestOrder) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.ForestOrderMapper.insert", forestOrder) == 1;
    }

    @Override
    public ForestOrder get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ForestOrderMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.ForestOrderMapper.delete", id) > 0;
    }

    @Override
    public boolean update(ForestOrder forestOrder) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.ForestOrderMapper.update", forestOrder) > 0;
    }

    @Override
    public List<ForestOrder> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ForestOrder> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ForestOrder> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<ForestOrder> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ForestOrderMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ForestOrderMapper.count4page", param);
        Page<ForestOrder> page = new Page<ForestOrder>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<ForestOrder> page(ForestOrderQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<ForestOrder> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ForestOrderMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ForestOrderMapper.count4query", param);
        Page<ForestOrder> page = new Page<ForestOrder>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<ForestOrder> listAll() {

        List<ForestOrder> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ForestOrderMapper.listAll");
        return list;
    }

    @Override
    public ForestOrder getByOrder(long orderId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("orderId", orderId);
        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ForestOrderMapper.getByOrder", param);
    }
}
