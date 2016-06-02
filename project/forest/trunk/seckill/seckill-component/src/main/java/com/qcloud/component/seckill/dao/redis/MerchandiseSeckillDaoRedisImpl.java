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
import com.qcloud.component.seckill.dao.MerchandiseSeckillDao;
import com.qcloud.component.seckill.model.MerchandiseSeckill;
import com.qcloud.component.seckill.model.query.MerchandiseSeckillQuery;

@Repository
public class MerchandiseSeckillDaoRedisImpl implements MerchandiseSeckillDao {

	//@Resource(name = "redis-seckill")
	//private Redis redis;

	@Override
	public boolean add(MerchandiseSeckill merchandiseSeckill) {			
		throw new NotImplementedException();
	}

	@Override
	public MerchandiseSeckill get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MerchandiseSeckill merchandiseSeckill){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseSeckill> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseSeckill> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchandiseSeckill> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MerchandiseSeckill> page(MerchandiseSeckillQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseSeckill> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<MerchandiseSeckill> listByScreenings(long screeningsId) {        
        throw new NotImplementedException();
    }

	@Override
	public List<MerchandiseSeckill> listByScreeningsAndQUnifiedMerchandiseId(
			long screeningsId, long qUnifiedMerchandiseId) {
		throw new NotImplementedException();
	}
    
}

