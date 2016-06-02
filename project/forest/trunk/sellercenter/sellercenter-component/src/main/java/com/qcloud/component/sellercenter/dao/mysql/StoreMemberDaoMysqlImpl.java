package com.qcloud.component.sellercenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.sellercenter.dao.StoreMemberDao;
import com.qcloud.component.sellercenter.model.StoreMember;
import com.qcloud.component.sellercenter.model.query.StoreMemberQuery;

@Repository
public class StoreMemberDaoMysqlImpl implements StoreMemberDao {

    @Resource(name = "sqlOperator-sellercenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(StoreMember storeMember) {

        return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMemberMapper.insert", storeMember) == 1;
    }

    @Override
    public StoreMember get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMemberMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMemberMapper.delete", id) > 0;
    }

    @Override
    public boolean update(StoreMember storeMember) {

        return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMemberMapper.update", storeMember) > 0;
    }

    @Override
    public List<StoreMember> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, StoreMember> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<StoreMember> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<StoreMember> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMemberMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMemberMapper.count4page", param);
        Page<StoreMember> page = new Page<StoreMember>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<StoreMember> page(StoreMemberQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("merchantId", query.getMerchantId());
        param.put("storeId", query.getStoreId());
        List<StoreMember> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMemberMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMemberMapper.count4query", param);
        Page<StoreMember> page = new Page<StoreMember>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<StoreMember> listAll() {

        List<StoreMember> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMemberMapper.listAll");
        return list;
    }

    @Override
    public StoreMember get(HashMap where) {
        where.put("start", 0);
        where.put("count", 1);
        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMemberMapper.list", where);
    }

    @Override
    public List<StoreMember> list(HashMap where) {
        return sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMemberMapper.list", where);
    }

    @Override
    public Page<StoreMember> page(HashMap where, int start, int size) {
        where.put("start", start);
        where.put("count", size);

        List<StoreMember> list = sqlOperator.selectList(
                "com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMemberMapper.list",
                where);
        int total = sqlOperator.selectOne(
                "com.qcloud.component.sellercenter.dao.mysql.mapper.StoreMemberMapper.count",
                where);
        Page<StoreMember> storeMemberPage = new Page<StoreMember>();
        storeMemberPage.setCount(total);
        storeMemberPage.setData(list);
        return storeMemberPage;
    }
}
