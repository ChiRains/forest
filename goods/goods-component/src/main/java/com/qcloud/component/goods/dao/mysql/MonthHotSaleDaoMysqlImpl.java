package com.qcloud.component.goods.dao.mysql;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.goods.dao.MonthHotSaleDao;
import com.qcloud.component.goods.model.MonthHotSale;
import com.qcloud.component.goods.model.query.MonthHotSaleQuery;

@Repository
public class MonthHotSaleDaoMysqlImpl implements MonthHotSaleDao {

    @Resource(name = "sqlOperator-goods")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MonthHotSale monthHotSale) {

        return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.MonthHotSaleMapper.insert", monthHotSale) == 1;
    }

    @Override
    public MonthHotSale get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MonthHotSaleMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.MonthHotSaleMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MonthHotSale monthHotSale) {

        return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.MonthHotSaleMapper.update", monthHotSale) > 0;
    }

    @Override
    public List<MonthHotSale> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MonthHotSale> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MonthHotSale> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MonthHotSale> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MonthHotSaleMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MonthHotSaleMapper.count4page", param);
        Page<MonthHotSale> page = new Page<MonthHotSale>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MonthHotSale> page(MonthHotSaleQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MonthHotSale> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MonthHotSaleMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MonthHotSaleMapper.count4query", param);
        Page<MonthHotSale> page = new Page<MonthHotSale>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MonthHotSale> listAll() {

        List<MonthHotSale> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MonthHotSaleMapper.listAll");
        return list;
    }

    @Override
    public MonthHotSale getByUnifiedMerchandise(Long unifiedMerchandiseId, int year, int month) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("unifiedMerchandiseId", unifiedMerchandiseId);
        param.put("year", year);
        param.put("month", month);
        MonthHotSale monthHotSale = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MonthHotSaleMapper.getByUnifiedMerchandise", param);
        return monthHotSale;
    }

    @Override
    public List<MonthHotSale> listByMallBsid(String mallBsid, int number) {

        Calendar calendar = Calendar.getInstance();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mallBsid", mallBsid);
        param.put("number", number);
        param.put("year", calendar.get(Calendar.YEAR));
        param.put("month", calendar.get(Calendar.MONTH));
        List<MonthHotSale> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MonthHotSaleMapper.listByMallBsid", param);
        return list;
    }

    @Override
    public List<MonthHotSale> listByMerchantBsid(String merchantBsid, int number) {

        Calendar calendar = Calendar.getInstance();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchantBsid", merchantBsid);
        param.put("number", number);
        param.put("year", calendar.get(Calendar.YEAR));
        param.put("month", calendar.get(Calendar.MONTH));
        List<MonthHotSale> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MonthHotSaleMapper.listByMerchantBsid", param);
        return list;
    }
}
