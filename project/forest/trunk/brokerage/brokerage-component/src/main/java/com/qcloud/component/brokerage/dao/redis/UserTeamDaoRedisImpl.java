package com.qcloud.component.brokerage.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.brokerage.dao.UserTeamDao;
import com.qcloud.component.brokerage.model.UserTeam;
import com.qcloud.component.brokerage.model.query.UserTeamQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class UserTeamDaoRedisImpl implements UserTeamDao {

	//@Resource(name = "redis-distribution")
	//private Redis redis;

	@Override
	public boolean add(UserTeam userTeam) {			
		throw new NotImplementedException();
	}

	@Override
	public UserTeam get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(UserTeam userTeam){
		throw new NotImplementedException();
	}
	
	@Override
	public List<UserTeam> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, UserTeam> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<UserTeam> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<UserTeam> page(UserTeamQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<UserTeam> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public UserTeam getByUserId(Long userId) {
        throw new NotImplementedException();
    }

    @Override
    public List<UserTeam> listChildren(long leader) {
        throw new NotImplementedException();
    }

    @Override
    public int countChildren(long leader) {
        throw new NotImplementedException();
    }

    @Override
    public Page<UserTeam> pageLeader(UserTeamQuery query, int start, int count) {
        throw new NotImplementedException();
    }
}

