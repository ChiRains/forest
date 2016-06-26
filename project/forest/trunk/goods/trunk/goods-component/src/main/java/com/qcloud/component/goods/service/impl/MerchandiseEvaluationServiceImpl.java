package com.qcloud.component.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.MerchandiseEvaluationDao;
import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.service.MerchandiseEvaluationService;
import com.qcloud.component.goods.model.query.MerchandiseEvaluationQuery;
		
@Service
public class MerchandiseEvaluationServiceImpl implements MerchandiseEvaluationService{
	
	@Autowired
	private MerchandiseEvaluationDao merchandiseEvaluationDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "goods_merchandise_evaluation";

	@Override
	public boolean add(MerchandiseEvaluation merchandiseEvaluation) {
		long id = autoIdGenerator.get(ID_KEY);
		merchandiseEvaluation.setId(id);
		
		return merchandiseEvaluationDao.add(merchandiseEvaluation);
	}

	@Override
	public MerchandiseEvaluation get(Long id) {	
		return merchandiseEvaluationDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return merchandiseEvaluationDao.delete(id);
	}
	
	@Override
	public boolean update(MerchandiseEvaluation merchandiseEvaluation) {
		return merchandiseEvaluationDao.update(merchandiseEvaluation);
	}
			
	public List<MerchandiseEvaluation> listByMerchandiseId(Long merchandiseId){
		return merchandiseEvaluationDao.listByMerchandiseId(merchandiseId);
	}
			
	public List<MerchandiseEvaluation> listByStar(Integer star){
		return merchandiseEvaluationDao.listByStar(star);
	}
			
	public List<MerchandiseEvaluation> listByTime(Date time){
		return merchandiseEvaluationDao.listByTime(time);
	}
			
	public List<MerchandiseEvaluation> listByStatus(Integer status){
		return merchandiseEvaluationDao.listByStatus(status);
	}
	
	@Override
	public Page<MerchandiseEvaluation> page(MerchandiseEvaluationQuery query, int start, int count) {
		return merchandiseEvaluationDao.page(query, start, count);
	}
	
	public List<MerchandiseEvaluation> listAll(){
		return merchandiseEvaluationDao.listAll();
	}
}

