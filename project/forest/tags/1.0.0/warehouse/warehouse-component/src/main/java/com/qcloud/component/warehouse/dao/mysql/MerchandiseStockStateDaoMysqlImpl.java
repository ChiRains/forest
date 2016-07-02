package com.qcloud.component.warehouse.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.warehouse.dao.MerchandiseStockStateDao;
import com.qcloud.component.warehouse.model.MerchandiseStockState;
import com.qcloud.component.warehouse.model.query.MerchandiseStockStateQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class MerchandiseStockStateDaoMysqlImpl implements MerchandiseStockStateDao {

    @Resource(name = "sqlOperator-warehouse")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MerchandiseStockState merchandiseStockState) {

        return sqlOperator.insert("com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockStateMapper.insert", merchandiseStockState) == 1;
    }

    @Override
    public MerchandiseStockState get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockStateMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockStateMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MerchandiseStockState merchandiseStockState) {

        return sqlOperator.update("com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockStateMapper.update", merchandiseStockState) > 0;
    }

    @Override
    public List<MerchandiseStockState> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseStockState> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseStockState> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchandiseStockState> list = sqlOperator.selectList("com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockStateMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockStateMapper.count4page", param);
        Page<MerchandiseStockState> page = new Page<MerchandiseStockState>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MerchandiseStockState> page(MerchandiseStockStateQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchandiseStockState> list = sqlOperator.selectList("com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockStateMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockStateMapper.count4query", param);
        Page<MerchandiseStockState> page = new Page<MerchandiseStockState>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MerchandiseStockState> listAll() {

        List<MerchandiseStockState> list = sqlOperator.selectList("com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockStateMapper.listAll");
        return list;
    }

    @Override
    public List<MerchandiseStockState> listAll(Map<String, Object> map) {

        List<MerchandiseStockState> list = sqlOperator.selectList("com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockStateMapper.listByMap", map);
        return list;
    }
}
