package com.qcloud.component.orderform.dao.mysql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.CollectOrderDao;
import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.orderform.model.query.CollectOrderQuery;
import com.qcloud.component.orderform.util.OrderTableSplitUtil;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.pirates.util.DateUtil;

@Repository
public class CollectOrderDaoMysqlImpl implements CollectOrderDao {

    @Resource(name = "sqlOperator-orderform")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(CollectOrder collectOrder) {

        Map<String, Object> param = BeanUtils.transBean2Map(collectOrder);
        param.put("table_name", getTableName(collectOrder.getTime()));
        return sqlOperator.insert("com.qcloud.component.orderform.dao.mysql.mapper.CollectOrderMapper.insert", param) == 1;
    }

    @Override
    public CollectOrder get(Long id, Date time) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("table_name", getTableName(time));
        return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.CollectOrderMapper.get", map);
    }

    @Override
    public boolean update(CollectOrder collectOrder) {

        Map<String, Object> param = BeanUtils.transBean2Map(collectOrder);
        param.put("table_name", getTableName(collectOrder.getTime()));
        return sqlOperator.update("com.qcloud.component.orderform.dao.mysql.mapper.CollectOrderMapper.update", param) > 0;
    }

    private String getTableName(Date time) {

        return OrderTableSplitUtil.getTableSplitName("orderform_collect_order", time);
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public Page<CollectOrder> page(CollectOrderQuery query, int start, int size) {

        Map<String, Object> param = BeanUtils.transBean2Map(query);
        param.put("start", start);
        param.put("count", size);
        param.put("state", query.getState());
        param.put("startDate", query.getStartOrderDate());
        param.put("endDate", query.getEndOrderDate());
        param.put("table_name", getTableName(query.getEndOrderDate()));
        param.put("orderNumber", query.getOrderNumber());
        List<CollectOrder> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.CollectOrderMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.CollectOrderMapper.count4query", param);
        Page<CollectOrder> page = new Page<CollectOrder>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Date[] getDatesByLatelyMinutes(int latelyMinutes) {

        Date date = new Date();
        Date limitDate = DateUtil.addTime(date, -latelyMinutes);
        Date yesterdayLimitDate = DateUtil.addTime(limitDate, -(24 * 60 + latelyMinutes));
        String current = getTableName(limitDate);
        String last = getTableName(yesterdayLimitDate);
        if (current.equals(last)) {
            return new Date[] { limitDate};
        } else {
            return new Date[] { limitDate, yesterdayLimitDate};
        }
    }

    @Override
    public CollectOrder get(String orderNumber) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", 0);
        param.put("count", 1);
        param.put("orderNumber", orderNumber);
        param.put("table_name", getTableName(orderNumber));
        return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.CollectOrderMapper.getByOrderNumber", param);
    }

    private String getTableName(String orderNumber) {

        return OrderTableSplitUtil.getTableSplitName("orderform_collect_order", orderNumber);
    }

    @Override
    public List<CollectOrder> list4ExpireState(Date tableDate, int state, int start, int size) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", size);
        param.put("state", state);
        param.put("now", new Date());
        param.put("table_name", getTableName(tableDate));
        List<CollectOrder> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.CollectOrderMapper.list4ExpireState", param);
        return list;
    }
}
