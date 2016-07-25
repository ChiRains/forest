package com.qcloud.component.my.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyAfterSaleDao;
import com.qcloud.component.my.model.MyAfterSale;
import com.qcloud.component.my.model.query.MyAfterSaleQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class MyAfterSaleDaoMysqlImpl implements MyAfterSaleDao {

    @Resource(name = "sqlOperator-my")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MyAfterSale myAfterSale) {

        return sqlOperator.insert("com.qcloud.component.my.dao.mysql.mapper.MyAfterSaleMapper.insert", myAfterSale) == 1;
    }

    @Override
    public MyAfterSale get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyAfterSaleMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.my.dao.mysql.mapper.MyAfterSaleMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MyAfterSale myAfterSale) {

        return sqlOperator.update("com.qcloud.component.my.dao.mysql.mapper.MyAfterSaleMapper.update", myAfterSale) > 0;
    }

    @Override
    public List<MyAfterSale> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyAfterSale> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyAfterSale> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyAfterSale> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyAfterSaleMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyAfterSaleMapper.count4page", param);
        Page<MyAfterSale> page = new Page<MyAfterSale>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MyAfterSale> page(MyAfterSaleQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyAfterSale> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyAfterSaleMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyAfterSaleMapper.count4query", param);
        Page<MyAfterSale> page = new Page<MyAfterSale>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MyAfterSale> listAll() {

        List<MyAfterSale> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyAfterSaleMapper.listAll");
        return list;
    }

    @Override
    public List<MyAfterSale> listByUser(long userId, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("userId", userId);
        List<MyAfterSale> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyAfterSaleMapper.listByUser", param);
        return list;
    }

    @Override
    public List<MyAfterSale> listByUserAndSubOrder(long userId, long subOrderId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("subOrderId", subOrderId);
        List<MyAfterSale> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyAfterSaleMapper.listByUserAndSubOrder", param);
        return list;
    }

    @Override
    public List<MyAfterSale> listByUserAndOrder(long userId, long orderId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("orderId", orderId);
        List<MyAfterSale> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyAfterSaleMapper.listByUserAndOrder", param);
        return list;
    }

    @Override
    public int stat(long userId) {

        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyAfterSaleMapper.stat", userId);
        return total;
    }

    @Override
    public int countByUser(long userId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyAfterSaleMapper.listByUser", param);
        return total;
    }
}
