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
import com.qcloud.component.orderform.dao.RefundOrderItemDao;
import com.qcloud.component.orderform.model.RefundOrderItem;
import com.qcloud.component.orderform.model.query.RefundOrderItemQuery;

@Repository
public class RefundOrderItemDaoMysqlImpl implements RefundOrderItemDao {

    @Resource(name = "sqlOperator-orderform")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(RefundOrderItem refundOrderItem) {

        return sqlOperator.insert("com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderItemMapper.insert", refundOrderItem) == 1;
    }

    @Override
    public RefundOrderItem get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderItemMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderItemMapper.delete", id) > 0;
    }

    @Override
    public boolean update(RefundOrderItem refundOrderItem) {

        return sqlOperator.update("com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderItemMapper.update", refundOrderItem) > 0;
    }

    @Override
    public List<RefundOrderItem> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, RefundOrderItem> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<RefundOrderItem> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<RefundOrderItem> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderItemMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderItemMapper.count4page", param);
        Page<RefundOrderItem> page = new Page<RefundOrderItem>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<RefundOrderItem> page(RefundOrderItemQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<RefundOrderItem> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderItemMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderItemMapper.count4query", param);
        Page<RefundOrderItem> page = new Page<RefundOrderItem>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<RefundOrderItem> listAll() {

        List<RefundOrderItem> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderItemMapper.listAll");
        return list;
    }

    @Override
    public List<RefundOrderItem> listByRefund(Long refundId) {

        List<RefundOrderItem> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.RefundOrderItemMapper.listByRefund", refundId);
        return list;
    }
}
