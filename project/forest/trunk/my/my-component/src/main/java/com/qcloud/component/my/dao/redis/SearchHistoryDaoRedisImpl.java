package com.qcloud.component.my.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.SearchHistoryDao;
import com.qcloud.component.my.model.SearchHistory;
import com.qcloud.component.my.model.query.SearchHistoryQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class SearchHistoryDaoRedisImpl implements SearchHistoryDao {

	//@Resource(name = "redis-my")
	//private Redis redis;

	@Override
	public boolean add(SearchHistory searchHistory) {			
		throw new NotImplementedException();
	}

	@Override
	public SearchHistory get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(SearchHistory searchHistory){
		throw new NotImplementedException();
	}
	
	@Override
	public List<SearchHistory> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, SearchHistory> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<SearchHistory> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<SearchHistory> page(SearchHistoryQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<SearchHistory> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public List<SearchHistory> list(long userId, int type, int number) {
        throw new NotImplementedException();
    }

    @Override
    public SearchHistory get(long userId, int type, String keywords) {
        throw new NotImplementedException();
    }

    @Override
    public boolean clear(long userId, int type) {
        throw new NotImplementedException();
    }
}

