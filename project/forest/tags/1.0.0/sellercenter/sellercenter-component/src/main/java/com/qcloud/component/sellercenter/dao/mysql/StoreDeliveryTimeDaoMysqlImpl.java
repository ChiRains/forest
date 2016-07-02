package com.qcloud.component.sellercenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.sellercenter.dao.StoreDeliveryTimeDao;
import com.qcloud.component.sellercenter.model.StoreDeliveryTime;
import com.qcloud.component.sellercenter.model.query.StoreDeliveryTimeQuery;

@Repository
public class StoreDeliveryTimeDaoMysqlImpl implements StoreDeliveryTimeDao {

    @Resource(name = "sqlOperator-sellercenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(StoreDeliveryTime storeDeliveryTime) {

        return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryTimeMapper.insert", storeDeliveryTime) == 1;
    }

    @Override
    public StoreDeliveryTime get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryTimeMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryTimeMapper.delete", id) > 0;
    }

    @Override
    public boolean update(StoreDeliveryTime storeDeliveryTime) {

        return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryTimeMapper.update", storeDeliveryTime) > 0;
    }

    @Override
    public List<StoreDeliveryTime> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, StoreDeliveryTime> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<StoreDeliveryTime> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<StoreDeliveryTime> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryTimeMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryTimeMapper.count4page", param);
        Page<StoreDeliveryTime> page = new Page<StoreDeliveryTime>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<StoreDeliveryTime> page(StoreDeliveryTimeQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<StoreDeliveryTime> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryTimeMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryTimeMapper.count4query", param);
        Page<StoreDeliveryTime> page = new Page<StoreDeliveryTime>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<StoreDeliveryTime> listAll() {

        List<StoreDeliveryTime> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryTimeMapper.listAll");
        return list;
    }

    @Override
    public StoreDeliveryTime getByStore(Long storeId) {

        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryTimeMapper.getByStore", storeId);
    }
}
