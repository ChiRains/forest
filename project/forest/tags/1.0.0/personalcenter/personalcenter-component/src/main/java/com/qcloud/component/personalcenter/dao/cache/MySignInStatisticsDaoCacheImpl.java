package com.qcloud.component.personalcenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.dao.MySignInStatisticsDao;
import com.qcloud.component.personalcenter.model.MySignInStatistics;
import com.qcloud.component.personalcenter.model.query.MySignInStatisticsQuery;

@Repository
public class MySignInStatisticsDaoCacheImpl implements MySignInStatisticsDao {
	
	@Autowired
	private MySignInStatisticsDao mySignInStatisticsDaoMysqlImpl;
	
//	@Autowired
//	private MySignInStatisticsDao mySignInStatisticsDaoRedisImpl;

	@Override
	public boolean add(MySignInStatistics mySignInStatistics) {
		return mySignInStatisticsDaoMysqlImpl.add(mySignInStatistics);
	}

	@Override
	public MySignInStatistics get(Long id) {
	    return mySignInStatisticsDaoMysqlImpl.get(id);
//		return CacheLoader.get(mySignInStatisticsDaoRedisImpl, mySignInStatisticsDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return mySignInStatisticsDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MySignInStatistics mySignInStatistics){
		return mySignInStatisticsDaoMysqlImpl.update(mySignInStatistics);
	}
	
	@Override
	public List<MySignInStatistics> list(List<Long> idList) {
		return CacheLoader.list(mySignInStatisticsDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MySignInStatistics> map(Set<Long> idSet){
		return CacheLoader.map(mySignInStatisticsDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MySignInStatistics> page(int start, int count){
		return mySignInStatisticsDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MySignInStatistics> page(MySignInStatisticsQuery query, int start, int count){
		return mySignInStatisticsDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MySignInStatistics> listAll(){
		return mySignInStatisticsDaoMysqlImpl.listAll();
	}

    @Override
    public MySignInStatistics getByUser(Long userId) {
        return mySignInStatisticsDaoMysqlImpl.getByUser(userId);
    }
}

