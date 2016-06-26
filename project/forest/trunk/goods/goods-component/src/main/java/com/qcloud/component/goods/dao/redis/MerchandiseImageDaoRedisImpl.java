package com.qcloud.component.goods.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.goods.dao.MerchandiseImageDao;
import com.qcloud.component.goods.model.MerchandiseImage;
import com.qcloud.component.goods.model.query.MerchandiseImageQuery;

@Repository
public class MerchandiseImageDaoRedisImpl implements MerchandiseImageDao {

	//@Resource(name = "redis-commoditycenter")
	//private Redis redis;

	@Override
	public boolean add(MerchandiseImage merchandiseImage) {			
		throw new NotImplementedException();
	}

	@Override
	public MerchandiseImage get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MerchandiseImage merchandiseImage){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseImage> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseImage> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchandiseImage> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MerchandiseImage> page(MerchandiseImageQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseImage> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<MerchandiseImage> listByMerchandise(Long merchandiseId) {
        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseImage> listByMerchandiseAndAttribute(Long merchandiseId, Long attributeId, String value) {
        throw new NotImplementedException();
    }
}

