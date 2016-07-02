package com.qcloud.component.marketing.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.marketing.dao.RecentDiscountDao;
import com.qcloud.component.marketing.model.RecentDiscount;
import com.qcloud.component.marketing.model.query.RecentDiscountQuery;

@Repository
public class RecentDiscountDaoRedisImpl implements RecentDiscountDao {

	//@Resource(name = "redis-marketing")
	//private Redis redis;

	@Override
	public boolean add(RecentDiscount recentDiscount) {			
		throw new NotImplementedException();
	}

	@Override
	public RecentDiscount get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(RecentDiscount recentDiscount){
		throw new NotImplementedException();
	}
	
	@Override
	public List<RecentDiscount> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, RecentDiscount> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<RecentDiscount> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<RecentDiscount> page(RecentDiscountQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<RecentDiscount> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<RecentDiscount> list(RecentDiscountQuery query, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public int count(RecentDiscountQuery query) {

        throw new NotImplementedException();
    }
}

