package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.project.forest.dao.MedicationDao;
import com.qcloud.project.forest.model.Medication;
import com.qcloud.project.forest.model.query.MedicationQuery;

@Repository
public class MedicationDaoRedisImpl implements MedicationDao {

	//@Resource(name = "redis-forest")
	//private Redis redis;

	@Override
	public boolean add(Medication medication) {			
		throw new NotImplementedException();
	}

	@Override
	public Medication get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(Medication medication){
		throw new NotImplementedException();
	}
	
	@Override
	public List<Medication> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, Medication> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<Medication> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<Medication> page(MedicationQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<Medication> listAll(){	
		throw new NotImplementedException();
	}
}

