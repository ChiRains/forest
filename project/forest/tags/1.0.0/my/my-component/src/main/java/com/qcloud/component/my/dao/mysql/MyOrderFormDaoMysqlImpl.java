package com.qcloud.component.my.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyOrderFormDao;
import com.qcloud.component.my.model.MyOrderForm;
import com.qcloud.component.my.model.query.MyOrderFormQuery;
import com.qcloud.component.template.core.util.string.StringUtils;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.util.TableSplitUtil;

@Repository
public class MyOrderFormDaoMysqlImpl implements MyOrderFormDao {

    @Resource(name = "sqlOperator-my")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MyOrderForm myOrderForm) {

        Map<String, Object> param = BeanUtils.transBean2Map(myOrderForm);
        param.put("table_name", getTableName(myOrderForm.getUserId()));
        return sqlOperator.insert("com.qcloud.component.my.dao.mysql.mapper.MyOrderFormMapper.insert", param) == 1;
    }

    @Override
    public MyOrderForm get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyOrderFormMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.my.dao.mysql.mapper.MyOrderFormMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MyOrderForm myOrderForm) {

        Map<String, Object> param = BeanUtils.transBean2Map(myOrderForm);
        param.put("table_name", getTableName(myOrderForm.getUserId()));
        return sqlOperator.update("com.qcloud.component.my.dao.mysql.mapper.MyOrderFormMapper.update", param) > 0;
    }

    @Override
    public List<MyOrderForm> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyOrderForm> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyOrderForm> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyOrderForm> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyOrderFormMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyOrderFormMapper.count4page", param);
        Page<MyOrderForm> page = new Page<MyOrderForm>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MyOrderForm> page(MyOrderFormQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyOrderForm> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyOrderFormMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyOrderFormMapper.count4query", param);
        Page<MyOrderForm> page = new Page<MyOrderForm>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MyOrderForm> listAll() {

        List<MyOrderForm> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyOrderFormMapper.listAll");
        return list;
    }

    @Override
    public List<MyOrderForm> list(MyOrderFormQuery query, long userId, int start, int count) {

        Map<String, Object> param = BeanUtils.transBean2Map(query);
        param.put("start", start);
        param.put("count", count);
        param.put("state", query.getState());
        param.put("startDate", query.getStartOrderDate());
        param.put("endDate", query.getEndOrderDate());
        param.put("userId", userId);
        param.put("table_name", getTableName(userId));
        String sqlWhereDefault = " 1=1 ";
        param.put("sqlwhere", sqlWhereDefault);
        Xml xml = XmlFactory.get("my-myOrderForm-SqlWherePart");
        if (xml != null && xml.getItemList().size() == 1) {
            String sqlwhere = xml.getItemList().get(0).getText();
            sqlwhere = StringUtil.nullToEmpty(sqlwhere).trim();
            sqlwhere = StringUtils.isEmpty(sqlwhere) ? sqlWhereDefault : sqlwhere;
            param.put("sqlwhere", sqlwhere);
        }
        List<MyOrderForm> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyOrderFormMapper.list", param);
        return list;
    }

    private String getTableName(long userId) {

        return TableSplitUtil.getTableSplitName(userId, "my_my_order_form", 100);
    }

    @Override
    public MyOrderForm getByOrder(long userId, long orderId, long subOrderId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(userId));
        param.put("userId", userId);
        param.put("orderId", orderId);
        param.put("subOrderId", subOrderId);
        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyOrderFormMapper.getByOrder", param);
    }

    @Override
    public List<MyOrderForm> listByOrder(long userId, long orderId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(userId));
        param.put("userId", userId);
        param.put("orderId", orderId);
        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyOrderFormMapper.listByOrder", param);
    }

    @Override
    public int statMyOrder(long userId, int state) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(userId));
        param.put("userId", userId);
        param.put("state", state);
        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyOrderFormMapper.statMyOrder", param);
    }

    @Override
    public int statMyMerchantOrder(long userId, int state) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_name", getTableName(userId));
        param.put("userId", userId);
        param.put("state", state);
        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyOrderFormMapper.statMyMerchantOrder", param);
    }

    @Override
    public int count(MyOrderFormQuery query, long userId) {

        Map<String, Object> param = BeanUtils.transBean2Map(query);
        param.put("state", query.getState());
        param.put("startDate", query.getStartOrderDate());
        param.put("endDate", query.getEndOrderDate());
        param.put("userId", userId);
        param.put("table_name", getTableName(userId));
        String sqlWhereDefault = " 1=1 ";
        param.put("sqlwhere", sqlWhereDefault);
        Xml xml = XmlFactory.get("my-myOrderForm-SqlWherePart");
        if (xml != null && xml.getItemList().size() == 1) {
            String sqlwhere = xml.getItemList().get(0).getText();
            sqlwhere = StringUtil.nullToEmpty(sqlwhere).trim();
            sqlwhere = StringUtils.isEmpty(sqlwhere) ? sqlWhereDefault : sqlwhere;
            param.put("sqlwhere", sqlwhere);
        }
        int size = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyOrderFormMapper.count", param);
        return size;
    }
}
