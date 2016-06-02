package com.qcloud.component.brokerage.dao.cache;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.brokerage.dao.UserTeamDao;
import com.qcloud.component.brokerage.model.UserTeam;
import com.qcloud.component.brokerage.model.query.UserTeamQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
@Repository
public class UserTeamDaoCacheImpl implements UserTeamDao {
    @Autowired
    private UserTeamDao userTeamDaoMysqlImpl;
//    @Autowired
//    private UserTeamDao userTeamDaoRedisImpl;

    @Override
    public boolean add(UserTeam userTeam) {
        return userTeamDaoMysqlImpl.add(userTeam);
    }

    @Override
    public UserTeam get(Long id) {
        return userTeamDaoMysqlImpl.get(id);
//        return CacheLoader.get(userTeamDaoRedisImpl, userTeamDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {
        return userTeamDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(UserTeam userTeam) {
        return userTeamDaoMysqlImpl.update(userTeam);
    }

    @Override
    public List<UserTeam> list(List<Long> idList) {
        return CacheLoader.list(userTeamDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, UserTeam> map(Set<Long> idSet) {
        return CacheLoader.map(userTeamDaoMysqlImpl, idSet);
    }

    @Override
    public Page<UserTeam> page(int start, int count) {
        return userTeamDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<UserTeam> page(UserTeamQuery query, int start, int count) {
        return userTeamDaoMysqlImpl.page(query, start, count);
    }

    public List<UserTeam> listAll() {
        return userTeamDaoMysqlImpl.listAll();
    }

    @Override
    public UserTeam getByUserId(Long userId) {
        return userTeamDaoMysqlImpl.getByUserId(userId);
    }

    @Override
    public List<UserTeam> listChildren(long leader) {
        return userTeamDaoMysqlImpl.listChildren(leader);
    }

    @Override
    public int countChildren(long leader) {
        return userTeamDaoMysqlImpl.countChildren(leader);
    }

    @Override
    public Page<UserTeam> pageLeader(UserTeamQuery query, int start, int count) {
        return userTeamDaoMysqlImpl.pageLeader(query, start, count);
    }
}
