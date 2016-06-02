package com.qcloud.component.personalcenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.personalcenter.model.MySignInMonth;
import com.qcloud.component.personalcenter.model.query.MySignInMonthQuery;
		
public interface MySignInMonthDao extends ISimpleDao<MySignInMonth, Long> {

	public boolean add(MySignInMonth mySignInMonth);	
	
	public MySignInMonth get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MySignInMonth mySignInMonth);
	
	public List<MySignInMonth> list(List<Long> idList);
	
	public Map<Long, MySignInMonth> map(Set<Long> idSet);
	
	public Page<MySignInMonth> page(MySignInMonthQuery query, int start, int size);

	public List<MySignInMonth> listAll();
	
	MySignInMonth getByYearAndMonth(long userId, int year, int month);	
}
