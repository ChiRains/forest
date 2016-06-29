package com.qcloud.component.publicservice.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.publicservice.dao.MessageSourceDao;
import com.qcloud.component.publicservice.model.MessageSource;
import com.qcloud.component.publicservice.model.query.MessageSourceQuery;
		
@Repository
public class MessageSourceDaoMysqlImpl implements MessageSourceDao {

	@Resource(name = "sqlOperator-publicservice")
	private SqlOperator sqlOperator;

	@Override
	public boolean add(MessageSource messageSource) {
		return sqlOperator.insert("com.qcloud.component.publicservice.dao.mysql.mapper.MessageSourceMapper.insert", messageSource) == 1;
	}	
	
	@Override
	public MessageSource get(Long id) {
		return sqlOperator.selectOne("com.qcloud.component.publicservice.dao.mysql.mapper.MessageSourceMapper.get", id);
	}	
	
	@Override
	public boolean delete(Long id){
		return sqlOperator.delete("com.qcloud.component.publicservice.dao.mysql.mapper.MessageSourceMapper.delete", id) > 0;
	}	
		
	@Override
	public boolean update(MessageSource messageSource){
		return sqlOperator.update("com.qcloud.component.publicservice.dao.mysql.mapper.MessageSourceMapper.update", messageSource) > 0;
	}
	
	@Override
	public List<MessageSource> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MessageSource> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MessageSource> page(int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MessageSource> list = sqlOperator.selectList(
				"com.qcloud.component.publicservice.dao.mysql.mapper.MessageSourceMapper.list4page",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.publicservice.dao.mysql.mapper.MessageSourceMapper.count4page",
				param);
		Page<MessageSource> page = new Page<MessageSource>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public Page<MessageSource> page(MessageSourceQuery query, int start, int count){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("count", count);

		List<MessageSource> list = sqlOperator.selectList(
				"com.qcloud.component.publicservice.dao.mysql.mapper.MessageSourceMapper.list4query",
				param);
		int total = sqlOperator.selectOne(
				"com.qcloud.component.publicservice.dao.mysql.mapper.MessageSourceMapper.count4query",
				param);
		Page<MessageSource> page = new Page<MessageSource>();
		page.setCount(total);
		page.setData(list);
		return page;
	}
	
	@Override
	public List<MessageSource> listAll(){	
		List<MessageSource> list = sqlOperator.selectList(
				"com.qcloud.component.publicservice.dao.mysql.mapper.MessageSourceMapper.listAll");
		return list;
	}
}

