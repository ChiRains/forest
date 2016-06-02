package com.qcloud.component.marketing.dao.mysql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.marketing.dao.CouponDao;
import com.qcloud.component.marketing.model.Coupon;
import com.qcloud.component.marketing.model.query.CouponQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class CouponDaoMysqlImpl implements CouponDao {

    @Resource(name = "sqlOperator-marketing")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(Coupon coupon) {

        return sqlOperator.insert("com.qcloud.component.marketing.dao.mysql.mapper.CouponMapper.insert", coupon) == 1;
    }

    @Override
    public Coupon get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.marketing.dao.mysql.mapper.CouponMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.marketing.dao.mysql.mapper.CouponMapper.delete", id) > 0;
    }

    @Override
    public boolean update(Coupon coupon) {

        return sqlOperator.update("com.qcloud.component.marketing.dao.mysql.mapper.CouponMapper.update", coupon) > 0;
    }

    @Override
    public List<Coupon> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Coupon> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Coupon> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<Coupon> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.CouponMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.marketing.dao.mysql.mapper.CouponMapper.count4page", param);
        Page<Coupon> page = new Page<Coupon>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<Coupon> page(CouponQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("merchantId", query.getMerchantId());
        List<Coupon> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.CouponMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.marketing.dao.mysql.mapper.CouponMapper.count4query", param);
        Page<Coupon> page = new Page<Coupon>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<Coupon> listAll() {

        List<Coupon> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.CouponMapper.listAll");
        return list;
    }

    @Override
    public List<Coupon> listActivityCoupon(long merchantId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("now", new Date());
        param.put("merchantId", merchantId);
        List<Coupon> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.CouponMapper.listActivityCoupon", param);
        return list;
    }

    @Override
    public int countActivityCoupon(long merchantId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("now", new Date());
        param.put("merchantId", merchantId);
        return sqlOperator.selectOne("com.qcloud.component.marketing.dao.mysql.mapper.CouponMapper.countActivityCoupon", param);
    }

    @Override
    public List<Coupon> listByPlatform() {

        List<Coupon> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.CouponMapper.listByPlatform");
        return list;
    }
}
