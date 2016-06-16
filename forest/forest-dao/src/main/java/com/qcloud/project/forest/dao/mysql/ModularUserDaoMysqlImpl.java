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
import com.qcloud.project.forest.dao.ModularUserDao;
import com.qcloud.project.forest.model.ModularUser;
import com.qcloud.project.forest.model.query.ModularUserQuery;

@Repository
public class ModularUserDaoMysqlImpl implements ModularUserDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(ModularUser modularUser) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.ModularUserMapper.insert", modularUser) == 1;
    }

    @Override
    public ModularUser get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ModularUserMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.ModularUserMapper.delete", id) > 0;
    }

    @Override
    public boolean update(ModularUser modularUser) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.ModularUserMapper.update", modularUser) > 0;
    }

    @Override
    public List<ModularUser> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ModularUser> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<ModularUser> listByUserId(Long userId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        return sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ModularUserMapper.listByUserId", param);
    }

    @Override
    public Page<ModularUser> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<ModularUser> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ModularUserMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ModularUserMapper.count4page", param);
        Page<ModularUser> page = new Page<ModularUser>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<ModularUser> page(ModularUserQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<ModularUser> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ModularUserMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ModularUserMapper.count4query", param);
        Page<ModularUser> page = new Page<ModularUser>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<ModularUser> listAll() {

        List<ModularUser> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ModularUserMapper.listAll");
        return list;
    }

    @Override
    public Boolean deleteByUserId(Long userId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ModularUserMapper.deleteByUserId", param);
    }
}
