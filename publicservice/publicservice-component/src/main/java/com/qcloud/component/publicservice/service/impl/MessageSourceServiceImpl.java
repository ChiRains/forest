package com.qcloud.component.publicservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.publicservice.dao.MessageSourceDao;
import com.qcloud.component.publicservice.model.MessageSource;
import com.qcloud.component.publicservice.service.MessageSourceService;
import com.qcloud.component.publicservice.model.query.MessageSourceQuery;
		
@Service
public class MessageSourceServiceImpl implements MessageSourceService{
	
	@Autowired
	private MessageSourceDao messageSourceDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "publicservice_message_source";

	@Override
	public boolean add(MessageSource messageSource) {
		long id = autoIdGenerator.get(ID_KEY);
		messageSource.setId(id);
		
		return messageSourceDao.add(messageSource);
	}

	@Override
	public MessageSource get(Long id) {	
		return messageSourceDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return messageSourceDao.delete(id);
	}
	
	@Override
	public boolean update(MessageSource messageSource) {
		return messageSourceDao.update(messageSource);
	}
	
	@Override
	public Page<MessageSource> page(MessageSourceQuery query, int start, int count) {
		return messageSourceDao.page(query, start, count);
	}
	
	public List<MessageSource> listAll(){
		return messageSourceDao.listAll();
	}
}

