package com.qcloud.component.marketing.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.marketing.dao.CouponItemsDao;
import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.marketing.model.query.CouponItemsQuery;

@Repository
public class CouponItemsDaoMysqlImpl implements CouponItemsDao {

    @Resource(name = "sqlOperator-marketing")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(CouponItems couponItems) {

        return sqlOperator.insert("com.qcloud.component.marketing.dao.mysql.mapper.CouponItemsMapper.insert", couponItems) == 1;
    }

    @Override
    public CouponItems get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.marketing.dao.mysql.mapper.CouponItemsMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.marketing.dao.mysql.mapper.CouponItemsMapper.delete", id) > 0;
    }

    @Override
    public boolean update(CouponItems couponItems) {

        return sqlOperator.update("com.qcloud.component.marketing.dao.mysql.mapper.CouponItemsMapper.update", couponItems) > 0;
    }

    @Override
    public List<CouponItems> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, CouponItems> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<CouponItems> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<CouponItems> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.CouponItemsMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.marketing.dao.mysql.mapper.CouponItemsMapper.count4page", param);
        Page<CouponItems> page = new Page<CouponItems>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<CouponItems> page(CouponItemsQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("couponID", query.getCouponID());
        List<CouponItems> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.CouponItemsMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.marketing.dao.mysql.mapper.CouponItemsMapper.count4query", param);
        Page<CouponItems> page = new Page<CouponItems>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<CouponItems> listAll() {

        List<CouponItems> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.CouponItemsMapper.listAll");
        return list;
    }

    @Override
    public List<CouponItems> list4Counpon(Long couponId) {

        List<CouponItems> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.CouponItemsMapper.list4Counpon", couponId);
        return list;
    }

    @Override
    public List<CouponItems> list4Extract(Long couponId) {

        List<CouponItems> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.CouponItemsMapper.list4Extract", couponId);
        return list;
    }

    @Override
    public List<CouponItems> listByCouponId(Long couponId) {

        List<CouponItems> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.CouponItemsMapper.listByCouponId", couponId);
        return list;
    }
}
