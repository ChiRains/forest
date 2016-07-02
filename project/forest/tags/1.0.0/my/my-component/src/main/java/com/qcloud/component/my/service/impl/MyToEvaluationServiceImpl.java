package com.qcloud.component.my.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.my.dao.MyToEvaluationDao;
import com.qcloud.component.my.model.MyToEvaluation;
import com.qcloud.component.my.model.query.MyToEvaluationQuery;
import com.qcloud.component.my.service.MyToEvaluationService;
import com.qcloud.pirates.data.Page;
		
@Service
public class MyToEvaluationServiceImpl implements MyToEvaluationService{
	
	@Autowired
	private MyToEvaluationDao myToEvaluationDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "personalcenter_my_to_evaluation";

	@Override
	public boolean add(MyToEvaluation myToEvaluation) {
		long id = autoIdGenerator.get(ID_KEY);
		myToEvaluation.setId(id);
		
		return myToEvaluationDao.add(myToEvaluation);
	}

	@Override
	public MyToEvaluation get(Long id) {	
		return myToEvaluationDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return myToEvaluationDao.delete(id);
	}
	
	@Override
	public boolean update(MyToEvaluation myToEvaluation) {
		return myToEvaluationDao.update(myToEvaluation);
	}
	
	@Override
	public Page<MyToEvaluation> page(MyToEvaluationQuery query, int start, int count) {
		return myToEvaluationDao.page(query, start, count);
	}
	
	public List<MyToEvaluation> listAll(){
		return myToEvaluationDao.listAll();
	}

    @Override
    public List<MyToEvaluation> listByUser(long userId, int start, int count) {
        return myToEvaluationDao.listByUser(userId, start, count);
    }

    @Override
    public List<MyToEvaluation> listByUserAndOrder(long userId, long subOrderId, int start, int count) {
        return myToEvaluationDao.listByUserAndOrder(userId, subOrderId, start, count);
    }

    @Override
    public int countByUserAndOrder(long userId, long subOrderId) {
        return myToEvaluationDao.countByUserAndOrder(userId, subOrderId);
    }
}

