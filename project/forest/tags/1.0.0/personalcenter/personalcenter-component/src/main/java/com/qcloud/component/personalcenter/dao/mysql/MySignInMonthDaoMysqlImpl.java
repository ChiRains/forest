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
import com.qcloud.component.personalcenter.dao.MySignInMonthDao;
import com.qcloud.component.personalcenter.model.MySignInMonth;
import com.qcloud.component.personalcenter.model.query.MySignInMonthQuery;
		
@Repository
public class MySignInMonthDaoMysqlImpl implements MySignInMonthDao {

	@Resource(name = "sqlOperator-personalcenter")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(MySignInMonth mySignInMonth) {
		return sqlOperator.insert("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInMonthMapper.insert", mySignInMonth) == 1;
	}	
	
	@Override
	public MySignInMonth get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInMonthMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInMonthMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(MySignInMonth mySignInMonth){
		return sqlOperator.update("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInMonthMapper.update", mySignInMonth) > 0;
	}
	
	@Override
	public List<MySignInMonth> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MySignInMonth> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MySignInMonth> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MySignInMonth> list = sqlOperator.selectList(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInMonthMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInMonthMapper.count4page",
				param);
		Page<MySignInMonth> page = new Page<MySignInMonth>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<MySignInMonth> page(MySignInMonthQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MySignInMonth> list = sqlOperator.selectList(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInMonthMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInMonthMapper.count4query",
				param);
		Page<MySignInMonth> page = new Page<MySignInMonth>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<MySignInMonth> listAll(){	
		List<MySignInMonth> list = sqlOperator.selectList(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInMonthMapper.listAll");
		return list;
	}

    @Override
    public MySignInMonth getByYearAndMonth(long userId, int year, int month) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("year", year);
        param.put("month", month);
        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInMonthMapper.getByYearAndMonth", param);
    }
}

