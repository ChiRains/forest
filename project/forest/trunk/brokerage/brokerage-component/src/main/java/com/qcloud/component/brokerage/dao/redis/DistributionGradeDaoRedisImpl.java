package com.qcloud.component.brokerage.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.brokerage.dao.DistributionGradeDao;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.model.query.DistributionGradeQuery;

@Repository
public class DistributionGradeDaoRedisImpl implements DistributionGradeDao {

	//@Resource(name = "redis-brokerage")
	//private Redis redis;

	@Override
	public boolean add(DistributionGrade distributionGrade) {			
		throw new NotImplementedException();
	}

	@Override
	public DistributionGrade get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(DistributionGrade distributionGrade){
		throw new NotImplementedException();
	}
	
	@Override
	public List<DistributionGrade> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, DistributionGrade> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<DistributionGrade> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<DistributionGrade> page(DistributionGradeQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<DistributionGrade> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public DistributionGrade getDefault() {
        throw new NotImplementedException();
    }
}

