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
import com.qcloud.project.forest.dao.UserdbDao;
import com.qcloud.project.forest.model.Userdb;
import com.qcloud.project.forest.model.query.UserdbQuery;
		
@Repository
public class UserdbDaoMysqlImpl implements UserdbDao {

	@Resource(name = "sqlOperator-forest")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(Userdb userdb) {
		return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.UserdbMapper.insert", userdb) == 1;
	}	
	
	@Override
	public Userdb get(Long id) {
		return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.UserdbMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.UserdbMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(Userdb userdb){
		return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.UserdbMapper.update", userdb) > 0;
	}
	
	@Override
	public List<Userdb> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, Userdb> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<Userdb> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<Userdb> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.UserdbMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.project.forest.dao.mysql.mapper.UserdbMapper.count4page",
				param);
		Page<Userdb> page = new Page<Userdb>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<Userdb> page(UserdbQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<Userdb> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.UserdbMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.project.forest.dao.mysql.mapper.UserdbMapper.count4query",
				param);
		Page<Userdb> page = new Page<Userdb>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<Userdb> listAll(){	
		List<Userdb> list = sqlOperator.selectList(
				"com.qcloud.project.forest.dao.mysql.mapper.UserdbMapper.listAll");
		return list;
	}
}

