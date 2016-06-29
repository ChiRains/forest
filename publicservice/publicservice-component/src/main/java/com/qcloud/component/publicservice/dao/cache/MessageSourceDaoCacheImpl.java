package com.qcloud.component.publicservice.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.publicservice.dao.MessageSourceDao;
import com.qcloud.component.publicservice.model.MessageSource;
import com.qcloud.component.publicservice.model.query.MessageSourceQuery;

@Repository
public class MessageSourceDaoCacheImpl implements MessageSourceDao {
	
	@Autowired
	private MessageSourceDao messageSourceDaoMysqlImpl;
	
	@Autowired
	private MessageSourceDao messageSourceDaoRedisImpl;

	@Override
	public boolean add(MessageSource messageSource) {
		return messageSourceDaoMysqlImpl.add(messageSource);
	}

	@Override
	public MessageSource get(Long id) {
		return CacheLoader.get(messageSourceDaoRedisImpl, messageSourceDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return messageSourceDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MessageSource messageSource){
		return messageSourceDaoMysqlImpl.update(messageSource);
	}
	
	@Override
	public List<MessageSource> list(List<Long> idList) {
		return CacheLoader.list(messageSourceDaoRedisImpl, messageSourceDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MessageSource> map(Set<Long> idSet){
		return CacheLoader.map(messageSourceDaoRedisImpl, messageSourceDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MessageSource> page(int start, int count){
		return messageSourceDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MessageSource> page(MessageSourceQuery query, int start, int count){
		return messageSourceDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MessageSource> listAll(){
		return messageSourceDaoMysqlImpl.listAll();
	}
}

