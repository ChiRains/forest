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
import com.qcloud.component.personalcenter.dao.MyBankCardDao;
import com.qcloud.component.personalcenter.model.MyBankCard;
import com.qcloud.component.personalcenter.model.query.MyBankCardQuery;

@Repository
public class MyBankCardDaoRedisImpl implements MyBankCardDao {

	//@Resource(name = "redis-personalcenter")
	//private Redis redis;

	@Override
	public boolean add(MyBankCard myBankCard) {			
		throw new NotImplementedException();
	}

	@Override
	public MyBankCard get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MyBankCard myBankCard){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MyBankCard> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MyBankCard> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MyBankCard> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MyBankCard> page(MyBankCardQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MyBankCard> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<MyBankCard> listByUser(long userId) {
        throw new NotImplementedException();
    }
}

