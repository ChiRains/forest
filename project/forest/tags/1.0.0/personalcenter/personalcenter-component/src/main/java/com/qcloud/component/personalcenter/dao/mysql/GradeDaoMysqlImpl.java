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
import com.qcloud.component.personalcenter.dao.GradeDao;
import com.qcloud.component.personalcenter.model.Grade;
import com.qcloud.component.personalcenter.model.query.GradeQuery;
		
@Repository
public class GradeDaoMysqlImpl implements GradeDao {

	@Resource(name = "sqlOperator-personalcenter")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(Grade grade) {
		return sqlOperator.insert("com.qcloud.component.personalcenter.dao.mysql.mapper.GradeMapper.insert", grade) == 1;
	}	
	
	@Override
	public Grade get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.GradeMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.personalcenter.dao.mysql.mapper.GradeMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(Grade grade){
		return sqlOperator.update("com.qcloud.component.personalcenter.dao.mysql.mapper.GradeMapper.update", grade) > 0;
	}
	
	@Override
	public List<Grade> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, Grade> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<Grade> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<Grade> list = sqlOperator.selectList(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.GradeMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.GradeMapper.count4page",
				param);
		Page<Grade> page = new Page<Grade>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<Grade> page(GradeQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<Grade> list = sqlOperator.selectList(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.GradeMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.GradeMapper.count4query",
				param);
		Page<Grade> page = new Page<Grade>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<Grade> listAll(){	
		List<Grade> list = sqlOperator.selectList(
				"com.qcloud.component.personalcenter.dao.mysql.mapper.GradeMapper.listAll");
		return list;
	}
}

