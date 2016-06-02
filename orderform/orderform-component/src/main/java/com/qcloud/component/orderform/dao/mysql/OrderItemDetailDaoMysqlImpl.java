package com.qcloud.component.orderform.dao.mysql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.OrderItemDetailDao;
import com.qcloud.component.orderform.model.OrderItemDetail;
import com.qcloud.component.orderform.model.query.OrderItemDetailQuery;
import com.qcloud.component.orderform.util.OrderTableSplitUtil;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class OrderItemDetailDaoMysqlImpl implements OrderItemDetailDao {

    @Resource(name = "sqlOperator-orderform")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(OrderItemDetail orderItemDetail, Date time) {

        Map<String, Object> param = BeanUtils.transBean2Map(orderItemDetail);
        param.put("table_name", getTableName(time));
        return sqlOperator.insert("com.qcloud.component.orderform.dao.mysql.mapper.OrderItemDetailMapper.insert", param) == 1;
    }

    @Override
    public OrderItemDetail get(Long id, Date time) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("table_name", getTableName(time));
        return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.OrderItemDetailMapper.get", map);
    }

    @Override
    public boolean update(OrderItemDetail orderItemDetail, Date time) {

        Map<String, Object> param = BeanUtils.transBean2Map(orderItemDetail);
        param.put("table_name", getTableName(time));
        return sqlOperator.update("com.qcloud.component.orderform.dao.mysql.mapper.OrderItemDetailMapper.update", param) > 0;
    }

    private String getTableName(Date time) {

        return OrderTableSplitUtil.getTableSplitName("orderform_order_item_detail", time);
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public Page<OrderItemDetail> page(OrderItemDetailQuery query, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public List<OrderItemDetail> listByCollectOrder(Long collectOrderId, Date time) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(time));
        param.put("collectOrderId", collectOrderId);
        return sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.OrderItemDetailMapper.listByCollectOrder", param);
    }

    @Override
    public List<OrderItemDetail> listBySubOrder(Long subOrderId, Date time) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(time));
        param.put("subOrderId", subOrderId);
        return sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.OrderItemDetailMapper.listBySubOrder", param);
    }

    @Override
    public List<OrderItemDetail> listByOrderItem(Long orderItemId, Date time) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(time));
        param.put("orderItemId", orderItemId);
        return sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.OrderItemDetailMapper.listByOrderItem", param);
    }

    @Override
    public List<OrderItemDetail> listOrderItemDetail(Long orderId, Date time) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(time));
        param.put("orderId", orderId);
        return sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.OrderItemDetailMapper.listOrderItemDetail", param);
    }

    @Override
    public List<OrderItemDetail> listItemDetailByItemId(Long orderItemId, Date time) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(time));
        param.put("orderItemId", orderItemId);
        return sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.OrderItemDetailMapper.listItemDetailByItemId", param);
    }
}
