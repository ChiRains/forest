package com.qcloud.component.commoditycenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.commoditycenter.dao.MerchandiseVipDiscountDao;
import com.qcloud.component.commoditycenter.model.MerchandiseVipDiscount;
import com.qcloud.component.commoditycenter.model.query.MerchandiseVipDiscountQuery;

@Repository
public class MerchandiseVipDiscountDaoMysqlImpl implements MerchandiseVipDiscountDao {

    @Resource(name = "sqlOperator-commoditycenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MerchandiseVipDiscount merchandiseVipDiscount) {

        return sqlOperator.insert("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseVipDiscountMapper.insert", merchandiseVipDiscount) == 1;
    }

    @Override
    public MerchandiseVipDiscount get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseVipDiscountMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseVipDiscountMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MerchandiseVipDiscount merchandiseVipDiscount) {

        return sqlOperator.update("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseVipDiscountMapper.update", merchandiseVipDiscount) > 0;
    }

    @Override
    public List<MerchandiseVipDiscount> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseVipDiscount> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseVipDiscount> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchandiseVipDiscount> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseVipDiscountMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseVipDiscountMapper.count4page", param);
        Page<MerchandiseVipDiscount> page = new Page<MerchandiseVipDiscount>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MerchandiseVipDiscount> page(MerchandiseVipDiscountQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchandiseVipDiscount> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseVipDiscountMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseVipDiscountMapper.count4query", param);
        Page<MerchandiseVipDiscount> page = new Page<MerchandiseVipDiscount>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MerchandiseVipDiscount> listAll() {

        List<MerchandiseVipDiscount> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseVipDiscountMapper.listAll");
        return list;
    }

    @Override
    public Double statMin(Long merchandiseItemId) {

        MerchandiseVipDiscount min = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseVipDiscountMapper.statMin", merchandiseItemId);
        return min == null ? 0 : min.getPrice();
    }

    @Override
    public Double statMax(Long merchandiseItemId) {

        MerchandiseVipDiscount max = sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseVipDiscountMapper.statMax", merchandiseItemId);
        return max == null ? 0 : max.getPrice();
    }

    @Override
    public MerchandiseVipDiscount get(Long userId, Long merchandiseItemId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("merchandiseItemId", merchandiseItemId);
        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseVipDiscountMapper.getByUserAndMerchandise", param);
    }

    @Override
    public List<MerchandiseVipDiscount> listByUser(long userId, long classifyId, String classifyBSID, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("userId", userId);
        param.put("classifyId", classifyId);
        param.put("classifyBSID", classifyBSID);
        List<MerchandiseVipDiscount> list = sqlOperator.selectList("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseVipDiscountMapper.listByUser", param);
        return list;
    }

    @Override
    public boolean deleteByUser(long userId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        return sqlOperator.delete("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseVipDiscountMapper.deleteByUser", param) > 1;
    }

    @Override
    public int countByUser(long userId, long classifyId, String classifyBSID) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("classifyId", classifyId);
        param.put("classifyBSID", classifyBSID);
        return sqlOperator.selectOne("com.qcloud.component.commoditycenter.dao.mysql.mapper.MerchandiseVipDiscountMapper.countByUser", param);
    }
}
