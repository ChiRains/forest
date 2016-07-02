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
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.component.orderform.dao.ReturnOrderDao;
import com.qcloud.component.orderform.model.ReturnOrder;
import com.qcloud.component.orderform.model.query.ReturnOrderQuery;

@Repository
public class ReturnOrderDaoMysqlImpl implements ReturnOrderDao {

    @Resource(name = "sqlOperator-orderform")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(ReturnOrder returnOrder) {

        return sqlOperator.insert("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderMapper.insert", returnOrder) == 1;
    }

    @Override
    public ReturnOrder get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderMapper.delete", id) > 0;
    }

    @Override
    public boolean update(ReturnOrder returnOrder) {

        return sqlOperator.update("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderMapper.update", returnOrder) > 0;
    }

    @Override
    public List<ReturnOrder> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ReturnOrder> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ReturnOrder> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<ReturnOrder> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderMapper.count4page", param);
        Page<ReturnOrder> page = new Page<ReturnOrder>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<ReturnOrder> page(ReturnOrderQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("merchantId", query.getMerchantId());
        param.put("storeId", query.getStoreId());
        param.put("state", query.getState());
        param.put("returnNumber", StringUtil.emptyToNull(query.getReturnNumber()));
        List<ReturnOrder> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderMapper.count4query", param);
        Page<ReturnOrder> page = new Page<ReturnOrder>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<ReturnOrder> listAll() {

        List<ReturnOrder> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderMapper.listAll");
        return list;
    }

    @Override
    public List<ReturnOrder> listBySubOrder(Long subOrderId) {

        List<ReturnOrder> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderMapper.listBySubOrder", subOrderId);
        return list;
    }

    @Override
    public List<ReturnOrder> list4ExpireState(int state, int start, int size) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", size);
        param.put("state", state);
        List<ReturnOrder> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.ReturnOrderMapper.list4ExpireState", param);
        return list;
    }
}
