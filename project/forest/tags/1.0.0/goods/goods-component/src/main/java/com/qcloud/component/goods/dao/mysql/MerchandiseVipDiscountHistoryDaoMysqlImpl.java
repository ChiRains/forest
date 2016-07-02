package com.qcloud.component.goods.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.component.goods.dao.MerchandiseVipDiscountHistoryDao;
import com.qcloud.component.goods.model.MerchandiseVipDiscountHistory;
import com.qcloud.component.goods.model.query.MerchandiseVipDiscountHistoryQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class MerchandiseVipDiscountHistoryDaoMysqlImpl implements MerchandiseVipDiscountHistoryDao {

    @Resource(name = "sqlOperator-goods")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory) {

        return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseVipDiscountHistoryMapper.insert", merchandiseVipDiscountHistory) == 1;
    }

    @Override
    public MerchandiseVipDiscountHistory get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseVipDiscountHistoryMapper.get", id);
    }

	@Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseVipDiscountHistoryMapper.delete", id) > 0;
    }

	@Override
    public boolean update(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory) {

        return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseVipDiscountHistoryMapper.update", merchandiseVipDiscountHistory) > 0;
    }

    @Override
    public List<MerchandiseVipDiscountHistory> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseVipDiscountHistory> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseVipDiscountHistory> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchandiseVipDiscountHistory> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseVipDiscountHistoryMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseVipDiscountHistoryMapper.count4page", param);
        Page<MerchandiseVipDiscountHistory> page = new Page<MerchandiseVipDiscountHistory>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MerchandiseVipDiscountHistory> page(MerchandiseVipDiscountHistoryQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("userId", query.getUserId());
        param.put("merchandiseItemId", query.getMerchandiseItemId());
        List<MerchandiseVipDiscountHistory> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseVipDiscountHistoryMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseVipDiscountHistoryMapper.count4query", param);
        Page<MerchandiseVipDiscountHistory> page = new Page<MerchandiseVipDiscountHistory>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MerchandiseVipDiscountHistory> listAll() {

        List<MerchandiseVipDiscountHistory> list = sqlOperator.selectList("com.qcloud.component.goods.dao.mysql.mapper.MerchandiseVipDiscountHistoryMapper.listAll");
        return list;
    }

}
