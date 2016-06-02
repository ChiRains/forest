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
import com.qcloud.project.forest.dao.AnalysisresultDao;
import com.qcloud.project.forest.model.Analysisresult;
import com.qcloud.project.forest.model.query.AnalysisresultQuery;

@Repository
public class AnalysisresultDaoRedisImpl implements AnalysisresultDao {

	//@Resource(name = "redis-forest")
	//private Redis redis;

	@Override
	public boolean add(Analysisresult analysisresult) {			
		throw new NotImplementedException();
	}

	@Override
	public Analysisresult get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(Analysisresult analysisresult){
		throw new NotImplementedException();
	}
	
	@Override
	public List<Analysisresult> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, Analysisresult> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<Analysisresult> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<Analysisresult> page(AnalysisresultQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<Analysisresult> listAll(){	
		throw new NotImplementedException();
	}

	@Override
	public Analysisresult getBMI(int type, double BMI) {
		throw new NotImplementedException();
	}
}

