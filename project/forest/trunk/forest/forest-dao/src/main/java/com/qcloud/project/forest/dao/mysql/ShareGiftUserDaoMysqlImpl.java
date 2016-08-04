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
import com.qcloud.project.forest.dao.ShareGiftUserDao;
import com.qcloud.project.forest.model.ShareGiftUser;
import com.qcloud.project.forest.model.query.ShareGiftUserQuery;

@Repository
public class ShareGiftUserDaoMysqlImpl implements ShareGiftUserDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(ShareGiftUser shareGiftUser) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftUserMapper.insert", shareGiftUser) == 1;
    }

    @Override
    public ShareGiftUser get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftUserMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftUserMapper.delete", id) > 0;
    }

    @Override
    public boolean update(ShareGiftUser shareGiftUser) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftUserMapper.update", shareGiftUser) > 0;
    }

    @Override
    public List<ShareGiftUser> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ShareGiftUser> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ShareGiftUser> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<ShareGiftUser> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftUserMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftUserMapper.count4page", param);
        Page<ShareGiftUser> page = new Page<ShareGiftUser>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<ShareGiftUser> page(ShareGiftUserQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<ShareGiftUser> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftUserMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftUserMapper.count4query", param);
        Page<ShareGiftUser> page = new Page<ShareGiftUser>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<ShareGiftUser> listAll() {

        List<ShareGiftUser> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftUserMapper.listAll");
        return list;
    }

    @Override
    public List<ShareGiftUser> listByCode(String code) {

        return sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftUserMapper.listByCode", code);
    }

    @Override
    public int countByCode(String code) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftUserMapper.countByCode", code);
    }

    @Override
    public int countCouponByCode(String code) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ShareGiftUserMapper.countCouponByCode", code);
    }
}
