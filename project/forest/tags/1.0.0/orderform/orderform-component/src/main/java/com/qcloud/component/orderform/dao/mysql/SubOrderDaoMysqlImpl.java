package com.qcloud.component.orderform.dao.mysql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.SubOrderDao;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.model.query.SubOrderQuery;
import com.qcloud.component.orderform.util.OrderTableSplitUtil;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.pirates.util.DateUtil;

@Repository
public class SubOrderDaoMysqlImpl implements SubOrderDao {

    @Resource(name = "sqlOperator-orderform")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(SubOrder subOrder, Date time) {

        Map<String, Object> param = BeanUtils.transBean2Map(subOrder);
        param.put("table_name", getTableName(time));
        return sqlOperator.insert("com.qcloud.component.orderform.dao.mysql.mapper.SubOrderMapper.insert", param) == 1;
    }

    @Override
    public SubOrder get(Long id, Date time) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("table_name", getTableName(time));
        return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.SubOrderMapper.get", map);
    }

    @Override
    public boolean update(SubOrder subOrder, Date time) {

        Map<String, Object> param = BeanUtils.transBean2Map(subOrder);
        param.put("table_name", getTableName(time));
        return sqlOperator.update("com.qcloud.component.orderform.dao.mysql.mapper.SubOrderMapper.update", param) > 0;
    }

    private String getTableName(Date time) {

        return OrderTableSplitUtil.getTableSplitName("orderform_sub_order", time);
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public Page<SubOrder> page(SubOrderQuery query, int start, int size) {

        Map<String, Object> param = BeanUtils.transBean2Map(query);
        param.put("start", start);
        param.put("count", size);
        param.put("state", query.getState());
        param.put("startDate", query.getStartOrderDate());
        param.put("endDate", query.getEndOrderDate());
        param.put("table_name", getTableName(query.getEndOrderDate()));
        List<SubOrder> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.SubOrderMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.SubOrderMapper.count4query", param);
        Page<SubOrder> page = new Page<SubOrder>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<SubOrder> listByCollectOrder(Long collectOrderId, Date time) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(time));
        param.put("collectOrderId", collectOrderId);
        return sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.SubOrderMapper.listByCollectOrder", param);
    }

    private String getTableName(String orderNumber) {

        return OrderTableSplitUtil.getTableSplitName("orderform_collect_order", orderNumber);
    }

    @Override
    public SubOrder get(String orderNumber) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", 0);
        param.put("count", 1);
        param.put("orderNumber", orderNumber);
        param.put("table_name", getTableName(orderNumber));
        return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.SubOrderMapper.getByOrderNumber", param);
    }

    private Log logger = LogFactory.getLog(getClass());

    @Override
    public List<SubOrder> listByMerchantAndDay(Long merchantId, Date time) {
        logger.info(time);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(time));
        param.put("merchantId", merchantId);
        param.put("date", DateUtil.date2String(time, DateUtil.DATE_FORMAT_STRING));
         return sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.SubOrderMapper.listByMerchantAndDay", param);
    }
}
