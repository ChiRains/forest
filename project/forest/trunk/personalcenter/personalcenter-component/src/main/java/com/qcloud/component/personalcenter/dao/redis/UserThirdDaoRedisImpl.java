package com.qcloud.component.personalcenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.personalcenter.dao.UserThirdDao;
import com.qcloud.component.personalcenter.model.UserThird;
import com.qcloud.component.personalcenter.model.key.TypeEnum.AccountType;
import com.qcloud.component.personalcenter.model.query.UserThirdQuery;

@Repository
public class UserThirdDaoRedisImpl implements UserThirdDao {

	//@Resource(name = "redis-personalcenter")
	//private Redis redis;

	@Override
	public boolean add(UserThird userThird) {			
		throw new NotImplementedException();
	}

	@Override
	public UserThird get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(UserThird userThird){
		throw new NotImplementedException();
	}
	
	@Override
	public List<UserThird> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, UserThird> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<UserThird> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<UserThird> page(UserThirdQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<UserThird> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public UserThird getByThird(String thirdId, AccountType type) {
        throw new NotImplementedException();
    }

    @Override
    public UserThird getByUser(Long userId) {
        throw new NotImplementedException();
    }
}

