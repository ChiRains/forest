package com.qcloud.component.personalcenter.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.model.MySignInRecord;
import com.qcloud.component.personalcenter.model.query.MySignInRecordQuery;

public interface MySignInRecordService {
	
	public boolean add(MySignInRecord mySignInRecord);	
	
	public MySignInRecord get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(MySignInRecord mySignInRecord);

	public Page<MySignInRecord> page(MySignInRecordQuery query, int start, int count);
	
	public List<MySignInRecord> listAll();
	
	public MySignInRecord listByUserId(Long userId);
}

