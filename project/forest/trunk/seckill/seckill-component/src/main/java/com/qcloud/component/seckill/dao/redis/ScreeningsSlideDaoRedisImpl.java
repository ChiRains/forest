package com.qcloud.component.seckill.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.seckill.dao.ScreeningsSlideDao;
import com.qcloud.component.seckill.model.ScreeningsSlide;
import com.qcloud.component.seckill.model.query.ScreeningsSlideQuery;

@Repository
public class ScreeningsSlideDaoRedisImpl implements ScreeningsSlideDao {

	//@Resource(name = "redis-seckill")
	//private Redis redis;

	@Override
	public boolean add(ScreeningsSlide screeningsSlide) {			
		throw new NotImplementedException();
	}

	@Override
	public ScreeningsSlide get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(ScreeningsSlide screeningsSlide){
		throw new NotImplementedException();
	}
	
	@Override
	public List<ScreeningsSlide> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, ScreeningsSlide> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<ScreeningsSlide> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<ScreeningsSlide> page(ScreeningsSlideQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<ScreeningsSlide> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<ScreeningsSlide> listByScreenings(long screeningsId) {
        throw new NotImplementedException();
    }
}

