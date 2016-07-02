package com.qcloud.component.warehouse.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.warehouse.dao.StockStateDao;
import com.qcloud.component.warehouse.model.StockState;
import com.qcloud.component.warehouse.model.query.StockStateQuery;

@Repository
public class StockStateDaoMysqlImpl implements StockStateDao {

    @Resource(name = "sqlOperator-warehouse")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(StockState stockState) {

        return sqlOperator.insert("com.qcloud.component.warehouse.dao.mysql.mapper.StockStateMapper.insert", stockState) == 1;
    }

    @Override
    public StockState get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.warehouse.dao.mysql.mapper.StockStateMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.warehouse.dao.mysql.mapper.StockStateMapper.delete", id) > 0;
    }

    @Override
    public boolean update(StockState stockState) {

        return sqlOperator.update("com.qcloud.component.warehouse.dao.mysql.mapper.StockStateMapper.update", stockState) > 0;
    }

    @Override
    public List<StockState> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, StockState> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<StockState> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<StockState> list = sqlOperator.selectList("com.qcloud.component.warehouse.dao.mysql.mapper.StockStateMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.warehouse.dao.mysql.mapper.StockStateMapper.count4page", param);
        Page<StockState> page = new Page<StockState>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<StockState> page(StockStateQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<StockState> list = sqlOperator.selectList("com.qcloud.component.warehouse.dao.mysql.mapper.StockStateMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.warehouse.dao.mysql.mapper.StockStateMapper.count4query", param);
        Page<StockState> page = new Page<StockState>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<StockState> listAll() {

        List<StockState> list = sqlOperator.selectList("com.qcloud.component.warehouse.dao.mysql.mapper.StockStateMapper.listAll");
        return list;
    }

    @Override
    public List<StockState> listAll(Map<String, Object> map) {

        List<StockState> list = sqlOperator.selectList("com.qcloud.component.warehouse.dao.mysql.mapper.StockStateMapper.listAllByMap", map);
        return list;
    }
}
