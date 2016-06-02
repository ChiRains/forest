package com.qcloud.component.personalcenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.personalcenter.dao.MySignInStatisticsDao;
import com.qcloud.component.personalcenter.model.MySignInStatistics;
import com.qcloud.component.personalcenter.model.query.MySignInStatisticsQuery;
		
@Repository
public class MySignInStatisticsDaoMysqlImpl implements MySignInStatisticsDao {

	@Resource(name = "sqlOperator-personalcenter")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(MySignInStatistics mySignInStatistics) {
		return sqlOperator.insert("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInStatisticsMapper.insert", mySignInStatistics) == 1;
	}	
	
	@Override
	public MySignInStatistics get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInStatisticsMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInStatisticsMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(MySignInStatistics mySignInStatistics){
		return sqlOperator.update("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInStatisticsMapper.update", mySignInStatistics) > 0;
	}
	
	@Override
	public List<MySignInStatistics> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MySignInStatistics> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MySignInStatistics> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MySignInStatistics> list = sqlOperator.selectList(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInStatisticsMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInStatisticsMapper.count4page",
				param);
		Page<MySignInStatistics> page = new Page<MySignInStatistics>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<MySignInStatistics> page(MySignInStatisticsQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MySignInStatistics> list = sqlOperator.selectList(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInStatisticsMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInStatisticsMapper.count4query",
				param);
		Page<MySignInStatistics> page = new Page<MySignInStatistics>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<MySignInStatistics> listAll(){	
		List<MySignInStatistics> list = sqlOperator.selectList(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInStatisticsMapper.listAll");
		return list;
	}

    @Override
    public MySignInStatistics getByUser(Long userId) {
        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInStatisticsMapper.getByUser", userId);
    }
}

