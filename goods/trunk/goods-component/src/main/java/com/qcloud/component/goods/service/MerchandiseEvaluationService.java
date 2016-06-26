package com.qcloud.component.goods.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.model.query.MerchandiseEvaluationQuery;

public interface MerchandiseEvaluationService {
	
	public boolean add(MerchandiseEvaluation merchandiseEvaluation);	
	
	public MerchandiseEvaluation get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(MerchandiseEvaluation merchandiseEvaluation);

	public List<MerchandiseEvaluation> listByMerchandiseId(Long merchandiseId);

	public List<MerchandiseEvaluation> listByStar(Integer star);

	public List<MerchandiseEvaluation> listByTime(Date time);

	public List<MerchandiseEvaluation> listByStatus(Integer status);

	public Page<MerchandiseEvaluation> page(MerchandiseEvaluationQuery query, int start, int count);
	
	public List<MerchandiseEvaluation> listAll();
}

