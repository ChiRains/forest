package com.qcloud.component.orderform.dao.redis;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.orderform.dao.RemindRecordDao;
import com.qcloud.component.orderform.model.RemindRecord;
import com.qcloud.component.orderform.model.query.RemindRecordQuery;

@Repository
public class RemindRecordDaoRedisImpl implements RemindRecordDao {

	//@Resource(name = "redis-orderform")
	//private Redis redis;

	@Override
	public boolean add(RemindRecord remindRecord) {			
		throw new NotImplementedException();
	}

	@Override
	public RemindRecord get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(RemindRecord remindRecord){
		throw new NotImplementedException();
	}
	
	@Override
	public List<RemindRecord> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, RemindRecord> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<RemindRecord> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<RemindRecord> page(RemindRecordQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<RemindRecord> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public boolean canRemind(long subOrderId, Date orderDate, int splitMinutes) {
        throw new NotImplementedException();
    }
}

