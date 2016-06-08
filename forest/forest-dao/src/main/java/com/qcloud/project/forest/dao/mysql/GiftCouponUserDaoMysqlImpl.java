package com.qcloud.project.forest.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.project.forest.dao.GiftCouponUserDao;
import com.qcloud.project.forest.model.GiftCouponUser;
import com.qcloud.project.forest.model.query.GiftCouponUserQuery;

@Repository
public class GiftCouponUserDaoMysqlImpl implements GiftCouponUserDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(GiftCouponUser giftCouponUser) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.GiftCouponUserMapper.insert", giftCouponUser) == 1;
    }

    @Override
    public GiftCouponUser get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.GiftCouponUserMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.GiftCouponUserMapper.delete", id) > 0;
    }

    @Override
    public boolean update(GiftCouponUser giftCouponUser) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.GiftCouponUserMapper.update", giftCouponUser) > 0;
    }

    @Override
    public List<GiftCouponUser> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, GiftCouponUser> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<GiftCouponUser> listByUserId(Long userId) {

        throw new NotImplementedException();
    }

    @Override
    public Page<GiftCouponUser> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<GiftCouponUser> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.GiftCouponUserMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.GiftCouponUserMapper.count4page", param);
        Page<GiftCouponUser> page = new Page<GiftCouponUser>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<GiftCouponUser> page(GiftCouponUserQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("userId", query.getUserId());
        List<GiftCouponUser> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.GiftCouponUserMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.GiftCouponUserMapper.count4query", param);
        Page<GiftCouponUser> page = new Page<GiftCouponUser>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<GiftCouponUser> listAll() {

        List<GiftCouponUser> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.GiftCouponUserMapper.listAll");
        return list;
    }
}
