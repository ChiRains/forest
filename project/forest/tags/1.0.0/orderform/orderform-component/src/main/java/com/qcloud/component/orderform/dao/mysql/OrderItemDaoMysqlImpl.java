package com.qcloud.component.orderform.dao.mysql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.OrderItemDao;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.model.query.OrderItemQuery;
import com.qcloud.component.orderform.util.OrderTableSplitUtil;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class OrderItemDaoMysqlImpl implements OrderItemDao {

    @Resource(name = "sqlOperator-orderform")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(OrderItem orderItem, Date time) {

        Map<String, Object> param = BeanUtils.transBean2Map(orderItem);
        param.put("table_name", getTableName(time));
        return sqlOperator.insert("com.qcloud.component.orderform.dao.mysql.mapper.OrderItemMapper.insert", param) == 1;
    }

    @Override
    public OrderItem get(Long id, Date time) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("table_name", getTableName(time));
        return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.OrderItemMapper.get", map);
    }

    @Override
    public boolean update(OrderItem orderItem, Date time) {

        Map<String, Object> param = BeanUtils.transBean2Map(orderItem);
        param.put("table_name", getTableName(time));
        return sqlOperator.update("com.qcloud.component.orderform.dao.mysql.mapper.OrderItemMapper.update", param) > 0;
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public Page<OrderItem> page(OrderItemQuery query, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public List<OrderItem> listByCollectOrder(Long collectOrderId, Date time) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(time));
        param.put("collectOrderId", collectOrderId);
        return sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.OrderItemMapper.listByCollectOrder", param);
    }

    @Override
    public List<OrderItem> listBySubOrder(Long subOrderId, Date time) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(time));
        param.put("subOrderId", subOrderId);
        return sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.OrderItemMapper.listBySubOrder", param);
    }

    private String getTableName(Date time) {

        return OrderTableSplitUtil.getTableSplitName("orderform_order_item", time);
    }
}
