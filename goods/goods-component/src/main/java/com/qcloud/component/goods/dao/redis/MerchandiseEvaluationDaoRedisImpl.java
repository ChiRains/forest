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
import com.qcloud.component.goods.dao.MerchandiseEvaluationDao;
import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.model.query.MerchandiseEvaluationQuery;

@Repository
public class MerchandiseEvaluationDaoRedisImpl implements MerchandiseEvaluationDao {

	//@Resource(name = "redis-goods")
	//private Redis redis;

	@Override
	public boolean add(MerchandiseEvaluation merchandiseEvaluation) {			
		throw new NotImplementedException();
	}

	@Override
	public MerchandiseEvaluation get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MerchandiseEvaluation merchandiseEvaluation){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseEvaluation> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MerchandiseEvaluation> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
			
																					public List<MerchandiseEvaluation> listByMerchandiseId(Long merchandiseId){
					throw new NotImplementedException();
				}

						
																					public List<MerchandiseEvaluation> listByStar(Integer star){
					throw new NotImplementedException();
				}

						
																					public List<MerchandiseEvaluation> listByTime(Date time){
					throw new NotImplementedException();
				}

						
																					public List<MerchandiseEvaluation> listByStatus(Integer status){
					throw new NotImplementedException();
				}

					
	@Override
	public Page<MerchandiseEvaluation> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MerchandiseEvaluation> page(MerchandiseEvaluationQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MerchandiseEvaluation> listAll(){	
		throw new NotImplementedException();
	}
}

