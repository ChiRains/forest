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
import com.qcloud.component.sellercenter.dao.StoreDeliveryRangeDao;
import com.qcloud.component.sellercenter.model.StoreDeliveryRange;
import com.qcloud.component.sellercenter.model.query.StoreDeliveryRangeQuery;

@Repository
public class StoreDeliveryRangeDaoMysqlImpl implements StoreDeliveryRangeDao {

    @Resource(name = "sqlOperator-sellercenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(StoreDeliveryRange storeDeliveryRange) {

        return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryRangeMapper.insert", storeDeliveryRange) == 1;
    }

    @Override
    public StoreDeliveryRange get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryRangeMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryRangeMapper.delete", id) > 0;
    }

    @Override
    public boolean update(StoreDeliveryRange storeDeliveryRange) {

        return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryRangeMapper.update", storeDeliveryRange) > 0;
    }

    @Override
    public List<StoreDeliveryRange> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, StoreDeliveryRange> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<StoreDeliveryRange> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<StoreDeliveryRange> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryRangeMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryRangeMapper.count4page", param);
        Page<StoreDeliveryRange> page = new Page<StoreDeliveryRange>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<StoreDeliveryRange> page(StoreDeliveryRangeQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<StoreDeliveryRange> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryRangeMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryRangeMapper.count4query", param);
        Page<StoreDeliveryRange> page = new Page<StoreDeliveryRange>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<StoreDeliveryRange> listAll() {

        List<StoreDeliveryRange> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryRangeMapper.listAll");
        return list;
    }

    @Override
    public StoreDeliveryRange getByStore(Long storeId) {

        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreDeliveryRangeMapper.getByStore", storeId);
    }
}
