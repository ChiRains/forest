package com.qcloud.component.personalcenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.dao.MySignInMonthDao;
import com.qcloud.component.personalcenter.model.MySignInMonth;
import com.qcloud.component.personalcenter.service.MySignInMonthService;
import com.qcloud.component.personalcenter.model.query.MySignInMonthQuery;
		
@Service
public class MySignInMonthServiceImpl implements MySignInMonthService{
	
	@Autowired
	private MySignInMonthDao mySignInMonthDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "personalcenter_my_sign_in_month";

	@Override
	public boolean add(MySignInMonth mySignInMonth) {
		long id = autoIdGenerator.get(ID_KEY);
		mySignInMonth.setId(id);
		
		return mySignInMonthDao.add(mySignInMonth);
	}

	@Override
	public MySignInMonth get(Long id) {	
		return mySignInMonthDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return mySignInMonthDao.delete(id);
	}
	
	@Override
	public boolean update(MySignInMonth mySignInMonth) {
		return mySignInMonthDao.update(mySignInMonth);
	}
	
	@Override
	public Page<MySignInMonth> page(MySignInMonthQuery query, int start, int count) {
		return mySignInMonthDao.page(query, start, count);
	}
	
	public List<MySignInMonth> listAll(){
		return mySignInMonthDao.listAll();
	}

    @Override
    public MySignInMonth getByYearAndMonth(long userId, int year, int month) {
        return mySignInMonthDao.getByYearAndMonth(userId, year, month);
    }
}

