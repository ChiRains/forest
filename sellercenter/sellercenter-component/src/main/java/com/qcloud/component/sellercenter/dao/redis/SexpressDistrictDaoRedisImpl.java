package com.qcloud.component.sellercenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.sellercenter.dao.SexpressDistrictDao;
import com.qcloud.component.sellercenter.model.SexpressDistrict;
import com.qcloud.component.sellercenter.model.query.SexpressDistrictQuery;

@Repository
public class SexpressDistrictDaoRedisImpl implements SexpressDistrictDao {

	//@Resource(name = "redis-sellercenter")
	//private Redis redis;

	@Override
	public boolean add(SexpressDistrict sexpressDistrict) {			
		throw new NotImplementedException();
	}

	@Override
	public SexpressDistrict get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(SexpressDistrict sexpressDistrict){
		throw new NotImplementedException();
	}
	
	@Override
	public List<SexpressDistrict> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, SexpressDistrict> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<SexpressDistrict> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<SexpressDistrict> page(SexpressDistrictQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<SexpressDistrict> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<SexpressDistrict> listByExpressId(Long id) {
        throw new NotImplementedException();
    }
}

