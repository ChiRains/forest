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
import com.qcloud.component.commoditycenter.dao.ClassifyAttributeDao;
import com.qcloud.component.commoditycenter.model.ClassifyAttribute;
import com.qcloud.component.commoditycenter.model.query.ClassifyAttributeQuery;

@Repository
public class ClassifyAttributeDaoRedisImpl implements ClassifyAttributeDao {

//	@Resource(name = "redis-commoditycenter")
//	private Redis redis;

	@Override
	public boolean add(ClassifyAttribute classifyAttribute) {	
		throw new NotImplementedException();
	}

	@Override
	public ClassifyAttribute get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(ClassifyAttribute classifyAttribute){
		throw new NotImplementedException();
	}
	
	@Override
	public List<ClassifyAttribute> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, ClassifyAttribute> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<ClassifyAttribute> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<ClassifyAttribute> page(ClassifyAttributeQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<ClassifyAttribute> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public ClassifyAttribute get(Long classifyId, Long attributeId) {
        throw new NotImplementedException();
    }

    @Override
    public List<ClassifyAttribute> listByClassify(Long classifyId) {
        throw new NotImplementedException();
    }
}

