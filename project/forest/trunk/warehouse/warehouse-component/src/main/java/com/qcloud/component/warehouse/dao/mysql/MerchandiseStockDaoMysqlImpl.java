package com.qcloud.component.warehouse.dao.mysql;

import com.qcloud.component.warehouse.dao.MerchandiseStockDao;
import com.qcloud.component.warehouse.model.MerchandiseStock;
import com.qcloud.component.warehouse.model.query.MerchandiseStockQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class MerchandiseStockDaoMysqlImpl implements MerchandiseStockDao {

    @Resource(name = "sqlOperator-warehouse")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MerchandiseStock merchandiseStock) {
        return sqlOperator.insert("com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockMapper.insert", merchandiseStock) == 1;
    }

    @Override
    public MerchandiseStock get(Long id) {
        return sqlOperator.selectOne("com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {
        return sqlOperator.delete("com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MerchandiseStock merchandiseStock) {
        return sqlOperator.update("com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockMapper.update", merchandiseStock) > 0;
    }

    @Override
    public List<MerchandiseStock> list(List<Long> idList) {
        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseStock> map(Set<Long> idSet) {
        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseStock> page(int start, int count) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);

        List<MerchandiseStock> list = sqlOperator.selectList(
                "com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockMapper.list4page",
                param);
        int total = sqlOperator.selectOne(
                "com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockMapper.count4page",
                param);
        Page<MerchandiseStock> page = new Page<MerchandiseStock>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MerchandiseStock> page(MerchandiseStockQuery query, int start, int count) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);

        List<MerchandiseStock> list = sqlOperator.selectList(
                "com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockMapper.list4query",
                param);
        int total = sqlOperator.selectOne(
                "com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockMapper.count4query",
                param);
        Page<MerchandiseStock> page = new Page<MerchandiseStock>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MerchandiseStock> listAll() {
        List<MerchandiseStock> list = sqlOperator.selectList(
                "com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockMapper.listAll");
        return list;
    }

    @Override
    public MerchandiseStock get(HashMap where) {
        where.put("start", 0);
        where.put("count", 1);
        return sqlOperator.selectOne("com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockMapper.list", where);
    }

    @Override
    public List<MerchandiseStock> list(HashMap where) {
        return sqlOperator.selectList("com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockMapper.list", where);
    }

    @Override
    public Page<MerchandiseStock> page(HashMap where, int start, int size) {
        where.put("start", start);
        where.put("count", size);

        List<MerchandiseStock> list = sqlOperator.selectList(
                "com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockMapper.list",
                where);
        int total = sqlOperator.selectOne(
                "com.qcloud.component.warehouse.dao.mysql.mapper.MerchandiseStockMapper.count",
                where);
        Page<MerchandiseStock> merchandiseStockPage = new Page<MerchandiseStock>();
        merchandiseStockPage.setCount(total);
        merchandiseStockPage.setData(list);
        return merchandiseStockPage;
    }
}

