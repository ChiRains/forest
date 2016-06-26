package com.qcloud.component.goods.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.model.query.MerchandiseEvaluationQuery;
		
public interface MerchandiseEvaluationDao extends ISimpleDao<MerchandiseEvaluation, Long> {

	public boolean add(MerchandiseEvaluation merchandiseEvaluation);	
	
	public MerchandiseEvaluation get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MerchandiseEvaluation merchandiseEvaluation);
	
	public List<MerchandiseEvaluation> list(List<Long> idList);
	
	public Map<Long, MerchandiseEvaluation> map(Set<Long> idSet);
	
	public Page<MerchandiseEvaluation> page(MerchandiseEvaluationQuery query, int start, int size);

	public List<MerchandiseEvaluation> listAll();
	
	public List<MerchandiseEvaluation> listByMerchandiseId(Long merchandiseId);

	public List<MerchandiseEvaluation> listByStar(Integer star);

	public List<MerchandiseEvaluation> listByTime(Date time);

	public List<MerchandiseEvaluation> listByStatus(Integer status);

}
