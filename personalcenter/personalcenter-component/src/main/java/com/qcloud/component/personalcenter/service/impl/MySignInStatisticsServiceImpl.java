package com.qcloud.component.personalcenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.dao.MySignInStatisticsDao;
import com.qcloud.component.personalcenter.model.MySignInStatistics;
import com.qcloud.component.personalcenter.service.MySignInStatisticsService;
import com.qcloud.component.personalcenter.model.query.MySignInStatisticsQuery;
		
@Service
public class MySignInStatisticsServiceImpl implements MySignInStatisticsService{
	
	@Autowired
	private MySignInStatisticsDao mySignInStatisticsDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "personalcenter_my_sign_in_statistics";

	@Override
	public boolean add(MySignInStatistics mySignInStatistics) {
		long id = autoIdGenerator.get(ID_KEY);
		mySignInStatistics.setId(id);
		
		return mySignInStatisticsDao.add(mySignInStatistics);
	}

	@Override
	public MySignInStatistics get(Long id) {	
		return mySignInStatisticsDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return mySignInStatisticsDao.delete(id);
	}
	
	@Override
	public boolean update(MySignInStatistics mySignInStatistics) {
		return mySignInStatisticsDao.update(mySignInStatistics);
	}
	
	@Override
	public Page<MySignInStatistics> page(MySignInStatisticsQuery query, int start, int count) {
		return mySignInStatisticsDao.page(query, start, count);
	}
	
	public List<MySignInStatistics> listAll(){
		return mySignInStatisticsDao.listAll();
	}

    @Override
    public MySignInStatistics getByUser(Long userId) {
        return mySignInStatisticsDao.getByUser(userId);
    }
}

