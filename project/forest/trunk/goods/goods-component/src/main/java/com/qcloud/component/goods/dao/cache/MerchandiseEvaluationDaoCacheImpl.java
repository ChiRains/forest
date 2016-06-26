package com.qcloud.component.goods.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseEvaluationDao;
import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.model.query.MerchandiseEvaluationQuery;

@Repository
public class MerchandiseEvaluationDaoCacheImpl implements MerchandiseEvaluationDao {
	
	@Autowired
	private MerchandiseEvaluationDao merchandiseEvaluationDaoMysqlImpl;
	
	@Autowired
	private MerchandiseEvaluationDao merchandiseEvaluationDaoRedisImpl;

	@Override
	public boolean add(MerchandiseEvaluation merchandiseEvaluation) {
		return merchandiseEvaluationDaoMysqlImpl.add(merchandiseEvaluation);
	}

	@Override
	public MerchandiseEvaluation get(Long id) {
		return CacheLoader.get(merchandiseEvaluationDaoRedisImpl, merchandiseEvaluationDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return merchandiseEvaluationDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseEvaluation merchandiseEvaluation){
		return merchandiseEvaluationDaoMysqlImpl.update(merchandiseEvaluation);
	}
	
	@Override
	public List<MerchandiseEvaluation> list(List<Long> idList) {
		return CacheLoader.list(merchandiseEvaluationDaoRedisImpl, merchandiseEvaluationDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MerchandiseEvaluation> map(Set<Long> idSet){
		return CacheLoader.map(merchandiseEvaluationDaoRedisImpl, merchandiseEvaluationDaoMysqlImpl, idSet);
	}
			
		public List<MerchandiseEvaluation> listByMerchandiseId(Long merchandiseId){
			return merchandiseEvaluationDaoMysqlImpl.listByMerchandiseId(merchandiseId);
		}
				
		public List<MerchandiseEvaluation> listByStar(Integer star){
			return merchandiseEvaluationDaoMysqlImpl.listByStar(star);
		}
				
		public List<MerchandiseEvaluation> listByTime(Date time){
			return merchandiseEvaluationDaoMysqlImpl.listByTime(time);
		}
				
		public List<MerchandiseEvaluation> listByStatus(Integer status){
			return merchandiseEvaluationDaoMysqlImpl.listByStatus(status);
		}
	
	@Override
	public Page<MerchandiseEvaluation> page(int start, int count){
		return merchandiseEvaluationDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MerchandiseEvaluation> page(MerchandiseEvaluationQuery query, int start, int count){
		return merchandiseEvaluationDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MerchandiseEvaluation> listAll(){
		return merchandiseEvaluationDaoMysqlImpl.listAll();
	}
}

