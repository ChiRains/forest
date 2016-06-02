package com.qcloud.component.commoditycenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.commoditycenter.dao.MerchandiseVipDiscountDateDao;
import com.qcloud.component.commoditycenter.model.MerchandiseVipDiscountDate;
import com.qcloud.component.commoditycenter.model.query.MerchandiseVipDiscountDateQuery;

@Repository
public class MerchandiseVipDiscountDateDaoRedisImpl implements MerchandiseVipDiscountDateDao {

	//@Resource(name = "redis-commoditycenter")
	//private Redis redis;

	@Override
	public boolean add(MerchandiseVipDiscountDate merchandiseVipDiscountDate) {			
		throw new NotImplementedException();
	}

	@Override
	public MerchandiseVipDiscountDate get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MerchandiseVipDiscountDate merchandiseVipDiscountDate){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseVipDiscountDate> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseVipDiscountDate> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MerchandiseVipDiscountDate> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MerchandiseVipDiscountDate> page(MerchandiseVipDiscountDateQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseVipDiscountDate> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public MerchandiseVipDiscountDate getByUser(Long userId, int year, int month, int day) {
        throw new NotImplementedException();
    }

    @Override
    public MerchandiseVipDiscountDate getLastByUser(Long userId) {
        throw new NotImplementedException();
    }
}

