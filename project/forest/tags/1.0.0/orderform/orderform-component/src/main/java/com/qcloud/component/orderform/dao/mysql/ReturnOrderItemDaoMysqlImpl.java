package com.qcloud.component.orderform.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.orderform.dao.ReturnOrderItemDao;
import com.qcloud.component.orderform.model.ReturnOrderItem;
import com.qcloud.component.orderform.model.query.ReturnOrderItemQuery;

@Repository
public class ReturnOrderItemDaoMysqlImpl implements ReturnOrderItemDao {

    @Resource(name = "sqlOperator-orderform")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(ReturnOrderItem returnOrderItem) {

        return sqlOperator.insert("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemMapper.insert", returnOrderItem) == 1;
    }

    @Override
    public ReturnOrderItem get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemMapper.delete", id) > 0;
    }

    @Override
    public boolean update(ReturnOrderItem returnOrderItem) {

        return sqlOperator.update("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemMapper.update", returnOrderItem) > 0;
    }

    @Override
    public List<ReturnOrderItem> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ReturnOrderItem> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ReturnOrderItem> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<ReturnOrderItem> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemMapper.count4page", param);
        Page<ReturnOrderItem> page = new Page<ReturnOrderItem>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<ReturnOrderItem> page(ReturnOrderItemQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<ReturnOrderItem> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemMapper.count4query", param);
        Page<ReturnOrderItem> page = new Page<ReturnOrderItem>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<ReturnOrderItem> listAll() {

        List<ReturnOrderItem> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemMapper.listAll");
        return list;
    }

    @Override
    public List<ReturnOrderItem> listByReturn(Long returnId) {

        List<ReturnOrderItem> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderItemMapper.listByReturn", returnId);
        return list;
    }
}
