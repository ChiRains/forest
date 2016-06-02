package com.qcloud.component.my.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.my.dao.SearchHistoryDao;
import com.qcloud.component.my.model.SearchHistory;
import com.qcloud.component.my.model.query.SearchHistoryQuery;

@Repository
public class SearchHistoryDaoCacheImpl implements SearchHistoryDao {
	
	@Autowired
	private SearchHistoryDao searchHistoryDaoMysqlImpl;
	
//	@Autowired
//	private SearchHistoryDao searchHistoryDaoRedisImpl;

	@Override
	public boolean add(SearchHistory searchHistory) {
		return searchHistoryDaoMysqlImpl.add(searchHistory);
	}

	@Override
	public SearchHistory get(Long id) {
	    return searchHistoryDaoMysqlImpl.get(id);
//		return CacheLoader.get(searchHistoryDaoRedisImpl, searchHistoryDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return searchHistoryDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(SearchHistory searchHistory){
		return searchHistoryDaoMysqlImpl.update(searchHistory);
	}
	
	@Override
	public List<SearchHistory> list(List<Long> idList) {
		return CacheLoader.list(searchHistoryDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, SearchHistory> map(Set<Long> idSet){
		return CacheLoader.map(searchHistoryDaoMysqlImpl, idSet);
	}

	@Override
	public Page<SearchHistory> page(int start, int count){
		return searchHistoryDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<SearchHistory> page(SearchHistoryQuery query, int start, int count){
		return searchHistoryDaoMysqlImpl.page(query, start, count);
	}
	
	public List<SearchHistory> listAll(){
		return searchHistoryDaoMysqlImpl.listAll();
	}

    @Override
    public List<SearchHistory> list(long userId, int type, int number) {
        return searchHistoryDaoMysqlImpl.list(userId, type, number);
    }

    @Override
    public SearchHistory get(long userId, int type, String keywords) {
        return searchHistoryDaoMysqlImpl.get(userId, type, keywords);
    }

    @Override
    public boolean clear(long userId, int type) {
        return searchHistoryDaoMysqlImpl.clear(userId, type);
    }
}

