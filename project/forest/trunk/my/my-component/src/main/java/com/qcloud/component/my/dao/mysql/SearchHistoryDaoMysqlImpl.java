package com.qcloud.component.my.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.my.dao.SearchHistoryDao;
import com.qcloud.component.my.model.SearchHistory;
import com.qcloud.component.my.model.query.SearchHistoryQuery;
		
@Repository
public class SearchHistoryDaoMysqlImpl implements SearchHistoryDao {

	@Resource(name = "sqlOperator-my")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(SearchHistory searchHistory) {
		return sqlOperator.insert("com.qcloud.component.my.dao.mysql.mapper.SearchHistoryMapper.insert", searchHistory) == 1;
	}	
	
	@Override
	public SearchHistory get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.SearchHistoryMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.my.dao.mysql.mapper.SearchHistoryMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(SearchHistory searchHistory){
		return sqlOperator.update("com.qcloud.component.my.dao.mysql.mapper.SearchHistoryMapper.update", searchHistory) > 0;
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
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<SearchHistory> list = sqlOperator.selectList(
				"com.qcloud.component.my.dao.mysql.mapper.SearchHistoryMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.my.dao.mysql.mapper.SearchHistoryMapper.count4page",
				param);
		Page<SearchHistory> page = new Page<SearchHistory>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<SearchHistory> page(SearchHistoryQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<SearchHistory> list = sqlOperator.selectList(
				"com.qcloud.component.my.dao.mysql.mapper.SearchHistoryMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.my.dao.mysql.mapper.SearchHistoryMapper.count4query",
				param);
		Page<SearchHistory> page = new Page<SearchHistory>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<SearchHistory> listAll(){	
		List<SearchHistory> list = sqlOperator.selectList(
				"com.qcloud.component.my.dao.mysql.mapper.SearchHistoryMapper.listAll");
		return list;
	}

    @Override
    public List<SearchHistory> list(long userId, int type, int number) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("type", type);
        param.put("number", number);        
        List<SearchHistory> list = sqlOperator.selectList(
                "com.qcloud.component.my.dao.mysql.mapper.SearchHistoryMapper.list",
                param);
        return list;
    }

    @Override
    public SearchHistory get(long userId, int type, String keywords) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("type", type);
        param.put("keywords", keywords);
        SearchHistory searchHistory = sqlOperator.selectOne(
                "com.qcloud.component.my.dao.mysql.mapper.SearchHistoryMapper.getByKeywords",
                param);
        return searchHistory;
    }

    @Override
    public boolean clear(long userId, int type) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("type", type);
        sqlOperator.delete("com.qcloud.component.my.dao.mysql.mapper.SearchHistoryMapper.clear", param);
        return true;
    }
}

