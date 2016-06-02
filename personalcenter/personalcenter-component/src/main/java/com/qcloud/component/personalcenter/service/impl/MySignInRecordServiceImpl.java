package com.qcloud.component.personalcenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.dao.MySignInRecordDao;
import com.qcloud.component.personalcenter.model.MySignInRecord;
import com.qcloud.component.personalcenter.service.MySignInRecordService;
import com.qcloud.component.personalcenter.model.query.MySignInRecordQuery;
		
@Service
public class MySignInRecordServiceImpl implements MySignInRecordService{
	
	@Autowired
	private MySignInRecordDao mySignInRecordDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "personalcenter_my_sign_in_record";

	@Override
	public boolean add(MySignInRecord mySignInRecord) {
		long id = autoIdGenerator.get(ID_KEY);
		mySignInRecord.setId(id);
		
		return mySignInRecordDao.add(mySignInRecord);
	}

	@Override
	public MySignInRecord get(Long id) {	
		return mySignInRecordDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return mySignInRecordDao.delete(id);
	}
	
	@Override
	public boolean update(MySignInRecord mySignInRecord) {
		return mySignInRecordDao.update(mySignInRecord);
	}
	
	@Override
	public Page<MySignInRecord> page(MySignInRecordQuery query, int start, int count) {
		return mySignInRecordDao.page(query, start, count);
	}
	
	public List<MySignInRecord> listAll(){
		return mySignInRecordDao.listAll();
	}

	@Override
	public MySignInRecord listByUserId(Long userId) {
		return mySignInRecordDao.listByUserId(userId);
	}
}

