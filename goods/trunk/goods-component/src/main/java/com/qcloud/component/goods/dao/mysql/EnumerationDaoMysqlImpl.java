package com.qcloud.component.goods.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.goods.dao.EnumerationDao;
import com.qcloud.component.goods.model.Enumeration;
import com.qcloud.component.goods.model.query.EnumerationQuery;
		
@Repository
public class EnumerationDaoMysqlImpl implements EnumerationDao {

	@Resource(name = "sqlOperator-goods")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(Enumeration enumeration) {
		return sqlOperator.insert("com.qcloud.component.goods.dao.mysql.mapper.EnumerationMapper.insert", enumeration) == 1;
	}	
	
	@Override
	public Enumeration get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.goods.dao.mysql.mapper.EnumerationMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.goods.dao.mysql.mapper.EnumerationMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(Enumeration enumeration){
		return sqlOperator.update("com.qcloud.component.goods.dao.mysql.mapper.EnumerationMapper.update", enumeration) > 0;
	}
	
	@Override
	public List<Enumeration> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, Enumeration> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<Enumeration> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<Enumeration> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.EnumerationMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.EnumerationMapper.count4page",
				param);
		Page<Enumeration> page = new Page<Enumeration>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<Enumeration> page(EnumerationQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<Enumeration> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.EnumerationMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.goods.dao.mysql.mapper.EnumerationMapper.count4query",
				param);
		Page<Enumeration> page = new Page<Enumeration>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<Enumeration> listAll(){	
		List<Enumeration> list = sqlOperator.selectList(
				"com.qcloud.component.goods.dao.mysql.mapper.EnumerationMapper.listAll");
		return list;
	}
}

