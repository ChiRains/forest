package com.qcloud.component.brokerage.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.brokerage.dao.UserRelationshipDao;
import com.qcloud.component.brokerage.model.UserRelationship;
import com.qcloud.component.brokerage.model.key.TypeEnum.UserAllocationType;
import com.qcloud.component.brokerage.model.query.UserRelationshipQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class UserRelationshipDaoMysqlImpl implements UserRelationshipDao {

    @Resource(name = "sqlOperator-brokerage")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(UserRelationship userRelationship) {

        return sqlOperator.insert("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.insert", userRelationship) == 1;
    }

    @Override
    public UserRelationship get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.delete", id) > 0;
    }

    @Override
    public boolean update(UserRelationship userRelationship) {

        return sqlOperator.update("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.update", userRelationship) > 0;
    }

    @Override
    public List<UserRelationship> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, UserRelationship> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<UserRelationship> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<UserRelationship> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.count4page", param);
        Page<UserRelationship> page = new Page<UserRelationship>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<UserRelationship> page(UserRelationshipQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<UserRelationship> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.count4query", param);
        Page<UserRelationship> page = new Page<UserRelationship>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<UserRelationship> listAll() {

        List<UserRelationship> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.listAll");
        return list;
    }

    @Override
    public UserRelationship getByUserId(Long userId) {

        return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.getByUserId", userId);
    }

    @Override
    public List<UserRelationship> listChildren(long recommedId, UserAllocationType allocation) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("recommedId", recommedId);
        if (allocation == null) {
            param.put("allocation", -1);
        } else {
            param.put("allocation", allocation.getKey());
        }
        List<UserRelationship> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.listChildren", param);
        return list;
    }

    @Override
    public int countChildren(long recommedId, UserAllocationType allocation) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("recommedId", recommedId);
        if (allocation == null) {
            param.put("allocation", -1);
        } else {
            param.put("allocation", allocation.getKey());
        }
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.countChildren", param);
        return total;
    }

    @Override
    public Page<UserRelationship> pageRecommed(UserRelationshipQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("recommedId", query.getRecommedId());
        List<UserRelationship> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.list4recommed", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.count4recommed", param);
        Page<UserRelationship> page = new Page<UserRelationship>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<UserRelationship> pageOneChildren(long recommedId, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("recommedId", recommedId);
        List<UserRelationship> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.pageOneChildren", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.countOneChildren", param);
        Page<UserRelationship> page = new Page<UserRelationship>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<UserRelationship> pageTwoChildren(long recommedId, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("recommedId", recommedId);
        List<UserRelationship> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.pageTwoChildren", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.countTwoChildren", param);
        Page<UserRelationship> page = new Page<UserRelationship>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<UserRelationship> pageThreeChildren(long recommedId, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("recommedId", recommedId);
        List<UserRelationship> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.pageThreeChildren", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.countThreeChildren", param);
        Page<UserRelationship> page = new Page<UserRelationship>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public int getCountByOneChildren(long recommedId) {

        return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.countOneChildren", recommedId);
    }

    @Override
    public int getCountByTwoChildren(long recommedId) {

        return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.countTwoChildren", recommedId);
    }

    @Override
    public int getCountByThreeChildren(long recommedId) {

        return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserRelationshipMapper.countThreeChildren", recommedId);
    }
}
