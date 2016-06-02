package com.qcloud.component.my.dao.mysql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyCouponDao;
import com.qcloud.component.my.model.MyCoupon;
import com.qcloud.component.my.model.query.MyCouponQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.pirates.util.DateUtil;

@Repository
public class MyCouponDaoMysqlImpl implements MyCouponDao {

    @Resource(name = "sqlOperator-my")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MyCoupon myCoupon) {

        return sqlOperator.insert("com.qcloud.component.my.dao.mysql.mapper.MyCouponMapper.insert", myCoupon) == 1;
    }

    @Override
    public MyCoupon get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyCouponMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.my.dao.mysql.mapper.MyCouponMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MyCoupon myCoupon) {

        return sqlOperator.update("com.qcloud.component.my.dao.mysql.mapper.MyCouponMapper.update", myCoupon) > 0;
    }

    @Override
    public List<MyCoupon> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyCoupon> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyCoupon> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyCoupon> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyCouponMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyCouponMapper.count4page", param);
        Page<MyCoupon> page = new Page<MyCoupon>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MyCoupon> page(MyCouponQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MyCoupon> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyCouponMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyCouponMapper.count4query", param);
        Page<MyCoupon> page = new Page<MyCoupon>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MyCoupon> listAll() {

        List<MyCoupon> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyCouponMapper.listAll");
        return list;
    }

    @Override
    public List<MyCoupon> listByUser(long userId, Integer type, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("userId", userId);
        param.put("type", type);
        param.put("nowtime", DateUtil.date2String(new Date()));
        List<MyCoupon> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyCouponMapper.listByUser", param);
        return list;
    }

    @Override
    public List<MyCoupon> listCanUseByUser(long userId, Long merchantId, double sum, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("userId", userId);
        param.put("merchantId", merchantId);
        param.put("now", new Date());
        param.put("sum", sum);
        List<MyCoupon> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyCouponMapper.listCanUseByUser", param);
        return list;
    }

    @Override
    public List<MyCoupon> listByUserAndCoupon(long userId, long couponId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("couponId", couponId);
        List<MyCoupon> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.MyCouponMapper.listByUserAndCoupon", param);
        return list;
    }

    @Override
    public MyCoupon getByCode(String code) {

        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyCouponMapper.getByCode", code);
    }

    @Override
    public int countByUser(long userId, Integer type) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("type", type);
        param.put("nowtime", DateUtil.date2String(new Date()));
        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.MyCouponMapper.countByUser", param);
    }
}
