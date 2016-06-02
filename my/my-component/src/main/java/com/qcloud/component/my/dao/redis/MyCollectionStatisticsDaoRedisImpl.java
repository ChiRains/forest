package com.qcloud.component.my.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyCollectionStatisticsDao;
import com.qcloud.component.my.model.MyCollectionStatistics;
import com.qcloud.component.my.model.query.MyCollectionStatisticsQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MyCollectionStatisticsDaoRedisImpl implements MyCollectionStatisticsDao {

	//@Resource(name = "redis-my")
	//private Redis redis;

	@Override
	public boolean add(MyCollectionStatistics myCollectionStatistics) {			
		throw new NotImplementedException();
	}

	@Override
	public MyCollectionStatistics get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MyCollectionStatistics myCollectionStatistics){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MyCollectionStatistics> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MyCollectionStatistics> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MyCollectionStatistics> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MyCollectionStatistics> page(MyCollectionStatisticsQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MyCollectionStatistics> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public MyCollectionStatistics get(Long userId, long classifyId, int type) {
        throw new NotImplementedException();
    }

    @Override
    public List<MyCollectionStatistics> listByUserAndType(long userId, int type) {
        throw new NotImplementedException();
    }
}

