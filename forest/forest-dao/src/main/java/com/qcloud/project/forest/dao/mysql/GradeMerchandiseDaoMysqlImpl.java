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
import com.qcloud.project.forest.dao.GradeMerchandiseDao;
import com.qcloud.project.forest.model.GradeMerchandise;
import com.qcloud.project.forest.model.query.GradeMerchandiseQuery;
		
@Repository
public class GradeMerchandiseDaoMysqlImpl implements GradeMerchandiseDao {

	@Resource(name = "sqlOperator-forest")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(GradeMerchandise gradeMerchandise) {
		return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.GradeMerchandiseMapper.insert", gradeMerchandise) == 1;
	}	
	
	@Override
	public GradeMerchandise get(Long id) {
		return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.GradeMerchandiseMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.GradeMerchandiseMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(GradeMerchandise gradeMerchandise){
		return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.GradeMerchandiseMapper.update", gradeMerchandise) > 0;
	}
	
	@Override
	public List<GradeMerchandise> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, GradeMerchandise> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<GradeMerchandise> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<GradeMerchandise> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.GradeMerchandiseMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.project.forest.dao.mysql.mapper.GradeMerchandiseMapper.count4page",
				param);
		Page<GradeMerchandise> page = new Page<GradeMerchandise>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<GradeMerchandise> page(GradeMerchandiseQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<GradeMerchandise> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.GradeMerchandiseMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.project.forest.dao.mysql.mapper.GradeMerchandiseMapper.count4query",
				param);
		Page<GradeMerchandise> page = new Page<GradeMerchandise>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<GradeMerchandise> listAll(){	
		List<GradeMerchandise> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.GradeMerchandiseMapper.listAll");
		return list;
	}
}

