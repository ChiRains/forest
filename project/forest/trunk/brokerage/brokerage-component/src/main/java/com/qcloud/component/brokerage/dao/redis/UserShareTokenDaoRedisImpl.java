package com.qcloud.component.brokerage.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.brokerage.dao.UserShareTokenDao;
import com.qcloud.component.brokerage.model.UserShareToken;
import com.qcloud.component.brokerage.model.query.UserShareTokenQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class UserShareTokenDaoRedisImpl implements UserShareTokenDao {

	//@Resource(name = "redis-distribution")
	//private Redis redis;

	@Override
	public boolean add(UserShareToken userShareToken) {			
		throw new NotImplementedException();
	}

	@Override
	public UserShareToken get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(UserShareToken userShareToken){
		throw new NotImplementedException();
	}
	
	@Override
	public List<UserShareToken> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, UserShareToken> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<UserShareToken> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<UserShareToken> page(UserShareTokenQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<UserShareToken> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public UserShareToken getByUser(Long userId) {
        throw new NotImplementedException();
    }

    @Override
    public UserShareToken getByToken(String token) {
        throw new NotImplementedException();
    }
}

