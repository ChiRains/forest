package com.qcloud.component.personalcenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.component.personalcenter.model.MySignInRecord;
import com.qcloud.component.personalcenter.model.query.MySignInRecordQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
		
public interface MySignInRecordDao extends ISimpleDao<MySignInRecord, Long> {

	public boolean add(MySignInRecord mySignInRecord);	
	
	public MySignInRecord get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MySignInRecord mySignInRecord);
	
	public List<MySignInRecord> list(List<Long> idList);
	
	public Map<Long, MySignInRecord> map(Set<Long> idSet);
	
	public Page<MySignInRecord> page(MySignInRecordQuery query, int start, int size);

	public List<MySignInRecord> listAll();
	
	public MySignInRecord listByUserId(Long userId);
	
}
