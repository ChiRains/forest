package com.qcloud.component.brokerage.dao.mysql;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.brokerage.dao.UserTeamDao;
import com.qcloud.component.brokerage.model.UserTeam;
import com.qcloud.component.brokerage.model.query.UserTeamQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
@Repository
public class UserTeamDaoMysqlImpl implements UserTeamDao {
    @Resource(name = "sqlOperator-brokerage")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(UserTeam userTeam) {
        return sqlOperator.insert("com.qcloud.component.brokerage.dao.mysql.mapper.UserTeamMapper.insert", userTeam) == 1;
    }

    @Override
    public UserTeam get(Long id) {
        return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserTeamMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {
        return sqlOperator.delete("com.qcloud.component.brokerage.dao.mysql.mapper.UserTeamMapper.delete", id) > 0;
    }

    @Override
    public boolean update(UserTeam userTeam) {
        return sqlOperator.update("com.qcloud.component.brokerage.dao.mysql.mapper.UserTeamMapper.update", userTeam) > 0;
    }

    @Override
    public List<UserTeam> list(List<Long> idList) {
        throw new NotImplementedException();
    }

    @Override
    public Map<Long, UserTeam> map(Set<Long> idSet) {
        throw new NotImplementedException();
    }

    @Override
    public Page<UserTeam> page(int start, int count) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<UserTeam> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UserTeamMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserTeamMapper.count4page", param);
        Page<UserTeam> page = new Page<UserTeam>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<UserTeam> page(UserTeamQuery query, int start, int count) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<UserTeam> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UserTeamMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserTeamMapper.count4query", param);
        Page<UserTeam> page = new Page<UserTeam>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<UserTeam> listAll() {
        List<UserTeam> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UserTeamMapper.listAll");
        return list;
    }

    @Override
    public UserTeam getByUserId(Long userId) {
        return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserTeamMapper.getByUserId", userId);
    }

    @Override
    public List<UserTeam> listChildren(long leader) {
        List<UserTeam> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UserTeamMapper.listChildren", leader);
        return list;
    }

    @Override
    public int countChildren(long leader) {
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserTeamMapper.countChildren", leader);
        return total;
    }

    @Override
    public Page<UserTeam> pageLeader(UserTeamQuery query, int start, int count) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<UserTeam> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UserTeamMapper.list4leader", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserTeamMapper.count4leader", param);
        Page<UserTeam> page = new Page<UserTeam>();
        page.setCount(total);
        page.setData(list);
        return page;
    }
}
