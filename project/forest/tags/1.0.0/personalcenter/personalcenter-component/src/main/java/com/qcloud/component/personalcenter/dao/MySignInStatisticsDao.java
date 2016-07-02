package com.qcloud.component.personalcenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.personalcenter.model.MySignInStatistics;
import com.qcloud.component.personalcenter.model.query.MySignInStatisticsQuery;
		
public interface MySignInStatisticsDao extends ISimpleDao<MySignInStatistics, Long> {

	public boolean add(MySignInStatistics mySignInStatistics);	
	
	public MySignInStatistics get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MySignInStatistics mySignInStatistics);
	
	public List<MySignInStatistics> list(List<Long> idList);
	
	public Map<Long, MySignInStatistics> map(Set<Long> idSet);
	
	public Page<MySignInStatistics> page(MySignInStatisticsQuery query, int start, int size);

	public List<MySignInStatistics> listAll();
	
	MySignInStatistics getByUser(Long userId);	
}
