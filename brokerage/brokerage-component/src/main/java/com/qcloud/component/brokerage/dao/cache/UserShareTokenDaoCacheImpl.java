package com.qcloud.component.brokerage.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.brokerage.dao.UserShareTokenDao;
import com.qcloud.component.brokerage.model.UserShareToken;
import com.qcloud.component.brokerage.model.query.UserShareTokenQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class UserShareTokenDaoCacheImpl implements UserShareTokenDao {
	
	@Autowired
	private UserShareTokenDao userShareTokenDaoMysqlImpl;
	
//	@Autowired
//	private UserShareTokenDao userShareTokenDaoRedisImpl;

	@Override
	public boolean add(UserShareToken userShareToken) {
		return userShareTokenDaoMysqlImpl.add(userShareToken);
	}

	@Override
	public UserShareToken get(Long id) {
	    return userShareTokenDaoMysqlImpl.get(id);		
	}

	@Override
	public boolean delete(Long id){
		return userShareTokenDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(UserShareToken userShareToken){
		return userShareTokenDaoMysqlImpl.update(userShareToken);
	}
	
	@Override
	public List<UserShareToken> list(List<Long> idList) {
		return CacheLoader.list(userShareTokenDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, UserShareToken> map(Set<Long> idSet){
		return CacheLoader.map(userShareTokenDaoMysqlImpl, idSet);
	}

	@Override
	public Page<UserShareToken> page(int start, int count){
		return userShareTokenDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<UserShareToken> page(UserShareTokenQuery query, int start, int count){
		return userShareTokenDaoMysqlImpl.page(query, start, count);
	}
	
	public List<UserShareToken> listAll(){
		return userShareTokenDaoMysqlImpl.listAll();
	}

    @Override
    public UserShareToken getByUser(Long userId) {       
        return userShareTokenDaoMysqlImpl.getByUser(userId);
    }

    @Override
    public UserShareToken getByToken(String token) {       
        return userShareTokenDaoMysqlImpl.getByToken(token);
    }
}

