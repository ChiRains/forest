package com.qcloud.project.forest.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.project.forest.dao.RangeGradeDao;
import com.qcloud.project.forest.model.RangeGrade;
import com.qcloud.project.forest.model.query.RangeGradeQuery;
		
@Repository
public class RangeGradeDaoMysqlImpl implements RangeGradeDao {

	@Resource(name = "sqlOperator-forest")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(RangeGrade rangeGrade) {
		return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.RangeGradeMapper.insert", rangeGrade) == 1;
	}	
	
	@Override
	public RangeGrade get(Long id) {
		return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.RangeGradeMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.RangeGradeMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(RangeGrade rangeGrade){
		return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.RangeGradeMapper.update", rangeGrade) > 0;
	}
	
	@Override
	public List<RangeGrade> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, RangeGrade> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<RangeGrade> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<RangeGrade> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.RangeGradeMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.project.forest.dao.mysql.mapper.RangeGradeMapper.count4page",
				param);
		Page<RangeGrade> page = new Page<RangeGrade>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<RangeGrade> page(RangeGradeQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<RangeGrade> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.RangeGradeMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.project.forest.dao.mysql.mapper.RangeGradeMapper.count4query",
				param);
		Page<RangeGrade> page = new Page<RangeGrade>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<RangeGrade> listAll(){	
		List<RangeGrade> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.RangeGradeMapper.listAll");
		return list;
	}
}

